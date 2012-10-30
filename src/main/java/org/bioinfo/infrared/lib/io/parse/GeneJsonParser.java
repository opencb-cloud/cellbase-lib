package org.bioinfo.infrared.lib.io.parse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bioinfo.commons.io.utils.FileUtils;
import org.bioinfo.formats.core.feature.Gtf;
import org.bioinfo.formats.core.feature.io.GtfReader;
import org.bioinfo.formats.exception.FileFormatException;
import org.bioinfo.infrared.lib.common.Exon;
import org.bioinfo.infrared.lib.common.Gene;
import org.bioinfo.infrared.lib.common.Transcript;
import org.bson.types.BasicBSONList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GeneJsonParser {

	List<Gene> genes;
	Map<String, Integer> geneDict;
	Map<String, Integer> transcriptDict;
	Map<String, Exon> exonDict;

	public GeneJsonParser() {
		init();
	}

	private void init() {
		genes = new ArrayList<Gene>(60000);
		geneDict = new HashMap<String, Integer>(60000);
		transcriptDict = new HashMap<String, Integer>(240000);
		exonDict = new HashMap<String, Exon>(7200000);
	}

	public String parseToJson(File file) throws IOException, SecurityException, NoSuchMethodException, FileFormatException {
		FileUtils.checkFile(file);
		init();

		String geneId;
		String transcriptId;

		Gene gene;
		Transcript transcript;
		Exon exon = null;
		int cdna = 1;
		int cds = 1;
		
		BasicBSONList list = new BasicBSONList();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		GtfReader gtfReader = new GtfReader(file);		
		Gtf gtf;
		while((gtf = gtfReader.read()) != null) {
			geneId = gtf.getAttributes().get("gene_id");
			transcriptId = gtf.getAttributes().get("transcript_id");

			// Check if gene exist en Map
			if(!geneDict.containsKey(geneId)) {
				gene = new Gene(geneId, gtf.getAttributes().get("gene_name"), gtf.getAttributes().get("gene_biotype"), 
						"KNOWN", gtf.getSequenceName().replaceFirst("chr", ""), gtf.getStart(), gtf.getEnd(), gtf.getStrand(), "Ensembl", "", new ArrayList<Transcript>());
				genes.add(gene);
				//
				list.add(gene);
				
				// Do not change order!!   size()-1 is the index of the gene ID
				geneDict.put(geneId, genes.size()-1);
			}else {
				gene = genes.get(geneDict.get(geneId));
			}

			//			// Check if Transcript exist in the Gene Set of transcripts
			if(!transcriptDict.containsKey(transcriptId)) {
				transcript = new Transcript(transcriptId, gtf.getAttributes().get("transcript_name"), 
						gtf.getSource(), "KNOWN", gtf.getSequenceName().replaceFirst("chr", ""), gtf.getStart(), gtf.getEnd(), gtf.getStrand(), 0, 0, 0, 0, "", "", new ArrayList<Exon>());
				gene.getTranscripts().add(transcript);
				// Do not change order!!   size()-1 is the index of the transcript ID
				transcriptDict.put(transcriptId, gene.getTranscripts().size()-1);
			}else {
				transcript = gene.getTranscripts().get(transcriptDict.get(transcriptId));
			}

			// At this point gene and transcript objects are set up

			// Update gene and transcript genomic coordinates, start must be the lower, and end the higher
			updateTranscriptAndGeneCoords(transcript, gene, gtf);

			if(gtf.getFeature().equalsIgnoreCase("exon")) {
				exon = new Exon(gtf.getAttributes().get("exon_id"), gtf.getSequenceName().replaceFirst("chr", ""), gtf.getStart(), gtf.getEnd(), gtf.getStrand(), 0, 0, 0, 0, 0, 0, -1, Integer.parseInt(gtf.getAttributes().get("exon_number")));
				transcript.getExons().add(exon);
				exonDict.put(transcript.getStableId()+"_"+exon.getExonNumber(), exon);
				if(gtf.getAttributes().get("exon_number").equals("1")) {
					cdna = 1;
					cds = 1;
				}else {
					// with every exon we update cDNA length with the previous exon length
					cdna += exonDict.get(transcript.getStableId()+"_"+(exon.getExonNumber()-1)).getEnd() - exonDict.get(transcript.getStableId()+"_"+(exon.getExonNumber()-1)).getStart() + 1;
				}
			}else {
				exon = exonDict.get(transcript.getStableId()+"_"+exon.getExonNumber());
				if(gtf.getFeature().equalsIgnoreCase("CDS")) {
					// CDS states the beginning of coding start
					exon.setGenomicCodingStart(gtf.getStart());
					exon.setGenomicCodingEnd(gtf.getEnd());
					
					// cDNA coordinates
					exon.setCdnaCodingStart(gtf.getStart()-exon.getStart()+cdna);
					exon.setCdnaCodingEnd(gtf.getEnd()-exon.getStart()+cdna);
					
					exon.setCdsStart(cds);
					exon.setCdsEnd(gtf.getEnd()-gtf.getStart()+cds);
					
					// increment in the coding length
					cds += gtf.getEnd()-gtf.getStart()+1;
					
					exon.setPhase(Integer.parseInt(gtf.getFrame()));
					transcript.setGenomicCodingStart(gtf.getStart());
					transcript.setGenomicCodingEnd(gtf.getEnd());
					transcript.setProteinID(gtf.getAttributes().get("protein_id"));
				}
				if(gtf.getFeature().equalsIgnoreCase("start_codon")) {
					if(exon.getStrand().equals("+")) {
//						exon.setCodingRegionStart(gtf.getStart());
//						transcript.setCodingRegionStart(gtf.getStart());						
					}else {
//						exon.setCodingRegionEnd(gtf.getEnd());
//						transcript.setCodingRegionEnd(gtf.getEnd());
					}
				}
				if(gtf.getFeature().equalsIgnoreCase("stop_codon")) {
					if(exon.getStrand().equals("+")) {
						exon.setGenomicCodingEnd(gtf.getEnd());
						transcript.setGenomicCodingEnd(gtf.getEnd());						
					}else {
//						exon.setCodingRegionEnd(gtf.getEnd());
//						transcript.setCodingRegionStart(gtf.getStart());
					}
				}
			}
		}

		gtfReader.close();
//		System.out.println(JSON.serialize(genes.get(0)));
//		return JSON.serialize(genes);
		return gson.toJson(genes);
	}

	private void updateTranscriptAndGeneCoords(Transcript transcript, Gene gene, Gtf gtf) {
		if(transcript.getStart() > gtf.getStart()) {
			transcript.setStart(gtf.getStart());
		}
		if(transcript.getEnd() < gtf.getEnd()) {
			transcript.setEnd(gtf.getEnd());
		}
		if(gene.getStart() > gtf.getStart()) {
			gene.setStart(gtf.getStart());
		}
		if(gene.getEnd() < gtf.getEnd()) {
			gene.setEnd(gtf.getEnd());
		}
	}
}


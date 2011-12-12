package org.bioinfo.infrared.lib.io.output;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.Cytoband;
import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.GenomeSequence;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.Transcript;
import org.bioinfo.infrared.serialization.InfraredSerializer;

public class StringWriter {

	public static String serialize(Gene gene){
		return new StringBuilder().append(gene.getStableId()).append("\t").append(gene.getExternalName()).append("\t").append(gene.getExternalDb()).append("\t").append(gene.getBiotype()).append("\t").append(gene.getStatus()).append("\t").append(gene.getChromosome()).append("\t").append(gene.getStart()).append("\t").append(gene.getEnd()).append("\t").append(gene.getStrand()).append("\t").append(gene.getSource()).append("\t").append(gene.getDescription()).toString(); 
	}
	
	public static String serialize(Exon exon){
		return new StringBuilder().append(exon.getStableId()).append("\t").append(exon.getChromosome()).append("\t").append(exon.getStart()).append("\t").append(exon.getEnd()).append("\t").append(exon.getStrand()).toString(); 
	}
	
	public static String serialize(Cytoband cytoband){
		return new StringBuilder().append(cytoband.getCytoband()).append("\t").append(cytoband.getChromosome()).append("\t").append(cytoband.getStart()).append("\t").append(cytoband.getEnd()).append("\t").append(cytoband.getStain()).toString();
	}
	
	public static String serialize(GenomeSequence genomeSequence){
		return new StringBuilder().append(genomeSequence.getId().getChromosome()).append("\t").append(genomeSequence.getId().getChunk()).append("\t").append(genomeSequence.getStart()).append("\t").append(genomeSequence.getEnd()).append(genomeSequence.getSequence()).toString();
	}
	
	public static String serialize(Snp snp){
		return new StringBuilder().append(snp.getName()).append("\t")
									.append(snp.getChromosome()).append("\t")
									.append(snp.getStart()).append("\t")
									.append(snp.getDisplayConsequence()).append("\t")
									.append(snp.getDisplaySoConsequence()).append("\t")
									.append(snp.getSequence())
									.toString(); 
	}
	
	public static String serialize(Transcript transcript){
		return new StringBuilder().append(transcript.getStableId()).append("\t")
								  .append(transcript.getExternalName()).append("\t")
								  .append(transcript.getExternalDb()).append("\t")
								  .append(transcript.getBiotype()).append("\t")
								  .append(transcript.getStatus()).append("\t")
								  .append(transcript.getChromosome()).append("\t")
								  .append(transcript.getStart()).append("\t")
								  .append(transcript.getEnd()).append("\t")
								  .append(transcript.getStrand()).append("\t")
								  .append(transcript.getCodingRegionStart()).append("\t")
								  .append(transcript.getCodingRegionEnd()).append("\t")
								  .append(transcript.getCdnaCodingStart()).append("\t")
								  .append(transcript.getCdnaCodingEnd()).append("\t")
								  .append(transcript.getDescription()).append("\t")
								  .toString();
		
		
	}
	
	public static String serialize(String string){
		return string;
	}
	
	public static String serialize(List<?> list){
		StringBuilder sb = new StringBuilder();
		for (Object object : list) {
			if (object instanceof Gene){
				sb.append(StringWriter.serialize((Gene) object)).append("\n");
				continue;
			}
			if (object instanceof Exon){
				sb.append(StringWriter.serialize((Exon) object)).append("\n");
				continue;
			}
			if (object instanceof Cytoband){
				sb.append(StringWriter.serialize((Cytoband) object)).append("\n");
				continue;
			}
			if (object instanceof GenomeSequence){
				sb.append(StringWriter.serialize((GenomeSequence) object)).append("\n");
				continue;
			}
			if (object instanceof Transcript){
				sb.append(StringWriter.serialize((Transcript) object)).append("\n");
				continue;
			}
			if (object instanceof Snp){
				sb.append(StringWriter.serialize((Snp) object)).append("\n");
				continue;
			}
			
			if (object instanceof String){
				sb.append(StringWriter.serialize((String) object)).append("\n");
				continue;
			}
			
			if (object instanceof List){
				sb.append(StringWriter.serialize((List)object));
				continue;
			}
		}
		return sb.toString();
	}

}

package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.ExonToTranscript;
import org.bioinfo.infrared.core.cellbase.FeatureMap;
import org.bioinfo.infrared.core.cellbase.FeatureMapId;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.RegulatoryRegion;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.Tfbs;
import org.bioinfo.infrared.core.cellbase.Transcript;
import org.bioinfo.infrared.lib.api.GenomicRegionFeatureDBAdaptor;
import org.bioinfo.infrared.lib.common.DNASequenceUtils;
import org.bioinfo.infrared.lib.common.GenomeSequenceFeature;
import org.bioinfo.infrared.lib.common.GenomicRegionFeatures;
import org.bioinfo.infrared.lib.common.Region;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class GenomicRegionFeatureHibernateDBAdaptor extends HibernateDBAdaptor implements GenomicRegionFeatureDBAdaptor {
	private static int FEATURE_MAP_CHUNK_SIZE = 400;
	
	public GenomicRegionFeatureHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	

	@Override
	public List<GenomicRegionFeatures> getAllByRegionList(List<Region> regions) {
		return this.getAllByRegionList(regions, null);
	}

	@Override
	public List<GenomicRegionFeatures> getAllByRegionList(List<Region> regions, List<String> sources) {
		 List<GenomicRegionFeatures> result = new ArrayList<GenomicRegionFeatures>();
		 for (int i = 0; i < regions.size(); i++) {
			 result.add(this.getByRegion(regions.get(i), sources));
		 }
		return result;
	}

	
	@Override
	public GenomicRegionFeatures getByRegion(String chromosome, int start, int end) {
		return getByRegion(chromosome, start, end, null);
	}

	@Override
	public GenomicRegionFeatures getByRegion(Region region) {
		return getByRegion(region.getChromosome(), region.getStart(), region.getEnd(), null);
	}

	@Override
	public GenomicRegionFeatures getByRegion(Region region, List<String> sources) {
		return getByRegion(region.getChromosome(), region.getStart(), region.getEnd(), sources);
	}
	
	private GenomicRegionFeatures getByRegion(String chromosome, int start, int end, List<String> sources) {
		int chunk_start = start / GenomicRegionFeatureHibernateDBAdaptor.FEATURE_MAP_CHUNK_SIZE;
		int chunk_end = end / GenomicRegionFeatureHibernateDBAdaptor.FEATURE_MAP_CHUNK_SIZE;
		
//		System.out.println("Chunks: " + chunk_start + "   " + chunk_end);
		
		Query query = this.openSession()
		//.createQuery("select featureMap from FeatureMap as featureMap left join fetch featureMap.id  where id.chunkId >= :start_chunk and id.chunkId <= :end_chunk and featureMap.start<= :endparam and featureMap.end >= :startparam and chromosome=:chromosome")
		
		//TODO:IF PARA SI EL CHUNKID ENTONCES USAR =
		.createQuery("select featureMap from FeatureMap as featureMap where id.chunkId >= :start_chunk and id.chunkId <= :end_chunk and featureMap.start<= :endparam and featureMap.end >= :startparam and chromosome=:chromosome")
		.setParameter("start_chunk", chunk_start)
		.setParameter("end_chunk", chunk_end)
		.setParameter("startparam", start)
		.setParameter("endparam", end)
		.setParameter("chromosome", chromosome);
		List<FeatureMap> list = (List<FeatureMap>)executeAndClose(query);
		
	
		GenomicRegionFeatures genomicRegionFeatures = this.getGenomicRegionFeature(list, new Region(chromosome, start, end), sources);
		return genomicRegionFeatures;
	}
	
	
	
	private GenomicRegionFeatures getGenomicRegionFeature(List<FeatureMap> featuresMap, Region region, List<String> sources) {
		GenomicRegionFeatures genomicRegionFeatures = new GenomicRegionFeatures(region);
		
		
		
		/** obtengo todos los id's de los featuremap **/
		List<String> genesIds = new ArrayList<String>();
		List<String> transcriptsIds = new ArrayList<String>();
		List<String> exonsIds = new ArrayList<String>();
		List<String> snpsIds = new ArrayList<String>();
		List<String> tfbsIds = new ArrayList<String>();
		List<String> regulatoryIds = new ArrayList<String>();
		
		for (FeatureMap featureMap : featuresMap) {
			if (featureMap.getId().getSource().equals("gene")){
				genesIds.add(featureMap.getFeatureId());
			}
			
			if (featureMap.getId().getSource().equals("transcript")){
				transcriptsIds.add(featureMap.getFeatureId());
			}
			
			if (featureMap.getId().getSource().equals("exon")){
				exonsIds.add(featureMap.getFeatureId());
			}
			
			if (featureMap.getId().getSource().equals("snp")){
				snpsIds.add(featureMap.getFeatureId());
			}
			
			if (featureMap.getId().getSource().equals("tfbs")){
				tfbsIds.add(String.valueOf(featureMap.getId().getSourceId()));
			}
			
			if (featureMap.getId().getSource().equals("regulatory_region")){
				regulatoryIds.add(String.valueOf(featureMap.getId().getSourceId()));
			}
		}
		
		/** Inicializo GenomicRegionFeatures, para las listas null si no estoy preguntando por esos sources y list(0) si realmente estoy preguntando por ellos**/
		if (sources != null){
			for (String source : sources) {
				if (source.equalsIgnoreCase("gene")){
					genomicRegionFeatures.setGenes(new ArrayList<Gene>());
					genomicRegionFeatures.getGenes().addAll(new GeneHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(genesIds));
				}
				
				if (source.equalsIgnoreCase("transcript")){
					genomicRegionFeatures.setTranscripts(new ArrayList<Transcript>());
					genomicRegionFeatures.getTranscripts().addAll(new TranscriptHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(transcriptsIds));
				}
				
				if (source.equalsIgnoreCase("exon")){
					genomicRegionFeatures.setExons(new ArrayList<Exon>());
					genomicRegionFeatures.getExons().addAll(new ExonHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(exonsIds));
				}
				
				if (source.equalsIgnoreCase("tfbs")){
					genomicRegionFeatures.setTfbs(new ArrayList<Tfbs>());
					
					genomicRegionFeatures.getTfbs().addAll(new TfbsHibernateDBAdaptor(this.getSessionFactory()).getAllByInternalIdList(tfbsIds));
				}
				
				if (source.equalsIgnoreCase("regulatory_region")){
					genomicRegionFeatures.setRegulatoryRegion(new ArrayList<RegulatoryRegion>());
					
					genomicRegionFeatures.setRegulatoryRegion(new RegulatoryRegionHibernateDBAdaptor(this.getSessionFactory()).getAllByInternalIdList(tfbsIds));
				}
				
				if (source.equalsIgnoreCase("snp")){
					genomicRegionFeatures.setSnp(new ArrayList<Snp>());
					
					List<List<Snp>> snpResult = new SnpHibernateDBAdapator(this.getSessionFactory()).getByDbSnpIdList(snpsIds);
					genomicRegionFeatures.getSnp().addAll(this.cleanSnpByRegion(region, snpResult));
				}
			}
		}
		else{
			genomicRegionFeatures.setGenes(new ArrayList<Gene>());
			genomicRegionFeatures.setTranscripts(new ArrayList<Transcript>());
			genomicRegionFeatures.setExons(new ArrayList<Exon>());
			genomicRegionFeatures.setSnp(new ArrayList<Snp>());
			genomicRegionFeatures.setTfbs(new ArrayList<Tfbs>());
			genomicRegionFeatures.setRegulatoryRegion(new ArrayList<RegulatoryRegion>());
			
			
			genomicRegionFeatures.getGenes().addAll(new GeneHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(genesIds));
			genomicRegionFeatures.getTranscripts().addAll(new TranscriptHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(transcriptsIds));
			genomicRegionFeatures.getExons().addAll(new ExonHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(exonsIds));
			genomicRegionFeatures.getTfbs().addAll(new TfbsHibernateDBAdaptor(this.getSessionFactory()).getAllByInternalIdList(tfbsIds));
			genomicRegionFeatures.setRegulatoryRegion(new RegulatoryRegionHibernateDBAdaptor(this.getSessionFactory()).getAllByInternalIdList(regulatoryIds));
			
			List<List<Snp>> snpResult = new SnpHibernateDBAdapator(this.getSessionFactory()).getByDbSnpIdList(snpsIds);
			genomicRegionFeatures.getSnp().addAll(this.cleanSnpByRegion(region, snpResult));
		}
		
		
		return genomicRegionFeatures;
	}
	
	private List<Snp> cleanSnpByRegion(Region region, List<List<Snp>> snpResult) {
		// Es posible que para el mismo dbName nos devuelva varios snp's de regiones diferentes, filtro los que esten dentro de la region indicada
		List<Snp> snps = new ArrayList<Snp>();
		for (List<Snp> list : snpResult) {
			if (list != null){
				for (Snp snp : list) {
					if (snp != null){
						if (snp.getChromosome().equals(region.getChromosome())){
							if (region.getStart() <=snp.getStart() && (region.getEnd() >= snp.getEnd())){
								snps.add(snp);
							}
						}
					}
				}
			}
		}
		return snps;
	}

	
	
	
	
	/** Consequence Type by Position **/
	
	@SuppressWarnings("unused")
	private void addElement(String key, String value, HashMap<String, List<String>> collection ){
		if(!collection.containsKey(key)){
			collection.put(key, new ArrayList<String>());
		}
		collection.get(key).add(value);
	}
	
	private List<Exon> getExonByPosition(List<Exon> exons, String chromosome, int position){
		List<Exon> result = new ArrayList<Exon>();
		for (Exon exon : exons) {
			if(exon.getChromosome().equals(chromosome)){
				if ((exon.getStart()<= position)&&(exon.getEnd()>=position)){
					result.add(exon);
				}
			}
		}
		return result;
	}

	
	
	private int getCodonByPosition(Transcript transcript, List<Exon> exons,  int position){
		int cdna_length = 0;
//		System.out.println("-----------------------------------------");
		System.out.println("TRANSCRIPT: " + transcript.getStableId());
//		for (Exon exonIntranscript : exons) {
		if (transcript.getStrand().equals("-1")){
			for (int i = exons.size() - 1; i >= 0; i--) {
				Exon exonIntranscript = exons.get(i);
//				System.out.println("EXON PROCESANDO: " + exonIntranscript.getStableId());
				if (position < exonIntranscript.getStart()){
					/** Primer exon **/
					if ((exonIntranscript.getEnd() >= transcript.getCodingRegionEnd()) && (exonIntranscript.getStart() <= transcript.getCodingRegionEnd())){
						cdna_length = cdna_length + (transcript.getCodingRegionEnd() - exonIntranscript.getStart()) +1 ;
						System.out.println("\t  EXON: " + exonIntranscript.getStableId()+ " " + cdna_length);
					}
					
					/** Los demas **/
					if ((exonIntranscript.getEnd() < transcript.getCodingRegionEnd()) && (exonIntranscript.getStart() > transcript.getCodingRegionStart())){
						cdna_length = cdna_length + (exonIntranscript.getEnd() - exonIntranscript.getStart()) + 1;
						System.out.println("\t +EXON: " + exonIntranscript.getStableId()+ " " + cdna_length);
					}
				}
				else{
					
					if ((exonIntranscript.getEnd() >= transcript.getCodingRegionEnd())&&(exonIntranscript.getStart()<= transcript.getCodingRegionStart())){
						cdna_length = cdna_length + (transcript.getCodingRegionEnd() - position ) + 1;
					}
					else{
						cdna_length = cdna_length + ( exonIntranscript.getEnd() - position) + 1;
						System.out.println("\t *EXON: " + exonIntranscript.getStableId()+ " " + cdna_length);
	//					System.out.println(transcript.getStableId() + "   Phase: " + (cdna_length%3)); 
					break;
					}
					
				}
			}	
		}
		else{
			
			for (int i = 0; i < exons.size(); i++) {
				Exon exonIntranscript = exons.get(i);
				if (position > exonIntranscript.getEnd()){
					/** Primer exon **/
					if ((exonIntranscript.getStart() <= transcript.getCodingRegionStart()) && (exonIntranscript.getEnd() >= transcript.getCodingRegionStart())){
						cdna_length = cdna_length + (exonIntranscript.getEnd() - transcript.getCodingRegionStart()) +1 ;
						System.out.println("\t  EXON: " + exonIntranscript.getStableId()+ " " + cdna_length);
					}
					
					/** Los demas **/
					if ((exonIntranscript.getStart() >  transcript.getCodingRegionStart())&&(exonIntranscript.getEnd() <= transcript.getCodingRegionEnd())){
						cdna_length = cdna_length + (exonIntranscript.getEnd() - exonIntranscript.getStart()) + 1;
						System.out.println("\t +EXON: " + exonIntranscript.getStableId()+ " " + cdna_length);
					}
				}
				else{
					if ((exonIntranscript.getStart() <= transcript.getCodingRegionStart())&&(exonIntranscript.getEnd()>= transcript.getCodingRegionEnd())){
						cdna_length = cdna_length + (position -  transcript.getCodingRegionStart()) + 1;
					}
					else{
						cdna_length = cdna_length + (position - exonIntranscript.getStart()) + 1;
						System.out.println("\t *EXON: " + exonIntranscript.getStableId()+ " " + cdna_length);
	//					System.out.println(transcript.getStableId() + "   Phase: " + (cdna_length%3)); 
					break;
					}
				}
				
			}
			
			
		}
		System.out.println("CDNA codon modulo: " +  cdna_length % 3 );
		return cdna_length % 3 ;
		
		
	}
	
	private List<String> getConsequenceTypeByAlternativeAllele(Transcript transcript, List<Exon> exons, int position, String alternativeAllele) {
		int codon_position = this.getCodonByPosition(transcript, exons, position);
		 List<String> consequenceTypes = new ArrayList<String>();
		
		
		GenomeSequenceDBAdaptor sequenceDbAdaptor = new GenomeSequenceDBAdaptor(this.getSessionFactory());
		
		GenomeSequenceFeature sequence = null;
		
		if (transcript.getStrand().equals("-1")){
			if (codon_position == 1){
				sequence = sequenceDbAdaptor.getByRegion(transcript.getChromosome(), position - 2, position);
			}
			
			if (codon_position == 2){
				sequence = sequenceDbAdaptor.getByRegion(transcript.getChromosome(), position - 1, position + 1);
			}
			/** Caso del 3 **/
			if (codon_position == 0){
				sequence = sequenceDbAdaptor.getByRegion(transcript.getChromosome(), position, position + 2);
				codon_position = 3;
			}
			
			sequence.setSequence(GenomeSequenceDBAdaptor.getRevComp(sequence.getSequence()));
			alternativeAllele = GenomeSequenceDBAdaptor.getRevComp(alternativeAllele);
		}
		else{
			if (codon_position == 1){
				sequence = sequenceDbAdaptor.getByRegion(transcript.getChromosome(), position, position + 2);
			}
			
			if (codon_position == 2){
				sequence = sequenceDbAdaptor.getByRegion(transcript.getChromosome(), position - 1, position + 1);
			}
			/** Caso del 3 **/
			if (codon_position == 0){
				sequence = sequenceDbAdaptor.getByRegion(transcript.getChromosome(), position - 2, position);
				codon_position = 3;
			}
			
		}
		
		
		String referenceSequence = sequence.getSequence();
	
		char[] referenceSequenceCharArray = referenceSequence.toCharArray();
		referenceSequenceCharArray[codon_position - 1] = alternativeAllele.toCharArray()[0]; 
		
		
		String alternative = new String();
		for (int i = 0; i < referenceSequenceCharArray.length; i++) {
			alternative = alternative + referenceSequenceCharArray[i];
		}
			
		referenceSequence = referenceSequence.replaceAll("T", "U");
		alternative = alternative.replaceAll("T", "U");
		
		
		consequenceTypes.add("coding_sequence_variant");
		
		if (DNASequenceUtils.codonToAminoacidShort.get(referenceSequence).equals(DNASequenceUtils.codonToAminoacidShort.get(alternative))){
			consequenceTypes.add("SYNONYMOUS_CODING (" + DNASequenceUtils.codonToAminoacidShort.get(referenceSequence) + ")");
		}
		else{
			consequenceTypes.add("NON_SYNONYMOUS_CODING (" + DNASequenceUtils.codonToAminoacidShort.get(referenceSequence) + "," + DNASequenceUtils.codonToAminoacidShort.get(alternative) + ")");
			if ((!DNASequenceUtils.codonToAminoacidShort.get(referenceSequence).toLowerCase().equals("stop"))&& (DNASequenceUtils.codonToAminoacidShort.get(alternative).toLowerCase().equals("stop"))){
				consequenceTypes.add("STOP_GAINED");
			}
			
			if ((DNASequenceUtils.codonToAminoacidShort.get(referenceSequence).toLowerCase().equals("stop"))&& (!DNASequenceUtils.codonToAminoacidShort.get(alternative).toLowerCase().equals("stop"))){
				consequenceTypes.add("STOP_LOST");
			}
		}
//		System.out.println("Reference:" + referenceSequence );
//		System.out.println("Alternative:" +alternative);
//		
//		System.out.println("Reference:" + DNASequenceUtils.codonToAminoacidShort.get(referenceSequence) );
//		System.out.println("Alternative:" + DNASequenceUtils.codonToAminoacidShort.get(alternative));
		return consequenceTypes;
	}
	
	
	private List<String> getConsequenceType(Transcript transcript, String chromosome, int position, String alternativeAllele){
		 List<String> consequenceTypes = new ArrayList<String>();
		
		ExonHibernateDBAdaptor dbaExonAdaptor = new ExonHibernateDBAdaptor(this.getSessionFactory());
		List<Exon> exons = dbaExonAdaptor.getByEnsemblTranscriptId(transcript.getStableId());
		
		List<Exon> exonsByPosition = this.getExonByPosition(exons, chromosome, position);
		
		
		if (transcript.getBiotype().equals("nonsense_mediated_decay")){
			consequenceTypes.add("NMD_TRANSCRIPT");
			
		}
		
		
		if(exonsByPosition.size() == 0){
			consequenceTypes.add("INTRONIC");
			
			for (Exon exon2 : exons) {
				if(transcript.getStrand().equals("-1")){
					if (exon2.getStart() > position){
							if (exon2.getStart() - position < 2){
									consequenceTypes.add("SPLICE_DONOR_VARIANT");
							}
							if ((exon2.getStart() - position >= 2)&&(exon2.getStart() - position <= 7)){
								consequenceTypes.add("SPLICE_REGION_VARIANT");
							}
					}
					
					if (exon2.getEnd() < position){
						if ( position - exon2.getEnd() < 2){
							consequenceTypes.add("SPLICE_ACCEPTOR_VARIANT");
						}
						
						if (( position - exon2.getEnd() >= 2) && ( position - exon2.getEnd() <= 7)){
							consequenceTypes.add("SPLICE_REGION_VARIANT");
						}
					}
				}
				else{
					if (exon2.getStart() > position){
						if (exon2.getStart() - position < 2){
								consequenceTypes.add("SPLICE_ACCEPTOR_VARIANT");
						}
						if ((exon2.getStart() - position >= 2)&&(exon2.getStart() - position <= 7)){
							consequenceTypes.add("SPLICE_REGION_VARIANT");
						}
				}
				
				if (exon2.getEnd() < position){
					if ( position - exon2.getEnd() < 2){
						consequenceTypes.add("SPLICE_DONOR_VARIANT");
					}
					
					if (( position - exon2.getEnd() >= 2) && ( position - exon2.getEnd() <= 7)){
						consequenceTypes.add("SPLICE_REGION_VARIANT");
					}
					
				}
					
				}
			}
		}
		else{
			if ((transcript.getCodingRegionStart() == 0)&&( transcript.getCdnaCodingEnd() ==0)){
				consequenceTypes.add("WITHIN_NON_CODING_GENE");
			}
			else{
				if (transcript.getStrand().equals("-1")){
					if (transcript.getCodingRegionStart() > position) {
						consequenceTypes.add("3_PRIME_UTR");
					}
	
					if (transcript.getCodingRegionEnd() < position) {
						consequenceTypes.add("5_PRIME_UTR");
					}
				}
				else{
					/** Strand + **/
					if (transcript.getCodingRegionStart() > position) {
						consequenceTypes.add("5_PRIME_UTR");
					}
	
					if (transcript.getCodingRegionEnd() < position) {
						consequenceTypes.add("3_PRIME_UTR");
					}
				}
				
				if ((transcript.getCodingRegionStart() <= position) && (transcript.getCodingRegionEnd() >= position)){
					
					
					for (Exon exon : exonsByPosition) {
						System.out.println("exon position: " + exon.getStableId() + "  " + exon.getEnd() + " " +position +"  " + ((exon.getEnd() - position <= 2)&&((exon.getEnd() - position >= 0)))) ;
						if ((position - exon.getStart() <= 2)&&((position - exon.getStart() >= 0))){
							consequenceTypes.add("SPLICE_REGION_VARIANT");
						}
						
						if ((exon.getEnd() - position <= 2)&&((exon.getEnd() - position >= 0))){
							consequenceTypes.add("SPLICE_REGION_VARIANT");
						}
					}
					
					
					if (alternativeAllele != null){
						consequenceTypes.addAll(this.getConsequenceTypeByAlternativeAllele(transcript, exons,  position, alternativeAllele));
					}
				}
			}
		}
		return consequenceTypes;
	}


	@Override
	public HashMap<String, List<String>> getConsequenceType(String chromosome, int position, String alternativeAllele){
		GenomicRegionFeatures maps = this.getByRegion(new Region(chromosome,position, position));
		HashMap<String, List<String>> types = new HashMap<String, List<String>>();

		DBAdaptorFactory dbAdaptorFact = new HibernateDBAdaptorFactory();
		ExonHibernateDBAdaptor dbaExonAdaptor = new ExonHibernateDBAdaptor(this.getSessionFactory());
		
		if ((maps.getTranscripts().size() > 0)) {
//		if ((maps.getTranscripts().size() > 0)&&(maps.getSnp().size() == 0)) {
			for (Transcript transcript : maps.getTranscripts()) {
				types.put(transcript.getStableId(), this.getConsequenceType(transcript, chromosome, position, alternativeAllele.toUpperCase().trim()));
			}
		}
		
		if (maps.getSnp().size() > 0){
			for (Snp snp : maps.getSnp()) {
				types.put(snp.getName(), Arrays.asList(snp.getSoConsequenceType().split(",")));
			}
		}
		
		if(maps.getTfbs().size() > 0){
			for (Tfbs tfbs : maps.getTfbs()) {
				types.put(tfbs.getTfName(), Arrays.asList("TRANSCRIPTION_FACTOR_BINDING_MOTIF"));
			}
		}
		
		if(maps.getRegulatoryRegion().size() > 0){
			for (RegulatoryRegion regulatory : maps.getRegulatoryRegion()) {
				types.put(regulatory.getName(), Arrays.asList("REGULATORY_REGION"));
			}
			
			
			for (RegulatoryRegion regulatory : maps.getHistones()){
				types.put(regulatory.getName(), Arrays.asList("histone_binding_site"));
			}
			
			for (RegulatoryRegion regulatory : maps.getOpenChromatin()){
				types.put(regulatory.getName(), Arrays.asList("open_chromatin_region"));
			}
			
			for (RegulatoryRegion regulatory : maps.getPolimerase()){
				types.put(regulatory.getName(), Arrays.asList("TRANSCRIPTION_FACTOR_BINDING_MOTIF"));
			}
		}
		
		return types;
	}


	@Override
	public HashMap<String, List<String>> getConsequenceType(String chromosome,int position) {
		return this.getConsequenceType(chromosome, position, null);

	}
}

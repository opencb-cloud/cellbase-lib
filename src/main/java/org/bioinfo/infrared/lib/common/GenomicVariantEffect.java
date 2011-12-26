package org.bioinfo.infrared.lib.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.RegulatoryRegion;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.Tfbs;
import org.bioinfo.infrared.core.cellbase.Transcript;
import org.bioinfo.infrared.lib.api.ExonDBAdaptor;
import org.bioinfo.infrared.lib.api.GenomicRegionFeatureDBAdaptor;
import org.bioinfo.infrared.lib.api.TranscriptDBAdaptor;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.bioinfo.infrared.lib.impl.hibernate.ExonHibernateDBAdaptor;
import org.bioinfo.infrared.lib.impl.hibernate.GenomeSequenceDBAdaptor;
import org.bioinfo.infrared.lib.impl.hibernate.GenomicRegionFeatures;
import org.bioinfo.infrared.lib.impl.hibernate.HibernateDBAdaptorFactory;

public class GenomicVariantEffect {

	private int position;
	private GenomicRegionFeatures features;

	private DBAdaptorFactory dbAdaptorFact;
	private GenomicRegionFeatureDBAdaptor genomicRegionFeatureDBAdaptor;
	private ExonDBAdaptor exonAdaptor;
	private GenomeSequenceDBAdaptor sequenceDbAdaptor;
	
	/** For consequence types **/
	private List<ConsequenceTypeResult> consequences; 
	
	
	/** Acelerador de los transcripts **/
	private TranscriptDBAdaptor transcriptAdaptor;
	private List<Transcript> transcripts;
	private HashMap<String, Transcript> transcriptHash = new HashMap<String, Transcript>();
	private int counter;
	
	public GenomicVariantEffect(String species){
		dbAdaptorFact = new HibernateDBAdaptorFactory();
		genomicRegionFeatureDBAdaptor = dbAdaptorFact.getFeatureMapDBAdaptor(species);
		transcriptAdaptor = dbAdaptorFact.getTranscriptDBAdaptor(species);
		exonAdaptor = dbAdaptorFact.getExonDBAdaptor(species);
		this.sequenceDbAdaptor = dbAdaptorFact.getGenomeSequenceDBAdaptor(species);
		
		this.counter = 0;
	}
	
	
	public List<ConsequenceTypeResult> getConsequenceType(List<GenomicVariant> variants){
		
		this.transcripts = transcriptAdaptor.getAll();
		System.out.println("All transcripts " + this.transcripts.size());
		
		for (Transcript transcript : this.transcripts) {
			this.transcriptHash.put(transcript.getStableId(), transcript);
		}
		List<ConsequenceTypeResult> result = new ArrayList<ConsequenceTypeResult>();
		
		int chunk = 50;
		
		/** Con esto nos ahorramos el open and close session **/
		for (int i = 0; i < variants.size(); i = i + chunk) {
			
			int end = i +chunk;
			if (i+chunk > variants.size()){
				end = variants.size();
			}
			List<GenomicVariant> variantsChunk = variants.subList(i, end);
			Long t0 = System.currentTimeMillis();
			List<GenomicRegionFeatures> genomicRegionMap = genomicRegionFeatureDBAdaptor.getByVariants(variantsChunk);
			System.out.println("Recovering features map chunk("+ (end - i)  +"): "+(System.currentTimeMillis()-t0)+" ms " +   genomicRegionMap.size());
			
			for (int j = 0; j < genomicRegionMap.size(); j++) {
				result.addAll(this.getConsequenceType(variantsChunk.get(j), genomicRegionMap.get(j)));
			}
		}
		
		return result;
	}
	
	private List<ConsequenceTypeResult> getConsequenceType(GenomicVariant variant){
		this.setFeatures(genomicRegionFeatureDBAdaptor.getByRegion(variant.getChromosome(), variant.getStart(), variant.getStart()));
		return this.getConsequenceType(variant.getChromosome(), variant.getStart(), variant.getAlternative());
	}
	
	
	private List<ConsequenceTypeResult> getConsequenceType(GenomicVariant variant, GenomicRegionFeatures genomicRegionFeatures){
		this.setFeatures(genomicRegionFeatures);
		return this.getConsequenceType(variant.getChromosome(), variant.getStart(), variant.getAlternative());
	}
	
	
	
	public List<ConsequenceTypeResult> getConsequenceType(String chromosome, int position, String alternativeAllele){
		long t0 = System.currentTimeMillis();
//		this.setFeatures(genomicRegionFeatureDBAdaptor.getByRegion(chromosome, position, position));
//		System.out.println("Recovering features map: "+(System.currentTimeMillis()-t0)+" ms  in chromosome: "+chromosome+ "  position: "+position + " size: " + this.features.featuresMap.size());
		
		this.consequences = new ArrayList<ConsequenceTypeResult>();
		this.position = position;

		if (getFeatures().getTranscriptsIds() != null){
//			System.out.println("\tBefore getTranscriptsIds(): ");
			if ((getFeatures().getTranscriptsIds().size() > 0)) {
				for (String stableId : getFeatures().getTranscriptsIds()) {
					long t1 = System.currentTimeMillis();
					Transcript transcript = this.transcriptHash.get(stableId);
					this.getConsequenceType(transcript, chromosome, position, alternativeAllele.toUpperCase().trim());
//					System.out.println("\tAfter getTranscriptsIds(): "+(System.currentTimeMillis()-t1)+" ms " + this.consequences.get(this.consequences.size()-1).consequenceType);
				}
			}
		}
		
		
		if (getFeatures().getSnpsIds() != null){
			if (getFeatures().getSnpsIds().size() > 0){
				for (Snp snp : getFeatures().getSnp()) {
//					types.put(snp.getName(), Arrays.asList(snp.getSoConsequenceType().split(",")));
				}
			}
		}
		
		if(getFeatures().getTfbs() != null){
			if(getFeatures().getTfbs().size() > 0){
				for (Tfbs tfbs : getFeatures().getTfbs()) {
					this.addConsequenceType(tfbs, "TF_binding_site_variant", "SO:0001782", "A sequence variant located within a transcription factor binding site.", "REGULATORY");
				}
			}
		}
		
		if(getFeatures().getRegulatoryRegion() != null){
			if(getFeatures().getRegulatoryRegion().size() > 0){
				for (RegulatoryRegion regulatory : getFeatures().getRegulatoryRegion()) {
					this.addConsequenceType(regulatory, "regulatory_region_variant", "SO:0001566", "In regulatory region annotated by Ensembl", "REGULATORY" );
				}
				for (RegulatoryRegion regulatory : getFeatures().getHistones()){
					this.addConsequenceType(regulatory, "regulatory_region_variant", "SO:0001566", "In regulatory region annotated by Ensembl", "REGULATORY" );
				}
				for (RegulatoryRegion regulatory : getFeatures().getOpenChromatin()){
					this.addConsequenceType(regulatory, "OPEN CHROMATINE", "OPEN CHROMATINE OBO", "In regulatory region annotated by Ensembl", "REGULATORY" );
				}
				for (RegulatoryRegion regulatory : getFeatures().getPolimerase()){
					this.addConsequenceType(regulatory, "POLYMERASE", "POLYMERASE OBO", "In regulatory region annotated by Ensembl", "REGULATORY" );
				}
			}
		}
		
		return this.consequences;
	}
	
	private void getConsequenceType(Transcript transcript, String chromosome, int position, String alternativeAllele){
	
		List<Exon> exons = exonAdaptor.getByEnsemblTranscriptId(transcript.getStableId());
		List<Exon> exonsByPosition = this.getExonByPosition(exons, chromosome, position);
		
		if (transcript.getBiotype().equals("nonsense_mediated_decay")){
			this.addConsequenceType(transcript, "NMD_TRANSCRIPT", "SO:0001621", "Located within a transcript predicted to undergo nonsense-mediated decay", "consequenceTypeType" );
		}
		
		if(exonsByPosition.size() == 0){
			this.addConsequenceType(transcript, "intron_variant", "SO:0001627", "In intron", "consequenceTypeType" );
			
			
			for (Exon exon2 : exons) {
				if(transcript.getStrand().equals("-1")){
					if (exon2.getStart() > position){
							if (exon2.getStart() - position < 2){
								this.addConsequenceType(exon2, "splice_donor_variant", "SO:0001575", "In the first 2 or the last 2 basepairs of an intron", "consequenceTypeType" );
							}
							if ((exon2.getStart() - position >= 2)&&(exon2.getStart() - position <= 7)){
								this.addConsequenceType(exon2, "splice_region_variant", "SO:0001630", "Splice site", "consequenceTypeType" );
							}
					}
					
					if (exon2.getEnd() < position){
						if ( position - exon2.getEnd() < 2){
							this.addConsequenceType(exon2, "splice_acceptor_variant", "SO:0001574", "In the first 2 or the last 2 basepairs of an intron", "consequenceTypeType" );
						}
						
						if (( position - exon2.getEnd() >= 2) && ( position - exon2.getEnd() <= 7)){
							this.addConsequenceType(exon2, "splice_region_variant", "SO:0001630", "Splice site", "consequenceTypeType" );
						}
					}
				}
				else{
					if (exon2.getStart() > position){
						if (exon2.getStart() - position < 2){
								this.addConsequenceType(exon2, "splice_acceptor_variant", "SO:0001574", "In the first 2 or the last 2 basepairs of an intron", "consequenceTypeType" );
						}
						if ((exon2.getStart() - position >= 2)&&(exon2.getStart() - position <= 7)){
							this.addConsequenceType(exon2, "splice_region_variant", "SO:0001630", "Splice site", "consequenceTypeType" );
						}
				}
				
				if (exon2.getEnd() < position){
					if ( position - exon2.getEnd() < 2){
						this.addConsequenceType(exon2, "splice_donor_variant", "SO:0001575", "In the first 2 or the last 2 basepairs of an intron", "consequenceTypeType" );
					}
					
					if (( position - exon2.getEnd() >= 2) && ( position - exon2.getEnd() <= 7)){
						this.addConsequenceType(exon2, "splice_region_variant", "SO:0001630", "1-3 bps into an exon or 3-8 bps into an intron", "consequenceTypeType" );
					}
				}
				}
			}
		}
		else{
			if ((transcript.getCodingRegionStart() == 0)&&( transcript.getCdnaCodingEnd() ==0)){
				this.addConsequenceType(transcript, "nc_transcript_variant", "SO:0001619", "Located within a gene that does not code for a protein", "consequenceTypeType" );
			}
			else{
				if (transcript.getStrand().equals("-1")){
					if (transcript.getCodingRegionStart() > position) {
						this.addConsequenceType(transcript, "3_prime_UTR_variant", "SO:0001624", "In 3 prime untranslated region", "consequenceTypeType" );
					}
	
					if (transcript.getCodingRegionEnd() < position) {
						this.addConsequenceType(transcript, "5_prime_UTR_variant", "SO:0001623", "In 5 prime untranslated region", "consequenceTypeType" );
					}
				}
				else{
					/** Strand + **/
					if (transcript.getCodingRegionStart() > position) {
						this.addConsequenceType(transcript, "5_prime_UTR_variant", "SO:0001623", "In 5 prime untranslated region", "consequenceTypeType" );
					}
	
					if (transcript.getCodingRegionEnd() < position) {
						this.addConsequenceType(transcript, "3_prime_UTR_variant", "SO:0001624", "In 3 prime untranslated region", "consequenceTypeType" );
					}
				}
				
				if ((transcript.getCodingRegionStart() <= position) && (transcript.getCodingRegionEnd() >= position)){
					for (Exon exon : exonsByPosition) {
						if ((position - exon.getStart() <= 2)&&((position - exon.getStart() >= 0))){
							this.addConsequenceType(exon, "splice_region_variant", "SO:0001630", "1-3 bps into an exon or 3-8 bps into an intron", "consequenceTypeType" );
						}
						
						if ((exon.getEnd() - position <= 2)&&((exon.getEnd() - position >= 0))){
							this.addConsequenceType(exon, "splice_region_variant", "SO:0001630", "1-3 bps into an exon or 3-8 bps into an intron", "consequenceTypeType" );
						}
					}
					
					if (alternativeAllele != null){
						this.getConsequenceTypeByAlternativeAllele(transcript, exons,  position, alternativeAllele);
					}
				}
			}
		}
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
	
	
	private List<String> getConsequenceTypeByAlternativeAllele(Transcript transcript, List<Exon> exons, int position, String alternativeAllele) {
		int codon_position = this.getCodonByPosition(transcript, exons, position);
		 List<String> consequenceTypes = new ArrayList<String>();
		
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
		
		
		this.addConsequenceType(transcript, "coding_sequence_variant", "SO:0001580", " In coding sequence with indeterminate effect", "consequenceTypeType" );
		
		
		if (DNASequenceUtils.codonToAminoacidShort.get(referenceSequence).equals(DNASequenceUtils.codonToAminoacidShort.get(alternative))){
			this.addConsequenceType(transcript, "synonymous_codon", "SO:0001588", "In coding sequence, not resulting in an amino acid change (silent mutation)", "consequenceTypeType" );
		}
		else{
			this.addConsequenceType(transcript, "non_synonymous_codon", "SO:0001583", "In coding sequence and results in an amino acid change in the encoded peptide sequence", "consequenceTypeType", DNASequenceUtils.codonToAminoacidShort.get(referenceSequence)+"/"+ DNASequenceUtils.codonToAminoacidShort.get(alternative), referenceSequence.replace("U", "T")+"/"+alternative.replace("U", "T")  );

			if ((!DNASequenceUtils.codonToAminoacidShort.get(referenceSequence).toLowerCase().equals("stop"))&& (DNASequenceUtils.codonToAminoacidShort.get(alternative).toLowerCase().equals("stop"))){
				this.addConsequenceType(transcript, "stop_gained", "SO:0001587", "In coding sequence, resulting in the gain of a stop codon", "consequenceTypeType", DNASequenceUtils.codonToAminoacidShort.get(referenceSequence)+"/"+ DNASequenceUtils.codonToAminoacidShort.get(alternative), referenceSequence.replace("U", "T")+"/"+alternative.replace("U", "T")  );
			}
			
			if ((DNASequenceUtils.codonToAminoacidShort.get(referenceSequence).toLowerCase().equals("stop"))&& (!DNASequenceUtils.codonToAminoacidShort.get(alternative).toLowerCase().equals("stop"))){
				this.addConsequenceType(transcript, "stop_lost", "SO:0001578", "In coding sequence, resulting in the loss of a stop codon", "consequenceTypeType", DNASequenceUtils.codonToAminoacidShort.get(referenceSequence)+"/"+ DNASequenceUtils.codonToAminoacidShort.get(alternative), referenceSequence.replace("U", "T")+"/"+alternative.replace("U", "T")  );
			}
		}
		return consequenceTypes;
	}
	
	

	private int getCodonByPosition(Transcript transcript, List<Exon> exons,  int position){
		int cdna_length = 0;
		if (transcript.getStrand().equals("-1")){
			for (int i = exons.size() - 1; i >= 0; i--) {
				Exon exonIntranscript = exons.get(i);
				if (position < exonIntranscript.getStart()){
					/** Primer exon **/
					if ((exonIntranscript.getEnd() >= transcript.getCodingRegionEnd()) && (exonIntranscript.getStart() <= transcript.getCodingRegionEnd())){
						cdna_length = cdna_length + (transcript.getCodingRegionEnd() - exonIntranscript.getStart()) +1 ;
					}
					
					/** Los demas **/
					if ((exonIntranscript.getEnd() < transcript.getCodingRegionEnd()) && (exonIntranscript.getStart() > transcript.getCodingRegionStart())){
						cdna_length = cdna_length + (exonIntranscript.getEnd() - exonIntranscript.getStart()) + 1;
					}
				}
				else{
					
					if ((exonIntranscript.getEnd() >= transcript.getCodingRegionEnd())&&(exonIntranscript.getStart()<= transcript.getCodingRegionStart())){
						cdna_length = cdna_length + (transcript.getCodingRegionEnd() - position ) + 1;
					}
					else{
						cdna_length = cdna_length + ( exonIntranscript.getEnd() - position) + 1;
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
					}
					
					/** Los demas **/
					if ((exonIntranscript.getStart() >  transcript.getCodingRegionStart())&&(exonIntranscript.getEnd() <= transcript.getCodingRegionEnd())){
						cdna_length = cdna_length + (exonIntranscript.getEnd() - exonIntranscript.getStart()) + 1;
					}
				}
				else{
					if ((exonIntranscript.getStart() <= transcript.getCodingRegionStart())&&(exonIntranscript.getEnd()>= transcript.getCodingRegionEnd())){
						cdna_length = cdna_length + (position -  transcript.getCodingRegionStart()) + 1;
					}
					else{
						cdna_length = cdna_length + (position - exonIntranscript.getStart()) + 1;
					break;
					}
				}
			}
		}
		return cdna_length % 3 ;
	}

	
	
//	private void printConsequenceTypes(){
//		for (ConsequenceTypeResult consequence : this.consequences) {
//			System.out.println(consequence.toString());
//		}
//	}
	
	/** adding consequence types **/
	private void addConsequenceType(Transcript transcript, String consequenceType, String consequenceTypeObo, String desc, String type, String aminoChange, String codonChange){
		this.consequences.add(
				new ConsequenceTypeResult(
						transcript.getChromosome(), 
						this.position,  
						this.position, 
						transcript.getStableId(), 
						transcript.getExternalName(),
						"TRANSCRIPT", 
						transcript.getBiotype(), 
						transcript.getChromosome(), 
						transcript.getStart(),
						transcript.getEnd(), 
						transcript.getStrand(), 
						".", 
						".", 
						".", 
						"GENEID", 
						transcript.getStableId(), 
						"GENENAME", 
						consequenceType,
						consequenceTypeObo, 
						desc, 
						type, 
						codonChange,
						aminoChange));
	}
	
	private void addConsequenceType(Transcript transcript, String consequenceType, String consequenceTypeObo, String desc, String type){
		this.addConsequenceType(transcript, consequenceType, consequenceTypeObo, desc, consequenceTypeObo, ".", ".");
	}
	
	private void addConsequenceType(RegulatoryRegion regulatory, String consequenceType, String consequenceTypeObo, String desc, String type){
		this.consequences.add(
				new ConsequenceTypeResult(
						regulatory.getChromosome(), 
						this.position,  
						this.position, 
						regulatory.getName(), 
						".",
						"REGULATORY_REGION", 
						".", 
						regulatory.getChromosome(), 
						regulatory.getStart(),
						regulatory.getEnd(), 
						".", 
						".", 
						".", 
						".", 
						"GENEID", 
						"TRANSCRIPTID", 
						"GENENAME", 
						consequenceType,
						consequenceTypeObo, 
						desc, 
						type, 
						".", "."));
	}
	
	private void addConsequenceType(Tfbs tfbs, String consequenceType, String consequenceTypeObo, String desc, String type){
		this.consequences.add(
				new ConsequenceTypeResult(
						tfbs.getChromosome(), 
						this.position,  
						this.position, 
						tfbs.getTfName(), 
						".",
						"TFBS", 
						".", 
						tfbs.getChromosome(), 
						tfbs.getStart(),
						tfbs.getEnd(), 
						tfbs.getStrand(), 
						".", 
						".", 
						".", 
						"GENEID", 
						"TRANSCRIPTID", 
						"GENENAME", 
						consequenceType,
						consequenceTypeObo, 
						desc, 
						type, 
						".",
						"."));
	}
	
	
	private void addConsequenceType(Exon exon, String consequenceType, String consequenceTypeObo, String desc, String type){
		this.consequences.add(
				new ConsequenceTypeResult(
						exon.getChromosome(), 
						this.position,  
						this.position, 
						exon.getStableId(), 
						".",
						"EXON", 
						".", 
						exon.getChromosome(), 
						exon.getStart(),
						exon.getEnd(), 
						exon.getStrand(), 
						".", 
						".", 
						".", 
						"GENEID", 
						exon.getStableId(), 
						"GENENAME", 
						consequenceType,
						consequenceTypeObo, 
						desc, 
						type, 
						".", "."));
	}
	
	/** GETTERS AND SETTERS **/
	private void setFeatures(GenomicRegionFeatures features) {
		this.features = features;
	}


	public GenomicRegionFeatures getFeatures() {
		return features;
	}
	
	
	
	
	
	
	public class ConsequenceTypeResult{
		private String chromosome;
		private int start;
		private int end;
		private String id;
		private String name;
		private String type;
		private String biotype;
		private String featureChromosome;
		private int featureStart;
		private int featureEnd;
		private String featureStrand;
		
		private String snpId;
		private String ancestral;
		private String alternative;
		
		private String geneId;
		private String transcriptId;
		private String geneName;

		public String consequenceType;
		private String consequenceTypeObo;
		private String consequenceTypeDesc;
		private String consequenceTypeType;
		
		private String aminoacidChange;
		private String codonChange;
		
		public String toString(){
			StringBuilder br = new StringBuilder();
			return 	 
			 br.append(chromosome).append("\t")
			.append(start).append("\t")
			.append(end).append("\t")
			.append(id).append("\t")
			.append(name).append("\t")
			.append(type).append("\t")
			.append(biotype).append("\t")
			.append(featureChromosome).append("\t")
			.append(featureStart).append("\t")
			.append(featureEnd).append("\t")
			.append(featureStrand).append("\t")
			.append(snpId).append("\t")
			.append(ancestral).append("\t")
			.append(alternative).append("\t")
			.append(geneId).append("\t")
			.append(transcriptId).append("\t")
			.append(geneName).append("\t")
			.append(consequenceType).append("\t")
			.append(consequenceTypeObo).append("\t")
			.append(consequenceTypeDesc).append("\t")
			.append(consequenceTypeType).append("\t")
			.append(aminoacidChange).append("\t")
			.append(codonChange).append("\t").append("\n").toString();
			
			
		}
		public ConsequenceTypeResult(String chromosome, int start, int end,
				String id, String name, String type, String biotype,
				String featureChromosome, int featureStart,
				int featureEnd, String featureStrand, String snpId,
				String ancestral, String alternative, String geneId,
				String transcriptId, String geneName, String consequenceType,
				String consequenceTypeObo, String consequenceTypeDesc,
				String consequenceTypeType, String aminoacidChange,
				String codonChange) {
			super();
			this.chromosome = chromosome;
			this.start = start;
			this.end = end;
			this.id = id;
			this.name = name;
			this.type = type;
			this.biotype = biotype;
			this.featureChromosome = featureChromosome;
			this.featureStart = featureStart;
			this.featureEnd = featureEnd;
			this.featureStrand = featureStrand;
			this.snpId = snpId;
			this.ancestral = ancestral;
			this.alternative = alternative;
			this.geneId = geneId;
			this.transcriptId = transcriptId;
			this.geneName = geneName;
			this.consequenceType = consequenceType;
			this.consequenceTypeObo = consequenceTypeObo;
			this.consequenceTypeDesc = consequenceTypeDesc;
			this.consequenceTypeType = consequenceTypeType;
			this.aminoacidChange = aminoacidChange;
			this.codonChange = codonChange;
		}
	}
		

}

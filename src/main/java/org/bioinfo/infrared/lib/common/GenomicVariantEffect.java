package org.bioinfo.infrared.lib.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bioinfo.commons.io.utils.FileUtils;
import org.bioinfo.commons.io.utils.IOUtils;
import org.bioinfo.commons.log.Logger;
import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.RegulatoryRegion;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.Tfbs;
import org.bioinfo.infrared.core.cellbase.Transcript;
import org.bioinfo.infrared.lib.api.ExonDBAdaptor;
import org.bioinfo.infrared.lib.api.GenomicRegionFeatureDBAdaptor;
import org.bioinfo.infrared.lib.api.TranscriptDBAdaptor;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
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
	private List<GenomicVariantConsequenceType> consequences; 

	//select start DIV 100000 as chunk_id, count(*) from snp where chromosome='1' and start > 11120 and end < 5555555 group by chunk_id ;

	/** Acelerador de los transcripts **/
	private TranscriptDBAdaptor transcriptAdaptor;
	private List<Transcript> transcripts;
	private HashMap<String, Transcript> transcriptHash = new HashMap<String, Transcript>();

	/** Options **/
	private boolean showFeatures = false;
	private boolean showVariation = true;
	private boolean showRegulatory = true;
	private boolean showDiseases = true;


	private Logger logger;
	private String internalID;

	public GenomicVariantEffect(String species){
		this.dbAdaptorFact = new HibernateDBAdaptorFactory();
		this.genomicRegionFeatureDBAdaptor = dbAdaptorFact.getFeatureMapDBAdaptor(species);
		this.transcriptAdaptor = dbAdaptorFact.getTranscriptDBAdaptor(species);
		this.exonAdaptor = dbAdaptorFact.getExonDBAdaptor(species);
		this.sequenceDbAdaptor = dbAdaptorFact.getGenomeSequenceDBAdaptor(species);

		this.logger = new Logger();
		this.logger.setLevel(Logger.INFO_LEVEL);

		this.internalID = String.valueOf(Math.random()).substring(1, 5);

	}


	public void writeConsequenceType(List<GenomicVariant> variants, File file) throws IOException{
		logger.debug("File path: " + file.getAbsolutePath());
		FileUtils.checkFile(file);

		//		if(file.canWrite()){
		this.transcriptHash = this.fillAllTranscriptFromDB();
		StringBuilder consequenceTypeString = new StringBuilder();
		for(int i = 0; i < variants.size(); i++) {
			try {
				//				IOUtils.append(file, this.getConsequenceType(variants.get(i)).toString());
				consequenceTypeString.append(this.getConsequenceType(variants.get(i)).toString()+"\n");
			}
			//			catch (IOException e) {
			//				IOUtils.append(file, "VARIANT TOOL ERROR: " + e.getMessage());
			//				e.printStackTrace();
			//			}
			catch(Exception e){
				//				IOUtils.append(file, "VARIANT TOOL ERROR: " + e.getMessage());
				consequenceTypeString.append("VARIANT TOOL ERROR: " + e.getMessage());
				e.printStackTrace();
			}
		}
		IOUtils.write(file, consequenceTypeString.toString());
		//		}
	}


	public String writeConsequenceType(List<GenomicVariant> variants){
		StringBuilder br = new StringBuilder();
		try {
			this.transcriptHash = this.fillAllTranscriptFromDB();
			for(int i = 0; i < variants.size(); i++) {
				br.append(this.getConsequenceType(variants.get(i)).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return br.toString();
	}


	public List<GenomicVariantConsequenceType> getConsequenceType(List<GenomicVariant> variants,  List<Transcript> transcripts){
		logger.info("VARIANT TOOL CALL id:" + this.internalID + " Features: "  + this.showFeatures + " Variation: "  + this.showVariation + " Regulatory: "  + this.showRegulatory+ " Disease: "  + this.showDiseases + " Number: "  + variants.size() );
		//		logger.info("\tFlags:");
		//		logger.info("\t\tFeatures: "  + this.showFeatures);
		//		logger.info("\t\tVariation: "  + this.showVariation);
		//		logger.info("\t\tRegulatory: "  + this.showRegulatory);
		//		logger.info("\t\tDisease: "  + this.showDiseases);
		//		logger.info("\tVariants:");
		//		logger.info("\t\tNumber: "  + variants.size());

		long t0 = System.currentTimeMillis();
		if (transcripts == null){
			//			logger.info("\tCache Transcripts: NONE");
			this.transcriptHash = this.fillAllTranscriptFromDB();
		}
		else{
			//			logger.info("\tCache Transcripts: "  + transcripts.size());
			this.transcriptHash = this.fillTranscriptHash(transcripts);
		}
		List<GenomicVariantConsequenceType> result = new ArrayList<GenomicVariantConsequenceType>();
		for (int i = 0; i < variants.size(); i++) {
			result.addAll(this.getConsequenceType(variants.get(i)));
		}

		logger.info("Finished " + variants.size() + " in " + (System.currentTimeMillis() - t0) + " ms");
		return result;
	}

	public List<GenomicVariantConsequenceType> getConsequenceType(List<GenomicVariant> variants){
		this.transcriptHash = this.fillAllTranscriptFromDB();
		List<GenomicVariantConsequenceType> result = new ArrayList<GenomicVariantConsequenceType>();

		for (int i = 0; i < variants.size(); i++) {
			result.addAll(this.getConsequenceType(variants.get(i)));
		}
		return result;
	}

	private HashMap<String, Transcript> fillTranscriptHash(List<Transcript> transcripts){
		HashMap<String, Transcript> hash = new HashMap<String, Transcript>();
		for (Transcript transcript : transcripts) {
			hash.put(transcript.getStableId(), transcript);
		}
		return hash;
	}

	private HashMap<String, Transcript> fillAllTranscriptFromDB(){
		this.transcripts = transcriptAdaptor.getAll();
		return this.fillTranscriptHash(this.transcripts);
	}



	private List<GenomicVariantConsequenceType> getConsequenceType(GenomicVariant variant){
		return this.getConsequenceType(variant.getChromosome(), variant.getPosition(), variant.getAlternative());
	}

	private String getSNPNameByPosition(){
		String result = ".";
		if (this.features.getSnp() != null){
			result = new String();
			if(this.features.getSnp().size() != 0){
				for (Snp snp : this.features.getSnp()) {
					result = result + " " + snp.getName();
				}
			}
		}
		return result;
	}

	private String getSNPAncestralByPosition(){
		String result = ".";
		if (this.features.getSnp() != null){
			result = new String();
			if(this.features.getSnp().size() != 0){
				for (Snp snp : this.features.getSnp()) {
					result = result + " " + snp.getAncestralAllele();
				}
			}
		}
		return result;
	}

	private String getSNPAlleleByPosition(){
		String result = ".";
		if (this.features.getSnp() != null){
			result = new String();
			if(this.features.getSnp().size() != 0){
				for (Snp snp : this.features.getSnp()) {
					result = result + " " + snp.getAlleleString();
				}
			}
		}
		return result;
	}


	private String getGeneStableIdByPosition(){
		String result = ".";
		if (this.features.getGenes() != null){
			result = new String();
			if(this.features.getGenes().size() != 0){
				for (Gene gene : this.features.getGenes()) {
					result = result + " " + gene.getStableId();
				}
			}
		}
		return result;
	}

	private String getGeneExternalIdByPosition(){
		String result = ".";
		if (this.features.getGenes() != null){
			result = new String();
			if(this.features.getGenes().size() != 0){
				for (Gene gene : this.features.getGenes()) {
					result = result + " " + gene.getExternalName();
				}
			}
		}
		return result;
	}

	private String getTranscriptStableIdByPosition(){
		String result = ".";
		if (this.features.getTranscripts() != null){
			result = new String();
			if(this.features.getTranscripts().size() != 0){
				for (Transcript transcript : this.features.getTranscripts()) {
					result = result + " " + transcript.getStableId();
				}
			}
		}
		return result;
	}


	public List<GenomicVariantConsequenceType> getConsequenceType(String chromosome, int position, String alternativeAllele){
		this.features = genomicRegionFeatureDBAdaptor.getByRegion(chromosome, position, position);
		this.consequences = new ArrayList<GenomicVariantConsequenceType>();
		this.position = position;

		if (this.showFeatures){
			if (getFeatures().getTranscriptsIds() != null){
				if ((getFeatures().getTranscriptsIds().size() > 0)) {
					for (String stableId : getFeatures().getTranscriptsIds()) {
						try{
							Transcript transcript = this.transcriptHash.get(stableId);
							this.getConsequenceType(transcript, chromosome, position, alternativeAllele.toUpperCase().trim());
						}
						catch(Exception exp){
							logger.error(chromosome + ":" + position + "-" + alternativeAllele + " " + exp.getMessage());
						}
					}
				}
			}
		}


		if(this.showVariation){
			if (getFeatures().getSnpsIds() != null){
				if (getFeatures().getSnpsIds().size() > 0){
					for (Snp snp : getFeatures().getSnp()) {

						this.addConsequenceType(snp);
					}
				}
			}
		}

		if(this.showRegulatory){
			if(getFeatures().getTfbs() != null){
				if(getFeatures().getTfbs().size() > 0){
					for (Tfbs tfbs : getFeatures().getTfbs()) {
						this.addConsequenceType(tfbs, "TF_binding_site_variant", "SO:0001782", "A sequence variant located within a transcription factor binding site.", "REGULATORY");
					}
				}
			}

			if(getFeatures().getRegulatoryRegion() != null){
				if(getFeatures().getRegulatoryRegion().size() > 0){
					for (RegulatoryRegion regulatory : getFeatures().getTranscriptionFactor()) {
						this.addConsequenceType(regulatory, "regulatory_region_variant", "SO:0001566", "In regulatory region annotated by Ensembl", "REGULATORY" );
					}
					for (RegulatoryRegion regulatory : getFeatures().getHistones()){
						this.addConsequenceType(regulatory, "regulatory_region_variant", "SO:0001566", "In regulatory region annotated by Ensembl", "REGULATORY" );
					}
					for (RegulatoryRegion regulatory : getFeatures().getOpenChromatin()){
						this.addConsequenceType(regulatory, "open_chromatine_region", " SO:0001747", "In regulatory region annotated by Ensembl", "REGULATORY" );
					}
					for (RegulatoryRegion regulatory : getFeatures().getPolimerase()){
						this.addConsequenceType(regulatory, "POLYMERASE", "POLYMERASE OBO", "In regulatory region annotated by Ensembl", "REGULATORY" );
					}
				}
			}
		}

		return this.consequences;
	}

	private void getConsequenceType(Transcript transcript, String chromosome, int position, String alternativeAllele) throws Exception{
		try{
			List<Exon> exons =  exonAdaptor.getByEnsemblTranscriptId(transcript.getStableId());
			List<Exon> exonsByPosition = this.getExonByPosition(exons, chromosome, position);
			if (transcript.getBiotype().equals("nonsense_mediated_decay")){
				this.addConsequenceType(transcript, "NMD_TRANSCRIPT", "SO:0001621", "Located within a transcript predicted to undergo nonsense-mediated decay", "consequenceTypeType" );
			}

			if(exonsByPosition.size() == 0){
				this.addConsequenceType(transcript, "intron_variant", "SO:0001627", "In intron", "consequenceTypeType" );

				for(Exon exon2 : exons) {
					if(transcript.getStrand().equals("-1")){
						if (exon2.getStart() > position){
							if (exon2.getStart() - position < 2){
								this.addConsequenceType(exon2, transcript, "splice_donor_variant", "SO:0001575", "In the first 2 or the last 2 basepairs of an intron", "consequenceTypeType" );
							}
							if ((exon2.getStart() - position >= 2)&&(exon2.getStart() - position <= 7)){
								this.addConsequenceType(exon2, transcript,"splice_region_variant", "SO:0001630", "Splice site", "consequenceTypeType" );
							}
						}

						if (exon2.getEnd() < position){
							if ( position - exon2.getEnd() < 2){
								this.addConsequenceType(exon2, transcript,"splice_acceptor_variant", "SO:0001574", "In the first 2 or the last 2 basepairs of an intron", "consequenceTypeType" );
							}

							if (( position - exon2.getEnd() >= 2) && ( position - exon2.getEnd() <= 7)){
								this.addConsequenceType(exon2, transcript,"splice_region_variant", "SO:0001630", "Splice site", "consequenceTypeType" );
							}
						}
					}else {
						if (exon2.getStart() > position){
							if (exon2.getStart() - position < 2){
								this.addConsequenceType(exon2, transcript, "splice_acceptor_variant", "SO:0001574", "In the first 2 or the last 2 basepairs of an intron", "consequenceTypeType" );
							}
							if ((exon2.getStart() - position >= 2)&&(exon2.getStart() - position <= 7)){
								this.addConsequenceType(exon2, transcript, "splice_region_variant", "SO:0001630", "Splice site", "consequenceTypeType" );
							}
						}

						if (exon2.getEnd() < position){
							if ( position - exon2.getEnd() < 2){
								this.addConsequenceType(exon2, transcript, "splice_donor_variant", "SO:0001575", "In the first 2 or the last 2 basepairs of an intron", "consequenceTypeType" );
							}

							if (( position - exon2.getEnd() >= 2) && ( position - exon2.getEnd() <= 7)){
								this.addConsequenceType(exon2, transcript, "splice_region_variant", "SO:0001630", "1-3 bps into an exon or 3-8 bps into an intron", "consequenceTypeType" );
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
								this.addConsequenceType(exon, transcript, "splice_region_variant", "SO:0001630", "1-3 bps into an exon or 3-8 bps into an intron", "consequenceTypeType" );
							}

							if ((exon.getEnd() - position <= 2)&&((exon.getEnd() - position >= 0))){
								this.addConsequenceType(exon, transcript, "splice_region_variant", "SO:0001630", "1-3 bps into an exon or 3-8 bps into an intron", "consequenceTypeType" );
							}
						}

						if (alternativeAllele != null){
							this.getConsequenceTypeByAlternativeAllele(transcript, exons,  position, alternativeAllele);
						}
					}
				}
			}

		}
		catch(Exception exp){
			throw exp;
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


	private List<String> getConsequenceTypeByAlternativeAllele(Transcript transcript, List<Exon> exons, int position, String alternativeAllele) throws Exception {
		try{
			int codon_position = this.getCodonByPosition(transcript, exons, position);
			if(codon_position == -1){
				throw new Exception("There was an error calculating codon position for transcript " + transcript.getStableId());
			}

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

				//				sequence.setSequence(GenomeSequenceDBAdaptor.getRevComp(sequence.getSequence()));
				//				alternativeAllele = GenomeSequenceDBAdaptor.getRevComp(alternativeAllele);
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

				//				sequence.setSequence(GenomeSequenceDBAdaptor.getRevComp(sequence.getSequence()));
				//				alternativeAllele = GenomeSequenceDBAdaptor.getRevComp(alternativeAllele);
			}

			sequence.setSequence(GenomeSequenceDBAdaptor.getRevComp(sequence.getSequence()));
			alternativeAllele = GenomeSequenceDBAdaptor.getRevComp(alternativeAllele);

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
		catch(Exception exp){
			throw exp;
		}
	}



	private int getCodonByPosition(Transcript transcript, List<Exon> exons,  int position){
		int cdna_length = 0;
		try{
			if(transcript.getStrand().equals("-1")) {
				for(int i = exons.size() - 1; i >= 0; i--) {
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
					}else {
						if((exonIntranscript.getEnd() >= transcript.getCodingRegionEnd())&&(exonIntranscript.getStart()<= transcript.getCodingRegionStart())){
							cdna_length = cdna_length + (transcript.getCodingRegionEnd() - position ) + 1;
							break;
						}else {
							cdna_length = cdna_length + ( exonIntranscript.getEnd() - position) + 1;
							break;
						}
					}
				}	
			}else {
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
							break;
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
		catch(Exception e){
			e.printStackTrace();
		}

		return -1;
	}



	//	private void printConsequenceTypes(){
	//		for (GenomicVariantConsequenceType consequence : this.consequences) {
	//			//System.out.println(consequence.toString());
	//		}
	//	}

	/** adding consequence types **/
	private void addConsequenceType(Transcript transcript, String consequenceType, String consequenceTypeObo, String desc, String type, String aminoChange, String codonChange){
		this.consequences.add(
				new GenomicVariantConsequenceType(
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
						getSNPNameByPosition(),
						getSNPAncestralByPosition(), 
						getSNPAlleleByPosition(), 
						transcript.getGene().getStableId(), 
						transcript.getStableId(), 
						transcript.getGene().getExternalName(), 
						consequenceType,
						consequenceTypeObo, 
						desc, 
						type, 
						codonChange,
						aminoChange));
	}

	private void addConsequenceType(Snp snp){
		this.consequences.add(
				new GenomicVariantConsequenceType(
						snp.getChromosome(), 
						this.position,  
						this.position, 
						snp.getName(), 
						".",
						"SNP", 
						".", //"biotype", 
						snp.getChromosome(), 
						snp.getStart(),
						snp.getEnd(), 
						snp.getStrand(), 
						snp.getName(), 
						snp.getAncestralAllele(),
						snp.getAlleleString(),
						getGeneStableIdByPosition(), 
						getTranscriptStableIdByPosition(),
						getGeneExternalIdByPosition(), 
						snp.getDisplayConsequence(),
						snp.getDisplaySoConsequence(), 
						".", //"description", 
						".",//"type", 
						".",//"codonChange",
						"."));//"aminoChange"));

	}


	private void addConsequenceType(Transcript transcript, String consequenceType, String consequenceTypeObo, String desc, String type){
		this.addConsequenceType(transcript, consequenceType, consequenceTypeObo, desc, consequenceTypeObo, ".", ".");
	}

	private void addConsequenceType(RegulatoryRegion regulatory, String consequenceType, String consequenceTypeObo, String desc, String type){
		this.consequences.add(
				new GenomicVariantConsequenceType(
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
						getSNPNameByPosition(),
						getSNPAncestralByPosition(), 
						getSNPAlleleByPosition(), 
						".", 
						".", 
						".", 
						consequenceType,
						consequenceTypeObo, 
						desc, 
						type, 
						".", 
						"."));
	}

	private void addConsequenceType(Tfbs tfbs, String consequenceType, String consequenceTypeObo, String desc, String type){
		this.consequences.add(
				new GenomicVariantConsequenceType(
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
						getSNPNameByPosition(),
						getSNPAncestralByPosition(), 
						getSNPAlleleByPosition(), 
						tfbs.getGeneByTargetGeneId().getStableId(), //"GENEID", 
						".", 
						tfbs.getGeneByTargetGeneId().getExternalName(),
						consequenceType,
						consequenceTypeObo, 
						desc, 
						type, 
						".",
						"."));
	}


	private void addConsequenceType(Exon exon, Transcript transcript, String consequenceType, String consequenceTypeObo, String desc, String type){
		this.consequences.add(
				new GenomicVariantConsequenceType(
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
						getSNPNameByPosition(),
						getSNPAncestralByPosition(), 
						getSNPAlleleByPosition(), 
						transcript.getGene().getStableId(), 
						transcript.getStableId(), 
						transcript.getGene().getExternalName(), 
						consequenceType,
						consequenceTypeObo, 
						desc, 
						type, 
						".", 
						"."));
	}

	/** GETTERS AND SETTERS **/
	private void setFeatures(GenomicRegionFeatures features) {
		this.features = features;
	}


	public GenomicRegionFeatures getFeatures() {
		return features;
	}



	@Deprecated
	private List<GenomicVariantConsequenceType> getConsequenceType(GenomicVariant variant, GenomicRegionFeatures genomicRegionFeatures){
		this.setFeatures(genomicRegionFeatures);
		return this.getConsequenceType(variant.getChromosome(), variant.getPosition(), variant.getAlternative());
	}

	@Deprecated
	public List<GenomicVariantConsequenceType> getConsequenceTypeByChunk(List<GenomicVariant> variants){

		this.transcripts = transcriptAdaptor.getAll();
		//System.out.println("All transcripts " + this.transcripts.size());
		logger.debug("VARIANT TOOL: All transcripts " + this.transcripts.size());

		for (Transcript transcript : this.transcripts) {
			this.transcriptHash.put(transcript.getStableId(), transcript);
		}
		List<GenomicVariantConsequenceType> result = new ArrayList<GenomicVariantConsequenceType>();

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

			for (int j = 0; j < genomicRegionMap.size(); j++) {
				result.addAll(this.getConsequenceType(variantsChunk.get(j), genomicRegionMap.get(j)));
			}
		}
		return result;
	}

	@Deprecated
	public List<GenomicVariantConsequenceType> getConsequenceTypeWithChunks(List<GenomicVariant> variants){

		this.transcriptHash = this.fillAllTranscriptFromDB();

		long t00 = System.currentTimeMillis();
		List<GenomicVariantConsequenceType> result = new ArrayList<GenomicVariantConsequenceType>();

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
			//System.out.println("Recovering features map chunk("+ (end - i)  +"): "+(System.currentTimeMillis()-t0)+" ms " +   genomicRegionMap.size());

			for (int j = 0; j < genomicRegionMap.size(); j++) {
				result.addAll(this.getConsequenceType(variantsChunk.get(j), genomicRegionMap.get(j)));
			}
		}
		//System.out.println("TOTAL MAIN LOOP: "+(System.currentTimeMillis()-t00)+" ms");
		return result;
	}

	
	public class GenomicVariantConsequenceType {
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
			return br.append(chromosome).append("\t")
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
					.append(codonChange).append("\t").toString();
		}

		public GenomicVariantConsequenceType(String chromosome, int start, int end,
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


	public boolean isShowFeatures() {
		return showFeatures;
	}


	public void setShowFeatures(boolean showFeatures) {
		this.showFeatures = showFeatures;
	}


	public boolean isShowVariation() {
		return showVariation;
	}


	public void setShowVariation(boolean showVariation) {
		this.showVariation = showVariation;
	}


	public boolean isShowRegulatory() {
		return showRegulatory;
	}


	public void setShowRegulatory(boolean showRegulatory) {
		this.showRegulatory = showRegulatory;
	}


	public boolean isShowDiseases() {
		return showDiseases;
	}


	public void setShowDiseases(boolean showDiseases) {
		this.showDiseases = showDiseases;
	}

}

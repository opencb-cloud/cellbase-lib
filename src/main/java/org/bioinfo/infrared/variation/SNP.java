package org.bioinfo.infrared.variation;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.commons.utils.ListUtils;
import org.bioinfo.commons.utils.StringUtils;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.common.feature.VariationFeature;
import org.bioinfo.infrared.core.Transcript;

public class SNP extends VariationFeature{

	private String allele;
	private int mapWeight;
	private List<String> consequenceType;
	private List<TranscriptConsequenceType> transcriptConsequenceTypes;
	private String sequence;

	public SNP(String snpId) {
		super(snpId);
	}

	public SNP(String snpId, String chromosome, Integer start, Integer end) {
		this(snpId, chromosome, start, end, "", "");
	}

	public SNP(String snpId, String chromosome, Integer start, Integer end, String strand) {
		this(snpId, chromosome, start, end, strand, "");
	}

	public SNP(String snpId, String chromosome, Integer start, Integer end, String strand, String allele) {
		this(snpId, chromosome, start, end, strand, allele, 1, "", "", "");
	}

	public SNP(String snpId, String chromosome, Integer start, Integer end, String strand, String allele, Integer mapWeight) {
		this(snpId, chromosome, start, end, strand, allele, mapWeight, "", "", "");
	}

	public SNP(String snpId, String chromosome, Integer start, Integer end, String strand, String allele, Integer mapWeight, String consequenceType) {
		this(snpId, chromosome, start, end, strand, allele, mapWeight, "", "", "");
	}
	
	public SNP(String snpId, String chromosome, Integer start, Integer end, String strand, String allele, Integer mapWeight, String consequenceType, String trancriptConsequenceType) {
		this(snpId, chromosome, start, end, strand, allele, mapWeight,consequenceType, trancriptConsequenceType, "");
	}
	
	public SNP(String snpId, String chromosome, Integer start, Integer end, String strand, String allele, Integer mapWeight, String consequenceType, String trancriptConsequenceType, String sequence) {
		super(snpId, chromosome, start, end, strand);
		this.allele = allele;
		this.mapWeight = mapWeight;
		this.consequenceType = StringUtils.toList(consequenceType, ",");
		this.transcriptConsequenceTypes = TranscriptConsequenceType.parseTranscriptConsequenceTypeList(trancriptConsequenceType);
		this.sequence = sequence;
	}
	
	// weird constructor
	@Deprecated
	public SNP(Integer id, String snpId, String chromosome, Integer start, Integer end, String strand, String allele, Integer weight, String consequenceType, String sequence) {
		this(snpId, chromosome, start, end, strand, allele, weight, consequenceType, sequence);
	}
	
//	public SNP(String snpId, String chromosome, Integer start, Integer end, String strand, String allele, String consequenceType) {
//		super(snpId, chromosome, start, end, strand);
//		this.allele = allele;
//		this.consequenceType = StringUtils.toList(consequenceType, ",");
//	}
	
//	public SNP(String snpId, String chromosome, Integer start, Integer end, String strand, String allele, Integer mapWeight, String consequenceType, String sequence) {
//		super(snpId, chromosome, start, end, strand);
//		this.allele = allele;
//		this.weight = mapWeight;
//		this.consequenceType = StringUtils.toList(consequenceType, ",");
//		//		this.transcriptConsequenceType = TranscriptConsequenceType.parse(consequenceType);
//		this.sequence = sequence;
//	}
	
	
	public FeatureList<Transcript> getTranscripts() {
		return null;
	}

	@Override
	public String getLocation() {
		if(strand.equals("1") || strand.equals("+")) {
			return chromosome+":"+start+","+end+"(+)";
		}else {
			return chromosome+":"+start+","+end+"(-)";
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id).append("\t");
		sb.append(chromosome).append("\t");
		sb.append(start).append("\t");
		sb.append(end).append("\t");
		sb.append(strand).append("\t");
		sb.append(allele).append("\t");
		sb.append(mapWeight).append("\t");
		sb.append(ListUtils.toString(consequenceType, ",")).append("\t");
		sb.append(ListUtils.toString(transcriptConsequenceTypes, ",")).append("\t");
		sb.append(sequence);
		return sb.toString();
	}

	/**
	 * @return the allele
	 */
	public String getAllele() {
		return allele;
	}

	/**
	 * @param allele the allele to set
	 */
	public void setAllele(String allele) {
		this.allele = allele;
	}

	/**
	 * @return the mapWeight
	 */
	public int getMapWeight() {
		return mapWeight;
	}

	/**
	 * @param mapWeight the mapWeight to set
	 */
	public void setMapWeight(int mapWeight) {
		this.mapWeight = mapWeight;
	}

	/**
	 * @return the sequence
	 */
	public String getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return the transcriptConsequenceTypes
	 */
	public List<TranscriptConsequenceType> getTranscriptConsequenceTypes() {
		return transcriptConsequenceTypes;
	}

	/**
	 * @param transcriptConsequenceTypes the transcriptConsequenceTypes to set
	 */
	public void setTranscriptConsequenceTypes(List<TranscriptConsequenceType> transcriptConsequenceTypes) {
		this.transcriptConsequenceTypes = transcriptConsequenceTypes;
	}


	/**
	 * @param consequenceType the consequenceType to set
	 */
	public void setConsequenceType(List<String> consequenceType) {
		this.consequenceType = consequenceType;
	}

	/**
	 * @return the consequenceType
	 */
	public List<String> getConsequenceType() {
		return consequenceType;
	}


	static class TranscriptConsequenceType {
		private String consequenceType;
		private String ensemblTranscript;
		private String ensemblGene;

		public TranscriptConsequenceType(String consequenceType) {
			this(consequenceType, "", "");
		}

		public TranscriptConsequenceType(String consequenceType, String ensemblTranscript, String ensemblGene) {
			this.consequenceType = consequenceType;
			this.ensemblTranscript= ensemblTranscript;
			this.ensemblGene = ensemblGene;
		}

		public static TranscriptConsequenceType parseTranscriptConsequenceType(String transcriptConsequenceType) {
			String[] fields = transcriptConsequenceType.split(":", -1);
			if(fields.length == 1) {
				return new TranscriptConsequenceType(fields[0]);
			}else {
				if(fields.length == 3) {
					return new TranscriptConsequenceType(fields[0], fields[1], fields[2]);
				}else {
					return null;
				}
			}
		}

		public static List<TranscriptConsequenceType> parseTranscriptConsequenceTypeList(String transcriptConsequenceType) {
			List<TranscriptConsequenceType> consequences  = new ArrayList<SNP.TranscriptConsequenceType>(4);
			for(String cons: transcriptConsequenceType.split(",")) {
				consequences.add(parseTranscriptConsequenceType(cons));
			}
			return consequences;
		}

		@Override
		public String toString() {
			return consequenceType + ":" + ensemblTranscript + ":" + ensemblGene +"";
		}

		/**
		 * @return the transcriptConsequenceTypes
		 */
		public String getConsequenceType() {
			return consequenceType;
		}

		/**
		 * @param transcriptConsequenceTypes the transcriptConsequenceTypes to set
		 */
		public void setConsequenceType(String consequenceType) {
			this.consequenceType = consequenceType;
		}

		/**
		 * @return the ensemblTranscript
		 */
		public String getEnsemblTranscript() {
			return ensemblTranscript;
		}

		/**
		 * @param ensemblTranscript the ensemblTranscript to set
		 */
		public void setEnsemblTranscript(String ensemblTranscript) {
			this.ensemblTranscript = ensemblTranscript;
		}

		/**
		 * @return the ensemblGene
		 */
		public String getEnsemblGene() {
			return ensemblGene;
		}

		/**
		 * @param ensemblGene the ensemblGene to set
		 */
		public void setEnsemblGene(String ensemblGene) {
			this.ensemblGene = ensemblGene;
		}

	}

}

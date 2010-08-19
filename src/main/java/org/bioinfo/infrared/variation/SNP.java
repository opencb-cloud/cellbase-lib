package org.bioinfo.infrared.variation;

import java.util.List;

import org.bioinfo.commons.utils.ListUtils;
import org.bioinfo.commons.utils.StringUtils;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.common.feature.VariationFeature;
import org.bioinfo.infrared.core.Transcript;

public class SNP extends VariationFeature{

	private String allele;
	private int weight;
	private List<String> consequenceType;
//	private List<ConsequenceType> consequenceType2;
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
		super(snpId, chromosome, start, end, strand);
		this.allele = allele;
	}
	
	public SNP(String snpId, String chromosome, Integer start, Integer end, String strand, String allele, String consequence_type) {
		super(snpId, chromosome, start, end, strand);
		this.allele = allele;
		this.consequenceType = StringUtils.toList(consequence_type);
	}
	
	public SNP(String snpId, String chromosome, Integer start, Integer end, String strand, String allele, Integer weight, String consequenceType, String sequence) {
		this(snpId, chromosome, start, end, strand, allele);
		this.weight = weight;
		this.consequenceType = StringUtils.toList(consequenceType);
		this.sequence = sequence;
	}
	
	public SNP(Integer id, String snpId, String chromosome, Integer start, Integer end, String strand, String allele, Integer weight, String consequence_type, String sequence) {
		this(snpId, chromosome, start, end, strand, allele,weight,consequence_type,sequence);
	}
	
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
		sb.append(weight).append("\t");
		sb.append(ListUtils.toString(consequenceType, ",")).append("\t");
		sb.append(sequence).append("\t");
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
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
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
	 * @return the consequenceType
	 */
	public List<String> getConsequence_type() {
		return consequenceType;
	}

	/**
	 * @param consequenceType the consequenceType to set
	 */
	public void setConsequence_type(List<String> consequence_type) {
		this.consequenceType = consequence_type;
	}

	class ConsequenceType {
		private String consequenceType;
		private String ensemblTranscript;
		private String ensemblGene;

		public ConsequenceType(String consequenceType) {
			this(consequenceType, "", "");
		}

		public ConsequenceType(String consequenceType, String ensemblTranscript, String ensemblGene) {
			this.consequenceType = consequenceType;
			this.ensemblTranscript= ensemblTranscript;
			this.ensemblGene = ensemblGene;
		}
		
		@Override
		public String toString() {
			return consequenceType + " in " + ensemblTranscript + " (gene: " + ensemblGene +")";
		}
		
		/**
		 * @return the consequenceType
		 */
		public String getConsequenceType() {
			return consequenceType;
		}

		/**
		 * @param consequenceType the consequenceType to set
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

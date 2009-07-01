package org.bioinfo.infrared.variation;

import org.bioinfo.infrared.common.feature.VariationFeature;

public class SpliceSite extends VariationFeature{
	
	private String trancriptStableId;
	private String geneStableId;
	private String consequenceType;
	private String spliceSiteType;
	private String allele1;
	private double score1;
	private String sequence1;
	private String allele2;
	private double score2;
	private String sequence2;
	
	public SpliceSite(String snpId, String trancriptStableId, String geneStableId, String consequenceType, String spliceSiteType, String allele1, Double score1, String sequence1, String allele2, Double score2, String sequence2) {
		this.id = snpId;
		this.trancriptStableId = trancriptStableId;
		this.geneStableId = geneStableId;
		this.consequenceType = consequenceType;
		this.spliceSiteType = spliceSiteType;
		this.allele1 = allele1;
		this.score1 = score1;
		this.sequence1 = sequence1;
		this.allele2 = allele2;
		this.score2 = score2;
		this.sequence2 = sequence2;
	}
	
//	public SpliceSite(String snpId, String chromosome, Integer start, Integer end, String strand, String allele, Integer weight, String consequence_type, String sequence, String trancriptStableId, String geneStableId, String consequenceType, String spliceSiteType, String allele1, Double score1, String sequence1, String allele2, Double score2, String sequence2) {
//		super(snpId, chromosome, start, end, strand, allele, weight, consequence_type, sequence);
//		this.trancriptStableId = trancriptStableId;
//		this.geneStableId = geneStableId;
//		this.consequenceType = consequenceType;
//		this.spliceSiteType = spliceSiteType;
//		this.allele1 = allele1;
//		this.score1 = score1;
//		this.sequence1 = sequence1;
//		this.allele2 = allele2;
//		this.score2 = score2;
//		this.sequence2 = sequence2;
//	}

	@Override
	public String toString() {
		return id+"\t"+trancriptStableId+"\t"+geneStableId+"\t"+consequenceType+"\t"+spliceSiteType+"\t"+allele1+"\t"+score1+"\t"+sequence1+"\t"+allele2+"\t"+score2+"\t"+sequence2;
	}
	
	/**
	 * @return the trancriptStableId
	 */
	public String getTrancriptStableId() {
		return trancriptStableId;
	}

	/**
	 * @param trancriptStableId the trancriptStableId to set
	 */
	public void setTrancriptStableId(String trancriptStableId) {
		this.trancriptStableId = trancriptStableId;
	}

	/**
	 * @return the geneStableId
	 */
	public String getGeneStableId() {
		return geneStableId;
	}

	/**
	 * @param geneStableId the geneStableId to set
	 */
	public void setGeneStableId(String geneStableId) {
		this.geneStableId = geneStableId;
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
	 * @return the spliceSiteType
	 */
	public String getSpliceSiteType() {
		return spliceSiteType;
	}

	/**
	 * @param spliceSiteType the spliceSiteType to set
	 */
	public void setSpliceSiteType(String spliceSiteType) {
		this.spliceSiteType = spliceSiteType;
	}

	/**
	 * @return the allele1
	 */
	public String getAllele1() {
		return allele1;
	}

	/**
	 * @param allele1 the allele1 to set
	 */
	public void setAllele1(String allele1) {
		this.allele1 = allele1;
	}

	/**
	 * @return the score1
	 */
	public double getScore1() {
		return score1;
	}

	/**
	 * @param score1 the score1 to set
	 */
	public void setScore1(double score1) {
		this.score1 = score1;
	}

	/**
	 * @return the sequence1
	 */
	public String getSequence1() {
		return sequence1;
	}

	/**
	 * @param sequence1 the sequence1 to set
	 */
	public void setSequence1(String sequence1) {
		this.sequence1 = sequence1;
	}

	/**
	 * @return the allele2
	 */
	public String getAllele2() {
		return allele2;
	}

	/**
	 * @param allele2 the allele2 to set
	 */
	public void setAllele2(String allele2) {
		this.allele2 = allele2;
	}

	/**
	 * @return the score2
	 */
	public double getScore2() {
		return score2;
	}

	/**
	 * @param score2 the score2 to set
	 */
	public void setScore2(double score2) {
		this.score2 = score2;
	}

	/**
	 * @return the sequence2
	 */
	public String getSequence2() {
		return sequence2;
	}

	/**
	 * @param sequence2 the sequence2 to set
	 */
	public void setSequence2(String sequence2) {
		this.sequence2 = sequence2;
	}
	
	
}

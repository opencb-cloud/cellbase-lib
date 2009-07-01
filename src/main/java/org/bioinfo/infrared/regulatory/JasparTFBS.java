package org.bioinfo.infrared.regulatory;

import org.bioinfo.infrared.common.feature.GenomicFeature;

public class JasparTFBS extends GenomicFeature {
	
	private String factorName;
	private String geneStableId;
	private double score;
	
	public JasparTFBS(String factorName, String geneStableId,  String chromosome, Integer start, Integer end, Double score) {
		super(factorName, chromosome, start, end);
		this.factorName = factorName;
		this.geneStableId = geneStableId;
		this.score = score;
	}

	@Override
	public String toString() {
		return factorName+"\t"+geneStableId+"\t"+chromosome+"\t"+start+"\t"+end+"\t"+score;
	}
	
	/**
	 * @return the factorName
	 */
	public String getFactorName() {
		return factorName;
	}

	/**
	 * @param factorName the factorName to set
	 */
	public void setFactorName(String factorName) {
		this.factorName = factorName;
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
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}

	
}

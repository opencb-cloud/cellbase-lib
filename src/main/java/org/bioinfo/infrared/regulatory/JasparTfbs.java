package org.bioinfo.infrared.regulatory;

import org.bioinfo.infrared.common.feature.GenomicFeature;

public class JasparTfbs extends GenomicFeature {
	
	private String factorName;
	private String geneStableId;
	private int relativeStart;
	private int relativeEnd;
	private double score;
	
	@Deprecated
	public JasparTfbs(String factorName, String geneStableId,  String chromosome, Integer start, Integer end, Double score) {
		super(factorName, chromosome, start, end);
		this.factorName = factorName;
		this.geneStableId = geneStableId;
		this.score = score;
	}

	public JasparTfbs(String factorName, String geneStableId, Integer relativeStart, Integer relativeEnd, String chromosome, Integer start, Integer end, String strand, Double score, String sequence) {
		super(factorName, chromosome, start, end, strand, sequence);
		this.factorName = factorName;
		this.geneStableId = geneStableId;
		this.relativeStart = relativeStart;
		this.relativeEnd = relativeEnd;
		this.score = score;
	}
	
	@Override
	public String toString() {
		return factorName+"\t"+geneStableId+"\t"+relativeStart+"\t"+relativeEnd+"\t"+chromosome+"\t"+start+"\t"+end+"\t"+strand+"\t"+score+"\t"+sequence;
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

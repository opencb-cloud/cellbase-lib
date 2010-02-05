package org.bioinfo.infrared.regulatory;

import org.bioinfo.infrared.common.feature.GenomicFeature;

public class MiRnaTarget extends GenomicFeature {
	
	private String miRNAId;
	private String transcriptStableId;
	private double score;
	private double pValue;
	
	public MiRnaTarget(String id) {
		super(id);
	}

	public MiRnaTarget(String miRNAId, String transcriptId, String chromosome, Integer start, Integer end, String strand, Double score, Double pValue){
		super(miRNAId, chromosome, start, end, strand);
		this.miRNAId = miRNAId;
		this.transcriptStableId = transcriptId;
		this.score = score;
		this.pValue = pValue;
	}
	
	@Deprecated
	public MiRnaTarget(Integer id, String miRNAId, Integer transcriptId, String chromosome, Integer start, Integer end, String strand, Double score, Double pValue){
		super(miRNAId, chromosome, start, end, strand);
		this.miRNAId = miRNAId;
		this.transcriptStableId = ""+transcriptId;
		this.score = score;
		this.pValue = pValue;
	}
	
	public String toString(){
		return miRNAId+"\t"+transcriptStableId+"\t"+chromosome+"\t"+start+"\t"+end+"\t"+strand+"\t"+score+"\t"+pValue; 
	}

	/**
	 * @return the miRNAId
	 */
	public String getMiRNAId() {
		return miRNAId;
	}

	/**
	 * @param miRNAId the miRNAId to set
	 */
	public void setMiRNAId(String miRNAId) {
		this.miRNAId = miRNAId;
	}

	/**
	 * @return the transcriptStableId
	 */
	public String getTranscriptId() {
		return transcriptStableId;
	}

	/**
	 * @param transcriptStableId the transcriptStableId to set
	 */
	public void setTranscriptId(String transcriptId) {
		this.transcriptStableId = transcriptId;
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

	/**
	 * @return the pValue
	 */
	public double getPValue() {
		return pValue;
	}

	/**
	 * @param value the pValue to set
	 */
	public void setPValue(double value) {
		pValue = value;
	}
	
}

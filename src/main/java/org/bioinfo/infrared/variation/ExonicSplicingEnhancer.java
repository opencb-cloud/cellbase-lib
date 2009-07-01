package org.bioinfo.infrared.variation;

import org.bioinfo.infrared.common.feature.VariationFeature;

public class ExonicSplicingEnhancer extends VariationFeature {

	private String transcriptStableId;
	private String geneStableId;
	private String eseId;
	private int eseRelativeStart;
	private int eseRelativeEnd;
	private int snpRelativePosition;
	private String scoreAllele1;
	private String scoreAllele2;
	private String effect;
	
	public ExonicSplicingEnhancer(String snpId, String transcriptStableId, String geneStableId, String eseId, Integer eseRelativeStart, Integer eseRelativeEnd, Integer snpRelativePosition, String scoreAllele1, String scoreAllele2, String effect, String allele) {
		this.id = snpId;
		this.transcriptStableId = transcriptStableId;
		this.geneStableId = geneStableId;
		this.eseId = eseId;
		this.eseRelativeStart = eseRelativeStart;
		this.eseRelativeEnd = eseRelativeEnd;
		this.snpRelativePosition = snpRelativePosition;
		this.scoreAllele1 = scoreAllele1;
		this.scoreAllele2 = scoreAllele2;
		this.effect = effect;
		this.allele = allele;
	}

	@Override
	public String toString() {
		return id+"\t"+transcriptStableId+"\t"+geneStableId+"\t"+eseId+"\t"+eseRelativeStart+"\t"+eseRelativeEnd+"\t"+snpRelativePosition+"\t"+scoreAllele1+"\t"+scoreAllele2+"\t"+effect+"\t"+allele;
	}
	
	/**
	 * @return the transcriptStableId
	 */
	public String getTranscriptStableId() {
		return transcriptStableId;
	}

	/**
	 * @param transcriptStableId the transcriptStableId to set
	 */
	public void setTranscriptStableId(String transcriptStableId) {
		this.transcriptStableId = transcriptStableId;
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
	 * @return the eseId
	 */
	public String getEseId() {
		return eseId;
	}

	/**
	 * @param eseId the eseId to set
	 */
	public void setEseId(String eseId) {
		this.eseId = eseId;
	}

	/**
	 * @return the eseRelativeStart
	 */
	public int getEseRelativeStart() {
		return eseRelativeStart;
	}

	/**
	 * @param eseRelativeStart the eseRelativeStart to set
	 */
	public void setEseRelativeStart(int eseRelativeStart) {
		this.eseRelativeStart = eseRelativeStart;
	}

	/**
	 * @return the eseRelativeEnd
	 */
	public int getEseRelativeEnd() {
		return eseRelativeEnd;
	}

	/**
	 * @param eseRelativeEnd the eseRelativeEnd to set
	 */
	public void setEseRelativeEnd(int eseRelativeEnd) {
		this.eseRelativeEnd = eseRelativeEnd;
	}

	/**
	 * @return the snpRelativePosition
	 */
	public int getSnpRelativePosition() {
		return snpRelativePosition;
	}

	/**
	 * @param snpRelativePosition the snpRelativePosition to set
	 */
	public void setSnpRelativePosition(int snpRelativePosition) {
		this.snpRelativePosition = snpRelativePosition;
	}

	/**
	 * @return the scoreAllele1
	 */
	public String getScoreAllele1() {
		return scoreAllele1;
	}

	/**
	 * @param scoreAllele1 the scoreAllele1 to set
	 */
	public void setScoreAllele1(String scoreAllele1) {
		this.scoreAllele1 = scoreAllele1;
	}

	/**
	 * @return the scoreAllele2
	 */
	public String getScoreAllele2() {
		return scoreAllele2;
	}

	/**
	 * @param scoreAllele2 the scoreAllele2 to set
	 */
	public void setScoreAllele2(String scoreAllele2) {
		this.scoreAllele2 = scoreAllele2;
	}

	/**
	 * @return the effect
	 */
	public String getEffect() {
		return effect;
	}

	/**
	 * @param effect the effect to set
	 */
	public void setEffect(String effect) {
		this.effect = effect;
	}
	
	
	
}

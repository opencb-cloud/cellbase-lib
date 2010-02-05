package org.bioinfo.infrared.regulatory;

import org.bioinfo.infrared.common.feature.GenomicFeature;

public class TransfacTfbs extends GenomicFeature{

	private String snpId;
	private String geneStableId;
	private String factorId;
	private String factorName;
	private int sequenceLength;
	private int factorRelativeStart;
	private int factorRelativeEnd;
	private int snpRelativePosition;
	private double coreMatch;
	private double matrixMatch;
	private String allele1;
	private String allele2;
	private String effect;
	
	@Deprecated
	public TransfacTfbs(String snpId, String geneStableId, String factorId, String factorName, String sequence, Integer sequenceLength, Integer factorRelativeStart, Integer factorRelativeEnd, Integer snpRelativePosition, Double coreMatch, Double matrixMatch, String allele1, String allele2, String effect) {
		this.snpId = snpId;
		this.geneStableId = geneStableId;
		this.factorId = factorId;
		this.factorName = factorName;
		this.sequence = sequence;
		this.sequenceLength = sequenceLength;
		this.factorRelativeStart = factorRelativeStart;
		this.factorRelativeEnd = factorRelativeEnd;
		this.snpRelativePosition = snpRelativePosition;
		this.coreMatch = coreMatch;
		this.matrixMatch = matrixMatch;
		this.allele1 = allele1;
		this.allele2 = allele2;
		this.effect = effect;
	}

	public TransfacTfbs(Integer transfacTfbsId, String geneStableId, String factorId, String factorName, Integer factorRelativeStart, Integer factorRelativeEnd, String chromosome, Integer absoluteStart, Integer absoluteEnd, String strand, Double coreMatch, Double matrixMatch, Integer sequenceLength, String sequence) {
		super(factorId, chromosome, absoluteStart, absoluteEnd, strand, sequence);
		this.geneStableId = geneStableId;
		this.factorId = factorId;
		this.factorName = factorName;
		this.factorRelativeStart = factorRelativeStart;
		this.factorRelativeEnd = factorRelativeEnd;
		this.coreMatch = coreMatch;
		this.matrixMatch = matrixMatch;
		this.sequenceLength = sequenceLength;
	}
	
	/* (non-Javadoc)
	 * @see org.bioinfo.infrared.common.feature.VariationFeature#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(factorId).append("\t");
		sb.append(geneStableId).append("\t");
		sb.append(factorName).append("\t");
		sb.append(factorRelativeStart).append("\t");
		sb.append(factorRelativeEnd).append("\t");
		sb.append(chromosome).append("\t").append(start).append("\t").append(end).append("\t").append(strand).append("\t");
		sb.append(coreMatch).append("\t");
		sb.append(matrixMatch).append("\t");
		sb.append(sequenceLength).append("\t");
		sb.append(sequence);
		return sb.toString();
	}



	/**
	 * @return the snpId
	 */
	public String getSnpId() {
		return snpId;
	}

	/**
	 * @param snpId the snpId to set
	 */
	public void setSnpId(String snpId) {
		this.snpId = snpId;
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
	 * @return the factorId
	 */
	public String getFactorId() {
		return factorId;
	}

	/**
	 * @param factorId the factorId to set
	 */
	public void setFactorId(String factorId) {
		this.factorId = factorId;
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
	 * @return the sequenceLength
	 */
	public int getSequenceLength() {
		return sequenceLength;
	}

	/**
	 * @param sequenceLength the sequenceLength to set
	 */
	public void setSequenceLength(int sequenceLength) {
		this.sequenceLength = sequenceLength;
	}

	/**
	 * @return the factorRelativeStart
	 */
	public int getFactorRelativeStart() {
		return factorRelativeStart;
	}

	/**
	 * @param factorRelativeStart the factorRelativeStart to set
	 */
	public void setFactorRelativeStart(int factorRelativeStart) {
		this.factorRelativeStart = factorRelativeStart;
	}

	/**
	 * @return the factorRelativeEnd
	 */
	public int getFactorRelativeEnd() {
		return factorRelativeEnd;
	}

	/**
	 * @param factorRelativeEnd the factorRelativeEnd to set
	 */
	public void setFactorRelativeEnd(int factorRelativeEnd) {
		this.factorRelativeEnd = factorRelativeEnd;
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
	 * @return the coreMatch
	 */
	public double getCoreMatch() {
		return coreMatch;
	}

	/**
	 * @param coreMatch the coreMatch to set
	 */
	public void setCoreMatch(double coreMatch) {
		this.coreMatch = coreMatch;
	}

	/**
	 * @return the matrixMatch
	 */
	public double getMatrixMatch() {
		return matrixMatch;
	}

	/**
	 * @param matrixMatch the matrixMatch to set
	 */
	public void setMatrixMatch(double matrixMatch) {
		this.matrixMatch = matrixMatch;
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

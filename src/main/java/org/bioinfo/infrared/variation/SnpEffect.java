package org.bioinfo.infrared.variation;

import org.bioinfo.infrared.common.feature.VariationFeature;

public class SnpEffect extends VariationFeature {

	private String proteinStableId;
	private String geneStableId;
	private String tangoDiff;
	private int foldxDiff;
	private int amySTVIIEDiff;
	private int amygeneralDiff;
	private int phdAccDiff;
	private int phdSecDiff;
	private int phobiusDiff;
	private int phosphoDiff;
	private int glycDiff;
	private int myristoylDiff;
	private int gpiAnchorDiff;
	private int pts1Diff;
	private int farnesylDiff;
	private int typeIGeranylDiff;
	private int typeIIGeranylDiff;
	private int turnoverDiff;
	private int psortDiff;
	
	/**
	 * @param proteinStableId
	 * @param geneStableId
	 * @param tangoDiff
	 * @param foldxDiff
	 * @param amySTVIIEDiff
	 * @param amygeneralDiff
	 * @param phdAccDiff
	 * @param phdSecDiff
	 * @param phobiusDiff
	 * @param phosphoDiff
	 * @param glycDiff
	 * @param myristoylDiff
	 * @param gpiAnchorDiff
	 * @param pts1Diff
	 * @param farnesylDiff
	 * @param typeIGeranylDiff
	 * @param typeIIGeranylDiff
	 * @param turnoverDiff
	 * @param psortDiff
	 */
	public SnpEffect(String snpId, String proteinStableId, String geneStableId, String tangoDiff, Integer foldxDiff, Integer amySTVIIEDiff, Integer amygeneralDiff, Integer phdAccDiff, Integer phdSecDiff, Integer phobiusDiff, Integer phosphoDiff, Integer glycDiff, Integer myristoylDiff, Integer gpiAnchorDiff, Integer pts1Diff, Integer farnesylDiff,	Integer typeIGeranylDiff, Integer typeIIGeranylDiff, Integer turnoverDiff, Integer psortDiff) {
		super(snpId);
		this.proteinStableId = proteinStableId;
		this.geneStableId = geneStableId;
		this.tangoDiff = tangoDiff;
		this.foldxDiff = foldxDiff;
		this.amySTVIIEDiff = amySTVIIEDiff;
		this.amygeneralDiff = amygeneralDiff;
		this.phdAccDiff = phdAccDiff;
		this.phdSecDiff = phdSecDiff;
		this.phobiusDiff = phobiusDiff;
		this.phosphoDiff = phosphoDiff;
		this.glycDiff = glycDiff;
		this.myristoylDiff = myristoylDiff;
		this.gpiAnchorDiff = gpiAnchorDiff;
		this.pts1Diff = pts1Diff;
		this.farnesylDiff = farnesylDiff;
		this.typeIGeranylDiff = typeIGeranylDiff;
		this.typeIIGeranylDiff = typeIIGeranylDiff;
		this.turnoverDiff = turnoverDiff;
		this.psortDiff = psortDiff;
	}

	/**
	 * @param snpId
	 * @param chromosome
	 * @param start
	 * @param end
	 * @param strand
	 * @param sequence
	 * @param allele
	 * @param proteinStableId
	 * @param geneStableId
	 * @param tangoDiff
	 * @param foldxDiff
	 * @param amySTVIIEDiff
	 * @param amygeneralDiff
	 * @param phdAccDiff
	 * @param phdSecDiff
	 * @param phobiusDiff
	 * @param phosphoDiff
	 * @param glycDiff
	 * @param myristoylDiff
	 * @param gpiAnchorDiff
	 * @param pts1Diff
	 * @param farnesylDiff
	 * @param typeIGeranylDiff
	 * @param typeIIGeranylDiff
	 * @param turnoverDiff
	 * @param psortDiff
	 */
	public SnpEffect(String snpId, String chromosome, Integer start, Integer end, String strand, String sequence, String allele, String proteinStableId, String geneStableId, String tangoDiff, Integer foldxDiff, Integer amySTVIIEDiff, Integer amygeneralDiff, Integer phdAccDiff, Integer phdSecDiff, Integer phobiusDiff, Integer phosphoDiff, Integer glycDiff, Integer myristoylDiff, Integer gpiAnchorDiff, Integer pts1Diff, Integer farnesylDiff, Integer typeIGeranylDiff, Integer typeIIGeranylDiff, Integer turnoverDiff, Integer psortDiff) {
		super(snpId, chromosome, start, end, strand, sequence, allele);
		this.proteinStableId = proteinStableId;
		this.geneStableId = geneStableId;
		this.tangoDiff = tangoDiff;
		this.foldxDiff = foldxDiff;
		this.amySTVIIEDiff = amySTVIIEDiff;
		this.amygeneralDiff = amygeneralDiff;
		this.phdAccDiff = phdAccDiff;
		this.phdSecDiff = phdSecDiff;
		this.phobiusDiff = phobiusDiff;
		this.phosphoDiff = phosphoDiff;
		this.glycDiff = glycDiff;
		this.myristoylDiff = myristoylDiff;
		this.gpiAnchorDiff = gpiAnchorDiff;
		this.pts1Diff = pts1Diff;
		this.farnesylDiff = farnesylDiff;
		this.typeIGeranylDiff = typeIGeranylDiff;
		this.typeIIGeranylDiff = typeIIGeranylDiff;
		this.turnoverDiff = turnoverDiff;
		this.psortDiff = psortDiff;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id);
		builder.append("\t");
		builder.append(getLocation());
		builder.append("\t");
		builder.append(allele);
		builder.append("\t");
		builder.append(proteinStableId);
		builder.append("\t");
		builder.append(geneStableId);
		builder.append("\t");
		builder.append(tangoDiff);
		builder.append("\t");
		builder.append(foldxDiff);
		builder.append("\t");
		builder.append(amySTVIIEDiff);
		builder.append("\t");
		builder.append(amygeneralDiff);
		builder.append("\t");
		builder.append(phdAccDiff);
		builder.append("\t");
		builder.append(phdSecDiff);
		builder.append("\t");
		builder.append(phobiusDiff);
		builder.append("\t");
		builder.append(phosphoDiff);
		builder.append("\t");
		builder.append(glycDiff);
		builder.append("\t");
		builder.append(myristoylDiff);
		builder.append("\t");
		builder.append(gpiAnchorDiff);
		builder.append("\t");
		builder.append(pts1Diff);
		builder.append("\t");
		builder.append(farnesylDiff);
		builder.append("\t");
		builder.append(typeIGeranylDiff);
		builder.append("\t");
		builder.append(typeIIGeranylDiff);
		builder.append("\t");
		builder.append(turnoverDiff);
		builder.append("\t");
		builder.append(psortDiff);
		return builder.toString();
	}


	/**
	 * @return the proteinStableId
	 */
	public String getProteinStableId() {
		return proteinStableId;
	}

	/**
	 * @param proteinStableId the proteinStableId to set
	 */
	public void setProteinStableId(String proteinStableId) {
		this.proteinStableId = proteinStableId;
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
	 * @return the tangoDiff
	 */
	public String getTangoDiff() {
		return tangoDiff;
	}

	/**
	 * @param tangoDiff the tangoDiff to set
	 */
	public void setTangoDiff(String tangoDiff) {
		this.tangoDiff = tangoDiff;
	}

	/**
	 * @return the foldxDiff
	 */
	public int getFoldxDiff() {
		return foldxDiff;
	}

	/**
	 * @param foldxDiff the foldxDiff to set
	 */
	public void setFoldxDiff(int foldxDiff) {
		this.foldxDiff = foldxDiff;
	}

	/**
	 * @return the amySTVIIEDiff
	 */
	public int getAmySTVIIEDiff() {
		return amySTVIIEDiff;
	}

	/**
	 * @param amySTVIIEDiff the amySTVIIEDiff to set
	 */
	public void setAmySTVIIEDiff(int amySTVIIEDiff) {
		this.amySTVIIEDiff = amySTVIIEDiff;
	}

	/**
	 * @return the amygeneralDiff
	 */
	public int getAmygeneralDiff() {
		return amygeneralDiff;
	}

	/**
	 * @param amygeneralDiff the amygeneralDiff to set
	 */
	public void setAmygeneralDiff(int amygeneralDiff) {
		this.amygeneralDiff = amygeneralDiff;
	}

	/**
	 * @return the phdAccDiff
	 */
	public int getPhdAccDiff() {
		return phdAccDiff;
	}

	/**
	 * @param phdAccDiff the phdAccDiff to set
	 */
	public void setPhdAccDiff(int phdAccDiff) {
		this.phdAccDiff = phdAccDiff;
	}

	/**
	 * @return the phdSecDiff
	 */
	public int getPhdSecDiff() {
		return phdSecDiff;
	}

	/**
	 * @param phdSecDiff the phdSecDiff to set
	 */
	public void setPhdSecDiff(int phdSecDiff) {
		this.phdSecDiff = phdSecDiff;
	}

	/**
	 * @return the phobiusDiff
	 */
	public int getPhobiusDiff() {
		return phobiusDiff;
	}

	/**
	 * @param phobiusDiff the phobiusDiff to set
	 */
	public void setPhobiusDiff(int phobiusDiff) {
		this.phobiusDiff = phobiusDiff;
	}

	/**
	 * @return the phosphoDiff
	 */
	public int getPhosphoDiff() {
		return phosphoDiff;
	}

	/**
	 * @param phosphoDiff the phosphoDiff to set
	 */
	public void setPhosphoDiff(int phosphoDiff) {
		this.phosphoDiff = phosphoDiff;
	}

	/**
	 * @return the glycDiff
	 */
	public int getGlycDiff() {
		return glycDiff;
	}

	/**
	 * @param glycDiff the glycDiff to set
	 */
	public void setGlycDiff(int glycDiff) {
		this.glycDiff = glycDiff;
	}

	/**
	 * @return the myristoylDiff
	 */
	public int getMyristoylDiff() {
		return myristoylDiff;
	}

	/**
	 * @param myristoylDiff the myristoylDiff to set
	 */
	public void setMyristoylDiff(int myristoylDiff) {
		this.myristoylDiff = myristoylDiff;
	}

	/**
	 * @return the gpiAnchorDiff
	 */
	public int getGpiAnchorDiff() {
		return gpiAnchorDiff;
	}

	/**
	 * @param gpiAnchorDiff the gpiAnchorDiff to set
	 */
	public void setGpiAnchorDiff(int gpiAnchorDiff) {
		this.gpiAnchorDiff = gpiAnchorDiff;
	}

	/**
	 * @return the pts1Diff
	 */
	public int getPts1Diff() {
		return pts1Diff;
	}

	/**
	 * @param pts1Diff the pts1Diff to set
	 */
	public void setPts1Diff(int pts1Diff) {
		this.pts1Diff = pts1Diff;
	}

	/**
	 * @return the farnesylDiff
	 */
	public int getFarnesylDiff() {
		return farnesylDiff;
	}

	/**
	 * @param farnesylDiff the farnesylDiff to set
	 */
	public void setFarnesylDiff(int farnesylDiff) {
		this.farnesylDiff = farnesylDiff;
	}

	/**
	 * @return the typeIGeranylDiff
	 */
	public int getTypeIGeranylDiff() {
		return typeIGeranylDiff;
	}

	/**
	 * @param typeIGeranylDiff the typeIGeranylDiff to set
	 */
	public void setTypeIGeranylDiff(int typeIGeranylDiff) {
		this.typeIGeranylDiff = typeIGeranylDiff;
	}

	/**
	 * @return the typeIIGeranylDiff
	 */
	public int getTypeIIGeranylDiff() {
		return typeIIGeranylDiff;
	}

	/**
	 * @param typeIIGeranylDiff the typeIIGeranylDiff to set
	 */
	public void setTypeIIGeranylDiff(int typeIIGeranylDiff) {
		this.typeIIGeranylDiff = typeIIGeranylDiff;
	}

	/**
	 * @return the turnoverDiff
	 */
	public int getTurnoverDiff() {
		return turnoverDiff;
	}

	/**
	 * @param turnoverDiff the turnoverDiff to set
	 */
	public void setTurnoverDiff(int turnoverDiff) {
		this.turnoverDiff = turnoverDiff;
	}

	/**
	 * @return the psortDiff
	 */
	public int getPsortDiff() {
		return psortDiff;
	}

	/**
	 * @param psortDiff the psortDiff to set
	 */
	public void setPsortDiff(int psortDiff) {
		this.psortDiff = psortDiff;
	}
	
}

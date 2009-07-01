package org.bioinfo.infrared.common.feature;

public class VariationFeature extends GenomicFeature{

//	protected String snpId;
	protected String allele;

	public VariationFeature() {
		// noting to do
	}
	public VariationFeature(String id) {
		super(id);
	}
	
	public VariationFeature(String id, String chromosome, int start, int end, String strand) {
		this(id, chromosome, start, end, strand, "", "");
	}
	
	public VariationFeature(String id, String chromosome, int start, int end, String strand, String sequence, String allele) {
		super(id, chromosome, start, end, strand, sequence);
//		this.snpId = snpId;
		this.allele = allele;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}
	
	/**
	 * @param allele the allele to set
	 */
	public void setAllele(String allele) {
		this.allele = allele;
	}

	/**
	 * @return the allele
	 */
	public String getAllele() {
		return allele;
	}

//	/**
//	 * @param snpId the snpId to set
//	 */
//	public void setSnpId(String snpId) {
//		this.snpId = snpId;
//	}
//
//	/**
//	 * @return the snpId
//	 */
//	public String getSnpId() {
//		return snpId;
//	}

}

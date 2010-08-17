package org.bioinfo.infrared.common.feature;

public class CodingFeature extends GenomicFeature {

//	protected String stableId;
	
	public CodingFeature(String id) {
		super(id);
	}
	
	public CodingFeature(String id, String chromosome, Integer start, Integer end) {
		super(id, chromosome, start, end);
//		this.setStableId(stableId);
	}
	
	public CodingFeature(String id, String chromosome, Integer start, Integer end, String strand) {
		super(id, chromosome, start, end, strand);
//		this.setStableId(stableId);
	}
	
	public CodingFeature(String id, String chromosome, Integer start, Integer end, String strand, String sequence) {
		super(id, chromosome, start, end, strand, sequence);
//		this.setStableId(id);
	}

//	/**
//	 * @param stableId the stableId to set
//	 */
//	public void setStableId(String stableId) {
//		this.stableId = stableId;
//	}
//
//	/**
//	 * @return the stableId
//	 */
//	public String getStableId() {
//		return stableId;
//	}
}

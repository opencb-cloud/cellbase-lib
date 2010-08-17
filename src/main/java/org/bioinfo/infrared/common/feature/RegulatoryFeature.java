  package org.bioinfo.infrared.common.feature;

public class RegulatoryFeature extends GenomicFeature {

//	protected String regulatoryId;
	
	public RegulatoryFeature(String id) {
		super(id);
	}
	
	public RegulatoryFeature(String id, String chromosome, Integer start, Integer end) {
		super(id, chromosome, start, end);
	}

	public RegulatoryFeature(String id, String chromosome, Integer start, Integer end, String strand) {
		super(id, chromosome, start, end, strand);
	}

	public RegulatoryFeature(String id, String chromosome, Integer start, Integer end, String strand, String sequence) {
		super(id, chromosome, start, end, strand, sequence);
//		this.regulatoryId = id;
	}

	
//	/**
//	 * @param regulatoryId the regulatoryId to set
//	 */
//	public void setRegulatoryId(String regulatoryId) {
//		this.regulatoryId = regulatoryId;
//	}
//
//	/**
//	 * @return the regulatoryId
//	 */
//	public String getRegulatoryId() {
//		return regulatoryId;
//	}
	
}

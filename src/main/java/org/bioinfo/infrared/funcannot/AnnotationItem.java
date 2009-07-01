package org.bioinfo.infrared.funcannot;

import org.bioinfo.infrared.common.feature.FunctionalFeature;


public class AnnotationItem extends FunctionalFeature{

//	private String id;
	private String functionalTermId;
	
	public AnnotationItem(String id, String functionalTermId) {
		this.id = id;
		this.functionalTermId = functionalTermId;
	}

	@Override
	public String toString() {
		return id+"\t"+functionalTermId;
	}
	

	/**
	 * @param functionalTermId the functionalTermId to set
	 */
	public void setFunctionalTermId(String functionalTermId) {
		this.functionalTermId = functionalTermId;
	}

	/**
	 * @return the functionalTermId
	 */
	public String getFunctionalTermId() {
		return functionalTermId;
	}
	
	
}

package org.bioinfo.infrared.funcannot;

import org.bioinfo.infrared.common.feature.FunctionalFeature;


public class AnnotationItem extends FunctionalFeature{

//	private String id;
	private String functionalTermId;
	
	public AnnotationItem(String id, String functionalTermId) {
		this.id = id;
		this.functionalTermId = functionalTermId;
	}
	
	public AnnotationItem(String id, String functionalTermId, String name) {
		this(id, functionalTermId);
		this.name = name;
	}

	public AnnotationItem(String id, String functionalTermId, String name, String description) {
		this(id, functionalTermId, name);
		this.description = description;
	}
	
	
	@Override
	public String toString() {
		if(name != null) {
			return id+"\t"+functionalTermId+"\t"+name;		
		}else {
			return id+"\t"+functionalTermId;
		}
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

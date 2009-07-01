package org.bioinfo.infrared.common.feature;


public class FunctionalFeature extends Feature{

//	protected String functionalId;
	protected String name;
	protected String description;
	
	public FunctionalFeature() {
		
	}
	public FunctionalFeature(String id, String name) {
		this.id = id;
//		this.functionalId = id;
		this.name = name;
	}
	
	public FunctionalFeature(String id, String name, String description) {
		this.id = id;
//		this.functionalId = id;
		this.name = name;
		this.description = description;
	}
	
//	@Override
//	public String getId() {
//		return functionalId;
//	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}
	
	public String toAnnotation() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}

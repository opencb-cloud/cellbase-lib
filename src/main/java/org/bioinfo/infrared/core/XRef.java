package org.bioinfo.infrared.core;

import org.bioinfo.infrared.common.feature.Feature;


public class XRef extends Feature {

//	private String id;
	private String dbName;
	private String displayName;
	private String description;
	
	public XRef(String dbname, String displayName, String description) {
		this("", dbname, displayName, description);
	}
	public XRef(String id, String dbname, String displayName, String description) {
//		super(stableId);
		this.id = id;
		this.dbName=dbname;
		this.displayName = displayName;
		this.description = description;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(id).append("\t").append(getDbName()).append("\t").append(getDisplayName()).append("\t").append(getDescription());
		return sb.toString();
	}
	
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param dbName the dbName to set
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	/**
	 * @return the dbName
	 */
	public String getDbName() {
		return dbName;
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

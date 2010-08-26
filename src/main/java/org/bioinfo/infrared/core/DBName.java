package org.bioinfo.infrared.core;

public class DBName {

	private int dbnameId;
	private String dbname;
	private String displayLabel;
	private String dbnameType;
	
	public DBName(String dbname, String displayLabel, String dbnameType) {
		this.dbname = dbname;
		this.displayLabel = displayLabel;
		this.dbnameType = dbnameType;
	}
	
	public DBName(Integer dbnameId, String dbname, String displayLabel, String dbnameType) {
		this.dbnameId = dbnameId;
		this.dbname = dbname;
		this.displayLabel = displayLabel;
		this.dbnameType = dbnameType;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
//		sb.append(dbnameId).append("\t");
		sb.append(dbname).append("\t");
		sb.append(displayLabel).append("\t");
		sb.append(dbnameType);
		return sb.toString();
	}
	
	/**
	 * @return the dbnameId
	 */
	public int getDbnameId() {
		return dbnameId;
	}

	/**
	 * @param dbnameId the dbnameId to set
	 */
	public void setDbnameId(int dbnameId) {
		this.dbnameId = dbnameId;
	}

	/**
	 * @return the dbname
	 */
	public String getDbname() {
		return dbname;
	}

	/**
	 * @param dbname the dbname to set
	 */
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	/**
	 * @return the displayLabel
	 */
	public String getDisplayLabel() {
		return displayLabel;
	}

	/**
	 * @param displayLabel the displayLabel to set
	 */
	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}

	/**
	 * @return the dbnameType
	 */
	public String getDbnameType() {
		return dbnameType;
	}

	/**
	 * @param dbnameType the dbnameType to set
	 */
	public void setDbnameType(String dbnameType) {
		this.dbnameType = dbnameType;
	}
	
} 

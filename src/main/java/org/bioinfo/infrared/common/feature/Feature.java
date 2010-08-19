package org.bioinfo.infrared.common.feature;

import org.bioinfo.infrared.common.dbsql.DBConnector;

public abstract class Feature {

	protected String id;
	protected DBConnector dBConnector;
	
	public Feature() {
		this.dBConnector = null;
	}
	
	public Feature(String id) {
		this();
		this.id = id;
	}
	
	public Feature(DBConnector dBConnector) {
		this.dBConnector = dBConnector;
	}

	
//	@Override
//	public String toString() {
//		return id;
//	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the dBConnector
	 */
	public DBConnector getRosettaDBConnector() {
		return dBConnector;
	}

	/**
	 * @param dBConnector the dBConnector to set
	 */
	public void setRosettaDBConnector(DBConnector dBConnector) {
		this.dBConnector = dBConnector;
	}
	
	
	
}

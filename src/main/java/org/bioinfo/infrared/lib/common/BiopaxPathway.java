package org.bioinfo.infrared.lib.common;

import java.util.ArrayList;
import java.util.List;

public class BiopaxPathway {
	String id, name, dbName, dbSource, dbVersion;
	List<String> species = new ArrayList<String>();
	List<String> displayName = new ArrayList<String>();
	List<String> xref = new ArrayList<String>();
	
	List<PhysicalEntity> physicalEntities = new ArrayList<PhysicalEntity>();
	List<SubPathway> subPathways = new ArrayList<SubPathway>();
	List<Interaction> interactions = new ArrayList<Interaction>();
	
	List<String> allInteractionsIDs = new ArrayList<String>();
	List<String> allEntitiesIDs = new ArrayList<String>();
	
	public BiopaxPathway(String name, String dbName, String dbSource, String dbVersion, List<String> species, List<String> displayName, List<String> xref) {
		this.name = name;
		this.dbName = dbName;
		this.dbSource = dbSource;
		this.dbVersion = dbVersion;
		this.species = species;
		this.displayName = displayName;
		this.xref = xref;
		
		this.id = this.dbSource + "_" + this.dbVersion + "_" + this.name;
	}
}

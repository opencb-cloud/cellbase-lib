package org.bioinfo.infrared.lib.common;

import java.util.ArrayList;
import java.util.List;

public class SubPathway {
	String name;
	List<String> displayName;
	List<SubPathway> subPathways;
	
	public SubPathway(String name, List<String> displayName) {
		this.name = name;
		this.displayName = displayName;
		this.subPathways = new ArrayList<SubPathway>();
	}

	public void addSubpathways(SubPathway sp) {
		this.subPathways.add(sp);
	}
}
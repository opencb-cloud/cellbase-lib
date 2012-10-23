package org.bioinfo.infrared.lib.common;

import java.util.List;

public class SubPathway {
	String name;
	List<SubPathway> subPathways;
	
	public SubPathway(String name) {
		this.name = name;
	}
}

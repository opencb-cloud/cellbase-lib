package org.bioinfo.infrared.funcannot;

import org.bioinfo.infrared.common.feature.FunctionalFeature;


public class Kegg extends FunctionalFeature {

//	private String keggId;
//	private String name;
	private String category1;
	private String category2;
	
	public Kegg(String keggId, String name) {
		super(keggId, name);
//		this.keggId = keggId;
//		this.name = name;
//		this.category1 = category1;
//		this.category2 = category2;
	}
	
//	public String getKeggId() {
//		return keggId;
//	}
//
//	public void setKeggId(String keggId) {
//		this.keggId = keggId;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}

	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}
//	public String toString() {
//		return keggId+"\t"+name+"\t"+category1+"\t"+category2;
//	}
}

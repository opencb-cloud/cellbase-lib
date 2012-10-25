package org.bioinfo.infrared.lib.common;

public class Attribute {

	private String name;
	private String value;
	private String type;
	
	public Attribute(){
		this.name = "";
		this.value = "";
		this.type = "";
	}
	
	public Attribute(String name, String value, String type) {
		this.name = name;
		this.value = value;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}

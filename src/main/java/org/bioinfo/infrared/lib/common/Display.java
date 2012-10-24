package org.bioinfo.infrared.lib.common;

public class Display {

	private String name;
	private int thickStart;
	private int thickEnd;
	private String itemRgb;
	
	public Display(){}

	public Display(String name, int thickStart, int thickEnd, String itemRgb) {
		this.name = name;
		this.thickStart = thickStart;
		this.thickEnd = thickEnd;
		this.itemRgb = itemRgb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getThickStart() {
		return thickStart;
	}

	public void setThickStart(int thickStart) {
		this.thickStart = thickStart;
	}

	public int getThickEnd() {
		return thickEnd;
	}

	public void setThickEnd(int thickEnd) {
		this.thickEnd = thickEnd;
	}

	public String getItemRgb() {
		return itemRgb;
	}

	public void setItemRgb(String itemRgb) {
		this.itemRgb = itemRgb;
	}
	
}

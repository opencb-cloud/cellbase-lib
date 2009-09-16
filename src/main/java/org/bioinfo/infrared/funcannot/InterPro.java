package org.bioinfo.infrared.funcannot;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.commons.utils.StringUtils;

public class InterPro {

	private String interProtId;
	private List<String> transcrits;
	private String description;
	
	public InterPro(String interProtId, List<String> list, String description) {
		this.interProtId = interProtId;
		this.transcrits = list;
		this.description = description;
	}
	
	public String toString() {
		if(transcrits != null)
			return interProtId+"\t"+StringUtils.arrayToString(transcrits, ",")+"\t"+description;
		else
			return interProtId+"\tunknown\t"+description;
	}
	
	public String getInterProtId() {
		return interProtId;
	}

	public void setInterProtId(String interProtId) {
		this.interProtId = interProtId;
	}

	public List<String> getTranscrits() {
		return transcrits;
	}

	public void setTranscrits(ArrayList<String> transcrits) {
		this.transcrits = transcrits;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

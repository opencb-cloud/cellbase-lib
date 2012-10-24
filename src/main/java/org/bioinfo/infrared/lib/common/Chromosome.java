package org.bioinfo.infrared.lib.common;

import java.util.List;

public class Chromosome {

	private String seqName;
	private int start;
	private int end;
	private String strand;
	private String score;
	private String type;
	private String phase;
	private String group;
	private List<Display> displays;
	private List<Attribute> attributes;
	
	public Chromosome(){
		
	}

	public Chromosome(String seqName, int start, int end, String strand,
			String score, String type, String phase, String group,
			List<Display> displays, List<Attribute> attributes) {
		this.seqName = seqName;
		this.start = start;
		this.end = end;
		this.strand = strand;
		this.score = score;
		this.type = type;
		this.phase = phase;
		this.group = group;
		this.displays = displays;
		this.attributes = attributes;
	}

	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getStrand() {
		return strand;
	}

	public void setStrand(String strand) {
		this.strand = strand;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public List<Display> getDisplays() {
		return displays;
	}

	public void setDisplays(List<Display> displays) {
		this.displays = displays;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	
}

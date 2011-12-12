package org.bioinfo.infrared.lib.common;

public class GenomeSequenceBean {

	private int start;
	private String chromosome;
	private int end;
	private String sequence;

	public GenomeSequenceBean(String chromosome, int start, int end, String sequence){
		this.chromosome = chromosome;
		this.start = start;
		this.end = end;
		this.sequence = sequence;
		
		
	}

	public int getStart() {
		return start;
	}

	public String getChromosome() {
		return chromosome;
	}

	public int getEnd() {
		return end;
	}

	public String getSequence() {
		return sequence;
	}
}

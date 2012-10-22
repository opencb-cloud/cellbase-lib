package org.bioinfo.infrared.lib.common;

import java.io.Serializable;

public class Exon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6453125614383801773L;
	private String stableId;
	private String chromosome;
	private int start;
	private int end;
	private String strand;
	private int codingRegionStart;
	private int codingRegionEnd;
	private int cdnaCodingStart;
	private int cdnaCodingEnd;
	private int phase;
	private int exonNumber;
	
	public Exon() {
		
	}

	public Exon(String stableId, String chromosome, Integer start, Integer end, String strand, Integer codingRegionStart, Integer codingRegionEnd, Integer cdnaCodingStart, Integer cdnaCodingEnd, Integer phase, Integer exonNumber) {
		super();
		this.stableId = stableId;
		this.chromosome = chromosome;
		this.start = start;
		this.end = end;
		this.strand = strand;
		this.codingRegionStart = codingRegionStart;
		this.codingRegionEnd = codingRegionEnd;
		this.cdnaCodingStart = cdnaCodingStart;
		this.cdnaCodingEnd = cdnaCodingEnd;
		this.phase = phase;
		this.exonNumber = exonNumber;
	}

	public String getStableId() {
		return stableId;
	}

	public void setStableId(String stableId) {
		this.stableId = stableId;
	}

	public String getChromosome() {
		return chromosome;
	}

	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
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

	public int getExonNumber() {
		return exonNumber;
	}

	public void setExonNumber(int exonNumber) {
		this.exonNumber = exonNumber;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public int getCodingRegionStart() {
		return codingRegionStart;
	}

	public void setCodingRegionStart(int codingRegionStart) {
		this.codingRegionStart = codingRegionStart;
	}

	public int getCodingRegionEnd() {
		return codingRegionEnd;
	}

	public void setCodingRegionEnd(int codingRegionEnd) {
		this.codingRegionEnd = codingRegionEnd;
	}

	public int getCdnaCodingStart() {
		return cdnaCodingStart;
	}

	public void setCdnaCodingStart(int cdnaCodingStart) {
		this.cdnaCodingStart = cdnaCodingStart;
	}

	public int getCdnaCodingEnd() {
		return cdnaCodingEnd;
	}

	public void setCdnaCodingEnd(int cdnaCodingEnd) {
		this.cdnaCodingEnd = cdnaCodingEnd;
	}
	
	
}

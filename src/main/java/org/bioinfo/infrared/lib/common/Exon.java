package org.bioinfo.infrared.lib.common;

public class Exon {

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

	public Exon(String stableId, String chromosome, int start, int end, String strand, int codingRegionStart, int codingRegionEnd, int cdnaCodingStart, int cdnaCodingEnd, int phase, int exonNumber) {
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

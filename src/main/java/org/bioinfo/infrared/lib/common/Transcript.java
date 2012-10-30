package org.bioinfo.infrared.lib.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Transcript implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2069002722080532350L;
	private String stableId;
	private String externalName;
	private String biotype;
	private String status;
	private String chromosome;
	private int start;
	private int end;
	private String strand;
	private int genomicCodingStart;
	private int genomicCodingEnd;
	private int cdnaCodingStart;
	private int cdnaCodingEnd;
	private String proteinID;
	private String description;
	private List<Exon> exons;
	

	public Transcript() {
		
	}

	public Transcript(String stableId, String externalName, String biotype, String status, String chromosome, Integer start, Integer end, String strand, Integer codingRegionStart, Integer codingRegionEnd, Integer cdnaCodingStart, Integer cdnaCodingEnd, String proteinId, String description, ArrayList<Exon> exons) {
		this.stableId = stableId;
		this.externalName = externalName;
		this.biotype = biotype;
		this.status = status;
		this.chromosome = chromosome;
		this.start = start;
		this.end = end;
		this.strand = strand;
		this.genomicCodingStart = codingRegionStart;
		this.genomicCodingEnd = codingRegionEnd;
		this.cdnaCodingStart = cdnaCodingStart;
		this.cdnaCodingEnd = cdnaCodingEnd;
		this.proteinID = proteinId;
		this.description = description;
		this.exons = exons;
	}

	public String getStableId() {
		return stableId;
	}

	public void setStableId(String stableId) {
		this.stableId = stableId;
	}

	public String getExternalName() {
		return externalName;
	}

	public void setExternalName(String externalName) {
		this.externalName = externalName;
	}

	public String getBiotype() {
		return biotype;
	}

	public void setBiotype(String biotype) {
		this.biotype = biotype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public int getGenomicCodingStart() {
		return genomicCodingStart;
	}

	public void setGenomicCodingStart(int genomicCodingStart) {
		this.genomicCodingStart = genomicCodingStart;
	}

	public int getGenomicCodingEnd() {
		return genomicCodingEnd;
	}

	public void setGenomicCodingEnd(int genomicCodingEnd) {
		this.genomicCodingEnd = genomicCodingEnd;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Exon> getExons() {
		return exons;
	}

	public void setExons(List<Exon> exons) {
		this.exons = exons;
	}

	public String getProteinID() {
		return proteinID;
	}

	public void setProteinID(String proteinID) {
		this.proteinID = proteinID;
	}
	
}

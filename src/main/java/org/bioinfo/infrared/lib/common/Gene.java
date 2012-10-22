package org.bioinfo.infrared.lib.common;

import java.io.Serializable;
import java.util.List;

public class Gene implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5804770440067183880L;
	/**
	 * 
	 */
	private String stableId;
	private String geneName;
	private String biotype;
	private String status;
	private String chromosome;
	private int start;
	private int end;
	private String strand;
	private String source;
	private String description;
	private List<Transcript> transcripts;

	
	public Gene() {
		
	}

	public Gene(String stableId, String externalName, String biotype, String status, 
			String chromosome, Integer start, Integer end, String strand, String source, String description, List<Transcript> transcripts) {
		super();
		this.stableId = stableId;
		this.geneName = externalName;
		this.biotype = biotype;
		this.status = status;
		this.chromosome = chromosome;
		this.start = start;
		this.end = end;
		this.strand = strand;
		this.source = source;
		this.description = description;
		this.transcripts = transcripts;
	}

	public String getStableId() {
		return stableId;
	}

	public void setStableId(String stableId) {
		this.stableId = stableId;
	}

	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String externalName) {
		this.geneName = externalName;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Transcript> getTranscripts() {
		return transcripts;
	}

	public void setTranscripts(List<Transcript> transcripts) {
		this.transcripts = transcripts;
	}
	
}

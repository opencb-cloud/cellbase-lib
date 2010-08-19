package org.bioinfo.infrared.core;

import org.bioinfo.infrared.common.feature.CodingFeature;

public class Exon extends CodingFeature{

	private String transcriptId;
	private String geneId;
	
	public Exon(String exonId, String chromosome, Integer start, Integer end, String strand) {
		this(exonId, chromosome, start, end, strand, "", "", "");
	}
	
	public Exon(String exonId, String chromosome, Integer start, Integer end, String strand, String sequence) {
		this(exonId, chromosome, start, end, strand, "", "", sequence);
	}

	public Exon(String exonId, String chromosome, Integer start, Integer end, String strand, String transcriptId, String geneId) {
		super(exonId, chromosome, start, end, strand, "");
		this.transcriptId = transcriptId;
		this.geneId = geneId;
	}
	
	public Exon(String exonId, String chromosome, Integer start, Integer end, String strand, String transcriptId, String geneId, String sequence) {
		super(exonId, chromosome, start, end, strand, sequence);
		this.transcriptId = transcriptId;
		this.geneId = geneId;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id).append("\t");
		builder.append(transcriptId).append("\t");
		builder.append(geneId).append("\t");
		builder.append(chromosome).append("\t");
		builder.append(start).append("\t");
		builder.append(end).append("\t");
		builder.append(strand);
		return builder.toString();
	}

	/**
	 * @param transcriptId the transcriptId to set
	 */
	public void setTranscriptId(String transcriptId) {
		this.transcriptId = transcriptId;
	}

	/**
	 * @return the transcriptId
	 */
	public String getTranscriptId() {
		return transcriptId;
	}

	/**
	 * @param geneId the geneId to set
	 */
	public void setGeneId(String geneId) {
		this.geneId = geneId;
	}

	/**
	 * @return the geneId
	 */
	public String getGeneId() {
		return geneId;
	}

}

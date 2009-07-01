package org.bioinfo.infrared.variation;

import org.bioinfo.infrared.common.feature.VariationFeature;

public class ExonicSplicingSilencer extends VariationFeature {

	private String transcriptStableId;
	private String geneStableId;
	private int snpAbsolutePosition;
	
	public ExonicSplicingSilencer(String snpId, String ensemblTranscriptId, String ensemblGeneId, String essSequence, Integer essAbsoluteStart,	Integer essAbsoluteEnd, Integer snpAbsolutePosition, String allele) {
		super(snpId,"NA",essAbsoluteStart, essAbsoluteEnd, "NA", essSequence, allele);
//		this.snpId = snpId;
		this.transcriptStableId = ensemblTranscriptId;
		this.geneStableId = ensemblGeneId;
		this.snpAbsolutePosition = snpAbsolutePosition;
	}

	@Override
	public String toString() {
		return id+"\t"+transcriptStableId+"\t"+geneStableId+"\t"+sequence+"\t"+start+"\t"+end+"\t"+snpAbsolutePosition+"\t"+allele;
	}
	

	/**
	 * @return the transcriptStableId
	 */
	public String getEnsemblTranscriptId() {
		return transcriptStableId;
	}

	/**
	 * @param transcriptStableId the transcriptStableId to set
	 */
	public void setEnsemblTranscriptId(String ensemblTranscriptId) {
		this.transcriptStableId = ensemblTranscriptId;
	}

	/**
	 * @return the geneStableId
	 */
	public String getEnsemblGeneId() {
		return geneStableId;
	}

	/**
	 * @param geneStableId the geneStableId to set
	 */
	public void setEnsemblGeneId(String ensemblGeneId) {
		this.geneStableId = ensemblGeneId;
	}

	/**
	 * @return the snpAbsolutePosition
	 */
	public int getSnpAbsolutePosition() {
		return snpAbsolutePosition;
	}

	/**
	 * @param snpAbsolutePosition the snpAbsolutePosition to set
	 */
	public void setSnpAbsolutePosition(int snpAbsolutePosition) {
		this.snpAbsolutePosition = snpAbsolutePosition;
	}
		
}

package org.bioinfo.infrared.regulatory;

import org.bioinfo.infrared.common.feature.RegulatoryFeature;


public class MiRNAInfo extends RegulatoryFeature {
	
	private String matureId;
	private String matureSequence;
	private String miRNAGeneId;
	private String status;
	private String miRNAGeneSequence;
	
	public MiRNAInfo(String id) {
		super(""+id);
	}
	
	public MiRNAInfo(Integer id, String matureId, String matureSequence, String miRNAGeneId, String status, String geneSequence){
		super(""+id);
		this.matureId = matureId;
		this.matureSequence = matureSequence;
		this.setMiRNAGeneId(miRNAGeneId);
		this.status = status;
		this.setMiRNAGeneSequence(geneSequence);		
	}
	
	@Override
	public String toString(){
		return id+"\t"+matureId+"\t"+matureSequence+"\t"+getMiRNAGeneId()+"\t"+status+"\t"+getMiRNAGeneSequence();
	}

	/**
	 * @return the matureId
	 */
	public String getMatureId() {
		return matureId;
	}

	/**
	 * @param matureId the matureId to set
	 */
	public void setMatureId(String matureId) {
		this.matureId = matureId;
	}

	/**
	 * @return the matureSequence
	 */
	public String getMatureSequence() {
		return matureSequence;
	}

	/**
	 * @param matureSequence the matureSequence to set
	 */
	public void setMatureSequence(String matureSequence) {
		this.matureSequence = matureSequence;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param miRNAGeneId the miRNAGeneId to set
	 */
	public void setMiRNAGeneId(String miRNAGeneId) {
		this.miRNAGeneId = miRNAGeneId;
	}

	/**
	 * @return the miRNAGeneId
	 */
	public String getMiRNAGeneId() {
		return miRNAGeneId;
	}

	/**
	 * @param miRNAGeneSequence the miRNAGeneSequence to set
	 */
	public void setMiRNAGeneSequence(String miRNAGeneSequence) {
		this.miRNAGeneSequence = miRNAGeneSequence;
	}

	/**
	 * @return the miRNAGeneSequence
	 */
	public String getMiRNAGeneSequence() {
		return miRNAGeneSequence;
	}

	
}

package org.bioinfo.infrared.regulatory;

import org.bioinfo.infrared.common.feature.RegulatoryFeature;


public class MiRnaMature extends RegulatoryFeature {
	
	private String matureId;
	private String matureSequence;
	private String miRnaGeneId;
	private String status;
	private String miRnaGeneSequence;
	
	public MiRnaMature(String id) {
		super(""+id);
	}
	
	public MiRnaMature(Integer id, String matureId, String matureSequence, String miRNAGeneId, String status, String geneSequence){
		super(""+id);
		this.matureId = matureId;
		this.matureSequence = matureSequence;
		this.setMiRnaGeneId(miRNAGeneId);
		this.status = status;
		this.setMiRnaGeneSequence(geneSequence);		
	}
	
	@Override
	public String toString(){
		return id+"\t"+matureId+"\t"+matureSequence+"\t"+getMiRnaGeneId()+"\t"+status+"\t"+getMiRnaGeneSequence();
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
	 * @param miRnaGeneId the miRnaGeneId to set
	 */
	public void setMiRnaGeneId(String miRNAGeneId) {
		this.miRnaGeneId = miRNAGeneId;
	}

	/**
	 * @return the miRnaGeneId
	 */
	public String getMiRnaGeneId() {
		return miRnaGeneId;
	}

	/**
	 * @param miRnaGeneSequence the miRnaGeneSequence to set
	 */
	public void setMiRnaGeneSequence(String miRNAGeneSequence) {
		this.miRnaGeneSequence = miRNAGeneSequence;
	}

	/**
	 * @return the miRnaGeneSequence
	 */
	public String getMiRnaGeneSequence() {
		return miRnaGeneSequence;
	}

	
}

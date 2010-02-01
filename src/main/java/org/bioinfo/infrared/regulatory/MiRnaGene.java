package org.bioinfo.infrared.regulatory;

import org.bioinfo.infrared.common.feature.GenomicFeature;

public class MiRnaGene extends GenomicFeature{

	private String accession;
	private String mirnaID;
	private String status;
	
	public MiRnaGene(String accession, String mirnaID, String status, String sequence) {
		this.accession = accession;
		this.mirnaID = mirnaID;
		this.status = status;
		this.sequence = sequence;
	}
	
	@Override
	public String toString() {
		return accession+"\t"+mirnaID+"\t"+status+"\t"+sequence; 
	}
	/**
	 * @return the accession
	 */
	public String getAccession() {
		return accession;
	}
	/**
	 * @param accession the accession to set
	 */
	public void setAccession(String accession) {
		this.accession = accession;
	}
	/**
	 * @return the mirnaID
	 */
	public String getMirnaID() {
		return mirnaID;
	}
	/**
	 * @param mirnaID the mirnaID to set
	 */
	public void setMirnaID(String mirnaID) {
		this.mirnaID = mirnaID;
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
	
}

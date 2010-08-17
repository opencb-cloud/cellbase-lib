package org.bioinfo.infrared.regulatory;

import org.bioinfo.infrared.common.feature.GenomicFeature;

public class OregannoTfbs extends GenomicFeature{
	
	private int oregannoId;
//	private String landMarkId;
	private String name;
	private String landMarkType;
	private String geneName;
	private String geneId;
	private String geneSource;
	private String tfName;
	private String tfId;
	private String tfSource;
	private int pubmedId;
	
	
	/**
	 * @param oregannoId
	 * @param landMarkId
	 * @param name
	 * @param landMarkType
	 * @param geneName
	 * @param geneId
	 * @param geneSource
	 * @param tfName
	 * @param tfId
	 * @param tfSource
	 * @param pubmedId
	 */
	public OregannoTfbs(Integer oregannoId, String landMarkId, String name,	String chromosome, Integer start, Integer end, String strand, String landMarkType, String geneName, String geneId, String geneSource, String tfName, String tfId, String tfSource, Integer pubmedId) {
		super(landMarkId, chromosome, start, end, strand);
//		this.landMarkId = landMarkId;
		this.name = name;
		this.landMarkType = landMarkType;
		this.geneName = geneName;
		this.geneId = geneId;
		this.geneSource = geneSource;
		this.tfName = tfName;
		this.tfId = tfId;
		this.tfSource = tfSource;
		this.pubmedId = pubmedId;
	}
	
	@Override
	public String toString() {
		return id+"\t"+name+"\t"+chromosome+"\t"+start+"\t"+end+"\t"+strand+"\t"+landMarkType+"\t"+geneName+"\t"+geneId+"\t"+geneSource+"\t"+tfName+"\t"+tfId+"\t"+tfSource+"\t"+pubmedId;
	}
	
	/**
	 * @return the oregannoId
	 */
	public int getOregannoId() {
		return oregannoId;
	}
	/**
	 * @param oregannoId the oregannoId to set
	 */
	public void setOregannoId(int oregannoId) {
		this.oregannoId = oregannoId;
	}
	
//	/**
//	 * @return the landMarkId
//	 */
//	public String getLandMarkId() {
//		return landMarkId;
//	}
//	/**
//	 * @param landMarkId the landMarkId to set
//	 */
//	public void setLandMarkId(String landMarkId) {
//		this.landMarkId = landMarkId;
//	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the landMarkType
	 */
	public String getLandMarkType() {
		return landMarkType;
	}
	/**
	 * @param landMarkType the landMarkType to set
	 */
	public void setLandMarkType(String landMarkType) {
		this.landMarkType = landMarkType;
	}
	/**
	 * @return the geneName
	 */
	public String getGeneName() {
		return geneName;
	}
	/**
	 * @param geneName the geneName to set
	 */
	public void setGeneName(String geneNAme) {
		this.geneName = geneNAme;
	}
	/**
	 * @return the geneId
	 */
	public String getGeneId() {
		return geneId;
	}
	/**
	 * @param geneId the geneId to set
	 */
	public void setGeneId(String geneId) {
		this.geneId = geneId;
	}
	/**
	 * @return the geneSource
	 */
	public String getGeneSource() {
		return geneSource;
	}
	/**
	 * @param geneSource the geneSource to set
	 */
	public void setGeneSource(String geneSource) {
		this.geneSource = geneSource;
	}
	/**
	 * @return the tfName
	 */
	public String getTfName() {
		return tfName;
	}
	/**
	 * @param tfName the tfName to set
	 */
	public void setTfName(String tfName) {
		this.tfName = tfName;
	}
	/**
	 * @return the tfId
	 */
	public String getTfId() {
		return tfId;
	}
	/**
	 * @param tfId the tfId to set
	 */
	public void setTfId(String tfId) {
		this.tfId = tfId;
	}
	/**
	 * @return the tfSource
	 */
	public String getTfSource() {
		return tfSource;
	}
	/**
	 * @param tfSource the tfSource to set
	 */
	public void setTfSource(String tfSource) {
		this.tfSource = tfSource;
	}
	/**
	 * @return the pubmedId
	 */
	public int getPubmedId() {
		return pubmedId;
	}
	/**
	 * @param pubmedId the pubmedId to set
	 */
	public void setPubmedId(int pubmedId) {
		this.pubmedId = pubmedId;
	}
	
}

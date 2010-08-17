package org.bioinfo.infrared.core;

import org.bioinfo.infrared.common.feature.CodingFeature;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.core.dbsql.ExonDBManager;
import org.bioinfo.infrared.variation.SNP;

public class Transcript extends CodingFeature {

	private String biotype;
	
//	private ExonList exons;
//	private SNPList snps;
	
	private FeatureList<Exon> exons;
	private FeatureList<SNP> snps;
	
	public Transcript(String transcriptId, String chromosome, Integer start, Integer end, String strand, String biotype) {
		super(transcriptId, chromosome, start, end, strand);
		this.setBiotype(biotype);
	}
	
	public Transcript(String transcriptId, String chromosome, Integer start, Integer end, String strand, String biotype, String sequence) {
		super(transcriptId, chromosome, start, end, strand, sequence);
		this.setBiotype(biotype);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getId()).append("\t").append(super.toString()).append("\t").append(biotype);
		return sb.toString();
	}

	
	/**
	 * @return the exons
	 */
	public FeatureList<Exon> getExons() {
		if(exons == null) {
			ExonDBManager ef = new ExonDBManager(getRosettaDBConnector());
			try {
				exons = ef.getAllById(getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return exons;
	}

	@Override
	public String getSequence() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**************************************************
	 * ************************************************
	 * *******	GETTERS AND SETTERS	*******************
	 * ************************************************
	 **************************************************/
	
	/**
	 * @param exons the exons to set
	 */
	public void setExons(FeatureList<Exon> exons) {
		this.exons = exons;
	}

	/**
	 * @param snps the snps to set
	 */
	public void setSnps(FeatureList<SNP> snps) {
		this.snps = snps;
	}

	/**
	 * @return the snps
	 */
	public FeatureList<SNP> getSnps() {
		if(snps == null) {
			
		}
		return snps;
	}

	/**
	 * @param biotype the biotype to set
	 */
	public void setBiotype(String biotype) {
		this.biotype = biotype;
	}

	/**
	 * @return the biotype
	 */
	public String getBiotype() {
		return biotype;
	}
	
}

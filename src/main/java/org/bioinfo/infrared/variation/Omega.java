package org.bioinfo.infrared.variation;

import org.bioinfo.infrared.common.feature.VariationFeature;

public class Omega extends VariationFeature{

	private String transcriptStableId;
	private String aminoacidChange;
	private String aminoacidEnviroment;
	private int aminoacidRelativePosition;
	private double wSlr;
	private String slrPvalue;
	private double wBayesian;
	private String bayesianModel;
	private String extrapolated;
	
	public Omega(String snpId, String chromosome, Integer start, Integer end, String strand, String sequence, String allele, String ensemblTranscriptId, String aminoacidChange, String aminoacidEnviroment, Integer aminoacidRelativePosition, Double slr, String slrPvalue, Double bayesian, String bayesianModel, String extrapolated) {
		super(snpId, chromosome, start, end, strand, sequence, allele);
		this.transcriptStableId = ensemblTranscriptId;
		this.aminoacidChange = aminoacidChange;
		this.aminoacidEnviroment = aminoacidEnviroment;
		this.aminoacidRelativePosition = aminoacidRelativePosition;
		this.wSlr = slr;
		this.slrPvalue = slrPvalue;
		this.wBayesian = bayesian;
		this.bayesianModel = bayesianModel;
		this.extrapolated = extrapolated;
	}

	@Override
	public String toString() {
		return id+"\t"+getLocation()+"\t"+allele+"\t"+transcriptStableId+"\t"+aminoacidChange+"\t"+aminoacidEnviroment+"\t"+aminoacidRelativePosition+"\t"+wSlr+"\t"+slrPvalue+"\t"+wBayesian+"\t"+bayesianModel+"\t"+extrapolated;
	}

	
	/**
	 * @return the transcriptStableId
	 */
	public String getTranscriptStableId() {
		return transcriptStableId;
	}

	/**
	 * @param transcriptStableId the transcriptStableId to set
	 */
	public void setTranscriptStableId(String transcriptStableId) {
		this.transcriptStableId = transcriptStableId;
	}

	/**
	 * @return the aminoacidChange
	 */
	public String getAminoacidChange() {
		return aminoacidChange;
	}

	/**
	 * @param aminoacidChange the aminoacidChange to set
	 */
	public void setAminoacidChange(String aminoacidChange) {
		this.aminoacidChange = aminoacidChange;
	}

	/**
	 * @return the aminoacidEnviroment
	 */
	public String getAminoacidEnviroment() {
		return aminoacidEnviroment;
	}

	/**
	 * @param aminoacidEnviroment the aminoacidEnviroment to set
	 */
	public void setAminoacidEnviroment(String aminoacidEnviroment) {
		this.aminoacidEnviroment = aminoacidEnviroment;
	}

	/**
	 * @return the aminoacidRelativePosition
	 */
	public int getAminoacidRelativePosition() {
		return aminoacidRelativePosition;
	}

	/**
	 * @param aminoacidRelativePosition the aminoacidRelativePosition to set
	 */
	public void setAminoacidRelativePosition(int aminoacidRelativePosition) {
		this.aminoacidRelativePosition = aminoacidRelativePosition;
	}

	/**
	 * @return the wSlr
	 */
	public double getWSlr() {
		return wSlr;
	}

	/**
	 * @param slr the wSlr to set
	 */
	public void setWSlr(double slr) {
		wSlr = slr;
	}

	/**
	 * @return the slrPvalue
	 */
	public String getSlrPvalue() {
		return slrPvalue;
	}

	/**
	 * @param slrPvalue the slrPvalue to set
	 */
	public void setSlrPvalue(String slrPvalue) {
		this.slrPvalue = slrPvalue;
	}

	/**
	 * @return the wBayesian
	 */
	public double getWBayesian() {
		return wBayesian;
	}

	/**
	 * @param bayesian the wBayesian to set
	 */
	public void setWBayesian(double bayesian) {
		wBayesian = bayesian;
	}

	/**
	 * @return the bayesianModel
	 */
	public String getBayesianModel() {
		return bayesianModel;
	}

	/**
	 * @param bayesianModel the bayesianModel to set
	 */
	public void setBayesianModel(String bayesianModel) {
		this.bayesianModel = bayesianModel;
	}

	/**
	 * @return the extrapolated
	 */
	public String getExtrapolated() {
		return extrapolated;
	}

	/**
	 * @param extrapolated the extrapolated to set
	 */
	public void setExtrapolated(String extrapolated) {
		this.extrapolated = extrapolated;
	}	
	
}

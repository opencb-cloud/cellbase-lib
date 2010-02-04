package org.bioinfo.infrared.variation;

import org.bioinfo.infrared.common.feature.VariationFeature;

public class AlleleFrequency extends VariationFeature {
	
	private String population;
	private String referenceAllele;
	private double referenceAlleleFrequency;
	private int referenceAlleleCount;
	private String otherAllele;
	private double otherAlleleFrequency;
	private int otherAlleleCount;
	private int totalAlleleCount;
	
	
	/**
	 * @param population
	 * @param referenceAllele
	 * @param referenceAlleleFrequency
	 * @param referenceAlleleCount
	 * @param otherAllele
	 * @param otherAlleleFrequency
	 * @param otherAlleleCount
	 * @param totalAlleleCount
	 */
	public AlleleFrequency(String snpId, String population, String referenceAllele, Double referenceAlleleFrequency, Integer referenceAlleleCount, String otherAllele, Double otherAlleleFrequency, Integer otherAlleleCount, Integer totalAlleleCount) {
		this.id = snpId;
		this.population = population;
		this.referenceAllele = referenceAllele;
		this.referenceAlleleFrequency = referenceAlleleFrequency;
		this.referenceAlleleCount = referenceAlleleCount;
		this.otherAllele = otherAllele;
		this.otherAlleleFrequency = otherAlleleFrequency;
		this.otherAlleleCount = otherAlleleCount;
		this.totalAlleleCount = totalAlleleCount;
	}

	/**
	 * @param id
	 * @param chromosome
	 * @param start
	 * @param end
	 * @param strand
	 * @param sequence
	 * @param allele
	 * @param population
	 * @param referenceAllele
	 * @param referenceAlleleFrequency
	 * @param referenceAlleleCount
	 * @param otherAllele
	 * @param otherAlleleFrequency
	 * @param otherAlleleCount
	 * @param totalAlleleCount
	 */
	public AlleleFrequency(String id, String chromosome, Integer start, Integer end, String strand, String sequence, String allele, String population, String referenceAllele, Double referenceAlleleFrequency, Integer referenceAlleleCount, String otherAllele, Double otherAlleleFrequency, Integer otherAlleleCount, Integer totalAlleleCount) {
		super(id, chromosome, start, end, strand, sequence, allele);
		this.population = population;
		this.referenceAllele = referenceAllele;
		this.referenceAlleleFrequency = referenceAlleleFrequency;
		this.referenceAlleleCount = referenceAlleleCount;
		this.otherAllele = otherAllele;
		this.otherAlleleFrequency = otherAlleleFrequency;
		this.otherAlleleCount = otherAlleleCount;
		this.totalAlleleCount = totalAlleleCount;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id);
		builder.append("\t");
		builder.append(getLocation());
		builder.append("\t");
		builder.append(allele);
		builder.append("\t");
		builder.append(population);
		builder.append("\t");
		builder.append(referenceAllele);
		builder.append("\t");
		builder.append(referenceAlleleCount);
		builder.append("\t");
		builder.append(referenceAlleleFrequency);
		builder.append("\t");
		builder.append(otherAllele);
		builder.append("\t");
		builder.append(otherAlleleCount);
		builder.append("\t");
		builder.append(otherAlleleFrequency);
		builder.append("\t");
		builder.append(totalAlleleCount);
		return builder.toString();
	}
	
	/**
	 * @return the population
	 */
	public String getPopulation() {
		return population;
	}

	/**
	 * @param population the population to set
	 */
	public void setPopulation(String population) {
		this.population = population;
	}

	/**
	 * @return the referenceAllele
	 */
	public String getReferenceAllele() {
		return referenceAllele;
	}

	/**
	 * @param referenceAllele the referenceAllele to set
	 */
	public void setReferenceAllele(String referenceAllele) {
		this.referenceAllele = referenceAllele;
	}

	/**
	 * @return the referenceAlleleFrequency
	 */
	public double getReferenceAlleleFrequency() {
		return referenceAlleleFrequency;
	}

	/**
	 * @param referenceAlleleFrequency the referenceAlleleFrequency to set
	 */
	public void setReferenceAlleleFrequency(double referenceAlleleFrequency) {
		this.referenceAlleleFrequency = referenceAlleleFrequency;
	}

	/**
	 * @return the referenceAlleleCount
	 */
	public int getReferenceAlleleCount() {
		return referenceAlleleCount;
	}

	/**
	 * @param referenceAlleleCount the referenceAlleleCount to set
	 */
	public void setReferenceAlleleCount(int referenceAlleleCount) {
		this.referenceAlleleCount = referenceAlleleCount;
	}

	/**
	 * @return the otherAllele
	 */
	public String getOtherAllele() {
		return otherAllele;
	}

	/**
	 * @param otherAllele the otherAllele to set
	 */
	public void setOtherAllele(String otherAllele) {
		this.otherAllele = otherAllele;
	}

	/**
	 * @return the otherAlleleFrequency
	 */
	public double getOtherAlleleFrequency() {
		return otherAlleleFrequency;
	}

	/**
	 * @param otherAlleleFrequency the otherAlleleFrequency to set
	 */
	public void setOtherAlleleFrequency(double otherAlleleFrequency) {
		this.otherAlleleFrequency = otherAlleleFrequency;
	}

	/**
	 * @return the otherAlleleCount
	 */
	public int getOtherAlleleCount() {
		return otherAlleleCount;
	}

	/**
	 * @param otherAlleleCount the otherAlleleCount to set
	 */
	public void setOtherAlleleCount(int otherAlleleCount) {
		this.otherAlleleCount = otherAlleleCount;
	}

	/**
	 * @return the totalAlleleCount
	 */
	public int getTotalAlleleCount() {
		return totalAlleleCount;
	}

	/**
	 * @param totalAlleleCount the totalAlleleCount to set
	 */
	public void setTotalAlleleCount(int totalAlleleCount) {
		this.totalAlleleCount = totalAlleleCount;
	}

}

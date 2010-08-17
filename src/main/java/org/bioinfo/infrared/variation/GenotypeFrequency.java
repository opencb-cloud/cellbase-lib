package org.bioinfo.infrared.variation;

import org.bioinfo.infrared.common.feature.VariationFeature;

public class GenotypeFrequency extends VariationFeature {

	private String population;
	private String referenceAlleleHomozygote;
	private double referenceAlleleHomozygoteFrequency;
	private int referenceAlleleHomozygoteCount;
	private String alleleHeterozygote;
	private double alleleHeterozygoteFrequency;
	private int alleleHeterozygoteCount;
	private String otherAlleleHomozygote;
	private double otherAlleleHeterozygoteFrequency;
	private int otherAlleleHeterozygoteCount;
	private int totalGenotypeCount;

	/**
	 * @param population
	 * @param referenceAlleleHomozygote
	 * @param referenceAlleleHomozygoteFrequency
	 * @param referenceAlleleHomozygoteCount
	 * @param alleleHeterozygote
	 * @param alleleHeterozygoteFrequency
	 * @param alleleHeterozygoteCount
	 * @param otherAlleleHomozygote
	 * @param otherAlleleHeterozygoteFrequency
	 * @param otherAlleleHeterozygoteCount
	 * @param totalGenotypeCount
	 */
	public GenotypeFrequency(String population, String referenceAlleleHomozygote, Double referenceAlleleHomozygoteFrequency, Integer referenceAlleleHomozygoteCount, String alleleHeterozygote, Double alleleHeterozygoteFrequency, Integer alleleHeterozygoteCount, String otherAlleleHomozygote, Double otherAlleleHeterozygoteFrequency, Integer otherAlleleHeterozygoteCount, Integer totalGenotypeCount) {
		this.population = population;
		this.referenceAlleleHomozygote = referenceAlleleHomozygote;
		this.referenceAlleleHomozygoteFrequency = referenceAlleleHomozygoteFrequency;
		this.referenceAlleleHomozygoteCount = referenceAlleleHomozygoteCount;
		this.alleleHeterozygote = alleleHeterozygote;
		this.alleleHeterozygoteFrequency = alleleHeterozygoteFrequency;
		this.alleleHeterozygoteCount = alleleHeterozygoteCount;
		this.otherAlleleHomozygote = otherAlleleHomozygote;
		this.otherAlleleHeterozygoteFrequency = otherAlleleHeterozygoteFrequency;
		this.otherAlleleHeterozygoteCount = otherAlleleHeterozygoteCount;
		this.totalGenotypeCount = totalGenotypeCount;
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
	 * @param referenceAlleleHomozygote
	 * @param referenceAlleleHomozygoteFrequency
	 * @param referenceAlleleHomozygoteCount
	 * @param alleleHeterozygote
	 * @param alleleHeterozygoteFrequency
	 * @param alleleHeterozygoteCount
	 * @param otherAlleleHomozygote
	 * @param otherAlleleHeterozygoteFrequency
	 * @param otherAlleleHeterozygoteCount
	 * @param totalGenotypeCount
	 */
	public GenotypeFrequency(String id, String chromosome, Integer start, Integer end, String strand, String sequence, String allele, String population, String referenceAlleleHomozygote, Double referenceAlleleHomozygoteFrequency, Integer referenceAlleleHomozygoteCount, String alleleHeterozygote, Double alleleHeterozygoteFrequency, Integer alleleHeterozygoteCount, String otherAlleleHomozygote, Double otherAlleleHeterozygoteFrequency, Integer otherAlleleHeterozygoteCount, Integer totalGenotypeCount) {
		super(id, chromosome, start, end, strand, sequence, allele);
		this.population = population;
		this.referenceAlleleHomozygote = referenceAlleleHomozygote;
		this.referenceAlleleHomozygoteFrequency = referenceAlleleHomozygoteFrequency;
		this.referenceAlleleHomozygoteCount = referenceAlleleHomozygoteCount;
		this.alleleHeterozygote = alleleHeterozygote;
		this.alleleHeterozygoteFrequency = alleleHeterozygoteFrequency;
		this.alleleHeterozygoteCount = alleleHeterozygoteCount;
		this.otherAlleleHomozygote = otherAlleleHomozygote;
		this.otherAlleleHeterozygoteFrequency = otherAlleleHeterozygoteFrequency;
		this.otherAlleleHeterozygoteCount = otherAlleleHeterozygoteCount;
		this.totalGenotypeCount = totalGenotypeCount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(alleleHeterozygote);
		builder.append("\t");
		builder.append(alleleHeterozygoteCount);
		builder.append("\t");
		builder.append(alleleHeterozygoteFrequency);
		builder.append("\t");
		builder.append(otherAlleleHeterozygoteCount);
		builder.append("\t");
		builder.append(otherAlleleHeterozygoteFrequency);
		builder.append("\t");
		builder.append(otherAlleleHomozygote);
		builder.append("\t");
		builder.append(population);
		builder.append("\t");
		builder.append(referenceAlleleHomozygote);
		builder.append("\t");
		builder.append(referenceAlleleHomozygoteCount);
		builder.append("\t");
		builder.append(referenceAlleleHomozygoteFrequency);
		builder.append("\t");
		builder.append(totalGenotypeCount);
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
	 * @return the referenceAlleleHomozygote
	 */
	public String getReferenceAlleleHomozygote() {
		return referenceAlleleHomozygote;
	}

	/**
	 * @param referenceAlleleHomozygote the referenceAlleleHomozygote to set
	 */
	public void setReferenceAlleleHomozygote(String referenceAlleleHomozygote) {
		this.referenceAlleleHomozygote = referenceAlleleHomozygote;
	}

	/**
	 * @return the referenceAlleleHomozygoteFrequency
	 */
	public double getReferenceAlleleHomozygoteFrequency() {
		return referenceAlleleHomozygoteFrequency;
	}

	/**
	 * @param referenceAlleleHomozygoteFrequency the referenceAlleleHomozygoteFrequency to set
	 */
	public void setReferenceAlleleHomozygoteFrequency(
			double referenceAlleleHomozygoteFrequency) {
		this.referenceAlleleHomozygoteFrequency = referenceAlleleHomozygoteFrequency;
	}

	/**
	 * @return the referenceAlleleHomozygoteCount
	 */
	public int getReferenceAlleleHomozygoteCount() {
		return referenceAlleleHomozygoteCount;
	}

	/**
	 * @param referenceAlleleHomozygoteCount the referenceAlleleHomozygoteCount to set
	 */
	public void setReferenceAlleleHomozygoteCount(int referenceAlleleHomozygoteCount) {
		this.referenceAlleleHomozygoteCount = referenceAlleleHomozygoteCount;
	}

	/**
	 * @return the alleleHeterozygote
	 */
	public String getAlleleHeterozygote() {
		return alleleHeterozygote;
	}

	/**
	 * @param alleleHeterozygote the alleleHeterozygote to set
	 */
	public void setAlleleHeterozygote(String alleleHeterozygote) {
		this.alleleHeterozygote = alleleHeterozygote;
	}

	/**
	 * @return the alleleHeterozygoteFrequency
	 */
	public double getAlleleHeterozygoteFrequency() {
		return alleleHeterozygoteFrequency;
	}

	/**
	 * @param alleleHeterozygoteFrequency the alleleHeterozygoteFrequency to set
	 */
	public void setAlleleHeterozygoteFrequency(double alleleHeterozygoteFrequency) {
		this.alleleHeterozygoteFrequency = alleleHeterozygoteFrequency;
	}

	/**
	 * @return the alleleHeterozygoteCount
	 */
	public int getAlleleHeterozygoteCount() {
		return alleleHeterozygoteCount;
	}

	/**
	 * @param alleleHeterozygoteCount the alleleHeterozygoteCount to set
	 */
	public void setAlleleHeterozygoteCount(int alleleHeterozygoteCount) {
		this.alleleHeterozygoteCount = alleleHeterozygoteCount;
	}

	/**
	 * @return the otherAlleleHomozygote
	 */
	public String getOtherAlleleHomozygote() {
		return otherAlleleHomozygote;
	}

	/**
	 * @param otherAlleleHomozygote the otherAlleleHomozygote to set
	 */
	public void setOtherAlleleHomozygote(String otherAlleleHomozygote) {
		this.otherAlleleHomozygote = otherAlleleHomozygote;
	}

	/**
	 * @return the otherAlleleHeterozygoteFrequency
	 */
	public double getOtherAlleleHeterozygoteFrequency() {
		return otherAlleleHeterozygoteFrequency;
	}

	/**
	 * @param otherAlleleHeterozygoteFrequency the otherAlleleHeterozygoteFrequency to set
	 */
	public void setOtherAlleleHeterozygoteFrequency(
			double otherAlleleHeterozygoteFrequency) {
		this.otherAlleleHeterozygoteFrequency = otherAlleleHeterozygoteFrequency;
	}

	/**
	 * @return the otherAlleleHeterozygoteCount
	 */
	public int getOtherAlleleHeterozygoteCount() {
		return otherAlleleHeterozygoteCount;
	}

	/**
	 * @param otherAlleleHeterozygoteCount the otherAlleleHeterozygoteCount to set
	 */
	public void setOtherAlleleHeterozygoteCount(int otherAlleleHeterozygoteCount) {
		this.otherAlleleHeterozygoteCount = otherAlleleHeterozygoteCount;
	}

	/**
	 * @return the totalGenotypeCount
	 */
	public int getTotalGenotypeCount() {
		return totalGenotypeCount;
	}

	/**
	 * @param totalGenotypeCount the totalGenotypeCount to set
	 */
	public void setTotalGenotypeCount(int totalGenotypeCount) {
		this.totalGenotypeCount = totalGenotypeCount;
	}
	
}

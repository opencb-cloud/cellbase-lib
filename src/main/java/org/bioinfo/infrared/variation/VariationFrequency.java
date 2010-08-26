package org.bioinfo.infrared.variation;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.common.feature.VariationFeature;

public class VariationFrequency extends VariationFeature {

	private String referenceAllele;
	private String otherAllele;
	private String alleleFrequencies;
	private String referenceAlleleHomozygote;
	private String alleleHeterozygote;
	private String otherAlleleHomozygote;
	private String genotypeFrequencies;

	/**
	 * @param id
	 * @param chromosome
	 * @param start
	 * @param end
	 * @param strand
	 * @param referenceAllele
	 * @param otherAllele
	 * @param alleleFrequencies
	 * @param referenceAlleleHomozygote
	 * @param alleleHeterozygote
	 * @param otherAlleleHomozygote
	 * @param genotypeFrequencies
	 */
	public VariationFrequency(String id, String chromosome, Integer start, Integer end, String strand, String referenceAllele, String otherAllele, String alleleFrequencies, String referenceAlleleHomozygote, String alleleHeterozygote, String otherAlleleHomozygote, String genotypeFrequencies) {
		super(id, chromosome, start, end, strand);
		this.referenceAllele = referenceAllele;
		this.otherAllele = otherAllele;
		this.alleleFrequencies = alleleFrequencies;
		this.referenceAlleleHomozygote = referenceAlleleHomozygote;
		this.alleleHeterozygote = alleleHeterozygote;
		this.otherAlleleHomozygote = otherAlleleHomozygote;
		this.genotypeFrequencies = genotypeFrequencies;
	}

	
	public String filterAlleleFrequencies(List<String> populations) {
		StringBuilder alleleFrequenciesStringBuilder = new StringBuilder();
		String[] alleleFrequenciesArray = alleleFrequencies.split(",");
		String[] fields;
		for(String alleleFreq: alleleFrequenciesArray) {
			fields = alleleFreq.split("[:|]");
			// now we filter by population
			if(fields.length == 3 && populations.contains(fields[0])) {
				alleleFrequenciesStringBuilder.append(alleleFreq).append(",");
			}
		}
		if(alleleFrequenciesStringBuilder.lastIndexOf(",") != -1) {
			alleleFrequenciesStringBuilder.deleteCharAt(alleleFrequenciesStringBuilder.lastIndexOf(","));
		}
		return alleleFrequenciesStringBuilder.toString();
	}

	public List<PopulationAlleleFrequency> getAlleleFrequenciesAsList() {
		List<PopulationAlleleFrequency> alleleFrequenciesList = new ArrayList<VariationFrequency.PopulationAlleleFrequency>();
		String[] alleleFrequenciesArray = alleleFrequencies.split(",");
		String[] fields;
		for(String alleleFreq: alleleFrequenciesArray) {
			fields = alleleFreq.split("[:|]");
			if(fields.length == 3) {
				alleleFrequenciesList.add(new PopulationAlleleFrequency(fields[0], Float.parseFloat(fields[1]), Float.parseFloat(fields[2])));
			}
		}
		return alleleFrequenciesList;
	}
	
	public List<PopulationAlleleFrequency> getAlleleFrequenciesAsList(List<String> populations) {
		List<PopulationAlleleFrequency> alleleFrequenciesList = new ArrayList<VariationFrequency.PopulationAlleleFrequency>();
		String[] alleleFrequenciesArray = alleleFrequencies.split(",");
		String[] fields;
		for(String alleleFreq: alleleFrequenciesArray) {
			fields = alleleFreq.split("[:|]");
			// now we filter by population
			if(fields.length == 3 && populations.contains(fields[0])) {
				alleleFrequenciesList.add(new PopulationAlleleFrequency(fields[0], Float.parseFloat(fields[1]), Float.parseFloat(fields[2])));
			}
		}
		return alleleFrequenciesList;
	}


	public String filterGenotypeFrequencies(List<String> populations) {
		StringBuilder genotypeFrequenciesStringBuilder = new StringBuilder();
		String[] genotypeFrequenciesArray = genotypeFrequencies.split(",");
		String[] fields;
		for(String genotypeFreq: genotypeFrequenciesArray) {
			fields = genotypeFreq.split("[:|]");
			// now we filter by population
			if(fields.length == 4  && populations.contains(fields[0])) {
				genotypeFrequenciesStringBuilder.append(genotypeFreq).append(",");
			}
		}
		if(genotypeFrequenciesStringBuilder.lastIndexOf(",") != -1) {
			genotypeFrequenciesStringBuilder.deleteCharAt(genotypeFrequenciesStringBuilder.lastIndexOf(","));
		}
		return genotypeFrequenciesStringBuilder.toString();
	}
	
	public List<PopulationGenotypeFrequency> getGenotypeFrequenciesAsList() {
		List<PopulationGenotypeFrequency> genotypeFrequenciesList = new ArrayList<VariationFrequency.PopulationGenotypeFrequency>();
		String[] genotypeFrequenciesArray = genotypeFrequencies.split(",");
		String[] fields;
		for(String alleleFreq: genotypeFrequenciesArray ) {
			fields = alleleFreq.split("[:|]");
			if(fields.length == 4) {
				genotypeFrequenciesList.add(new PopulationGenotypeFrequency(fields[0], Float.parseFloat(fields[1]), Float.parseFloat(fields[2]), Float.parseFloat(fields[3])));
			}
		}
		return genotypeFrequenciesList;
	}
	
	public List<PopulationGenotypeFrequency> getGenotypeFrequenciesAsList(List<String> populations) {
		List<PopulationGenotypeFrequency> genotypeFrequenciesList = new ArrayList<VariationFrequency.PopulationGenotypeFrequency>();
		String[] genotypeFrequenciesArray = genotypeFrequencies.split(",");
		String[] fields;
		for(String alleleFreq: genotypeFrequenciesArray ) {
			fields = alleleFreq.split("[:|]");
			if(fields.length == 4 && populations.contains(fields[0])) {
				genotypeFrequenciesList.add(new PopulationGenotypeFrequency(fields[0], Float.parseFloat(fields[1]), Float.parseFloat(fields[2]), Float.parseFloat(fields[3])));
			}
		}
		return genotypeFrequenciesList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id).append("\t");
		sb.append(chromosome).append("\t");
		sb.append(start).append("\t");
		sb.append(end).append("\t");
		sb.append(strand).append("\t");
		sb.append(referenceAllele).append("\t");
		sb.append(otherAllele).append("\t");
		sb.append(alleleFrequencies).append("\t");
		sb.append(referenceAlleleHomozygote).append("\t");
		sb.append(alleleHeterozygote).append("\t");
		sb.append(otherAlleleHomozygote).append("\t");
		sb.append(genotypeFrequencies);
		return sb.toString();
	}

	
	class PopulationAlleleFrequency {
		private String population;
		private float referenceAlleleFrequency;
		private float otherAlleleFrequency;

		public PopulationAlleleFrequency(String population, float referenceAlleleFrequency, float otherAlleleFrequency) {
			this.population = population;
			this.referenceAlleleFrequency = referenceAlleleFrequency;
			this.otherAlleleFrequency = otherAlleleFrequency;
		}
		
		@Override
		public String toString() {
			return population+"\t"+referenceAllele+":"+referenceAlleleFrequency+"\t"+otherAllele+":"+otherAlleleFrequency;
		}

		/**
		 * @param population the population to set
		 */
		public void setPopulation(String population) {
			this.population = population;
		}

		/**
		 * @return the population
		 */
		public String getPopulation() {
			return population;
		}

		/**
		 * @param referenceAlleleFrequency the referenceAlleleFrequency to set
		 */
		public void setReferenceAlleleFrequency(float referenceAlleleFrequency) {
			this.referenceAlleleFrequency = referenceAlleleFrequency;
		}

		/**
		 * @return the referenceAlleleFrequency
		 */
		public float getReferenceAlleleFrequency() {
			return referenceAlleleFrequency;
		}

		/**
		 * @param otherAlleleFrequency the otherAlleleFrequency to set
		 */
		public void setOtherAlleleFrequency(float otherAlleleFrequency) {
			this.otherAlleleFrequency = otherAlleleFrequency;
		}

		/**
		 * @return the otherAlleleFrequency
		 */
		public float getOtherAlleleFrequency() {
			return otherAlleleFrequency;
		}
	}

	class PopulationGenotypeFrequency {
		private String population;
		private float referenceAlleleHomozygoteFrequency;
		private float alleleHeterozygoteFrequency;
		private float otherAlleleHomozygoteFrequency;

		public PopulationGenotypeFrequency(String population, float referenceAlleleHomozygoteFrequency, float alleleHeterozygoteFrequency, float otherAlleleHomozygoteFrequency) {
			this.population = population;
			this.referenceAlleleHomozygoteFrequency = referenceAlleleHomozygoteFrequency;
			this.alleleHeterozygoteFrequency = alleleHeterozygoteFrequency;
			this.otherAlleleHomozygoteFrequency = otherAlleleHomozygoteFrequency;
		}
		
		@Override
		public String toString() {
			return population+"\t"+referenceAlleleHomozygote+":"+referenceAlleleHomozygoteFrequency+"\t"+alleleHeterozygote+":"+alleleHeterozygoteFrequency+"\t"+otherAlleleHomozygote+":"+otherAlleleHomozygoteFrequency;
		}

		/**
		 * @param population the population to set
		 */
		public void setPopulation(String population) {
			this.population = population;
		}

		/**
		 * @return the population
		 */
		public String getPopulation() {
			return population;
		}

		/**
		 * @param referenceAlleleHomozygoteFrequency the referenceAlleleHomozygoteFrequency to set
		 */
		public void setReferenceAlleleHomozygoteFrequency(
				float referenceAlleleHomozygoteFrequency) {
			this.referenceAlleleHomozygoteFrequency = referenceAlleleHomozygoteFrequency;
		}

		/**
		 * @return the referenceAlleleHomozygoteFrequency
		 */
		public float getReferenceAlleleHomozygoteFrequency() {
			return referenceAlleleHomozygoteFrequency;
		}

		/**
		 * @param alleleHeterozygoteFrequency the alleleHeterozygoteFrequency to set
		 */
		public void setAlleleHeterozygoteFrequency(
				float alleleHeterozygoteFrequency) {
			this.alleleHeterozygoteFrequency = alleleHeterozygoteFrequency;
		}

		/**
		 * @return the alleleHeterozygoteFrequency
		 */
		public float getAlleleHeterozygoteFrequency() {
			return alleleHeterozygoteFrequency;
		}

		/**
		 * @param otherAlleleHomozygoteFrequency the otherAlleleHomozygoteFrequency to set
		 */
		public void setOtherAlleleHomozygoteFrequency(
				float otherAlleleHomozygoteFrequency) {
			this.otherAlleleHomozygoteFrequency = otherAlleleHomozygoteFrequency;
		}

		/**
		 * @return the otherAlleleHomozygoteFrequency
		 */
		public float getOtherAlleleHomozygoteFrequency() {
			return otherAlleleHomozygoteFrequency;
		}
	}
}
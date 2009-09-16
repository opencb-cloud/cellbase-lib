package org.bioinfo.infrared.variation;

import java.util.List;

import org.bioinfo.commons.utils.StringUtils;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.common.feature.VariationFeature;
import org.bioinfo.infrared.core.Transcript;

public class SNP extends VariationFeature{

	private String allele;
	private int weight;
	private List<String> consequence_type;
	private String sequence;
	
	public SNP(String snpId) {
		super(snpId);
	}
	
	public SNP(String snpId, String chromosome, Integer start, Integer end, String strand, String allele) {
		super(snpId, chromosome, start, end, strand);
		this.allele = allele;
	}
	
	public SNP(String snpId, String chromosome, Integer start, Integer end, String strand, String allele, String consequence_type) {
		super(snpId, chromosome, start, end, strand);
		this.allele = allele;
		this.consequence_type = StringUtils.stringToList(consequence_type);
	}
	
	public SNP(String snpId, String chromosome, Integer start, Integer end, String strand, String allele, Integer weight, String consequence_type, String sequence) {
		this(snpId, chromosome, start, end, strand, allele);
		this.weight = weight;
		this.consequence_type = StringUtils.stringToList(consequence_type);
		this.sequence = sequence;
	}
	
	public SNP(Integer id, String snpId, String chromosome, Integer start, Integer end, String strand, String allele, Integer weight, String consequence_type, String sequence) {
		this(snpId, chromosome, start, end, strand, allele,weight,consequence_type,sequence);
	}
	
	public FeatureList<Transcript> getTranscripts() {
		return null;
	}

	@Override
	public String getLocation() {
		return chromosome+":"+start+"("+strand+")";
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id).append("\t");
		sb.append(chromosome).append("\t");
		sb.append(start).append("\t");
		sb.append(end).append("\t");
		sb.append(strand).append("\t");
		sb.append(allele).append("\t");
		sb.append(weight).append("\t");
		sb.append(StringUtils.arrayToString(consequence_type, ",")).append("\t");
		sb.append(sequence).append("\t");
		return sb.toString();
	}
	
	/**
	 * @return the allele
	 */
	public String getAllele() {
		return allele;
	}

	/**
	 * @param allele the allele to set
	 */
	public void setAllele(String allele) {
		this.allele = allele;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return the sequence
	 */
	public String getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	/**
	 * @return the consequence_type
	 */
	public List<String> getConsequence_type() {
		return consequence_type;
	}

	/**
	 * @param consequence_type the consequence_type to set
	 */
	public void setConsequence_type(List<String> consequence_type) {
		this.consequence_type = consequence_type;
	}
	
}

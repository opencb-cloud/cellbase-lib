package org.bioinfo.infrared.variation;

import java.util.List;

import org.bioinfo.commons.utils.StringUtils;
import org.bioinfo.infrared.common.feature.VariationFeature;

public class AnnotatedSnps extends VariationFeature {
	
	private List<String> consequence_type;
	private String source;
	private String geneId;
	private String geneName;
	private String link;
	private String description;
	
	/**
	 * @param source
	 * @param geneId
	 * @param geneName
	 * @param link
	 * @param description
	 */
	public AnnotatedSnps(String snpId, String source, String geneId, String geneName, String link, String description) {
		super(snpId);
		this.source = source;
		this.geneId = geneId;
		this.geneName = geneName;
		this.link = link;
		this.description = description;
	}
	
	public AnnotatedSnps(String snpId, String chromosome, Integer start, Integer end, String strand, String sequence, String allele,  String consequence_type, String source, String geneId, String geneName, String link, String description) {
		super(snpId, chromosome, start, end, strand, sequence, allele);
		this.consequence_type = StringUtils.toList(consequence_type);
		this.source = source;
		this.geneId = geneId;
		this.geneName = geneName;
		this.link = link;
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id).append("\t");
		builder.append(chromosome).append("\t");
		builder.append(start).append("\t");
		builder.append(end).append("\t");
		builder.append(strand).append("\t");
		builder.append(allele).append("\t");
		builder.append(description).append("\t");
		builder.append(geneId).append("\t");
		builder.append(geneName).append("\t");
		builder.append(link).append("\t");
		builder.append(source).append("\t");
		builder.append(sequence);
		return builder.toString();
	}

	
	/**
	 * @param consequence_type the consequence_type to set
	 */
	public void setConsequence_type(List<String> consequence_type) {
		this.consequence_type = consequence_type;
	}

	/**
	 * @return the consequence_type
	 */
	public List<String> getConsequence_type() {
		return consequence_type;
	}
	
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
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
	 * @return the geneName
	 */
	public String getGeneName() {
		return geneName;
	}

	/**
	 * @param geneName the geneName to set
	 */
	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}

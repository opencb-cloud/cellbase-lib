package org.bioinfo.infrared.lib.common;

public class GenomicVariantConsequenceType {

	private String chromosome;
	private int start;
	private int end;
	private String id;
	private String name;
	private String type;
	private String biotype;
	private String featureChromosome;
	private int featureStart;
	private int featureEnd;
	private String featureStrand;

	private String snpId;
	private String ancestral;
	private String alternative;

	private String geneId;
	private String transcriptId;
	private String geneName;

	public String consequenceType;
	private String consequenceTypeObo;
	private String consequenceTypeDesc;
	private String consequenceTypeType;

	private String aminoacidChange;
	private String codonChange;


	public GenomicVariantConsequenceType(String chromosome, int start, int end,
			String id, String name, String type, String biotype,
			String featureChromosome, int featureStart,
			int featureEnd, String featureStrand, String snpId,
			String ancestral, String alternative, String geneId,
			String transcriptId, String geneName, String consequenceType,
			String consequenceTypeObo, String consequenceTypeDesc,
			String consequenceTypeType, String aminoacidChange,
			String codonChange) {
		super();
		this.chromosome = chromosome;
		this.start = start;
		this.end = end;
		this.id = id;
		this.name = name;
		this.type = type;
		this.biotype = biotype;
		this.featureChromosome = featureChromosome;
		this.featureStart = featureStart;
		this.featureEnd = featureEnd;
		this.featureStrand = featureStrand;
		this.snpId = snpId;
		this.ancestral = ancestral;
		this.alternative = alternative;
		this.geneId = geneId;
		this.transcriptId = transcriptId;
		this.geneName = geneName;
		this.consequenceType = consequenceType;
		this.consequenceTypeObo = consequenceTypeObo;
		this.consequenceTypeDesc = consequenceTypeDesc;
		this.consequenceTypeType = consequenceTypeType;
		this.aminoacidChange = aminoacidChange;
		this.codonChange = codonChange;
	}


	public String toString(){
		StringBuilder br = new StringBuilder();
		return br.append(chromosome).append("\t")
				.append(start).append("\t")
				.append(end).append("\t")
				.append(id).append("\t")
				.append(name).append("\t")
				.append(type).append("\t")
				.append(biotype).append("\t")
				.append(featureChromosome).append("\t")
				.append(featureStart).append("\t")
				.append(featureEnd).append("\t")
				.append(featureStrand).append("\t")
				.append(snpId).append("\t")
				.append(ancestral).append("\t")
				.append(alternative).append("\t")
				.append(geneId).append("\t")
				.append(transcriptId).append("\t")
				.append(geneName).append("\t")
				.append(consequenceType).append("\t")
				.append(consequenceTypeObo).append("\t")
				.append(consequenceTypeDesc).append("\t")
				.append(consequenceTypeType).append("\t")
				.append(aminoacidChange).append("\t")
				.append(codonChange).append("\t").toString();
	}
}
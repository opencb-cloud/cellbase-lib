package org.bioinfo.infrared.variation;

@Deprecated
public class SpliceSiteVariation extends SNP{

	private String ensemblTranscriptId;
	private String ensemblGeneId;
	private String consequenceType;
	private String spliceSite;
	private String allele1;
	private double score1;
	private String sequence1;
	private String allele2;
	private double score2;
	private String sequence2;
	
	public SpliceSiteVariation(String snpId, String ensemblTranscriptId, String ensemblGeneId, String consequenceType, String spliceSite, String allele1, Double score1, String sequence1, String allele2, Double score2, String sequence2) {
		super(snpId);
		this.ensemblTranscriptId = ensemblTranscriptId;
		this.ensemblGeneId = ensemblGeneId;
		this.consequenceType = consequenceType;
		this.spliceSite = spliceSite;
		this.allele1 = allele1;
		this.score1 = score1;
		this.sequence1 = sequence1;
		this.allele2 = allele2;
		this.score2 = score2;
		this.sequence2 = sequence2;
	}

	@Override
	public String toString() {
		return id+"\t"+ensemblTranscriptId+"\t"+ensemblGeneId+"\t"+consequenceType+"\t"+spliceSite+"\t"+allele1+"\t"+score1+"\t"+sequence1+"\t"+allele2+"\t"+score2+"\t"+sequence2;
	}
}

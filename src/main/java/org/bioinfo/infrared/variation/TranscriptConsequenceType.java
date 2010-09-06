package org.bioinfo.infrared.variation;

import java.util.ArrayList;
import java.util.List;

public class TranscriptConsequenceType {
	private String consequenceType;
	private String ensemblTranscript;
	private String ensemblGene;

	public TranscriptConsequenceType(String consequenceType) {
		this(consequenceType, "", "");
	}

	public TranscriptConsequenceType(String consequenceType, String ensemblTranscript, String ensemblGene) {
		this.consequenceType = consequenceType;
		this.ensemblTranscript= ensemblTranscript;
		this.ensemblGene = ensemblGene;
	}

	public static TranscriptConsequenceType parseTranscriptConsequenceType(String transcriptConsequenceType) {
		String[] fields = transcriptConsequenceType.split(":", -1);
		if(fields.length == 1) {
			return new TranscriptConsequenceType(fields[0]);
		}else {
			if(fields.length == 3) {
				return new TranscriptConsequenceType(fields[0], fields[1], fields[2]);
			}else {
				return null;
			}
		}
	}

	public static List<TranscriptConsequenceType> parseTranscriptConsequenceTypeList(String transcriptConsequenceType) {
		List<TranscriptConsequenceType> consequences  = new ArrayList<TranscriptConsequenceType>(4);
		for(String cons: transcriptConsequenceType.split(",")) {
			consequences.add(parseTranscriptConsequenceType(cons));
		}
		return consequences;
	}

	@Override
	public String toString() {
		return consequenceType + ":" + ensemblTranscript + ":" + ensemblGene +"";
	}

	/**
	 * @return the transcriptConsequenceTypes
	 */
	public String getConsequenceType() {
		return consequenceType;
	}

	/**
	 * @param transcriptConsequenceTypes the transcriptConsequenceTypes to set
	 */
	public void setConsequenceType(String consequenceType) {
		this.consequenceType = consequenceType;
	}

	/**
	 * @return the ensemblTranscript
	 */
	public String getEnsemblTranscript() {
		return ensemblTranscript;
	}

	/**
	 * @param ensemblTranscript the ensemblTranscript to set
	 */
	public void setEnsemblTranscript(String ensemblTranscript) {
		this.ensemblTranscript = ensemblTranscript;
	}

	/**
	 * @return the ensemblGene
	 */
	public String getEnsemblGene() {
		return ensemblGene;
	}

	/**
	 * @param ensemblGene the ensemblGene to set
	 */
	public void setEnsemblGene(String ensemblGene) {
		this.ensemblGene = ensemblGene;
	}
}

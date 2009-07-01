package org.bioinfo.infrared.core;

import org.bioinfo.infrared.common.feature.CodingFeature;

public class Exon extends CodingFeature{

	public Exon(String exonId, String chromosome, Integer start, Integer end, String strand) {
		super(exonId, chromosome, start, end, strand);
	}
	
	public Exon(String exonId, String chromosome, Integer start, Integer end, String strand, String sequence) {
		super(exonId, chromosome, start, end, strand, sequence);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getId()).append("\t").append(super.toString());
		return sb.toString();
	}
	
	
	@Override
	public String getSequence() {
		// TODO Auto-generated method stub
		return null;
	}

}

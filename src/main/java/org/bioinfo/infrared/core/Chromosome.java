package org.bioinfo.infrared.core;

import org.bioinfo.infrared.common.feature.GenomicFeature;

public class Chromosome extends GenomicFeature {

	public Chromosome(String chromosome, Integer start, Integer end) {
		super("",chromosome, start, end);
	}

	@Override
	public String getSequence() {
		// TODO Auto-generated method stub
		return null;
	}

	
}

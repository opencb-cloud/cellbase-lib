package org.bioinfo.infrared.core;

import org.bioinfo.infrared.common.feature.GenomicFeature;

public class Region extends GenomicFeature {

	public Region(String chromosome, int start, int end, String strand) {
		super("",chromosome, start, end, strand);
	}

	@Override
	public String getSequence() {
		// TODO Auto-generated method stub
		return null;
	}

	
}

package org.bioinfo.infrared.regulatory;

import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.common.DBManager;
import org.bioinfo.infrared.core.common.GenomicFeature;
import org.bioinfo.infrared.core.feature.Position;
import org.bioinfo.infrared.core.variation.SNP;

public class RegulatoryDBManager extends DBManager {
	
	public Map<String, List<GenomicFeature>> getAllBySnps(List<SNP> snps) {
		return null;
	}
	
	public Map<String, List<GenomicFeature>> getAllByPositions(List<Position> positions) {
		return null;
	}
}

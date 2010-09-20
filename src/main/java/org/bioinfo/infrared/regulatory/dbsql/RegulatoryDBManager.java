package org.bioinfo.infrared.regulatory.dbsql;

import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.GenomicFeature;
import org.bioinfo.infrared.core.Position;
import org.bioinfo.infrared.variation.SNP;

public class RegulatoryDBManager extends DBManager {
	
	public Map<String, List<GenomicFeature>> getAllBySnps(List<SNP> snps) {
		return null;
	}
	
	public Map<String, List<GenomicFeature>> getAllByPositions(List<Position> positions) {
		return null;
	}
}

package org.bioinfo.infrared.variation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.common.DBManager;
import org.bioinfo.infrared.core.common.FeatureList;

public class FunctionalSnpDBManager extends DBManager {

	public FunctionalSnpDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	public Map<String, List<FeatureList>> getAllByFunctional(String source) {
		Map<String, List<FeatureList>> functionalSnpMap = new HashMap<String, List<FeatureList>>();
		return null;
	}
}

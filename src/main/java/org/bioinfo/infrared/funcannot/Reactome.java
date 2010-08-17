package org.bioinfo.infrared.funcannot;

import org.bioinfo.infrared.common.feature.FunctionalFeature;

public class Reactome extends FunctionalFeature {


	public Reactome(String reactomeId) {
		super(reactomeId,"");
	}
	
	public Reactome(String reactomeId, String name) {
		super(reactomeId,name);
	}
}

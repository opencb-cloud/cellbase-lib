package org.bioinfo.infrared.lib.api;

import org.bioinfo.infrared.lib.common.Region;


public interface FeatureDBAdaptor {

	public String getSequence();
	
	public String getSequence(String strand);
	
	public Region getRegion(String id);
	
}

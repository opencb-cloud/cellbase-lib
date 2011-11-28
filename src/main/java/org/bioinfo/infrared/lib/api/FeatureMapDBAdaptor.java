package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.FeatureMap;
import org.bioinfo.infrared.core.cellbase.Transcript;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;


// Como no es una fetaure no deberia implementas FeatureDBAdaptor no?
//public interface FeatureMapDBAdaptor extends FeatureDBAdaptor {	

public interface FeatureMapDBAdaptor {
	
	public List<FeatureMap> getAllByRegion(String chromosome, int start, int end);
	
	
}

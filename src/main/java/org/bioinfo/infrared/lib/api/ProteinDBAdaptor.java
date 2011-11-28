package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.Protein;

public interface ProteinDBAdaptor extends FeatureDBAdaptor {

	@Override
	public List<Protein> getAll();

	
}

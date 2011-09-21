package org.bioinfo.infrared.lib.io.output;

import java.util.List;

import org.bioinfo.infrared.core.Gene;

public abstract class ObjectSerialization {

	public abstract String serialize(Gene gene);
	
	public abstract String serialize(List<Gene> gene);
	
}

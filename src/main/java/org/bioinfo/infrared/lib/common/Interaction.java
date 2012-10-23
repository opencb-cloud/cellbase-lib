package org.bioinfo.infrared.lib.common;

import java.util.List;
import java.util.Map;

public class Interaction {
	String name, type;
	Map<String, List<String>> params;
	
	public Interaction(String id, String type, Map<String, List<String>> params) {
		this.name = id;
		this.type = type;
		this.params = params;
	}
}

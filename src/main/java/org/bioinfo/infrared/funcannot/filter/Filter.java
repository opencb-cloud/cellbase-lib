package org.bioinfo.infrared.funcannot.filter;

import java.util.HashMap;
import java.util.Iterator;

@SuppressWarnings("serial")
public class Filter extends HashMap<String,String>{

	protected String getDefaultValue(String value, String def) {
		if(value == null) {
			return def;
		}else {
			if(value.equals("")) {
				return def;
			}
		}
		return value;
	}
	
	public boolean equals(String s1, String s2) {
		if(s1 != null) {
			return s1.equalsIgnoreCase(s2);
		}else {
			return s1 == s2;
		}
	}
	
	public boolean minor(String s1, String s2) {
		if (s1 != null && s2 != null) {
			int i1 = Integer.parseInt(s1);
			int i2 = Integer.parseInt(s2);
			return i1 < i2;
		}
		return false;
	}
	
	public boolean major(String s1, String s2) {
		if (s1 != null && s2 != null) {
			int i1 = Integer.parseInt(s1);
			int i2 = Integer.parseInt(s2);
			return i1 > i2;
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String key;
		Iterator<String> iter = this.keySet().iterator();
		while(iter.hasNext()) {
			key = iter.next();
			sb.append(key).append("::").append(this.get(key)).append("\t");
		}
		return sb.toString();
	}
}

package org.bioinfo.cellbase.lib.impl.result;

import java.util.LinkedHashMap;
import java.util.Map;

public class QueryResult extends LinkedHashMap<String, Object> {


	private static final long serialVersionUID = 4427749106006400267L;

	
	public QueryResult() {

	}

	public QueryResult(int size) {
		super(size); 
	}

	public QueryResult(String key, Object value) {
		put(key, value); 
	}

	public QueryResult(Map<String, Object> map) {
		super(map);
	}
	
	public static QueryResult build() {
		return new QueryResult();
	}
	
	public boolean containsField(String key) {
		return this.containsKey(key);
	}
	
	public Object removeField(String key) {
		return this.remove(key);
	}
	
	public Object get(String key ){
        return this.get(key);
    }
	
	
	
	public String getString(String field) {
		return getString(field, "");
	}

	public String getString(String field, String defaultValue) {
		if(field != null && this.containsKey(field)) {
			return (String)this.get(field);
		}
		return defaultValue;
	}


	public int getInt(String field) {
		return getInt(field, 0);
	}

	public int getInt(String field, int defaultValue) {
		if(field != null && this.containsKey(field)) {
			Object obj = this.get(field);
			if(obj instanceof Integer) {
				return (Integer)obj;
			}else{
				return Integer.parseInt(String.valueOf(obj));								
			}
		}
		return defaultValue;
	}


	public float getFloat(String field) {
		return getFloat(field, 0.0f);
	}

	public float getFloat(String field, float defaultValue) {
		if(field != null && this.containsKey(field)) {
			return Float.parseFloat((String)this.get(field));				
		}
		return defaultValue;
	}


	public double getDouble(String field) {
		return getDouble(field, 0.0);
	}

	public double getDouble(String field, double defaultValue) {
		if(field != null && this.containsKey(field)) {
			return Double.parseDouble((String)this.get(field));
		}
		return defaultValue;
	}

	
	public boolean getBoolean(String field) {
		return getBoolean(field, false);
	}

	public boolean getBoolean(String field, boolean defaultValue) {
		if(field != null && this.containsKey(field)) {
			return Boolean.parseBoolean((String)this.get(field));				
		}
		return defaultValue;
	}


}

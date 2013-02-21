
package org.bioinfo.cellbase.lib.impl.hibernate;
import java.util.HashSet;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class SNPFeatureExclusionStrategy implements ExclusionStrategy {

	
	private Class<?> typeToExclude;

	public SNPFeatureExclusionStrategy(Class<?> typeToExclude){
		this.typeToExclude = typeToExclude;
		
	}
	
	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		//System.out.println("------------------------------>" + arg0.getCanonicalName());
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes arg0) {
		//System.out.println("------------------------------>" + arg0.getName() + "\t :: " + arg0.getDeclaredType().toString() );
		//if (arg0.getDeclaredType().toString().contains("infrared")){
//	System.out.println("------------------------------>" + arg0.getName() + " " + arg0.getDeclaredType().toString() + " --  " + this.typeToExclude.getName());
		
		if (arg0.getDeclaredType().toString().contains("infrared")){
			return true;
		}
		return false;
	}

}

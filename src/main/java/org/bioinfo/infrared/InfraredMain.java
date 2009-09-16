package org.bioinfo.infrared;

import org.bioinfo.infrared.tools.InfraredFactory;
import org.bioinfo.infrared.tools.InfraredTool;

public class InfraredMain {

	public static void main(String[] args) {
		
		if(args.length == 0) {
			System.out.println("No options provided");
		}
		
		InfraredTool tool = InfraredFactory.createTool(args);
		if(tool != null) {
			tool.run();
		}
		
	}
	
}


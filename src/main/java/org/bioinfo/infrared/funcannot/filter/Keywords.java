package org.bioinfo.infrared.funcannot.filter;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Keywords extends ArrayList<Keyword>{

//	private ArrayList<Keyword> keywords;
	
	public Keywords() {
		super();
	}
	
	public Keywords(int num) {
		super(num);
	}
	
	public boolean pass() {
		
		return true;
	}
	
	public String getClause(String sqlField, String logicalOperator) {
		StringBuffer sb = new StringBuffer();
		if(this.size() != 0) {
			for(int i=0;i < this.size() -1 ; i++) {
				sb.append(sqlField).append(this.get(i).toString()).append(" ").append(logicalOperator).append(" ");
			}
			sb.append(sqlField).append(this.get(this.size()-1).toString());
		}
		return sb.toString();
	}

}

package org.bioinfo.infrared.funcannot.filter;

public class GOSlimFilter extends FunctionalFilter {

	private static final long serialVersionUID = 1L;

	public GOSlimFilter() {
		this(5, 2000);
	}
	
	public GOSlimFilter(int minNumberGenes, int maxNumberGenes) {
		super(minNumberGenes, maxNumberGenes);
	}
	
	@Override
	public String getSQLWhereClause(String prefixSqlField) {
		return "";
	}

}

package org.bioinfo.infrared.funcannot.filter;

public class InterproFilter extends FunctionalFilter {
	
	private static final long serialVersionUID = 1L;

	public InterproFilter() {
		this(2, 500);
	}
	
	public InterproFilter(int minNumberGenes, int maxNumberGenes) {
		super(minNumberGenes, maxNumberGenes);
	}
	
	@Override
	public String getSQLWhereClause(String prefixSqlField) {
		return "";
	}

}

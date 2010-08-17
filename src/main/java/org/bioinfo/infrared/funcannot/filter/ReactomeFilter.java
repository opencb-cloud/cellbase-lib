package org.bioinfo.infrared.funcannot.filter;

public class ReactomeFilter extends FunctionalFilter {
	
	private static final long serialVersionUID = 1L;

	public ReactomeFilter() {
		this(2, 500);
	}
	
	public ReactomeFilter(int minNumberGenes, int maxNumberGenes) {
		super(minNumberGenes, maxNumberGenes);
	}
	
	@Override
	public String getSQLWhereClause(String prefixSqlField) {
		return "";
	}
}

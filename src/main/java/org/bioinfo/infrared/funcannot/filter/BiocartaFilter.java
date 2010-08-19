package org.bioinfo.infrared.funcannot.filter;

public class BiocartaFilter extends FunctionalFilter{

	private static final long serialVersionUID = 1L;

	public BiocartaFilter() {
		this(2, 500);
	}
	
	public BiocartaFilter(int minNumberGenes, int maxNumberGenes) {
		super(minNumberGenes, maxNumberGenes);
	}
	
	@Override
	public String getSQLWhereClause(String prefixSqlField) {
		return "";
	}
}

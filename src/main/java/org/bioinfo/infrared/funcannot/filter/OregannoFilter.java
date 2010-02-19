package org.bioinfo.infrared.funcannot.filter;

public class OregannoFilter extends FunctionalFilter {
	
	private static final long serialVersionUID = 1L;

	public OregannoFilter() {
		this(2, 500);
	}
	
	public OregannoFilter(int minNumberGenes, int maxNumberGenes) {
		super(minNumberGenes, maxNumberGenes);
	}
	
	@Override
	public String getSQLWhereClause(String prefixSqlField) {
		return "";
	}
}

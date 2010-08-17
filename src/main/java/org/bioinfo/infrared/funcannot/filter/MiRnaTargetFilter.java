package org.bioinfo.infrared.funcannot.filter;

public class MiRnaTargetFilter extends FunctionalFilter{
	
	private static final long serialVersionUID = 1L;

	public MiRnaTargetFilter() {
		this(5, 500);
	}
	
	public MiRnaTargetFilter(int minNumberGenes, int maxNumberGenes) {
		super(minNumberGenes, maxNumberGenes);
	}
	
	@Override
	public String getSQLWhereClause(String prefixSqlField) {
		return "";
	}
}

package org.bioinfo.infrared.funcannot.filter;

public class JasparFilter extends FunctionalFilter{

	private static final long serialVersionUID = 1L;

	public JasparFilter() {
		this(2, 500);
	}
	
	public JasparFilter(int minNumberGenes, int maxNumberGenes) {
		super(minNumberGenes, maxNumberGenes);
	}
	
	@Override
	public String getSQLWhereClause(String prefixSqlField) {
		return "";
	}
}

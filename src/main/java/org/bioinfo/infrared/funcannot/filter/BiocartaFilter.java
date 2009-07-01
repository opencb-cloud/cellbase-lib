package org.bioinfo.infrared.funcannot.filter;

@SuppressWarnings("serial")
public class BiocartaFilter extends FunctionalFilter{

	private Keywords keywords;
	private String logicalOperator;
	
	public BiocartaFilter() {
		this(0,Integer.MAX_VALUE);
	}
	public BiocartaFilter(int minNumberGenes, int maxNumberGenes) {
		this.minNumberGenes = minNumberGenes;
		this.maxNumberGenes = maxNumberGenes;
		keywords = new Keywords(2);
		this.useNumberOfGenes = true;
	}
	
	@Override
	public String getWhereClause(String prefixSqlField) {
		StringBuffer sb = new StringBuffer();
		
		if(getKeywords().size() != 0) {
			sb.append(" and ( ").append(getKeywords().getClause(prefixSqlField+"name", getLogicalOperator())).append(" ) ") ;
		}
		return sb.toString();
	}
	

	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(Keywords keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return the keywords
	 */
	public Keywords getKeywords() {
		return keywords;
	}

	/**
	 * @param logicalOperator the logicalOperator to set
	 */
	public void setLogicalOperator(String logicalOperator) {
		this.logicalOperator = logicalOperator;
	}

	/**
	 * @return the logicalOperator
	 */
	public String getLogicalOperator() {
		return logicalOperator;
	}

}

package org.bioinfo.infrared.funcannot.filter;

import java.util.List;

import org.bioinfo.commons.utils.StringUtils;

@SuppressWarnings("serial")
public class KeggFilter extends FunctionalFilter {

	private Keywords keywords;
	private String logicalOperator;
	
	public KeggFilter() {
		this(0,Integer.MAX_VALUE);
	}
	public KeggFilter(int minNumberGenes, int maxNumberGenes) {
		this.minNumberGenes = minNumberGenes;
		this.maxNumberGenes = maxNumberGenes;
		keywords = new Keywords(2);
		this.genomicNumberOfGenes = true;
	}
	
	public void addKeyword(String keyword) {
//		keywords.add(new Keyword(keyword));
		addKeywords(StringUtils.stringToList(keyword));
	}

	public void addKeywords(List<String> keywordNames) {
		for(String k: keywordNames) {
			keywords.add(new Keyword(k));
		}
	}
	
	@Override
	public String getSQLWhereClause(String prefixSqlField) {
		StringBuilder sb = new StringBuilder();
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

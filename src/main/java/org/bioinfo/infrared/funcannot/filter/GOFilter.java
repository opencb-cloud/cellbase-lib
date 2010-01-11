package org.bioinfo.infrared.funcannot.filter;

import java.util.List;

import org.bioinfo.commons.utils.StringUtils;


@SuppressWarnings("serial")
public class GOFilter extends FunctionalFilter{

	private String namespace;
	private int minLevel;
	private int maxLevel;
	private Keywords keywords;
	private String logicalOperator;
	private boolean propagated;
	
	public GOFilter(String namespace) {
		this.namespace = namespace;
		minLevel = 3;
		maxLevel = 19;		
		initDefaults();
	}
	
	public GOFilter(String namespace, int minLevel, int maxLevel) {
		this.namespace = namespace;
		this.minLevel = minLevel;
		this.maxLevel = maxLevel;
		initDefaults();
	}
	
	public GOFilter(String namespace, int minLevel, int maxLevel, int minNumberGenes, int maxNumberGenes) {
		this.namespace = namespace;
		this.minLevel = minLevel;
		this.maxLevel = maxLevel;
		this.minNumberGenes = minNumberGenes;
		this.maxNumberGenes = maxNumberGenes; 
		initDefaults();
	}
	
	private void initDefaults() {
		keywords = new Keywords(2);
		logicalOperator = "OR";
		this.useNumberOfGenes = true;
		this.propagated = true;
	}
	
	public void addKeyword(String keyword) {
		addKeywords(StringUtils.toList(keyword));
	}

	public void addKeywords(List<String> keywordNames) {
		for(String k: keywordNames) {
			keywords.add(new Keyword(k));
		}
	}
	
	public String getWhereClause(String prefixSqlField) {
		StringBuffer sb = new StringBuffer(" and ");
		sb.append(prefixSqlField).append("namespace = '").append(namespace).append("' and ");
		sb.append(prefixSqlField).append("level >= ").append(minLevel).append(" and ");
		sb.append(prefixSqlField).append("level <= ").append(maxLevel).append(" ");
		if(isUseNumberOfGenes()) {
			sb.append(" and ");
			sb.append(prefixSqlField).append("propagated_number_genes >= ").append(getMinNumberGenes()).append(" and ");
			sb.append(prefixSqlField).append("propagated_number_genes <= ").append(getMaxNumberGenes()).append(" ");
		}
		
		if(keywords.size() != 0) {
			sb.append(" and ( ").append(keywords.getClause(prefixSqlField+"name", getLogicalOperator())).append(" ) ") ;
		}
		return sb.toString();
	}
	
	/**
	 * @return the namespace
	 */
	public String getNamespace() {
		return namespace;
	}


	/**
	 * @param namespace the namespace to set
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}


	/**
	 * @return the minLevel
	 */
	public int getMinLevel() {
		return minLevel;
	}


	/**
	 * @param minLevel the minLevel to set
	 */
	public void setMinLevel(int minLevel) {
		this.minLevel = minLevel;
	}


	/**
	 * @return the maxLevel
	 */
	public int getMaxLevel() {
		return maxLevel;
	}


	/**
	 * @param maxLevel the maxLevel to set
	 */
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}


	/**
	 * @return the keywords
	 */
	public Keywords getKeywords() {
		return keywords;
	}


	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(Keywords keywords) {
		this.keywords = keywords;
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

	/**
	 * @param propagated the propagated to set
	 */
	public void setPropagated(boolean propagated) {
		this.propagated = propagated;
	}

	/**
	 * @return the propagated
	 */
	public boolean isPropagated() {
		return propagated;
	}


	
//	public String getWhereClause() {
////		StringBuilder query = new StringBuilder("select acc,name,namespace,description,level,score_b2g,coherence_index,parents from go_info where ");
//		StringBuilder query = new StringBuilder();
//		if(minLevel != null && !minLevel.equalsIgnoreCase("")) {
//			query.append(" level >= ").append(getDefaultValue(minLevel,""+GO.getMIN_LEVEL()));
//		}
//		if(maxLevel != null && !maxLevel.equalsIgnoreCase("")) {
//			query.append(" and ").append(" level <= ").append(getDefaultValue(maxLevel,""+GO.MAX_LEVEL));	
//		}
//		if(namespace != null && namespace.size() > 0) {
//			query.append(" and ").append(getNamespaceQuery());
//		}
//		if(keywords != null && keywords.size() > 0) {
//			query.append(" and ").append(getKeywordsQuery());
//		}
//		
//		return query.toString();
//	}
	
//	private String getDefaultValue(String value, String def) {
//		if(value == null) {
//			return def;
//		}else {
//			if(value.equals("")) {
//				return def;
//			}
//		}
//		return value;
//	}
	
//	private String getNamespaceQuery() {
//		StringBuilder ns = new StringBuilder();
//		ns.append(" ( ");
//		
//		for(int i=0; i<namespace.size();i++) {
//			if(namespace.get(i).equalsIgnoreCase("biological process")) {
//				ns.append(" namespace = ").append("'biological_process'");
//			} else {
//				if(namespace.get(i).equalsIgnoreCase("'molecular function'")) {
//					ns.append("molecular_function");
//				}else {
//					if(namespace.get(i).equalsIgnoreCase("'cellular component'")) {
//						ns.append("cellular_component");
//					}
//				}
//			}
//			if(i < namespace.size()-1) {
//				ns.append(" OR ");
//			}
//		}
//		ns.append(" ) ");
//		return ns.toString();
//	}
//	
//	private String getKeywordsQuery() {
//		StringBuilder kw = new StringBuilder();
//		kw.append(" ( ");
//		
//		for(int i=0; i<keywords.size(); i++) {
//			kw.append("description ").append(kw.toString());
//			
//			if(i < keywords.size()-1) {
//				kw.append(" OR ");
//			}
//		}
//		kw.append(" ) ");
//		return kw.toString();
//	}
}

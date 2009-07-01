package org.bioinfo.infrared.funcannot.filter;

@SuppressWarnings("serial")
public abstract class FunctionalFilter extends Filter{

	protected int minNumberGenes;
	protected int maxNumberGenes;
	protected boolean useNumberOfGenes;
	
	public FunctionalFilter() {
		this(0,Integer.MAX_VALUE);
	}
	
	public FunctionalFilter(int minNumberGenes, int maxNumberGenes) {
		this.minNumberGenes = minNumberGenes;
		this.maxNumberGenes = maxNumberGenes;
		this.useNumberOfGenes = true;
	}
	
	public abstract String getWhereClause(String prefixSqlField);
	
	
	
	public String getWhereClause() {
		return "";
	}

	/**
	 * @param minNumberGenes the minNumberGenes to set
	 */
	public void setMinNumberGenes(int minNumberGenes) {
		this.minNumberGenes = minNumberGenes;
	}

	/**
	 * @return the minNumberGenes
	 */
	public int getMinNumberGenes() {
		return minNumberGenes;
	}

	/**
	 * @param maxNumberGenes the maxNumberGenes to set
	 */
	public void setMaxNumberGenes(int maxNumberGenes) {
		this.maxNumberGenes = maxNumberGenes;
	}

	/**
	 * @return the maxNumberGenes
	 */
	public int getMaxNumberGenes() {
		return maxNumberGenes;
	}
	
	/**
	 * @param useNumberOfGenes the useNumberOfGenes to set
	 */
	public void setUseNumberOfGenes(boolean useNumberOfGenes) {
		this.useNumberOfGenes = useNumberOfGenes;
	}

	/**
	 * @return the useNumberOfGenes
	 */
	public boolean isUseNumberOfGenes() {
		return useNumberOfGenes;
	}
	
}

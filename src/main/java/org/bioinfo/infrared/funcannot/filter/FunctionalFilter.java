package org.bioinfo.infrared.funcannot.filter;

@SuppressWarnings("serial")
public abstract class FunctionalFilter extends Filter{

	protected int minNumberGenes;
	protected int maxNumberGenes;
	protected boolean genomicNumberOfGenes;
	
	public FunctionalFilter() {
		this(0, Integer.MAX_VALUE);
	}
	
	public FunctionalFilter(int minNumberGenes, int maxNumberGenes) {
		this.minNumberGenes = minNumberGenes;
		this.maxNumberGenes = maxNumberGenes;
		this.genomicNumberOfGenes = true;
	}
	
	public abstract String getSQLWhereClause(String prefixSqlField);
	
	
	public String getSQLWhereClause() {
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
	 * @param genomicNumberOfGenes the genomicNumberOfGenes to set
	 */
	public void setGenomicNumberOfGenes(boolean useNumberOfGenes) {
		this.genomicNumberOfGenes = useNumberOfGenes;
	}

	/**
	 * @return the genomicNumberOfGenes
	 */
	public boolean isGenomicNumberOfGenes() {
		return genomicNumberOfGenes;
	}
	
}

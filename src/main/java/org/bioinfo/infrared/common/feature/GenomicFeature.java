package org.bioinfo.infrared.common.feature;


public class GenomicFeature extends Feature{
	
	protected String chromosome;
	protected int start;
	protected int end;
	protected String strand;
	protected String sequence;
	
	public GenomicFeature() {
		// TODO Auto-generated constructor stub
	}
	
	public GenomicFeature(String id) {
		this(id, "NA", -1, -1, "NA");
	}
	
	public GenomicFeature(String id, String chromosome, int start, int end) {
		this(id, chromosome, start, end, "+", "");
	}
	
	public GenomicFeature(String id, String chromosome, int start, int end, String strand) {
		this(id, chromosome, start, end, strand, "");
	}
	
	public GenomicFeature(String id, String chromosome, int start, int end, String strand, String sequence) {
		super(id);
		this.chromosome = chromosome;
		this.start = start;
		this.end = end;
		this.strand = strand;
		this.sequence = sequence;
	}
	
	
	public String getLocation() {
		if(strand.equals("1") || strand.equals("+")) {
			return chromosome+":"+start+","+end+"(+)";
		}else {
			return chromosome+":"+start+","+end+"(-)";
		}
	}
	
//	@Override
//	public String getId() {
//		return "NA";
//	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
//		sb.append(id).append("\t");
		sb.append(chromosome).append("\t");
		sb.append(start).append("\t");
		sb.append(end).append("\t");
		sb.append(strand);
		return sb.toString();
	}
	
	/**
	 * @return the chromosome
	 */
	public String getChromosome() {
		return chromosome;
	}

	/**
	 * @param chromosome the chromosome to set
	 */
	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * @return the strand
	 */
	public String getStrand() {
		return strand;
	}

	/**
	 * @param strand the strand to set
	 */
	public void setStrand(String strand) {
		this.strand = strand;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return the sequence
	 */
	public String getSequence() {
		return sequence;
	}

}

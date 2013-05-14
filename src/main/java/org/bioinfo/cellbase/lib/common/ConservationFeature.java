package org.bioinfo.cellbase.lib.common;

public class ConservationFeature {

	private String chromosome;
	private int start;
	private int end;
	private int strand;
    private Float[] phastCons;
    private Float[] phylop;

	public ConservationFeature(String chromosome, int start, int end){
		this.chromosome = chromosome;
		this.start = start;
		this.end = end;
		this.strand = 1;
	}

	public ConservationFeature(String chromosome, int start, int end, int strand){
		this.chromosome = chromosome;
		this.start = start;
		this.end = end;
		this.strand = strand;
	}

	public int getStart() {
		return start;
	}

	public String getChromosome() {
		return chromosome;
	}

	public int getEnd() {
		return end;
	}
	
	public int getStrand() {
		return strand;
	}
	
	public void setStrand(int strand) {
		this.strand = strand;
	}

    public Float[] getPhastCons() {
        return phastCons;
    }

    public void setPhastCons(Float[] phastCons) {
        this.phastCons = phastCons;
    }

    public Float[] getPhylop() {
        return phylop;
    }

    public void setPhylop(Float[] phylop) {
        this.phylop = phylop;
    }

}

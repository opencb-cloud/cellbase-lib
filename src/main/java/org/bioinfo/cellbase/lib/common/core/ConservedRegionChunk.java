package org.bioinfo.cellbase.lib.common.core;

public class ConservedRegionChunk {

	private String chromosome;
	private int chunkId;
	private int start;
	private int end;
    private Float[] phastCons;
    private Float[] phylop;


	public ConservedRegionChunk(String chromosome, int chunkId, int start, int end) {
		this.chromosome = chromosome;
		this.chunkId = chunkId;
		this.start = start;
		this.end = end;

        int size = end - start + 1;
        this.phastCons = new Float[size];
        this.phylop = new Float[size];
	}
	
	
	public String getChromosome() {
		return chromosome;
	}
	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}
	
	public int getChunkId() {
		return chunkId;
	}
	public void setChunkId(int chunkId) {
		this.chunkId = chunkId;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
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

package org.bioinfo.infrared.lib.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.FeatureMap;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.Transcript;

public class GenomicRegionFeatures {

	private Region region;
	
	private List<Gene> genes;
	private List<Transcript> transcripts;
	private List<Exon> exons;
	private List<Snp> snp;

	private List<String> sources;
	
//	private List<String> positionConsequenceType = new ArrayList<String>();
	private HashMap<String, String> consequenceTypes = new HashMap<String, String>();
	
	public GenomicRegionFeatures(Region region){
		this.region = region;
		this.sources = sources;
	}


	public HashMap<String, String> getConsequenceType(){
		HashMap<String, String> types = new HashMap<String, String>();
		
		
		if (this.getTranscripts().size() > 0){
			
			
			if (this.getExons().size() == 0){
				for (Transcript transcript : this.getTranscripts()) {
					types.put(transcript.getStableId(), "INTRONIC");
				}
			}
			else{
				
				
				
				
				
				
				
				
				
			}
		
		
		}
		
		
		
		
		
		
		
		
		
		return types;
	}
	
	public Region getRegion() {
		return region;
	}


	public List<Gene> getGenes() {
		return genes;
	}


	public List<Transcript> getTranscripts() {
		return transcripts;
	}


	public List<Exon> getExons() {
		return exons;
	}


	public List<Snp> getSnp() {
		return snp;
	}


	public void setRegion(Region region) {
		this.region = region;
	}


	public void setGenes(List<Gene> genes) {
		this.genes = genes;
	}


	public void setTranscripts(List<Transcript> transcripts) {
		this.transcripts = transcripts;
	}


	public void setExons(List<Exon> exons) {
		this.exons = exons;
	}


	public void setSnp(List<Snp> snp) {
		this.snp = snp;
	}


	
	
}

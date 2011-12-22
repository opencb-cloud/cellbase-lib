package org.bioinfo.infrared.lib.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.FeatureMap;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.RegulatoryRegion;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.Tfbs;
import org.bioinfo.infrared.core.cellbase.Transcript;

public class GenomicRegionFeatures {

	private Region region;
	
	private List<Gene> genes;
	private List<Transcript> transcripts;
	private List<Exon> exons;
	private List<Snp> snp;
	private List<Tfbs> tfbs;
	private List<RegulatoryRegion> regulatoryRegion;
	
	private List<RegulatoryRegion> histones = new ArrayList<RegulatoryRegion>();
	private List<RegulatoryRegion> openChromatin  = new ArrayList<RegulatoryRegion>();
	private List<RegulatoryRegion> transcriptionFactor  = new ArrayList<RegulatoryRegion>();
	private List<RegulatoryRegion> polimerase  = new ArrayList<RegulatoryRegion>();

	private List<String> sources;
	
//	private List<String> positionConsequenceType = new ArrayList<String>();
	private HashMap<String, String> consequenceTypes = new HashMap<String, String>();
	
	public GenomicRegionFeatures(Region region){
		this.region = region;
		this.sources = sources;
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


	public void setTfbs(List<Tfbs> tfbs) {
		this.tfbs = tfbs;
	}


	public List<Tfbs> getTfbs() {
		return tfbs;
	}


	public void setRegulatoryRegion(List<RegulatoryRegion> regulatoryRegions) {
		this.regulatoryRegion = regulatoryRegions;
		
		
		for (RegulatoryRegion regulatoryRegion : regulatoryRegions) {
			System.out.println(regulatoryRegion.getType());
			
			if (regulatoryRegion.getType().equalsIgnoreCase("histone")){
				this.histones.add(regulatoryRegion);
			}
			
			if (regulatoryRegion.getType().equalsIgnoreCase("Open Chromatin")){
				this.openChromatin.add(regulatoryRegion);
			}
			
			if (regulatoryRegion.getType().equalsIgnoreCase("Polymerase")){
				this.polimerase.add(regulatoryRegion);
			}
			
			if (regulatoryRegion.getType().equalsIgnoreCase("Transcription Factor")){
				this.transcriptionFactor.add(regulatoryRegion);
			}
			
		}
		
	}


	public List<RegulatoryRegion> getRegulatoryRegion() {
		return regulatoryRegion;
	}


	public List<RegulatoryRegion> getHistones() {
		return histones;
	}


	public List<RegulatoryRegion> getOpenChromatin() {
		return openChromatin;
	}


	public List<RegulatoryRegion> getTranscriptionFactor() {
		return transcriptionFactor;
	}


	public List<RegulatoryRegion> getPolimerase() {
		return polimerase;
	}


	
	
}

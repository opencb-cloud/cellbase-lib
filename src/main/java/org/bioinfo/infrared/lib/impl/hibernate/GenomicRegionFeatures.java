package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.FeatureMap;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.RegulatoryRegion;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.Tfbs;
import org.bioinfo.infrared.core.cellbase.Transcript;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.SessionFactory;

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

	private ArrayList<String> genesIds;
	private ArrayList<String> transcriptsIds;
	private ArrayList<String> exonsIds;
	private ArrayList<String> snpsIds;
	private ArrayList<String> tfbsIds;
	private ArrayList<String> regulatoryIds;

	/** Para acceder posteriormente a los objetos bajo demanda **/
	private SessionFactory sessionFactory;
	private String species;

	public GenomicRegionFeatures(Region region){
		this.region = region;
	}
	
	public GenomicRegionFeatures(Region region, List<FeatureMap> featuresMap, SessionFactory sessionFactory, String species){
		this.region = region;
		
		this.sessionFactory = sessionFactory;
		this.species = species;
		
		/** obtengo todos los id's de los featuremap **/
		this.genesIds = new ArrayList<String>();
		this.transcriptsIds = new ArrayList<String>();
		this.exonsIds = new ArrayList<String>();
		this.snpsIds = new ArrayList<String>();
		this.tfbsIds = new ArrayList<String>();
		this.regulatoryIds = new ArrayList<String>();
		
		for (FeatureMap featureMap : featuresMap) {
			if (featureMap.getId().getSource().equals("gene")){
				genesIds.add(featureMap.getFeatureId());
				continue;
			}
			
			if (featureMap.getId().getSource().equals("transcript")){
				transcriptsIds.add(featureMap.getFeatureId());
				continue;
			}
			
			if (featureMap.getId().getSource().equals("exon")){
				exonsIds.add(featureMap.getFeatureId());
				continue;
			}
			
			if (featureMap.getId().getSource().equals("snp")){
				snpsIds.add(featureMap.getFeatureId());
				continue;
			}
			
			if (featureMap.getId().getSource().equals("tfbs")){
				tfbsIds.add(String.valueOf(featureMap.getId().getSourceId()));
				continue;
			}
			
			if (featureMap.getId().getSource().equals("regulatory_region")){
				regulatoryIds.add(String.valueOf(featureMap.getId().getSourceId()));
				continue;
			}
		}
		
	}
	
	
	
	/** Inicializo GenomicRegionFeatures, para las listas null si no estoy preguntando por esos sources y list(0) si realmente estoy preguntando por ellos**/
	/*
	if (sources != null){
		for (String source : sources) {
			if (source.equalsIgnoreCase("gene")){
				genomicRegionFeatures.setGenes(new GeneHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(genesIds));
				continue;
			}
			
			if (source.equalsIgnoreCase("transcript")){
				genomicRegionFeatures.setTranscripts(new TranscriptHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(transcriptsIds));
				continue;
			}
			
			if (source.equalsIgnoreCase("exon")){
				genomicRegionFeatures.setExons(new ExonHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(exonsIds));
				continue;
			}
			
			if (source.equalsIgnoreCase("tfbs")){
				genomicRegionFeatures.setTfbs(new TfbsHibernateDBAdaptor(this.getSessionFactory()).getAllByInternalIdList(tfbsIds));
				continue;
			}
			
			if (source.equalsIgnoreCase("regulatory_region")){
				genomicRegionFeatures.setRegulatoryRegion(new RegulatoryRegionHibernateDBAdaptor(this.getSessionFactory()).getAllByInternalIdList(tfbsIds));
				continue;
			}
			
			if (source.equalsIgnoreCase("snp")){
				genomicRegionFeatures.setSnp(new ArrayList<Snp>());
				
				List<List<Snp>> snpResult = new SnpHibernateDBAdapator(this.getSessionFactory()).getByDbSnpIdList(snpsIds);
				genomicRegionFeatures.setSnp(this.cleanSnpByRegion(region, snpResult));
				continue;
			}
		}
	}
	else{
		genomicRegionFeatures.setGenes(new GeneHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(genesIds));
		genomicRegionFeatures.setTranscripts(new TranscriptHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(transcriptsIds));
		genomicRegionFeatures.setExons(new ExonHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(exonsIds));
		genomicRegionFeatures.setTfbs(new TfbsHibernateDBAdaptor(this.getSessionFactory()).getAllByInternalIdList(tfbsIds));
		genomicRegionFeatures.setRegulatoryRegion(new RegulatoryRegionHibernateDBAdaptor(this.getSessionFactory()).getAllByInternalIdList(regulatoryIds));
		
		List<List<Snp>> snpResult = new SnpHibernateDBAdapator(this.getSessionFactory()).getByDbSnpIdList(snpsIds);
		genomicRegionFeatures.setSnp(this.cleanSnpByRegion(region, snpResult));
	}
	*/
	
	public Region getRegion() {
		return region;
	}


	public List<Gene> getGenes() {
		if (genes == null){
			genes = new GeneHibernateDBAdaptor(this.sessionFactory).getAllByEnsemblIdList(genesIds);
		}
		return genes;
	}


	public List<Transcript> getTranscripts() {
		if (transcripts == null){
			transcripts = new TranscriptHibernateDBAdaptor(sessionFactory).getAllByEnsemblIdList(transcriptsIds);
		}
		return transcripts;
	}


	public List<Exon> getExons() {
		if (exons == null){
			exons = new ExonHibernateDBAdaptor(this.sessionFactory).getAllByEnsemblIdList(exonsIds);
		}
		return exons;
	}


	public List<Snp> getSnp() {
		if (snp == null){
			snp = cleanSnpByRegion(this.region, new SnpHibernateDBAdapator(this.sessionFactory).getByDbSnpIdList(snpsIds));
		}
		return snp;
	}

	@SuppressWarnings("unused")
	private List<Snp> cleanSnpByRegion(Region region, List<List<Snp>> snpResult) {
		// Es posible que para el mismo dbName nos devuelva varios snp's de regiones diferentes, filtro los que esten dentro de la region indicada
		List<Snp> snps = new ArrayList<Snp>();
		for (List<Snp> list : snpResult) {
			if (list != null){
				for (Snp snp : list) {
					if (snp != null){
						if (snp.getChromosome().equals(region.getChromosome())){
							if (region.getStart() <=snp.getStart() && (region.getEnd() >= snp.getEnd())){
								snps.add(snp);
							}
						}
					}
				}
			}
		}
		return snps;
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

	public ArrayList<String> getTranscriptsIds() {
		return transcriptsIds;
	}


	
	
}

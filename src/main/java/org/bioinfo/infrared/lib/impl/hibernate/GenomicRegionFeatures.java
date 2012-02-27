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

	// features
	private List<String> genesIds;
	private List<String> transcriptsIds;
	private List<String> exonsIds;
	private List<String> snpsIds;
	
	// regulatory
	private List<String> tfbsIds;
	private List<String> mirnaTargetIds;
	private List<String> regulatoryRegionIds;

	/** Para acceder posteriormente a los objetos bajo demanda **/
	private SessionFactory sessionFactory;

	public List<FeatureMap> featuresMap;

	public GenomicRegionFeatures(Region region){
		this.region = region;
	}
	
	public GenomicRegionFeatures(Region region, List<FeatureMap> featuresMap, SessionFactory sessionFactory){
		this.featuresMap = featuresMap;
		this.region = region;
		
		this.sessionFactory = sessionFactory;
		
		/** obtengo todos los id's de los featuremap **/
		this.genesIds = new ArrayList<String>();
		this.transcriptsIds = new ArrayList<String>();
		this.exonsIds = new ArrayList<String>();
		this.snpsIds = new ArrayList<String>();
		this.tfbsIds = new ArrayList<String>();
		this.mirnaTargetIds = new ArrayList<String>();
		this.regulatoryRegionIds = new ArrayList<String>();
		
//		logger.("Feature map: " + featuresMap.size());
		
		for(FeatureMap featureMap : featuresMap) {
			if (featureMap.getFeatureType().equals("gene")){
				genesIds.add(featureMap.getFeatureName());
				continue;
			}
			
			if (featureMap.getFeatureType().equals("transcript")){
				transcriptsIds.add(featureMap.getFeatureName());
				continue;
			}
			
			if (featureMap.getFeatureType().equals("exon")){
				exonsIds.add(featureMap.getFeatureName());
				continue;
			}
			
			if (featureMap.getFeatureType().equals("snp")){
				snpsIds.add(featureMap.getFeatureName());
				continue;
			}
			
			/**
			 * If a feature name in FeatureMap maps to more than 1 location
			 * then PK is store in order to get the correct one.
			 */
			if (featureMap.getFeatureType().equals("tfbs")){
				tfbsIds.add(String.valueOf(featureMap.getFeatureId()));
				continue;
			}
			
			if (featureMap.getFeatureType().equals("mirna_target")){
				mirnaTargetIds.add(String.valueOf(featureMap.getFeatureId()));
				continue;
			}
			
			if (featureMap.getFeatureType().equals("regulatory_region")){
				regulatoryRegionIds.add(String.valueOf(featureMap.getFeatureId()));
				continue;
			}
		}
		
	}
	
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
			snp = cleanSnpByRegion(this.region, new SnpHibernateDBAdapator(this.sessionFactory).getAllBySnpIdList(snpsIds));
		}
		return snp;
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
		if (tfbs == null){
			tfbs = new TfbsHibernateDBAdaptor(sessionFactory).getAllByInternalIdList(this.getTfbsIds());
		}
		return tfbs;
	}

	public List<RegulatoryRegion> getRegulatoryRegion() {
		if (regulatoryRegion == null){
			this.setRegulatoryRegion(new RegulatoryRegionHibernateDBAdaptor(sessionFactory).getAllByInternalIdList(this.getRegulatoryIds()));
		}
		return regulatoryRegion;
	}


	public List<RegulatoryRegion> getHistones() {
		if (regulatoryRegion == null){
			this.setRegulatoryRegion(new RegulatoryRegionHibernateDBAdaptor(sessionFactory).getAllByInternalIdList(this.getRegulatoryIds()));
		}
		return histones;
	}


	public List<RegulatoryRegion> getOpenChromatin() {
		if (regulatoryRegion == null){
			this.setRegulatoryRegion(new RegulatoryRegionHibernateDBAdaptor(sessionFactory).getAllByInternalIdList(this.getRegulatoryIds()));
		}
		return openChromatin;
	}


	public List<RegulatoryRegion> getPolimerase() {
		if (regulatoryRegion == null){
			this.setRegulatoryRegion(new RegulatoryRegionHibernateDBAdaptor(sessionFactory).getAllByInternalIdList(this.getRegulatoryIds()));
		}
		return polimerase;
	}
	
	public List<RegulatoryRegion> getTranscriptionFactor() {
		if (regulatoryRegion == null){
			this.setRegulatoryRegion(new RegulatoryRegionHibernateDBAdaptor(sessionFactory).getAllByInternalIdList(this.getTfbsIds()));
		}
		return transcriptionFactor;
	}

	public List<String> getTranscriptsIds() {
		return transcriptsIds;
	}

	public List<String> getGenesIds() {
		return genesIds;
	}

	public List<String> getExonsIds() {
		return exonsIds;
	}

	public List<String> getSnpsIds() {
		return snpsIds;
	}

	public List<String> getTfbsIds() {
		return tfbsIds;
	}

	public List<String> getRegulatoryIds() {
		return regulatoryRegionIds;
	}


	
	
}

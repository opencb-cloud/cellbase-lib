package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.FeatureMap;
import org.bioinfo.infrared.core.cellbase.FeatureMapId;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.Transcript;
import org.bioinfo.infrared.lib.api.GenomicRegionFeatureDBAdaptor;
import org.bioinfo.infrared.lib.common.GenomicRegionFeatures;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class GenomicRegionFeatureHibernateDBAdaptor extends HibernateDBAdaptor implements GenomicRegionFeatureDBAdaptor {
	private static int FEATURE_MAP_CHUNK_SIZE = 400;
	
	public GenomicRegionFeatureHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	
	/*
	@SuppressWarnings("unchecked")
	public List<FeatureMapId> getAllByRegion(String chromosome, int start, int end) {
		int chunk_start = start / GenomicRegionFeatureHibernateDBAdaptor.FEATURE_MAP_CHUNK_SIZE;
		int chunk_end = end / GenomicRegionFeatureHibernateDBAdaptor.FEATURE_MAP_CHUNK_SIZE;
		
		System.out.println("getAllByRegion: " + chromosome + " : " + start + ", "+ end + " ----> " + chunk_start + "," + chunk_end);
		
		Criteria criteria = this.openSession().createCriteria(FeatureMapId.class);
		
		criteria.add(Restrictions.ge("chunk_id", chunk_start));
		criteria.add(Restrictions.le("chunk_id", chunk_end));
		criteria.add(Restrictions.eq("chromosome", chromosome));
		
		List<FeatureMapId> list = (List<FeatureMapId>)executeAndClose(criteria);
		
		System.out.println("result: " + list.size());
		return list;
	
	}*/
	
	public List<FeatureMapId> getAllByRegion(String chromosome, int start, int end, List<String> features) {
		
		if(features.contains("")) {
			
		}
		return null;
	
	}



	@Override
	public List<GenomicRegionFeatures> getAllByRegionList(List<Region> regions) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<GenomicRegionFeatures> getAllByRegionList(List<Region> regions,List<String> sources) {
		// TODO Auto-generated method stub
		return null;
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
	
	
	private GenomicRegionFeatures getGenomicRegionFeature(List<FeatureMap> featuresMap, Region region, List<String> sources) {
		GenomicRegionFeatures genomicRegionFeatures = new GenomicRegionFeatures(region);
		
		/** obtengo todos los id's de los featuremap **/
		List<String> genesIds = new ArrayList<String>();
		List<String> transcriptsIds = new ArrayList<String>();
		List<String> exonsIds = new ArrayList<String>();
		List<String> snpsIds = new ArrayList<String>();
		
		for (FeatureMap featureMap : featuresMap) {
			if (featureMap.getId().getSource().equals("gene")){
				genesIds.add(featureMap.getFeatureId());
			}
			
			if (featureMap.getId().getSource().equals("transcript")){
				transcriptsIds.add(featureMap.getFeatureId());
			}
			
			if (featureMap.getId().getSource().equals("exon")){
				exonsIds.add(featureMap.getFeatureId());
			}
			
			if (featureMap.getId().getSource().equals("snp")){
				snpsIds.add(featureMap.getFeatureId());
			}
		}
		
		
		/** Inicializo GenomicRegionFeatures, para las listas null si no estoy preguntando por esos sources y list(0) si realmente estoy preguntando por ellos**/
		if (sources != null){
			for (String source : sources) {
				if (source.equalsIgnoreCase("gene")){
					genomicRegionFeatures.setGenes(new ArrayList<Gene>());
					genomicRegionFeatures.getGenes().addAll(new GeneHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(genesIds));
				}
				
				if (source.equalsIgnoreCase("transcript")){
					genomicRegionFeatures.setTranscripts(new ArrayList<Transcript>());
					genomicRegionFeatures.getTranscripts().addAll(new TranscriptHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(transcriptsIds));
				}
				
				if (source.equalsIgnoreCase("exon")){
					genomicRegionFeatures.setExons(new ArrayList<Exon>());
					genomicRegionFeatures.getExons().addAll(new ExonHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(exonsIds));
				}
				
				if (source.equalsIgnoreCase("snp")){
					genomicRegionFeatures.setSnp(new ArrayList<Snp>());
					
					List<List<Snp>> snpResult = new SnpHibernateDBAdapator(this.getSessionFactory()).getByDbSnpIdList(snpsIds);
					genomicRegionFeatures.getSnp().addAll(this.cleanSnpByRegion(region, snpResult));
				}
			}
		}
		else{
			genomicRegionFeatures.setGenes(new ArrayList<Gene>());
			genomicRegionFeatures.setTranscripts(new ArrayList<Transcript>());
			genomicRegionFeatures.setExons(new ArrayList<Exon>());
			genomicRegionFeatures.setSnp(new ArrayList<Snp>());
			
			genomicRegionFeatures.getGenes().addAll(new GeneHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(genesIds));
			genomicRegionFeatures.getTranscripts().addAll(new TranscriptHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(transcriptsIds));
			genomicRegionFeatures.getExons().addAll(new ExonHibernateDBAdaptor(this.getSessionFactory()).getAllByEnsemblIdList(exonsIds));
			
			List<List<Snp>> snpResult = new SnpHibernateDBAdapator(this.getSessionFactory()).getByDbSnpIdList(snpsIds);
			System.out.println("rrrr " + snpResult.size() + " " + snpsIds);
			genomicRegionFeatures.getSnp().addAll(this.cleanSnpByRegion(region, snpResult));
		}
		
		
		return genomicRegionFeatures;
	}

	private GenomicRegionFeatures getByRegion(String chromosome, int start, int end, List<String> sources) {
		int chunk_start = start / GenomicRegionFeatureHibernateDBAdaptor.FEATURE_MAP_CHUNK_SIZE;
		int chunk_end = end / GenomicRegionFeatureHibernateDBAdaptor.FEATURE_MAP_CHUNK_SIZE;
		
		Query query = this.openSession()
		.createQuery("select featureMap from FeatureMap as featureMap left join fetch featureMap.id where id.chunkId >= :start and id.chunkId <= :end")
		.setParameter("start", chunk_start)
		.setParameter("end", chunk_end);
		List<FeatureMap> list = (List<FeatureMap>)executeAndClose(query);
		
		GenomicRegionFeatures genomicRegionFeatures = this.getGenomicRegionFeature(list, new Region(chromosome, start, end), sources);
		return genomicRegionFeatures;
	}
	
	@Override
	public GenomicRegionFeatures getByRegion(String chromosome, int start, int end) {
		return getByRegion(chromosome, start, end, null);
	}

	@Override
	public GenomicRegionFeatures getByRegion(Region region) {
		return getByRegion(region.getChromosome(), region.getStart(), region.getEnd(), null);
	}

	@Override
	public GenomicRegionFeatures getByRegion(Region region, List<String> sources) {
		return getByRegion(region.getChromosome(), region.getStart(), region.getEnd(), sources);
	}

}

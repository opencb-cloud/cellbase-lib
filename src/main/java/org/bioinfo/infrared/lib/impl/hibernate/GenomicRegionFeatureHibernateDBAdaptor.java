package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.FeatureMap;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.lib.api.GenomicRegionFeatureDBAdaptor;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class GenomicRegionFeatureHibernateDBAdaptor extends HibernateDBAdaptor implements GenomicRegionFeatureDBAdaptor {
	private static int FEATURE_MAP_CHUNK_SIZE = 400;
	
	public GenomicRegionFeatureHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	

	@Override
	public List<GenomicRegionFeatures> getAllByRegionList(List<Region> regions) {
		return this.getAllByRegionList(regions, null);
	}

	@Override
	public List<GenomicRegionFeatures> getAllByRegionList(List<Region> regions, List<String> sources) {
		 List<GenomicRegionFeatures> result = new ArrayList<GenomicRegionFeatures>();
		 for (int i = 0; i < regions.size(); i++) {
			 result.add(this.getByRegion(regions.get(i), sources));
		 }
		return result;
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
	
	@SuppressWarnings("unchecked")
	private GenomicRegionFeatures getByRegion(String chromosome, int start, int end, List<String> sources) {
		int chunk_start = start / GenomicRegionFeatureHibernateDBAdaptor.FEATURE_MAP_CHUNK_SIZE;
		int chunk_end = end / GenomicRegionFeatureHibernateDBAdaptor.FEATURE_MAP_CHUNK_SIZE;
		
		
		Query query;
		
		if (chunk_start == chunk_end){
			query = this.openSession().createQuery("select featureMap from FeatureMap as featureMap where id.chunkId = :start_chunk and featureMap.start<= :endparam and featureMap.end >= :startparam and chromosome=:chromosome");			
		}
		else{
			query = this.openSession().createQuery("select featureMap from FeatureMap as featureMap where id.chunkId >= :start_chunk and id.chunkId <= :end_chunk and featureMap.start<= :endparam and featureMap.end >= :startparam and chromosome=:chromosome");
			query.setParameter("end_chunk", chunk_end);
		}
		
	
		query.setParameter("start_chunk", chunk_start);
		query.setParameter("startparam", start);
		query.setParameter("endparam", end);
		query.setParameter("chromosome", chromosome);
		List<FeatureMap> list = (List<FeatureMap>)executeAndClose(query);
		
		//TODO: ojo corregir esto ¿DE DONDE SACO LA SPECIE **/
		GenomicRegionFeatures genomicRegionFeatures = new GenomicRegionFeatures(new Region(chromosome, start, end), list, this.getSessionFactory() , "hsa"); 
		
		return genomicRegionFeatures;
	}
	
	
	
	@SuppressWarnings("unused")
	private GenomicRegionFeatures getGenomicRegionFeature(List<FeatureMap> featuresMap, Region region, List<String> sources) {
		GenomicRegionFeatures genomicRegionFeatures = new GenomicRegionFeatures(region);
		return genomicRegionFeatures;
	}
	
	

	
	
	
	
	/** Consequence Type by Position **/
	
	@SuppressWarnings("unused")
	private void addElement(String key, String value, HashMap<String, List<String>> collection ){
		if(!collection.containsKey(key)){
			collection.put(key, new ArrayList<String>());
		}
		collection.get(key).add(value);
	}
	

	
	
	
	
	
	


	

}

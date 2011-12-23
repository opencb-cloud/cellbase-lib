package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.FeatureMap;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.lib.api.GenomicRegionFeatureDBAdaptor;
import org.bioinfo.infrared.lib.common.GenomicVariant;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Query;
import org.hibernate.Session;
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
	
	
	private GenomicRegionFeatures getByRegion(String chromosome, int start, int end, List<String> sources, Session session) {
		int chunk_start = start / GenomicRegionFeatureHibernateDBAdaptor.FEATURE_MAP_CHUNK_SIZE;
		int chunk_end = end / GenomicRegionFeatureHibernateDBAdaptor.FEATURE_MAP_CHUNK_SIZE;
		
		Query query;
		
		if (chunk_start == chunk_end){
			query = session.createQuery("select featureMap from FeatureMap as featureMap where id.chunkId = :start_chunk and featureMap.start<= :endparam and featureMap.end >= :startparam and chromosome=:chromosome");			
		}
		else{
			query = session.createQuery("select featureMap from FeatureMap as featureMap where id.chunkId >= :start_chunk and id.chunkId <= :end_chunk and featureMap.start<= :endparam and featureMap.end >= :startparam and chromosome=:chromosome");
			query.setParameter("end_chunk", chunk_end);
		}
		
		query.setParameter("start_chunk", chunk_start);
		query.setParameter("startparam", start);
		query.setParameter("endparam", end);
		query.setParameter("chromosome", chromosome);
		List<FeatureMap> list = (List<FeatureMap>)execute(query);
		
		//TODO: ojo corregir esto ¿DE DONDE SACO LA SPECIE **/
		GenomicRegionFeatures genomicRegionFeatures = new GenomicRegionFeatures(new Region(chromosome, start, end), list, this.getSessionFactory() , "hsa"); 
		return genomicRegionFeatures;
	}
	
	@SuppressWarnings("unchecked")
	private GenomicRegionFeatures getByRegion(String chromosome, int start, int end, List<String> sources) {
		Session session = this.openSession();
		GenomicRegionFeatures result = this.getByRegion(chromosome, start, end, sources, session);
		session.close();
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<GenomicRegionFeatures> getByVariants(List<GenomicVariant> variants, List<String> sources) {
		List<GenomicRegionFeatures> result = new ArrayList<GenomicRegionFeatures>();
		Session session = this.openSession();
		for (GenomicVariant variant : variants) {
			result.add(this.getByRegion(variant.getChromosome(), variant.getStart(), variant.getStart(), sources, session));
		}
		session.close();
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<GenomicRegionFeatures> getByVariants(List<GenomicVariant> variants) {
		return this.getByVariants(variants, null);
	}
	
	
	@SuppressWarnings("unused")
	private GenomicRegionFeatures getGenomicRegionFeature(List<FeatureMap> featuresMap, Region region, List<String> sources) {
		return null;
	}
	
	

	
	
	

	
	
	
	
	
	


	

}

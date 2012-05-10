package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.ConservedRegion;
import org.bioinfo.infrared.core.cellbase.RegulatoryRegion;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.lib.api.RegulatoryRegionDBAdaptor;
import org.bioinfo.infrared.lib.common.IntervalFeatureFrequency;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

class RegulatoryRegionHibernateDBAdaptor extends HibernateDBAdaptor implements RegulatoryRegionDBAdaptor {


	public RegulatoryRegionHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public RegulatoryRegionHibernateDBAdaptor(SessionFactory sessionFactory, String species, String version) {
		super(sessionFactory, species, version);
	}
	
	
	
	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome) {
		return getAllByRegion(new Region(chromosome, 0, Integer.MAX_VALUE));
	}

	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start) {
		return getAllByRegion(new Region(chromosome, start, Integer.MAX_VALUE));
	}

	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start, int end) {
		return getAllByRegion(new Region(chromosome, start, end));
	}

	@Override
	public List<RegulatoryRegion> getAllByRegion(Region region) {
		return null;
	}

	@Override
	public List<RegulatoryRegion> getAllByRegion(Region region, String type) {
		List<RegulatoryRegion> regulatoryRegionsList = new ArrayList<RegulatoryRegion>();
		Criteria criteria =  this.openSession().createCriteria(RegulatoryRegion.class);
		criteria.add(Restrictions.eq("chromosome", region.getChromosome()))
			.add(Restrictions.le("start", region.getEnd()))
			.add(Restrictions.ge("end", region.getStart()))
			.add(Restrictions.eq("type", type));
		
		return (List<RegulatoryRegion>) executeAndClose(criteria);
	}
	
	@Override
	public List<List<RegulatoryRegion>> getAllByRegionList(List<Region> regionList) {
		return this.getAllByRegionList(regionList, null);
	}

	
	
	
	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome, List<String> type) {
		return getAllByRegion(chromosome, 0, Integer.MAX_VALUE, type);
	}

	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start, List<String> type) {
		return getAllByRegion(chromosome, start, Integer.MAX_VALUE, type);
	}

	
	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start, int end, List<String> typeList) {
		List<RegulatoryRegion> regulatoryRegionsList = new ArrayList<RegulatoryRegion>();
		if(typeList != null){
			for(String type: typeList) {
				regulatoryRegionsList.addAll(getAllByRegion(new Region(chromosome, start, end), type));
			}
		}
		return regulatoryRegionsList;
	}
	
	@SuppressWarnings("unchecked")
	public List<RegulatoryRegion> getAllByRegion2(String chromosome, int start, int end, List<String> type) {
		long t0 = System.currentTimeMillis();

		/** Utilizar feature map !!! **/ 
		GenomicRegionFeatureHibernateDBAdaptor adaptor = new GenomicRegionFeatureHibernateDBAdaptor(this.getSessionFactory());
		GenomicRegionFeatures genomicRegionFeatures = adaptor.getByRegion(new Region(chromosome, start, end), type);
		
		logger.debug("Regulatory obtained: " + genomicRegionFeatures.getRegulatoryIds().size() + "  " + (System.currentTimeMillis()-t0)+" ms");
		logger.debug("type:  " + genomicRegionFeatures.getHistones().size());
	
		List<RegulatoryRegion> result = new ArrayList<RegulatoryRegion>();
		
		if (type == null){
			return genomicRegionFeatures.getRegulatoryRegion();
		}
		else{
			t0 = System.currentTimeMillis();
			if (type == null){
				System.out.println("type: null ");
			}
			else{
				System.out.println("type: " + type);
			}
			for (String string : type) {
				
				if (string.equalsIgnoreCase("histone")){
					result.addAll(genomicRegionFeatures.getHistones());
				}
				if (string.equalsIgnoreCase("Open Chromatin")){
					result.addAll(genomicRegionFeatures.getOpenChromatin());
				}
				if (string.equalsIgnoreCase("Polymerase")){
					result.addAll(genomicRegionFeatures.getPolimerase());
				}
				if (string.equalsIgnoreCase("Transcription Factor")){
					result.addAll(genomicRegionFeatures.getTranscriptionFactor());
				}
				
			}
			logger.debug("Subtype obtained: " + result.size() + "  " + (System.currentTimeMillis()-t0)+" ms");
			return result;
		}
	}
	
	@Override
	public List<List<RegulatoryRegion>> getAllByRegionList(List<Region> regionList,List<String> type) {
		List<List<RegulatoryRegion>> result = new ArrayList<List<RegulatoryRegion>>();
		for(Region region : regionList) {
			result.add(this.getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd(), type));
		}
		return result;
	}
	
	
	@Override
	public List<GenomicRegionFeatures> getAllFeatureMapByRegion(List<Region> regions){
		 List<GenomicRegionFeatures> result = new ArrayList<GenomicRegionFeatures>();
		 for (Region region : regions) {
			 result.add(this.getAllFeatureMapByRegion(region.getChromosome(), region.getStart(), region.getEnd()));
		 }
		 return result;
	}
	
	
	public GenomicRegionFeatures getAllFeatureMapByRegion(String chromosome, int start, int end) {
		GenomicRegionFeatureHibernateDBAdaptor adaptor = new GenomicRegionFeatureHibernateDBAdaptor(this.getSessionFactory());
		GenomicRegionFeatures genomicRegionFeatures = adaptor.getByRegion(new Region(chromosome, start, end));
		return genomicRegionFeatures;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegulatoryRegion> getAllByInternalId(String id) {
		  String Hquery = "from RegulatoryRegion re where re.regulatoryRegionId=:id";
		  Query query = this.openSession().createQuery(Hquery);
		  query.setParameter("id", Integer.valueOf(id));
		  return (List<RegulatoryRegion>) executeAndClose(query);
	}
	
	
	
	@Override
	public List<RegulatoryRegion> getAllByInternalIdList(List<String> idList) {
		List<RegulatoryRegion> result = new ArrayList<RegulatoryRegion>();
		for (String id : idList) {
			result.addAll(this.getAllByInternalId(id));
		}
		return result;
		
	}
	
	

	@Override
	public List<? extends Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Region> getAllRegionsByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllSequencesByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getFullInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getFullInfoByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getInfoByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Region getRegionById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSequenceById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConservedRegion> getAllConservedRegionByRegion(Region region) {
		
		int chunk_size = applicationProperties.getIntProperty("CHUNK_SIZE", 400);
		int start_chunk = region.getStart() / chunk_size;
		int end_chunk = region.getEnd() / chunk_size;
		
		Query query = this.openSession().createQuery("select distinct cr from ConservedRegion cr, FeatureMap fm where cr.conservedRegionId=fm.featureId and fm.chromosome= :CHROMOSOME and fm.chunkId >= :START and fm.chunkId <= :END")
										.setParameter("CHROMOSOME", region.getChromosome())
										.setParameter("START", start_chunk)
										.setParameter("END", end_chunk);
		return (List<ConservedRegion>) executeAndClose(query);
	}

	@Override
	public List<List<ConservedRegion>> getAllConservedRegionByRegionList(List<Region> regionList) {
		List<List<ConservedRegion>> result = new ArrayList<List<ConservedRegion>>(regionList.size());
		for (Region region : regionList) {
			result.add(this.getAllConservedRegionByRegion(region));
		}
		return result;
	}

	@Override
	public List<IntervalFeatureFrequency> getAllRegulatoryRegionIntervalFrequencies(Region region, int interval, String type) {
		SQLQuery sqlquery = this.openSession().createSQLQuery("select (rr.start - "+region.getStart()+") DIV "+interval+" as inter, count(*) from regulatory_region rr where rr.chromosome= '"+region.getChromosome()+"' and rr.start <= "+region.getEnd()+" and rr.end >= "+region.getStart()+" and rr.type = '"+type+"' group by inter");
		List<Object[]> objectList =  (List<Object[]>) executeAndClose(sqlquery);
		List<IntervalFeatureFrequency> intervalFreqsList = getIntervalFeatureFrequencies(region , interval, objectList);
		return intervalFreqsList;
	}
	
	@Override
	public List<IntervalFeatureFrequency> getAllRegulatoryRegionIntervalFrequencies(Region region, int interval) {
		SQLQuery sqlquery = this.openSession().createSQLQuery("select (rr.start - "+region.getStart()+") DIV "+interval+" as inter, count(*) from regulatory_region rr where rr.chromosome= '"+region.getChromosome()+"' and rr.start <= "+region.getEnd()+" and rr.end >= "+region.getStart()+" group by inter");
		List<Object[]> objectList =  (List<Object[]>) executeAndClose(sqlquery);
		List<IntervalFeatureFrequency> intervalFreqsList = getIntervalFeatureFrequencies(region , interval, objectList);
		return intervalFreqsList;
	}

	@Override
	public List<IntervalFeatureFrequency> getAllConservedRegionIntervalFrequencies(Region region, int interval) {
		SQLQuery sqlquery = this.openSession().createSQLQuery("select (cr.start - "+region.getStart()+") DIV "+interval+" as inter, count(*) from conserved_region cr where cr.chromosome= '"+region.getChromosome()+"' and cr.start <= "+region.getEnd()+" and cr.end >= "+region.getStart()+" group by inter");
		List<Object[]> objectList =  (List<Object[]>) executeAndClose(sqlquery);
		List<IntervalFeatureFrequency> intervalFreqsList = getIntervalFeatureFrequencies(region , interval, objectList);
		return intervalFreqsList;
	}


}

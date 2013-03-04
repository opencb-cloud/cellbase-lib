package org.bioinfo.cellbase.lib.impl.hibernate;

import org.hibernate.SessionFactory;

class RegulatoryRegionHibernateDBAdaptor {


	
	/*
	
	
	public List<RegulatoryRegion> getAllByRegion(String chromosome) {
		return getAllByRegion(new Region(chromosome, 0, Integer.MAX_VALUE));
	}

	
	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start) {
		return getAllByRegion(new Region(chromosome, start, Integer.MAX_VALUE));
	}

	
	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start, int end) {
		return getAllByRegion(new Region(chromosome, start, end));
	}

	
	public List<RegulatoryRegion> getAllByRegion(Region region) {
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<RegulatoryRegion> getAllByRegion(Region region, String type) {
		Criteria criteria =  this.openSession().createCriteria(RegulatoryRegion.class)
				.add(Restrictions.eq("chromosome", region.getChromosome()))
				.add(Restrictions.le("start", region.getEnd()))
				.add(Restrictions.ge("end", region.getStart()))
				.add(Restrictions.eq("type", type));
		return (List<RegulatoryRegion>) executeAndClose(criteria);
	}
	
	
	public List<List<RegulatoryRegion>> getAllByRegionList(List<Region> regionList) {
		return this.getAllByRegionList(regionList, null);
	}

	
	
	
	
	public List<RegulatoryRegion> getAllByRegion(String chromosome, List<String> type) {
		return getAllByRegion(chromosome, 0, Integer.MAX_VALUE, type);
	}

	
	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start, List<String> type) {
		return getAllByRegion(chromosome, start, Integer.MAX_VALUE, type);
	}

	
	
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
	
	
	public List<List<RegulatoryRegion>> getAllByRegionList(List<Region> regionList,List<String> type) {
		List<List<RegulatoryRegion>> result = new ArrayList<List<RegulatoryRegion>>();
		for(Region region : regionList) {
			result.add(this.getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd(), type));
		}
		return result;
	}
	
	
	
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
	
	public List<RegulatoryRegion> getAllByInternalId(String id) {
		  String Hquery = "from RegulatoryRegion re where re.regulatoryRegionId=:id";
		  Query query = this.openSession().createQuery(Hquery);
		  query.setParameter("id", Integer.valueOf(id));
		  return (List<RegulatoryRegion>) executeAndClose(query);
	}
	
	
	
	
	public List<RegulatoryRegion> getAllByInternalIdList(List<String> idList) {
		List<RegulatoryRegion> result = new ArrayList<RegulatoryRegion>();
		for (String id : idList) {
			result.addAll(this.getAllByInternalId(id));
		}
		return result;
		
	}
	
	

	
	public List<? extends Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<String> getAllIds() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Region> getAllRegionsByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<String> getAllSequencesByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Map<String, Object> getFullInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Map<String, Object>> getFullInfoByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Map<String, Object> getInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Map<String, Object>> getInfoByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Region getRegionById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getSequenceById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@SuppressWarnings("unchecked")
	
	public List<ConservedRegion> getAllConservedRegionByRegion(Region region) {
		
		int chunk_size = applicationProperties.getIntProperty("CELLBASE."+version.toUpperCase()+".FEATURE_MAP.CHUNK_SIZE", 500);
		int start_chunk = region.getStart() / chunk_size;
		int end_chunk = region.getEnd() / chunk_size;
		
		Query query = this.openSession().createQuery("select distinct cr from ConservedRegion cr, FeatureMap fm where cr.conservedRegionId=fm.featureId and fm.chromosome= :CHROMOSOME and fm.chunkId >= :START and fm.chunkId <= :END")
										.setParameter("CHROMOSOME", region.getChromosome())
										.setParameter("START", start_chunk)
										.setParameter("END", end_chunk);
		return (List<ConservedRegion>) executeAndClose(query);
	}

	
	public List<List<ConservedRegion>> getAllConservedRegionByRegionList(List<Region> regionList) {
		List<List<ConservedRegion>> result = new ArrayList<List<ConservedRegion>>(regionList.size());
		for (Region region : regionList) {
			result.add(this.getAllConservedRegionByRegion(region));
		}
		return result;
	}

	
	public List<IntervalFeatureFrequency> getAllRegulatoryRegionIntervalFrequencies(Region region, int interval, String type) {
		SQLQuery sqlquery = this.openSession().createSQLQuery("select (rr.start - "+region.getStart()+") DIV "+interval+" as inter, count(*) from regulatory_region rr where rr.chromosome= '"+region.getChromosome()+"' and rr.start <= "+region.getEnd()+" and rr.end >= "+region.getStart()+" and rr.type = '"+type+"' group by inter");
		List<Object[]> objectList =  (List<Object[]>) executeAndClose(sqlquery);
		List<IntervalFeatureFrequency> intervalFreqsList = getIntervalFeatureFrequencies(region , interval, objectList);
		return intervalFreqsList;
	}
	
	
	public List<IntervalFeatureFrequency> getAllRegulatoryRegionIntervalFrequencies(Region region, int interval) {
		SQLQuery sqlquery = this.openSession().createSQLQuery("select (rr.start - "+region.getStart()+") DIV "+interval+" as inter, count(*) from regulatory_region rr where rr.chromosome= '"+region.getChromosome()+"' and rr.start <= "+region.getEnd()+" and rr.end >= "+region.getStart()+" group by inter");
		List<Object[]> objectList =  (List<Object[]>) executeAndClose(sqlquery);
		List<IntervalFeatureFrequency> intervalFreqsList = getIntervalFeatureFrequencies(region , interval, objectList);
		return intervalFreqsList;
	}

	
	public List<IntervalFeatureFrequency> getAllConservedRegionIntervalFrequencies(Region region, int interval) {
		SQLQuery sqlquery = this.openSession().createSQLQuery("select (cr.start - "+region.getStart()+") DIV "+interval+" as inter, count(*) from conserved_region cr where cr.chromosome= '"+region.getChromosome()+"' and cr.start <= "+region.getEnd()+" and cr.end >= "+region.getStart()+" group by inter");
		List<Object[]> objectList =  (List<Object[]>) executeAndClose(sqlquery);
		List<IntervalFeatureFrequency> intervalFreqsList = getIntervalFeatureFrequencies(region , interval, objectList);
		return intervalFreqsList;
	}
*/

}

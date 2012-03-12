package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.ConservedRegion;
import org.bioinfo.infrared.core.cellbase.FeatureMap;
import org.bioinfo.infrared.core.cellbase.Pwm;
import org.bioinfo.infrared.core.cellbase.RegulatoryRegion;
import org.bioinfo.infrared.lib.api.RegulatoryRegionDBAdaptor;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.omg.CORBA.portable.ApplicationException;

class RegulatoryRegionHibernateDBAdaptor extends HibernateDBAdaptor implements RegulatoryRegionDBAdaptor {


	public RegulatoryRegionHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public RegulatoryRegionHibernateDBAdaptor(SessionFactory sessionFactory, String species, String version) {
		super(sessionFactory, species, version);
	}
	
	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegulatoryRegion> getAllByRegion(Region region) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome, List<String> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start,
			List<String> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start, int end, List<String> type) {
		long t0 = System.currentTimeMillis();

		/** Utilizar feature map !!! **/ 
		GenomicRegionFeatureHibernateDBAdaptor adaptor = new GenomicRegionFeatureHibernateDBAdaptor(this.getSessionFactory());
		GenomicRegionFeatures genomicRegionFeatures = adaptor.getByRegion(new Region(chromosome, start, end), type);
		
		System.out.println("Regulatory obtained: " + genomicRegionFeatures.getRegulatoryIds().size() + "  " + (System.currentTimeMillis()-t0)+" ms");
		System.out.println("type:  " + genomicRegionFeatures.getHistones().size());
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
			System.out.println("Subtype obtained: " + result.size() + "  " + (System.currentTimeMillis()-t0)+" ms");
			return result;
		}
	}
	
	@SuppressWarnings("unchecked")
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
	public List<List<RegulatoryRegion>> getAllByRegionList(List<Region> regionList) {
		return this.getAllByRegionList(regionList, null);
	}

	@Override
	public List<List<RegulatoryRegion>> getAllByRegionList(List<Region> regionList,List<String> type) {
		List<List<RegulatoryRegion>> result = new ArrayList<List<RegulatoryRegion>>();
		for (Region region : regionList) {
			result.add(this.getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd(), type));
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

	@Override
	public List<RegulatoryRegion> getAllByRegion(Region region, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConservedRegion> getAllConservedRegionByRegion(Region region) {
		
//		int chunk_size = applicationProperties.getIntProperty("CHUNK_SIZE", 400);
//		int start_chunk = region.getStart() / chunk_size;
//		int end_chunk = region.getEnd() / chunk_size;
		
		Query query = this.openSession().createQuery("select cr from ConservedRegion cr where cr.chromosome= :CHROMOSOME and cr.start> :START and cr.end < :END")
										.setParameter("CHROMOSOME", region.getChromosome())
										.setParameter("START", region.getStart())
										.setParameter("END", region.getEnd());
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


	


}

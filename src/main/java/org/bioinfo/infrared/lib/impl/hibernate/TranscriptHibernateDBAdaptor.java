package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.Transcript;
import org.bioinfo.infrared.lib.api.TranscriptDBAdaptor;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

class TranscriptHibernateDBAdaptor extends HibernateDBAdaptor implements TranscriptDBAdaptor {
	
	
	public TranscriptHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	

	@Override
	public Map<String, Object> getInfo(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> infoMap = new HashMap<String, Object>();
		
		Criteria criteria = this.openSession().createCriteria(Transcript.class).add(Restrictions.eq("stableId", id)).setFetchMode("exonToTranscript", FetchMode.JOIN);
		List<Exon> exons = (List<Exon>) executeAndClose(criteria);
		infoMap.put("snps", exons);
		
		
		criteria = this.openSession().createCriteria(Snp.class);
		List<Exon> exons= (List<Exon>) executeAndClose(criteria);
		infoMap.put("exons", exons);
		
		
//		criteria = this.openSession().createCriteria(Snp.class);
//		List<Snp> snps = (List<Snp>) executeAndClose(criteria);
//		infoMap.put("snps", snps);
		
		
		return infoMap;
	}

	@Override
	public Map<String, Object> getFullInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Map<String, Object>> getInfoByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Map<String, Object>> getFullInfoByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllIds() {
		Query query = this.openSession().createQuery("select t.stableId from Transcript t");
		return (List<String>) executeAndClose(query);
	}


	
	@SuppressWarnings("unchecked")
	@Override
	public List<Transcript> getAll() {
//		Criteria criteria = this.getSession().createCriteria(Gene.class);
//		return (List<Gene>) execute(criteria);
		Query query = this.openSession().createQuery("select t from Transcript t").setCacheable(true);
		return (List<Transcript>) executeAndClose(query);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllEnsemblIds() {
		Query query = this.openSession().createQuery("select t.stableId from Transcript t");
		return (List<String>) executeAndClose(query);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Transcript getByEnsemblId(String ensemblId) {
		Criteria criteria = this.openSession().createCriteria(Transcript.class);
		criteria.add(Restrictions.eq("stableId", ensemblId.trim()));
		List<Transcript> transcripts = (List<Transcript>) executeAndClose(criteria);
		if(transcripts != null && transcripts.size() > 0) {
			return transcripts.get(0);
		}else {
			return null;
		}
	}
	
	@Override
	public List<Transcript> getAllByEnsemblIdList(List<String> ensemblIds) {
		List<Transcript> genes = new ArrayList<Transcript>(ensemblIds.size());
		for(String ensemblId: ensemblIds) {
			genes.add(getByEnsemblId(ensemblId));
		}
		return genes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transcript> getAllById(String id) {
		Criteria criteria = this.openSession().createCriteria(Transcript.class);
		criteria.add(Restrictions.eq("stableId", id.trim()));
		return (List<Transcript>)executeAndClose(criteria);
	}
	

	@Override
	public List<List<Transcript>> getAllByIdList(List<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Transcript> getByEnsemblGeneId(String ensemblGeneId) {
		Criteria criteria = this.openSession().createCriteria(Transcript.class).createCriteria("transcripts").add(Restrictions.eq("stableId", ensemblGeneId.trim()));
		return (List<Transcript>) executeAndClose(criteria).get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<List<Transcript>> getByEnsemblGeneIdList(List<String> ensemblGeneIds) {
		Criteria criteria = this.openSession().createCriteria(Transcript.class).createCriteria("transcripts").add(Restrictions.in("stableId", ensemblGeneIds));
		return (List<List<Transcript>>) executeAndClose(criteria);
	}



	
	@SuppressWarnings("unchecked")
	@Override
	public List<Transcript> getAllByBiotype(String biotype) {
		Criteria criteria = this.openSession().createCriteria(Transcript.class);
		criteria.add(Restrictions.eq("biotype", biotype)).addOrder(Order.asc("chromosome")).addOrder(Order.asc("start"));
		return (List<Transcript>)executeAndClose(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transcript> getAllByBiotypeList(List<String> biotypeList) {
		Criteria criteria = this.openSession().createCriteria(Transcript.class);
		criteria.add(Restrictions.in("biotype", biotypeList));
		return (List<Transcript>)executeAndClose(criteria);
	}

	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Transcript> getAllByPosition(String chromosome, int position) {
		Criteria criteria =  this.openSession().createCriteria(Transcript.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add(Restrictions.ge("end", position)).add(Restrictions.le("start", position));
		return (List<Transcript>)executeAndClose(criteria);
	}


	@Override
	public List<Transcript> getAllByPosition(Position position) {
		if(position == null) {
			return null;
		}else {
			return getAllByPosition(position.getChromosome(), position.getPosition());	
		}
	}


	@Override
	public List<List<Transcript>> getAllByPositionList(List<Position> positions) {
		List<List<Transcript>> transcripts = new ArrayList<List<Transcript>>(positions.size());
		for(Position position: positions) {
			transcripts.add(getAllByPosition(position));
		}
		return transcripts;
	}

	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Transcript> getAllByRegion(String chromosome) {
		Criteria criteria =  this.openSession().createCriteria(Transcript.class);
		criteria.add(Restrictions.eq("chromosome", chromosome));
		return (List<Transcript>)executeAndClose(criteria);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Transcript> getAllByRegion(String chromosome, int start) {
		Criteria criteria =  this.openSession().createCriteria(Transcript.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add(Restrictions.ge("end", start));
		return (List<Transcript>)executeAndClose(criteria);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Transcript> getAllByRegion(String chromosome, int start, int end) {
		Criteria criteria =  this.openSession().createCriteria(Transcript.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add(Restrictions.ge("end", start)).add(Restrictions.le("start", end));
		return (List<Transcript>)executeAndClose(criteria);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Transcript> getAllByRegion(String chromosome, int start, int end,	List<String> biotypes) {
		Criteria criteria =  this.openSession().createCriteria(Transcript.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add(Restrictions.ge("end", start)).add(Restrictions.le("start", end)).add(Restrictions.in("biotype", biotypes));
		return (List<Transcript>)executeAndClose(criteria);
	}


	@Override
	public List<Transcript> getAllByRegion(Region region) {
		if(region == null) {
			return null;
		}else {
			return getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd());	
		}
	}


	@Override
	public List<Transcript> getAllByRegion(Region region, List<String> biotypes) {
		if(region == null) {
			return null;
		}else {
			return getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd(), biotypes);	
		}
	}


	@Override
	public List<List<Transcript>> getAllByRegionList(List<Region> regions) {
		List<List<Transcript>> genes = new ArrayList<List<Transcript>>(regions.size());
		for(Region region: regions) {
			genes.add(getAllByRegion(region));
		}
		return genes;
	}

	@Override
	public List<List<Transcript>> getAllByRegionList(List<Region> regions, List<String> biotypes) {
		// TODO Auto-generated method stub
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Transcript> getAllByCytoband(String chromosome, String cytoband) {
		Query query = this.openSession().createQuery("select t from Transcript t, Cytoband k where k.chromosome= :chromosome and k.cytoband = :cytoband and k.chromosome=g.chromosome and g.end>=k.start and g.start<=k.end").setParameter("chromosome", chromosome).setParameter("cytoband", cytoband);
		return (List<Transcript>)executeAndClose(query);
	}


	@Override
	public List<Transcript> getAllBySnpId(String snpId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Transcript>> getAllBySnpIdList(List<String> snpIds) {
		// TODO Auto-generated method stub
		return null;
	}



	
	@Override
	public Region getRegionById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<Region> getAllRegionsByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String getSequenceById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<String> getAllSequencesByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}



}

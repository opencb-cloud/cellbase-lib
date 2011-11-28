package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.Transcript;
import org.bioinfo.infrared.lib.api.TranscriptDBAdaptor;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

class TranscriptHibernateDBAdaptor extends HibernateDBAdaptor implements TranscriptDBAdaptor {
	
	
	public TranscriptHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Transcript> getAll() {
		Criteria criteria = this.openSession().createCriteria(Transcript.class);
		return (List<Transcript>) executeAndClose(criteria);
//		Query query = this.openSession().createQuery("select t from Transcript t").setCacheable(true);
//		return (List<Transcript>) executeAndClose(query);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<String> getAllIds() {
		Criteria criteria = this.openSession().createCriteria(Transcript.class);
		ProjectionList projections = Projections.projectionList();
		projections.add(Projections.property("stableId"));
		criteria.setProjection(projections);
		return (List<String>) executeAndClose(criteria);
	}

	
	@Override
	public Map<String, Object> getInfo(String id) {
		return null;
	}

	@Override
	public List<Map<String, Object>> getInfoByIdList(List<String> idList) {
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
	@SuppressWarnings("unchecked")
	public List<String> getAllEnsemblIds() {
		Criteria criteria = this.openSession().createCriteria(Transcript.class);
		ProjectionList projections = Projections.projectionList();
		projections.add(Projections.property("stableId"));
		criteria.setProjection(projections);
		return (List<String>) executeAndClose(criteria);
	}
	
	@Override
	@SuppressWarnings("unchecked")
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
		List<Transcript> transcripts = new ArrayList<Transcript>(ensemblIds.size());
		for(String ensemblId: ensemblIds) {
			transcripts.add(getByEnsemblId(ensemblId));
		}
		return transcripts;
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
		//TODO DONE
		List<List<Transcript>> transcripts =  new ArrayList<List<Transcript>>(ids.size());
		for(String id: ids){
			transcripts.add(getAllById(id));
		}
		return transcripts;
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
		List<List<Transcript>> transcripts = new ArrayList<List<Transcript>>(regions.size());
		for(Region region: regions) {
			transcripts.add(getAllByRegion(region));
		}
		return transcripts;
	}

	@Override
	public List<List<Transcript>> getAllByRegionList(List<Region> regions, List<String> biotypes) {
		// TODO DONE
		List<List<Transcript>> transcripts = new ArrayList<List<Transcript>>(regions.size());
		for(Region region: regions){
			transcripts.add(getAllByRegion(region, biotypes));
		}
		return transcripts;
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Transcript> getAllByCytoband(String chromosome, String cytoband) {
		Query query = this.openSession().createQuery("select t from Transcript t, Cytoband k where k.chromosome= :chromosome and k.cytoband = :cytoband and k.chromosome=g.chromosome and g.end>=k.start and g.start<=k.end").setParameter("chromosome", chromosome).setParameter("cytoband", cytoband);
		return (List<Transcript>)executeAndClose(query);
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Transcript> getAllBySnpId(String snpNameId) {
		// TODO DONE
		Query query = this.openSession().createQuery("select transcript from Transcript as transcript  left join fetch transcript.snpToTranscripts as stt left join fetch stt.snp as snp where snp.name = :snpName").setParameter("snpName",snpNameId);
		return (List<Transcript>)executeAndClose(query);
	}

	@Override
	public List<List<Transcript>> getAllBySnpIdList(List<String> snpNameIds) {
		// TODO DONE
		List<List<Transcript>> transcripts = new ArrayList<List<Transcript>>(snpNameIds.size());
		for(String snpNameId: snpNameIds) {
			transcripts.add(getAllBySnpId(snpNameId));
		}
		return transcripts;
	}


	@Override
	public Region getRegionById(String id) {
		// TODO DOING
		return null;
	}
	
	
	
	@Override
	public List<Region> getAllRegionsByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String getSequenceById(String id) {
		// TODO DOING
//		select x2.* from xref x1, xref x2, transcript_to_xref tx1, transcript_to_xref tx2, dbname db where 
//		x1.display_id='ENST00000493561' and 
//		x1.xref_id=tx1.xref_id and 
//		tx1.transcript_id=tx2.transcript_id and 
//		tx2.xref_id=x2.xref_id and 
//		x2.dbname_id=db.dbname_id and 
//		db.name='ensembl_transcript';

//	cruzada 	
//		select t.* from xref x1, xref x2, transcript_to_xref tx1, transcript_to_xref tx2, dbname db, transcript t 
//		where x1.display_id='ENST00000493561' and 
//		x1.xref_id=tx1.xref_id and 
//		tx1.transcript_id=tx2.transcript_id and 
//		tx2.xref_id=x2.xref_id and 
//		x2.dbname_id=db.dbname_id and 
//		db.name='ensembl_transcript' and 
//		x2.display_id=t.stable_id;
		Query query = this.openSession().createQuery("select x2.displayId from Xref as x1, Xref as x2, TranscriptToXref as tx1, TranscriptToXref tx2, Dbname as db where" +
													 " x1.displayId= :id and" +
													 " x1.xrefId=tx1.xref and" +
													 " tx1.transcript=tx2.transcript and" +
													 " tx2.xref=x2.xrefId and" +
													 " x2.dbname=db.dbnameId and" +
													 " db.name='ensembl_transcript'").setParameter("id",id);
		return (String)executeAndClose(query).get(0);
	}

	
	@Override
	public List<String> getAllSequencesByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}



}

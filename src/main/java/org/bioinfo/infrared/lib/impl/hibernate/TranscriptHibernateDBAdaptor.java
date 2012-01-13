package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.Transcript;
import org.bioinfo.infrared.lib.api.TranscriptDBAdaptor;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
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
//		Criteria criteria = this.openSession().createCriteria(Transcript.class)
//		.createCriteria("gene")
		
		String HQLquery = "select t from Transcript t left join fetch t.gene"; 
		Query query = this.openSession().createQuery(HQLquery);
		return (List<Transcript>) executeAndClose(query);
//		Query query = this.openSession().createQuery("select t from Transcript t").setCacheable(true);
//		return (List<Transcript>) executeAndClose(query);
	}
	
	@Override
	public List<String> getAllIds() {
		return this.getAllEnsemblIds();
	}

	/*********************No se implementan todavia*/
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
	public Map<String, Object> getFullInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getFullInfoByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}
	/**********************/
	
	@Override
	@SuppressWarnings("unchecked")
	public List<String> getAllEnsemblIds() {
		Criteria criteria = this.openSession().createCriteria(Transcript.class);
		ProjectionList projections = Projections.projectionList();
		projections.add(Projections.property("stableId"));
		criteria.setProjection(projections);
		return (List<String>) executeAndClose(criteria);
	}
	

	
	/****/
	@Override
	public Transcript getByEnsemblId(String ensemblId) {
		Session session = this.openSession();
		Transcript transcript =  this.getByEnsemblId(ensemblId, session);
		session.close();
		return transcript;		
	}
	@SuppressWarnings("unchecked")
	private Transcript getByEnsemblId(String ensemblId, Session session) {
		Criteria criteria = session.createCriteria(Transcript.class);
		criteria.add(Restrictions.eq("stableId", ensemblId.trim()));
		List<Transcript> transcripts = (List<Transcript>) criteria.list();
		
		if(transcripts != null && transcripts.size() > 0) {
			return transcripts.get(0);
		}else {
			return null;
		}
	}
	@Override
	public List<Transcript> getAllByEnsemblIdList(List<String> ensemblIds) {
		Session session = this.openSession();
		List<Transcript> transcripts = new ArrayList<Transcript>(ensemblIds.size());
		for(String ensemblId: ensemblIds) {
			transcripts.add(this.getByEnsemblId(ensemblId, session));
		}
		session.close();
		return transcripts;
	}
	/****/
	
	@Override
	public List<Transcript> getAllByName(String name) {
		Session session = this.openSession();
		List<Transcript> transcripts = this.getAllByName(name, session);
		session.close();
		return transcripts;
	}
	
	@SuppressWarnings("unchecked")
	private List<Transcript> getAllByName(String name, Session session) {
		
		/**WARNING Xref no estan rellenos para todas las especies **/
		/*Query query = session.createQuery("select t from Xref as x1, Xref as x2, TranscriptToXref as tx1, TranscriptToXref as tx2, Dbname as db, Transcript as t where" +
			 " x1.displayId= :name and" +
			 " x1.xrefId=tx1.xref and" +
			 " tx1.transcript=tx2.transcript and" +
			 " tx2.xref=x2.xrefId and" +
			 " x2.dbname=db.dbnameId and" +
			 " db.name='ensembl_transcript' and" +
			 " x2.displayId=t.stableId").setParameter("name",name.trim());
		return (List<Transcript>)query.list();*/
		

		Criterion stable = Restrictions.eq("stableId", name);
	    Criterion nameCrit = Restrictions.eq("externalName", name);
		LogicalExpression orExp = Restrictions.or(stable,nameCrit);
		
		Criteria criteria = this.openSession().createCriteria(Transcript.class)
		.addOrder(Order.asc("chromosome"))
		.addOrder(Order.asc("start"))
		.createCriteria("gene").add(orExp);
		 
		 return (List<Transcript>)criteria.list();
	}

	@Override
	public List<List<Transcript>> getAllByNameList(List<String> names) {
		Session session = this.openSession();
		List<List<Transcript>> transcripts =  new ArrayList<List<Transcript>>(names.size());
		for(String name: names){
			transcripts.add(this.getAllByName(name,session));
		}
		session.close();
		return transcripts;
	}

	@Override
	public List<Transcript> getByEnsemblGeneId(String ensemblGeneId) {
		Session session = this.openSession();
		List<Transcript> transcripts = this.getByEnsemblGeneId(ensemblGeneId, session);
		session.close();
		return transcripts;
	}

	@SuppressWarnings("unchecked")
	private List<Transcript> getByEnsemblGeneId(String ensemblGeneId, Session session) {
		Criteria criteria = session
		.createCriteria(Transcript.class)
		.addOrder(Order.asc("start"))
		.createCriteria("gene")
		.add(Restrictions.eq("stableId", ensemblGeneId.trim()));
		
	
		return (List<Transcript>) criteria.list();
	}
	
	
	@Override
	public List<List<Transcript>> getByEnsemblGeneIdList(List<String> ensemblGeneIds) {
		
		Session session = this.openSession();
		List<List<Transcript>> transcripts =  new ArrayList<List<Transcript>>(ensemblGeneIds.size());
		for(String ensemblGeneId: ensemblGeneIds){
			transcripts.add(this.getAllByName(ensemblGeneId,session));
		}
		session.close();
		return transcripts;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Transcript> getAllByBiotype(String biotype) {
		Criteria criteria = this.openSession().createCriteria(Transcript.class);
		criteria.add(Restrictions.eq("biotype", biotype))
		.addOrder(Order.asc("chromosome"))
		.addOrder(Order.asc("start"));
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
			return getAllByPosition(position.getChromosome(), position.getPosition());	
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
		List<List<Transcript>> transcripts = new ArrayList<List<Transcript>>(regions.size());
		for(Region region: regions){
			transcripts.add(getAllByRegion(region, biotypes));
		}
		return transcripts;
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Transcript> getAllByCytoband(String chromosome, String cytoband) {
		Query query = this.openSession().createQuery("select t from Transcript t, Cytoband k where k.chromosome= :chromosome and k.cytoband = :cytoband and k.chromosome=t.chromosome and t.end>=k.start and t.start<=k.end").setParameter("chromosome", chromosome).setParameter("cytoband", cytoband);
		return (List<Transcript>)executeAndClose(query);
	}

	/****/
	@Override
	public List<Transcript> getAllBySnpId(String snpNameId) {
		Session session = this.openSession();
		List<Transcript> list =  this.getAllBySnpId(snpNameId, session);
		session.close();
		return list;
	}
	@SuppressWarnings("unchecked")
	private List<Transcript> getAllBySnpId(String snpNameId, Session session) {
		Criteria criteria = session
				.createCriteria(Transcript.class)
				.createCriteria("snpToTranscripts")
				.createCriteria("snp").add(Restrictions.eq("name", snpNameId));
		return (List<Transcript>)criteria.list();
	}
	@Override
	public List<List<Transcript>> getAllBySnpIdList(List<String> snpNameIds) {
		Session session = this.openSession();
		List<List<Transcript>> transcripts = new ArrayList<List<Transcript>>(snpNameIds.size());
		for(String id: snpNameIds) {
			transcripts.add(getAllBySnpId(id,session));
		}
		session.close();
		return transcripts;
	}
	/****/
	
	@Override
	public Region getRegionById(String ensemblId) {
		Transcript transcript = this.getByEnsemblId(ensemblId);
		return new Region(transcript.getChromosome(),transcript.getStart(),transcript.getEnd());
	}
	
	@Override
	public List<Region> getAllRegionsByIdList(List<String> ensemblIdList) {
		List<Region> regions = new ArrayList<Region>(ensemblIdList.size());
		List<Transcript> transcripts = getAllByEnsemblIdList(ensemblIdList);
		for(Transcript transcript: transcripts) {
			regions.add(new Region(transcript.getChromosome(),transcript.getStart(),transcript.getEnd()));
		}
		return regions;
	}

	
	@Override
	public String getSequenceById(String ensemblId) {
		Transcript transcript = this.getByEnsemblId(ensemblId);
		GenomeSequenceDBAdaptor da = new GenomeSequenceDBAdaptor(this.getSessionFactory());
		return da.getByRegion(transcript.getChromosome(),transcript.getStart(),transcript.getEnd()).getSequence();
	}

	
	@Override
	public List<String> getAllSequencesByIdList(List<String> ensemblIdList) {
		List<String> sequence = new ArrayList<String>(ensemblIdList.size());
		List<Transcript> transcripts = getAllByEnsemblIdList(ensemblIdList);
		GenomeSequenceDBAdaptor da = new GenomeSequenceDBAdaptor(this.getSessionFactory());
		for(Transcript transcript: transcripts) {
			sequence.add(da.getByRegion(transcript.getChromosome(),transcript.getStart(),transcript.getEnd()).getSequence());
		}
		return sequence;
	}
	
	
	@Override
	public List<Transcript> getAllByEnsemblExonId(String ensemblExonId) {
		Criteria criteria =  this.openSession().createCriteria(Transcript.class)
		.createCriteria("exonToTranscripts")
		.createCriteria("exon")
		.add(Restrictions.eq("stableId", ensemblExonId));
		return (List<Transcript>) executeAndClose(criteria);
	}
	
	@Override
	public List<List<Transcript>> getAllByEnsemblExonId(List<String> ensemblExonId) {
		List<List<Transcript>> result = new ArrayList<List<Transcript>>();
		for (String id : ensemblExonId) {
			result.add(this.getAllByEnsemblExonId(id));
		}
		return result;
	}
	
	
}

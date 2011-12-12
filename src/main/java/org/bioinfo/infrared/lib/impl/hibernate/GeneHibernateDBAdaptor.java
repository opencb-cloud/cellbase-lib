package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

class GeneHibernateDBAdaptor extends HibernateDBAdaptor implements GeneDBAdaptor {


	public GeneHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<Gene> getAll() {
//		Criteria criteria = this.getSession().createCriteria(Gene.class);
//		return (List<Gene>) execute(criteria);
		Query query = this.openSession().createQuery("select g from Gene g").setCacheable(true);
		return (List<Gene>) executeAndClose(query);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getAllIds() {
		Query query = this.openSession().createQuery("select g.stableId from Gene g");
		return (List<String>) executeAndClose(query);
	}

	
	
	
	@Override
	public Map<String, Object> getInfo(String id) {
		Query query = this.openSession().createQuery("select g from Gene g");
		@SuppressWarnings("unchecked")
		List<Gene> genes =  (List<Gene>)query.list();
		
		System.out.println(genes.toString());
//		System.out.println(gene.getTranscripts());
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
		Query query = this.openSession().createQuery("select g.stableId from Gene g");
		return (List<String>) executeAndClose(query);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Gene getByEnsemblId(String ensemblId) {
		Criteria criteria = this.openSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("stableId", ensemblId.trim()));
		List<Gene> genes = (List<Gene>) executeAndClose(criteria);
		if(genes != null && genes.size() > 0) {
			return genes.get(0);
		}else {
			return null;
		}
	}
	
	@Override
	public List<Gene> getAllByEnsemblIdList(List<String> ensemblIds) {
		List<Gene> result = new ArrayList<Gene>();
		for (String id : ensemblIds) {
			result.add(this.getByEnsemblId(id));
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByName(String name) {
		Criteria criteria = this.openSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("stableId", name.trim()));
		return (List<Gene>)executeAndClose(criteria);
	}
	

	@Override
	public List<List<Gene>> getAllByNameList(List<String> names) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public Gene getByEnsemblTranscriptId(String transcriptId) {
		Criteria criteria = this.openSession().createCriteria(Gene.class).createCriteria("transcripts").add(Restrictions.eq("stableId", transcriptId.trim()));
		return (Gene) executeAndClose(criteria).get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByEnsemblTranscriptIdList(List<String> transcriptIdList) {
		Criteria criteria = this.openSession().createCriteria(Gene.class)
		.createCriteria("transcripts").add(Restrictions.in("stableId", transcriptIdList));
		return (List<Gene>) executeAndClose(criteria);
	}



	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByBiotype(String biotype) {
		Criteria criteria = this.openSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("biotype", biotype)).addOrder(Order.asc("chromosome")).addOrder(Order.asc("start"));
		return (List<Gene>)executeAndClose(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByBiotypeList(List<String> biotypeList) {
		Criteria criteria = this.openSession().createCriteria(Gene.class);
		criteria.add(Restrictions.in("biotype", biotypeList));
		return (List<Gene>)executeAndClose(criteria);
	}

	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByPosition(String chromosome, int position) {
		Criteria criteria =  this.openSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add(Restrictions.ge("end", position)).add(Restrictions.le("start", position));
		return (List<Gene>)executeAndClose(criteria);
	}


	@Override
	public List<Gene> getAllByPosition(Position position) {
		if(position == null) {
			return null;
		}else {
			return getAllByPosition(position.getChromosome(), position.getPosition());	
		}
	}


	@Override
	public List<List<Gene>> getAllByPositionList(List<Position> positionList) {
		List<List<Gene>> genes = new ArrayList<List<Gene>>(positionList.size());
		for(Position position: positionList) {
			genes.add(getAllByPosition(position));
		}
		return genes;
	}

	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByRegion(String chromosome) {
		Criteria criteria =  this.openSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("chromosome", chromosome));
		return (List<Gene>)executeAndClose(criteria);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByRegion(String chromosome, int start) {
		Criteria criteria =  this.openSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add(Restrictions.ge("end", start));
		return (List<Gene>)executeAndClose(criteria);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByRegion(String chromosome, int start, int end) {
		Criteria criteria =  this.openSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add(Restrictions.ge("end", start)).add(Restrictions.le("start", end));
		return (List<Gene>)executeAndClose(criteria);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByRegion(String chromosome, int start, int end,	List<String> biotypes) {
		Criteria criteria =  this.openSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add(Restrictions.ge("end", start)).add(Restrictions.le("start", end)).add(Restrictions.in("biotype", biotypes));
		return (List<Gene>)executeAndClose(criteria);
	}


	@Override
	public List<Gene> getAllByRegion(Region region) {
		if(region == null) {
			return null;
		}else {
			return getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd());	
		}
	}


	@Override
	public List<Gene> getAllByRegion(Region region, List<String> biotypes) {
		if(region == null) {
			return null;
		}else {
			return getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd(), biotypes);	
		}
	}


	@Override
	public List<List<Gene>> getAllByRegionList(List<Region> regions) {
		List<List<Gene>> genes = new ArrayList<List<Gene>>(regions.size());
		for(Region region: regions) {
			genes.add(getAllByRegion(region));
		}
		return genes;
	}

	@Override
	public List<List<Gene>> getAllByRegionList(List<Region> regions, List<String> biotypes) {
		// TODO Auto-generated method stub
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByCytoband(String chromosome, String cytoband) {
		//		Criteria criteria =  this.getSession().createCriteria(Gene.class, "g").createCriteria("cytoband", "k");
		//		criteria.add(Restrictions.eq("k.cytoband", cytoband)).add(Restrictions.ge("g.end", "k.start")).add(Restrictions.le("g.start", "k.end"));
		Query query = this.openSession().createQuery("select g from Gene g, Cytoband k where k.chromosome= :chromosome and k.cytoband = :cytoband and k.chromosome=g.chromosome and g.end>=k.start and g.start<=k.end").setParameter("chromosome", chromosome).setParameter("cytoband", cytoband);
		return (List<Gene>)executeAndClose(query);
	}


	
	
	@Override
	public List<Gene> getAllBySnpId(String snpId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Gene>> getAllBySnpIdList(List<String> snpIds) {
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



	//		Criteria criteria =  this.getSession().createCriteria(Gene.class);
	//		Disjunction disjunction = Restrictions.disjunction();
	//		for (Region region : regions) {
	//			Conjunction disjunctionRegion = Restrictions.conjunction();
	//			disjunctionRegion.add(Restrictions.eq("chromosome", region.getChromosome())).add( Restrictions.ge("start", region.getStart())).add(Restrictions.le("end", region.getEnd()));
	//			disjunction.add(disjunctionRegion);
	//		}
	//		criteria.add(disjunction);
	//		return  execute(criteria);

}

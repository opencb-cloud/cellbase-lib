package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.Gene;
import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

class GeneHibernateDBAdaptor extends HibernateDBAdaptor implements GeneDBAdaptor {


	public GeneHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllIds() {
		Query query = this.getSession().createQuery("select g.stableId from Gene g");
		return (List<String>) execute(query);
	}


	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAll() {
//		Criteria criteria = this.getSession().createCriteria(Gene.class);
//		return (List<Gene>) execute(criteria);
		Query query = this.getSession().createQuery("select g from Gene g").setCacheable(true);
		return (List<Gene>) execute(query);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllEnsemblIds() {
		Query query = this.getSession().createQuery("select g.stableId from Gene g");
		return (List<String>) execute(query);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Gene getByEnsemblId(String ensemblId) {
		Criteria criteria = this.getSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("stableId", ensemblId.trim()));
		List<Gene> genes = (List<Gene>) execute(criteria);
		if(genes != null && genes.size() > 0) {
			return genes.get(0);
		}else {
			return null;
		}
	}
	
	@Override
	public List<Gene> getAllByEnsemblIdList(List<String> ensemblIds) {
		List<Gene> genes = new ArrayList<Gene>(ensemblIds.size());
		for(String ensemblId: ensemblIds) {
			genes.add(getByEnsemblId(ensemblId));
		}
		return genes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllById(String id) {
		Criteria criteria = this.getSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("stableId", id.trim()));
		return (List<Gene>)execute(criteria);
	}
	

	@Override
	public List<List<Gene>> getAllByIdList(List<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Gene getByEnsemblTranscriptId(String transcriptId) {
		Criteria criteria = this.getSession().createCriteria(Gene.class).createCriteria("transcripts").add(Restrictions.eq("stableId", transcriptId.trim()));
		return (Gene) execute(criteria).get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getByEnsemblTranscriptIdList(List<String> transcriptIdList) {
		Criteria criteria = this.getSession().createCriteria(Gene.class).createCriteria("transcripts").add(Restrictions.in("stableId", transcriptIdList));
		return (List<Gene>) execute(criteria);
	}



	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByBiotype(String biotype) {
		Criteria criteria = this.getSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("biotype", biotype)).addOrder(Order.asc("chromosome")).addOrder(Order.asc("start"));
		return (List<Gene>)execute(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByBiotypeList(List<String> biotypeList) {
		Criteria criteria = this.getSession().createCriteria(Gene.class);
		criteria.add(Restrictions.in("biotype", biotypeList));
		return (List<Gene>)execute(criteria);
	}

	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByPosition(String chromosome, int position) {
		Criteria criteria =  this.getSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add(Restrictions.ge("end", position)).add(Restrictions.le("start", position));
		return (List<Gene>)execute(criteria);
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
		Criteria criteria =  this.getSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("chromosome", chromosome));
		return (List<Gene>)execute(criteria);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByRegion(String chromosome, int start) {
		Criteria criteria =  this.getSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add(Restrictions.ge("end", start));
		return (List<Gene>)execute(criteria);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByRegion(String chromosome, int start, int end) {
		Criteria criteria =  this.getSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add(Restrictions.ge("end", start)).add(Restrictions.le("start", end));
		return (List<Gene>)execute(criteria);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByRegion(String chromosome, int start, int end,	List<String> biotypes) {
		Criteria criteria =  this.getSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add(Restrictions.ge("end", start)).add(Restrictions.le("start", end)).add(Restrictions.in("biotype", biotypes));
		return (List<Gene>)execute(criteria);
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
		Query query = this.getSession().createQuery("select g from Gene g, Cytoband k where k.chromosome= :chromosome and k.cytoband = :cytoband and k.chromosome=g.chromosome and g.end>=k.start and g.start<=k.end").setParameter("chromosome", chromosome).setParameter("cytoband", cytoband);
		return (List<Gene>)execute(query);
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

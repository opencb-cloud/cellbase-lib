package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.Tfbs;
import org.bioinfo.infrared.core.cellbase.Xref;
import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
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
		Criterion ensemblId = Restrictions.eq("stableId", name.trim());
		Criterion nameCriterio = Restrictions.eq("externalName", name.trim());
		LogicalExpression log = Restrictions.or(ensemblId, nameCriterio);
		criteria.addOrder(Order.asc("chromosome"));
		criteria.addOrder(Order.asc("start"));
		criteria.add(log);
		return (List<Gene>)executeAndClose(criteria);
	}
	

	@Override
	public List<List<Gene>> getAllByNameList(List<String> names) {
		List<List<Gene>> genes = new ArrayList<List<Gene>>();
		for (String name : names) {
			genes.add(this.getAllByName(name));
		}
		return genes;
	}
	
	
	@Override
	public Gene getByEnsemblTranscriptId(String transcriptId) {
		Criteria criteria = this.openSession().createCriteria(Gene.class)
		.createCriteria("transcripts")
		.add(Restrictions.eq("stableId", transcriptId.trim()))
		.addOrder(Order.asc("chromosome"))
		.addOrder(Order.asc("start"));
		return (Gene) executeAndClose(criteria).get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByEnsemblTranscriptIdList(List<String> transcriptIdList) {
		Criteria criteria = this.openSession().createCriteria(Gene.class)
		.createCriteria("transcripts")
		.add(Restrictions.in("stableId", transcriptIdList))
		.addOrder(Order.asc("chromosome"))
		.addOrder(Order.asc("start"));
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
		criteria.add(Restrictions.eq("chromosome", chromosome))
		.add(Restrictions.ge("end", position))
		.add(Restrictions.le("start", position))
		.addOrder(Order.asc("chromosome"))
		.addOrder(Order.asc("start"));
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
		criteria.add(Restrictions.eq("chromosome", chromosome))
		.addOrder(Order.asc("chromosome"))
		.addOrder(Order.asc("start"));
		return (List<Gene>)executeAndClose(criteria);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByRegion(String chromosome, int start) {
		Criteria criteria =  this.openSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("chromosome", chromosome))
		.add(Restrictions.ge("end", start))
		.addOrder(Order.asc("chromosome"))
		.addOrder(Order.asc("start"));
		return (List<Gene>)executeAndClose(criteria);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByRegion(String chromosome, int start, int end) {
		Criteria criteria =  this.openSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("chromosome", chromosome))
		.add(Restrictions.ge("end", start))
		.add(Restrictions.le("start", end))
		.addOrder(Order.asc("chromosome"))
		.addOrder(Order.asc("start"));
		return (List<Gene>)executeAndClose(criteria);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByRegion(String chromosome, int start, int end,	List<String> biotypes) {
		Criteria criteria =  this.openSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("chromosome", chromosome))
		.add(Restrictions.ge("end", start))
		.add(Restrictions.le("start", end))
		.add(Restrictions.in("biotype", biotypes))
		.addOrder(Order.asc("chromosome"))
		.addOrder(Order.asc("start"));
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


	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByTf(String idTf) {
		TfbsHibernateDBAdaptor tfbsAdaptor = new TfbsHibernateDBAdaptor(this.getSessionFactory());
		List<Tfbs> result = tfbsAdaptor.getAllByTfGeneName(idTf);
		HashSet<String> keys = new HashSet<String>();
		
		for (Tfbs tfbs : result) {
//			if (!keys.contains(tfbs.getGeneByTfGeneId().getStableId())){
//				keys.add(tfbs.getGeneByTfGeneId().getStableId());
//			}
//			
			if (!keys.contains(tfbs.getGeneByTargetGeneId().getStableId())){
				keys.add(tfbs.getGeneByTargetGeneId().getStableId());
			}
		}
		
		Criteria criteria = this.openSession().createCriteria(Gene.class)
		.add(Restrictions.in("stableId", keys.toArray()));
		return (List<Gene>) executeAndClose(criteria);
	}
	
	@Override
	public List<List<Gene>> getAllByTfList(List<String> idList) {
		List<List<Gene>> result = new ArrayList<List<Gene>>();
		for (String string : idList) {
			result.add(this.getAllByTf(string));
		}
		return result;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gene> getAllByMiRna(String mirbaseId) {
		Criteria criteria = this.openSession().createCriteria(Gene.class)
		.createCriteria("mirnaTargets")
		.add(Restrictions.eq("mirbaseId", mirbaseId));
		return (List<Gene>) executeAndClose(criteria);
	}
	
	@Override
	public List<List<Gene>> getAllByMiRnaList(List<String> mirbaseIds) {
		List<List<Gene>> result = new ArrayList<List<Gene>>(mirbaseIds.size());
		for(String string: mirbaseIds) {
			result.add(this.getAllByMiRna(string));
		}
		return result;
	}


	@Override
	public List<Gene> getAllByXref(String xrefName) {
		XRefsHibernateDBAdaptor xrefsAdaptor = new XRefsHibernateDBAdaptor(this.getSessionFactory());
		List<Xref> xrefs = xrefsAdaptor.getByDBNameList(xrefName, Arrays.asList("ensembl_gene"));
		
		List<String> ensemblId = new ArrayList<String>();
		for (Xref xref : xrefs) {
			ensemblId.add(xref.getDisplayId());
		}
		
		return this.getAllByEnsemblIdList(ensemblId);
	}
	

}

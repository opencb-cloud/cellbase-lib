package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.lib.api.ExonDBAdaptor;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;


public class ExonHibernateDBAdaptor extends HibernateDBAdaptor implements ExonDBAdaptor {

	
	public ExonHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	/** BY GENE **/
//	@SuppressWarnings("unchecked")
//	public List<Exon> getByGeneId(String geneId){
//		Criteria criteria =  this.openSession().createCriteria(Exon.class).setFetchMode("exon2transcripts", FetchMode.SELECT)
//				.createCriteria("exon2transcripts").setFetchMode("transcript", FetchMode.SELECT)
//				.createCriteria("transcript").setFetchMode("gene", FetchMode.SELECT)
//				.createCriteria("gene").add( Restrictions.eq("stableId", geneId.trim()));
//		return (List<Exon>)execute(criteria);
//	}
//
//
//	public List<List<Exon>> getByGeneIdList(List<String> geneIds) {
//		List<List<Exon>> result = new ArrayList<List<Exon>>(geneIds.size());
//		for (String id: geneIds) {
//			result.add(getByGeneId(id));
//		}
//		return result;
//	}

	/** BY TRANSCRIPT **/
//	@SuppressWarnings("unchecked")
//	public List<Exon> getBytranscriptId(String transcriptId){
//		Criteria criteria =  this.openSession().createCriteria(Exon.class)
//				.createCriteria("exon2transcripts")
//				.createCriteria("transcript").add( Restrictions.eq("stableId", transcriptId.trim()));
//		return (List<Exon>)execute(criteria);
//	}
//
//	public List<List<Exon>> getByTranscriptIdList(List<String> transcriptIds) {
//		List<List<Exon>> result = new ArrayList<List<Exon>>(transcriptIds.size());
//		for (String id: transcriptIds) {
//			result.add(getBytranscriptId(id));
//		}
//		return result;
//	}

	/** BY REGION **/
//	@SuppressWarnings("unchecked")
//	public List<Exon> getExonByRegion(String chromosome, int start, int end){
//		Criteria criteria =  this.openSession().createCriteria(Exon.class);
//		criteria.add(Restrictions.eq("chromosome", chromosome)).add( Restrictions.ge("start", start)).add(Restrictions.le("end", end));
//		return  (List<Exon>)execute(criteria);
//	}
//
//	public List<List<Exon>> getExonByRegionList(String chregionId){
//		List<Region> regions = Region.parseRegions(chregionId);
//		List<List<Exon>> result = new ArrayList<List<Exon>>(regions.size());
//		for (Region region : regions) {
//			result.add(getExonByRegion(region.getChromosome(), region.getStart(), region.getEnd()));
//		}
//		return result;
//	}


	@Override
	public Map<String, Object> getInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Map<String, Object> getFullInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exon> getAll() {
		Query query = this.openSession().createQuery("select e from Exon e").setCacheable(true);
		return (List<Exon>) executeAndClose(query);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllIds() {
		Query query = this.openSession().createQuery("select e.stableId from Exon e");
		return (List<String>) executeAndClose(query);
	}




	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllEnsemblIds() {
		Query query = this.openSession().createQuery("select e.stableId from Exon e");
		return (List<String>) executeAndClose(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Exon getByEnsemblId(String ensemblId) {
		Criteria criteria = this.openSession().createCriteria(Exon.class);
		criteria.add(Restrictions.eq("stableId", ensemblId.trim()));
		List<Exon> exons = (List<Exon>) executeAndClose(criteria);
		if(exons != null && exons.size() > 0) {
			return exons.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<Exon> getAllByEnsemblIdList(List<String> ensemblIds) {
		List<Exon> exons = new ArrayList<Exon>(ensemblIds.size());
		for(String ensemblId: ensemblIds) {
			exons.add(getByEnsemblId(ensemblId));
		}
		return exons;
	}

	@SuppressWarnings("unchecked")
	//@Override
	public List<Exon> getAllById(String id) {
		Criteria criteria = this.openSession().createCriteria(Exon.class);
		criteria.add(Restrictions.eq("stableId", id.trim()));
		return (List<Exon>)executeAndClose(criteria);
	}


	//@Override
	public List<List<Exon>> getAllByIdList(List<String> ids) {
		List<List<Exon>> exonList = new ArrayList<List<Exon>>(ids.size());
		for(String id: ids) {
			exonList.add(getAllById(id));
		}
		return exonList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exon> getByEnsemblTranscriptId(String transcriptId) {
//		Criteria criteria = this.openSession().createCriteria(Exon.class).createCriteria("transcripts").add(Restrictions.eq("stableId", transcriptId.trim()));
//		return (List<Exon>) execute(criteria).get(0);
		Criteria criteria =  this.openSession().createCriteria(Exon.class)
				.createCriteria("exon2transcripts")
				.createCriteria("transcript").add( Restrictions.eq("stableId", transcriptId.trim()));
		return (List<Exon>)executeAndClose(criteria);
	}

	@Override
	public List<List<Exon>> getByEnsemblTranscriptIdList(List<String> transcriptIds) {
//		Criteria criteria = this.openSession().createCriteria(Exon.class).createCriteria("transcripts").add(Restrictions.in("stableId", transcriptIds));
//		return (List<List<Exon>>) execute(criteria);
		List<List<Exon>> exonsList = new ArrayList<List<Exon>>(transcriptIds.size());
		for(String id: transcriptIds) {
			exonsList.add(getByEnsemblTranscriptId(id));
		}
		return exonsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exon> getByEnsemblGeneId(String geneId) {
		Criteria criteria =  this.openSession().createCriteria(Exon.class).setFetchMode("exon2transcripts", FetchMode.SELECT)
				.createCriteria("exon2transcripts").setFetchMode("transcript", FetchMode.SELECT)
				.createCriteria("transcript").setFetchMode("gene", FetchMode.SELECT)
				.createCriteria("gene").add( Restrictions.eq("stableId", geneId.trim()));
		return (List<Exon>)executeAndClose(criteria);
	}


	@Override
	public List<List<Exon>> getByEnsemblGeneIdList(List<String> geneIds) {
		List<List<Exon>> exonsList = new ArrayList<List<Exon>>(geneIds.size());
		for (String id: geneIds) {
			exonsList.add(getByEnsemblGeneId(id));
		}
		return exonsList;
	}



	//	@SuppressWarnings("unchecked")
	//	@Override
	//	public List<Exon> getAllByBiotype(String biotype) {
	//		Criteria criteria = this.openSession().createCriteria(Exon.class);
	//		criteria.add(Restrictions.eq("biotype", biotype)).addOrder(Order.asc("chromosome")).addOrder(Order.asc("start"));
	//		return (List<Exon>)execute(criteria);
	//	}
	//
	//	@SuppressWarnings("unchecked")
	//	@Override
	//	public List<Exon> getAllByBiotypeList(List<String> biotypeList) {
	//		Criteria criteria = this.openSession().createCriteria(Exon.class);
	//		criteria.add(Restrictions.in("biotype", biotypeList));
	//		return (List<Exon>)execute(criteria);
	//	}




	@SuppressWarnings("unchecked")
	@Override
	public List<Exon> getAllByPosition(String chromosome, int position) {
		Criteria criteria =  this.openSession().createCriteria(Exon.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add(Restrictions.ge("end", position)).add(Restrictions.le("start", position));
		return (List<Exon>)executeAndClose(criteria);
	}


	@Override
	public List<Exon> getAllByPosition(Position position) {
		if(position == null) {
			return null;
		}else {
			return getAllByPosition(position.getChromosome(), position.getPosition());	
		}
	}


	@Override
	public List<List<Exon>> getAllByPositionList(List<Position> positions) {
		List<List<Exon>> genes = new ArrayList<List<Exon>>(positions.size());
		for(Position position: positions) {
			genes.add(getAllByPosition(position));
		}
		return genes;
	}




	@SuppressWarnings("unchecked")
	@Override
	public List<Exon> getAllByRegion(String chromosome) {
		Criteria criteria =  this.openSession().createCriteria(Exon.class);
		criteria.add(Restrictions.eq("chromosome", chromosome));
		return (List<Exon>)executeAndClose(criteria);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Exon> getAllByRegion(String chromosome, int start) {
		Criteria criteria =  this.openSession().createCriteria(Exon.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add(Restrictions.ge("end", start));
		return (List<Exon>)executeAndClose(criteria);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Exon> getAllByRegion(String chromosome, int start, int end) {
		Criteria criteria =  this.openSession().createCriteria(Exon.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add(Restrictions.ge("end", start)).add(Restrictions.le("start", end));
		return (List<Exon>)executeAndClose(criteria);
	}


	//	@SuppressWarnings("unchecked")
	//	@Override
	//	public List<Exon> getAllByRegion(String chromosome, int start, int end,	List<String> biotypes) {
	//		Criteria criteria =  this.openSession().createCriteria(Exon.class);
	//		criteria.add(Restrictions.eq("chromosome", chromosome)).add(Restrictions.ge("end", start)).add(Restrictions.le("start", end)).add(Restrictions.in("biotype", biotypes));
	//		return (List<Exon>)execute(criteria);
	//	}


	@Override
	public List<Exon> getAllByRegion(Region region) {
		if(region == null) {
			return null;
		}else {
			return getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd());	
		}
	}


	//	@Override
	//	public List<Exon> getAllByRegion(Region region, List<String> biotypes) {
	//		if(region == null) {
	//			return null;
	//		}else {
	//			return getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd(), biotypes);	
	//		}
	//	}


	@Override
	public List<List<Exon>> getAllByRegionList(List<Region> regions) {
		List<List<Exon>> exonsList = new ArrayList<List<Exon>>(regions.size());
		for(Region region: regions) {
			exonsList.add(getAllByRegion(region));
		}
		return exonsList;
	}

	//	@Override
	//	public List<List<Exon>> getAllByRegionList(List<Region> regions, List<String> biotypes) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Exon> getAllByCytoband(String chromosome, String cytoband) {
		Query query = this.openSession().createQuery("select e from Exon e, Cytoband k where k.chromosome= :chromosome and k.cytoband = :cytoband and k.chromosome=e.chromosome and e.end>=k.start and e.start<=k.end").setParameter("chromosome", chromosome).setParameter("cytoband", cytoband);
		return (List<Exon>)executeAndClose(query);
	}




	@Override
	public List<Exon> getAllBySnpId(String snpId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Exon>> getAllBySnpIdList(List<String> snpIds) {
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

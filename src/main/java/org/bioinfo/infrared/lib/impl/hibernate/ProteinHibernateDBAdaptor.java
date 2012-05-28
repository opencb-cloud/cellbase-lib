package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.Protein;
import org.bioinfo.infrared.core.cellbase.ProteinFeature;
import org.bioinfo.infrared.core.cellbase.ProteinInteraction;
import org.bioinfo.infrared.core.cellbase.ProteinSequence;
import org.bioinfo.infrared.core.cellbase.ProteinXref;
import org.bioinfo.infrared.lib.api.ProteinDBAdaptor;
import org.bioinfo.infrared.lib.common.ProteinRegion;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class ProteinHibernateDBAdaptor extends HibernateDBAdaptor implements ProteinDBAdaptor {

	public ProteinHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	public ProteinHibernateDBAdaptor(SessionFactory sessionFactory, String species, String version) {
		super(sessionFactory, species, version);
	}
	@Override
	public List<String> getAllIds() {
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

	@Override
	public List<Protein> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllUniprotIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Protein> getAllByUniprotId(String uniprotId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Protein>> getAllByUniprotIdList(List<String> uniprotIdList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Protein> getAllByProteinName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Protein>> getAllByProteinNameList(List<String> nameList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Protein> getAllByEnsemblGene(String ensemblGene) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Protein>> getAllByEnsemblGeneList(List<String> ensemblGeneList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Protein> getAllByEnsemblTranscriptId(String transcriptId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Protein>> getAllByEnsemblTranscriptIdList(
			List<String> transcriptIdList) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Protein> getAllByGeneName(String geneName) {
		Criteria criteria = this.openSession().createCriteria(Protein.class)
			.add(Restrictions.eq("geneName", geneName));
		return (List<Protein>)executeAndClose(criteria);
	}

	@Override
	public List<List<Protein>> getAllByGeneNameList(List<String> geneNameList) {
		List<List<Protein>> proteinList = new ArrayList<List<Protein>>(geneNameList.size());
		for(String geneName: geneNameList) {
			proteinList.add(getAllByGeneName(geneName));
		}
		return proteinList;
	}

	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProteinFeature> getAllProteinFeaturesByUniprotId(String uniprotId) {
		Criteria criteria = this.openSession().createCriteria(ProteinFeature.class)
			.createCriteria("protein")
			.add(Restrictions.eq("primaryAccession", uniprotId));
		return (List<ProteinFeature>) executeAndClose(criteria);
	}

	@Override
	public List<List<ProteinFeature>> getAllProteinFeaturesByUniprotIdList(List<String> uniprotIdList) {
		List<List<ProteinFeature>> proteinList = new ArrayList<List<ProteinFeature>>(uniprotIdList.size());
		for(String uniprotId: uniprotIdList) {
			proteinList.add(getAllProteinFeaturesByUniprotId(uniprotId));
		}
		return proteinList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProteinFeature> getAllProteinFeaturesByGeneName(String name) {
		Criteria criteria = this.openSession().createCriteria(ProteinFeature.class)
			.createCriteria("protein")
			.add(Restrictions.eq("geneName", name));
		return (List<ProteinFeature>) executeAndClose(criteria);
	}

	@Override
	public List<List<ProteinFeature>> getAllProteinFeaturesByGeneNameList(List<String> nameList) {
		List<List<ProteinFeature>> proteinList = new ArrayList<List<ProteinFeature>>(nameList.size());
		for(String uniprotId: nameList) {
			proteinList.add(getAllProteinFeaturesByGeneName(uniprotId));
		}
		return proteinList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProteinFeature> getAllProteinFeaturesByProteinXref(String name) {
		Criteria criteria = this.openSession().createCriteria(ProteinFeature.class)
				.createCriteria("protein")
				.createCriteria("proteinXrefs")
				.add(Restrictions.eq("name", name));
		return (List<ProteinFeature>) executeAndClose(criteria);
	}

	@Override
	public List<List<ProteinFeature>> getAllProteinFeaturesByProteinXrefList(List<String> nameList) {
		List<List<ProteinFeature>> proteinList = new ArrayList<List<ProteinFeature>>(nameList.size());
		for(String uniprotId: nameList) {
			proteinList.add(getAllProteinFeaturesByProteinXref(uniprotId));
		}
		return proteinList;
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<ProteinInteraction> getAllProteinInteractionsByProteinName(String name) {
		
		Query query = this.openSession().createQuery("select distinct(pi) from Protein p, ProteinXref px, ProteinInteraction pi " +
				"where px.name = :NAME and (px.protein = pi.proteinByProteinId1 or px.protein = pi.proteinByProteinId2) and px.protein=p.proteinId").setParameter("NAME", name);
		List<ProteinInteraction> proteinInteractionList = (List<ProteinInteraction>)executeAndClose(query);
		return proteinInteractionList;
//		Criteria criteria = this.openSession().createCriteria(Protein.class)
//		.createCriteria("proteinXrefs")
//		.add(Restrictions.eq("name", name));
//		List<Protein> prots = (List<Protein>) executeAndClose(criteria);
//		
//		if(prots != null && prots.size() > 0) {
//			List<Integer> protIntIds = new ArrayList<Integer>(prots.size());
//			for(Protein p: prots) {
//				protIntIds.add(p.getProteinId());
//			}
//			Criterion proteinId1 = Restrictions.in("proteinByProteinId1",protIntIds);
//			Criterion proteinId2 = Restrictions.in("proteinByProteinId2",protIntIds);
//			LogicalExpression log = Restrictions.or(proteinId1, proteinId2);
//			criteria = this.openSession().createCriteria(ProteinInteraction.class)
//					.createCriteria("protein")
//					.add(log).setFetchMode("protein", FetchMode.JOIN);
//		}
////		return proteinInteractionList;
//
//		return (List<ProteinInteraction>)executeAndClose(criteria);
	}

	@Override
	public List<List<ProteinInteraction>> getAllProteinInteractionsByProteinNameList(	List<String> nameList) {
		List<List<ProteinInteraction>> proteinList = new ArrayList<List<ProteinInteraction>>(nameList.size());
		for(String name: nameList) {
			proteinList.add(getAllProteinInteractionsByProteinName(name));
		}
		return proteinList;
	}
	
	
	
	@Override
	public List<ProteinRegion> getAllProteinRegionByGenomicRegion(Region region) {
		return null;
	}
	@Override
	public List<List<ProteinRegion>> getAllProteinRegionByGenomicRegionList(List<Region> regionList) {
		List<List<ProteinRegion>> proteinRegionList = new ArrayList<List<ProteinRegion>>(regionList.size());
		
		return proteinRegionList;
	}
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<ProteinXref> getAllProteinXrefsByProteinName(String name) {
		System.out.println("NAME:"  + name);
//		Query query = this.openSession().createQuery("select db.* from Xref x, Dbname db where x.display_id = :Id and x.dbname_id=db.dbname_id").setParameter("Id", id);
		String query = "select px2.* from protein_xref px1, protein_xref px2 where px1.protein_id = px2.protein_id and px1.name = :NAME";
//		SQLQuery hquery = (SQLQuery) this.openSession().createSQLQuery(query).addEntity(ProteinXref.class).setParameter("NAME", name);
//		hquery.setParameter("NAME", name);
		List<ProteinXref> xrefs = this.openSession().createSQLQuery(query).addEntity(ProteinXref.class).setParameter("NAME", name).list();
//		List<ProteinXref> xrefs = (List<ProteinXref>) executeAndClose(hquery);
		System.out.println("xrefs.size(): " + xrefs.size());
		System.out.println("xrefs type: " + xrefs.getClass());
//		return (List<ProteinXref>) executeAndClose(hquery);
		return xrefs;
	}

	@Override
	public List<List<ProteinXref>> getAllProteinXrefsByProteinNameList(List<String> nameList) {
		List<List<ProteinXref>> proteinList = new ArrayList<List<ProteinXref>>(nameList.size());
		for(String name: nameList) {
			proteinList.add(getAllProteinXrefsByProteinName(name));
		}
		return proteinList;
	}

	@Override
	public List<ProteinXref> getAllProteinXrefsByProteinName(String name, String dbname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<ProteinXref>> getAllProteinXrefsByProteinNameList(List<String> nameList, String dbname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProteinXref> getAllProteinXrefsByProteinName(String name, List<String> dbname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<ProteinXref>> getAllProteinXrefsByProteinNameList(List<String> nameList, List<String> dbname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProteinSequence> getAllProteinSequenceByProteinName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<ProteinSequence>> getAllProteinSequenceByProteinNameList(
			List<String> nameList) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

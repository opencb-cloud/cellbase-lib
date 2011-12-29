package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.Protein;
import org.bioinfo.infrared.core.cellbase.ProteinFeature;
import org.bioinfo.infrared.core.cellbase.ProteinXref;
import org.bioinfo.infrared.lib.api.ProteinDBAdaptor;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ProteinHibernateDBAdaptor extends HibernateDBAdaptor implements ProteinDBAdaptor {

	public ProteinHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
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
		Criterion ensemblId = Restrictions.eq("primaryAccession", name.trim());
		Criterion nameCriterio = Restrictions.eq("geneName", name.trim());
		LogicalExpression log = Restrictions.or(ensemblId, nameCriterio);
		Criteria criteria = this.openSession().createCriteria(Protein.class)
				.add(log);
		List<Protein> proteinList = (List<Protein>) executeAndClose(criteria);
		
		// adding IDs from protein_xref
		criteria = this.openSession().createCriteria(Protein.class)
			.createCriteria("proteinXrefs")
			.add(Restrictions.eq("name", name));
		proteinList.addAll((List<Protein>) executeAndClose(criteria));
		
		List<ProteinFeature> proteinFeatureList = new ArrayList<ProteinFeature>(); 
		for(Protein prot: proteinList) {
			criteria = this.openSession().createCriteria(ProteinFeature.class)
				.createCriteria("protein")
				.add(Restrictions.eq("proteinId", prot.getProteinId()));
			proteinFeatureList.addAll((List<ProteinFeature>) executeAndClose(criteria));
		}
		return proteinFeatureList;
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
	public List<ProteinXref> getAllProteinXrefsByProteinName(String name) {
		Criterion ensemblId = Restrictions.eq("primaryAccession", name.trim());
		Criterion nameCriterio = Restrictions.eq("geneName", name.trim());
		LogicalExpression log = Restrictions.or(ensemblId, nameCriterio);
		Criteria criteria = this.openSession().createCriteria(ProteinXref.class)
			.createCriteria("protein").add(log);
		return (List<ProteinXref>) executeAndClose(criteria);
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

	
}

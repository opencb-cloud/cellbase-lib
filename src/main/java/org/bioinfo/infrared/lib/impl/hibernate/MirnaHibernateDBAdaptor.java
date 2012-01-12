package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.MirnaDisease;
import org.bioinfo.infrared.core.cellbase.MirnaGene;
import org.bioinfo.infrared.core.cellbase.MirnaMature;
import org.bioinfo.infrared.core.cellbase.MirnaTarget;
import org.bioinfo.infrared.lib.api.MirnaDBAdaptor;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

class MirnaHibernateDBAdaptor extends HibernateDBAdaptor implements MirnaDBAdaptor {

	
	public MirnaHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<MirnaGene> getMiRnaGeneByName(String miRnaGeneName) {
		Criterion mirbaseAcc = Restrictions.eq("mirbaseAcc", miRnaGeneName.trim());
		Criterion mirbaseId = Restrictions.eq("mirbaseId", miRnaGeneName.trim());
		LogicalExpression logExpression = Restrictions.or(mirbaseAcc, mirbaseId);
		Criteria criteria = this.openSession().createCriteria(MirnaGene.class);
		criteria.add(logExpression);
		return (List<MirnaGene>) executeAndClose(criteria);
	}

	@Override
	public List<List<MirnaGene>> getAllMiRnaGenesByNameList(List<String> miRnaNameList) {
		List<List<MirnaGene>> mirnaGeneList = new ArrayList<List<MirnaGene>>(miRnaNameList.size());
		for(String miRnaGeneName: miRnaNameList) {
			mirnaGeneList.add(getMiRnaGeneByName(miRnaGeneName));
		}
		return mirnaGeneList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MirnaGene> getAllMiRnaGenesByMiRnaMature(String miRnaMatureName) {
		Criterion mirbaseAcc = Restrictions.eq("mirbaseAcc", miRnaMatureName.trim());
		Criterion mirbaseId = Restrictions.eq("mirbaseId", miRnaMatureName.trim());
		LogicalExpression logExpression = Restrictions.or(mirbaseAcc, mirbaseId);
		Criteria criteria = this.openSession().createCriteria(MirnaGene.class)
				.createCriteria("mirnaGeneToMatures")
				.createCriteria("mirnaMature").add(logExpression);//Restrictions.eq("mirbaseId", miRnaMatureName)
		return (List<MirnaGene>) executeAndClose(criteria);
	}

	@Override
	public List<List<MirnaGene>> getAllMiRnaGenesByMiRnaMatureList(List<String> miRnaMatureNameList) {
		List<List<MirnaGene>> mirnaGeneList = new ArrayList<List<MirnaGene>>(miRnaMatureNameList.size());
		for(String miRnaMatureName: miRnaMatureNameList) {
			mirnaGeneList.add(getAllMiRnaGenesByMiRnaMature(miRnaMatureName));
		}
		return mirnaGeneList;
	}

	@Override
	public List<MirnaGene> getAllMiRnaGenesByDisease(String disease) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<MirnaGene>> getAllMiRnaGenesByDiseaseList(
			List<String> diseaseList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MirnaGene> getAllMiRnaGenesByGeneName(String geneName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<MirnaGene>> getAllMiRnaGenesByGeneNameList(
			List<String> geneNames) {
		// TODO Auto-generated method stub
		return null;
	}




	@SuppressWarnings("unchecked")
	@Override
	public List<MirnaMature> getMiRnaMatureByName(String miRnaMatureName) {
		Criterion mirbaseAcc = Restrictions.eq("mirbaseAcc", miRnaMatureName.trim());
		Criterion mirbaseId = Restrictions.eq("mirbaseId", miRnaMatureName.trim());
		LogicalExpression logExpression = Restrictions.or(mirbaseAcc, mirbaseId);
		Criteria criteria = this.openSession().createCriteria(MirnaMature.class);
		criteria.add(logExpression);
		return (List<MirnaMature>) executeAndClose(criteria);
	}

	@Override
	public List<List<MirnaMature>> getAllMiRnaMaturesByNameList(List<String> miRnaMatureNameList) {
		List<List<MirnaMature>> mirnaMatureList = new ArrayList<List<MirnaMature>>(miRnaMatureNameList.size());
		for(String miRnaMatureName: miRnaMatureNameList) {
			mirnaMatureList.add(getMiRnaMatureByName(miRnaMatureName));
		}
		return mirnaMatureList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MirnaMature> getAllMiRnaMaturesByMiRnaGene(String miRnaGeneName) {
		Criterion mirbaseAcc = Restrictions.eq("mirbaseAcc", miRnaGeneName.trim());
		Criterion mirbaseId = Restrictions.eq("mirbaseId", miRnaGeneName.trim());
		LogicalExpression logExpression = Restrictions.or(mirbaseAcc, mirbaseId);
		Criteria criteria = this.openSession().createCriteria(MirnaMature.class)
				.createCriteria("mirnaGeneToMatures")
				.createCriteria("mirnaGene").add(logExpression);//Restrictions.eq("mirbaseId", miRnaMatureName)
		return (List<MirnaMature>) executeAndClose(criteria);
	}

	@Override
	public List<List<MirnaMature>> getAllMiRnaMaturesByMiRnaGeneList(List<String> miRnaGeneNameList) {
		List<List<MirnaMature>> mirnaMatureList = new ArrayList<List<MirnaMature>>(miRnaGeneNameList.size());
		for(String miRnaGeneName: miRnaGeneNameList) {
			mirnaMatureList.add(getAllMiRnaMaturesByMiRnaGene(miRnaGeneName));
		}
		return mirnaMatureList;
	}

	@Override
	public List<MirnaMature> getAllMiRnaMaturesByGeneName(String geneName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<MirnaMature>> getAllMiRnaMaturesByGeneNameList(
			List<String> geneNames) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MirnaTarget> getAllMiRnaTargetsByMiRnaMature(String miRnaMature) {
		Criteria criteria = this.openSession().createCriteria(MirnaTarget.class)
				.add(Restrictions.eq("mirbaseId", miRnaMature));
		return (List<MirnaTarget>) executeAndClose(criteria);
	}

	@Override
	public List<List<MirnaTarget>> getAllMiRnaTargetsByMiRnaMatureList(List<String> miRnaMatureList) {
		List<List<MirnaTarget>> mirnaTargetsList = new ArrayList<List<MirnaTarget>>();
		for(String id: miRnaMatureList) {
			mirnaTargetsList.add(this.getAllMiRnaTargetsByMiRnaMature(id));
		}
		return mirnaTargetsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MirnaTarget> getAllMiRnaTargetsByMiRnaGene(String miRnaGeneName) {
		Criterion mirbaseAcc = Restrictions.eq("mirbaseAcc", miRnaGeneName.trim());
		Criterion mirbaseId = Restrictions.eq("mirbaseId", miRnaGeneName.trim());
		LogicalExpression logExpression = Restrictions.or(mirbaseAcc, mirbaseId);
		Criteria criteria = this.openSession().createCriteria(MirnaTarget.class)
				.createCriteria("mirnaMature")
				.createCriteria("mirnaGeneToMatures")
				.createCriteria("mirnaGene").add(logExpression);
		return (List<MirnaTarget>) executeAndClose(criteria);
	}

	@Override
	public List<List<MirnaTarget>> getAllMiRnaTargetsByMiRnaGeneList(List<String> miRnaGeneNameList) {
		List<List<MirnaTarget>> mirnaTargetsList = new ArrayList<List<MirnaTarget>>();
		for(String miRnaGeneName: miRnaGeneNameList) {
			mirnaTargetsList.add(this.getAllMiRnaTargetsByMiRnaGene(miRnaGeneName));
		}
		return mirnaTargetsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MirnaTarget> getAllMiRnaTargetsByGeneName(String geneName) {
		
		GeneHibernateDBAdaptor adaptor = new GeneHibernateDBAdaptor(this.getSessionFactory());
		List<Gene> genes = adaptor.getAllByXref(geneName);

		List<String> ensemblId = new ArrayList<String>();
		for (Gene gene : genes) {
			ensemblId.add(gene.getStableId());
		}

	
		 Criteria criteria = this.openSession().createCriteria(MirnaTarget.class).createCriteria("gene");
		
		if (ensemblId.size() > 0){
			criteria.add(Restrictions.in("stableId", ensemblId));
			return (List<MirnaTarget>) executeAndClose(criteria);
		}
		
		return new ArrayList<MirnaTarget>();
		
	}

	@Override
	public List<List<MirnaTarget>> getAllMiRnaTargetsByGeneNameList(List<String> geneNames) {
		List<List<MirnaTarget>>  result = new ArrayList<List<MirnaTarget>>();
		for (String string : geneNames) {
			result.add(this.getAllMiRnaTargetsByGeneName(string));
		}

		return result;
	}




	@SuppressWarnings("unchecked")
	@Override
	public List<MirnaDisease> getAllMiRnaDiseasesByMiRnaGene(String miRnaGene) {
		Criteria criteria = this.openSession().createCriteria(MirnaDisease.class)
				.add(Restrictions.eq("mirbaseId", miRnaGene));
		return (List<MirnaDisease>) executeAndClose(criteria);
	}

	@Override
	public List<List<MirnaDisease>> getAllMiRnaDiseasesByMiRnaGeneList(List<String> miRnaGeneList) {
		List<List<MirnaDisease>> result = new ArrayList<List<MirnaDisease>>(miRnaGeneList.size());
		for(String miRnaGene : miRnaGeneList) {
			result.add(this.getAllMiRnaDiseasesByMiRnaGene(miRnaGene));
		}
		return result;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MirnaDisease> getAllMiRnaDiseasesByMiRnaMature(String miRnaMature) {
		Criterion mirbaseAcc = Restrictions.eq("mirbaseAcc", miRnaMature.trim());
		Criterion mirbaseId = Restrictions.eq("mirbaseId", miRnaMature.trim());
		LogicalExpression logExpression = Restrictions.or(mirbaseAcc, mirbaseId);
		Criteria criteria = this.openSession().createCriteria(MirnaDisease.class)
				.createCriteria("mirnaGene")
				.createCriteria("mirnaGeneToMatures")
				.createCriteria("mirnaMature").add(logExpression);
		return (List<MirnaDisease>) executeAndClose(criteria);
	}


	@Override
	public List<List<MirnaDisease>> getAllMiRnaDiseasesByMiRnaMatureList(List<String> miRnaMatureList) {
		List<List<MirnaDisease>> result = new ArrayList<List<MirnaDisease>>(miRnaMatureList.size());
		for(String miRnaMature : miRnaMatureList) {
			result.add(this.getAllMiRnaDiseasesByMiRnaMature(miRnaMature));
		}
		return result;
	}



	
	@Override
	public List<Object> getAllAnnotation() {
		return this.getAllAnnotationBySourceList(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllAnnotationBySourceList(List<String> sources) {
		SQLQuery querySQL;
		if (sources != null){
			String query = "select gene_target_name, mirbase_id from mirna_target where source in :source group by gene_target_name, mirbase_id";
			querySQL = this.openSession().createSQLQuery(query);
			querySQL.setParameterList("source", sources);
		}
		else{
			String query = "select gene_target_name, mirbase_id from mirna_target group by gene_target_name, mirbase_id";
			querySQL = this.openSession().createSQLQuery(query);
		}

		return (List<Object>) executeAndClose(querySQL);
	}

	
	
	
	@Override
	public List<MirnaTarget> getAllMiRnaTargetsByPosition(String chromosome, int start) {
		return this.getAllMiRnaTargetsByRegion(chromosome, start, start);
	}
	
	@Override
	public List<List<MirnaTarget>> getAllMiRnaTargetsByRegionList(List<Region> regionList) {
		List<List<MirnaTarget>> result = new ArrayList<List<MirnaTarget>>();
		for (Region region : regionList) {
			result.add(this.getAllMiRnaTargetsByRegion(region));
		}
		return result;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<MirnaTarget> getAllMiRnaTargetsByRegion(String chromosome, int start, int end) {
//		ProjectionList projList = Projections.projectionList();
//		projList.add(Projections.property("mirbaseId"));
//		projList.add(Projections.groupProperty("mirbaseId"));
		 
		Criteria criteria = this.openSession().createCriteria(MirnaTarget.class)
		.add(Restrictions.ge("end", start))
		.add(Restrictions.le("start", end))
		.addOrder(Order.asc("chromosome"));
		
//		criteria.setProjection(projList);
		
		return (List<MirnaTarget>) executeAndClose(criteria);
	}


	@Override
	public List<MirnaTarget> getAllMiRnaTargetsByRegion(Region region) {
		return this.getAllMiRnaTargetsByRegion(region.getChromosome(), region.getStart(), region.getEnd());
	}

}

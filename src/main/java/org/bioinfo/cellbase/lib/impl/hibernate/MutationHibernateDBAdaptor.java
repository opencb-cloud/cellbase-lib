package org.bioinfo.cellbase.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.cellbase.lib.api.MutationDBAdaptor;
import org.bioinfo.cellbase.lib.common.IntervalFeatureFrequency;
import org.bioinfo.cellbase.lib.common.Position;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.infrared.core.cellbase.MutationPhenotypeAnnotation;
import org.bioinfo.infrared.core.cellbase.SnpPhenotypeAnnotation;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class MutationHibernateDBAdaptor extends HibernateDBAdaptor implements MutationDBAdaptor{

	public MutationHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public MutationHibernateDBAdaptor(SessionFactory sessionFactory, String species, String version) {
		super(sessionFactory, species, version);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MutationPhenotypeAnnotation> getAllMutationPhenotypeAnnotationByGeneName(String geneName) {
//		select m.* from mutation_phenotype_annotation m, xref x1, xref x2, transcript_to_xref tx1, transcript_to_xref tx2  where x1.display_id='A1CF' and x1.xref_id=tx1.xref_id and tx1.transcript_id=tx2.transcript_id and tx2.xref_id=x2.xref_id and x2.display_id=m.gene_name group by mutation_phenotype_id;
		Query query = this.openSession().createQuery("select m from MutationPhenotypeAnnotation m, Xref x1, Xref x2, TranscriptToXref tx1, TranscriptToXref tx2 where x1.displayId= :GENENAME and x1.xrefId=tx1.xref and tx1.transcript=tx2.transcript and tx2.xref=x2.xrefId and x2.displayId=m.geneName group by m.mutationPhenotypeId").setParameter("GENENAME", geneName);
		return (List<MutationPhenotypeAnnotation>)executeAndClose(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<List<MutationPhenotypeAnnotation>> getAllMutationPhenotypeAnnotationByGeneNameList(	List<String> geneNameList) {
		List<List<MutationPhenotypeAnnotation>> mutPhenListList = new ArrayList<List<MutationPhenotypeAnnotation>>(geneNameList.size());
		Session session = this.openSession();
		Query query = null;
		for(String geneName: geneNameList) {
			query = session.createQuery("select m from MutationPhenotypeAnnotation m, Xref x1, Xref x2, TranscriptToXref tx1, TranscriptToXref tx2 where x1.displayId= :GENENAME and x1.xrefId=tx1.xref and tx1.transcript=tx2.transcript and tx2.xref=x2.xrefId and x2.displayId=m.geneName group by m.mutationPhenotypeId").setParameter("GENENAME", geneName);
			mutPhenListList.add((List<MutationPhenotypeAnnotation>)execute(query));			
		}
		session.close();
		return mutPhenListList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MutationPhenotypeAnnotation> getAllMutationPhenotypeAnnotationByEnsemblTranscript(String ensemblTranscript) {
		Query query = this.openSession().createQuery("select m from MutationPhenotypeAnnotation m, Xref x1, Xref x2, TranscriptToXref tx1, TranscriptToXref tx2 where x1.displayId= :ENSEMBL_TRANSCRIPT and x1.xrefId=tx1.xref and tx1.transcript=tx2.transcript and tx2.xref=x2.xrefId and x2.displayId=m.ensemblTranscript group by m.mutationPhenotypeId").setParameter("ENSEMBL_TRANSCRIPT", ensemblTranscript);
		return (List<MutationPhenotypeAnnotation>)executeAndClose(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<List<MutationPhenotypeAnnotation>> getAllMutationPhenotypeAnnotationByEnsemblTranscriptList(List<String> ensemblTranscriptList) {
		List<List<MutationPhenotypeAnnotation>> mutPhenListList = new ArrayList<List<MutationPhenotypeAnnotation>>(ensemblTranscriptList.size());
		Session session = this.openSession();
		Query query = null;
		for(String ensemblTranscript: ensemblTranscriptList) {
			query = session.createQuery("select m from MutationPhenotypeAnnotation m, Xref x1, Xref x2, TranscriptToXref tx1, TranscriptToXref tx2 where x1.displayId= :ENSEMBL_TRANSCRIPT and x1.xrefId=tx1.xref and tx1.transcript=tx2.transcript and tx2.xref=x2.xrefId and x2.displayId=m.ensemblTranscript group by m.mutationPhenotypeId").setParameter("ENSEMBL_TRANSCRIPT", ensemblTranscript);
			mutPhenListList.add((List<MutationPhenotypeAnnotation>)execute(query));			
		}
		session.close();
		return mutPhenListList;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MutationPhenotypeAnnotation> getAllMutationPhenotypeAnnotationByPosition(Position position) {
		Criteria criteria = this.openSession().createCriteria(SnpPhenotypeAnnotation.class)
				.add(Restrictions.eq("chromosome", position.getChromosome()))
				.add(Restrictions.le("start", position.getPosition()))
				.add(Restrictions.ge("end", position.getPosition()));
		return (List<MutationPhenotypeAnnotation>) executeAndClose(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<List<MutationPhenotypeAnnotation>> getAllMutationPhenotypeAnnotationByPositionList(List<Position> position) {
		List<List<MutationPhenotypeAnnotation>> result = new ArrayList<List<MutationPhenotypeAnnotation>>(position.size());
//		Criteria criteria;
		Session session =  this.openSession();
		Query query = session.createQuery("select m from MutationPhenotypeAnnotation m where m.chromosome = :CHROM and m.start <= :START and m.end >= :END");
//		int chunkId;
//		int chunkSize = applicationProperties.getIntProperty("CELLBASE."+version.toUpperCase()+".FEATURE_MAP.CHUNK_SIZE", 500);
//		Query query = session.createQuery("select m from MutationPhenotypeAnnotation m, FeatureMap fm where fm.chunkId= :CHUNK_ID and fm.chromosome = :CHROM and fm.start <= :START and fm.end >= :END and fm.featureId=m.mutationPhenotypeId and fm.featureType='mutation_phenotype_annotation'");
		for(Position pos: position) {
//			criteria = session.createCriteria(MutationPhenotypeAnnotation.class)
//				.add(Restrictions.eq("chromosome", pos.getChromosome()))
//				.add(Restrictions.le("start", pos.getPosition()))
//				.add(Restrictions.ge("end", pos.getPosition()));
//			result.add((List<MutationPhenotypeAnnotation>) execute(criteria));
//			chunkId = pos.getPosition() / chunkSize;
//			query.setParameter("CHUNK_ID", chunkId).setParameter("CHROM", pos.getChromosome()).setParameter("START", pos.getPosition()).setParameter("END", pos.getPosition());
			query.setParameter("CHROM", pos.getChromosome()).setParameter("START", pos.getPosition()).setParameter("END", pos.getPosition());
			result.add((List<MutationPhenotypeAnnotation>) execute(query));
		}
		session.close();
		return result;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MutationPhenotypeAnnotation> getAllByRegion(Region region) {
		Query query = this.openSession().createQuery("select mpa from MutationPhenotypeAnnotation mpa where mpa.chromosome= :CHROMOSOME and mpa.start >= :START and mpa.end <= :END")
				.setParameter("CHROMOSOME", region.getChromosome())
				.setParameter("START", region.getStart())
				.setParameter("END", region.getEnd());
		return (List<MutationPhenotypeAnnotation>) executeAndClose(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<List<MutationPhenotypeAnnotation>> getAllByRegionList(List<Region> regionList) {
		List<List<MutationPhenotypeAnnotation>> result = new ArrayList<List<MutationPhenotypeAnnotation>>(regionList.size());
		Query query = null;
		Session session = this.openSession();
		for (Region region : regionList) {
			query = session.createQuery("select mpa from MutationPhenotypeAnnotation mpa where mpa.chromosome= :CHROMOSOME and mpa.start >= :START and mpa.end <= :END")
					.setParameter("CHROMOSOME", region.getChromosome())
					.setParameter("START", region.getStart())
					.setParameter("END", region.getEnd());
			result.add((List<MutationPhenotypeAnnotation>) execute(query));
		}
		session.close();
		return result;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IntervalFeatureFrequency> getAllIntervalFrequencies(Region region, int interval) {
		SQLQuery sqlquery = this.openSession().createSQLQuery("select (g.start - "+region.getStart()+") DIV "+interval+" as inter, count(*) from  mutation_phenotype_annotation g where g.chromosome= '"+region.getChromosome()+"' and g.start <= "+region.getEnd()+" and g.end >= "+region.getStart()+" group by inter");
		List<Object[]> objectList =  (List<Object[]>) executeAndClose(sqlquery);
		List<IntervalFeatureFrequency> intervalFreqsList = getIntervalFeatureFrequencies(region , interval, objectList);
		return intervalFreqsList;
	}

}

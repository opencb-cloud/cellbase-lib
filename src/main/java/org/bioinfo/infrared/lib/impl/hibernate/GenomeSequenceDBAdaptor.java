package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.GenomeSequence;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class GenomeSequenceDBAdaptor extends HibernateDBAdaptor {

	private final static int CHUNK_SIZE = 2000;

	public GenomeSequenceDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	private static int getChunk(int position){
		return (position / CHUNK_SIZE) + 1;
	}

	private static int getOffset(int position){
		return ((position) % CHUNK_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public String getByRegion(String chromosome, int start, int end) {
		Query query = this.openSession().createQuery("from GenomeSequence where chromosome = :chromosome and chunk >= :start and chunk <= :end")
		.setParameter("chromosome", chromosome.trim())
		.setParameter("start", String.valueOf(getChunk(start)))
		.setParameter("end", String.valueOf(getChunk(end)));
		
//		Criteria criteria = session.createCriteria(GenomeSequence.class).add(Restrictions.eq("chromosome", chromosome)).add( Restrictions.ge("start", start)).add(Restrictions.le("end", end));
//		List<GenomeSequence> genomeSequenceList = (List<GenomeSequence>)query.list();
		List<GenomeSequence> genomeSequenceList = (List<GenomeSequence>) executeAndClose(query);
		
		System.out.println(genomeSequenceList.size());
		
		StringBuilder sb = new StringBuilder();
		for(GenomeSequence genomeSequence: genomeSequenceList) {
			sb.append(genomeSequence.getSequence());
		}
//		this.openSession().close();
		
		System.out.println( chromosome+":"  + start +"-"+ end);
		
		System.out.println( getChunk(start));
		System.out.println( getChunk(end));
		
		System.out.println( sb.toString().length());
		System.out.println( getOffset(start));
		System.out.println( getOffset(start) + (end-start));
		
		return sb.toString().substring(getOffset(start), getOffset(start) + (end-start));
	}
	
	public List<String> getByRegionList(List<Region> regions){
		List<String> result = new ArrayList<String>(regions.size());
		for(Region region: regions) {
			result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd()));
		}
		return result;
	}
	
//	public static GenomeSequenceDBAdaptor getByRegion(String chromosome, int start, int end){
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		start = start - 1;
//
//		Query queryHql = session.createQuery("from GenomeSequence where chromosome=:chromosome and chunk >= :start and chunk <= :end")
//		.setParameter("chromosome", chromosome.trim())
//		.setParameter("start", String.valueOf(getChunk(start)).trim())
//		.setParameter("end", String.valueOf(getChunk(end)).trim());
//		
////		Criteria criteria = session.createCriteria(GenomeSequence.class).add(Restrictions.eq("chromosome", chromosome)).add( Restrictions.ge("start", start)).add(Restrictions.le("end", end));
//		List query = queryHql.list();
//		
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < query.size(); i++) {
//			GenomeSequence genomeSequence = (GenomeSequence)query.get(i);
//			sb.append(genomeSequence.getSequence());
//		}
//		session.close();
//		String sequence = sb.toString().substring(getOffset(start)   , getOffset(start) + (end-start) );
//		return new GenomeSequenceDBAdaptor(chromosome, start, end, sequence);
//	}

}

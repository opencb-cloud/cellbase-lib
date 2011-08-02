package org.bioinfo.infrared.common.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bioinfo.infrared.core.Cytoband;
import org.bioinfo.infrared.core.Gene;
import org.bioinfo.infrared.core.GenomeSequence;
import org.bioinfo.infrared.dao.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

public class GenomeSequenceDataAdapter {

	private String chromosome;
	private int start;
	private int end;
	private String sequence;

	public GenomeSequenceDataAdapter(String chromosome, int start, int end, String sequence) {
		super();
		this.chromosome = chromosome;
		this.start = start;
		this.end = end;
		this.sequence = sequence;
	}

	private static int getChunk(int position){
		return (position / 2000) + 1;
	}

	private static int getOffset(int position){
		return ((position) % 2000);
	}
	

	
	public static List<GenomeSequenceDataAdapter> getByRegionList(List<Region> regions){
		ArrayList<GenomeSequenceDataAdapter> result = new ArrayList<GenomeSequenceDataAdapter>(regions.size());
		for (Region region : regions) {
			result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd()));
		}
		return result;
	}
	
	public static GenomeSequenceDataAdapter getByRegion(String chromosome, int start, int end){
		Session session = HibernateUtil.getSessionFactory().openSession();
		start = start - 1;

		Query queryHql = session.createQuery("from GenomeSequence where chromosome=:chromosome and chunk>=:start and chunk<=:end")
		.setParameter("chromosome", chromosome.trim())
		.setParameter("start", String.valueOf(getChunk(start)).trim())
		.setParameter("end", String.valueOf(getChunk(end)).trim());
		
//		Criteria criteria = session.createCriteria(GenomeSequence.class).add(Restrictions.eq("chromosome", chromosome)).add( Restrictions.ge("start", start)).add(Restrictions.le("end", end));
		List query = queryHql.list();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < query.size(); i++) {
			GenomeSequence genomeSequence = (GenomeSequence)query.get(i);
			sb.append(genomeSequence.getSequence());
		}
		session.close();
		String sequence = sb.toString().substring(getOffset(start)   , getOffset(start) + (end-start) );
		return new GenomeSequenceDataAdapter(chromosome, start, end, sequence);
	}

	public String getChromosome() {
		return chromosome;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public String getSequence() {
		return sequence;
	}
}

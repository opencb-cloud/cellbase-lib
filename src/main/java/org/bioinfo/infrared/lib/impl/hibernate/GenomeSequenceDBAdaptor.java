package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.GenomeSequence;
import org.bioinfo.infrared.lib.common.GenomeSequenceFeature;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class GenomeSequenceDBAdaptor extends HibernateDBAdaptor {

	private final static int CHUNK_SIZE = 2000;

	public GenomeSequenceDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	private static int getChunk(int position){
		return (position / CHUNK_SIZE);
	}

	private static int getOffset(int position){
		return ((position) % CHUNK_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public GenomeSequenceFeature getByRegion(String chromosome, int start, int end) {
		Query query = this.openSession().createQuery("from GenomeSequence where chromosome = :chromosome and chunk >= :start and chunk <= :end")
		.setParameter("chromosome", chromosome.trim())
		.setParameter("start", String.valueOf(getChunk(start)))
		.setParameter("end", String.valueOf(getChunk(end)));
		
		List<GenomeSequence> genomeSequenceList = (List<GenomeSequence>) executeAndClose(query);
		
		System.out.println(genomeSequenceList.size());
		
		StringBuilder sb = new StringBuilder();
		for(GenomeSequence genomeSequence: genomeSequenceList) {
			sb.append(genomeSequence.getSequence());
		}
		/*
		System.out.println( chromosome+":"  + start +"-"+ end);
		
		System.out.println("test: " + getChunk(start));
		System.out.println( getChunk(end));
		
		System.out.println( sb.toString().length());
		System.out.println( getOffset(start));
		System.out.println( getOffset(start) + (end-start));
		*/
		return new GenomeSequenceFeature(chromosome, start - 1, end - 1, sb.toString().substring(getOffset(start), getOffset(start) + (end-start) + 1));
	}
	
	public List<GenomeSequenceFeature> getByRegionList(List<Region> regions){
		List<GenomeSequenceFeature> result = new ArrayList<GenomeSequenceFeature>(regions.size());
		for(Region region: regions) {
			result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd()));
		}
		return result;
	}
	


}

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
		return new GenomeSequenceFeature(chromosome, start - 1, end - 1, sb.toString().substring(getOffset(start), getOffset(start) + (end-start) + 1));
	}
	
	
	public static String getComplementarySequence(String sequence){
		sequence = sequence.replace("A", "1");
		sequence = sequence.replace("T", "2");
		sequence = sequence.replace("C", "3");
		sequence = sequence.replace("G", "4");
		sequence = sequence.replace("1", "T");
		sequence = sequence.replace("2", "A");
		sequence = sequence.replace("3", "G");
		sequence = sequence.replace("4", "C");
		return sequence;
	}
	
	public static String getRevComp(String sequence){
		String sequenceRef = new String();
		sequenceRef = new StringBuffer(sequence).reverse().toString();
		return GenomeSequenceDBAdaptor.getComplementarySequence(sequenceRef);
	}
	
	
	@SuppressWarnings("unchecked")
	public GenomeSequenceFeature getByRegion(String chromosome, int start, int end, int strand) {
		GenomeSequenceFeature genomeSequenceFeature = this.getByRegion(chromosome, start, end);
		
		if (strand == -1){
			genomeSequenceFeature.setSequence(GenomeSequenceDBAdaptor.getRevComp(genomeSequenceFeature.getSequence()));
		}
		
		return genomeSequenceFeature;
	}
	
	public List<GenomeSequenceFeature> getByRegionList(List<Region> regions, int strand){
		List<GenomeSequenceFeature> result = new ArrayList<GenomeSequenceFeature>(regions.size());
		for(Region region: regions) {
			result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd(), strand));
		}
		return result;
	}
	
	public List<GenomeSequenceFeature> getByRegionList(List<Region> regions){
		List<GenomeSequenceFeature> result = new ArrayList<GenomeSequenceFeature>(regions.size());
		for(Region region: regions) {
			result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd(), 1));
		}
		return result;
	}
	


}

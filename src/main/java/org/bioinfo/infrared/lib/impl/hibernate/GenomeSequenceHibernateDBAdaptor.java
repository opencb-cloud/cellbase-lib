package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.GenomeSequence;
import org.bioinfo.infrared.lib.api.GenomeSequenceDBAdaptor;
import org.bioinfo.infrared.lib.common.GenomeSequenceFeature;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

class GenomeSequenceHibernateDBAdaptor extends HibernateDBAdaptor implements GenomeSequenceDBAdaptor {

	private final static int CHUNK_SIZE = 2000;

	
	public GenomeSequenceHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public GenomeSequenceHibernateDBAdaptor(SessionFactory sessionFactory, String species, String version) {
		super(sessionFactory, species, version);
	}
	
	private static int getChunk(int position){
		return (position / CHUNK_SIZE);
	}

	private static int getOffset(int position){
		return ((position) % CHUNK_SIZE);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public GenomeSequenceFeature getByRegion(String chromosome, int start, int end) {
		Query query = this.openSession().createQuery("from GenomeSequence where chromosome = :chromosome and chunk >= :start and chunk <= :end")
		.setParameter("chromosome", chromosome.trim())
		.setParameter("start", String.valueOf(getChunk(start)))
		.setParameter("end", String.valueOf(getChunk(end)));
		
		List<GenomeSequence> genomeSequenceList = (List<GenomeSequence>) executeAndClose(query);
		
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
	
	@Override
	public String getRevComp(String sequence) {
		String sequenceRef = new String();
		sequenceRef = new StringBuffer(sequence).reverse().toString();
		return GenomeSequenceHibernateDBAdaptor.getComplementarySequence(sequenceRef);
	}
	
	@Override
	public GenomeSequenceFeature getByRegion(String chromosome, int start, int end, int strand) {
		GenomeSequenceFeature genomeSequenceFeature = this.getByRegion(chromosome, start, end);
		
		if (strand == -1){
			genomeSequenceFeature.setSequence(getRevComp(genomeSequenceFeature.getSequence()));
		}
		
		return genomeSequenceFeature;
	}
	
	@Override
	public List<GenomeSequenceFeature> getByRegionList(List<Region> regions, int strand){
		List<GenomeSequenceFeature> result = new ArrayList<GenomeSequenceFeature>(regions.size());
		for(Region region: regions) {
			result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd(), strand));
		}
		return result;
	}
	
	@Override
	public List<GenomeSequenceFeature> getByRegionList(List<Region> regions){
		List<GenomeSequenceFeature> result = new ArrayList<GenomeSequenceFeature>(regions.size());
		for(Region region: regions) {
			result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd(), 1));
		}
		return result;
	}
	


}

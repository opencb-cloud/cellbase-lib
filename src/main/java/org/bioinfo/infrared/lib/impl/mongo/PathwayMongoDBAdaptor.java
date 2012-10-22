package org.bioinfo.infrared.lib.impl.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.GenomeSequence;
import org.bioinfo.infrared.lib.api.GenomeSequenceDBAdaptor;
import org.bioinfo.infrared.lib.common.GenomeSequenceFeature;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

class PathwayMongoDBAdaptor extends MongoDBAdaptor implements PathwayDBAdaptor {

//	private final static int CHUNK_SIZE = 2000;

	
	public PathwayMongoDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public PathwayMongoDBAdaptor(SessionFactory sessionFactory, String species, String version) {
		super(sessionFactory, species, version);
	}
	
	
	private int getChunk(int position){
		return (position / applicationProperties.getIntProperty("CELLBASE."+version.toUpperCase()+".GENOME_SEQUENCE.CHUNK_SIZE", 2000));
	}

	private int getOffset(int position){
		return ((position) % applicationProperties.getIntProperty("CELLBASE."+version.toUpperCase()+".GENOME_SEQUENCE.CHUNK_SIZE", 2000));
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public GenomeSequenceFeature getByRegion(String chromosome, int start, int end) {
		// positions below 1 are not allowed
		if(start < 1) {
			start = 1;
		}
		
		Query query = this.openSession().createQuery("from GenomeSequence where chromosome = :chromosome and chunk >= :start and chunk <= :end")
			.setParameter("chromosome", chromosome.trim())
			.setParameter("start", String.valueOf(getChunk(start)))
			.setParameter("end", String.valueOf(getChunk(end)));
		
		List<GenomeSequence> genomeSequenceList = (List<GenomeSequence>) executeAndClose(query);
		StringBuilder sb = new StringBuilder();
		for(GenomeSequence genomeSequence: genomeSequenceList) {
			sb.append(genomeSequence.getSequence());
		}
		
//	return new GenomeSequence(new GenomeSequenceId(chromosome, getChunk(start)), start, end, sb.toString().substring(getOffset(start), getOffset(start) + (end-start) + 1));
		int startStr = getOffset(start);
		int endStr = getOffset(start) + (end-start) + 1;
		String subStr = "";
		if(getChunk(end) > 0) {
			if(sb.toString().length() > 0 && sb.toString().length() >= endStr){
				subStr = sb.toString().substring(startStr, endStr);
			}			
		}else {
			if(sb.toString().length() > 0 && sb.toString().length()+1 >= endStr){
				subStr = sb.toString().substring(startStr, endStr-1);
			}
		}
		
//		return new GenomeSequence(new GenomeSequenceId(chromosome, getChunk(start)), start, end, subStr);
		return new GenomeSequenceFeature(chromosome, start, end, subStr);
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
		return PathwayMongoDBAdaptor.getComplementarySequence(sequenceRef);
	}
	
	@Override
	public GenomeSequenceFeature getByRegion(String chromosome, int start, int end, int strand) {
		GenomeSequenceFeature genomeSequence = this.getByRegion(chromosome, start, end);
		
		if (strand == -1){
			genomeSequence.setSequence(getRevComp(genomeSequence.getSequence()));
		}
		
		return genomeSequence;
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

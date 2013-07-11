package org.bioinfo.cellbase.lib.impl.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.cellbase.lib.api.GenomeSequenceDBAdaptor;
import org.bioinfo.cellbase.lib.common.GenomeSequenceFeature;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.common.core.GenomeSequenceChunk;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryOptions;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResponse;

@Deprecated
public class GenomeSequenceMongoDBAdaptor extends MongoDBAdaptor implements GenomeSequenceDBAdaptor {

	public GenomeSequenceMongoDBAdaptor(DB db) {
		super(db);
	}

	public GenomeSequenceMongoDBAdaptor(DB db, String species, String version) {
		super(db, species, version);
		mongoDBCollection = db.getCollection("genome_sequence");
	}

	private int getChunk(int position) {
		return (position / applicationProperties.getIntProperty("CELLBASE." + version.toUpperCase()
				+ ".GENOME_SEQUENCE.CHUNK_SIZE", 2000));
	}

	private int getOffset(int position) {
		return ((position) % applicationProperties.getIntProperty("CELLBASE." + version.toUpperCase()
				+ ".GENOME_SEQUENCE.CHUNK_SIZE", 2000));
	}

	public static String getComplementarySequence(String sequence) {
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
    public QueryResponse getByRegion(String chromosome, int start, int end, QueryOptions options) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResponse getAllByRegionList(List<Region> regions, QueryOptions options) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getRevComp(String sequence) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }









    private List<GenomeSequenceChunk> executeQuery(DBObject query) {
        List<GenomeSequenceChunk> result = null;
        DBCursor cursor = mongoDBCollection.find(query);
        try {
            if (cursor != null) {
                result = new ArrayList<GenomeSequenceChunk>(cursor.size());
//				Gson jsonObjectMapper = new Gson();
                GenomeSequenceChunk chunk = null;
                while (cursor.hasNext()) {
//					chunk = (GenomeSequenceChunk) jsonObjectMapper.fromJson(cursor.next().toString(), GenomeSequenceChunk.class);
                    result.add(chunk);
                }
            }
        } finally {
            cursor.close();
        }
        return result;
    }


//	@Override
//	public GenomeSequenceFeature getByRegion(String chromosome, int start, int end) {
//		// positions below 1 are not allowed
//		if (start < 1) {
//			start = 1;
//		}
//        if (end < 1) {
//            end = 1;
//        }
//		QueryBuilder builder = QueryBuilder.start("chromosome").is(chromosome.trim()).and("chunkId")
//				.greaterThanEquals(getChunk(start)).lessThanEquals(getChunk(end));
//
//		System.out.println(builder.get().toString());
//		List<GenomeSequenceChunk> chunkList = executeQuery(builder.get());
//		StringBuilder sb = new StringBuilder();
//		for (GenomeSequenceChunk chunk : chunkList) {
//			sb.append(chunk.getSequence());
//		}
//
//		int startStr = getOffset(start);
//		int endStr = getOffset(start) + (end - start) + 1;
//		String subStr = "";
//		if (getChunk(end) > 0) {
//			if (sb.toString().length() > 0 && sb.toString().length() >= endStr) {
//				subStr = sb.toString().substring(startStr, endStr);
//			}
//		} else {
//			if (sb.toString().length() > 0 && sb.toString().length() + 1 >= endStr) {
//				subStr = sb.toString().substring(startStr-1, endStr - 1);
//			}
//		}
//
//		return new GenomeSequenceFeature(chromosome, start, end, subStr);
//	}
//
//	@Override
//	public GenomeSequenceFeature getByRegion(String chromosome, int start, int end, int strand) {
//		GenomeSequenceFeature genomeSequence = this.getByRegion(chromosome, start, end);
//
//		if (strand == -1) {
//			genomeSequence.setSequence(getRevComp(genomeSequence.getSequence()));
//		}
//
//		return genomeSequence;
//	}
//
//	@Override
//	public List<GenomeSequenceFeature> getByRegionList(List<Region> regions) {
//		List<GenomeSequenceFeature> result = new ArrayList<GenomeSequenceFeature>(regions.size());
//		for (Region region : regions) {
//			result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd(), 1));
//		}
//		return result;
//	}
//
//	@Override
//	public List<GenomeSequenceFeature> getByRegionList(List<Region> regions, int strand) {
//		List<GenomeSequenceFeature> result = new ArrayList<GenomeSequenceFeature>(regions.size());
//		for (Region region : regions) {
//			result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd(), strand));
//		}
//		return result;
//	}
//
//	@Override
//	public String getRevComp(String sequence) {
//		String sequenceRef = new String();
//		sequenceRef = new StringBuffer(sequence).reverse().toString();
//		return getComplementarySequence(sequenceRef);
//	}


}

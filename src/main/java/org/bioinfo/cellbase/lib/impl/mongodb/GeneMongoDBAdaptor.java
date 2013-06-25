package org.bioinfo.cellbase.lib.impl.mongodb;

import java.util.*;

import com.mongodb.*;
import org.bioinfo.cellbase.lib.api.GeneDBAdaptor;
import org.bioinfo.cellbase.lib.common.IntervalFeatureFrequency;
import org.bioinfo.cellbase.lib.common.Position;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.common.core.Gene;

import com.google.gson.Gson;

//import org.bioinfo.infrared.core.cellbase.Gene;

public class GeneMongoDBAdaptor extends MongoDBAdaptor implements GeneDBAdaptor {

	public GeneMongoDBAdaptor(DB db) {
		super(db);
	}

	public GeneMongoDBAdaptor(DB db, String species, String version) {
		super(db, species, version);
		mongoDBCollection = db.getCollection("core");
	}
	
	private List<Gene> executeQuery(DBObject query, List<String> excludeFields) {
		List<Gene> result = null;
		
		DBCursor cursor = null;
		if (excludeFields != null && excludeFields.size() > 0) {
			BasicDBObject returnFields = new BasicDBObject("_id", 0);
			for (String field : excludeFields) {
				returnFields.put(field, 0);
			}
			cursor = mongoDBCollection.find(query, returnFields);
		} else {
			cursor = mongoDBCollection.find(query);
		}
		
		try {
			if (cursor != null) {
				result = new ArrayList<Gene>(cursor.size());
				Gson gson = new Gson();
				Gene gene;
				while (cursor.hasNext()) {
					gene = (Gene) gson.fromJson(cursor.next().toString(), Gene.class);
					result.add(gene);
				}
			}
		} finally {
			cursor.close();
		}
		return result;
	}
	
	@Override
	public List<? extends Object> getAll() {
		BasicDBObject query = new BasicDBObject();
		return executeQuery(query, null);
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
	public List<Gene> getAll(List<String> biotype, Boolean id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllEnsemblIds() {
		BasicDBObject query = new BasicDBObject();

		BasicDBObject returnFields = new BasicDBObject();
		// returnFields.put("id", 1);
		returnFields.put("transcripts", 0);

		BasicDBObject orderBy = new BasicDBObject();
		orderBy.put("id", 1);

		DBCursor cursor = mongoDBCollection.find(query, returnFields).sort(orderBy);
		List<String> result = new ArrayList<String>(cursor.size());
		while (cursor.hasNext()) {
			result.add(cursor.next().get("id").toString());
		}
		// List<DBObject> result = cursor.toArray();
		cursor.close();

		return result;
	}

	@Override
	public Gene getByEnsemblId(String ensemblId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllByEnsemblIdList(List<String> ensemblIdList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gene getByEnsemblId(String ensemblId, boolean fetchTranscriptsAndExons) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllByEnsemblIdList(List<String> ensemblIdList, boolean fetchTranscriptsAndExons) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllByName(String name, List<String> exclude) {
		BasicDBObject query = new BasicDBObject("transcripts.xrefs.id", name.toUpperCase());
        return executeQuery(query, exclude);
	}
	
	@Override
	public List<List<Gene>> getAllByNameList(List<String> nameList, List<String> exclude) {
		List<List<Gene>> genes = new ArrayList<List<Gene>>(nameList.size());
		for (String name : nameList) {
			genes.add(getAllByName(name, exclude));
		}
		return genes;
	}
	
	@Override
	public Gene getByEnsemblTranscriptId(String transcriptId) {
		BasicDBObject query = new BasicDBObject("transcripts.id", transcriptId.toUpperCase());

		List<Gene> genes = executeQuery(query, Arrays.asList("transcripts"));
		if (genes != null && genes.size() > 0) {
			return genes.get(0);
		}
		return null;
	}

	@Override
	public List<Gene> getAllByEnsemblTranscriptIdList(List<String> transcriptIdList) {
		List<Gene> genes = new ArrayList<Gene>(transcriptIdList.size());
		for (String name : transcriptIdList) {
			genes.add(getByEnsemblTranscriptId(name));
		}
		return genes;
	}

//	@Override
//	public List<Gene> getByXref(String xref, List<String> exclude) {
//		BasicDBObject query = new BasicDBObject("transcripts.xrefs.id", xref.toUpperCase());
//		return executeQuery(query, exclude);
//	}
//
//    @Override
//    public List<List<Gene>> getByXrefList(List<String> xrefList, List<String> exclude) {
//        List<List<Gene>> genes = new ArrayList<List<Gene>>(xrefList.size());
//        for (String name : xrefList) {
//            genes.add(getByXref(name, exclude));
//        }
//        return genes;
//    }

	@Override
	public List<Gene> getAllByBiotype(String biotype) {
		BasicDBObject query = new BasicDBObject("biotype", biotype);
		return executeQuery(query, Arrays.asList("transcripts"));
	}

	@Override
	public List<Gene> getAllByBiotypeList(List<String> biotypeList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllByPosition(String chromosome, int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllByPosition(Position position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Gene>> getAllByPositionList(List<Position> positionList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllByRegion(String chromosome, int start, int end, boolean fetchTranscripts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllByRegion(String chromosome, int start, int end, List<String> biotypeList,
			boolean fetchTranscripts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllByRegion(Region region, boolean fetchTranscripts) {
		BasicDBObject query = new BasicDBObject("chromosome", region.getChromosome());
		query.put("end", new BasicDBObject("$gt", region.getStart()));
		query.put("start", new BasicDBObject("$lt", region.getEnd()));

		if (!fetchTranscripts) {
			return executeQuery(query, Arrays.asList("transcripts"));
		} else {
			return executeQuery(query, null);
		}
	}

	@Override
	public List<Gene> getAllByRegion(Region region, List<String> biotypeList, boolean fetchTranscripts) {
		QueryBuilder builder = QueryBuilder.start("chromosome").is(region.getChromosome()).and("end")
				.greaterThan(region.getStart()).and("start").lessThan(region.getEnd());

		if (biotypeList != null && biotypeList.size() > 0) {
			BasicDBList biotypeIds = new BasicDBList();
			biotypeIds.addAll(biotypeList);
			builder = builder.and("biotype").in(biotypeIds);
		}

		if (!fetchTranscripts) {
			return executeQuery(builder.get(), Arrays.asList("transcripts"));
		} else {
			return executeQuery(builder.get(), null);
		}
	}

	@Override
	public List<List<Gene>> getAllByRegionList(List<Region> regionList, boolean fetchTranscripts) {
		List<List<Gene>> genes = new ArrayList<List<Gene>>(regionList.size());
		for (Region region : regionList) {
			genes.add(getAllByRegion(region, fetchTranscripts));
		}
		return genes;
	}

	@Override
	public List<List<Gene>> getAllByRegionList(List<Region> regionList, List<String> biotypeList,
			boolean fetchTranscripts) {
		List<List<Gene>> genes = new ArrayList<List<Gene>>(regionList.size());
		for (Region region : regionList) {
			genes.add(getAllByRegion(region, biotypeList, fetchTranscripts));
		}
		return genes;
	}

	@Override
	public List<Gene> getAllByCytoband(String chromosome, String cytoband) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllBySnpId(String snpId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Gene>> getAllBySnpIdList(List<String> snpIdList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllByTf(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Gene>> getAllByTfList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllByTfName(String tfName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Gene>> getAllByTfNameList(List<String> tfNameList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllTargetsByTf(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Gene>> getAllTargetsByTfList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllByMiRnaMature(String mirbaseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Gene>> getAllByMiRnaMatureList(List<String> mirbaseIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllTargetsByMiRnaMature(String mirbaseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Gene>> getAllTargetsByMiRnaMatureList(List<String> mirbaseIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllIntervalFrequencies(Region region, int interval) {

        BasicDBObject start = new BasicDBObject("$gt",region.getStart());
        start.append("$lt",region.getEnd());

        BasicDBList andArr = new BasicDBList();
        andArr.add(new BasicDBObject("chromosome",region.getChromosome()));
        andArr.add(new BasicDBObject("start",start));

        BasicDBObject match = new BasicDBObject("$match",new BasicDBObject("$and",andArr));



        BasicDBList divide1 = new BasicDBList();
        divide1.add("$start");
        divide1.add(interval);

        BasicDBList divide2 = new BasicDBList();
        divide2.add(new BasicDBObject("$mod",divide1));
        divide2.add(interval);

        BasicDBList subtractList = new BasicDBList();
        subtractList.add(new BasicDBObject("$divide",divide1));
        subtractList.add(new BasicDBObject("$divide",divide2));


        BasicDBObject substract = new BasicDBObject("$subtract",subtractList);

        DBObject totalCount = new BasicDBObject("$sum",1);

        BasicDBObject g = new BasicDBObject("_id",substract);
        g.append("features_count",totalCount);
        BasicDBObject group = new BasicDBObject("$group",g);

        BasicDBObject sort = new BasicDBObject("$sort",new BasicDBObject("_id",1));

        AggregationOutput output = mongoDBCollection.aggregate(match, group, sort);

        System.out.println(output.getCommand());

        HashMap<Long,DBObject> ids = new HashMap<>();
        BasicDBList resultList = new BasicDBList();

        for (DBObject intervalObj : output.results()){
            Long _id = Math.round((Double)intervalObj.get("_id"));//is double

            DBObject intervalVisited = ids.get(_id);
            if(intervalVisited == null){
                intervalObj.put("_id",_id);
                intervalObj.put("start",getChunkStart(_id.intValue(), interval));
                intervalObj.put("end",getChunkEnd(_id.intValue(), interval));
                intervalObj.put("features_count",Math.log((int)intervalObj.get("features_count")));
                ids.put(_id,intervalObj);
                resultList.add(intervalObj);
            }else{
                Double sum = (Double)intervalVisited.get("features_count") + Math.log((int)intervalObj.get("features_count"));
                intervalVisited.put("features_count", sum.intValue());
            }
        }
        return resultList.toString();

//		QueryBuilder builder = QueryBuilder.start("chromosome").is(region.getChromosome()).and("end")
//				.greaterThan(region.getStart()).and("start").lessThan(region.getEnd());
//
//		int numIntervals = (region.getEnd() - region.getStart()) / interval + 1;
//		int[] intervalCount = new int[numIntervals];
//
//		List<Gene> genes = executeQuery(builder.get(),
//				Arrays.asList("transcripts,id,name,biotype,status,chromosome,end,strand,source,description"));
//
//		System.out.println("GENES index");
//		System.out.println("numIntervals: " + numIntervals);
//		for (Gene gene : genes) {
//			System.out.print("gs:" + gene.getStart() + " ");
//			if (gene.getStart() >= region.getStart() && gene.getStart() <= region.getEnd()) {
//				int intervalIndex = (gene.getStart() - region.getStart()) / interval; // truncate
//				System.out.print(intervalIndex + " ");
//				intervalCount[intervalIndex]++;
//			}
//		}
//		System.out.println("GENES index");
//
//		int intervalStart = region.getStart();
//		int intervalEnd = intervalStart + interval - 1;
//		BasicDBList intervalList = new BasicDBList();
//		for (int i = 0; i < numIntervals; i++) {
//			BasicDBObject intervalObj = new BasicDBObject();
//			intervalObj.put("start", intervalStart);
//			intervalObj.put("end", intervalEnd);
//			intervalObj.put("interval", i);
//			intervalObj.put("value", intervalCount[i]);
//			intervalList.add(intervalObj);
//			intervalStart = intervalEnd + 1;
//			intervalEnd = intervalStart + interval - 1;
//		}
//
//		System.out.println(region.getChromosome());
//		System.out.println(region.getStart());
//		System.out.println(region.getEnd());
//		return intervalList.toString();

	}

    private int getChunkId(int position, int chunksize){
        return position/chunksize;
    }
    private int getChunkStart(int id, int chunksize){
        return  (id==0) ? 1 : id*chunksize;
    }
    private int getChunkEnd(int id, int chunksize){
        return (id*chunksize)+chunksize-1;
    }
}

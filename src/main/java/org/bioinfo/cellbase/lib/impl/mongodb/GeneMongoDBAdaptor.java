package org.bioinfo.cellbase.lib.impl.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bioinfo.cellbase.lib.api.GeneDBAdaptor;
import org.bioinfo.cellbase.lib.common.IntervalFeatureFrequency;
import org.bioinfo.cellbase.lib.common.Position;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.infrared.core.cellbase.Gene;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
//import org.bioinfo.infrared.core.cellbase.Gene;

public class GeneMongoDBAdaptor extends MongoDBAdaptor implements GeneDBAdaptor {


	public GeneMongoDBAdaptor(DB db) {
		super(db);
	}

	public GeneMongoDBAdaptor(DB db, String species, String version) {
		super(db, species, version);
		mongoDBCollection = db.getCollection("core");
	}


	@Override
	public List<? extends Object> getAll() {
		BasicDBObject query = new BasicDBObject();

		BasicDBObject returnFields = new BasicDBObject();
		returnFields.put("_id", 0);

		BasicDBObject orderBy = new BasicDBObject();
		orderBy.put("chromosome", 1);

//		DBCursor cursor = mongoDBCollection.find(query, returnFields).limit(10);
//
//		List<DBObject> result = cursor.toArray();
//		cursor.close();
//		return result;
		return executeQuery(query, returnFields);
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
		//		returnFields.put("id", 1);
		returnFields.put("transcripts", 0);

		BasicDBObject orderBy = new BasicDBObject();
		orderBy.put("id", 1);

		DBCursor cursor = mongoDBCollection.find(query, returnFields).sort(orderBy);
		List<String> result = new ArrayList<>(cursor.size());
		while(cursor.hasNext()) {
			result.add(cursor.next().get("id").toString());
		}
		//		List<DBObject> result = cursor.toArray();
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
	public Gene getByEnsemblId(String ensemblId,
			boolean fetchTranscriptsAndExons) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllByEnsemblIdList(List<String> ensemblIdList,
			boolean fetchTranscriptsAndExons) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllByName(String name) {
		BasicDBObject query = new BasicDBObject("name", name.toUpperCase());

		BasicDBObject returnFields = new BasicDBObject();
		returnFields.put("transcripts", 0);

		return executeQuery(query, returnFields);
	}

	@Override
	public List<List<Gene>> getAllByNameList(List<String> nameList) {
		List<List<Gene>> genes = new ArrayList<List<Gene>>(nameList.size());
		for(String name: nameList) {
			genes.add(getAllByName(name));
		}
		return genes;
	}

	@Override
	public Gene getByEnsemblTranscriptId(String transcriptId) {
		BasicDBObject query = new BasicDBObject("transcripts.id", transcriptId.toUpperCase());

		BasicDBObject returnFields = new BasicDBObject();
		returnFields.put("transcripts", 0);
		List<Gene> genes = executeQuery(query, returnFields);
		if(genes != null && genes.size() > 0) {
			return genes.get(0);
		}
		return null;
	}

	@Override
	public List<Gene> getAllByEnsemblTranscriptIdList(List<String> transcriptIdList) {
		List<Gene> genes = new ArrayList<Gene>(transcriptIdList.size());
		for(String name: transcriptIdList) {
			genes.add(getByEnsemblTranscriptId(name));
		}
		return genes;
	}

	@Override
	public List<Gene> getAllByXref(String xref) {
		BasicDBObject query = new BasicDBObject("transcripts.xref.id", xref);

		BasicDBObject returnFields = new BasicDBObject();
		returnFields.put("transcripts", 0);

		return executeQuery(query, returnFields);
	}

	@Override
	public List<Gene> getAllByBiotype(String biotype) {
		BasicDBObject query = new BasicDBObject("biotype", biotype);

		BasicDBObject returnFields = new BasicDBObject();
		returnFields.put("transcripts", 0);

		return executeQuery(query, returnFields);
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
	public List<Gene> getAllByRegion(String chromosome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllByRegion(String chromosome, int start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllByRegion(String chromosome, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllByRegion(String chromosome, int start, int end,
			List<String> biotypeList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gene> getAllByRegion(Region region) {
		BasicDBObject query = new BasicDBObject("chromosome", region.getChromosome());
		query.put("end", new BasicDBObject("$gt", region.getStart()));
		query.put("start", new BasicDBObject("$lt", region.getEnd()));

		BasicDBObject returnFields = new BasicDBObject();
		returnFields.put("transcripts", 0);

//		DBCursor cursor = mongoDBCollection.find(query, returnFields);
//		List<Gene> result = new ArrayList<>(cursor.size());
//		Gson gson = new Gson();
//		while(cursor.hasNext()) {
//			Gene gene = (Gene)gson.fromJson(cursor.next().toString(), Gene.class);
//			result.add(gene);
//		}
//		cursor.close();
//		return result;
		return executeQuery(query, returnFields);
	}

	@Override
	public List<Gene> getAllByRegion(Region region, List<String> biotypeList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Gene>> getAllByRegionList(List<Region> regionList) {
		List<List<Gene>> genes = new ArrayList<List<Gene>>(regionList.size());
		for(Region region: regionList) {
			genes.add(getAllByRegion(region));
		}
		return genes;
	}

	@Override
	public List<List<Gene>> getAllByRegionList(List<Region> regions,
			List<String> biotypeList) {
		// TODO Auto-generated method stub
		return null;
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
	public List<List<Gene>> getAllTargetsByMiRnaMatureList(
			List<String> mirbaseIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IntervalFeatureFrequency> getAllIntervalFrequencies(
			Region region, int interval) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Gene> executeQuery(BasicDBObject query, BasicDBObject returnFields) {
		List<Gene> result = null;
		DBCursor cursor = mongoDBCollection.find(query, returnFields);
		if(cursor != null) {
			result = new ArrayList<>(cursor.size());
			Gson gson = new Gson();
			Gene gene;
			while(cursor.hasNext()) {
				gene = (Gene)gson.fromJson(cursor.next().toString(), Gene.class);
				result.add(gene);
			}
		}
		cursor.close();

		return result;
	}

}

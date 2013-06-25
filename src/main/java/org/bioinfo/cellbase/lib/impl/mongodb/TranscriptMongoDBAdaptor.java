package org.bioinfo.cellbase.lib.impl.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bioinfo.cellbase.lib.api.TranscriptDBAdaptor;
import org.bioinfo.cellbase.lib.common.Position;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.common.core.Gene;
import org.bioinfo.cellbase.lib.common.core.Transcript;
import org.bioinfo.cellbase.lib.common.core.Xref;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class TranscriptMongoDBAdaptor extends MongoDBAdaptor implements TranscriptDBAdaptor {

	public TranscriptMongoDBAdaptor(DB db) {
		super(db);
	}

	public TranscriptMongoDBAdaptor(DB db, String species, String version) {
		super(db, species, version);
		mongoDBCollection = db.getCollection("core");
	}

	private List<Transcript> executeQuery(DBObject query) {
		List<Transcript> result = null;

		BasicDBObject returnFields = new BasicDBObject("transcripts", 1);
		DBCursor cursor = mongoDBCollection.find(query, returnFields);

		try {
			if (cursor != null) {
				result = new ArrayList<Transcript>();
				Gson gson = new Gson();
				Gene gene;
				while (cursor.hasNext()) {
					gene = (Gene) gson.fromJson(cursor.next().toString(), Gene.class);
					result.addAll(gene.getTranscripts());
//					BasicDBList b = new BasicDBList();
//					b.addAll((BasicDBList)cursor.next().get("transcripts"));
//					trans = (Transcript) gson.fromJson(cursor.next().get("transcripts").toString(), Transcript.class);
				}
			}
		} finally {
			cursor.close();
		}

		return result;
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
	public List<Transcript> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllEnsemblIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transcript getByEnsemblId(String ensemblId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transcript> getAllByEnsemblIdList(List<String> ensemblIdList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transcript> getAllByName(String name) {
		BasicDBObject query = new BasicDBObject("transcripts.xrefs.id", name);

		List<Transcript> result = new ArrayList<Transcript>();
		List<Transcript> transcripts = executeQuery(query);
		for (Transcript transcript : transcripts) {
			// List<Xref> xrefs = transcript.getXrefs();
			for (Xref xref : transcript.getXrefs()) {
				if (xref.getId().equals(name)) {
					result.add(transcript);
					break;
				}
			}
		}
		return result;

	}

	@Override
	public List<List<Transcript>> getAllByNameList(List<String> nameList) {
		List<List<Transcript>> transcripts = new ArrayList<List<Transcript>>(nameList.size());
		for (String name : nameList) {
			transcripts.add(getAllByName(name));
		}
		return transcripts;
	}

	@Override
	public List<Transcript> getByEnsemblGeneId(String ensemblGeneId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Transcript>> getByEnsemblGeneIdList(List<String> ensemblGeneIdList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transcript> getAllByBiotype(String biotype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transcript> getAllByBiotypeList(List<String> biotypeList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transcript> getAllByPosition(String chromosome, int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transcript> getAllByPosition(Position position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Transcript>> getAllByPositionList(List<Position> positionList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transcript> getAllByRegion(String chromosome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transcript> getAllByRegion(String chromosome, int start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transcript> getAllByRegion(String chromosome, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transcript> getAllByRegion(String chromosome, int start, int end, List<String> biotypeList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transcript> getAllByRegion(Region region) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transcript> getAllByRegion(Region region, List<String> biotypeList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Transcript>> getAllByRegionList(List<Region> regionList) {
		List<List<Transcript>> transcript = new ArrayList<List<Transcript>>(regionList.size());
		for (Region region : regionList) {
			transcript.add(getAllByRegion(region));
		}
		return transcript;
	}

	@Override
	public List<List<Transcript>> getAllByRegionList(List<Region> regionList, List<String> biotypeList) {
		List<List<Transcript>> transcript = new ArrayList<List<Transcript>>(regionList.size());
		for (Region region : regionList) {
			transcript.add(getAllByRegion(region, biotypeList));
		}
		return transcript;
	}

	@Override
	public List<Transcript> getAllByCytoband(String chromosome, String cytoband) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transcript> getAllBySnpId(String snpId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Transcript>> getAllBySnpIdList(List<String> snpIdList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transcript> getAllByEnsemblExonId(String ensemblExonId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Transcript>> getAllByEnsemblExonId(List<String> ensemblExonId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transcript> getAllByProteinName(String proteinName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Transcript>> getAllByProteinNameList(List<String> proteinNameList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transcript> getAllByMirnaMature(String mirnaID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Transcript>> getAllByMirnaMatureList(List<String> mirnaIDList) {
		// TODO Auto-generated method stub
		return null;
	}
}

package org.bioinfo.cellbase.lib.impl.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.cellbase.lib.api.XRefsDBAdaptor;
import org.bioinfo.cellbase.lib.common.XRefs;
import org.bioinfo.cellbase.lib.common.core.Gene;
import org.bioinfo.cellbase.lib.common.core.Transcript;
import org.bioinfo.cellbase.lib.common.core.Xref;
import org.bioinfo.cellbase.lib.common.core.DBname;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class XRefsMongoDBAdaptor extends MongoDBAdaptor implements XRefsDBAdaptor {
	
	
	public XRefsMongoDBAdaptor(DB db) {
		super(db);
	}

	public XRefsMongoDBAdaptor(DB db, String species, String version) {
		super(db, species, version);
		mongoDBCollection = db.getCollection("core");
	}

	@Override
	public List<DBname> getAllDBNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DBname> getAllDBNamesById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dbname> getAllDBNamesByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllIdsByDBName(String dbname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Xref> getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Xref>> getAllByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Xref> getByStartsWithQuery(String likeQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Xref>> getByStartsWithQueryList(List<String> likeQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Xref> getByStartsWithSnpQuery(String likeQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Xref>> getByStartsWithSnpQueryList(List<String> likeQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Xref> getByContainsQuery(String likeQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Xref>> getByContainsQueryList(List<String> likeQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XRefs getById(String id, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<XRefs> getAllByIdList(List<String> ids, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Xref> getByDBName(String id, String dbname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Xref>> getAllByDBName(List<String> ids, String dbname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Xref> getByDBNameList(String id, List<String> dbnames) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Xref>> getAllByDBNameList(List<String> ids, List<String> dbnames) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XRefs getByDBName(String id, String dbname, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<XRefs> getAllByDBName(List<String> ids, String dbname, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XRefs getByDBNameList(String id, List<String> dbnames, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<XRefs> getAllByDBNameList(List<String> ids, List<String> dbnames, String type) {
		// TODO Auto-generated method stub
		return null;
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
				}
			}
		} finally {
			cursor.close();
		}

		return result;
	}
	
	
}

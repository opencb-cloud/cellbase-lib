package org.bioinfo.cellbase.lib.impl.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.cellbase.lib.api.ChromosomeDBAdaptor;
import org.bioinfo.cellbase.lib.common.core.Chromosome;
import org.bioinfo.cellbase.lib.common.core.Cytoband;

import com.mongodb.DB;
import com.mongodb.DBObject;


public class ChromosomeMongoDBAdaptor extends MongoDBAdaptor implements ChromosomeDBAdaptor {

	public ChromosomeMongoDBAdaptor(DB db) {
		super(db);
	}

	public ChromosomeMongoDBAdaptor(DB db, String species, String version) {
		super(db, species, version);
		mongoDBCollection = db.getCollection("info_stats");
	}


    private List<Chromosome> executeQuery() {
//        Gson jsonObjectMapper = new Gson();
        DBObject item = mongoDBCollection.findOne();
//		System.out.println(item.toString());
//        InfoStats infoStats = (InfoStats) jsonObjectMapper.fromJson(item.toString(), InfoStats.class);

//        return infoStats.getChromosomes();
        return null;
    }

	@Override
	public Chromosome getById(String name) {
		for (Chromosome chromosome : executeQuery()) {
			if (chromosome.getName().equals(name)) {
				return chromosome;
			}
		}
		return null;
	}

	@Override
	public List<Chromosome> getAllByIdList(List<String> nameList) {
		List<Chromosome> foundList = new ArrayList<Chromosome>(nameList.size());

		for (Chromosome chromosome : executeQuery()) {
			if (nameList.contains(chromosome.getName())) {
				foundList.add(chromosome);
			}
		}
		return foundList;
	}

	@Override
	public List<Cytoband> getCytobandByName(String name) {
		for (Chromosome chromosome : executeQuery()) {
			if (chromosome.getName().equals(name)) {
				return chromosome.getCytobands();
			}
		}
		return null;
	}

	@Override
	public List<List<Cytoband>> getCytobandByNameList(List<String> nameList) {
		List<List<Cytoband>> foundLists = new ArrayList<List<Cytoband>>(nameList.size());

		for (Chromosome chromosome : executeQuery()) {
			if (nameList.contains(chromosome.getName())) {
				foundLists.add(chromosome.getCytobands());
			}
		}
		return foundLists;
	}

	@Override
	public List<Chromosome> getAll() {
		return executeQuery();
	}

	@Override
	public List<String> getChromosomeNames() {
		List<String> names = new ArrayList<String>();
		for (Chromosome chromosome : executeQuery()) {
			names.add(chromosome.getName());
		}
		return names;
	}
}

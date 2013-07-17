package org.bioinfo.cellbase.lib.impl.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.cellbase.lib.api.RegulationDBAdaptor;
import org.bioinfo.cellbase.lib.common.GenericFeature;
import org.bioinfo.cellbase.lib.common.GenericFeatureChunk;
import org.bioinfo.cellbase.lib.common.Region;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

public class RegulatoryRegionMongoDBAdaptor extends MongoDBAdaptor implements RegulationDBAdaptor {


    public RegulatoryRegionMongoDBAdaptor(DB db) {
        super(db);
    }

    public RegulatoryRegionMongoDBAdaptor(DB db, String species, String version) {
        super(db, species, version);
        mongoDBCollection = db.getCollection("regulation");
    }

    private int getChunk(int position) {
        return (position / applicationProperties.getIntProperty("CELLBASE." + version.toUpperCase()
                + ".REGULATION.CHUNK_SIZE", 2000));
    }

    private List<GenericFeatureChunk> executeQuery(DBObject query) {
        List<GenericFeatureChunk> result = null;
        logger.info(query);
        DBCursor cursor = mongoDBCollection.find(query);
        try {
            if (cursor != null) {
                result = new ArrayList<GenericFeatureChunk>(cursor.size());
//                Gson jsonObjectMapper = new Gson();
                GenericFeatureChunk chunk = null;
                while (cursor.hasNext()) {
//                    chunk = (GenericFeatureChunk) jsonObjectMapper.fromJson(cursor.next().toString(), GenericFeatureChunk.class);
                    result.add(chunk);
                }
            }
        } finally {
            cursor.close();
        }
        return result;
    }


    @Override
    public List<GenericFeature> getByRegion(String chromosome, int start, int end, List<String> types) {
        // positions below 1 are not allowed
        if (start < 1) {
            start = 1;
        }
        if (end < 1) {
            end = 1;
        }
        QueryBuilder builder = QueryBuilder.start("chromosome").is(chromosome.trim()).and("chunkId")
                .greaterThanEquals(getChunk(start)).lessThanEquals(getChunk(end));


        System.out.println(builder.get().toString());
        List<GenericFeatureChunk> chunkList = executeQuery(builder.get());
        List<GenericFeature> featureList = new ArrayList<>();
        for (GenericFeatureChunk chunk : chunkList) {
            if(types.isEmpty()){
                featureList.addAll(chunk.getFeatures());
            }else{
                for(GenericFeature genericFeature: chunk.getFeatures()){
                    if(types.contains(genericFeature.getFeatureType())){
                        featureList.add(genericFeature);
                    }
                }
            }
        }
        return featureList;
    }

    @Override
    public List<List<GenericFeature>> getByRegionList(List<Region> regions) {
        return this.getByRegionList(regions, new ArrayList<String>());
    }

    @Override
    public List<List<GenericFeature>> getByRegionList(List<Region> regions, List<String> types) {
        List<List<GenericFeature>> result = new ArrayList<List<GenericFeature>>(regions.size());
        for (Region region : regions) {
            result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd(), types));
        }
        return result;
    }

}

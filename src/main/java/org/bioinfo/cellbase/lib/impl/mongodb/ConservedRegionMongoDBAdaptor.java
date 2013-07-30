package org.bioinfo.cellbase.lib.impl.mongodb;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.*;
import org.bioinfo.cellbase.lib.api.ConservedRegionDBAdaptor;
import org.bioinfo.cellbase.lib.common.ConservedRegion;
import org.bioinfo.cellbase.lib.common.Region;

import org.bioinfo.cellbase.lib.impl.dbquery.QueryOptions;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResponse;

@Deprecated
public class ConservedRegionMongoDBAdaptor extends MongoDBAdaptor implements ConservedRegionDBAdaptor {

    private static int CHUNKSIZE = 2000;

    public ConservedRegionMongoDBAdaptor(DB db) {
        super(db);
    }

    public ConservedRegionMongoDBAdaptor(DB db, String species, String version) {
        super(db, species, version);
        CHUNKSIZE = applicationProperties.getIntProperty("CELLBASE." + version.toUpperCase() + ".REGULATION.CHUNK_SIZE", 2000);
        mongoDBCollection = db.getCollection("conserved_region");
    }


    @Override
    public QueryResponse getAllByRegion(Region region, QueryOptions options) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResponse getAllByRegionList(List<Region> regionList, QueryOptions options) {

        List<DBObject> queries = new ArrayList<>();
        List<String> ids = new ArrayList<>(regionList.size());
        for (Region region : regionList) {
            int firstChunkId = getChunkId(region.getStart(), CHUNKSIZE);
            int lastChunkId = getChunkId(region.getEnd(), CHUNKSIZE);
            BasicDBList chunksId = new BasicDBList();
            for(int j=firstChunkId; j<=lastChunkId; j++) {
                int chunkId = j;
                chunksId.add(chunkId);
            }

            QueryBuilder builder = QueryBuilder.start("chromosome").is(region.getChromosome()).and("chunkId").in(chunksId).and("start").lessThanEquals(region.getEnd()).and("end").greaterThanEquals(region.getStart());
            System.out.println("Query: " + builder.get());
            queries.add(builder.get());
            ids.add(region.toString());
        }
        options = addExcludeReturnFields("chunkIds", options);
        return executeQueryList(ids, queries, options);
    }



//    private List<ConservedRegion> executeQuery(DBObject query) {
//        List<ConservedRegion> result = null;
//        DBCursor cursor = mongoDBCollection.find(query);
//        try {
//            if (cursor != null) {
//                result = new ArrayList<ConservedRegion>(cursor.size());
////                Gson jsonObjectMapper = new Gson();
//                ConservedRegion feature = null;
//                while (cursor.hasNext()) {
////                    feature = (ConservedRegion) jsonObjectMapper.fromJson(cursor.next().toString(), ConservedRegion.class);
//                    result.add(feature);
//                }
//            }
//        } finally {
//            cursor.close();
//        }
//        return result;
//    }


//    @Override
//    public List<ConservedRegion> getByRegion(String chromosome, int start, int end) {
//        // positions below 1 are not allowed
//        if (start < 1) {
//            start = 1;
//        }
//        if (end < 1) {
//            end = 1;
//        }
//        QueryBuilder builder = QueryBuilder.start("chromosome").is(chromosome).and("end")
//                .greaterThan(start).and("start").lessThan(end);
//
//        System.out.println(builder.get().toString());
//        List<ConservedRegion> conservedRegionList = executeQuery(builder.get());
//
//        return conservedRegionList;
//    }

//    @Override
//    public List<List<ConservedRegion>> getByRegionList(List<Region> regions) {
//        List<List<ConservedRegion>> result = new ArrayList<List<ConservedRegion>>(regions.size());
//        for (Region region : regions) {
//            result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd()));
//        }
//        return result;
//    }
private static int getChunkId(int position, int chunksize) {
    if (chunksize <= 0) {
        return position / CHUNKSIZE;
    } else {
        return position / chunksize;
    }
}

    private static int getChunkStart(int id, int chunksize) {
        if (chunksize <= 0) {
            return (id == 0) ? 1 : id * CHUNKSIZE;
        } else {
            return (id == 0) ? 1 : id * chunksize;
        }
    }

    private static int getChunkEnd(int id, int chunksize) {
        if (chunksize <= 0) {
            return (id * CHUNKSIZE) + CHUNKSIZE - 1;
        } else {
            return (id * chunksize) + chunksize - 1;
        }
    }

}

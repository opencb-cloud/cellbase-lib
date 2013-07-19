package org.bioinfo.cellbase.lib.impl.mongodb.RegulatoryRegion;

import com.mongodb.*;
import org.bioinfo.cellbase.lib.api.RegulatoryRegion.RegulatoryRegionDBAdaptor;
import org.bioinfo.cellbase.lib.common.Position;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryOptions;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResponse;
import org.bioinfo.cellbase.lib.impl.mongodb.MongoDBAdaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: fsalavert
 * Date: 7/18/13
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegulatoryRegionMongoDBAdaptor extends MongoDBAdaptor implements RegulatoryRegionDBAdaptor {

    private static int CHUNKSIZE = 2000;
    public RegulatoryRegionMongoDBAdaptor(DB db) {
        super(db);
    }

    public RegulatoryRegionMongoDBAdaptor(DB db, String species, String version) {
        super(db, species, version);
        mongoDBCollection = db.getCollection("regulatory_region");
    }

    @Override
    public QueryResponse getAllById(String id, QueryOptions options) {
        QueryBuilder builder = QueryBuilder.start("name").is(id);

//        System.out.println("Query: " + builder.get());

        options = addExcludeReturnFields("chunkIds", options);
        return executeQuery("result", builder.get(), options);
    }

    @Override
    public QueryResponse getAllByIdList(List<String> idList, QueryOptions options) {
        List<DBObject> queries = new ArrayList<>();
        for (String id : idList) {
            QueryBuilder builder = QueryBuilder.start("name").is(id);
            queries.add(builder.get());
        }
        options = addExcludeReturnFields("chunkIds", options);
        return executeQueryList(idList, queries, options);
    }

    @Override
    public QueryResponse getAllByPosition(Position position, QueryOptions options) {
        //  db.regulatory_region.find({"chunkIds": {$in:["1_200", "1_300"]}, "start": 601156})
        String chunkId = position.getChromosome() +"_"+ getChunkId(position.getPosition(), CHUNKSIZE);
        BasicDBList chunksId = new BasicDBList();
        chunksId.add(chunkId);

        QueryBuilder builder = QueryBuilder.start("chunkIds").in(chunksId).and("start").is(position.getPosition());

//        System.out.println("Query: " + builder.get());

        options = addExcludeReturnFields("chunkIds", options);
        return executeQuery("result", builder.get(), options);
    }

    @Override
    public QueryResponse getAllByPositionList(List<Position> positionList, QueryOptions options) {
        //  db.regulatory_region.find({"chunkIds": {$in:["1_200", "1_300"]}, "start": 601156})
        List<DBObject> queries = new ArrayList<>();
        for (Position position : positionList){
            String chunkId = position.getChromosome() +"_"+ getChunkId(position.getPosition(), CHUNKSIZE);
            BasicDBList chunksId = new BasicDBList();
            chunksId.add(chunkId);

            QueryBuilder builder = QueryBuilder.start("chunkIds").in(chunksId).and("start").is(position.getPosition());
            queries.add(builder.get());
        }

        System.out.println("Query: " + queries);

        options = addExcludeReturnFields("chunkIds", options);
        return executeQueryList(positionList, queries, options);
    }






    @Override
    public QueryResponse getAllByRegion(String chromosome, int start, int end, QueryOptions options) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResponse getAllByRegion(Region region, QueryOptions options) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResponse getAllByRegionList(List<Region> regions, QueryOptions options) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResponse getAll(QueryOptions options) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResponse next(String id, QueryOptions options) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResponse next(String chromosome, int position, QueryOptions options) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> getAllIds() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<String, Object> getInfo(String id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Map<String, Object>> getInfoByIdList(List<String> idList) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<String, Object> getFullInfo(String id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Map<String, Object>> getFullInfoByIdList(List<String> idList) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Region getRegionById(String id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Region> getAllRegionsByIdList(List<String> idList) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getSequenceById(String id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> getAllSequencesByIdList(List<String> idList) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

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

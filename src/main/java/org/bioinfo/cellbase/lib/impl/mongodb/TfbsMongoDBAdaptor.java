package org.bioinfo.cellbase.lib.impl.mongodb;

import com.mongodb.*;
import org.bioinfo.cellbase.lib.api.TfbsDBAdaptor;
import org.bioinfo.cellbase.lib.common.IntervalFeatureFrequency;
import org.bioinfo.cellbase.lib.common.Position;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryOptions;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResponse;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mbleda
 * Date: 7/17/13
 * Time: 11:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class TfbsMongoDBAdaptor extends MongoDBAdaptor implements TfbsDBAdaptor {

    private static int CHUNKSIZE= 2000;

    public TfbsMongoDBAdaptor(DB db) {
        super(db);
    }

    public TfbsMongoDBAdaptor(DB db, String species, String version) {
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
    public QueryResponse getAllByTargetGeneId(String targetGeneId, QueryOptions options) {
        DBCollection coreMongoDBCollection = db.getCollection("core");

        DBObject[] commands = new DBObject[3];
        DBObject match = new BasicDBObject("$match", new BasicDBObject("transcripts.xrefs.id", targetGeneId));
        DBObject unwind = new BasicDBObject("$unwind", "$transcripts");


        BasicDBObject projectObj = new BasicDBObject("_id", 0);
        projectObj.append("transcripts.id", 1);
        projectObj.append("transcripts.tfbs", 1);
        DBObject project = new BasicDBObject("$project", projectObj);

        commands[0] = match;
        commands[1] = unwind;
        commands[2] = project;

        QueryResponse queryResponse = executeAggregation(targetGeneId, commands, options, coreMongoDBCollection);

        QueryResult queryResult = (QueryResult) queryResponse.get(targetGeneId);
        BasicDBList list = (BasicDBList) queryResult.get("result");

        for (int i = 0; i < list.size(); i++) {
            BasicDBObject gene = (BasicDBObject) list.get(i);
            BasicDBObject transcript = (BasicDBObject) gene.get("transcripts");
            String transcriptId = transcript.getString("id");
            if (transcriptId.toUpperCase().equals(targetGeneId)) {
                BasicDBList tfbs = (BasicDBList) transcript.get("tfbs");
                queryResult.setResult(tfbs);
                break;
            }
        }

        return queryResponse;
    }

    @Override
    public QueryResponse getAllByTargetGeneIdList(List<String> targetGeneIdList, QueryOptions options) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResponse getAllByJasparId(String jasparId, QueryOptions options) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResponse getAllByJasparIdList(List<String> jasparIdList, QueryOptions options) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

//    @Override
//    public QueryResponse getAllByPosition(String chromosome, int position, QueryOptions options) {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }

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
    public List<Object> getAllAnnotation() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Object> getAllAnnotationByCellTypeList(List<String> cellTypes) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<IntervalFeatureFrequency> getAllTfIntervalFrequencies(Region region, int interval) {
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

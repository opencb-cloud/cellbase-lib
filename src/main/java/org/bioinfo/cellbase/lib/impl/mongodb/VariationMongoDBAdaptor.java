package org.bioinfo.cellbase.lib.impl.mongodb;

import com.google.gson.Gson;
import com.mongodb.*;
import org.bioinfo.cellbase.lib.api.VariationDBAdaptor;
import org.bioinfo.cellbase.lib.common.GenericFeature;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.common.core.Gene;
import org.bioinfo.cellbase.lib.common.variation.TranscriptVariation;
import org.bioinfo.cellbase.lib.common.variation.Variation;

import java.util.*;

public class VariationMongoDBAdaptor extends MongoDBAdaptor implements VariationDBAdaptor {


    public VariationMongoDBAdaptor(DB db) {
        super(db);
    }

    public VariationMongoDBAdaptor(DB db, String species, String version) {
        super(db, species, version);
        mongoDBCollection = db.getCollection("variation");
    }

    private List<Variation> executeQuery(DBObject query, List<String> excludeFields) {
        List<Variation> result = null;

        DBCursor cursor = null;
        if (excludeFields != null && excludeFields.size() > 0) {
            BasicDBObject returnFields = new BasicDBObject();
                returnFields.put("_id", 0);
            for (String field : excludeFields) {
                returnFields.put(field, 0);
            }
            System.out.println(query);
            System.out.println(returnFields);
            cursor = mongoDBCollection.find(query, returnFields);

        } else {
            System.out.println(query);
            cursor = mongoDBCollection.find(query);
        }


        try {
            if (cursor != null) {
                result = new ArrayList<Variation>(cursor.size());
                Gson gson = new Gson();
                Variation variation;
                while (cursor.hasNext()) {
                    variation = (Variation) gson.fromJson(cursor.next().toString(), Variation.class);
                    result.add(variation);
                }
            }
        } finally {
            cursor.close();
        }
        return result;
    }


    @Override
    public List<Variation> getByRegion(String chromosome, int start, int end, List<String> consequence_types, List<String> exclude) {
        // positions below 1 are not allowed
        if (start < 1) {
            start = 1;
        }
        if (end < 1) {
            end = 1;
        }
        QueryBuilder builder = QueryBuilder.start("chromosome").is(chromosome.trim()).and("start").greaterThanEquals(start).lessThanEquals(end);

        List<Variation> variationList = executeQuery(builder.get(), exclude);
        List<Variation> filteredList  = new ArrayList<>();
        if(consequence_types == null){
            return variationList;
        }else{
            for (Variation variation : variationList) {
               List<TranscriptVariation> transcriptVariationList = variation.getTranscriptVariations();
                for(TranscriptVariation transcriptVariation : transcriptVariationList){
                    List<String> consequenceTypes = transcriptVariation.getConsequenceTypes();
                    for(String consequence_type : consequence_types){
                        if(consequenceTypes.contains(consequence_type)){
                            filteredList.add(variation);
                            break;
                        }
                    }
                }
            }
        }
        return filteredList;
    }

    @Override
    public List<List<Variation>> getByRegionList(List<Region> regions, List<String> exclude) {
        return this.getByRegionList(regions, null, exclude);
    }

    @Override
    public List<List<Variation>> getByRegionList(List<Region> regions, List<String> consequence_types, List<String> exclude) {
        List<List<Variation>> result = new ArrayList<List<Variation>>(regions.size());
        for (Region region : regions) {
            result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd(), consequence_types, exclude));
        }
        return result;
    }


    @Override
    public List<Variation> getById(String id, List<String> exclude) {
        QueryBuilder builder = QueryBuilder.start("id").is(id);
        System.out.println(builder.get().toString());
        List<Variation> variationList = executeQuery(builder.get(), exclude);
        return variationList;
    }

    @Override
    public List<List<Variation>> getByIdList(List<String> idList, List<String> exclude) {
        List<List<Variation>> result = new ArrayList<List<Variation>>(idList.size());
        for (String id : idList) {
            result.add(getById(id, exclude));
        }
        return result;
    }


    public String getAllIntervalFrequencies(Region region, int interval) {
//    db.variation.aggregate({$match: {$and: [{chromosome: "1"}, {start: {$gt: 251391, $lt: 2701391}}]}}, {$group: {_id: {$subtract: [{$divide: ["$start", 40000]}, {$divide: [{$mod: ["$start", 40000]}, 40000]}]}, totalCount: {$sum: 1}}})
//        {
//            $match: {
//                $and: [{
//                    chromosome: "1"
//                }, {
//                    start: {
//                        $gt: 251391,
//                                $lt: 2701391
//                    }
//                }
//                ]
//            }
//        }, {
//            $group: {
//                _id: {
//                    $subtract: [{
//                        $divide: ["$start", 40000]
//                    }, {
//                        $divide: [{
//                            $mod: ["$start", 40000]
//                        },
//                        40000
//                        ]
//                    }
//                    ]
//                },
//                totalCount: {
//                    $sum: 1
//                }
//            }
//        }


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
//        query.put("accountId", accountId);
//        query.put("password", password);


//        userCollection.update(query, action);
//



/***************************/
//        QueryBuilder builder = QueryBuilder.start("chromosome").is(region.getChromosome()).and("end")
//                .greaterThan(region.getStart()).and("start").lessThan(region.getEnd());
//
//        int numIntervals = (region.getEnd() - region.getStart()) / interval + 1;
//        int[] intervalCount = new int[numIntervals];
//
//        List<Variation> variationList = executeQuery(builder.get(), Arrays.asList("id,chromosome,end,strand,type,reference,alternate,alleleString,species,assembly,source,version,transcriptVariations,xrefs,featureId,featureAlias,variantFreq,validationStatus"));
//
//        System.out.println("Variation index");
//        System.out.println("numIntervals: " + numIntervals);
//        for (Variation variation : variationList) {
//            System.out.print("gsnp start:" + variation.getStart() + " ");
//            if (variation.getStart() >= region.getStart() && variation.getStart() <= region.getEnd()) {
//                int intervalIndex = (variation.getStart() - region.getStart()) / interval; // truncate
//                System.out.print(intervalIndex + " ");
//                intervalCount[intervalIndex]++;
//            }
//        }
//        System.out.println("Variation index");
//
//        int intervalStart = region.getStart();
//        int intervalEnd = intervalStart + interval - 1;
//        BasicDBList intervalList = new BasicDBList();
//        for (int i = 0; i < numIntervals; i++) {
//            BasicDBObject intervalObj = new BasicDBObject();
//            intervalObj.put("start", intervalStart);
//            intervalObj.put("end", intervalEnd);
//            intervalObj.put("interval", i);
//            intervalObj.put("value", intervalCount[i]);
//            intervalList.add(intervalObj);
//            intervalStart = intervalEnd + 1;
//            intervalEnd = intervalStart + interval - 1;
//        }
//
//        System.out.println(region.getChromosome());
//        System.out.println(region.getStart());
//        System.out.println(region.getEnd());
//        return intervalList.toString();
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

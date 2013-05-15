package org.bioinfo.cellbase.lib.impl.mongodb;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import org.bioinfo.cellbase.lib.api.ConservedRegionDBAdaptor;
import org.bioinfo.cellbase.lib.common.ConservedRegion;
import org.bioinfo.cellbase.lib.common.Region;

import java.util.ArrayList;
import java.util.List;

public class ConservedRegionMongoDBAdaptor extends MongoDBAdaptor implements ConservedRegionDBAdaptor {


    int CHUNKSIZE;

    public ConservedRegionMongoDBAdaptor(DB db) {
        super(db);
    }

    public ConservedRegionMongoDBAdaptor(DB db, String species, String version) {
        super(db, species, version);
        CHUNKSIZE = applicationProperties.getIntProperty("CELLBASE." + version.toUpperCase() + ".REGULATION.CHUNK_SIZE", 2000);
        mongoDBCollection = db.getCollection("conservation");
    }

    private List<ConservedRegion> executeQuery(DBObject query) {
        List<ConservedRegion> result = null;
        DBCursor cursor = mongoDBCollection.find(query);
        try {
            if (cursor != null) {
                result = new ArrayList<ConservedRegion>(cursor.size());
                Gson gson = new Gson();
                ConservedRegion feature;
                while (cursor.hasNext()) {
                    feature = (ConservedRegion) gson.fromJson(cursor.next().toString(), ConservedRegion.class);
                    result.add(feature);
                }
            }
        } finally {
            cursor.close();
        }
        return result;
    }


    @Override
    public List<ConservedRegion> getByRegion(String chromosome, int start, int end) {
        // positions below 1 are not allowed
        if (start < 1) {
            start = 1;
        }
        if (end < 1) {
            end = 1;
        }
        QueryBuilder builder = QueryBuilder.start("chromosome").is(chromosome).and("end")
                .greaterThan(start).and("start").lessThan(end);

        System.out.println(builder.get().toString());
        List<ConservedRegion> conservedRegionList = executeQuery(builder.get());

        return conservedRegionList;
    }

    @Override
    public List<List<ConservedRegion>> getByRegionList(List<Region> regions) {
        List<List<ConservedRegion>> result = new ArrayList<List<ConservedRegion>>(regions.size());
        for (Region region : regions) {
            result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd()));
        }
        return result;
    }
}

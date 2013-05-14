package org.bioinfo.cellbase.lib.impl.mongodb;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import org.bioinfo.cellbase.lib.api.RegulationDBAdaptor;
import org.bioinfo.cellbase.lib.api.VariationDBAdaptor;
import org.bioinfo.cellbase.lib.common.GenericFeature;
import org.bioinfo.cellbase.lib.common.GenericFeatureChunk;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.common.variation.Variation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VariationMongoDBAdaptor extends MongoDBAdaptor implements VariationDBAdaptor {


    public VariationMongoDBAdaptor(DB db) {
        super(db);
    }

    public VariationMongoDBAdaptor(DB db, String species, String version) {
        super(db, species, version);
        mongoDBCollection = db.getCollection("variation");
    }

    private int getChunk(int position) {
        return (position / applicationProperties.getIntProperty("CELLBASE." + version.toUpperCase()
                + ".REGULATION.CHUNK_SIZE", 2000));
    }

    private List<Variation> executeQuery(DBObject query) {
        List<Variation> result = null;
        DBCursor cursor = mongoDBCollection.find(query);
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
    public List<Variation> getByRegion(String chromosome, int start, int end, List<String> consequence_types) {
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
        List<Variation> variationList = executeQuery(builder.get());
        List<Variation> filteredList  = new ArrayList<>();
        if(consequence_types.isEmpty()){
            return variationList;
        }else{
            for (Variation variation : variationList) {
               List<TranscriptVariation> transcriptVariationList = variation.getTranscriptVariations();
                for(TranscriptVariation transcriptVariation : transcriptVariationList){
                    List<String> consequenceTypes = transcriptVariation.getConsequenceTypes();
                    Collection<String> intersection = consequenceTypes.retainAll(consequence_types);
                    if(!intersection.isEmpty()){
                        filteredList.add(variation);
                    }
                }
            }
        }
        return filteredList;
    }

    @Override
    public List<List<GenericFeature>> getByRegionList(List<Region> regions) {
        return this.getByRegionList(regions, new ArrayList<String>());
    }

    @Override
    public List<List<Variation>> getByRegionList(List<Region> regions, List<String> consequence_types) {
        List<List<Variation>> result = new ArrayList<List<Variation>>(regions.size());
        for (Region region : regions) {
            result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd(), consequence_types));
        }
        return result;
    }

}

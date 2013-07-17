package org.bioinfo.cellbase.lib.impl.mongodb;

import com.mongodb.DB;
import org.bioinfo.cellbase.lib.api.TfbsDBAdaptor;
import org.bioinfo.cellbase.lib.common.IntervalFeatureFrequency;
import org.bioinfo.cellbase.lib.common.Position;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryOptions;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResponse;

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

     public TfbsMongoDBAdaptor(DB db) {
        super(db);
    }

    public TfbsMongoDBAdaptor(DB db, String species, String version) {
        super(db, species, version);
        mongoDBCollection = db.getCollection("regulatory_region");
    }

    @Override
    public QueryResponse getAllById(String id, QueryOptions options) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResponse getAllByIdList(List<String> idList, QueryOptions options) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResponse getAllByTargetGeneId(String targetGeneId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResponse getAllByTargetGeneIdList(List<String> targetGeneIdList) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResponse getAllByJasparId(String jasparId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResponse getAllByJasparIdList(List<String> jasparIdList) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResponse getAllByPosition(String chromosome, int position, QueryOptions options) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResponse getAllByPosition(Position position, QueryOptions options) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResponse getAllByPositionList(List<Position> positionList, QueryOptions options) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
}

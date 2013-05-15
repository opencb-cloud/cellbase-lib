package org.bioinfo.cellbase.lib.api;

import org.bioinfo.cellbase.lib.common.GenericFeature;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.common.variation.Variation;

import java.util.List;

public interface VariationDBAdaptor {

    public List<Variation> getById(String id);

    List<List<Variation>> getByIdList(List<String> idList);

    public List<Variation> getByRegion(String chromosome, int start, int end, List<String> consequence_types);

    public List<List<Variation>> getByRegionList(List<Region> regions);

    public List<List<Variation>> getByRegionList(List<Region> regions, List<String> consequence_types);

}

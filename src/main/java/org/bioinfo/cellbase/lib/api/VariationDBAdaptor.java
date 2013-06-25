package org.bioinfo.cellbase.lib.api;

import org.bioinfo.cellbase.lib.common.GenericFeature;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.common.variation.Variation;

import java.util.List;

public interface VariationDBAdaptor {

    public List<Variation> getById(String id, List<String> exclude);

    List<List<Variation>> getByIdList(List<String> idList, List<String> exclude);

    public List<Variation> getByRegion(String chromosome, int start, int end, List<String> consequence_types, List<String> exclude);

    public List<List<Variation>> getByRegionList(List<Region> regions, List<String> exclude);

    public List<List<Variation>> getByRegionList(List<Region> regions, List<String> consequence_types, List<String> exclude);

    public String getAllIntervalFrequencies(Region region, int interval);

}

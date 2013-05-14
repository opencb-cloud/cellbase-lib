package org.bioinfo.cellbase.lib.api;

import org.bioinfo.cellbase.lib.common.GenericFeature;
import org.bioinfo.cellbase.lib.common.Region;

import java.util.List;

public interface VariationDBAdaptor {

    public List<GenericFeature> getByRegion(String chromosome, int start, int end, List<String> consequence_types);

    public List<List<GenericFeature>> getByRegionList(List<Region> regions);

    public List<List<GenericFeature>> getByRegionList(List<Region> regions, List<String> consequence_types);


}

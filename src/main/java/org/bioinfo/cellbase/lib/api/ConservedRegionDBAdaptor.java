package org.bioinfo.cellbase.lib.api;

import org.bioinfo.cellbase.lib.common.ConservedRegion;
import org.bioinfo.cellbase.lib.common.Region;

import java.util.List;

public interface ConservedRegionDBAdaptor {

    public List<ConservedRegion> getByRegion(String chromosome, int start, int end);

    public List<List<ConservedRegion>> getByRegionList(List<Region> regions);


}

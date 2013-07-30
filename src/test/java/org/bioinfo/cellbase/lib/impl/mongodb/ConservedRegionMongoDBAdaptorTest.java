package org.bioinfo.cellbase.lib.impl.mongodb;

import org.bioinfo.cellbase.lib.api.ConservedRegionDBAdaptor;
import org.bioinfo.cellbase.lib.common.ConservedRegion;
import org.bioinfo.cellbase.lib.common.DataSourceStats;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.impl.DBAdaptorFactory;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryOptions;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ConservedRegionMongoDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFactory = new MongoDBAdaptorFactory();
    ConservedRegionDBAdaptor conservedRegionDBAdaptor;
	private String species = "hsa";
	private String version = "v3";

	@Before
	public void beforeTestStart() {
        conservedRegionDBAdaptor = dbAdaptorFactory.getConservedRegionDBAdaptor(this.species, this.version);
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetByRegionList() {
        List<Region> regions = Region.parseRegions("2:11517-30000");
        QueryResponse qr = conservedRegionDBAdaptor.getAllByRegionList(regions, new QueryOptions());
        System.out.println(qr.toJson());
	}
	

	
}

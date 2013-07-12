package org.bioinfo.cellbase.lib.impl.mongodb;

import org.bioinfo.cellbase.lib.api.ConservedRegionDBAdaptor;
import org.bioinfo.cellbase.lib.impl.DBAdaptorFactory;
import org.junit.After;
import org.junit.Before;

public class ChromosomeMongoDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFactory = new MongoDBAdaptorFactory();
    ConservedRegionDBAdaptor conservedRegionDBAdaptor;
	private String species = "hsa";
	private String version = "v3";

	@Before
	public void beforeTestStart() {
//        conservedRegionDBAdaptor = dbAdaptorFactory.getConservedRegionDBAdaptor(this.species, this.version);
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testGetByRegionList() {
//        List<Region> regions = Region.parseRegions("13:19020001-19020002");
//        int strand = 1;
//        List<List<ConservedRegion>> a = conservedRegionDBAdaptor.getByRegionList(regions);
//        System.out.println(gson.toJson(a));
//	}
	

	
}

package org.bioinfo.cellbase.lib.impl.mongodb;

import org.bioinfo.cellbase.lib.api.variation.VariationDBAdaptor;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.common.variation.Variation;
import org.bioinfo.cellbase.lib.impl.DBAdaptorFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class VariationMongoDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new MongoDBAdaptorFactory();
	private VariationDBAdaptor variationDBAdaptor;
	private long startExecTime;
	private String species = "hsapiens";

	@Before
	public void beforeTestStart() {
		variationDBAdaptor = dbAdaptorFact.getVariationDBAdaptor(species, "v3");
		startExecTime = System.currentTimeMillis();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllByRegionTest() {
		List<Variation> variations = variationDBAdaptor.getByRegion("13", 32889607,32889731, null, Arrays.asList("transcriptVariations,id".split(",")));
//        System.out.println(gson.toJson(variations));
	}

    @Test
    public void testInterval() {
        Region region = new Region("20",25680000,40069999);
//        Region region = new Region("1",251391,2701391);
//        Region region = new Region("13",32889611,32973805);
        System.out.println(variationDBAdaptor.getAllIntervalFrequencies(region, 8311));
    }
}

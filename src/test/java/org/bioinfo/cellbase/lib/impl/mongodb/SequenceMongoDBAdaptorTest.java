package org.bioinfo.cellbase.lib.impl.mongodb;

import org.bioinfo.cellbase.lib.api.ChromosomeDBAdaptor;
import org.bioinfo.cellbase.lib.api.ConservedRegionDBAdaptor;
import org.bioinfo.cellbase.lib.api.GenomeSequenceDBAdaptor;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.impl.DBAdaptorFactory;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryOptions;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResponse;
import org.bioinfo.commons.utils.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SequenceMongoDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFactory = new MongoDBAdaptorFactory();
    GenomeSequenceMongoDBAdaptor genomeSequenceMongoDBAdaptor;
	private String species = "hsapiens";
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

	@Test
	public void testGetByRegionList() {

        GenomeSequenceDBAdaptor dbAdaptor = dbAdaptorFactory.getGenomeSequenceDBAdaptor(this.species, this.version);
        List<Region> regions = new ArrayList<>();
        regions.add(new Region("20", 32877847, 32878707));
        System.out.println(dbAdaptor.getAllByRegionList(regions, new QueryOptions()));

	}
	

	
}

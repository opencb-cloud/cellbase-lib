package org.bioinfo.cellbase.lib.impl.mongo;

import com.google.gson.Gson;
import org.bioinfo.cellbase.lib.api.GeneDBAdaptor;
import org.bioinfo.cellbase.lib.api.GenomeSequenceDBAdaptor;
import org.bioinfo.cellbase.lib.common.GenomeSequenceFeature;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.common.core.Gene;
import org.bioinfo.cellbase.lib.impl.DBAdaptorFactory;
import org.bioinfo.cellbase.lib.impl.mongodb.MongoDBAdaptorFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GenomeSequenceMongoDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFactory = new MongoDBAdaptorFactory();
    GenomeSequenceDBAdaptor genomeSequenceDBAdaptor;
	private String species = "hsa";
	private String version = "v3";
    Gson gson = new Gson();

	@Before
	public void beforeTestStart() {
        genomeSequenceDBAdaptor = dbAdaptorFactory.getGenomeSequenceDBAdaptor(this.species, this.version);
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetByRegionList() {
        List<Region> regions = Region.parseRegions("13:1998-1999,13:1999-2000");
        int strand = 1;
        List<GenomeSequenceFeature> a = genomeSequenceDBAdaptor.getAllByRegionList(regions, strand);
        System.out.println(gson.toJson(a));
	}
	

	
}

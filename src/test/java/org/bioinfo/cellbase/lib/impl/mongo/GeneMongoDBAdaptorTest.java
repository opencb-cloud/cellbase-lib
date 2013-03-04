package org.bioinfo.cellbase.lib.impl.mongo;

import java.util.List;

import org.bioinfo.cellbase.lib.api.GeneDBAdaptor;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.impl.DBAdaptorFactory;
import org.bioinfo.cellbase.lib.impl.mongodb.MongoDBAdaptorFactory;
import org.bioinfo.cellbase.lib.io.output.StringWriter;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeneMongoDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new MongoDBAdaptorFactory();
	private GeneDBAdaptor geneDBAdaptor;
	private long startExecTime;
	private String species = "hsa";
	
	@Before
	public void beforeTestStart() {
		geneDBAdaptor = dbAdaptorFact.getGeneDBAdaptor(species, "V3");
		startExecTime = System.currentTimeMillis();
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public void testGetAll() {
		System.out.println(geneDBAdaptor.getAll().get(0).toString());
	}

	@Test
	public void testGetAllEnsemblIds() {
		System.out.println(geneDBAdaptor.getAllEnsemblIds().size());
	}
	
	@Test
	public void testGetAllByRegionTest() {
		List<Gene> genes = geneDBAdaptor.getAllByRegion(new Region("2", 1000, 50000));
		for(Gene gene: genes) {
			
			System.out.println(StringWriter.serialize(gene));			
		}
	}
	
	@Test
	public void testGetAllByBiotypeTest() {
		List<Gene> genes = geneDBAdaptor.getAllByBiotype("protein_coding");
//		for(Gene gene: genes) {
//			
//			System.out.println(StringWriter.serialize(gene));			
//		}
	}
	
}

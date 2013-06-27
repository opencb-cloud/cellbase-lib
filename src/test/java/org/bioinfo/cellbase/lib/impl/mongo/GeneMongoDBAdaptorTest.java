package org.bioinfo.cellbase.lib.impl.mongo;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import org.bioinfo.cellbase.lib.api.GeneDBAdaptor;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.common.core.Gene;
import org.bioinfo.cellbase.lib.impl.DBAdaptorFactory;
import org.bioinfo.cellbase.lib.impl.mongodb.MongoDBAdaptorFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeneMongoDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new MongoDBAdaptorFactory();
	private GeneDBAdaptor geneDBAdaptor;
	private long startExecTime;
	private String species = "hsapiens";
	
	@Before
	public void beforeTestStart() {
		geneDBAdaptor = dbAdaptorFact.getGeneDBAdaptor(species, "v3");
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
		List<Gene> genes = geneDBAdaptor.getAllByRegion(new Region("2", 1000, 90000), true);
		for(Gene gene: genes) {
			System.out.println(gene.toString());			
		}
		
		genes = geneDBAdaptor.getAllByRegion(new Region("2", 1000, 90000), true);
		for(Gene gene: genes) {
			System.out.println(gene.toString());			
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


    @Test
    public void testXref() {
//        List<List<Gene>> genes = geneDBAdaptor.getByXrefList(Arrays.asList("brca2"));
//        List<Gene> genes2 = geneDBAdaptor.getByXref("brca2");
//        System.out.println(genes.size());
//        System.out.println(genes.get(0));
//        System.out.println(genes2.size());
//		for(Gene gene: genes2) {
//			System.out.println(gene);
//		}
        String str = "a,b, ";

        System.out.println(Arrays.asList(str.split(",")));

    }
}

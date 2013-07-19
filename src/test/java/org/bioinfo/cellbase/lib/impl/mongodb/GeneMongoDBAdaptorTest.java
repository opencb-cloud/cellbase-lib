package org.bioinfo.cellbase.lib.impl.mongodb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bioinfo.cellbase.lib.api.GeneDBAdaptor;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.impl.DBAdaptorFactory;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryOptions;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResponse;
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
		System.out.println(geneDBAdaptor.getAll(null).get(0).toString());
	}

    @Test
    public void testNext() {
//        QueryResponse qr = geneDBAdaptor.next("BRCA2", new QueryOptions());
        QueryOptions qo =  new QueryOptions("transcripts", false);
        qo.put("strand", "1");
        QueryResponse qr = geneDBAdaptor.next("BRCA2", qo);
//        System.out.println(qr.toJson());
        System.out.println("");

    }

	@Test
	public void testGetAllBYId() {
		QueryResponse qr = geneDBAdaptor.getAllById("BRCA2", new QueryOptions("transcripts", false));
		System.out.println(qr.toJson());
		
		qr = geneDBAdaptor.getAllByIdList(Arrays.asList("SNORA16", "BRCA2"), new QueryOptions("transcripts", false));
		System.out.println(qr.toJson());
	}
	
	@Test
	public void testGetAllByRegionTest() {
//		QueryResponse qr = geneDBAdaptor.getAllByRegion(new Region("2", 1000, 11190000), new QueryOptions("transcripts", false));
//		System.out.println(qr.toJson());
		
		
		List<Region> regions = new ArrayList<>();
		regions.add(new Region("2", 1000, 1190000));
		regions.add(new Region("12", 5000, 1190000));
		QueryResponse qr = geneDBAdaptor.getAllByRegionList(regions, new QueryOptions("{'transcripts': false, 'metadata': false, 'exclude': 'id'}"));
		System.out.println(qr.toJson());
		
//		for(Gene gene: genes) {
//			System.out.println(gene.toString());			
//		}
		
	}
	
	@Test
	public void testGetAllByBiotypeTest() {
//		List<Gene> genes = geneDBAdaptor.getAllByBiotype("protein_coding");
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

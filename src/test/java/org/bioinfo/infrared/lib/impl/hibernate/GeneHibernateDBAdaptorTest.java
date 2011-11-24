package org.bioinfo.infrared.lib.impl.hibernate;

import static org.junit.Assert.fail;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.bioinfo.infrared.lib.io.output.StringWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeneHibernateDBAdaptorTest {
	/*
	private DBAdaptorFactory dbAdaptorFact = new HibernateDBAdaptorFactory();

	private GeneDBAdaptor geneDBAdaptor;

	private long startExecTime;
	
	private String species = "drerio";

	@Before
	public void beforeTestStart() {
		geneDBAdaptor = dbAdaptorFact.getGeneDBAdaptor(species);
		startExecTime = System.currentTimeMillis();
	}

	@After
	public void afterTestStart() {
		dbAdaptorFact.close();
	}



	@Test
	public void testGeneHibernateDBAdaptorGetAll() {
		List<Gene> genes = geneDBAdaptor.getAll();
		printGeneList("testGeneHibernateDBAdaptorGetAll", genes, 5);
	}
	@Test
	public void testGeneHibernateDBAdaptorGetAll2() {
		List<Gene> genes = geneDBAdaptor.getAll();
		printGeneList("testGeneHibernateDBAdaptorGetAll2", genes, 5);
	}

	@Test
	public void testGeneHibernateDBAdaptorGetAllEnsemblIds() {
		List<String> genes = geneDBAdaptor.getAllEnsemblIds();
		printGeneList("testGeneHibernateDBAdaptorGetAllEnsemblIds", genes, 5);
	}

	@Test
	public void testGeneHibernateDBAdaptorGetByEnsemblId() {
		Gene gene = geneDBAdaptor.getByEnsemblId("ENSG00000252775");
		//System.out.println(gene.toString());
		//System.out.println(gene.getTranscripts());
		printGeneList("testGeneHibernateDBAdaptorGetByEnsemblId", Arrays.asList(gene), 5);
	}

	@Test
	public void testGeneHibernateDBAdaptorGetByEnsemblIdList() {
		List<Gene> genes = geneDBAdaptor.getAllByEnsemblIdList(Arrays.asList("ENSG00000252775", "ENSG00000000419", "ENSG00000187642"));
		printGeneList("testGeneHibernateDBAdaptorGetByEnsemblIdList", genes, 5);
	}

	@Test
	public void testGeneHibernateDBAdaptorGetAllById() {
		List<Gene> genes = geneDBAdaptor.getAllById("ENSG00000252775");
		printGeneList("testGeneHibernateDBAdaptorGetByEnsemblIdList", genes, 5);
	}

	@Test
	public void testGeneHibernateDBAdaptorGetAllByIdList() {
		List<Gene> genes = geneDBAdaptor.getAllById("ENSG00000252775");
		printGeneList("testGeneHibernateDBAdaptorGetByEnsemblIdList", genes, 5);
	}




	@Test
	public void testGeneHibernateDBAdaptorGetAllByBiotype() {
		List<Gene> genes = geneDBAdaptor.getAllByBiotype("protein_coding");
		printGeneList("testGeneHibernateDBAdaptorGetAllByBiotype", genes, 5);
	}

	@Test
	public void testGeneHibernateDBAdaptorGetAllByBiotypeList() {
		List<Gene> genes = geneDBAdaptor.getAllByBiotypeList(Arrays.asList("processed_transcript", "protein_coding"));
		printGeneList("testGeneHibernateDBAdaptorGetAllByBiotypeList", genes, 5);
	}




	@Test
	public void testGetAllByPosition() {
		List<Gene> genes = geneDBAdaptor.getAllByPosition("1", 244515940);
		printGeneList("testGetAllByPosition", genes, 5);
	}

	@Test
	public void testGetAllByPositionList() {
		List<List<Gene>> genes = geneDBAdaptor.getAllByPositionList(Arrays.asList(new Position("1", 244515940), new Position("10", 24451594), new Position("11", 244515)));
		printGeneList("testGetAllByPositionList", genes, 5);
	}




	@Test
	public void testGetAllByRegionString() {
		List<Gene> genes = geneDBAdaptor.getAllByRegion("Y");
		printGeneList("testGetAllByRegionString", genes, 5);
	}

	@Test
	public void testGetAllByRegionStringInt() {
		List<Gene> genes = geneDBAdaptor.getAllByRegion("1", 240000000);
		printGeneList("testGetAllByRegionStringInt", genes, 5);
	}

	@Test
	public void testGetAllByRegionStringIntInt() {
		List<Gene> genes = geneDBAdaptor.getAllByRegion("1", 1, 300000);
		printGeneList("testGetAllByRegionStringIntInt", genes, 5);
	}

	@Test
	public void testGetAllByRegionStringIntIntStringList() {
		List<Gene> genes = geneDBAdaptor.getAllByRegion("1", 1, 300000, Arrays.asList("protein_coding", "processed_transcript"));
		printGeneList("testGetAllByRegionStringIntIntStringList", genes, 5);
	}

	public void testGetGeneByRegionString() {

	}

	public void testGetGeneByTranscript() {
		fail("Not yet implemented");
	}

	public void testGetGeneByTranscriptList() {
		fail("Not yet implemented");
	}

	public void testGetSequence() {
		fail("Not yet implemented");
	}

	public void testGetSequenceString() {
		fail("Not yet implemented");
	}

	public void testGetRegion() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllByCytoband() {
		List<Gene> genes = geneDBAdaptor.getAllByCytoband("9", "q31.3");
		printGeneList("testGetAllByCytoband", genes, 5);
	}


	private void printGeneList(String title, List<?> genes, int numResults) {
		System.out.println("************************************************************");
		System.out.println(title);
		System.out.println("Number of results: "+genes.size());
		if(numResults < 0) {
			if(genes.get(0) instanceof Gene) {
				System.out.println(StringWriter.serialize((List<Gene>)genes));
			}else {
				System.out.println(genes);
			}
		}else {
			for(int i=0; i<numResults && i<genes.size(); i++) {
				if(genes.get(i) instanceof Gene) {
					System.out.println(StringWriter.serialize((Gene)genes.get(i)));
				}else {
					if(genes.get(i) instanceof List<?>) {
						System.out.println(StringWriter.serialize((List<Gene>)genes.get(i)));
					}else {
						System.out.println(genes.get(i));
					}
				}
			}
			System.out.println("...");
		}
		long executionTime = System.currentTimeMillis() - startExecTime;
		System.out.println("Test executed in: "+ executionTime +" ms");
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("Number of results per ms: "+df.format((double)genes.size()/executionTime)+" rows/ms");
		System.out.println("************************************************************\n");

	}
	*/
}

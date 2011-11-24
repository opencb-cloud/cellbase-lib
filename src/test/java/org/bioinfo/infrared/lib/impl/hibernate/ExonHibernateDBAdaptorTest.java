package org.bioinfo.infrared.lib.impl.hibernate;

import static org.junit.Assert.fail;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.lib.api.ExonDBAdaptor;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.bioinfo.infrared.lib.io.output.StringWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExonHibernateDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new HibernateDBAdaptorFactory();

	private ExonDBAdaptor exonDBAdaptor;

	private long startExecTime;

	@Before
	public void beforeTestStart() {
		exonDBAdaptor = dbAdaptorFact.getExonDBAdaptor("hsapiens");
		startExecTime = System.currentTimeMillis();
	}

	@After
	public void afterTestStart() {
		dbAdaptorFact.close();
	}


	@Test
	public void testExonHibernateDBAdaptorGetAll() {
		List<Exon> exons = exonDBAdaptor.getAll();
		printGeneList("testExonHibernateDBAdaptorGetAll", exons, 5);
	}
	@Test
	public void testExonHibernateDBAdaptorGetAll2() {
		List<Exon> exons = exonDBAdaptor.getAll();
		printGeneList("testExonHibernateDBAdaptorGetAll2", exons, 5);
	}

	@Test
	public void testExonHibernateDBAdaptorGetAllEnsemblIds() {
		List<String> exons = exonDBAdaptor.getAllEnsemblIds();
		printGeneList("testExonHibernateDBAdaptorGetAllEnsemblIds", exons, 5);
	}

	@Test
	public void testExonHibernateDBAdaptorGetByEnsemblId() {
		Exon exon = exonDBAdaptor.getByEnsemblId("ENSG00000252775");
		System.out.println(exon.toString());
		System.out.println(exon.getStrand());
		printGeneList("testExonHibernateDBAdaptorGetByEnsemblId", Arrays.asList(exon), 5);
	}

	@Test
	public void testExonHibernateDBAdaptorGetByEnsemblIdList() {
		List<Exon> exons = exonDBAdaptor.getAllByEnsemblIdList(Arrays.asList("ENSG00000252775", "ENSG00000000419", "ENSG00000187642"));
		printGeneList("testExonHibernateDBAdaptorGetByEnsemblIdList", exons, 5);
	}


	@Test
	public void testGetAllByPosition() {
		List<Exon> exons = exonDBAdaptor.getAllByPosition("1", 244515940);
		printGeneList("testGetAllByPosition", exons, 5);
	}

	@Test
	public void testGetAllByPositionList() {
		List<List<Exon>> exons = exonDBAdaptor.getAllByPositionList(Arrays.asList(new Position("1", 244515940), new Position("10", 24451594), new Position("11", 244515)));
		printGeneList("testGetAllByPositionList", exons, 5);
	}




	@Test
	public void testGetAllByRegionString() {
		List<Exon> exons = exonDBAdaptor.getAllByRegion("Y");
		printGeneList("testGetAllByRegionString", exons, 5);
	}

	@Test
	public void testGetAllByRegionStringInt() {
		List<Exon> exons = exonDBAdaptor.getAllByRegion("1", 240000000);
		printGeneList("testGetAllByRegionStringInt", exons, 5);
	}

	@Test
	public void testGetAllByRegionStringIntInt() {
		List<Exon> exons = exonDBAdaptor.getAllByRegion("1", 1, 300000);
		printGeneList("testGetAllByRegionStringIntInt", exons, 5);
	}

	@Test
	public void testGetAllByRegionStringIntIntStringList() {
		List<Exon> exons = exonDBAdaptor.getAllByRegion("1", 1, 300000, Arrays.asList("protein_coding", "processed_transcript"));
		printGeneList("testGetAllByRegionStringIntIntStringList", exons, 5);
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
		List<Exon> exons = exonDBAdaptor.getAllByCytoband("9", "q31.3");
		printGeneList("testGetAllByCytoband", exons, 5);
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
}

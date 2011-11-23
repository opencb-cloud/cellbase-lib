package org.bioinfo.infrared.lib.impl.hibernate;

import static org.junit.Assert.fail;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.Transcript;
import org.bioinfo.infrared.lib.api.TranscriptDBAdaptor;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.bioinfo.infrared.lib.io.output.StringWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TranscriptHibernateDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new HibernateDBAdaptorFactory();

	private TranscriptDBAdaptor transcriptDBAdaptor;

	private long startExecTime;

	@Before
	public void beforeTestStart() {
		transcriptDBAdaptor = dbAdaptorFact.getTranscriptDBAdaptor("hsapiens");
		startExecTime = System.currentTimeMillis();
	}

	@After
	public void afterTestStart() {
		dbAdaptorFact.close();
	}



	@Test
	public void testTranscriptHibernateDBAdaptorGetAll() {
		List<Transcript> transcripts = transcriptDBAdaptor.getAll();
		printGeneList("testTranscriptHibernateDBAdaptorGetAll", transcripts, 5);
	}
	@Test
	public void testTranscriptHibernateDBAdaptorGetAll2() {
		List<Transcript> transcripts = transcriptDBAdaptor.getAll();
		printGeneList("testTranscriptHibernateDBAdaptorGetAll2", transcripts, 5);
	}

	@Test
	public void testTranscriptHibernateDBAdaptorGetAllEnsemblIds() {
		List<String> transcripts = transcriptDBAdaptor.getAllEnsemblIds();
		printGeneList("testTranscriptHibernateDBAdaptorGetAllEnsemblIds", transcripts, 5);
	}

	@Test
	public void testTranscriptHibernateDBAdaptorGetByEnsemblId() {
		Transcript transcript = transcriptDBAdaptor.getByEnsemblId("ENST00000493562");
		System.out.println(transcript.toString());
		System.out.println(transcript.getGene());
		printGeneList("testTranscriptHibernateDBAdaptorGetByEnsemblId", Arrays.asList(transcript), 5);
	}

	@Test
	public void testTranscriptHibernateDBAdaptorGetByEnsemblIdList() {
		List<Transcript> transcripts = transcriptDBAdaptor.getAllByEnsemblIdList(Arrays.asList("ENST00000493562", "ENST00000472808", "ENST00000465089"));
		printGeneList("testTranscriptHibernateDBAdaptorGetByEnsemblIdList", transcripts, 5);
	}

	@Test
	public void testTranscriptHibernateDBAdaptorGetAllById() {
		List<Transcript> transcripts = transcriptDBAdaptor.getAllById("brca2");
		printGeneList("testTranscriptHibernateDBAdaptorGetAllById", transcripts, 5);
	}

	@Test
	public void testTranscriptHibernateDBAdaptorGetAllByIdList() {
		List<List<Transcript>> transcripts = transcriptDBAdaptor.getAllByIdList(Arrays.asList("ENST00000493562","brca2","brca1"));
		printGeneList("testTranscriptHibernateDBAdaptorGetAllByIdList", transcripts, 5);
	}

	

	@Test
	public void testTranscriptHibernateDBAdaptorGetAllByBiotype() {
		List<Transcript> transcripts = transcriptDBAdaptor.getAllByBiotype("protein_coding");
		printGeneList("testTranscriptHibernateDBAdaptorGetAllByBiotype", transcripts, 5);
	}

	@Test
	public void testTranscriptHibernateDBAdaptorGetAllByBiotypeList() {
		List<Transcript> transcripts = transcriptDBAdaptor.getAllByBiotypeList(Arrays.asList("processed_transcript", "protein_coding"));
		printGeneList("testTranscriptHibernateDBAdaptorGetAllByBiotypeList", transcripts, 5);
	}




	@Test
	public void testGetAllByPosition() {
		List<Transcript> transcripts = transcriptDBAdaptor.getAllByPosition("1", 244515940);
		printGeneList("testGetAllByPosition", transcripts, 5);
	}

	@Test
	public void testGetAllByPositionList() {
		List<List<Transcript>> transcripts = transcriptDBAdaptor.getAllByPositionList(Arrays.asList(new Position("1", 244515940), new Position("10", 24451594), new Position("11", 244515)));
		printGeneList("testGetAllByPositionList", transcripts, 5);
	}




	@Test
	public void testGetAllByRegionString() {
		List<Transcript> transcripts = transcriptDBAdaptor.getAllByRegion("Y");
		printGeneList("testGetAllByRegionString", transcripts, 5);
	}

	@Test
	public void testGetAllByRegionStringInt() {
		List<Transcript> transcripts = transcriptDBAdaptor.getAllByRegion("1", 240000000);
		printGeneList("testGetAllByRegionStringInt", transcripts, 5);
	}

	@Test
	public void testGetAllByRegionStringIntInt() {
		List<Transcript> transcripts = transcriptDBAdaptor.getAllByRegion("1", 1, 300000);
		printGeneList("testGetAllByRegionStringIntInt", transcripts, 5);
	}

	@Test
	public void testGetAllByRegionStringIntIntStringList() {
		List<Transcript> transcripts = transcriptDBAdaptor.getAllByRegion("1", 1, 300000, Arrays.asList("protein_coding", "processed_transcript"));
		printGeneList("testGetAllByRegionStringIntIntStringList", transcripts, 5);
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
		List<Transcript> transcript = transcriptDBAdaptor.getAllByCytoband("9", "q31.3");
		printGeneList("testGetAllByCytoband", transcript, 5);
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

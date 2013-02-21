package org.bioinfo.cellbase.lib.impl.hibernate;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import org.bioinfo.cellbase.lib.api.TranscriptDBAdaptor;
import org.bioinfo.cellbase.lib.common.Position;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.impl.DBAdaptorFactory;
import org.bioinfo.cellbase.lib.impl.hibernate.HibernateDBAdaptorFactory;
import org.bioinfo.cellbase.lib.io.output.StringWriter;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.Transcript;
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
//		dbAdaptorFact.close();
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
	public void testTranscriptHibernateDBAdaptorGetAllIds() {
		List<String> transcripts = transcriptDBAdaptor.getAllIds();
		printGeneList("testTranscriptHibernateDBAdaptorGetAllIds", transcripts, 5);
	}


//	@Test
//	public void testTranscriptHibernateDBAdaptorGetInfo() {
//
//	}
//	@Test
//	public void testTranscriptHibernateDBAdaptorGetInfoByIdList() {
//		
//	}
//	@Test
//	public void testTranscriptHibernateDBAdaptorGetFullInfo() {
//		
//	}
//	@Test
//	public void testTranscriptHibernateDBAdaptorGetFullInfoByIdList() {
//		
//	}
	
	@Test
	public void testTranscriptHibernateDBAdaptorGetAllEnsemblIds() {
		List<String> transcripts = transcriptDBAdaptor.getAllEnsemblIds();
		printGeneList("testTranscriptHibernateDBAdaptorGetAllEnsemblIds", transcripts, 5);
	}

	@Test
	public void testTranscriptHibernateDBAdaptorGetByEnsemblId() {
		Transcript transcript = transcriptDBAdaptor.getByEnsemblId("ENST00000493562");
		printGeneList("testTranscriptHibernateDBAdaptorGetByEnsemblId", Arrays.asList(transcript), 5);
	}

	@Test
	public void testTranscriptHibernateDBAdaptorGetByEnsemblIdList() {
		List<Transcript> transcripts = transcriptDBAdaptor.getAllByEnsemblIdList(Arrays.asList("ENST00000493562", "ENST00000472808", "ENST00000465089"));
		printGeneList("testTranscriptHibernateDBAdaptorGetByEnsemblIdList", transcripts, 5);
	}

	@Test
	public void testTranscriptHibernateDBAdaptorGetAllByName() {
		List<Transcript> transcripts = transcriptDBAdaptor.getAllByName("brca2");
		printGeneList("testTranscriptHibernateDBAdaptorGetAllByName", transcripts, 5);
	}

	@Test
	public void testTranscriptHibernateDBAdaptorGetAllByNameList() {
		List<List<Transcript>> transcripts = transcriptDBAdaptor.getAllByNameList(Arrays.asList("ENST00000493562","brca2","brca1"));
		printGeneList("testTranscriptHibernateDBAdaptorGetAllByNameList", transcripts, 5);
	}

	@Test
	public void testTranscriptHibernateDBAdaptorGetByEnsemblGeneId() {
		List<Transcript> transcript = transcriptDBAdaptor.getByEnsemblGeneId("ENSG00000102181");
		System.out.println(transcript);
	}
	
	@Test
	public void testTranscriptHibernateDBAdaptorGetByEnsemblGeneIdList() {
		List<List<Transcript>> transcripts = transcriptDBAdaptor.getByEnsemblGeneIdList(Arrays.asList("ENSG00000102181","brca2","brca1"));
		System.out.println(transcripts);
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
	
	@Test
	public void testGetAllByCytoband() {
		List<Transcript> transcripts = transcriptDBAdaptor.getAllByCytoband("9", "q31.3");
		printGeneList("testGetAllByCytoband", transcripts, 5);
	}
	
	
	@Test
	public void testGetAllBySnpId(){
		List<Transcript> transcripts = transcriptDBAdaptor.getAllBySnpId("rs1333049");
		printGeneList("testGetAllBySnpId", transcripts, 5);
		System.out.println("Transcrits: "+transcripts.get(0).getStableId());
		System.out.println("Transcrits size: "+transcripts.size());
	}
	@Test
	public void testgetAllBySnpIdList(){
		List<List<Transcript>> trans = transcriptDBAdaptor.getAllBySnpIdList(Arrays.asList("rs1333049","rs1333049"));
		printGeneList("testgetAllBySnpIdList", trans, 5);
		
		int c = 0;
		
		for(List<Transcript> transcripts : trans){
			System.out.println("Results for "+c+" element: ");
			System.out.println("Transcrits: "+transcripts.get(0).getStableId());
			System.out.println("Transcrits size: "+transcripts.size());			
			c++;			
		}
		
	
	}
	
//	transcrito: ENST00000482343
	
	@Test
	public void TestGetRegionById(){
		System.out.println("TestGetRegionById");
		Region region = transcriptDBAdaptor.getRegionById("ENST00000493561");
		System.out.println(region.toString());
	}
	@Test
	public void TestGetAllRegionsByIdList(){
		System.out.println("TestGetAllRegionsByIdList");
		List<Region> regions = transcriptDBAdaptor.getAllRegionsByIdList(Arrays.asList("ENST00000493561","ENST00000482343"));
		System.out.println(regions.toString());
	}
	
	@Test
	public void testGetSequenceById(){
		String s = transcriptDBAdaptor.getSequenceById("ENST00000482343");
		System.out.println(s);
	}
	
	@Test
	public void testGetAllSequencesByIdList(){
		List<String> strings = transcriptDBAdaptor.getAllSequencesByIdList(Arrays.asList("ENST00000370377","ENST00000482343"));
		System.out.println(strings);
	}
	
	
	@Test
	public void testGetAllByProteinNameList(){
		List<List<Transcript>> strings = transcriptDBAdaptor.getAllByProteinNameList(Arrays.asList("Q9H6T3","BRCA2"));
		System.out.println(strings);
	}
	
	@Test
	public void testGetAllByMirnaMatureList(){
		List<List<Transcript>> strings = transcriptDBAdaptor.getAllByMirnaMatureList(Arrays.asList("hsa-miR-320c"));
		System.out.println(strings);
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

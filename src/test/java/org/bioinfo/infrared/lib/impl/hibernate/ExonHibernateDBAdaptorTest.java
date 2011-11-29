package org.bioinfo.infrared.lib.impl.hibernate;

import static org.junit.Assert.fail;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.Transcript;
import org.bioinfo.infrared.lib.api.ExonDBAdaptor;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;
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
		Exon exon = exonDBAdaptor.getByEnsemblId("ENSE00002084795");
		System.out.println(exon.toString());
		System.out.println(exon.getStrand());
		printGeneList("testExonHibernateDBAdaptorGetByEnsemblId", Arrays.asList(exon), 5);
	}

	@Test
	public void testExonHibernateDBAdaptorGetByEnsemblIdList() {
		List<Exon> exons = exonDBAdaptor.getAllByEnsemblIdList(Arrays.asList("ENSE00002084795", "ENSE00001448904", "ENSE00001260858"));
		printGeneList("testExonHibernateDBAdaptorGetByEnsemblIdList", exons, 5);
	}

	
	@Test
	public void TestGetByEnsemblTranscriptId() {
		List<Exon> exons =exonDBAdaptor.getByEnsemblTranscriptId("ENST00000482343");
		System.out.println(exons);
	}
	@Test
	public void TestGetByEnsemblTranscriptIdList() {
		List<List<Exon>> exons = exonDBAdaptor.getByEnsemblTranscriptIdList(Arrays.asList("ENST00000482343","ENST00000358595"));
		System.out.println(exons);
	}
	
	@Test
	public void TestGetByEnsemblGeneId() {
		List<Exon> exons = exonDBAdaptor.getByEnsemblGeneId("ENSG00000102181");
		System.out.println(exons);
	}
	@Test
	public void TestGetByEnsemblGeneIdList() {
		List<List<Exon>> exons = exonDBAdaptor.getByEnsemblGeneIdList(Arrays.asList("ENSG00000102181","brca2","brca1"));
		System.out.println(exons);
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
		Region reg1 = new Region("1", 4000, 76465);
		Region reg2 = new Region("2", 7777, 23454);
		Region reg3 = new Region("3", 6543, 542366);
		List<Region> regions = new ArrayList<Region>();
		regions.add(reg1);
		regions.add(reg2);
		regions.add(reg3);
		List<List<Exon>> exons = exonDBAdaptor.getAllByRegionList(regions);
		printGeneList("testGetAllByRegionStringIntIntStringList", exons, 5);
	}


	@Test
	public void testGetAllByCytoband() {
		List<Exon> exons = exonDBAdaptor.getAllByCytoband("9", "q31.3");
		printGeneList("testGetAllByCytoband", exons, 5);
	}

	
	@Test
	public void testGetAllBySnpId(){
		List<Exon> exons = exonDBAdaptor.getAllBySnpId("rs1333049");
		printGeneList("testGetAllBySnpId", exons, 5);
		System.out.println("Transcrits: "+exons.get(0).getStableId());
		System.out.println("Transcrits size: "+exons.size());
	}
	@Test
	public void testgetAllBySnpIdList(){
		List<List<Exon>> exonList = exonDBAdaptor.getAllBySnpIdList(Arrays.asList("rs1333049","rs1333049"));
		printGeneList("testgetAllBySnpIdList", exonList, 5);
		
		int c = 0;
		for(List<Exon> exons : exonList){
			System.out.println("Results for "+c+" element: ");
			System.out.println("Transcrits: "+exons.get(0).getStableId());
			System.out.println("Transcrits size: "+exons.size());			
			c++;			
		}
	}
	
	
	@Test
	public void TestGetRegionById(){
		System.out.println("TestGetRegionById");
		Region region = exonDBAdaptor.getRegionById("ENSE00002084795");
		System.out.println(region.toString());
	}
	@Test
	public void TestGetAllRegionsByIdList(){
		System.out.println("TestGetAllRegionsByIdList");
		List<Region> regions = exonDBAdaptor.getAllRegionsByIdList(Arrays.asList("ENSE00002084795","ENSE00001448904"));
		System.out.println(regions.toString());
	}
	
	@Test
	public void testGetSequenceById(){
		System.out.println("testGetSequenceById");
		String s = exonDBAdaptor.getSequenceById("ENSE00002084795");
		System.out.println(s);
	}
	
	@Test
	public void testGetAllSequencesByIdList(){
		System.out.println("testGetAllSequencesByIdList");
		List<String> strings = exonDBAdaptor.getAllSequencesByIdList(Arrays.asList("ENSE00002084795","ENSE00001448904"));
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

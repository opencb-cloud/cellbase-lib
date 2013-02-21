package org.bioinfo.infrared.lib.impl.hibernate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.ConsequenceType;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.SnpPhenotypeAnnotation;
import org.bioinfo.infrared.core.cellbase.SnpPopulationFrequency;
import org.bioinfo.infrared.core.cellbase.SnpToTranscript;
import org.bioinfo.infrared.lib.api.SnpDBAdaptor;
import org.bioinfo.infrared.lib.common.Region;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.bioinfo.infrared.lib.io.output.StringWriter;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class SnpHibernateDBAdaptorTest {

	private static DBAdaptorFactory dbAdaptorFact = new HibernateDBAdaptorFactory();

	private SnpDBAdaptor snpDBAdaptor;

	private long startExecTime;

	@Before
	public void beforeTestStart() {
		snpDBAdaptor = dbAdaptorFact.getSnpDBAdaptor("hsapiens");
		startExecTime = System.currentTimeMillis();
	}

	@AfterClass
	public static void afterTestStart() {
//		dbAdaptorFact.close();
	}
	
	@Test
	public void getAllBySnpId() {
		List<Snp> snps = snpDBAdaptor.getAllBySnpId("rs56289060");
		this.printSNPList("getByDbSnpIdList", snps, 6);
	}
	
	@Test
	public void getByDbSnpIdList() {
		List<String> query = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			StringBuilder br = new StringBuilder();
			br.append("rs117");
			int size = String.valueOf(i).length();
			for (int j = size; j < 5; j++) {
				br.append("0");
			}
			br.append(i);
			query.add(br.toString());
		}
		
		List<List<Snp>> snpsList = snpDBAdaptor.getAllBySnpIdList(query);
		
		this.printSNPListList("getByDbSnpIdList", snpsList, 6);
	}
	
	@Test
	public void getByGeneId() {
		List<Snp> snps = snpDBAdaptor.getAllByGeneName("brca2");
		this.printSNPList("getByGeneId", snps, 6);
	}
	
	@Test
	public void getAllByGeneIdList() {
		List<List<Snp>> snps = snpDBAdaptor.getAllByGeneNameList(Arrays.asList("brca2", "bcl2"));
		this.printSNPListList("getAllByGeneIdList", snps, 6);
	}
	
	@Test
	public void getByEnsemblGeneId() {
		List<Snp> snps = snpDBAdaptor.getAllByEnsemblGeneId("ENSG00000080910");
		this.printSNPList("getByEnsemblGeneId", snps, 6);
	}
	
	@Test
	public void getAllByEnsemblGeneIdList() {
		List<List<Snp>> snps = snpDBAdaptor.getAllByEnsemblGeneIdList(Arrays.asList("ENSG00000080910", "ENSG00000000003"));
		this.printSNPListList("getByEnsemblGeneIdList", snps, 6);
	}
	
	@Test
	public void getByEnsemblTranscriptId() {
		List<Snp> snps = snpDBAdaptor.getAllByEnsemblTranscriptId("ENST00000299130");
		this.printSNPList("getByEnsemblTranscriptId", snps, 6);
	}
	
	@Test
	public void getAllByEnsemblTranscriptIdList() {
		List<List<Snp>> snps = snpDBAdaptor.getAllByEnsemblTranscriptIdList(Arrays.asList("ENST00000299130", "ENST00000278100"));
		this.printSNPListList("getAllByEnsemblTranscriptIdList", snps, 6);
	}
	
	
	
	@Test
	public void getAllConsequenceTypes() {
		List<ConsequenceType> consTypes = snpDBAdaptor.getAllConsequenceTypes();
		System.out.println("getAllByEnsemblTranscriptIdList");
		System.out.println(StringWriter.serialize(consTypes));
	}
	
	
	@Test
	public void getSequenceById() {
		String sequence = snpDBAdaptor.getSequenceById("rs4");
		System.out.println(sequence);
	}
	
	@Test
	public void getAllByRegion() {
//		List<Snp> snps = snpDBAdaptor.getAllByRegion(new Region("1", 150000000, 150001000));
		List<String> consequenceTypeList = new ArrayList<String>();
		consequenceTypeList.add("2KB_upstream_variant");
		consequenceTypeList.add("splice_region_variant");
		List<Snp> snps = snpDBAdaptor.getAllByRegion(new Region("1", 10327, 12807), consequenceTypeList);
		this.printSNPList("getAllByRegion", snps, 50);
	}
	
	@Test
	public void getAllIdsByRegion() {
		List<String> snpsIds = snpDBAdaptor.getAllIdsByRegion("1", 140000000, 150001000);
		this.printSNPList("getAllByRegion", snpsIds, 50);
	}
	
	@Test
	public void getRegionById() {
		Region region = snpDBAdaptor.getRegionById("rs4");
		System.out.println(region);
	}
	
	@Test
	public void getAllRegionsByIdList() {
		List<String> idList = new ArrayList<String>();
		idList.add("rs11510119");
		idList.add("rs2462499");
		List<Region> regions = snpDBAdaptor.getAllRegionsByIdList(idList);
		System.out.println(regions);
	}
	
	@Test
	public void getAllSequencesByIdList() {
		List<String> idList = new ArrayList<String>();
		idList.add("rs11510119");
		idList.add("rs2462499");
		List<String> seqs = snpDBAdaptor.getAllSequencesByIdList(idList);
		System.out.println(seqs);
	}
	
	@Test
	public void getAllFilteredByConsequenceType() {
		List<String> idList = new ArrayList<String>();
		idList.add("rs11510119");
		idList.add("rs2462499");
		List<Snp> snp = snpDBAdaptor.getAllFilteredByConsequenceType(idList,"intron_variant");
		System.out.println(snp.size());
		for (int i = 0; i < snp.size(); i++) {
			System.out.println(snp.get(i).getName());
		}
	}
	
	@Test
	public void getAllFilteredByConsequenceTypes() {
		List<String> idList = new ArrayList<String>();
		idList.add("rs11510119");
		idList.add("rs2462499");
		List<String> consequenceList = new ArrayList<String>();
		consequenceList.add("intron_variant");
		consequenceList.add("asdfasd");
		List<Snp> snp = snpDBAdaptor.getAllFilteredByConsequenceType(idList,consequenceList);
		System.out.println(snp.size());
		for (int i = 0; i < snp.size(); i++) {
			System.out.println(snp.get(i).getName());
		}
	}
	
	@Test
	public void getAllIdsBySOConsequenceTypeList() {
		List<List<String>> snp = snpDBAdaptor.getAllIdsBySOConsequenceTypeList(Arrays.asList("intron_variant"));
		System.out.println("Number of features 'intron_variant'"+ snp.get(0).size());
	}
	
	@Test
	public void getAllIds() {
		List<String> ids = snpDBAdaptor.getAllIds();
		System.out.println(ids.size());
	}
	
	
	@Test
	public void getSnpPhenotypeAnnotation() {
		List<List<SnpPhenotypeAnnotation>> snpPhenotypeList = snpDBAdaptor.getAllSnpPhenotypeAnnotationListBySnpNameList(Arrays.asList("rs3934834","rs307355"));
		System.out.println(snpPhenotypeList.get(0).size());
		System.out.println(snpPhenotypeList.get(1).size());
	}
	
	@Test
	public void getSnpPopulationFrequency() {
		List<List<SnpPopulationFrequency>> snpPopulationList = snpDBAdaptor.getAllSnpPopulationFrequencyList(Arrays.asList("rs3934834","rs307355"));
		System.out.println(snpPopulationList.get(0).size());
		System.out.println(snpPopulationList.get(1).size());
	}
	
	@Test
	public void getSnpToTranscript() {
		List<List<SnpToTranscript>> snpToTranscriptList = snpDBAdaptor.getAllSnpToTranscriptList(Arrays.asList("rs3934834","rs307355"));
		System.out.println(snpToTranscriptList.get(0).size());
		System.out.println(snpToTranscriptList.get(1).size());
	}
	
	
	@Test
	public void getAllConsequenceType() {
		List<List<ConsequenceType>> snpToTranscriptList = snpDBAdaptor.getAllConsequenceTypeList(Arrays.asList("rs3934834","rs307355"));
		System.out.println(snpToTranscriptList.get(0).size());
		System.out.println(snpToTranscriptList.get(1).size());
	}
	
	
	private void printSNPListList(String title, List<List<Snp>> snpsList, int numResults) {
		List<Snp> completeList = new ArrayList<Snp>();
		for (List<Snp> list : snpsList) {
			completeList.addAll(list);
		}
		printSNPList(title, completeList, numResults);
	}
	
	private void printSNPList(String title, List<?> snps, int numResults) {
		System.out.println("************************************************************");
		System.out.println(title);
		System.out.println("\tNumber of results: "+snps.size());
		if(numResults < 0) {
			if(snps.get(0) instanceof Snp) {
				System.out.print("\t" + StringWriter.serialize(snps));
			}else {
				System.out.print("\t" + StringWriter.serialize(snps));
			}
		}else {
			for(int i=0; i<numResults && i<snps.size(); i++) {
				if(snps.get(i) instanceof Snp) {
					System.out.print("\t" + StringWriter.serialize(Arrays.asList(snps.get(i))));
				}else {
					if(snps.get(i) instanceof List<?>) {
						System.out.print("\t" +StringWriter.serialize(Arrays.asList(snps.get(i))));
					}else {
						System.out.print("\t" + StringWriter.serialize(Arrays.asList(snps.get(i))));
					}
				}
			}
			System.out.println("...");
		}
		long executionTime = System.currentTimeMillis() - startExecTime;
		System.out.println("Test executed in: "+ executionTime +" ms");
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("Number of results per ms: "+df.format((double)snps.size()/executionTime)+" rows/ms");
		System.out.println("************************************************************\n");
	}
	
}

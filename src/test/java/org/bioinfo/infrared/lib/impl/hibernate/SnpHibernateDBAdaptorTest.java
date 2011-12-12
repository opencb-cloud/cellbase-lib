package org.bioinfo.infrared.lib.impl.hibernate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.lib.api.SnpDBAdaptor;
import org.bioinfo.infrared.lib.common.Region;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.bioinfo.infrared.lib.io.output.StringWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SnpHibernateDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new HibernateDBAdaptorFactory();

	private SnpDBAdaptor snpDBAdaptor;

	private long startExecTime;

	@Before
	public void beforeTestStart() {
		snpDBAdaptor = dbAdaptorFact.getSnpDBAdaptor("hsapiens");
		startExecTime = System.currentTimeMillis();
	}

	@After
	public void afterTestStart() {
		dbAdaptorFact.close();
	}
	
	@Test
	public void getByGeneId() {
		List<Snp> snps = snpDBAdaptor.getByGeneId("ENSG00000080910");
		this.printSNPList("getByGeneId", snps, 6);
	}
	
	@Test
	public void getByGeneIdList() {
		List<List<Snp>> snps = snpDBAdaptor.getByGeneIdList(Arrays.asList("ENSG00000080910"));
		this.printSNPListList("getByGeneIdList", snps, 6);
	}
	
	@Test
	public void getByDbSnpId() {
		List<Snp> snps = snpDBAdaptor.getByDbSnpId("rs56289060");
		this.printSNPList("getByDbSnpIdList", snps, 6);
	}
	
	@Test
	public void getAllByRegion() {
		List<Snp> snps = snpDBAdaptor.getAllByRegion(new Region("1", 150000000, 150001000));
		this.printSNPList("getAllByRegion", snps, 6);
	}
	
	@Test
	public void getByDbSnpIdList() {
		List<String> query = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			StringBuilder br = new StringBuilder();
			br.append("rs117");
			int size = String.valueOf(i).length();
			for (int j = size; j < 5; j++) {
				br.append("0");
			}
			br.append(i);
			query.add(br.toString());
		}
		
		List<List<Snp>> snpsList = snpDBAdaptor.getByDbSnpIdList(query);
		
		this.printSNPListList("getByDbSnpIdList", snpsList, 6);
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

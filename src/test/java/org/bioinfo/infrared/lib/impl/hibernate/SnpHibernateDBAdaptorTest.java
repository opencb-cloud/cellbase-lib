package org.bioinfo.infrared.lib.impl.hibernate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.bioinfo.commons.utils.ListUtils;
import org.bioinfo.infrared.core.cellbase.ConsequenceType;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.SnpToTranscript;
import org.bioinfo.infrared.core.cellbase.Transcript;
import org.bioinfo.infrared.dao.utils.HibernateUtil;
import org.bioinfo.infrared.lib.api.SnpDBAdaptor;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.bioinfo.infrared.lib.io.output.StringWriter;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SnpHibernateDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new HibernateDBAdaptorFactory();

	private SnpDBAdaptor snpDBAdaptor;

	private long startExecTime;

	@Before
	public void beforeTestStart() {
		snpDBAdaptor = dbAdaptorFact.getSnpDBAdaptor("drerio");
		startExecTime = System.currentTimeMillis();
	}

	@After
	public void afterTestStart() {
		dbAdaptorFact.close();
	}

	//@Test
	//public void testGeneHibernateDBAdaptorGetAll() {
		//List<Snp> genes = snpDBAdaptor.getAll();
		//printGeneList("testGeneHibernateDBAdaptorGetAll", genes, 5);
	//}
	
	@Test
	public void testSnpHibernateDBAdaptorGetById() {
		System.out.println("......................");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		List<String> query = new ArrayList<String>();
		for (int i = 0; i <1000; i++) {
			
			StringBuilder br = new StringBuilder();
			br.append("rs407");
			int size = String.valueOf(i).length();
			for (int j = size; j < 5; j++) {
				br.append("0");
			}
			br.append(i);
			System.out.println(br.toString());
			query.add(br.toString());
			
		
			
		}
		
		int count = 0;
		long t1 = System.currentTimeMillis();
		List<Snp> snps = snpDBAdaptor.getByIdList(query);
		long t2 = System.currentTimeMillis();
		System.out.println("\t Query result size: " + snps.size());
		for (int x = 0; x < snps.size(); x++) {
			Snp snp = snps.get(x);
			System.out.println("\t " + count +"   SNP:\t " + snp.getName() + " " + snp.getStart());
			/*for (SnpToTranscript transcript : snp.getSnpToTranscripts()) {
				ConsequenceType consequenceTypes =  transcript.getConsequenceType();
				System.out.println("\t " + count +"   SNP:\t " + snp.getName() + " " + snp.getStart() + "\t\tConsequenceType:\t\t " + consequenceTypes.getDisplayTerm());
			}
			*/
			count++;
		}
		System.out.println("tiempo: "+(t2-t1));
	//"rs40961296");
		/*
		Gson gson1 = new GsonBuilder()
		.serializeNulls()
		.setExclusionStrategies(new SNPFeatureExclusionStrategy(Snp.class)).create();
		
		System.out.println(snps.size());
		System.out.println("");
		System.out.println("");
		
		for (int i = 0; i < snps.size(); i++) {
			Snp snp = snps.get(i);
			System.out.println("SNP:\t\t " + gson1.toJson(snp));
		
			for (SnpToTranscript transcript : snp.getSnpToTranscripts()) {
				System.out.println("\nSnpToTranscript:\t\t " + gson1.toJson(transcript));
				
				ConsequenceType consequenceTypes =  transcript.getConsequenceType();
				System.out.println("\nConsequenceType:\t\t " + gson1.toJson(consequenceTypes));
			}
		}
		
		*/
		
		//System.out.println("SNP: "  + (snp.get(0)).getAncestralAllele());
		
		
		
		//System.out.println("SNP: "  + ((Snp) snp.get(0)).getName());
		
	/*	for (SnpToTranscript snpToTranscript : snpToTranscripts) {
			System.out.println("SNPtoTranscript codon: "  + snpToTranscript.getCodon() + snpToTranscript.getSnpToTranscriptId());
			System.out.println("SNPtoTranscript desc: "  + snpToTranscript.getSnpToTranscriptConsequenceTypes());
		}
	*/
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		System.out.println("......................");
		
		/*
		Gson gson = new GsonBuilder()
						.serializeNulls()
						.setExclusionStrategies(new SNPFeatureExclusionStrategy(Snp.class)).create();
		*/
		System.out.println("");
		System.out.println("");
		System.out.println("");
	//	System.out.println("JSON:" + gson.toJson(snps.get(0)));
		System.out.println("");
		System.out.println("");
		System.out.println("");
		//printGeneList("testSnpHibernateDBAdaptorGetById", snps, 5);
	}
	
	
	
	/*@Test
	public void testSnpHibernateDBAdaptorGetById2() {
		Configuration cfg = new Configuration().configure("cell_db_v1_hibernate.cfg.xml");
		Session session = cfg.buildSessionFactory().openSession();
		
		 Transaction transaction = null;
	      try {
	         transaction = session.beginTransaction();
	     	//Query query = session.createQuery("select g from Snp g where name = 'rs41044450'");
	         Criteria criteria = session.createCriteria(Snp.class).add(Restrictions.eq("name", "rs41044450"));
	         criteria.setFetchMode("SnpToTranscripts",FetchMode.JOIN);
	    	//Query query = session.createQuery("select g from Snp g where name = 'rs41044450'");
	         
	     	List<Snp> snps = criteria.list();
	     	printGeneList("testSnpHibernateDBAdaptorGetById2", snps, 5);
	     	
			Set<SnpToTranscript> ma = snps.get(0).getSnpToTranscripts();
			System.out.println("----------> Size: " + ma.size());
			
			
		} catch (Exception e) { 
	          e.printStackTrace();
	      }  finally { 
	           session.close();
	      }
	}*/
/*
	@Test
	public void testSnpHibernateDBAdaptorgetAllByConsequenceType() {
		List<Snp> snps = snpDBAdaptor.getAllByConsequenceType("3PRIME_UTR");
		printGeneList("testSnpHibernateDBAdaptorgetAllByConsequenceType", snps, 5);
	}
	*/
/*	@Test
	public void testSnpHibernateDBAdaptorgetAllByConsequenceTypeList() {
		List<String> consq = new ArrayList<String>();
		consq.add("3PRIME_UTR");
		//consq.add("SYNONYMOUS_CODING");
		
		List<Snp> snps = snpDBAdaptor.getAllByConsequenceTypeList(consq);
		
		printGeneList("testSnpHibernateDBAdaptorgetAllByConsequenceTypeList", snps, 5);
	}*/
	
	private void printGeneList(String title, List<?> genes, int numResults) {
		System.out.println("************************************************************");
		System.out.println(title);
		System.out.println("Number of results: "+genes.size());
		if(numResults < 0) {
			if(genes.get(0) instanceof Gene) {
				System.out.print(StringWriter.serialize(genes));
			}else {
				System.out.print(StringWriter.serialize(genes));
			}
		}else {
			for(int i=0; i<numResults && i<genes.size(); i++) {
				if(genes.get(i) instanceof Gene) {
					System.out.print(StringWriter.serialize(Arrays.asList(genes.get(i))));
				}else {
					if(genes.get(i) instanceof List<?>) {
						System.out.print(StringWriter.serialize(Arrays.asList(genes.get(i))));
					}else {
						System.out.print(StringWriter.serialize(Arrays.asList(genes.get(i))));
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

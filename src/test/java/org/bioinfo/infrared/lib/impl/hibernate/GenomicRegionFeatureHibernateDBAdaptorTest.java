package org.bioinfo.infrared.lib.impl.hibernate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.bioinfo.commons.utils.ListUtils;
import org.bioinfo.infrared.core.cellbase.ConsequenceType;
import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.FeatureMap;
import org.bioinfo.infrared.core.cellbase.FeatureMapId;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.SnpToTranscript;
import org.bioinfo.infrared.core.cellbase.Transcript;
import org.bioinfo.infrared.dao.utils.HibernateUtil;
import org.bioinfo.infrared.lib.api.GenomicRegionFeatureDBAdaptor;
import org.bioinfo.infrared.lib.api.SnpDBAdaptor;
import org.bioinfo.infrared.lib.common.GenomicRegionFeatures;
import org.bioinfo.infrared.lib.common.Region;
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

public class GenomicRegionFeatureHibernateDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new HibernateDBAdaptorFactory();

	private GenomicRegionFeatureDBAdaptor genomicRegionFeatureDBAdaptor;

	private long startExecTime;

	@Before
	public void beforeTestStart() {
		genomicRegionFeatureDBAdaptor = dbAdaptorFact.getFeatureMapDBAdaptor("hsapiens");
		startExecTime = System.currentTimeMillis();
	}

	@After
	public void afterTestStart() {
		dbAdaptorFact.close();
	}

	
//	@Test
//	public void testGenomicRegionGetByRegion() {
//		GenomicRegionFeatures maps = this.genomicRegionFeatureDBAdaptor.getByRegion("1", 4985398, 4987398);
//		print("testGenomicRegionGetByRegion", maps);
//	}
	
	
//	@Test
//	public void testGenomicRegionGetByRegionSource() {
//		GenomicRegionFeatures maps = this.genomicRegionFeatureDBAdaptor.getByRegion(new Region("2", 127436121, 127436121));
//		print("testGenomicRegionGetByRegionSource", maps);
//	}
	
	
	@Test
	public void testConsequenceType() {
		HashMap<String, List<String>> result =  this.genomicRegionFeatureDBAdaptor.getConsequenceType("1",1154001, "A");
		for (String key : result.keySet()) {
			System.out.println(key + " " + result.get(key));
		}
//		System.out.println(result);
	}
	
	
	
	
	private void print(String title, GenomicRegionFeatures maps){
		
		long executionTime = System.currentTimeMillis() - startExecTime;
		System.out.println("\nTest:" + title);
		if (maps.getGenes() != null){
			System.out.println("\n\tGENES (" + maps.getGenes().size() + ")");
			System.out.println("\t--------------------------------------");
			for (Gene gene : maps.getGenes()) {
				System.out.println("\t\t" + gene.getStableId() + "\t" + gene.getStart() + "\t" + gene.getEnd());
			}
		}
		else{
			System.out.println("\n\tGENES: NULL");
		}
		
		if (maps.getTranscripts() != null){
			System.out.println("\n\tTRANSCRIPT (" + maps.getTranscripts().size() + ")");
			System.out.println("\t--------------------------------------");
			for (Transcript transcript : maps.getTranscripts()) {
				System.out.println("\t\t" + transcript.getStableId() + "\t" + transcript.getStart() + "\t" + transcript.getEnd());
			}
		}
		else{
			System.out.println("\n\tTRANSCRIPT: NULL");
		}
		
		if (maps.getExons() != null){
			System.out.println("\n\tEXON (" + maps.getExons().size() + ")");
			System.out.println("\t--------------------------------------");
			for (Exon exon : maps.getExons()) {
				System.out.println("\t\t" +exon.getStableId() + "\t" + exon.getStart() + "\t" + exon.getEnd());
			}
		}
		else{
			System.out.println("\n\tEXON: NULL");
		}
		
		if (maps.getSnp() != null){
			System.out.println("\n\tSNP (" + maps.getSnp().size() + ")");
			System.out.println("\t--------------------------------------");
			for (Snp snp : maps.getSnp()) {
				System.out.println("\t\t" + snp.getName() + "\t" + snp.getStart() + "\t" + snp.getEnd());
			}
		}
		else{
			System.out.println("\n\tSNP: NULL");
		}
		System.out.println("\n\nTest executed in: "+ executionTime +" ms");
		
	}
	
	
}

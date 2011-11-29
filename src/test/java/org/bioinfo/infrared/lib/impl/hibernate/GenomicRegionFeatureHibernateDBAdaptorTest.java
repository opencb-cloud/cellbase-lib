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
		genomicRegionFeatureDBAdaptor = dbAdaptorFact.getFeatureMapDBAdaptor("drerio");
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
		GenomicRegionFeatures maps = this.genomicRegionFeatureDBAdaptor.getByRegion("1", 4986414, 4986414);
		print(maps);
	}
	
	private void print(GenomicRegionFeatures maps){
		System.out.println("\nGENES (" + maps.getGenes().size() + ")");
		System.out.println("--------------------------------------");
		for (Gene gene : maps.getGenes()) {
			System.out.println(gene.getStableId() + "\t" + gene.getStart() + "\t" + gene.getEnd());
		}
		
		System.out.println("\nTRANSCRIPT (" + maps.getTranscripts().size() + ")");
		System.out.println("--------------------------------------");
		for (Transcript transcript : maps.getTranscripts()) {
			System.out.println(transcript.getStableId() + "\t" + transcript.getStart() + "\t" + transcript.getEnd());
		}
		
		System.out.println("\nEXON (" + maps.getExons().size() + ")");
		System.out.println("--------------------------------------");
		for (Exon exon : maps.getExons()) {
			System.out.println(exon.getStableId() + "\t" + exon.getStart() + "\t" + exon.getEnd());
		}
		
		System.out.println("\nSNP (" + maps.getSnp().size() + ")");
		System.out.println("--------------------------------------");
		for (Snp snp : maps.getSnp()) {
			System.out.println(snp.getName() + "\t" + snp.getStart() + "\t" + snp.getEnd());
		}
		
		
	}
	
	
}

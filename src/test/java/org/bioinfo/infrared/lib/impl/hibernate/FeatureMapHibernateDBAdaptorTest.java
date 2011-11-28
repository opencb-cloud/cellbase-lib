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

public class FeatureMapHibernateDBAdaptorTest {

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
	}
	
	
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

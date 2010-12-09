package org.bioinfo.infrared.variation;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.variation.AnnotatedSNP;
import org.junit.Test;

public class AnnotatedSnpDBManagerTest {

	DBConnector infraDBConnector = new DBConnector();
	AnnotatedSnpDBManager annotSnpsDBManager = new AnnotatedSnpDBManager(infraDBConnector);
	
	@Test
	public void testGetAllAnnotatedSnpsById() {
		System.out.println("Test 1\n");
		try {
			FeatureList<AnnotatedSNP> annotSnps = annotSnpsDBManager.getAllById("rs16862847");
			System.out.println("Annotated Snps: "+annotSnps.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testGetAllAnnotatedSnpsByIds() {
		System.out.println("\nTest 2\n");
		try {
			List<FeatureList<AnnotatedSNP>> annotSnps = annotSnpsDBManager.getAllByIds(Arrays.asList("rs3212227", "rs3766379", "rs221667", "rs219780", "rs7910977"));
			System.out.println("Annotated Snps: "+annotSnps.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testGetAllAnnotatedSnps() {
		System.out.println("\nTest 3\n");
		try {
			FeatureList<AnnotatedSNP> annotSnps = annotSnpsDBManager.getAllAnnotatedSnps();
			System.out.println("Annotated Snps size: "+annotSnps.size());
			System.out.println("Annotated Snps size: "+annotSnps.get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}

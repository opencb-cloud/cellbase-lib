package org.bioinfo.infrared.variation;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.core.variation.VariationFrequency;
import org.bioinfo.infrared.variation.VariationFrequencyDBManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VariationFrequencyDBManagerTest {

	DBConnector infDBConnector;
	VariationFrequencyDBManager vf;
	
	@Before
	public void setUp() throws Exception {
		infDBConnector = new DBConnector();
		vf = new VariationFrequencyDBManager(infDBConnector);
		System.out.println("VariationFrequencyDBManagerTest");
		System.out.println("===============================================");
	}

	@After
	public void tearDown() throws Exception {
		infDBConnector.getDbConnection().disconnect();
	}
	
	public void testGetBySnpId() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetBySnpIdsListOfString() {
		System.out.println("testGetBySnpIdsListOfString");
		try {
			List<VariationFrequency> vfs = vf.getBySnpIds(Arrays.asList("rs1516713", "rs1502938", "rs1502937"));
			System.out.println(vfs.toString());
			System.out.println("\nCEU and JPT population:");
			System.out.println(vfs.get(0).filterAlleleFrequencies(Arrays.asList("CEU", "JPT")).toString());
			System.out.println(vfs.get(0).filterGenotypeFrequencies(Arrays.asList("CEU", "JPT")).toString());
			System.out.println("\nAll population:");
			System.out.println(vfs.get(0).getAlleleFrequenciesAsList().toString());
			System.out.println(vfs.get(0).getGenotypeFrequenciesAsList().toString());
			System.out.println("\nCEU and JPT population:");
			System.out.println(vfs.get(0).getAlleleFrequenciesAsList(Arrays.asList("CEU", "JPT")).toString());
			System.out.println(vfs.get(0).getGenotypeFrequenciesAsList(Arrays.asList("CEU", "JPT")).toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		} 
	}

	public void testGetBySnpIdsListOfStringDouble() {
//		fail("Not yet implemented");
	}

}

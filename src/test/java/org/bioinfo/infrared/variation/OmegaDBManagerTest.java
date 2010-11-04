package org.bioinfo.infrared.variation;

import static org.junit.Assert.fail;

import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.variation.Omega;
import org.bioinfo.infrared.variation.OmegaDBManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OmegaDBManagerTest {

	DBConnector ros = new DBConnector();
	OmegaDBManager omega;
	
	@Before
	public void setUp() throws Exception {
		ros = new DBConnector();
		omega = new OmegaDBManager(ros);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAll() {
		System.out.println("Test 1");
		FeatureList<Omega> o;
		try {
			o = omega.getAll();
			System.out.println(o);
		} catch (Exception e) {
			fail(e.toString());
			e.printStackTrace();
		} 
	}
	
	@Test
	public void testGetAllBySnpId() {
		System.out.println("Test 2");
		FeatureList<Omega> o;
		try {
			o = omega.getAllBySnpId("rs9653599");
			System.out.println(o);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		} 
	}

	@Test
	public void testWriteAll() {
		System.out.println("Test 3");
		try {
			omega.writeAll("/tmp/omega_test3.txt");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		} 

	}
	
	@Test
	public void testGetAllBySnpIds() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetAllByTranscriptId() {
//		fail("Not yet implemented");
	}

}

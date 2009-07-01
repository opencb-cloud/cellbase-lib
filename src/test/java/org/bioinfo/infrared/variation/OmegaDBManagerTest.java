package org.bioinfo.infrared.variation;

import static org.junit.Assert.fail;

import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.variation.dbsql.OmegaDBManager;
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
	public void testGetAllBySnpId() {
		FeatureList<Omega> o;
		try {
			o = omega.getAllBySnpId("rs9653599");
			System.out.println(o);
		} catch (Exception e) {
			fail(e.toString());
			e.printStackTrace();
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

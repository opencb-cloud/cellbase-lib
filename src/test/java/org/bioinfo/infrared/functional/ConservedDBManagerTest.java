package org.bioinfo.infrared.functional;

import static org.junit.Assert.fail;

import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.regulatory.ConservedRegion;
import org.bioinfo.infrared.regulatory.dbsql.ConservedRegionDBManager;
import org.junit.Before;
import org.junit.Test;


public class ConservedDBManagerTest {

	DBConnector ros = new DBConnector();
	ConservedRegionDBManager cdbf;
	
	@Before
	public void setUp() throws Exception {
		cdbf = new ConservedRegionDBManager(ros);
	}
	
	@Test
	public void testGetAllByRegion() {	
		try {
			FeatureList<ConservedRegion> cl = cdbf.getAllByRegion("19", 63748441, 63769730);
			header("testGetAllByRegion");
			System.out.println(cl.toString());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void testGetAllByLocation() {
		try {
			FeatureList<ConservedRegion> cl = cdbf.getAllByPosition("19", 63781247);
			header("testGetAllByLocation");
			System.out.println(cl.toString());
		} catch (Exception e) {
			fail(e.toString());
		}
	}


	private void header(String title){
		System.out.println();
		System.out.println();
		System.out.println(title + "========>");
		System.out.println();
	}

}

package org.bioinfo.infrared.functional;

import static org.junit.Assert.fail;

import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.funcannot.GO;
import org.bioinfo.infrared.funcannot.dbsql.GODBManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GOFactoryTest {

	DBConnector ros;
	GODBManager gof;
	@Before
	public void setUp() throws Exception {
		ros = new DBConnector();
		gof = new GODBManager(ros);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetByAccesion() {
		try {
			GO go = gof.getByAccesion("GO:0000009");
			if(go != null) {
				System.out.println(go.toString());
				System.out.println(go.getParents().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testGetParents() {
		try {
			FeatureList<GO> gos = gof.getParentsByAccesion("GO:0000009");
			System.out.println(gos.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testGetAllByNamespace() {
		try {
			FeatureList<GO> gos = gof.getAllByNamespace("biological_process");
			System.out.println(gos.size());
			System.out.println(gos.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testGetAllByLevel() {
		try {
			FeatureList<GO> gos = gof.getAllByLevel(5, 7);
			System.out.println(gos.size());
			System.out.println(gos.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testGetAllByNumberOfGenes() {
		try {
			FeatureList<GO> gos = gof.getAllByNumberOfGenes(10, 20);
			System.out.println(gos.size());
			System.out.println(gos.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testGetAllByFilter() {
		try {
			FeatureList<GO> gos = gof.getAllByFilter("biological_process", 5, 7, 10, 20);
			System.out.println(gos.size());
			System.out.println(gos.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}

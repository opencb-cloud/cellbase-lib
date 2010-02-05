package org.bioinfo.infrared.variation.dbsql;

import static org.junit.Assert.fail;

import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.junit.Test;

public class AlleleFrequencyDBManagerTest {

	@Test
	public void testGetAllByMAFDoubleDouble() {
		System.out.println("Test 1");
		DBConnector conn = new DBConnector();
		AlleleFrequencyDBManager man = new AlleleFrequencyDBManager(conn);
		try {
			System.out.println(man.getAllByMAF(0.1, 0.105));
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetAllByMAFDoubleDouble: "+e.toString());
		}
	}

	@Test
	public void testGetAllByMAFDoubleDoubleString() {
		System.out.println("Test 2");
		DBConnector conn = new DBConnector();
		AlleleFrequencyDBManager man = new AlleleFrequencyDBManager(conn);
		try {
			System.out.println(man.getAllByMAF(0.1, 0.11, "CEU"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetAllByMAFDoubleDoubleString: "+e.toString());
		}
	}

}

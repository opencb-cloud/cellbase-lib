package org.bioinfo.infrared.regulatory.dbsql;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.junit.Test;

public class OregannoTfbsDBManagerTest {

	public void testGetAllByGeneId() {
		fail("Not yet implemented");
	}

	public void testGetAllBySnpId() {
		fail("Not yet implemented");
	}

	public void testGetAllBySnpIds() {
		fail("Not yet implemented");
	}

	public void testGetAll() {
		fail("Not yet implemented");
	}

	public void testGetAllByLocation() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSnpsByIds() {
		System.out.println("Test 1");
		DBConnector conn = new DBConnector();
		OregannoTfbsDBManager man = new OregannoTfbsDBManager(conn);
		try {
			System.out.println(man.getSnpsByIds(Arrays.asList("OREG0000022", "OREG0000031")).toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetAllByMAFDoubleDouble: "+e.toString());
		}
	}

}

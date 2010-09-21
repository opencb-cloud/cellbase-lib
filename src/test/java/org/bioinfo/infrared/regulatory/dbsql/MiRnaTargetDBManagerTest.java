package org.bioinfo.infrared.regulatory.dbsql;

import static org.junit.Assert.fail;

import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.regulatory.MiRnaTargetDBManager;
import org.junit.Test;

public class MiRnaTargetDBManagerTest {

	public void testGetAllByMatureId() {
		System.out.println("Test 1");
		fail("Not yet implemented");
	}

	public void testGetAllByMatureIds() {
		System.out.println("Test 2");
		fail("Not yet implemented");
	}

	public void testGetAllByTranscriptId() {
		System.out.println("Test 3");
		fail("Not yet implemented");
	}

	public void testGetAllByTranscriptIds() {
		System.out.println("Test 4");
		fail("Not yet implemented");
	}

	public void testGetAllByLocation() {
		System.out.println("Test 5");
		fail("Not yet implemented");
	}

	public void testGetAllByRegion() {
		System.out.println("Test 6");
		fail("Not yet implemented");
	}

	public void testGetAllByScore() {
		System.out.println("Test 7");
		fail("Not yet implemented");
	}

	public void testGetAllByPValue() {
		System.out.println("Test 8");
		fail("Not yet implemented");
	}

	public void testGetAllByGeneId() {
		System.out.println("Test 9");
		fail("Not yet implemented");
	}

	public void testGetAllBySnpId() {
		System.out.println("Test 10");
		fail("Not yet implemented");
	}

	public void testGetAllBySnpIds() {
		System.out.println("Test 11");
		fail("Not yet implemented");
	}

	@Test
	public void testWruteAllWithSnps() {
		System.out.println("Test 12");
		DBConnector conn = new DBConnector();
		MiRnaTargetDBManager man = new MiRnaTargetDBManager(conn);
		try {
			man.writeAllWithSnps("/tmp/mirna_target_test12.txt");
		} catch (Exception e) {
			e.printStackTrace();
			fail("testWruteAllWithSnps: "+e.toString());
		}
	}

}

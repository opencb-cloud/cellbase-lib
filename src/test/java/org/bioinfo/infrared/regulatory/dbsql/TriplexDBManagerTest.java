package org.bioinfo.infrared.regulatory.dbsql;

import static org.junit.Assert.fail;

import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.regulatory.TriplexDBManager;
import org.junit.Test;

public class TriplexDBManagerTest {

	public void testGetAllByGeneIds() {
		System.out.println("Test 1");
	}

	public void testGetAllByGeneId() {
		System.out.println("Test 2");
	}

	public void testGetAllBySnpId() {
		System.out.println("Test 3");
	}

	public void testGetAllBySnpIds() {
		System.out.println("Test 4");
	}

	public void testGetAll() {
		System.out.println("Test 5");
	}

	public void testGetAllByLocation() {
		System.out.println("Test 6");
	}

	public void testGetAllByRegion() {
		System.out.println("Test 7");
	}

	@Test
	public void testWriteAllWithSnps() {
		System.out.println("Test 8");
		DBConnector conn = new DBConnector();
		TriplexDBManager man = new TriplexDBManager(conn);
		try {
			man.writeAllWithSnps("/tmp/triplex_test8.txt");
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetAllByMAFDoubleDoubleString: "+e.toString());
		}
	}

}

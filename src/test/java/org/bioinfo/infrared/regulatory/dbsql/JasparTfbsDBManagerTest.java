package org.bioinfo.infrared.regulatory.dbsql;

import static org.junit.Assert.fail;

import java.util.Arrays;

import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.junit.Test;

public class JasparTfbsDBManagerTest {

	
	public void testGetAll() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testWriteAll() {
		System.out.println("Test 1");
		DBConnector conn = new DBConnector();
		JasparTfbsDBManager man = new JasparTfbsDBManager(conn);
		try {
			man.writeAll("/tmp/jaspar_tfbs_test1.txt");
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetAllByMAFDoubleDouble: "+e.toString());
		}
	}
	
	@Test
	public void testWriteAllWithSnps() {
		System.out.println("Test 2");
		DBConnector conn = new DBConnector();
		JasparTfbsDBManager man = new JasparTfbsDBManager(conn);
		try {
			man.writeAllWithSnps("/tmp/jaspar_tfbs_test2.txt");
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetAllByMAFDoubleDouble: "+e.toString());
		}
	}
	
	public void testGetAllByGeneId() {
		fail("Not yet implemented");
	}

	public void testGetAllBySnpId() {
		fail("Not yet implemented");
	}

	public void testGetAllBySnpIds() {
		fail("Not yet implemented");
	}

	public void testGetAllByLocation() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSnpsByIds() {
		System.out.println("\nTest 3");
		DBConnector conn = new DBConnector();
		JasparTfbsDBManager man = new JasparTfbsDBManager(conn);
		try {
			System.out.println(man.getSnpsByIds(Arrays.asList("GATA2", "FOXC1")).toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetAllByMAFDoubleDouble: "+e.toString());
		}
	}

}

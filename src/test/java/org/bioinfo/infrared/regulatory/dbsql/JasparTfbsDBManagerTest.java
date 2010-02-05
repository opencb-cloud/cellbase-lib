package org.bioinfo.infrared.regulatory.dbsql;

import static org.junit.Assert.fail;

import java.util.Arrays;

import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.junit.Test;

public class JasparTfbsDBManagerTest {

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
		JasparTfbsDBManager man = new JasparTfbsDBManager(conn);
		try {
			System.out.println(man.getSnpsByIds(Arrays.asList("GATA2", "FOXC1")).toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetAllByMAFDoubleDouble: "+e.toString());
		}
	}

}

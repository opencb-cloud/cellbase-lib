package org.bioinfo.infrared.regulatory.dbsql;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.junit.Test;

public class TransfacTfbsDBManagerTest {

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

	@Test
	public void testGetSnpsByIds() {
		System.out.println("Test 1");
		DBConnector conn = new DBConnector();
		TransfacTfbsDBManager man = new TransfacTfbsDBManager(conn);
		try {
			System.out.println(man.getSnpsByIds(Arrays.asList("V$HNF1_Q6_01", "V$HNF1_Q6")).toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetAllByMAFDoubleDouble: "+e.toString());
		}
	}

}

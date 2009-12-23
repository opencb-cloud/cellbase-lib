package org.bioinfo.infrared.common.dbsql;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class DBConnectorTest {

	@Test
	public void testGetAllChromosomes() {
		DBConnector conn = new DBConnector("hsa");
		try {
			System.out.println(conn.getAllChromosomes());
			conn.disconnect();
		} catch (Exception e) {
			fail("Not yet implemented");
			e.printStackTrace();
		}
		
	}

	@Test
	public void testGetAvailableDBs() {
		DBConnector conn = new DBConnector("hsa");
		System.out.println(conn.getAvailableDBs());
	}

	@Test
	public void testIsValidSpecies() {
//		DBConnector conn = new DBConnector("hsa");
//		fail("Not yet implemented");
	}

}

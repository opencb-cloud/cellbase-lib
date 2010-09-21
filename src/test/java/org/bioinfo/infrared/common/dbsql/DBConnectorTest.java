package org.bioinfo.infrared.common.dbsql;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.bioinfo.infrared.common.DBConnector;
import org.junit.Test;

public class DBConnectorTest {

	@Test
	public void testGetAllChromosomes() {
		System.out.println("\nTest - 1");
		DBConnector conn = new DBConnector("homo_sapiens");
		try {
			System.out.println(conn.getAllChromosomes());
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
//			fail(e.toString());
		}
		
	}

	@Test
	public void testGetAvailableDBs() {
		System.out.println("\nTest - 2");
		DBConnector conn = new DBConnector("hsa");
		try {
			System.out.println(conn.getAvailableDBs());
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testIsValidSpecies() {
		System.out.println("\nTest - 3");
		DBConnector conn = new DBConnector("homo_sapiens");
		try {
			System.out.println(conn.isValidSpecies("homo_sapiens"));
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

}

package org.bioinfo.infrared.regulatory.dbsql;

import static org.junit.Assert.fail;

import java.util.Arrays;

import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.junit.Test;

public class MiRnaGeneDBManagerTest {

	@Test
	public void testGetAllByLocation() {
		System.out.println("Test 1");
		DBConnector conn = new DBConnector();
		MiRnaGeneDBManager man = new MiRnaGeneDBManager(conn);
		try {
			System.out.println(man.getAllByLocation("1", 23456));
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetAllByMAFDoubleDouble: "+e.toString());
		}
	}

	@Test
	public void testGetAllBySnpIds() {
		System.out.println("Test 2");
		DBConnector conn = new DBConnector();
		MiRnaGeneDBManager man = new MiRnaGeneDBManager(conn);
		try {
			System.out.println(man.getAllBySnpIds(Arrays.asList("rs11671784","rs895819")));
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetAllByMAFDoubleDouble: "+e.toString());
		}
	}
	
	@Test
	public void testWriteAllWithSnps() {
		System.out.println("Test 3");
		DBConnector conn = new DBConnector();
		MiRnaGeneDBManager man = new MiRnaGeneDBManager(conn);
		try {
			man.writeAllWithSnps("/tmp/mirna_gene_test3.txt");
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetAllByMAFDoubleDouble: "+e.toString());
		}
	}
	
	
}

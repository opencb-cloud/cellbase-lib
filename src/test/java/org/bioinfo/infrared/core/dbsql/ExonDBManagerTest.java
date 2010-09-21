package org.bioinfo.infrared.core.dbsql;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.core.ExonDBManager;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.feature.Exon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExonDBManagerTest {

	private DBConnector infraDBConnector;
	private ExonDBManager exonDBManager;
	
	@Before
	public void setUp() throws Exception {
		infraDBConnector = new DBConnector();
		exonDBManager = new ExonDBManager(infraDBConnector);
	}

	@After
	public void tearDown() throws Exception {
		infraDBConnector.disconnect();
	}

	@Test
	public void testGetAllById() {
		System.out.println("Test 1");
		try {
			FeatureList<Exon> exon = exonDBManager.getAllById("brca2");
			System.out.println(exon.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testGetAllByIds() {
		System.out.println("Test 2");
		try {
			List<FeatureList<Exon>> exons = exonDBManager.getAllByIds(Arrays.asList("brca1", "brca2", "p53", "bcl2"));
			System.out.println(exons.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testGetAllByPosition() {
		System.out.println("Test 3");
		try {
			FeatureList<Exon> exon = exonDBManager.getAllByPosition("5", 179028898);
			System.out.println(exon.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}

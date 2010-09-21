package org.bioinfo.infrared.functional;

import static org.junit.Assert.fail;

import java.util.List;

import org.bioinfo.commons.utils.StringUtils;
import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.regulatory.Triplex;
import org.bioinfo.infrared.regulatory.TriplexDBManager;
import org.junit.Before;
import org.junit.Test;

public class TriplexDBManagerTest {

	DBConnector ros = new DBConnector();
	TriplexDBManager tdbf;
	
	@Before
	public void setUp() throws Exception {
		tdbf = new TriplexDBManager(ros);
	}

	@Test
	public void testGetAllByGeneId() {
		try {
			FeatureList<Triplex> tl = tdbf.getAllByGeneId("ENSG00000220962");
			header("testGetAllByGeneId");
			System.out.println(tl.toString());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	@Test
	public void testGetAllByGeneIds() {
		try {
			List<FeatureList<Triplex>> tl = tdbf.getAllByGeneIds(StringUtils.stringToList("ENSG00000220962 ENSG00000120327"));
			header("testGetAllByGeneIds");
			System.out.println(tl.toString());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	@Test
	public void testGetAllByRegion() {
		try {
			FeatureList<Triplex> tl = tdbf.getAllByRegion("5", 148706400, 148965015);
			header("testGetAllByRegion");
			System.out.println(tl.toString());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void testGetAllByLocation() {
		try {
			FeatureList<Triplex> tl = tdbf.getAllByPosition("5", 148706475);
			header("testGetAllByLocation");
			System.out.println(tl.toString());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	private void header(String title){
		System.out.println();
		System.out.println();
		System.out.println(title + "========>");
		System.out.println();
	}
}

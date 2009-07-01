package org.bioinfo.infrared.functional;

import static org.junit.Assert.fail;

import java.util.List;

import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.regulatory.MiRNATarget;
import org.bioinfo.infrared.regulatory.dbsql.MiRNATargetDBManager;
import org.bioinfo.utils.StringUtils;
import org.junit.Before;
import org.junit.Test;

public class MiRNATargetDBManagerTest {

	DBConnector ros = new DBConnector();
	MiRNATargetDBManager mitdbf;
	
	@Before
	public void setUp() throws Exception {
		mitdbf = new MiRNATargetDBManager(ros);
	}

	@Test
	public void testGetAllByMatureId() {
		try {
			FeatureList<MiRNATarget> mitl = mitdbf.getAllByMatureId("hsa-miR-454");
			header("testGetByMatureId");
			System.out.println(mitl.toString());
		} catch (Exception e) {
			fail(e.toString());
		}		
	}

	@Test
	public void testGetAllByMatureIds() {
		try {
			List<FeatureList<MiRNATarget>> mitl = mitdbf.getAllByMatureIds(StringUtils.stringToList("hsa-miR-454 hsa-miR-17"));
			header("testGetByMatureIds");
			System.out.println(mitl.toString());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	
	@Test
	public void testGetAllByTranscriptId() {
		try {
			FeatureList<MiRNATarget> mitl = mitdbf.getAllByTranscriptId("ENST00000038176");
			header("testGetAllByTranscriptId");
			System.out.println(mitl.toString());
		} catch (Exception e) {
			fail(e.toString());
		}		
	}

	@Test
	public void testGetAllByTranscriptIds() {
		try {
			List<FeatureList<MiRNATarget>> mitl = mitdbf.getAllByTranscriptIds(StringUtils.stringToList("ENST00000038176 ENST00000348568"));
			header("testGetAllByTranscriptIds");
			System.out.println(mitl.toString());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void testGetAllByRegion() {	
		try {
			FeatureList<MiRNATarget> mitl = mitdbf.getAllByRegion("19", 60723000, 60723060);
			header("testGetAllByRegion");
			System.out.println(mitl.toString());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void testGetAllByLocation() {
		try {
			FeatureList<MiRNATarget> mitl = mitdbf.getAllByLocation("19", 60723020);
			header("testGetAllByLocation");
			System.out.println(mitl.toString());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void testGetAllByScore() {
		try {
			FeatureList<MiRNATarget> mitl = mitdbf.getAllByScore(22);
			header("testGetAllByScore");
			System.out.println(mitl.toString());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	public void testGetAllByPValue() {
		try {
			FeatureList<MiRNATarget> mitl = mitdbf.getAllByPValue(1.0E-16);
			header("testGetAllByPValue");
			System.out.println(mitl.toString());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testGetAllLocation() {
		try {
			FeatureList<MiRNATarget> mitl = mitdbf.getAllByLocation("2", 120824264);
			header("testGetAllLocation");
			System.out.println(mitl.toString());
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

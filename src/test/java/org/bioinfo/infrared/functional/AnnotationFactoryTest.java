package org.bioinfo.infrared.functional;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.funcannot.AnnotationItem;
import org.bioinfo.infrared.funcannot.dbsql.AnnotationDBManager;
import org.bioinfo.infrared.funcannot.filter.GOFilter;
import org.bioinfo.infrared.funcannot.filter.KeggFilter;
import org.bioinfo.utils.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AnnotationFactoryTest {

	DBConnector ros;
	AnnotationDBManager af;
	@Before
	public void setUp() throws Exception {
		ros = new DBConnector();
		af = new AnnotationDBManager(ros);
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testGetGOAnnotation() {
//		System.out.println("Test - 1");
//		try {
//			AnnotationList al = af.getGOAnnotationByFilter("biological_process", 3, 19, 10, 500);
//			System.out.println(al.size());
//		} catch (SQLException e) {
//			e.printStackTrace();
//			fail("Not yet implemented");
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail("Not yet implemented");
//		} 
//	}

	@Test
	public void testGetGOAnnotationByFilter() {
		System.out.println("Test - 1");
		GOFilter gof = new GOFilter("biological_process");
		gof.setMinLevel(3);
		gof.setMaxLevel(19);
		gof.setMinNumberGenes(10);
		gof.setMaxNumberGenes(500);
//		gof.addKeyword("+apoptosis");
//		gof.addKeyword("-transport");
//		gof.addKeyword("-gut");
//		gof.setLogicalOperator("and");
		try {
			FeatureList<AnnotationItem> al = af.getGOAnnotation(gof);
			System.out.println(al.size());
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} 
	}
	
	@Test
	public void testGetGOAnnotationByIds() {
		System.out.println("Test - 2");
		GOFilter gof = new GOFilter("biological_process");
		gof.setMinLevel(3);
		gof.setMaxLevel(19);
		gof.setMinNumberGenes(1);
		gof.setMaxNumberGenes(10);
		gof.addKeyword("-apoptosis");
		gof.addKeyword("-transport");
		gof.setLogicalOperator("AND");
		String ids = "FGFR2 PCDH15 IL7R SPOCK ENSG00000037280,ENSG00000038382,ENSG00000044524,ENSG00000070019,ENSG00000072401,ENSG00000077943,ENSG00000078549,ENSG00000080224,ENSG00000080815,ENSG00000084234,ENSG00000089159,ENSG00000095637,ENSG00000099250,ENSG00000100784,ENSG00000101134,ENSG00000102755,ENSG00000037280,ENSG00000038382,ENSG00000044524,ENSG00000070019,ENSG00000072401,ENSG00000077943,ENSG00000078549,ENSG00000080224,ENSG00000080815,ENSG00000084234,ENSG00000089159,ENSG00000095637,ENSG00000099250,ENSG00000100784,ENSG00000101134,ENSG00000102755";
		try {
			FeatureList<AnnotationItem> al = af.getGOAnnotation(StringUtils.stringToList(ids), gof);
			System.out.println(al.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} 
	}
	

	@Test
	public void testGetKeggAnnotation() {
		System.out.println("Test - 3");
		try {
			KeggFilter kf = new KeggFilter(2,200);
			FeatureList<AnnotationItem> al = af.getKeggAnnotation(kf);
			System.out.println(al.size());
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} 
	}

}

package org.bioinfo.infrared.functional;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.bioinfo.commons.utils.StringUtils;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.funcannot.AnnotationItem;
import org.bioinfo.infrared.funcannot.dbsql.AnnotationDBManager;
import org.bioinfo.infrared.funcannot.filter.GOFilter;
import org.bioinfo.infrared.funcannot.filter.GOSlimFilter;
import org.bioinfo.infrared.funcannot.filter.InterproFilter;
import org.bioinfo.infrared.funcannot.filter.JasparFilter;
import org.bioinfo.infrared.funcannot.filter.KeggFilter;
import org.bioinfo.infrared.funcannot.filter.OregannoFilter;
import org.bioinfo.infrared.funcannot.filter.ReactomeFilter;
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
		gof.setMaxLevel(9);
		gof.setMinNumberGenes(10);
		gof.setMaxNumberGenes(500);
		gof.addKeyword("+apoptosis");
		gof.addKeyword("-anti");
		gof.setLogicalOperator("and");
		gof.setGenomicNumberOfGenes(true);
		try {
			FeatureList<AnnotationItem> al = af.getGOAnnotation(gof);
			System.out.println(al.size());
//			System.out.println(al.toString());
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
		gof.setMaxLevel(9);
		gof.setMinNumberGenes(1);
		gof.setMaxNumberGenes(10);
		gof.addKeyword("+apoptosis");
		gof.addKeyword("-transport");
		gof.setLogicalOperator("AND");
		String ids = "FGFR2 PCDH15 IL7R SPOCK ENSG00000037280,ENSG00000038382,ENSG00000044524,ENSG00000070019,ENSG00000072401,ENSG00000077943,ENSG00000078549,ENSG00000080224,ENSG00000080815,ENSG00000084234,ENSG00000089159,ENSG00000095637,ENSG00000099250,ENSG00000100784,ENSG00000101134,ENSG00000102755,ENSG00000037280,ENSG00000038382,ENSG00000044524,ENSG00000070019,ENSG00000072401,ENSG00000077943,ENSG00000078549,ENSG00000080224,ENSG00000080815,ENSG00000084234,ENSG00000089159,ENSG00000095637,ENSG00000099250,ENSG00000100784,ENSG00000101134,ENSG00000102755";
		try {
			long t1 = System.currentTimeMillis();
			FeatureList<AnnotationItem> al = af.getGOAnnotation(StringUtils.toList(ids, ","), gof);
			System.out.println("time: "+(System.currentTimeMillis()-t1));
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
	public void testGetGOSLimAnnotationByIds() {
		System.out.println("Test - 2.1");
		GOSlimFilter gsf = new GOSlimFilter();
		String ids = "ENST00000257215,ENST00000434841,FGFR2,PCDH15,IL7R,SPOCK,ENST00000449252,ENSG00000037280,ENST00000480017,ENSG00000038382,ENST00000425036,ENSG00000044524,ENSG00000070019,ENSG00000072401,ENSG00000077943,ENSG00000078549,ENSG00000080224,ENSG00000080815,ENSG00000084234,ENSG00000089159,ENSG00000095637,ENSG00000099250,ENSG00000100784,ENSG00000101134,ENSG00000102755,ENSG00000037280,ENSG00000038382,ENSG00000044524,ENSG00000070019,ENSG00000072401,ENSG00000077943,ENSG00000078549,ENSG00000080224,ENSG00000080815,ENSG00000084234,ENSG00000089159,ENSG00000095637,ENSG00000099250,ENSG00000100784,ENSG00000101134,ENSG00000102755";
		try {
			long t1 = System.currentTimeMillis();
			FeatureList<AnnotationItem> al = af.getGOSlimAnnotation(StringUtils.toList(ids, ","), gsf);
			System.out.println("time: "+(System.currentTimeMillis()-t1));
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
	public void testGetKeggAnnotation() {
		System.out.println("Test - 3");
		try {
			KeggFilter kf = new KeggFilter(5,60);
			long t1 = System.currentTimeMillis();
			FeatureList<AnnotationItem> al = af.getKeggAnnotation(kf);
			System.out.println("time: "+(System.currentTimeMillis()-t1));
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
	public void testGetKeggAnnotationList() {
		System.out.println("Test - 3.1");
		try {
			KeggFilter kf = new KeggFilter(5,60);
			String ids = "FGFR2,ENST00000261597,ENST00000431386,PCDH15,IL7R,SPOCK,ENST00000449252,ENSG00000037280,ENST00000440336,ENST00000480017,ENSG00000038382,ENST00000425036,ENST00000433804,ENSG00000044524,ENSG00000070019,ENSG00000072401,ENSG00000077943,ENSG00000078549,ENSG00000080224,ENSG00000080815,ENSG00000084234,ENSG00000089159,ENSG00000095637,ENSG00000099250,ENSG00000100784,ENSG00000101134,ENSG00000102755,ENSG00000037280,ENSG00000038382,ENSG00000044524,ENSG00000070019,ENSG00000072401,ENSG00000077943,ENSG00000078549,ENSG00000080224,ENSG00000080815,ENSG00000084234,ENSG00000089159,ENSG00000095637,ENSG00000099250,ENSG00000100784,ENSG00000101134,ENSG00000102755";
			long t1 = System.currentTimeMillis();
			FeatureList<AnnotationItem> al = af.getKeggAnnotation(StringUtils.toList(ids, ","), kf);
			System.out.println("time: "+(System.currentTimeMillis()-t1));
			System.out.println(al.size());
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
	public void testGetInterproAnnotationByIds() {
		System.out.println("Test - 4");
		InterproFilter rf = new InterproFilter();
		rf.setMinNumberGenes(1);
		rf.setMaxNumberGenes(40);
		rf.setGenomicNumberOfGenes(false);
		String ids = "FGFR2,ENST00000261597,ENST00000431386,PCDH15,IL7R,SPOCK,ENST00000449252,ENSG00000037280,ENST00000440336,ENST00000480017,ENSG00000038382,ENST00000425036,ENST00000433804,ENSG00000044524,ENSG00000070019,ENSG00000072401,ENSG00000077943,ENSG00000078549,ENSG00000080224,ENSG00000080815,ENSG00000084234,ENSG00000089159,ENSG00000095637,ENSG00000099250,ENSG00000100784,ENSG00000101134,ENSG00000102755,ENSG00000037280,ENSG00000038382,ENSG00000044524,ENSG00000070019,ENSG00000072401,ENSG00000077943,ENSG00000078549,ENSG00000080224,ENSG00000080815,ENSG00000084234,ENSG00000089159,ENSG00000095637,ENSG00000099250,ENSG00000100784,ENSG00000101134,ENSG00000102755";
		try {
			long t1 = System.currentTimeMillis();
			FeatureList<AnnotationItem> al = af.getInterproAnnotation(StringUtils.toList(ids, ","), rf);
			System.out.println("time: "+(System.currentTimeMillis()-t1));
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
	public void testGetReactomeAnnotationByIds() {
		System.out.println("Test - 5");
		ReactomeFilter rf = new ReactomeFilter();
		rf.setMinNumberGenes(1);
		rf.setMaxNumberGenes(200);
		String ids = "ENST00000257215,ENST00000434841,FGFR2,PCDH15,IL7R,SPOCK,ENST00000449252,ENSG00000037280,ENST00000480017,ENSG00000038382,ENST00000425036,ENSG00000044524,ENSG00000070019,ENSG00000072401,ENSG00000077943,ENSG00000078549,ENSG00000080224,ENSG00000080815,ENSG00000084234,ENSG00000089159,ENSG00000095637,ENSG00000099250,ENSG00000100784,ENSG00000101134,ENSG00000102755,ENSG00000037280,ENSG00000038382,ENSG00000044524,ENSG00000070019,ENSG00000072401,ENSG00000077943,ENSG00000078549,ENSG00000080224,ENSG00000080815,ENSG00000084234,ENSG00000089159,ENSG00000095637,ENSG00000099250,ENSG00000100784,ENSG00000101134,ENSG00000102755";
		try {
			long t1 = System.currentTimeMillis();
			FeatureList<AnnotationItem> al = af.getReactomeAnnotation(StringUtils.toList(ids, ","), rf);
			System.out.println("time: "+(System.currentTimeMillis()-t1));
			System.out.println(al.size());
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} 
	}
	
	public void testGetJasparAnnotationByIds() {
		System.out.println("Test - 6");
		JasparFilter jf = new JasparFilter();
		jf.setMinNumberGenes(1);
		jf.setMaxNumberGenes(200);
		String ids = "ENST00000257215,ENST00000434841,FGFR2,PCDH15,IL7R,SPOCK,ENST00000449252,ENSG00000037280,ENST00000480017,ENSG00000038382,ENST00000425036,ENSG00000044524,ENSG00000070019,ENSG00000072401,ENSG00000077943,ENSG00000078549,ENSG00000080224,ENSG00000080815,ENSG00000084234,ENSG00000089159,ENSG00000095637,ENSG00000099250,ENSG00000100784,ENSG00000101134,ENSG00000102755,ENSG00000037280,ENSG00000038382,ENSG00000044524,ENSG00000070019,ENSG00000072401,ENSG00000077943,ENSG00000078549,ENSG00000080224,ENSG00000080815,ENSG00000084234,ENSG00000089159,ENSG00000095637,ENSG00000099250,ENSG00000100784,ENSG00000101134,ENSG00000102755";
		try {
			long t1 = System.currentTimeMillis();
			FeatureList<AnnotationItem> al = af.getJasparAnnotation(StringUtils.toList(ids, ","), jf);
			System.out.println("time: "+(System.currentTimeMillis()-t1));
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
	public void testGetOregannoAnnotationByIds() {
		System.out.println("Test - 7");
		OregannoFilter of = new OregannoFilter();
		of.setMinNumberGenes(1);
		of.setMaxNumberGenes(200);
		String ids = "ENST00000257215,ENST00000434841,FGFR2,PCDH15,IL7R,SPOCK,ENST00000449252,ENSG00000037280,ENST00000480017,ENSG00000038382,ENST00000425036,ENSG00000044524,ENSG00000070019,ENSG00000072401,ENSG00000077943,ENSG00000078549,ENSG00000080224,ENSG00000080815,ENSG00000084234,ENSG00000089159,ENSG00000095637,ENSG00000099250,ENSG00000100784,ENSG00000101134,ENSG00000102755,ENSG00000037280,ENSG00000038382,ENSG00000044524,ENSG00000070019,ENSG00000072401,ENSG00000077943,ENSG00000078549,ENSG00000080224,ENSG00000080815,ENSG00000084234,ENSG00000089159,ENSG00000095637,ENSG00000099250,ENSG00000100784,ENSG00000101134,ENSG00000102755";
		try {
			long t1 = System.currentTimeMillis();
			FeatureList<AnnotationItem> al = af.getOregannoAnnotation(StringUtils.toList(ids, ","), of);
			System.out.println("time: "+(System.currentTimeMillis()-t1));
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
	public void testGetAnnotationTermsSizeTest() {
		System.out.println("Test - 8");
		try {
			long t1 = System.currentTimeMillis();
			System.out.println(af.getAnnotationTermsSize("reactome").toString());
			System.out.println("time: "+(System.currentTimeMillis()-t1));
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} 
	}
	
	@Test
	public void testGetReactomeAnnotationTest() {
		System.out.println("Test - 9");
		try {
			long t1 = System.currentTimeMillis();
			System.out.println(af.getReactomeAnnotation(new ReactomeFilter()));
			System.out.println("time: "+(System.currentTimeMillis()-t1));
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} 
	}
}

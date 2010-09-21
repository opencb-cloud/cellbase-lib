package org.bioinfo.infrared.core;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bioinfo.commons.utils.ListUtils;
import org.bioinfo.commons.utils.StringUtils;
import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.core.XRefDBManager;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.feature.DBName;
import org.bioinfo.infrared.core.feature.XRef;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class XRefDBManagerTest {

	DBConnector man;
	XRefDBManager xrefDBMan;
	
	@Before
	public void setUp() throws Exception {
		man = new DBConnector();
		xrefDBMan = new XRefDBManager(man);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllIdsByDBName() {
		System.out.println("Test - 1");
		try {
//			List<String> names = xrefDBMan.getAllIdsByDBName("ipi");
			List<DBName> names = xrefDBMan.getAllDBNames();
			System.out.println(names.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		
	}

	@Test
	public void testGetByDBName() {
		System.out.println("\nTest - 2");
		try {
//			FeatureList<XRef> xrefs = xrefDBMan.getByDBNameOld("brca2", "uniprotkb/swissprot");
			XRef xref = xrefDBMan.getByDBName("brca2", "embl");
			if(xref != null) {
				System.out.println(xref.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} 
	}
	
	@Test
	public void testGetListByDBName() {
		System.out.println("\nTest - 3");
		try {
//			FeatureList<XRef> xrefs = xrefDBMan.getByDBNameOld("brca2", "uniprotkb/swissprot");
			List<XRef> xrefs = xrefDBMan.getByDBName(Arrays.asList("brca2", null, "bcl2"), "embl");
			System.out.println(ListUtils.toString(xrefs, "\n"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} 
	}
	
	@Test
	public void testGetByDBNames() {
		System.out.println("\nTest - 4");
		try {
//			FeatureList<XRef> xrefs = xrefDBMan.getByDBNameOld("brca2", "uniprotkb/swissprot");
			XRef xref = xrefDBMan.getByDBName("brca2", Arrays.asList("", "go", "kegg", "unigene", null, "embl"));
			System.out.println(xref.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} 
	}
	
	@Test
	public void testGetListByDBNames() {
		System.out.println("\nTest - 5");
		try {
//			FeatureList<XRef> xrefs = xrefDBMan.getByDBNameOld("brca2", "uniprotkb/swissprot");
			List<XRef> xrefs = xrefDBMan.getByDBName(Arrays.asList("brca2", null, "bcl2"), Arrays.asList("", "unigene", null, "embl"));
			System.out.println(ListUtils.toString(xrefs, "\n"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} 
	}

//	public void testGetByDBNames() {
//		System.out.println("Test - 3");
//		try {
//			HashMap<String, FeatureList<XRef>> xrefs = (HashMap<String, FeatureList<XRef>>) xrefDBMan.getByDBNames("brca2", StringUtils.stringToList(",", "uniprotkb/swissprot,ipi,go"));
//			System.out.println(xrefs.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail("Not yet implemented");
//		} 
//	}

	public void testGetXRefListByIds() {
		System.out.println("Test - 4");
		try {
			List<String> ids = StringUtils.toList(",", "p53,brca2");
			List<Map<String, FeatureList<XRef>>> xrefList = xrefDBMan.getListByDBNames(ids, StringUtils.toList("uniprotkb/swissprot,ensembl_transcript", ","));
//			for(Map map: xrefList) {
			for(int i=0;i<xrefList.size(); i++) {
				System.out.println(ids.get(i));
				System.out.println("==================================");
				System.out.println(xrefList.get(i).toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} 
	}
	
//	@Test
//	public void testGetIdsListByIds() {
//		System.out.println("Test - 5");
//		try {
//			List<String> ids = StringUtils.toList("p53,brca2,bcl2,ENSG00000141510", ",");
//			List<List<String>> xrefList = xrefDBMan.getIdsByDBName(ids, "interpro");
////			for(Map map: xrefList) {
//			for(int i=0;i<xrefList.size(); i++) {
//				System.out.println(ids.get(i));
//				System.out.println("==================================");
//				if(xrefList.get(i) != null) {
//					System.out.println(xrefList.get(i).toString());
//				}
//				
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail("Not yet implemented");
//		} 
//	}
	
	public void testGetAllDBNames() {
		System.out.println("Test - 5");
		try {
			System.out.println(xrefDBMan.getAllDBNames().toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		} 
	}
	
}

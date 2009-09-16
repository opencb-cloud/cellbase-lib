package org.bioinfo.infrared.core;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bioinfo.commons.utils.StringUtils;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.core.dbsql.XRefDBManager;
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
			List<String> names = xrefDBMan.getAllIdsByDBName("ipi");
			System.out.println(names.size());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		
	}

	@Test
	public void testGetByDBName() {
		System.out.println("Test - 2");
		try {
			FeatureList<XRef> xrefs = xrefDBMan.getByDBName("brca2", "uniprotkb/swissprot");
			System.out.println(xrefs.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} 
	}

	@Test
	public void testGetByDBNames() {
		System.out.println("Test - 3");
		try {
			HashMap<String, FeatureList<XRef>> xrefs = (HashMap<String, FeatureList<XRef>>) xrefDBMan.getByDBNames("brca2", StringUtils.stringToList(",", "uniprotkb/swissprot,ipi,go"));
			System.out.println(xrefs.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} 
	}

	@Test
	public void testGetXRefListByIds() {
		System.out.println("Test - 4");
		try {
			List<String> ids = StringUtils.stringToList(",", "p53,brca2");
			List<Map<String, FeatureList<XRef>>> xrefList = xrefDBMan.getListByDBNames(ids, StringUtils.stringToList(",", "uniprotkb/swissprot,ensembl_transcript"));
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
	
	@Test
	public void testGetIdsListByIds() {
		System.out.println("Test - 5");
		try {
			List<String> ids = StringUtils.stringToList(",", "p53,brca2,bcl2,ENSG00000141510");
			List<List<String>> xrefList = xrefDBMan.getIdsByDBName(ids, "interpro");
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

}

package org.bioinfo.infrared.variation.dbsql;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.bioinfo.commons.utils.StringUtils;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.variation.SNP;
import org.bioinfo.infrared.variation.dbsql.SNPDBManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SNPDBManagerTest {

	DBConnector ros;
	SNPDBManager sf;
	
	@Before
	public void setUp() throws Exception {
		ros = new DBConnector();
		sf = new SNPDBManager(ros);
	}

	

	@After
	public void tearDown() throws Exception {
		ros.getDbConnection().disconnect();
	}
	
	public void testAllSNPIdFactory() {
		System.out.println("Test - 1.1");
		List<String> snps;
		try {
			snps = sf.getAllNames();
			System.out.println(snps.size());
			System.out.println(snps.get(0).toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAllSNPIdByLocationFactory() {
		System.out.println("Test - 1.2");
		List<String> snps;
		try {
			snps = sf.getAllNamesByLocation("1",1,1000000);
			System.out.println(snps.size());
			System.out.println(snps.get(0).toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
	
	public void testAllSNPFactory() {
		System.out.println("Test - 2");
		FeatureList<SNP> snps;
		try {
			snps = sf.getAll();
			System.out.println(snps.size());
			System.out.println(snps.get(0).toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAllSNPByLocationFactory() {
		System.out.println("Test - 3");
		FeatureList<SNP> snps;
		try {
			snps = sf.getAllByLocation("1", 1, 1000000);
			System.out.println(snps.size());
			System.out.println(snps.get(0).toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSNPByIdFactory() {
		System.out.println("Test - 4");
		SNP snp;
		try {
			snp = sf.getByName("rs11644186");
			if(snp != null) {
				System.out.println(snp);
				System.out.println(snp.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSNPListByIdsFactory() {
		System.out.println("Test - 5");
		FeatureList<SNP> snpList;
		try {
			List<String> snps = StringUtils.stringToList(" rs7342690 rs11644186 rs7342797");
			snpList = sf.getSNPListByIds(snps);
			System.out.println(snpList.toString());
			System.out.println(snpList.size());
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} 
	}
	
	@Test
	public void testSNPListByConsequence() {
		System.out.println("Test - 6");
		FeatureList<SNP> snpList;
		try {
			snpList = sf.getAllByConsequenceType("SPLICE_SITE");
			System.out.println(snpList.toString());
			System.out.println(""+snpList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSNPListFilteredByConsequence() {
		System.out.println("Test - 7");
		FeatureList<SNP> snpList;
		try {
			snpList = sf.getAllFilteredByConsequenceType(StringUtils.stringToList("rs999980 rs7342690 rs9999097 rs11644186 rs7342797"), "NON_SYNONYMOUS_CODING");
			System.out.println(snpList.toString());
			System.out.println(""+snpList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSNPListByExternalId() {
		System.out.println("Test - 8");
		FeatureList<SNP> snpList;
		try {
			snpList = sf.getSNPListByExternalId("ENSG00000039068");
			System.out.println(""+snpList.size());
			System.out.println(snpList.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSNPListNamesByConsequence() {
		System.out.println("Test - 9");
		List<String> snpList;
		try {
			snpList = sf.getAllNamesByConsequenceType("NON_SYNONYMOUS_CODING");
			System.out.println(snpList.toString());
			System.out.println(""+snpList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testWriteAllFilteredByConsequenceType() {
		System.out.println("Test - 10");
		try {
			sf.writeAllFilteredByConsequenceType("DOWNSTREAM", "/tmp/downstream_test10.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

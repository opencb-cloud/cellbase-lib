package org.bioinfo.cellbase.lib.impl.hibernate;

import java.util.Arrays;
import java.util.List;

import org.bioinfo.cellbase.lib.api.RegulatoryRegionDBAdaptor;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.impl.DBAdaptorFactory;
import org.bioinfo.cellbase.lib.impl.hibernate.HibernateDBAdaptorFactory;
import org.bioinfo.infrared.core.cellbase.ConservedRegion;
import org.bioinfo.infrared.core.cellbase.RegulatoryRegion;
import org.junit.Test;

public class RegulatoryRegionHibernateDBAdaptorTest {

	private static DBAdaptorFactory dbAdaptorFactory = new HibernateDBAdaptorFactory();

	private RegulatoryRegionDBAdaptor getRegulatoryRegionDBAdaptor(){
		return dbAdaptorFactory.getRegulatoryRegionDBAdaptor("hsa");
	}
	
//	@Test
//	public void testRegulatoryRegionHibernateDBAdaptorSessionFactory() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRegulatoryRegionHibernateDBAdaptorSessionFactoryStringString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllByRegionString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllByRegionStringInt() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllByRegionStringIntInt() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllByRegionRegion() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllByRegionStringListOfString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllByRegionStringIntListOfString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllByRegionStringIntIntListOfString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllFeatureMapByRegionListOfRegion() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllFeatureMapByRegionStringIntInt() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllByInternalId() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllByInternalIdList() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllByRegionListListOfRegion() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllByRegionListListOfRegionListOfString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAll() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllIds() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllRegionsByIdList() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllSequencesByIdList() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetFullInfo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetFullInfoByIdList() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetInfo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetInfoByIdList() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetRegionById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetSequenceById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllByRegionRegionString() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetAllConservedRegionByRegion() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetAllConservedRegionByRegionList() {
		Region reg1 = new Region("5",100,2000);
		Region reg2 = new Region("X",500,8000);
		List<List<ConservedRegion>> list = getRegulatoryRegionDBAdaptor().getAllConservedRegionByRegionList(Arrays.asList(reg1,reg2));
		System.out.println(list.get(0).size());
		System.out.println(list.get(1).size());
	
	}

	
	@Test
	public void testGetAllRegulatoryRegionList2() {
		List<RegulatoryRegion> list = getRegulatoryRegionDBAdaptor().getAllByRegion2("5",1000, 200000, Arrays.asList("Histone", "Open Chromatin"));
		for(RegulatoryRegion rr: list) {
			System.out.println(rr.getRegulatoryRegionId());			
		}
	}
	
	@Test
	public void testGetAllRegulatoryRegionList() {
		List<RegulatoryRegion> list = getRegulatoryRegionDBAdaptor().getAllByRegion("5",1000, 200000, Arrays.asList("Histone", "Open Chromatin"));
		System.out.println(list.size());
	}

}

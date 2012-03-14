package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.Arrays;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.CpGIsland;
import org.bioinfo.infrared.lib.api.CpGIslandDBAdaptor;
import org.bioinfo.infrared.lib.common.Region;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.junit.Test;

public class CpGIslandHibernateDBAdaptorTest {

	private static DBAdaptorFactory dbAdaptorFactory = new HibernateDBAdaptorFactory();

	private CpGIslandDBAdaptor getCpGIslandDBAdaptor(){
		return dbAdaptorFactory.getCpGIslandDBAdaptor("hsa");
	}
	
	@Test
	public void testGetAllByRegion() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetAllByRegionList() {
		Region reg1 = new Region("5",1000,700000);
		Region reg2 = new Region("3",500,1800000);
		List<List<CpGIsland>> list = getCpGIslandDBAdaptor().getAllByRegionList(Arrays.asList(reg1,reg2));
		System.out.println(list.get(0).size());
		System.out.println(list.get(1).size());		
	}

}

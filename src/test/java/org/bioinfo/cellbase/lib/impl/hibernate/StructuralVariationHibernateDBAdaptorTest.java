package org.bioinfo.cellbase.lib.impl.hibernate;

import java.util.Arrays;
import java.util.List;

import org.bioinfo.cellbase.lib.api.StructuralVariationDBAdaptor;
import org.bioinfo.cellbase.lib.common.IntervalFeatureFrequency;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.impl.DBAdaptorFactory;
import org.bioinfo.cellbase.lib.impl.hibernate.HibernateDBAdaptorFactory;
import org.bioinfo.infrared.core.cellbase.StructuralVariation;
import org.junit.Test;

public class StructuralVariationHibernateDBAdaptorTest {

	private static DBAdaptorFactory dbAdaptorFactory = new HibernateDBAdaptorFactory();

	private StructuralVariationDBAdaptor getStructuralVariationDBAdaptor(){
		return dbAdaptorFactory.getStructuralVariationDBAdaptor("hsa");
	}
	
	@Test
	public void testGetAllByRegion() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetAllByRegionList() {
		Region reg1 = new Region("5",1000,7000000);
		Region reg2 = new Region("3",500,180000000);
		List<List<StructuralVariation>> list = getStructuralVariationDBAdaptor().getAllByRegionList(Arrays.asList(reg1,reg2));
		System.out.println(list.get(0).size());
		System.out.println(list.get(1).size());	
	}
	
	
	@Test
	public void testGetAllIntervalFrequencies() {
		Region region = new Region("3", 1000, 20000000);
		List<IntervalFeatureFrequency> a = getStructuralVariationDBAdaptor().getAllIntervalFrequencies(region, 100000);
//		printGeneList("testGeneHibernateDBAdaptorGetAll", genes, 5);
	}
}

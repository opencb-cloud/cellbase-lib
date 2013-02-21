package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.Arrays;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.MutationPhenotypeAnnotation;
import org.bioinfo.infrared.lib.api.MutationDBAdaptor;
import org.bioinfo.infrared.lib.common.IntervalFeatureFrequency;
import org.bioinfo.infrared.lib.common.Region;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.junit.Test;

public class MutationHibernateDBAdaptorTest {

	private static DBAdaptorFactory dbAdaptorFactory = new HibernateDBAdaptorFactory();

	private MutationDBAdaptor getMutationDBAdaptor(){
		return dbAdaptorFactory.getMutationDBAdaptor("hsa");
	}

	
//	@Test
//	public void testMutationHibernateDBAdaptor() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetAllByRegion() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetAllByRegionList() {
		Region reg1 = new Region("5",1000,700000);
		Region reg2 = new Region("3",500,1800000);
		List<List<MutationPhenotypeAnnotation>> list = getMutationDBAdaptor().getAllByRegionList(Arrays.asList(reg1,reg2));
		System.out.println(list.get(0).size());
		System.out.println(list.get(1).size());		
		
	}

	
	@Test
	public void testGetAllIntervalFrequencies() {
		Region region = new Region("3", 1000, 20000000);
		List<IntervalFeatureFrequency> a = getMutationDBAdaptor().getAllIntervalFrequencies(region, 100000);
//		printGeneList("testGeneHibernateDBAdaptorGetAll", genes, 5);
	}
}

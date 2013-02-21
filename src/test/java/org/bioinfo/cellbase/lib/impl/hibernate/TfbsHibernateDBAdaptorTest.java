package org.bioinfo.cellbase.lib.impl.hibernate;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.bioinfo.cellbase.lib.api.TfbsDBAdaptor;
import org.bioinfo.cellbase.lib.common.IntervalFeatureFrequency;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.impl.DBAdaptorFactory;
import org.bioinfo.cellbase.lib.impl.hibernate.HibernateDBAdaptorFactory;
import org.bioinfo.infrared.core.cellbase.Pwm;
import org.junit.Test;

public class TfbsHibernateDBAdaptorTest {

	private static DBAdaptorFactory dbAdaptorFact = new HibernateDBAdaptorFactory();

	private TfbsDBAdaptor tfbsDBAdaptor = dbAdaptorFact.getTfbsDBAdaptor("hsa");;
	
	@Test
	public void testGetAll() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetAllByTargetGeneName() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetAllPwmByTfGeneNameList() {
		List<List<Pwm>> pwmList = tfbsDBAdaptor.getAllPwmByTfGeneNameList(Arrays.asList("ENSG00000137203", "Ap2alpha"));
		if(pwmList != null) {
			System.out.println(pwmList.size());
			System.out.println(pwmList.get(0).size());
			System.out.println(pwmList.get(0).get(0).getFrequencies());
			System.out.println(pwmList.get(0).get(1).getFrequencies());
			System.out.println(pwmList.get(1).size());
		}else {
			System.out.println("nulazo!!!");
		}
//		fail("Not yet implemented");
	}

	@Test
	public void testGetAllByRegionString() {
//		fail("Not yet implemented");
	}

	
	@Test
	public void testGetAllTfIntervalFrequencies() {
		Region region = new Region("3", 1000, 20000000);
		List<IntervalFeatureFrequency> a = tfbsDBAdaptor.getAllTfIntervalFrequencies(region, 100000);
//		printGeneList("testGeneHibernateDBAdaptorGetAll", genes, 5);
	}
}

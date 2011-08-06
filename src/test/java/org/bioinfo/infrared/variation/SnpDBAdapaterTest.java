package org.bioinfo.infrared.variation;

import static org.junit.Assert.fail;

import java.util.List;

import org.bioinfo.infrared.lib.db.HibernateDBUtils;
import org.bioinfo.infrared.lib.impl.hibernate.SnpHibernateDBAdapator;
import org.junit.Test;

public class SnpDBAdapaterTest {

	private SnpHibernateDBAdapator snpHibernateDBAdapator;
	
	public SnpDBAdapaterTest() {
		snpHibernateDBAdapator = new SnpHibernateDBAdapator();
	}
	
	@Test
	public void testGetAllNames() {
		
		System.out.println(HibernateDBUtils.getStaticTest());
		
//		List<String> snpNames = snpHibernateDBAdapator.getAllNames();
//		System.out.println("SNP number: "+snpNames.size()+", ");
//		fail("Not yet implemented");
	}

	public void testGetAllNamesByRegion() {
		fail("Not yet implemented");
	}

}

package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.List;

import org.bioinfo.infrared.lib.api.CytobandDBAdaptor;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.junit.Test;

public class CytobandHibernateDBAdaptorTest {

	private static DBAdaptorFactory dbAdaptorFactory = new HibernateDBAdaptorFactory();
	
	
	
	@Test
	public void testGetAllChromosomeNames() {
		CytobandDBAdaptor cytobandDBAdaptor = dbAdaptorFactory.getCytobandDBAdaptor("hsa");
		List<String> names = cytobandDBAdaptor.getAllChromosomeNames();
		System.out.println("PAKO"+names.toString());
	}

}

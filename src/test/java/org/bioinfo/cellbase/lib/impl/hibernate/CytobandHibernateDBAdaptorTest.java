package org.bioinfo.cellbase.lib.impl.hibernate;

import java.util.List;

import org.bioinfo.cellbase.lib.api.CytobandDBAdaptor;
import org.bioinfo.cellbase.lib.impl.DBAdaptorFactory;
import org.bioinfo.cellbase.lib.impl.hibernate.HibernateDBAdaptorFactory;
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

package org.bioinfo.infrared.lib.impl.hibernate;

import static org.junit.Assert.*;

import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.junit.Test;

public class GenomeSequenceDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new HibernateDBAdaptorFactory();

	private GenomeSequenceHibernateDBAdaptor genomeSequenceHibernateDBAdaptor;

	/*@Test
	public void testGetByRegion() {
		genomeSequenceHibernateDBAdaptor = dbAdaptorFact.getGenomeSequenceDBAdaptor("drerio");
		System.out.println(genomeSequenceHibernateDBAdaptor.getByRegion("1", 1000001, 1000501));
		dbAdaptorFact.close();
	}*/

}

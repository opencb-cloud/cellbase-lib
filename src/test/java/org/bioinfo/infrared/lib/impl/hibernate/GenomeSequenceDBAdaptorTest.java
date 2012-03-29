package org.bioinfo.infrared.lib.impl.hibernate;

import org.bioinfo.infrared.lib.api.GenomeSequenceDBAdaptor;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.junit.Test;

public class GenomeSequenceDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new HibernateDBAdaptorFactory();

	private GenomeSequenceDBAdaptor genomeSequenceHibernateDBAdaptor;

	@Test
	public void testGetByRegion() {
		genomeSequenceHibernateDBAdaptor = dbAdaptorFact.getGenomeSequenceDBAdaptor("hsa");
		System.out.println(genomeSequenceHibernateDBAdaptor.getByRegion("1", 1000001, 1000501));
		System.out.println(genomeSequenceHibernateDBAdaptor.getByRegion("1", 1000001, 1000501).getSequence());
		dbAdaptorFact.close();
	}

}

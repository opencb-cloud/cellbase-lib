package org.bioinfo.infrared.lib.impl.hibernate;

import static org.junit.Assert.*;

import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.junit.Test;

public class GenomeSequenceDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new HibernateDBAdaptorFactory();

	private GenomeSequenceDBAdaptor genomeSequenceDBAdaptor;

	/*@Test
	public void testGetByRegion() {
		genomeSequenceDBAdaptor = dbAdaptorFact.getGenomeSequenceDBAdaptor("drerio");
		System.out.println(genomeSequenceDBAdaptor.getByRegion("1", 1000001, 1000501));
		dbAdaptorFact.close();
	}*/

}

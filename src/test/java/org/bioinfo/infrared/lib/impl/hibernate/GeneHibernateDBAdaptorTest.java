package org.bioinfo.infrared.lib.impl.hibernate;

import static org.junit.Assert.*;

import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.junit.Test;

public class GeneHibernateDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new HibernateDBAdaptorFactory();
	
	private GeneDBAdaptor geneDBAdaptor;
	
	@Test
	public void testGeneHibernateDBAdaptorString() {
		geneDBAdaptor = dbAdaptorFact.getGeneDBAdaptor("hsapiens");
		System.out.println(geneDBAdaptor.getGeneById("ENSG00000252775").get(0).getStart());
//		fail("Not yet implemented");
	}

	@Test
	public void testGetGeneById() {
		geneDBAdaptor = dbAdaptorFact.getGeneDBAdaptor("hsapiens", "v2");
		System.out.println(geneDBAdaptor.getGeneById("ENSG00000252775").get(0).getEnd());
		
		dbAdaptorFact.close();
	}

	public void testGetGeneByIdList() {
		fail("Not yet implemented");
	}

	public void testGetGeneByRegionStringIntInt() {
		fail("Not yet implemented");
	}

	public void testGetGeneByRegionString() {
		fail("Not yet implemented");
	}

	public void testGetGeneByTranscript() {
		fail("Not yet implemented");
	}

	public void testGetGeneByTranscriptList() {
		fail("Not yet implemented");
	}

	public void testGetSequence() {
		fail("Not yet implemented");
	}

	public void testGetSequenceString() {
		fail("Not yet implemented");
	}

	public void testGetRegion() {
		fail("Not yet implemented");
	}

}

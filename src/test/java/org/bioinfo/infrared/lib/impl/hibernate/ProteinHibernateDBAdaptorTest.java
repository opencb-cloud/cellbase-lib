package org.bioinfo.infrared.lib.impl.hibernate;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.ProteinFeature;
import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.api.ProteinDBAdaptor;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProteinHibernateDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new HibernateDBAdaptorFactory();
	private ProteinDBAdaptor proteinDBAdaptor;
	private long startExecTime;
	private String species = "hsa";
	
	@Before
	public void beforeTestStart() {
		proteinDBAdaptor = dbAdaptorFact.getProteinDBAdaptor(species);
		startExecTime = System.currentTimeMillis();
	}

	@After
	public void afterTestStart() {
//		dbAdaptorFact.close();
	}
	
	
	@Test
	public void testProteinHibernateDBAdaptorSessionFactory() {
		//fail("Not yet implemented");
	}

	@Test
	public void testProteinHibernateDBAdaptorSessionFactoryStringString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllIds() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetInfo() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetInfoByIdList() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetFullInfo() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetFullInfoByIdList() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetRegionById() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllRegionsByIdList() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetSequenceById() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllSequencesByIdList() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAll() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllUniprotIds() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllByUniprotId() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllByUniprotIdList() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllByProteinName() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllByProteinNameList() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllByEnsemblGene() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllByEnsemblGeneList() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllByEnsemblTranscriptId() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllByEnsemblTranscriptIdList() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllByGeneName() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllByGeneNameList() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllProteinFeaturesByUniprotId() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllProteinFeaturesByUniprotIdList() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllProteinFeaturesByGeneName() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllProteinFeaturesByGeneNameList() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllProteinFeaturesByProteinXref() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllProteinFeaturesByProteinXrefList() {
		List<List<ProteinFeature>> proteinFeatureList = proteinDBAdaptor.getAllProteinFeaturesByProteinXrefList(Arrays.asList("Ap2alpha","SP1"));
		if(proteinFeatureList != null) {
			System.out.println(proteinFeatureList.size());
			System.out.println(proteinFeatureList.get(0).size());
			System.out.println(proteinFeatureList.get(1).size());
		}else {
			System.out.println("nulazo!!!");
		}
	}

	@Test
	public void testGetAllProteinXrefsByProteinNameString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllProteinXrefsByProteinNameListListOfString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllProteinXrefsByProteinNameStringString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllProteinXrefsByProteinNameListListOfStringString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllProteinXrefsByProteinNameStringListOfString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllProteinXrefsByProteinNameListListOfStringListOfString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllProteinSequenceByProteinName() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllProteinSequenceByProteinNameList() {
		//fail("Not yet implemented");
	}

}

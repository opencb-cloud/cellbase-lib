package org.bioinfo.infrared.variation.dbsql;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bioinfo.commons.io.utils.IOUtils;
import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.core.feature.Position;
import org.bioinfo.infrared.core.variation.TranscriptConsequenceType;
import org.bioinfo.infrared.variation.TranscriptConsequenceTypeDBManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VariationPositionDBManagerTest {

	DBConnector infDBConnector;
	TranscriptConsequenceTypeDBManager vf;
	
	@Before
	public void setUp() throws Exception {
		infDBConnector = new DBConnector();
		vf = new TranscriptConsequenceTypeDBManager(infDBConnector);
		System.out.println("VariationPositionDBManagerTest");
		System.out.println("===============================================");
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testGetConsequenceType() {
		try {
			
			List<String> lines = IOUtils.readLines(new File("/mnt/commons/test/formats/features/positions_consequence_type.txt"));
			List<Position> positions = new ArrayList<Position>();
			String[] fields;
			for(String line: lines) {
				fields = line.split("\t");
				positions.add(new Position(fields[0], Integer.parseInt(fields[1])));
			}
			long t1 = System.currentTimeMillis();
			List<List<TranscriptConsequenceType>> tct = vf.getConsequenceType(positions);
			System.out.println(tct.size());
			
			for(int i=0; i<tct.size(); i++) {
				System.out.println(positions.get(i)+" ==> "+tct.get(i).toString());	
			}
			System.out.println(System.currentTimeMillis()-t1);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		} 
	}

}

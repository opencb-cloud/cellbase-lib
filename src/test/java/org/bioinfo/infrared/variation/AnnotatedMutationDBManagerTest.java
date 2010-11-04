package org.bioinfo.infrared.variation;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.feature.Position;
import org.bioinfo.infrared.core.variation.AnnotatedMutation;
import org.junit.Test;

public class AnnotatedMutationDBManagerTest {

	DBConnector infraDBConnector = new DBConnector();
	AnnotatedMutationDBManager annotMutationDBManager = new AnnotatedMutationDBManager(infraDBConnector);
	
	@Test
	public void testGetAllByPositionPosition() {
		System.out.println("Test 1\n");
		try {
			FeatureList<AnnotatedMutation> annotSnps = annotMutationDBManager.getAllByPosition(new Position("11", 17352480));
			System.out.println("Annotated Snps: "+annotSnps.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testGetAllByPositionStringInt() {
		System.out.println("Test 2\n");
		try {
			FeatureList<AnnotatedMutation> annotSnps = annotMutationDBManager.getAllByPosition("11", 17352479);
			System.out.println("Annotated Snps: "+annotSnps.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testGetAllByPositions() {
		System.out.println("Test 3\n");
		try {
			List<Position> positions = new ArrayList<Position>();
			positions.add(new Position("11", 17352480));positions.add(new Position("4", 71068629));
			positions.add(new Position("19", 9325358));positions.add(new Position("8", 9325279));
			List<FeatureList<AnnotatedMutation>> annotSnps = annotMutationDBManager.getAllByPositions(positions);
			System.out.println("Annotated Snps: "+annotSnps.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testGetAllAnnotatedMutations() {
		System.out.println("\nTest 4\n");
		try {
			FeatureList<AnnotatedMutation> annotSnps = annotMutationDBManager.getAllAnnotatedMutations();
			System.out.println("Annotated Snps size: "+annotSnps.size());
			System.out.println("Annotated Snps size: "+annotSnps.get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}

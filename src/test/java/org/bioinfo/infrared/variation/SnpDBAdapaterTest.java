package org.bioinfo.infrared.variation;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

public class SnpDBAdapaterTest {

	private SnpDBAdapater snpDBAdapater;
	
	public SnpDBAdapaterTest() {
		snpDBAdapater = new SnpDBAdapater();
	}
	
	@Test
	public void testGetAllNames() {
		List<String> snpNames = snpDBAdapater.getAllNames();
		System.out.println("SNP number: "+snpNames.size()+", ");
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllNamesByRegion() {
		fail("Not yet implemented");
	}

}

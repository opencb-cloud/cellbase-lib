package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.lib.api.GenomicVariantEffectDBAdaptor;
import org.bioinfo.infrared.lib.common.GenomicVariant;
import org.bioinfo.infrared.lib.common.GenomicVariantConsequenceType;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.junit.Test;

public class GenomicVariantEffectDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new HibernateDBAdaptorFactory();
	private GenomicVariantEffectDBAdaptor genomicVariantEffectDBAdaptor;
	
	private String species = "hsapiens";
	
	@Test
	public void testGetAllConsequenceTypeByVariant() {
		genomicVariantEffectDBAdaptor = dbAdaptorFact.getGenomicVariantEffectDBAdaptor(species);
		List<GenomicVariantConsequenceType> genomicVariantConsequenceTypeList = genomicVariantEffectDBAdaptor.getAllConsequenceTypeByVariant(new GenomicVariant("X", 99884985, "T"));

		System.out.println("genomicVariantConsequenceTypeList size: "+genomicVariantConsequenceTypeList);
		
	}

	
	@Test
	public void testGetAllConsequenceTypeByVariantList() {
		genomicVariantEffectDBAdaptor = dbAdaptorFact.getGenomicVariantEffectDBAdaptor(species);
		
		List<GenomicVariant> variants = new ArrayList<GenomicVariant>(1000);
		for(int i=0; i<100; i++) {
			variants.add(new GenomicVariant("X", 99884985+i, "T"));
		}
		
		long t1 = System.currentTimeMillis();
		List<GenomicVariantConsequenceType> genomicVariantConsequenceTypeList = genomicVariantEffectDBAdaptor.getAllConsequenceTypeByVariantList(variants);
		System.out.println("genomicVariantConsequenceTypeList time: "+ (System.currentTimeMillis()-t1) + " ms");
		System.out.println("size: "+genomicVariantConsequenceTypeList.size());
		
	}

	
}

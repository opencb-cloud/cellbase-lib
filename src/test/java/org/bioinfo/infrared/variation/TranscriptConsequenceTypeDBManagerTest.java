package org.bioinfo.infrared.variation;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bioinfo.commons.utils.ListUtils;
import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.feature.Position;
import org.bioinfo.infrared.core.variation.SNP;
import org.bioinfo.infrared.core.variation.TranscriptConsequenceType;
import org.junit.Test;

public class TranscriptConsequenceTypeDBManagerTest {

	DBConnector ic;
	SNPDBManager sf;
	TranscriptConsequenceTypeDBManager tc;
	
	@Test
	public void testGetConsequenceTypesByPosition() {
		ic = new DBConnector();
		sf = new SNPDBManager(ic);
		tc = new TranscriptConsequenceTypeDBManager(ic);
		List<TranscriptConsequenceType> transConsqTypes;
		
		try {
			transConsqTypes = tc.getConsequenceType(new Position("13", 32889711));
			System.out.println(transConsqTypes.toString());
		}catch(Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	
	@Test
	public void testGetConsequenceTypes() {
		ic = new DBConnector();
		sf = new SNPDBManager(ic);
		tc = new TranscriptConsequenceTypeDBManager(ic);
		List<TranscriptConsequenceType> transConsqTypes;
		try {
			FeatureList<SNP> snps = sf.getAllByRegion("12", 10000, 20000);
			
			List<String> altAlleles = new ArrayList<String>(snps.size());
			for(SNP snp: snps) {
				if(snp.getAllele().indexOf("/") != -1) {
					altAlleles.add(snp.getAllele().split("/")[1]);
				}else {
					altAlleles.add(snp.getAllele());
				}
			}
//			System.out.println(altAlleles);
			
			for(SNP snp: snps) {
				transConsqTypes = tc.getConsequenceType(new Position(snp.getChromosome(), snp.getStart()));
//				System.out.println("SNP: " + snp.getTranscriptConsequenceTypes());
//				System.out.println("Calculated: " + transConsqTypes.toString());
				
				List<String> snpConsqType = ListUtils.toStringList(snp.getTranscriptConsequenceTypes());
				List<String> calculatedConsqType = ListUtils.toStringList(transConsqTypes);
				
				Collections.sort(snpConsqType);
				Collections.sort(calculatedConsqType);
				
				System.out.println("SNP: " + snp.toString());
				System.out.println("SNP consequence type:   " + snpConsqType);
				System.out.println("Calc. consequence type: " + calculatedConsqType);
				System.out.println("===================\n");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

}

package org.bioinfo.infrared.variation;

import static org.junit.Assert.fail;

import java.util.List;

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
	public void testGetConsequenceTypes() {
		ic = new DBConnector();
		sf = new SNPDBManager(ic);
		tc = new TranscriptConsequenceTypeDBManager(ic);
		List<List<TranscriptConsequenceType>> transConsqTypes;
		try {
			FeatureList<SNP> snps = sf.getAllByRegion("1", 1, 25000);
			for(SNP snp: snps) {
				transConsqTypes = tc.getConsequenceType(new Position(snp.getChromosome(), snp.getStart()));
				System.out.println(snp.getTranscriptConsequenceTypes());
				System.out.println(transConsqTypes.get(0).toString());
				System.out.println("===================");
			}
			
		}catch(Exception e) {
			fail(e.toString());
		}
	}

}

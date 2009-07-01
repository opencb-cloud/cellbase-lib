package org.bioinfo.infrared.variation;

import org.bioinfo.infrared.common.feature.FeatureList;

public class VariationUtils {

	public static FeatureList<SNP> getSnpsByConsequenceType(String consequenceType, FeatureList<SNP> snps) {
		FeatureList<SNP> selectedSnps = new FeatureList<SNP>(10);
		if(snps != null && snps.size()>0) {
			for(SNP snp: snps) {
				if(snp.getConsequence_type().contains(consequenceType)) {
					selectedSnps.add(snp);
				}
			}
		}
		return selectedSnps;
	}
	
//	public static FeatureList<SNP> removeSnps(FeatureList<SNP> snps) {
//		
//	}
	
}

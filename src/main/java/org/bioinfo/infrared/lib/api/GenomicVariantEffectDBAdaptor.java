package org.bioinfo.infrared.lib.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bioinfo.infrared.lib.common.GenomicVariant;
import org.bioinfo.infrared.lib.common.GenomicVariantConsequenceType;


public interface GenomicVariantEffectDBAdaptor {

	
	public List<GenomicVariantConsequenceType> getAllConsequenceTypeByVariant(GenomicVariant variant);
	
	public List<GenomicVariantConsequenceType> getAllConsequenceTypeByVariant(GenomicVariant variant, Set<String> excludeSet);

	
	public List<GenomicVariantConsequenceType> getAllConsequenceTypeByVariantList(List<GenomicVariant> variants);

	public List<GenomicVariantConsequenceType> getAllConsequenceTypeByVariantList(List<GenomicVariant> variants, Set<String> excludeSet);

	
	public Map<GenomicVariant, List<GenomicVariantConsequenceType>> getConsequenceTypeMap(List<GenomicVariant> variants);

	public Map<GenomicVariant, List<GenomicVariantConsequenceType>> getConsequenceTypeMap(List<GenomicVariant> variants, Set<String> excludeSet);

}

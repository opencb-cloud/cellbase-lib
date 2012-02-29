package org.bioinfo.infrared.lib.api;

import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.lib.common.GenomicVariant;
import org.bioinfo.infrared.lib.common.GenomicVariantConsequenceType;


public interface GenomicVariantEffectDBAdaptor {

	List<GenomicVariantConsequenceType> getAllConsequenceTypeByVariantList(List<GenomicVariant> variants);

	Map<GenomicVariant, List<GenomicVariantConsequenceType>> getConsequenceTypeMap(List<GenomicVariant> variants);

	List<GenomicVariantConsequenceType> getAllConsequenceTypeByVariant(GenomicVariant variant);

}

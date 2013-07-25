package org.bioinfo.cellbase.lib.api.variation;

import java.util.List;

import org.bioinfo.cellbase.lib.common.GenomicVariant;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryOptions;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResponse;


public interface GenomicVariantEffectDBAdaptor {

	
	public QueryResponse getAllConsequenceTypeByVariant(GenomicVariant variant, QueryOptions options);
	
	public QueryResponse getAllConsequenceTypeByVariantList(List<GenomicVariant> variants, QueryOptions options);
	
	
//	public List<GenomicVariantConsequenceType> getAllConsequenceTypeByVariant(GenomicVariant variant);
//	
//	public List<GenomicVariantConsequenceType> getAllConsequenceTypeByVariant(GenomicVariant variant, Set<String> excludeSet);
//
//	
//	public List<GenomicVariantConsequenceType> getAllConsequenceTypeByVariantList(List<GenomicVariant> variants);
//
//	public List<GenomicVariantConsequenceType> getAllConsequenceTypeByVariantList(List<GenomicVariant> variants, Set<String> excludeSet);

	
//	public Map<GenomicVariant, List<GenomicVariantConsequenceType>> getConsequenceTypeMap(List<GenomicVariant> variants);
//
//	public Map<GenomicVariant, List<GenomicVariantConsequenceType>> getConsequenceTypeMap(List<GenomicVariant> variants, Set<String> excludeSet);

}

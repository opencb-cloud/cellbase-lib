package org.bioinfo.cellbase.lib.api;

import java.util.List;

import org.bioinfo.cellbase.lib.common.Position;
import org.bioinfo.cellbase.lib.impl.hibernate.GenomicRegionFeatures;

public interface GenomicPositionDBAdaptor {

	
	public GenomicRegionFeatures getByPosition(String chromosome, int position);
	
	public GenomicRegionFeatures getByPosition(Position position);
	
	public List<GenomicRegionFeatures> getAllByPositionList(List<Position> positionList);

	
	public GenomicRegionFeatures getByPosition(Position position, List<String> sources);	// sources: gene, exon, snp, ...
	
	public List<GenomicRegionFeatures> getAllByPositionList(List<Position> positions, List<String> sources);
	

	public GenomicRegionFeatures getConsequenceTypeByPosition(String chromosome, int position);
	
	public GenomicRegionFeatures getConsequenceTypeByPosition(Position position);
	
	public List<GenomicRegionFeatures> getAllConsequenceTypeByPositionList(List<Position> positionList);
	
}

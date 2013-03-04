package org.bioinfo.cellbase.lib.api;

import java.util.List;

import org.bioinfo.cellbase.lib.common.Position;



public interface GenomicPositionDBAdaptor {

	
	public String getByPosition(String chromosome, int position);
	
	public String getByPosition(Position position);
	
	public List<String> getAllByPositionList(List<Position> positionList);

	
	public String getByPosition(Position position, List<String> sources);	// sources: gene, exon, snp, ...
	
	public List<String> getAllByPositionList(List<Position> positions, List<String> sources);
	

	public String getConsequenceTypeByPosition(String chromosome, int position);
	
	public String getConsequenceTypeByPosition(Position position);
	
	public List<String> getAllConsequenceTypeByPositionList(List<Position> positionList);
	
}

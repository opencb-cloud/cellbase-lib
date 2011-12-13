package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;

public interface SnpDBAdaptor extends FeatureDBAdaptor {

	
	@Override
	public List<Snp> getAll();

	
	public List<Snp> getByDbSnpId(String dbSnpId);

	public List<List<Snp>> getByDbSnpIdList(List<String> dbSnpIdList);

	public List<Snp> getAllByGeneId(String externalId);
	
	public List<List<Snp>> getAllByGeneIdList(List<String> externalIds);
	
	public List<Snp> getAllByEnsemblGeneId(String ensemblGeneId);
	
	public List<List<Snp>> getAllByEnsemblGeneIdList(List<String> ensemblGeneIds);
	
	public List<Snp> getAllByEnsemblTranscriptId(String ensemblTranscriptId);
	
	public List<List<Snp>> getAllByEnsemblTranscriptIdList(List<String> ensemblTranscriptIds);
	
	public List<String> getAllConsequenceTypes();

	public List<String> getAllIdsByConsequenceType(String consequenceType);
	
	public List<List<String>> getAllIdsByConsequenceTypeList(List<String> consequenceTypeList);
	
	public List<String> getAllIdsByRegion(String chromosome, int start, int end);

	
	public List<Snp> getAllByConsequenceType(String consequenceType);
	
	public List<Snp> getAllByConsequenceTypeList(List<String> consequenceTypeList);
	
	
	public List<Snp> getAllByPosition(String chromosome, int position);
	
	public List<Snp> getAllByPosition(Position position);
	
	public List<List<Snp>> getAllByPositionList(List<Position> positionList);
	
	
	public List<Snp> getAllByRegion(String chromosome);
	
	public List<Snp> getAllByRegion(String chromosome, int start);
	
	public List<Snp> getAllByRegion(String chromosome, int start, int end);
	
	public List<Snp> getAllByRegion(String chromosome, int start, int end, List<String> consequenceTypeList);
	
	public List<Snp> getAllByRegion(Region region);
	
	public List<Snp> getAllByRegion(Region region, List<String> consequenceTypeList);
	
	public List<List<Snp>> getAllByRegionList(List<Region> regionList);
	
	public List<List<Snp>> getAllByRegionList(List<Region> regionList, List<String> consequenceTypeList);
	
	public List<Snp> getAllByCytoband(String chromosome, String cytoband);
	
	
	public List<Snp> getAllFilteredByConsequenceType(List<String> snpIds, String consequence);

	public List<Snp> getAllFilteredByConsequenceType(List<String> snpIds, List<String> consequenceTypes);

	public void writeAllFilteredByConsequenceType(String consequence, String outfile);
	
}

package org.bioinfo.infrared.lib.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.bioinfo.infrared.core.Snp;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;

public interface SnpDBAdaptor {

	public List<String> getAllNamesByRegion(String chromosome, int start, int end) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;

	public List<String> getAllNamesByConsequenceType(String consequenceType) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<String> getAllNamesByConsequenceTypes(List<String> consequenceTypes) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<String> getAllConsequenceTypes() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<String> getAllNamesByLocation(String chromosome, int start, int end) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<Snp> getAll() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public Snp getByName(String name) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<Snp> getByNames(List<String> names) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<Snp> getSnpListByIds(List<String> snpIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<Snp> getAllByPosition(String chromosome, int position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<Snp> getAllByPosition(Position position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<List<Snp>> getAllByPositions(List<Position> positions) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<Snp> getAllByRegion(String chromosome) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<Snp> getAllByRegion(String chromosome, int start) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<Snp> getAllByRegion(String chromosome, int start, int end) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<Snp> getAllByRegion(Region region) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<List<Snp>> getAllByRegions(List<Region> regions) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<Snp> getAllByConsequenceType(String consequence) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<Snp> getAllFilteredByConsequenceType(List<String> snpIds, String consequence) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<Snp> getAllFilteredByConsequenceType(List<String> snpIds, List<String> consequenceTypes) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public void writeAllFilteredByConsequenceType(String consequence, String outfile) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException, IOException;
	
	public List<Snp> getByExternalId(String externalId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
	
	public List<List<Snp>> getByExternalId(List<String> externalIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;

}

package org.bioinfo.infrared.regulatory.dbsql;

import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.regulatory.ConservedRegion;

public class ConservedRegionDBManager extends DBManager {

	public static final String GET_ALL_BY = "select * from conserved_region where ";
	public static final String GET_ALL_BY_SNP = "select cr.* from snp s, snp2conserved_region s2cr, conserved_region cr where s.name = ? and s.snp_id=s2cr.snp_id and s2cr.conserved_region_id=cr.conserved_region_id";
	
	public ConservedRegionDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	public void writeAll(String outfile) {
		
	}

	@SuppressWarnings("unchecked")
	public FeatureList<ConservedRegion> getAllBySnpId(String snpId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById(GET_ALL_BY_SNP, snpId, new BeanArrayListHandler(ConservedRegion.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<ConservedRegion>> getAllBySnpIds(List<String> snpIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getListOfFeatureListByIds(GET_ALL_BY_SNP, snpIds, new BeanArrayListHandler(ConservedRegion.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<ConservedRegion> getAllByLocation(String chromosome, int position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList(GET_ALL_BY+" chromosome = '"+chromosome+"' and start <= "+position +" and end >= " + position, new BeanArrayListHandler(ConservedRegion.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<ConservedRegion> getAllByRegion(String chromosome, int start, int end) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList(GET_ALL_BY+" chromosome = '"+chromosome+"' and start <= "+ end+" and end >= " + start, new BeanArrayListHandler(ConservedRegion.class));
	}
	
	public void writeAllWithSnps(String outfile) {
		
	}
}

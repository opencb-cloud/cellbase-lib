package org.bioinfo.infrared.variation.dbsql;

import java.sql.SQLException;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.variation.AlleleFrequency;

public class AlleleFrequencyDBManager extends DBManager {

	private static final String SELECT_FIELDS = "s.name, s.chromosome, s.start, s.end, s.strand, s.sequence, s.allele_string, af.population, af.ref_allele, af.ref_allele_freq, af.ref_allele_count, af.other_allele, af.other_allele_freq, af.other_allele_count, af.total_allele_count";
	
	public AlleleFrequencyDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<AlleleFrequency> getAllByMAF(double min, double max) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		if(min > max) {
			double swap = max;
			max = min;
			min = swap;
		}
		return getFeatureList("SELECT "+SELECT_FIELDS+" FROM allele_frequency af, snp s where af.other_allele_freq>="+min+" and af.other_allele_freq<="+max+" and af.snp_id=s.snp_id", new BeanArrayListHandler(AlleleFrequency.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<AlleleFrequency> getAllByMAF(double min, double max, String population) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		if(min > max) {
			double swap = max;
			max = min;
			min = swap;
		}
		return getFeatureList("SELECT "+SELECT_FIELDS+" FROM allele_frequency af, snp s where af.other_allele_freq>="+min+" and af.other_allele_freq<="+max+" and af.population='"+population+"' and af.snp_id=s.snp_id", new BeanArrayListHandler(AlleleFrequency.class));
	}
	
}

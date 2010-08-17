package org.bioinfo.infrared.variation.dbsql;

import java.sql.SQLException;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.variation.GenotypeFrequency;

public class GenotypeFrequencyDBManager extends DBManager {

private static final String SELECT_FIELDS = "s.name, s.chromosome, s.start, s.end, s.strand, s.sequence, s.allele_string, gf.population, gf.ref_allele, gf.ref_allele_freq, gf.ref_allele_count, gf.other_allele, gf.other_allele_freq, gf.other_allele_count, gf.total_allele_count";
	
	public GenotypeFrequencyDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<GenotypeFrequency> getAllByMAF(double min, double max) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		if(min > max) {
			double swap = max;
			max = min;
			min = swap;
		}
		return getFeatureList("SELECT "+SELECT_FIELDS+" FROM genotype_frequency af, snp s where gf.other_allele_freq>="+min+" and gf.other_allele_freq<="+max+" and gf.snp_id=s.snp_id", new BeanArrayListHandler(GenotypeFrequency.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<GenotypeFrequency> getAllByMAF(double min, double max, String population) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		if(min > max) {
			double swap = max;
			max = min;
			min = swap;
		}
		return getFeatureList("SELECT "+SELECT_FIELDS+" FROM genotype_frequency af, snp s where gf.other_allele_freq>="+min+" and gf.other_allele_freq<="+max+" and gf.population='"+population+"' and gf.snp_id=s.snp_id", new BeanArrayListHandler(GenotypeFrequency.class));
	}
	
}

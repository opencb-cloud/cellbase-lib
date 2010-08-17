package org.bioinfo.infrared.variation.dbsql;

import java.sql.SQLException;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.variation.SnpEffect;

public class SnpEffectDBManager extends DBManager {

	private static final String SELECT_FIELDS = "s.name, s.chromosome, s.start, s.end, s.strand, s.sequence, s.allele_string, se.protein_stable_id, se.gene_stable_id, se.tango_diff, se.foldx_diff, se.amySTVIIE_diff, se.amygeneral_diff, se.phd_acc_diff, se.phd_sec_diff, se.phobius_diff, se.phospho_diff, se.glyc_diff, se.myristoyl_diff, se.gpi_anchor_diff, se.pts1_diff, se.farnesyl_diff, se.typeI_geranyl_diff, se.typeII_geranyl_diff, se.turnover_diff, se.psort_diff";
	
	public SnpEffectDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<SnpEffect> getAll() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("SELECT "+SELECT_FIELDS+", se.* FROM snp_effect se, snp s WHERE se.snp_name=s.name", new BeanArrayListHandler(SnpEffect.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<SnpEffect> getAllByType(String type) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		if(type.equalsIgnoreCase("protein-structure")) {
			return getFeatureList("SELECT "+SELECT_FIELDS+", se.* FROM snp_effect se, snp s WHERE se.snp_name=s.name", new BeanArrayListHandler(SnpEffect.class));
		}else {
			if(type.equalsIgnoreCase("functional-site")) {
				return getFeatureList("SELECT "+SELECT_FIELDS+", se.* FROM snp_effect se, snp s WHERE se.snp_name=s.name", new BeanArrayListHandler(SnpEffect.class));
			}else {
				return getFeatureList("SELECT "+SELECT_FIELDS+", se.* FROM snp_effect se, snp s WHERE se.snp_name=s.name", new BeanArrayListHandler(SnpEffect.class));
			}
		}
	}
	
}

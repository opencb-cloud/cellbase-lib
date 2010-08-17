package org.bioinfo.infrared.variation.dbsql;

import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.variation.AnnotatedSnps;

public class AnnotatedSnpsDBManager extends DBManager{

	public AnnotatedSnpsDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<AnnotatedSnps> getAllById(String id) throws Exception{
		return getFeatureListById("select a.snp_id, s.chromosome, s.start, s.end, s.strand, s.sequence, s.allele_string, s.consequence_type, a.source, a.gene_id, a.gene_name, a.link, a.description from annotated_snps a, snp s where a.snp_id= ? and a.snp_id=s.name", id, new BeanArrayListHandler(AnnotatedSnps.class));
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<FeatureList<AnnotatedSnps>> getAllByIds(List<String> ids) throws Exception{
		return getListOfFeatureListByIds("select a.snp_id, s.chromosome, s.start, s.end, s.strand, s.sequence, s.allele_string, s.consequence_type, a.source, a.gene_id, a.gene_name, a.link, a.description from annotated_snps a, snp s where a.snp_id= ? and a.snp_id=s.name", ids, new BeanArrayListHandler(AnnotatedSnps.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<AnnotatedSnps> getAllAnnotatedSnps() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select a.snp_id, s.chromosome, s.start, s.end, s.strand, s.sequence, s.allele_string, s.consequence_type, a.source, a.gene_id, a.gene_name, a.link, a.description from annotated_snps a, snp s where a.snp_id=s.name", new BeanArrayListHandler(AnnotatedSnps.class));
	}
	
	
}

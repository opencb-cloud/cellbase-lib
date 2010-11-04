package org.bioinfo.infrared.variation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bioinfo.db.api.PreparedQuery;
import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.common.DBManager;
import org.bioinfo.infrared.core.common.Feature;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.variation.AnnotatedSnp;

public class AnnotatedSnpDBManager extends DBManager {

	public AnnotatedSnpDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<AnnotatedSnp> getAllById(String id) throws Exception{
//		return getFeatureListById("select a.annotated_snp_id, s.chromosome, s.start, s.end, s.strand, s.sequence, s.allele_string, s.consequence_type, a.source, a.gene_name, a.gene_name, a.link, a.description from annotated_snp a, snp s where a.snp_name= ? and a.snp_name=s.name", id, new BeanArrayListHandler(AnnotatedSnp.class));
		return getFeatureListById("select * from annotated_snp a where a.snp_name= ? ", id, new BeanArrayListHandler(AnnotatedSnp.class));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<FeatureList<AnnotatedSnp>> getAllByIds(List<String> ids) throws Exception{
////		return getListOfFeatureListByIds("select a.annotated_snp_id, s.chromosome, s.start, s.end, s.strand, s.sequence, s.allele_string, s.consequence_type, a.source, a.gene_name, a.gene_name, a.link, a.description from annotated_snp a, snp s where a.snp_name= ? and a.snp_name=s.name", ids, new BeanArrayListHandler(AnnotatedSnp.class));
//		return getListOfFeatureListByIds("select * from annotated_snp a where a.snp_name= ? ", ids, new BeanArrayListHandler(AnnotatedSnp.class));
		
		if(ids != null) {
			List<FeatureList<AnnotatedSnp>> snpList = new ArrayList<FeatureList<AnnotatedSnp>>(ids.size());
			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery("select * from annotated_snp a where a.snp_name= ? ");
			FeatureList<AnnotatedSnp> snpFeatureList;
			for(String id: ids) {
				if(id != null) {
					prepQuery.setParams(id);
					snpFeatureList = new FeatureList((List<Feature>)prepQuery.execute(new BeanArrayListHandler(AnnotatedSnp.class)));
//					snpFeatureList = (FeatureList<AnnotatedMutation>)prepQuery.execute(new BeanArrayListHandler(AnnotatedMutation.class));
					if(snpFeatureList != null && snpFeatureList.size() > 0) {
						snpList.add(snpFeatureList);
					}
				}else {
					snpList.add(null);
				}
			}
			prepQuery.close();
			return snpList;
		}else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<AnnotatedSnp> getAllAnnotatedSnps() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getFeatureList("select a.annotated_snp_id, s.chromosome, s.start, s.end, s.strand, s.sequence, s.allele_string, s.consequence_type, a.source, a.gene_id, a.gene_name, a.link, a.description from annotated_snp a, snp s where a.snp_name=s.name", new BeanArrayListHandler(AnnotatedSnp.class));
		return getFeatureList("select * from annotated_snp", new BeanArrayListHandler(AnnotatedSnp.class));
	}
	
	
}

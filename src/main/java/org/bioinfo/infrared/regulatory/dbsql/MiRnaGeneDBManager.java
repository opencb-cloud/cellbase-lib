package org.bioinfo.infrared.regulatory.dbsql;

import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.regulatory.MiRnaGene;

public class MiRnaGeneDBManager extends DBManager {

	public final static String GET_BY_LOCATION = "select mg.* from transcript t, transcript2xref tx, xref x, dbname db, mirna_gene mg where t.transcript_id=tx.transcript_id and tx.xref_id=x.xref_id and x.dbname_id=db.dbname_id and db.dbname='mirna_gene' and x.display_id=mg.id ";
	public static final String GET_ALL_BY_SNP = "select  mg.accession, mg.id, mg.status, mg.sequence from snp s, snp2mirna_gene s2mg, mirna_gene mg where s.name = ? and s.snp_id=s2mg.snp_id and s2mg.mirna_gene_id=mg.mirna_gene_id";
	// s.name, s.chromosome, s.start, s.end, s.strand, s.sequence, s.allele_string,
	public MiRnaGeneDBManager(DBConnector infraredDBConnector) {
		super(infraredDBConnector);
	}

	@SuppressWarnings("unchecked")
	public FeatureList<MiRnaGene> getAllByLocation(String chromosome, int position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		//t.chromosome=19 and t.start<=56887859 and t.end>=56887859";
		return getFeatureList(GET_BY_LOCATION+" and t.chromosome = '"+chromosome+"' and t.start <= "+position +" and t.end >= " + position, new BeanArrayListHandler(MiRnaGene.class));
	}

	@SuppressWarnings("unchecked")
	public List<FeatureList<MiRnaGene>> getAllBySnpIds(List<String> snpIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getListOfFeatureListByIds(GET_ALL_BY_SNP, snpIds, new BeanArrayListHandler(MiRnaGene.class));
	}
	
}

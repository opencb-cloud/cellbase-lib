package org.bioinfo.infrared.regulatory.dbsql;

import java.sql.SQLException;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.regulatory.MiRnaGene;

public class MiRnaGeneDBManager extends DBManager {

	public final static String GET_BY_LOCATION= "select mg.* from transcript t, transcript2xref tx, xref x, dbname db, mirna_gene mg where t.transcript_id=tx.transcript_id and tx.xref_id=x.xref_id and x.dbname_id=db.dbname_id and db.dbname='mirna_gene' and x.display_id=mg.id ";
	
	public MiRnaGeneDBManager(DBConnector rosettaDBConnector) {
		super(rosettaDBConnector);
	}

	@SuppressWarnings("unchecked")
	public FeatureList<MiRnaGene> getAllByLocation(String chromosome, int position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		//t.chromosome=19 and t.start<=56887859 and t.end>=56887859";
		return getFeatureList(GET_BY_LOCATION+" and t.chromosome = '"+chromosome+"' and t.start <= "+position +" and t.end >= " + position, new BeanArrayListHandler(MiRnaGene.class));
	}

}

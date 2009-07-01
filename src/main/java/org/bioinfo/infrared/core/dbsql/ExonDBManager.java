package org.bioinfo.infrared.core.dbsql;

import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.core.Exon;

public class ExonDBManager extends DBManager {

	public static final String GET_EXONLIST_BY_ID = "select e.stable_id,e.chromosome,e.start,e.end,e.strand from xref x, transcript2xref tx, exon2transcript et, exon e where x.display_id = ? and x.xref_id = tx.xref_id and tx.transcript_id=et.transcript_id and et.exon_id=e.exon_id group by e.stable_id  order by e.start";
	
	public ExonDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Exon> getAllById(String id) throws Exception{
//		return new ExonList((List<Exon>)getFeatureList(GET_EXONLIST_BY_ID, id, new BeanArrayListHandler(Exon.class)).getElements());
		return getFeatureListById(GET_EXONLIST_BY_ID, id, new BeanArrayListHandler(Exon.class));
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<FeatureList<Exon>> getAllByIds(List<String> ids) throws Exception{
		return getListOfFeatureListByIds(GET_EXONLIST_BY_ID,ids, new BeanArrayListHandler(Exon.class));
	}
}

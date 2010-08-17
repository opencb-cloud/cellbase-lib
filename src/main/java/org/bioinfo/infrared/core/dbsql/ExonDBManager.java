package org.bioinfo.infrared.core.dbsql;

import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.core.Exon;

public class ExonDBManager extends DBManager {

	public static final String GET_EXONLIST_BY_ID = "select e.stable_id, e.chromosome, e.start, e.end, e.strand, t.stable_id, g.stable_id from xref x, transcript2xref tx, exon2transcript et, exon e, transcript t, gene g where x.display_id = ? and x.xref_id = tx.xref_id and tx.transcript_id=et.transcript_id and et.exon_id=e.exon_id and tx.transcript_id=t.transcript_id and t.gene_id=g.gene_id group by e.stable_id  order by e.start";
	public static final String GET_EXONLIST_BY_POSITION = " select e.stable_id, e.chromosome, e.start, e.end, e.strand, t.stable_id, g.stable_id from exon e, exon2transcript e2t, transcript t, gene g where e.chromosome=1 and e.start<28059115 and e.end> 28059111 and e.exon_id=e2t.exon_id and e2t.transcript_id=t.transcript_id and t.gene_id=g.gene_id;";

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
	
	@SuppressWarnings({ "unchecked" })
	public FeatureList<Exon> getAllByPosition(String chromosome, int position) throws Exception{
//		return getFeatureList("select e.stable_id, e.chromosome, e.start, e.end, e.strand, t.stable_id, g.stable_id from exon e, exon2transcript e2t, transcript t, gene g where e.chromosome='"+chromosome+"' and e.start<"+position+" and e.end>"+position+" and e.exon_id=e2t.exon_id and e2t.transcript_id=t.transcript_id and t.gene_id=g.gene_id", new BeanArrayListHandler(Exon.class));
		return getFeatureList("select e.stable_id, e.chromosome, e.start, e.end, e.strand, t.stable_id, g.stable_id from feature_map_exon fme, exon e, exon2transcript e2t, transcript t, gene g where fme.chromosome='"+chromosome+"' and fme.position="+position+" and fme.id=e.exon_id and e.exon_id=e2t.exon_id and e2t.transcript_id=t.transcript_id and t.gene_id=g.gene_id", new BeanArrayListHandler(Exon.class));
	}
}

package org.bioinfo.infrared.funcannot.dbsql;

import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.regulatory.Triplex;

@Deprecated
public class TriplexDBManager extends DBManager {

	public static final String GET_ALL_BY_GENE_ID = "select t.triplex_id, g.stable_id, t.relative_start, t.relative_end, t.chromosome, t.absolute_start, t.absolute_end, t.strand, t.sequence, t.length from triplex t, gene g where g.stable_id = ? and g.gene_id=t.gene_id ";
	public static final String GET_ALL_BY_LOCATION = "select t.triplex_id, g.stable_id, t.relative_start, t.relative_end, t.chromosome, t.absolute_start, t.absolute_end, t.strand, t.sequence, t.length from triplex t, gene g where t.gene_id=g.gene_id ";
	
	public TriplexDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	 
	@SuppressWarnings("unchecked")
	public FeatureList<Triplex> getAllByGeneId(String geneId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new TriplexList((List<Triplex>)getFeatureList(GET_ALL_BY_GENE_ID, geneId, new BeanArrayListHandler(Triplex.class)).getElements());
		return getFeatureListById(GET_ALL_BY_GENE_ID, geneId, new BeanArrayListHandler(Triplex.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<Triplex>> getAllByGeneIds(List<String> geneIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		List<FeatureList<Triplex>> featList = getListOfFeatureListByIds(GET_ALL_BY_GENE_ID, geneIds, new BeanArrayListHandler(Triplex.class));
//		List<FeatureList<Triplex>> triplexList = new ArrayList<FeatureList<Triplex>>(featList.size());
//		for(FeatureList ros: featList) {
//			triplexList.add(new TriplexList((List<Triplex>)ros));
//		}
		return featList;
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Triplex> getAllByLocation(String chromosome, int position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new TriplexList((List<Triplex>)getFeatureList(GET_ALL_BY_LOCATION+" and t.chromosome = '"+chromosome+"' and t.absolute_start < "+position +" and t.absolute_end > " + position, new BeanArrayListHandler(Triplex.class)).getElements());		
		return getFeatureList(GET_ALL_BY_LOCATION+" and t.chromosome = '"+chromosome+"' and t.absolute_start < "+position +" and t.absolute_end > " + position, new BeanArrayListHandler(Triplex.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Triplex> getAllByRegion(String chromosome, int start, int end) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new TriplexList((List<Triplex>)getFeatureList(GET_ALL_BY_LOCATION+" and t.chromosome = '"+chromosome+"' and t.absolute_start > "+start +" and t.absolute_end < " + end, new BeanArrayListHandler(Triplex.class)).getElements());
		return getFeatureList(GET_ALL_BY_LOCATION+" and t.chromosome = '"+chromosome+"' and t.absolute_start > "+start +" and t.absolute_end < " + end, new BeanArrayListHandler(Triplex.class));
	}
}

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
import org.bioinfo.infrared.core.feature.Position;
import org.bioinfo.infrared.core.variation.AnnotatedMutation;

public class AnnotatedMutationDBManager extends DBManager {

	public AnnotatedMutationDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}

	
	public FeatureList<AnnotatedMutation> getAllByPosition(Position position) throws Exception{
		return getAllByPosition(position.getChromosome(), position.getPosition());
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<AnnotatedMutation> getAllByPosition(String chromosome, int position) throws Exception{
		return getFeatureList("select a.* from annotated_mutation a where a.chromosome = '"+chromosome+"' and a.start <= "+position+" and a.end >= "+position, new BeanArrayListHandler(AnnotatedMutation.class));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<FeatureList<AnnotatedMutation>> getAllByPositions(List<Position> positions) throws Exception{
		if(positions != null) {
			List<FeatureList<AnnotatedMutation>> snpList = new ArrayList<FeatureList<AnnotatedMutation>>(positions.size());
			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery("select a.* from annotated_mutation a where a.chromosome = ? and a.start <= ? and a.end >= ?");
			FeatureList<AnnotatedMutation> snpFeatureList;
			for(Position position: positions) {
				if(position != null) {
					prepQuery.setParams(position.getChromosome(), ""+position.getPosition(), ""+position.getPosition());
					snpFeatureList = new FeatureList((List<Feature>)prepQuery.execute(new BeanArrayListHandler(AnnotatedMutation.class)));
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
	public FeatureList<AnnotatedMutation> getAllAnnotatedMutations() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select * from annotated_mutation", new BeanArrayListHandler(AnnotatedMutation.class));
	}
	
}

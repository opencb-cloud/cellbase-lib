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
import org.bioinfo.infrared.core.feature.Region;
import org.bioinfo.infrared.core.variation.AnnotatedMutation;
import org.bioinfo.infrared.core.variation.SNP;

public class AnnotatedMutationDBManager extends DBManager {

	public AnnotatedMutationDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}

	@SuppressWarnings("unchecked")
	public FeatureList<SNP> getAllByRegion(String chromosome) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select a.* from annotated_mutation a where a.chromosome = '"+chromosome+"' ", new BeanArrayListHandler(SNP.class));
	}

	@SuppressWarnings("unchecked")
	public FeatureList<SNP> getAllByRegion(String chromosome, int start) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select a.* from annotated_mutation a where a.chromosome = '"+chromosome+"' and a.start >= "+start +" ", new BeanArrayListHandler(SNP.class));
	}

	@SuppressWarnings("unchecked")
	public FeatureList<SNP> getAllByRegion(String chromosome, int start, int end) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select a.* from annotated_mutation a where a.chromosome = '"+chromosome+"' and a.start >= "+start +" and a.end <= "+end+" ", new BeanArrayListHandler(SNP.class));
	}

	@SuppressWarnings("unchecked")
	public FeatureList<SNP> getAllByRegion(Region region) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select a.* from annotated_mutation a where a.chromosome = '"+region.getChromosome()+"' and a.start >= "+region.getStart()+" and a.end <= "+region.getEnd()+" ", new BeanArrayListHandler(SNP.class));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<FeatureList<AnnotatedMutation>> getAllByRegions(List<Region> regions) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		List<FeatureList<AnnotatedMutation>> annotMutationList = null;
		if(regions != null) {
			annotMutationList = new ArrayList<FeatureList<AnnotatedMutation>>(regions.size());
			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery("select a.* from annotated_mutation a where a.chromosome = ? and a.start >= ? and a.end <= ?");
			FeatureList<AnnotatedMutation> snpFeatureList;
			for(Region region: regions) {
				if(region != null) {
					prepQuery.setParams(region.getChromosome(), ""+region.getStart(), ""+region.getEnd());
					snpFeatureList = new FeatureList((List<Feature>)prepQuery.execute(new BeanArrayListHandler(AnnotatedMutation.class)));
					if(snpFeatureList != null && snpFeatureList.size() > 0) {
						annotMutationList.add(snpFeatureList);
					}else {
						annotMutationList.add(null);
					}
				}else {
					annotMutationList.add(null);
				}
			}
			prepQuery.close();
		}
		return annotMutationList;
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
		List<FeatureList<AnnotatedMutation>> annotMutationList = null;
		if(positions != null) {
			annotMutationList = new ArrayList<FeatureList<AnnotatedMutation>>(positions.size());
			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery("select a.* from annotated_mutation a where a.chromosome = ? and a.start <= ? and a.end >= ?");
			FeatureList<AnnotatedMutation> annotMutationFeatureList;
			for(Position position: positions) {
				if(position != null) {
					prepQuery.setParams(position.getChromosome(), ""+position.getPosition(), ""+position.getPosition());
					annotMutationFeatureList = new FeatureList((List<Feature>)prepQuery.execute(new BeanArrayListHandler(AnnotatedMutation.class)));
					if(annotMutationFeatureList != null && annotMutationFeatureList.size() > 0) {
						annotMutationList.add(annotMutationFeatureList);
					}else {
						annotMutationList.add(null);
					}
				}else {
					annotMutationList.add(null);
				}
			}
			prepQuery.close();
		}
		return annotMutationList;
	}


	@SuppressWarnings("unchecked")
	public FeatureList<AnnotatedMutation> getAllAnnotatedMutations() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select * from annotated_mutation", new BeanArrayListHandler(AnnotatedMutation.class));
	}

}

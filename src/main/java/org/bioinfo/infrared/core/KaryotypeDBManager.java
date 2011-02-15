package org.bioinfo.infrared.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.db.handler.BeanHandler;
import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.common.DBManager;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.feature.Chromosome;
import org.bioinfo.infrared.core.feature.Cytoband;
import org.bioinfo.infrared.core.feature.Position;
import org.bioinfo.infrared.core.feature.Region;

public class KaryotypeDBManager extends DBManager {

	public static final String select = "cytoband, chromosome, start, end, stain";
	public static final String GET_ALL_CYTOBANDS = "select " + select + " from karyotype order by chromosome, start";
	public static final String GET_CYTOBANDS = "select " + select + " from karyotype ";
	
	public KaryotypeDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	public FeatureList<Cytoband> getAllCytoband() throws Exception{
		return getFeatureList(GET_ALL_CYTOBANDS, new BeanArrayListHandler(Cytoband.class));
	}
	//No sirve, es solamente pra que compile
	public List<Chromosome>  getAllChromosomes() throws Exception{
		return null;
	}

	public FeatureList<Cytoband> getCytobandByChromosomes(List<String> chromosomeIds) throws Exception{
			FeatureList<Cytoband> list = new FeatureList<Cytoband>();
			for (String id : chromosomeIds) {
				list.addAll(getCytobandByChromosome(id));
			}
			return list;
	}
	
	public FeatureList<Cytoband> getCytobandById(String cytobandId) throws Exception{
		System.out.println(GET_CYTOBANDS + " where cytoband = '" + cytobandId + "' order  by chromosome, start");
		return getFeatureList(GET_CYTOBANDS + " where cytoband = '" + cytobandId + "' order  by chromosome, start",  new BeanArrayListHandler(Cytoband.class));

	}
	
	public FeatureList<Cytoband> getCytobandById(List<String> cytobandIds) throws Exception{
		FeatureList<Cytoband> list = new FeatureList<Cytoband>();
		for (String id : cytobandIds) {
			list.addAll(getCytobandById(id));
		}
		return list;
	}
	
	
	
	public FeatureList<Cytoband> getCytobandByChromosome(String chromosomeID) throws Exception{
			return getFeatureList(GET_CYTOBANDS + " where chromosome = " + chromosomeID + " order  by chromosome, start",  new BeanArrayListHandler(Cytoband.class));
	}
	
	public List<FeatureList<Cytoband>> getCytobandByRegions(List<Region> regions) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
			List<FeatureList<Cytoband>> list = new ArrayList<FeatureList<Cytoband>>();
			for (Region  region : regions) {
				list.add(getCytobandByRegion(region));
			}
			return list;
	}
	
	public FeatureList<Cytoband> getCytobandByRegion(Region region) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
			String query = String.format(GET_CYTOBANDS + " where chromosome = '%s' and start>=%s and end<=%s order by chromosome, start", region.getChromosome(), region.getStart(), region.getEnd());
			return getFeatureList(query,  new BeanArrayListHandler(Cytoband.class));
	}
	
	
	public List<FeatureList<Cytoband>> getCytobandByPosition(List<Position> positions) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
			List<FeatureList<Cytoband>> list = new ArrayList<FeatureList<Cytoband>>();
			for (Position position : positions) {
				list.add(getCytobandByPosition(position));
			}
			return list;
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Cytoband> getCytobandByPosition(Position position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
			String query = String.format(GET_CYTOBANDS + " where chromosome ='%s' and start<=%s and %s<=end ", position.getChromosome(), position.getPosition(), position.getPosition());
			return getFeatureList(query,  new BeanArrayListHandler(Cytoband.class));
	}
}

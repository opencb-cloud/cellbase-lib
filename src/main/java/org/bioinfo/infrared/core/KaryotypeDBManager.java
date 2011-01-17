package org.bioinfo.infrared.core;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.db.handler.BeanHandler;
import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.common.DBManager;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.feature.Chromosome;
import org.bioinfo.infrared.core.feature.Cytoband;
import org.bioinfo.infrared.core.feature.Region;

public class KaryotypeDBManager extends DBManager {

	public static final String select = "cytoband, chromosome, start, end, stain";
	
	
	public static final String GET_ALL_CYTOBANDS = "select " + select + " from karyotype order by chromosome, start";
	
	//public static final String GET_ALL_CYTOBANDS = "select distinct cytoband, chromosome, min(start), max(end), stain from karyotype group by chromosome";
	public static final String GET_CYTOBANDS = "select " + select + " from karyotype ";
	
	
	//public static final String GET_CYTOBANDS_BY_CHROMOSOME = "select " + select + " from karyotype where chromosome = ? order by start";
	
	public KaryotypeDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Cytoband> getAllCytoband() throws Exception{
		return getFeatureList(GET_ALL_CYTOBANDS, new BeanArrayListHandler(Cytoband.class));
	}

	@SuppressWarnings("unchecked")
	public FeatureList<Cytoband> getCytobandByChromosomes(List<String> chromosomeIds) throws Exception{
		try
		{
			FeatureList<Cytoband> list = new FeatureList<Cytoband>();
			for (String id : chromosomeIds) {
				list.addAll(getCytobandByChromosomes(id));
			}
			return list;
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Cytoband> getCytobandByChromosomes(String chromosomeID) throws Exception{
		try
		{
			return getFeatureList(GET_CYTOBANDS + " where chromosome = " + chromosomeID + " order  by chromosome, start",  new BeanArrayListHandler(Cytoband.class));
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<Cytoband>> getCytobandByRegions(List<Region> regions) throws Exception{
		List<FeatureList<Cytoband>> pepe = new ArrayList<FeatureList<Cytoband>>(regions.size());
		
		return null;
	}
	

	
}

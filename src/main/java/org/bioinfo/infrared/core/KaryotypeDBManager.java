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

	//public static final String GET_ALL_CYTOBANDS = "select * from karyotype order by start";
	public static final String GET_ALL_CYTOBANDS = "select distinct cytoband, chromosome, min(start), max(end), stain from karyotype group by chromosome";
	public static final String GET_CYTOBANDS_BY_CHROMOSOME = "select distinct cytoband, chromosome, min(start), max(end), stain  from karyotype where chromosome = ? group by chromosome";
	//public static final String GET_CYTOBANDS_BY_CHROMOSOME = "select * from karyotype where chromosome = ? order by start";
	
	public KaryotypeDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Cytoband> getAllCytoband() throws Exception{
		return getFeatureList(GET_ALL_CYTOBANDS, new BeanArrayListHandler(Cytoband.class));
	}

	@SuppressWarnings("unchecked")
	public FeatureList<Cytoband> getCytobandByChromosomes(List<String> ids) throws Exception{
		try
		{
			System.err.println(ids.toString()); 
			return getFeatureListByIds(GET_CYTOBANDS_BY_CHROMOSOME, ids, new BeanHandler(Cytoband.class));
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

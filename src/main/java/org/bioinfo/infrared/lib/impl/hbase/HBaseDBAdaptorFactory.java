package org.bioinfo.infrared.lib.impl.hbase;

import java.util.Properties;

import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;


public class HBaseDBAdaptorFactory extends DBAdaptorFactory{

	@Override
	public void setConfiguration(Properties properties) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GeneDBAdaptor getGeneDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public GeneDBAdaptor getGeneDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public GeneDBAdaptor getTranscriptDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneDBAdaptor getExonDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneDBAdaptor getProteinDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneDBAdaptor getSnpDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneDBAdaptor getCytobandDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneDBAdaptor getChromosomeDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneDBAdaptor getGenomeDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneDBAdaptor getXRefDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}


}

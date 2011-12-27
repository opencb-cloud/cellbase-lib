package org.bioinfo.infrared.lib.impl.hbase;

import java.util.Properties;

import org.bioinfo.infrared.lib.api.CytobandDBAdaptor;
import org.bioinfo.infrared.lib.api.ExonDBAdaptor;
import org.bioinfo.infrared.lib.api.GenomicRegionFeatureDBAdaptor;
import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.api.MirnaDBAdaptor;
import org.bioinfo.infrared.lib.api.ProteinDBAdaptor;
import org.bioinfo.infrared.lib.api.RegulatoryRegionDBAdaptor;
import org.bioinfo.infrared.lib.api.SnpDBAdaptor;
import org.bioinfo.infrared.lib.api.TfbsDBAdaptor;
import org.bioinfo.infrared.lib.api.TranscriptDBAdaptor;
import org.bioinfo.infrared.lib.api.XRefsDBAdaptor;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.bioinfo.infrared.lib.impl.hibernate.GenomeSequenceDBAdaptor;


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
	public TranscriptDBAdaptor getTranscriptDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public TranscriptDBAdaptor getTranscriptDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public ExonDBAdaptor getExonDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ExonDBAdaptor getExonDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public GenomicRegionFeatureDBAdaptor getFeatureMapDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public GenomicRegionFeatureDBAdaptor getFeatureMapDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ProteinDBAdaptor getProteinDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ProteinDBAdaptor getProteinDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public SnpDBAdaptor getSnpDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SnpDBAdaptor getSnpDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public GenomeSequenceDBAdaptor getGenomeSequenceDBAdaptor(String species,
			String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenomeSequenceDBAdaptor getGenomeSequenceDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@Override
	public CytobandDBAdaptor getCytobandDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public GeneDBAdaptor getChromosomeDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}


	

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public CytobandDBAdaptor getCytobandDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public XRefsDBAdaptor getXRefDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public XRefsDBAdaptor getXRefDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public TfbsDBAdaptor getTfbsDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public TfbsDBAdaptor getTfbsDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public RegulatoryRegionDBAdaptor getRegulatoryRegionDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public RegulatoryRegionDBAdaptor getRegulatoryRegionDBAdaptor(
			String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public MirnaDBAdaptor getMirnaDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public MirnaDBAdaptor getMirnaDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}

}

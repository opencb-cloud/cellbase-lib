package org.bioinfo.infrared.lib.impl.hbase;

import java.util.List;

import org.bioinfo.infrared.core.Gene;
import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.common.Region;

class GeneHBaseDBAdaptor extends HBaseDBAdaptorFactory implements GeneDBAdaptor{

	@Override
	public List<Gene> getGeneById(String geneId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Gene>> getGeneByIdList(List<String> geneIdList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSequence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSequence(String strand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Region getRegion(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

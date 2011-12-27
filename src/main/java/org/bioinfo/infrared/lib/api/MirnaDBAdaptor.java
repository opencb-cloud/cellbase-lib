package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.MirnaDisease;
import org.bioinfo.infrared.core.cellbase.Tfbs;
import org.bioinfo.infrared.coreold.regulatory.MiRnaTarget;

public interface MirnaDBAdaptor {

	
	public List<MiRnaTarget> getAllByMirbaseId(String id);

	public List<List<MiRnaTarget>> getAllByMirbaseId(List<String> ids);
	
	
	public List<MiRnaTarget> getAllByGeneNameList(String geneName);
	
	public List<List<MiRnaTarget>> getAllByGeneNameList(List<String> geneNames);

	public List<MirnaDisease> getAllDiseasesByMirnaId(String mirbaseId);


	public List<List<MirnaDisease>> getAllDiseasesByMirnaIdList(List<String> mirbaseId);

	public List<Object> getAnnotation();

	public List<Object> getAnnotation(List<String> sources);
}

package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.MirnaDisease;
import org.bioinfo.infrared.coreold.regulatory.MiRnaTarget;

public interface MirnaDBAdaptor {


	public List<MiRnaTarget> getAllMiRnaTargetsByMirbaseId(String id);

	public List<List<MiRnaTarget>> getAllMiRnaTargetsByMirbaseIdList(List<String> ids);
	
	
	public List<MiRnaTarget> getAllMiRnaTargetsByGeneName(String geneName);
	
	public List<List<MiRnaTarget>> getAllMiRnaTargetsByGeneNameList(List<String> geneNames);

	
	public List<MirnaDisease> getAllMiRnaDiseasesByMirbaseId(String mirbaseId);

	public List<List<MirnaDisease>> getAllMiRnaDiseasesByMirbaseIdList(List<String> mirbaseId);

	
	public List<Object> getAllAnnotation();

	public List<Object> getAllAnnotationBySourceList(List<String> sourceList);

}

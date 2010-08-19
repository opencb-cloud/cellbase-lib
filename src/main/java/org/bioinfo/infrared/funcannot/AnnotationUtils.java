package org.bioinfo.infrared.funcannot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bioinfo.commons.io.TextFileReader;
import org.bioinfo.commons.io.utils.FileUtils;
import org.bioinfo.commons.io.utils.IOUtils;
import org.bioinfo.infrared.common.feature.FeatureList;

public class AnnotationUtils {

	@Deprecated
	public static FeatureList<AnnotationItem> readAnnotationFileDep(String filename) throws IOException {
		FileUtils.checkFile(filename);
		TextFileReader tfr = new TextFileReader(filename);
		String[] fields;
		String line;
		while((line = tfr.readLine()) != null) {
			if(!line.startsWith("#") && !line.trim().equals("")) {
				fields = line.split("\t");
				if(fields.length == 2) {
					if(fields[1].contains(",")) {
						tfr.close();
						return readAnnotationFile(filename);
					}
				}
			}
		}
		tfr.close();
		return readExtendedFormatFile(filename);
	}
	
	@Deprecated
	public static FeatureList<AnnotationItem> readExtendedFormatFile(String filename) throws IOException {
		FileUtils.checkFile(filename);
		int numLines = IOUtils.countLines(filename);
		FeatureList<AnnotationItem> featureList = new FeatureList<AnnotationItem>(numLines);
		TextFileReader tfr = new TextFileReader(filename);
		String[] fields;
		String line;
		while((line = tfr.readLine()) != null) {
			if(!line.startsWith("#") && !line.trim().equals("")) {
				fields = line.split("\t");
				if(fields.length == 2) {
					featureList.add(new AnnotationItem(fields[0], fields[1]));
				}
			}
		}
		tfr.close();		
		return featureList;
	}

	public static FeatureList<AnnotationItem> readAnnotationFile(String filename) throws IOException {
		FileUtils.checkFile(filename);
		int numLines = IOUtils.countLines(filename);
		FeatureList<AnnotationItem> featureList = new FeatureList<AnnotationItem>(numLines*4);
		TextFileReader tfr = new TextFileReader(filename);
		String[] fields;
		String[] annotations;
		String line;
		while((line = tfr.readLine()) != null) {
			if(!line.startsWith("#") && !line.trim().equals("")) {
				fields = line.split("\t");
				if(fields.length == 2) {
					annotations = fields[1].split(",");
					for(String annot: annotations) {
						featureList.add(new AnnotationItem(fields[0], annot));
					}
				}
			}
		}
		tfr.close();		
		return featureList;
	}

	public static List<String> toExtendedFormatList(FeatureList<AnnotationItem> featureList) {
		List<String> list = new ArrayList<String>(featureList.size());
		for(AnnotationItem ai: featureList) {
			list.add(ai.getId()+"\t"+ai.getFunctionalTermId());
		}
		return list;
	}
	
	public static List<String> toCompactFormatList(FeatureList<AnnotationItem> featureList) {
		List<String> list = new ArrayList<String>(featureList.size());
		LinkedHashMap<String, StringBuilder> values = new LinkedHashMap<String, StringBuilder>(featureList.size());
		for(AnnotationItem ai: featureList) {
			if(!values.containsKey(ai.getId())) {
				values.put(ai.getId(), new StringBuilder());
				values.get(ai.getId()).append(ai.getFunctionalTermId());
			}else {
				values.get(ai.getId()).append(",").append(ai.getFunctionalTermId());
			}
		}
		Iterator<String> it = values.keySet().iterator();
		String id;
		while(it.hasNext()) {
			id = it.next();
			list.add(id+"\t"+values.get(id).toString());
		}
		return list;
	}
	
	public static FeatureList<AnnotationItem> filterByNumberOfAnnotationsPerId(FeatureList<AnnotationItem> al, int numMin, int numMax) {
		FeatureList<AnnotationItem> annotationResult = new FeatureList<AnnotationItem>(100);
		Map<String, Integer> visitedTerms = new LinkedHashMap<String, Integer>();
		Map<String, List<String>> termsToGenes = new HashMap<String, List<String>>();
		for(AnnotationItem ai: al) {
			if(visitedTerms.containsKey(ai.getFunctionalTermId())) {
				visitedTerms.put(ai.getFunctionalTermId(), visitedTerms.get(ai.getFunctionalTermId()) + 1);
			}else {
				visitedTerms.put(ai.getFunctionalTermId(), 1);
				termsToGenes.put(ai.getFunctionalTermId(), new ArrayList<String>());
			}
			if(!termsToGenes.get(ai.getFunctionalTermId()).contains(ai.getId())) {
				termsToGenes.get(ai.getFunctionalTermId()).add(ai.getId());
			}
		}
		for(String key: visitedTerms.keySet()) {
			if(visitedTerms.get(key) >= numMin && visitedTerms.get(key) <= numMax) {
				for(String id: termsToGenes.get(key)) {
					annotationResult.add(new AnnotationItem(id, key));
				}
			}
		}
		return annotationResult;
	}

}

package org.bioinfo.infrared.lib.io.parse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.bioinfo.commons.io.utils.FileUtils;
import org.bioinfo.formats.core.feature.Bed;
import org.bioinfo.formats.core.feature.Gff;
import org.bioinfo.formats.core.feature.io.BedReader;
import org.bioinfo.formats.core.feature.io.GffReader;
import org.bioinfo.formats.exception.FileFormatException;
import org.bioinfo.infrared.lib.common.Attribute;
import org.bioinfo.infrared.lib.common.Display;
import org.bioinfo.infrared.lib.common.FeatureJsonFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FeatureToJsonParser {

	List<FeatureJsonFormat> seqNames;

	public FeatureToJsonParser() {
		init();
	}

	private void init() {
		seqNames = new ArrayList<FeatureJsonFormat>(60000);
	}

	public void bedParseToJson(File fileRead, File fileWrite) throws IOException, SecurityException, NoSuchMethodException, FileFormatException {
		FileUtils.checkFile(fileRead);
		init();
		
		FeatureJsonFormat featJsonFormat = new FeatureJsonFormat();
		BufferedWriter bfr = new BufferedWriter(new FileWriter(fileWrite));
		String strand = "";
		String score = "";
		String name = "";
		Integer thickStart = 0;
		Integer thickEnd = 0;
		String itemRgb = "";
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		Gson gson = new Gson();
		BedReader bedReader = new BedReader(fileRead);
//		System.out.println(bedReader.size());
		Bed bed;
		while((bed = bedReader.read()) != null) {
			featJsonFormat.setSeqName(bed.getChromosome());
			featJsonFormat.setStart(bed.getStart());
			featJsonFormat.setEnd(bed.getEnd());
			
			if ((strand = bed.getStrand()) != null)
				featJsonFormat.setStrand(strand);
			else 
				featJsonFormat.setStrand("");
			
			if ((score = String.valueOf(bed.getScore())) != null)
				featJsonFormat.setScore(score);
			else
				featJsonFormat.setScore("0");
			
			if ((name = bed.getName()) != null){
				featJsonFormat.getDisplay().setName(name);
			}else
				featJsonFormat.getDisplay().setName("");
				
			if ((thickStart = bed.getThickStart()) != null)
				featJsonFormat.getDisplay().setThickStart(thickStart);
			else 
				featJsonFormat.getDisplay().setThickStart(0);
			
			if ((thickEnd = bed.getThickEnd()) != null)
				featJsonFormat.getDisplay().setThickEnd(thickEnd);
			else 
				featJsonFormat.getDisplay().setThickEnd(0);
			
			if ((itemRgb = bed.getItemRgb()) != null)
				featJsonFormat.getDisplay().setItemRgb(itemRgb);
			else 
				featJsonFormat.getDisplay().setItemRgb("");

			bfr.write(gson.toJson(featJsonFormat));
		}
		bfr.close();
		bedReader.close();
	}
	
	public void gffParseToJson(File fileRead,File fileWrite) throws IOException, SecurityException, NoSuchMethodException, FileFormatException {
		FileUtils.checkFile(fileRead);
		init();
		
		FeatureJsonFormat featJsonFormat = new FeatureJsonFormat();
		BufferedWriter bfr = new BufferedWriter(new FileWriter(fileWrite));
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
//		Gson gson = new Gson(); // Este ocupa la mitad que el anterior
		GffReader gffReader = new GffReader(fileRead);		
		Gff gff;
		while((gff = gffReader.read()) != null) {
			featJsonFormat.setSeqName(gff.getSequenceName());
			featJsonFormat.setStart(gff.getStart());
			featJsonFormat.setEnd(gff.getEnd());
			featJsonFormat.setStrand(gff.getStrand());
			featJsonFormat.setScore(gff.getScore());
			featJsonFormat.setType(gff.getFeature());
			featJsonFormat.setPhase(gff.getFrame());
			featJsonFormat.setGroup(gff.getGroup());
//			featJsonFormat.setDisplay(display);
//			featJsonFormat.setAttributes(attribute);
			bfr.write(gson.toJson(featJsonFormat));
		}
		bfr.close();
		gffReader.close();
		
	}
}


package org.bioinfo.infrared.lib.io.parse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bioinfo.commons.io.utils.FileUtils;
import org.bioinfo.formats.core.feature.Bed;
import org.bioinfo.formats.core.feature.Gff;
import org.bioinfo.formats.core.feature.io.BedReader;
import org.bioinfo.formats.core.feature.io.GffReader;
import org.bioinfo.formats.exception.FileFormatException;
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

	@SuppressWarnings("null")
	public String bedParseToJson(File file) throws IOException, SecurityException, NoSuchMethodException, FileFormatException {
		FileUtils.checkFile(file);
		init();
		
		FeatureJsonFormat seqName = null;
		String strand = "";
		String score = "";
		String name = "";
		Integer thickStart = 0;
		Integer thickEnd = 0;
		String itemRgb = "";
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		BedReader bedReader = new BedReader(file);		
		Bed bed;
		while((bed = bedReader.read()) != null) {
			seqName.setSeqName(bed.getChromosome());
			seqName.setStart(bed.getStart());
			seqName.setEnd(bed.getEnd());
			
			if ((strand = bed.getStrand()) != null)
				seqName.setStrand(strand);
			else 
				seqName.setStrand("");
			
			if ((score = String.valueOf(bed.getScore())) != null)
				seqName.setScore(score);
			else
				seqName.setScore("0");
			
			if ((name = bed.getName()) != null)
				seqName.getDisplay().setName(name);
			else
				seqName.getDisplay().setName("");
				
			if ((thickStart = bed.getThickStart()) != null)
				seqName.getDisplay().setThickStart(thickStart);
			else 
				seqName.getDisplay().setThickStart(0);
			
			if ((thickEnd = bed.getThickEnd()) != null)
				seqName.getDisplay().setThickEnd(thickEnd);
			else 
				seqName.getDisplay().setThickEnd(0);
			
			if ((itemRgb = bed.getItemRgb()) != null)
				seqName.getDisplay().setItemRgb(itemRgb);
			else 
				seqName.getDisplay().setItemRgb("");

			seqNames.add(seqName);
		}

		bedReader.close();
		return gson.toJson(seqNames);
	}
	
	@SuppressWarnings("null")
	public String gffParseToJson(File file) throws IOException, SecurityException, NoSuchMethodException, FileFormatException {
		FileUtils.checkFile(file);
		init();
		
		FeatureJsonFormat featJsonFormat = new FeatureJsonFormat();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		GffReader gffReader = new GffReader(file);		
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

			seqNames.add(featJsonFormat);
		}

		gffReader.close();
		return gson.toJson(seqNames);
		
	}
}


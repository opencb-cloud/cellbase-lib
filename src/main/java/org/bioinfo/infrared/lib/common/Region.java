package org.bioinfo.infrared.lib.common;

import java.util.ArrayList;
import java.util.List;


public class Region {

	private String chromosome;
	private int start;
	private int end;
	
	public Region(String chromosome, int start, int end) {
		super();
		this.chromosome = chromosome;
		this.start = start;
		this.end = end;
	}

	public String getChromosome() {
		return chromosome;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}
	
	
	public static Region parseRegion(String regionString) {
		Region region = null;
		if(regionString != null && !regionString.equals("")) {
			if(regionString.indexOf(':') != -1) {
				String[] fields = regionString.split("[:-]", -1);
				if(fields.length == 3) {
					region = new Region(fields[0], Integer.parseInt(fields[1]), Integer.parseInt(fields[2]));
				}
			}else {
				region = new Region(regionString, 0, Integer.MAX_VALUE);
			}
		}
		return region;
	}

	public static List<Region> parseRegions(String regionsString) {
		List<Region> regions = null;
		if(regionsString != null && !regionsString.equals("")) {
			regions = new ArrayList<Region>();
			String[] regionsItems = regionsString.split(",");
			for(String regionString: regionsItems) {
				if(regionString.indexOf(':') != -1) {
					String[] fields = regionString.split("[:-]", -1);
					if(fields.length == 3) {
						regions.add(new Region(fields[0], Integer.parseInt(fields[1]), Integer.parseInt(fields[2])));
					}
				}else {
					regions.add(new Region(regionString, 0, Integer.MAX_VALUE));
				}
			}	
		}
		return regions;
	}

	public static String parseRegions(List<Region> regions) {
		if(regions != null) {
			StringBuilder br = new StringBuilder();
			for (Region region : regions) {
				if(region != null) {
					br.append(region.toString()).append(",");					
				}else {
					br.append("null,");
				}
			}
			if(br.toString().length() > 0) {
				return br.toString().substring(0, br.toString().length()-1);			
			}else {
				return br.toString();
			}
		}
		return null;
	}
	
}

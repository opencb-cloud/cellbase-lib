package org.bioinfo.infrared.lib.common;

import java.util.ArrayList;
import java.util.List;


public class GenomicVariant {

	private String chromosome;
	private int start;
	private String alternative;

	public GenomicVariant(String chromosome, int start, String alternative) {
		this.chromosome = chromosome;
		this.start = start;
		this.setAlternative(alternative);
	}

	public static GenomicVariant parseVariant(String regionString) {
		GenomicVariant region = null;
		if(regionString != null && !regionString.equals("")) {
			if(regionString.indexOf(':') != -1) {
				String[] fields = regionString.split("[:-]", -1);
				if(fields.length == 3) {
					region = new GenomicVariant(fields[0], Integer.parseInt(fields[1]), fields[2]);
				}
			}else {
				region = new GenomicVariant(regionString, 0, new String());
			}
		}
		return region;
	}

	public static List<GenomicVariant> parseVariants(String regionsString) {
		List<GenomicVariant> regions = null;
		if(regionsString != null && !regionsString.equals("")) {
			String[] regionItems = regionsString.split(",");
			regions = new ArrayList<GenomicVariant>(regionItems.length);
			String[] fields;
			for(String regionString: regionItems) {
				if(regionString.indexOf(':') != -1) {
					fields = regionString.split("[:-]", -1);
					if(fields.length == 3) {
						regions.add(new GenomicVariant(fields[0], Integer.parseInt(fields[1]), fields[2]));
					}else {
						regions.add(null);
					}
				}else {
					regions.add(new GenomicVariant(regionString, 0, new String()));
				}
			}	
		}
		return regions;
	}

	/**
	 * 
	 * @param regions
	 * @return A comma separated string with all the regions. If parameter is null then a null objects is returned, an empty string is returned if parameter size list is 0 
	 */
	public static String parseRegionList(List<GenomicVariant> regions) {
		if(regions == null) {
			return null;
		}else {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<regions.size()-1; i++) {
				if(regions.get(i) != null) {
					sb.append(regions.get(i).toString()).append(",");					
				}else {
					sb.append("null,");
				}
			}
			if(regions.get(regions.size()-1) != null) {
				sb.append(regions.get(regions.size()-1).toString());					
			}else {
				sb.append("null");
			}
			
			return sb.toString();
		}
	}


	@Override
	public String toString() {
		return chromosome+":"+start+":"+this.getAlternative(); 
	}


	/**
	 * @return the chromosome
	 */
	public String getChromosome() {
		return chromosome;
	}

	/**
	 * @param chromosome the chromosome to set
	 */
	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}


	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	public void setAlternative(String alternative) {
		this.alternative = alternative;
	}

	public String getAlternative() {
		return alternative;
	}



}

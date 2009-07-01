package org.bioinfo.infrared.funcannot.filter;


@SuppressWarnings("serial")
public abstract class GenomicFilter extends Filter{

	private String chromosome;
	private String start;
	private String end;
	
//	public boolean pass(GenomicFeature feature) {
//		boolean pass = true;
//		if(this.get("regionName") != null) {
//			pass = pass && equals(this.get("regionName"),feature.getRegionName());
//		}
//		if(this.get("start") != null) {
//			pass = pass && major(feature.getStart(), this.get("start"));
//		}
//		if(this.get("end") != null) {
//			pass = pass && minor(feature.getEnd(), this.get("end"));
//		}
//		return pass;
//	}

	public String getWhereClause() {
		StringBuilder query = new StringBuilder();
		if(chromosome != null && !chromosome.equalsIgnoreCase("")) {
			query.append(" seq_region_name = ").append(chromosome);
		}
		if(start != null && !start.equalsIgnoreCase("")) {
			query.append(" and ").append(" start >= ").append(start);	
		}
		if(end != null && !end.equalsIgnoreCase("")) {
			query.append(" and end <= ").append(end);
		}
		return query.toString();
	}

}

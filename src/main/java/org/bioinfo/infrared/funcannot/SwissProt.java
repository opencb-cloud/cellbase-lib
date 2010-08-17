package org.bioinfo.infrared.funcannot;

import java.util.ArrayList;

import org.bioinfo.commons.utils.StringUtils;

public class SwissProt {
	
	private String swissProtId;
	private ArrayList<String> transcrits;
	private String description;
	
	private SwissProt(String swissProtId, ArrayList<String> transcrits,	String description) {
		super();
		this.swissProtId = swissProtId;
		this.transcrits = transcrits;
		this.description = description;
	}

//	public static ArrayList<GO> getAllSwissProtList() throws Exception{
//		ArrayList<GO> gos = new ArrayList<GO>();
//		ResultList dataResult;
//		ArrayList<String> line;
//		Utils.connect();
//		Query sqlQuery = Utils.dbConn.createSQLQuery();
//		sqlQuery.setQuery("select * from h");
//		dataResult = sqlQuery.run();
//		for(int i=0;i<dataResult.getRowNumber();i++) {
//			line = dataResult.getRowAsArrayList(i);
//			gos.add(new GO(line.get(0),line.get(1),line.get(2),line.get(3),line.get(4),line.get(5),line.get(6),line.get(7)));
//		}
//		sqlQuery.close();
//		Utils.disconnect();
//		return gos;
//	}
	
	/*
	public static ArrayList<SwissProt> getSwissProtListByIds(String specie, String ids) throws Exception{
		return getSwissProtListByIds(specie,Utils.str2ArrayList(ids));
	}
	public static ArrayList<SwissProt> getSwissProtListByIds(String specie, ArrayList<String> ids) throws Exception{
		ArrayList<SwissProt> prots = new ArrayList<SwissProt>();
		Utils.connect();
		ResultList resultList;
		PreparedQuery sqlPrepQuery = Utils.dbConn.createSQLPrepQuery("select stable_id,description from "+specie+"_xref where display_id = ? and dbname = 'uniprot/swissprot'");
		for(String id: ids) {
			sqlPrepQuery.setParams(id);
			resultList = sqlPrepQuery.run();
			if(resultList.getRowNumber()>0)
				prots.add(new SwissProt(id,resultList.getColumnAsArrayList(0),resultList.getValue(0, 1)));
			else
				prots.add(new SwissProt(id,null,"unknown"));
		}
		sqlPrepQuery.close();
		Utils.disconnect();
		return prots;
	}
	*/
	public String toString() {
		if(transcrits != null)
			return swissProtId+"\t"+StringUtils.arrayToString(transcrits, ",")+"\t"+description;
		else
			return swissProtId+"\tunknown\t"+description;
	}
	
	public String getSwissProtId() {
		return swissProtId;
	}

	public void setSwissProtId(String swissProtId) {
		this.swissProtId = swissProtId;
	}

	public ArrayList<String> getTranscrits() {
		return transcrits;
	}

	public void setTranscrits(ArrayList<String> transcrits) {
		this.transcrits = transcrits;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

package org.bioinfo.infrared.funcannot;

import java.sql.SQLException;

import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.common.feature.FunctionalFeature;
import org.bioinfo.infrared.funcannot.dbsql.GOFactory;


public class GO extends FunctionalFeature {

//	private String id;
//	private String name;
	private String namespace;
//	private String description;
	private int level;
	private int directNumberOfGenes;
	private int propagatedNumberOfGenes;
	private String score;
	private String coherence_index;
	private FeatureList<GO> parents;
	private FeatureList<GO> children;
	
	public static final int MIN_LEVEL = 1;
	public static final int MAX_LEVEL = 19;
	
	public GO(String acc, String name, String namespace, String description, Integer level, Integer directNumberOfGenes, Integer propagatedNumberOfGenes) {
		super(acc,name,description);
//		this.id = acc;
//		this.name = name;
		this.namespace = namespace;
		this.description = description;
		this.level = level;
		this.directNumberOfGenes = directNumberOfGenes;
		this.propagatedNumberOfGenes = propagatedNumberOfGenes;
	}
	
	public GO(String acc, String name, String namespace, String description, Integer level, Integer numberOfGenes, FeatureList<GO> parents, FeatureList<GO> children) {
		super(acc,name,description);
//		this.id = acc;
//		this.name = name;
		this.namespace = namespace;
		this.description = description;
		this.level = level;
		this.directNumberOfGenes = numberOfGenes;
		this.parents = parents;
		this.children = children;
	}
	
	@Override
	public String toString() {
		return id+"\t"+name+"\t"+namespace+"\t"+description+"\t"+level+"\t"+directNumberOfGenes;
	}
	
	/*****************************************************
	 * ***************************************************
	 * FACTORY METHODS
	 * ***************************************************
	******************************************************/

	/**
	 * @return the parents
	 */
	public FeatureList<GO> getParents() {
		if(parents == null) {
			GOFactory gof = new GOFactory(getRosettaDBConnector());
			try {
				parents = gof.getParentsByAccesion(id);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return parents;
	}

	/**
	 * @return the children
	 */
	public FeatureList<GO> getChildren() {
		return children;
	}
	
	
//	public static List<String> getAllIds() throws Exception{
//		List<String> ids;
//		Utils.connect();
//		Query sqlQuery = Utils.dbConn.createSQLQuery();
//		sqlQuery.setQuery("select id from go_info");
//		ids = sqlQuery.run().getColumnByIndex(0);
//		sqlQuery.close();
//		Utils.disconnect();
//		return ids;
//	}
//	
//	public static List<String> getAllIdsBySpecie(String specie) throws Exception{
//		List<String> ids;
//		Utils.connect();
//		Query sqlQuery = Utils.dbConn.createSQLQuery();
//		sqlQuery.setQuery("select display_id from "+ specie +"_xref where dbname = 'go' group by display_id");
//		ids = sqlQuery.run().getColumnByIndex(0);
//		sqlQuery.close();
//		Utils.disconnect();
//		return ids;
//	}
//	
//	public static List<String> getAllIdsFiltered(String minLevel, String maxLevel, String filter, String score, String coherence_index) throws Exception{
//		List<String> ids;
//		String sQLCommand = "select id from go_info where ";
//		
//		sQLCommand += " level >= " + getDefaultValue(minLevel,""+MIN_LEVEL) + " and ";
//		sQLCommand += " level <= " + getDefaultValue(maxLevel,""+MAX_LEVEL) + " and ";
//		sQLCommand += " (" + getDefaultNamespace(filter) + ") and ";
//		sQLCommand += " score_b2g >= " + getDefaultValue(score,"0") + " ";
//		
//		Utils.connect();
//		Query sqlQuery = Utils.dbConn.createSQLQuery();
//		sqlQuery.setQuery(sQLCommand);
//		ids = sqlQuery.run().getColumnByIndex(0);
//		sqlQuery.close();
//		Utils.disconnect();
//		return ids;
//	}
//
//	public static List<GO> getAllGOList() throws Exception{
//		List<GO> gos = new ArrayList<GO>();
//		ResultList resultList;
//		List<String> line;
//		Utils.connect();
//		Query sqlQuery = Utils.dbConn.createSQLQuery();
//		sqlQuery.setQuery("select * from go_info");
//		resultList = sqlQuery.run();
//		for(int i=0;i<resultList.getNumberOfRegisters();i++) {
//			line = resultList.getRowByIndex(i);
//			gos.add(new GO(line.get(0),line.get(1),line.get(2),line.get(3),line.get(4),line.get(5),line.get(6),line.get(7)));
//		}
//		sqlQuery.close();
//		Utils.disconnect();
//		return gos;
//	}
//	public static List<GO> getGOListByIds(String goIds) throws Exception{
//		return getGOListByIds(Utils.str2ArrayList(goIds));
//	}
//	public static List<GO> getGOListByIds(ArrayList<String> goIds) throws Exception{
//		List<GO> gos = new ArrayList<GO>();
//		List<String> line;
//		Utils.connect();
//		PreparedQuery sqlPrepQuery = Utils.dbConn.createSQLPrepQuery("select * from go_info where id = ? ");
//		ResultList resultList;
//		for(String id: goIds) {
//			sqlPrepQuery.setParams(id);
//			resultList = sqlPrepQuery.run(); 
//			for(int i=0;i<resultList.getNumberOfRegisters();i++) {
//				line = resultList.getRowByIndex(i);
//				gos.add(new GO(line.get(0),line.get(1),line.get(2),line.get(3),line.get(4),line.get(5),line.get(6),line.get(7)));
//			}
//		}
//		sqlPrepQuery.close();
//		Utils.disconnect();
//		return gos;
//	}
	/*****************************************************
	 * ***************************************************
	 * END FACTORY METHODS
	 * ***************************************************
	******************************************************/
	
//	public String getGoId() {
//		return id;
//	}
//
//	public void setGoId(String goId) {
//		this.id = goId;
//	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCoherence_index() {
		return coherence_index;
	}

	public void setCoherence_index(String coherence_index) {
		this.coherence_index = coherence_index;
	}
	

	/**
	 * @return the mIN_LEVEL
	 */
	public static int getMIN_LEVEL() {
		return MIN_LEVEL;
	}

	/**
	 * @param directNumberOfGenes the directNumberOfGenes to set
	 */
	public void setNumberOfGenes(int numberOfGenes) {
		this.directNumberOfGenes = numberOfGenes;
	}

	/**
	 * @return the directNumberOfGenes
	 */
	public int getNumberOfGenes() {
		return directNumberOfGenes;
	}

	/**
	 * @param parents the parents to set
	 */
	public void setParents(FeatureList<GO> parents) {
		this.parents = parents;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(FeatureList<GO> children) {
		this.children = children;
	}
}

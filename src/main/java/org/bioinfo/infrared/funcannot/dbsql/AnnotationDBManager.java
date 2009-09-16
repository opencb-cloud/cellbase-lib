package org.bioinfo.infrared.funcannot.dbsql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bioinfo.commons.utils.ListUtils;
import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.funcannot.AnnotationItem;
import org.bioinfo.infrared.funcannot.Biocarta;
import org.bioinfo.infrared.funcannot.GO;
import org.bioinfo.infrared.funcannot.Kegg;
import org.bioinfo.infrared.funcannot.filter.BiocartaFilter;
import org.bioinfo.infrared.funcannot.filter.GOFilter;
import org.bioinfo.infrared.funcannot.filter.KeggFilter;

public class AnnotationDBManager extends DBManager {
	
	public static final String GET_GO_ANNOTATION = "SELECT gpr.gene_stable_id, gpr.acc FROM go_propagated gpr ";
	public static final String GET_GO_ANNOTATION_BY_NAMESPACE = "SELECT gpr.gene_stable_id, gpr.acc FROM go_info gi, go_propagated gpr WHERE gpr.acc=gi.acc and gi.namespace = ? group by gpr.gene_stable_id, gpr.acc";
	public static final String GET_GO_ANNOTATION_CONSTRAINT = "SELECT gpr.gene_stable_id, gpr.acc FROM go_info gi, go_propagated gpr WHERE gpr.acc=gi.acc and gi.namespace = ? ";
	public static final String GET_GO_ANNOTATION_CONSTRAINT_FILTERED = "SELECT gpr.gene_stable_id, gpr.acc FROM go_info gi, go_propagated gpr WHERE gpr.acc=gi.acc ";
	public static final String GET_GO_ANNOTATION_CONSTRAINT_BY_IDS = "select gi.* from transcript2xref tx1, transcript2xref tx2, xref x1, xref x2, go_info gi where x1.display_id = ? and x1.xref_id=tx1.xref_id and tx1.transcript_id=tx2.transcript_id and tx2.xref_id=x2.xref_id and x2.dbname_id=15 and x2.display_id=gi.acc ";
	public static final String GET_GO_ANNOTATION_CONSTRAINT_BY_IDS_PROPAGATED = "select gi.* from transcript2xref tx1, transcript2xref tx2, xref x1, xref x2, go_info gi, go_parent gp where x1.display_id = ? and x1.xref_id=tx1.xref_id and tx1.transcript_id=tx2.transcript_id and tx2.xref_id=x2.xref_id and x2.dbname_id=15 and x2.display_id=gp.child_acc and gp.parent_acc=gi.acc ";
	public static final String GET_KEGG_ANNOTATION = "SELECT g.stable_id, x.display_id FROM xref x, transcript t, gene g, transcript2xref tx, dbname db  WHERE db.dbname = 'kegg' and db.dbname_id=x.dbname_id and x.xref_id=tx.xref_id and tx.transcript_id=t.transcript_id and t.gene_id=g.gene_id group by g.stable_id, x.display_id";
	public static final String GET_KEGG_ANNOTATION_BY_IDS = "select x2.display_id, x2.description from transcript2xref tx1, transcript2xref tx2, xref x1, xref x2, dbname db  where x1.display_id = ? and x1.xref_id=tx1.xref_id and tx1.transcript_id=tx2.transcript_id and tx2.xref_id=x2.xref_id and x2.dbname_id=db.dbname_id and db.dbname='kegg' ";
	public static final String GET_BIOCARTA_ANNOTATION_BY_IDS = "select x2.display_id, x2.description from transcript2xref tx1, transcript2xref tx2, xref x1, xref x2, dbname db  where x1.display_id = ? and x1.xref_id=tx1.xref_id and tx1.transcript_id=tx2.transcript_id and tx2.xref_id=x2.xref_id and x2.dbname_id=db.dbname_id and db.dbname='biocarta' ";
	
	public AnnotationDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	
//	@SuppressWarnings("unchecked")
//	public AnnotationList getGOAnnotation() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new AnnotationList((List<AnnotationItem>)getFeatureList(GET_GO_ANNOTATION, new BeanArrayListHandler(AnnotationItem.class)).getElements());
//	}
//	
//	@SuppressWarnings("unchecked")
//	public AnnotationList getGOAnnotation(String namespace) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new AnnotationList((List<AnnotationItem>)getFeatureList(GET_GO_ANNOTATION_BY_NAMESPACE, namespace, new BeanArrayListHandler(AnnotationItem.class)).getElements());
//	}
//
	

	public FeatureList<AnnotationItem> getGOAnnotation() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getGOAnnotation(new GOFilter("biological_process"));
	}
	
	public FeatureList<AnnotationItem> getGOAnnotation(List<String> ids) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getGOAnnotation(ids, new GOFilter("biological_process"));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<AnnotationItem> getGOAnnotation(GOFilter goFilter) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new AnnotationList((List<AnnotationItem>)getFeatureList(GET_GO_ANNOTATION_CONSTRAINT_FILTERED + goFilter.getWhereClause("gi.")+" group by gpr.gene_stable_id, gpr.acc", new BeanArrayListHandler(AnnotationItem.class)).getElements());
		return getFeatureList(GET_GO_ANNOTATION_CONSTRAINT_FILTERED + goFilter.getWhereClause("gi.")+" group by gpr.gene_stable_id, gpr.acc", new BeanArrayListHandler(AnnotationItem.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<AnnotationItem> getGOAnnotation(List<String> ids, GOFilter goFilter) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
		goFilter.setUseNumberOfGenes(false);
		FeatureList<AnnotationItem> al = new FeatureList<AnnotationItem>(ids.size());
		FeatureList<GO> gos;
		// remove duplicated ids
		ids = ListUtils.unique(ids);
		getRosettaDBConnector().getDbConnection().setAutoConnectAndDisconnect(false);
		getRosettaDBConnector().getDbConnection().connect();
		
		String sqlQuery;
		if(goFilter.isPropagated()) {
			sqlQuery = GET_GO_ANNOTATION_CONSTRAINT_BY_IDS_PROPAGATED + goFilter.getWhereClause("gi.")+" group by gi.acc";
		}else {
			sqlQuery = GET_GO_ANNOTATION_CONSTRAINT_BY_IDS + goFilter.getWhereClause("gi.")+" group by gi.acc";
		}
		
		System.out.println(sqlQuery);
		
		for(String id: ids) {
			gos = getFeatureListById(sqlQuery, id, new BeanArrayListHandler(GO.class));
			for(GO go: gos) {
				al.add(new AnnotationItem(id, go.getId()));
			}
		}
		getRosettaDBConnector().getDbConnection().disconnect();
		getRosettaDBConnector().getDbConnection().setAutoConnectAndDisconnect(true);
		return filterByNumberOfRepeats(al, goFilter.getMinNumberGenes(), goFilter.getMaxNumberGenes());
	}
	
	
	
	@SuppressWarnings("unchecked")
	public FeatureList<AnnotationItem> getKeggAnnotation(KeggFilter keggFilter) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new AnnotationList((List<AnnotationItem>)getFeatureList(GET_KEGG_ANNOTATION, new BeanArrayListHandler(AnnotationItem.class)).getElements());
		return getFeatureList(GET_KEGG_ANNOTATION, new BeanArrayListHandler(AnnotationItem.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<AnnotationItem> getKeggAnnotation(List<String> ids, KeggFilter keggFilter) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		keggFilter.setUseNumberOfGenes(false);
		FeatureList<AnnotationItem> al = new FeatureList<AnnotationItem>(ids.size());
		FeatureList<Kegg> keggs;
		ids = ListUtils.unique(ids);
		getRosettaDBConnector().getDbConnection().setAutoConnectAndDisconnect(false);
		getRosettaDBConnector().getDbConnection().connect();
		for(String id: ids) {
			keggs = getFeatureListById(GET_KEGG_ANNOTATION_BY_IDS, id, new BeanArrayListHandler(Kegg.class));
			for(Kegg kegg: keggs) {
				al.add(new AnnotationItem(id, kegg.getId()));
			}
		}
		getRosettaDBConnector().getDbConnection().disconnect();
		getRosettaDBConnector().getDbConnection().setAutoConnectAndDisconnect(true);
		return filterByNumberOfRepeats(al, keggFilter.getMinNumberGenes(), keggFilter.getMaxNumberGenes());
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<AnnotationItem> getBiocartaAnnotation(List<String> ids, BiocartaFilter biocartaFilter) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		biocartaFilter.setUseNumberOfGenes(false);
		FeatureList<AnnotationItem> al = new FeatureList<AnnotationItem>(ids.size());
		FeatureList<Biocarta> biocartas;
		ids = ListUtils.unique(ids);
		getRosettaDBConnector().getDbConnection().setAutoConnectAndDisconnect(false);
		getRosettaDBConnector().getDbConnection().connect();
		for(String id: ids) {
			biocartas = getFeatureListById(GET_BIOCARTA_ANNOTATION_BY_IDS, id, new BeanArrayListHandler(Biocarta.class));
			for(Biocarta biocarta: biocartas) {
				al.add(new AnnotationItem(id, biocarta.getId()));
			}
		}
		getRosettaDBConnector().getDbConnection().disconnect();
		getRosettaDBConnector().getDbConnection().setAutoConnectAndDisconnect(true);
		return filterByNumberOfRepeats(al, biocartaFilter.getMinNumberGenes(), biocartaFilter.getMaxNumberGenes());
	}
	
	
	
	private FeatureList<AnnotationItem> filterByNumberOfRepeats(FeatureList<AnnotationItem> al, int numMin, int numMax) {
		FeatureList<AnnotationItem> result = new FeatureList<AnnotationItem>(100);
		HashMap<String, Integer> visited = new HashMap<String, Integer>();
		HashMap<String, ArrayList<String>> goToGenes = new HashMap<String, ArrayList<String>>();
		for(AnnotationItem ai: al) {
			if(visited.containsKey(ai.getFunctionalTermId())) {
				visited.put(ai.getFunctionalTermId(), visited.get(ai.getFunctionalTermId()) +1);
			}else {
				visited.put(ai.getFunctionalTermId(), 1);
				goToGenes.put(ai.getFunctionalTermId(), new ArrayList<String>());
			}
			if(!goToGenes.get(ai.getFunctionalTermId()).contains(ai.getId())) {
				goToGenes.get(ai.getFunctionalTermId()).add(ai.getId());
			}
			
		}
		for(String key: visited.keySet()) {
			if(visited.get(key) >= numMin && visited.get(key) <= numMax) {
				for(String id: goToGenes.get(key)) {
					result.add(new AnnotationItem(id, key));
				}
			}
		}
		return result;
	}

}


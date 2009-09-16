package org.bioinfo.infrared.funcannot.dbsql;

import java.sql.SQLException;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.db.handler.BeanHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.funcannot.GO;

public class GODBManager extends DBManager {
	
	private static final String NAMESPACE_CONSTRAINT= " gi.namespace = ? ";
//	private static final String LEVEL_CONSTRAINT= " gi.level between ? and ?  ";
//	private static final String NUMBER_OF_GENES_CONSTRAINT= " gi.number_genes between ? and ? ";
	public static final String GET_GO_BY_ACC= "SELECT gi.* FROM go_info gi WHERE gi.acc = ? ";
	public static final String GET_GO= "SELECT gi.* FROM go_info gi WHERE ";
	public static final String GET_GO_PARENTS= "SELECT gi.* FROM go_parent gp, go_info gi WHERE gp.child_acc = ? and gp.distance > 0 and gp.parent_acc=gi.acc group by gp.parent_acc ";
	
	public GODBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	public GO getByAccesion(String acc) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return (GO) getFeatureById(GET_GO_BY_ACC, acc, new BeanHandler(GO.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<GO> getParentsByAccesion(String acc) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new GOList((List<GO>)getFeatureList(GET_GO_PARENTS, acc, new BeanArrayListHandler(GO.class)).getElements());
		return getFeatureListById(GET_GO_PARENTS, acc, new BeanArrayListHandler(GO.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<GO> getAllByNamespace(String namespace) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new GOList((List<GO>)getFeatureList(GET_GO+NAMESPACE_CONSTRAINT, namespace, new BeanArrayListHandler(GO.class)).getElements());
		return getFeatureListById(GET_GO+NAMESPACE_CONSTRAINT, namespace, new BeanArrayListHandler(GO.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<GO> getAllByLevel(int min, int max) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new GOList((List<GO>)getFeatureList(GET_GO+" gi.level between "+min+" and "+max+" group by gi.acc", new BeanArrayListHandler(GO.class)).getElements());
		return getFeatureList(GET_GO+" gi.level between "+min+" and "+max+" group by gi.acc", new BeanArrayListHandler(GO.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<GO> getAllByNumberOfGenes(int minNumGenes, int maxNumGenes) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new GOList((List<GO>)getFeatureList(GET_GO+" gi.propagated_number_genes between "+minNumGenes+" and "+maxNumGenes+" group by gi.acc", new BeanArrayListHandler(GO.class)).getElements());
		return getFeatureList(GET_GO+" gi.propagated_number_genes between "+minNumGenes+" and "+maxNumGenes+" group by gi.acc", new BeanArrayListHandler(GO.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<GO> getAllByFilter(String namespace, int min, int max, int minNumGenes, int maxNumGenes) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new GOList((List<GO>)getFeatureList(GET_GO+NAMESPACE_CONSTRAINT+" and gi.level >= "+min+" and gi.level <= "+max+" and gi.propagated_number_genes >= "+minNumGenes+" and gi.propagated_number_genes <= "+maxNumGenes+" group by gi.acc", namespace, new BeanArrayListHandler(GO.class)).getElements());
		return getFeatureListById(GET_GO+NAMESPACE_CONSTRAINT+" and gi.level >= "+min+" and gi.level <= "+max+" and gi.propagated_number_genes >= "+minNumGenes+" and gi.propagated_number_genes <= "+maxNumGenes+" group by gi.acc", namespace, new BeanArrayListHandler(GO.class));
	}
	
}

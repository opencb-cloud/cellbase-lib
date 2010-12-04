package org.bioinfo.infrared.core;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.db.handler.BeanHandler;
import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.common.DBManager;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.feature.Gene;

public class GeneDBManager extends DBManager{

	private static final String SELECT_FIELDS = " g.stable_id,g.chromosome,g.start,g.end,g.strand,g.biotype ";
	
	public static final String GET_ALL_ENSEMBL_IDS = "SELECT stable_id FROM gene ";
	public static final String GET_ALL_ENSEMBL_GENES = "SELECT " + SELECT_FIELDS + "FROM gene g ";
	public static final String GET_GENE_BY_ENSEMBL_GENE_ID = "SELECT " + SELECT_FIELDS + " FROM gene g WHERE g.stable_id = ? ";
	public static final String GET_GENE_BY_ENSEMBL_TRANSCRIPT_ID = "SELECT " + SELECT_FIELDS + " FROM gene g, transcript t WHERE t.stable_id=? and t.gene_id=g.gene_id ";
	public static final String GET_GENELIST_BY_ID = "select " + SELECT_FIELDS + " from xref x, transcript2xref tx, transcript t, gene g where x.display_id = ? and x.xref_id = tx.xref_id and tx.transcript_id = t.transcript_id and t.gene_id = g.gene_id group by g.stable_id";
	public static final String GET_GENELIST_BY_SNP_ID = "SELECT " + SELECT_FIELDS + " FROM gene g, transcript t, snp2transcript st, snp s WHERE s.name = ? and s.snp_id=st.snp_id and st.transcript_id=t.transcript_id and t.gene_id=g.gene_id group by g.stable_id";
	public static final String GET_SEQUENCE_BY_ENSEMBL_ID = "SELECT gs.sequence FROM gene g, gene_sequence gs WHERE g.stable_id = ? and g.gene_id=gs.gene_id ";
	
	public GeneDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}


	@SuppressWarnings("unchecked")
	public List<String> getAllEnsemblIds() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getStringList(GET_ALL_ENSEMBL_IDS);
	}
	
	public Gene getByEnsemblId(String ensemblId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return (Gene)getFeatureById(GET_GENE_BY_ENSEMBL_GENE_ID, ensemblId, new BeanHandler(Gene.class));
	}
	
	public Gene getByEnsemblTranscriptId(String transcriptId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return (Gene)getFeatureById(GET_GENE_BY_ENSEMBL_TRANSCRIPT_ID, transcriptId, new BeanHandler(Gene.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Gene> getAll() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList(GET_ALL_ENSEMBL_GENES, new BeanArrayListHandler(Gene.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Gene> getAllByBiotype(String biotype) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList(GET_ALL_ENSEMBL_GENES + " where biotype = '"+biotype+"' ", new BeanArrayListHandler(Gene.class));
	}
	
	@SuppressWarnings("unchecked")
	@Deprecated
	public FeatureList<Gene> getAllByLocation(String chromosome, int start, int end) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList(GET_ALL_ENSEMBL_GENES + " where g.chromosome = '"+chromosome+"' and g.start >= "+start +" and g.end <= "+end+" ", new BeanArrayListHandler(Gene.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Gene> getAllByRegion(String chromosome, int start, int end) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList(GET_ALL_ENSEMBL_GENES + " where g.chromosome = '"+chromosome+"' and g.start >= "+start +" and g.end <= "+end+" ", new BeanArrayListHandler(Gene.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Gene> getAllByRegion(String chromosome, int start, int end, List<String> biotypes) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		FeatureList<Gene> allGenes = getFeatureList(GET_ALL_ENSEMBL_GENES + " where g.chromosome = '"+chromosome+"' and g.start >= "+start +" and g.end <= "+end+" ", new BeanArrayListHandler(Gene.class));
		if(biotypes != null) {
			FeatureList<Gene> filteredGenes = new FeatureList<Gene>();
			Set<String> biotypesSet = new HashSet<String>(biotypes);
			for(Gene gene: allGenes) {
				if(biotypesSet.contains(gene.getBiotype())) {
					filteredGenes.add(gene);
				}
			}
			return filteredGenes;
		}else {
			return allGenes;
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public FeatureList<Gene> getAllByExternalId(String id) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById(GET_GENELIST_BY_ID, id, new BeanArrayListHandler(Gene.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<Gene>> getAllByExternalIds(List<String> ids) throws Exception {
		List<FeatureList<Gene>> featList = getListOfFeatureListByIds(GET_GENELIST_BY_ID,ids, new BeanArrayListHandler(Gene.class));
		return featList;
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Gene> getGeneListBySNP(String snpId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById(GET_GENELIST_BY_SNP_ID, snpId, new BeanArrayListHandler(Gene.class));
	}
	
	public String getSequenceByEnsemblId(String ensemblId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return (String) dBConnector.getDbConnection().createSQLPrepQuery(GET_SEQUENCE_BY_ENSEMBL_ID).execute(ensemblId, new BeanHandler(String.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Gene> test(String snpId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById(GET_GENELIST_BY_SNP_ID, snpId, new BeanArrayListHandler(Gene.class));
	}
}

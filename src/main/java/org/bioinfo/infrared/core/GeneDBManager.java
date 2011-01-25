package org.bioinfo.infrared.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bioinfo.db.api.PreparedQuery;
import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.db.handler.BeanHandler;
import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.common.DBManager;
import org.bioinfo.infrared.core.common.Feature;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.feature.Gene;
import org.bioinfo.infrared.core.feature.Position;
import org.bioinfo.infrared.core.feature.Region;

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

	public List<String> getAllEnsemblIds() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getStringList(GET_ALL_ENSEMBL_IDS);
	}

	@SuppressWarnings("unchecked")
	public FeatureList<Gene> getAll() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList(GET_ALL_ENSEMBL_GENES, new BeanArrayListHandler(Gene.class));
	}

	public FeatureList<Gene> getByEnsemblId(List<String> ids) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListByIds(GET_GENE_BY_ENSEMBL_GENE_ID, ids, new BeanHandler(Gene.class));
	}

	public FeatureList<Gene> getByEnsemblTranscriptId(List<String> ids) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListByIds(GET_GENE_BY_ENSEMBL_TRANSCRIPT_ID, ids, new BeanArrayListHandler(Gene.class));
	}


	@SuppressWarnings("unchecked")
	public List<FeatureList<Gene>> getAllByExternalIds(List<String> ids) throws Exception {
		List<FeatureList<Gene>> featList = getListOfFeatureListByIds(GET_GENELIST_BY_ID, ids, new BeanArrayListHandler(Gene.class));
		return featList;
	}

	@SuppressWarnings("unchecked")
	public FeatureList<Gene> getAllByBiotype(String biotype) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList(GET_ALL_ENSEMBL_GENES + " where biotype = '"+biotype+"' ", new BeanArrayListHandler(Gene.class));
	}



	@SuppressWarnings("unchecked")
	public FeatureList<Gene> getAllByPosition(String chromosome, int position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList(GET_ALL_ENSEMBL_GENES + " where g.chromosome = '"+chromosome+"' and g.start <= "+ position +" and g.end >= "+position+" ", new BeanArrayListHandler(Gene.class));
	}

	public FeatureList<Gene> getAllByPosition(Position position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		if(position != null) {
			return getAllByPosition(position.getChromosome(), position.getPosition());	
		}else {
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<FeatureList<Gene>> getAllByPositions(List<Position> positions) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		List<FeatureList<Gene>> geneList = null;
		if(positions != null) {
			geneList = new ArrayList<FeatureList<Gene>>(positions.size());
			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_ALL_ENSEMBL_GENES + " where g.chromosome = ? and g.start <= ? and g.end >= ? ");
			FeatureList<Gene> geneFeatureList;
			for(Position position: positions) {
				if(position != null) {
					prepQuery.setParams(position.getChromosome(), ""+position.getPosition());
					geneFeatureList = new FeatureList((List<Feature>)prepQuery.execute(new BeanArrayListHandler(Gene.class)));
					if(geneFeatureList != null && geneFeatureList.size() > 0) {
						geneList.add(geneFeatureList);
					}else {
						geneList.add(null);
					}
				}else {
					geneList.add(null);
				}
			}
			prepQuery.close();
		}
		return geneList;
	}



	@SuppressWarnings("unchecked")
	public FeatureList<Gene> getAllByRegion(String chromosome) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList(GET_ALL_ENSEMBL_GENES + " where g.chromosome = '"+chromosome+"' ", new BeanArrayListHandler(Gene.class));
	}

	@SuppressWarnings("unchecked")
	public FeatureList<Gene> getAllByRegion(String chromosome, int start) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList(GET_ALL_ENSEMBL_GENES + " where g.chromosome = '"+chromosome+"' and g.end >= " + start + " ", new BeanArrayListHandler(Gene.class));
	}

	@SuppressWarnings("unchecked")
	public FeatureList<Gene> getAllByRegion(String chromosome, int start, int end) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList(GET_ALL_ENSEMBL_GENES + " where g.chromosome = '"+chromosome+"' and g.end >= "+start +" and g.start <= "+end+" ", new BeanArrayListHandler(Gene.class));
	}

	@SuppressWarnings("unchecked")
	public FeatureList<Gene> getAllByRegion(String chromosome, int start, int end, List<String> biotypes) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		FeatureList<Gene> allGenes = getFeatureList(GET_ALL_ENSEMBL_GENES + " where g.chromosome = '"+chromosome+"' and g.end >= "+start +" and g.start <= "+end+" ", new BeanArrayListHandler(Gene.class));
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

	public FeatureList<Gene> getAllByRegion(Region region) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		if(region != null) {
			return getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd());
		}else {
			return null;
		}
	}

	public FeatureList<Gene> getAllByRegion(Region region, List<String> biotypes) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		if(region != null) {
			return getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd(), biotypes);
		}else {
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<FeatureList<Gene>> getAllByRegions(List<Region> regions) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		List<FeatureList<Gene>> snpList = null;
		if(regions != null) {
			snpList = new ArrayList<FeatureList<Gene>>(regions.size());
			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_ALL_ENSEMBL_GENES + " where g.chromosome = ? and g.end >= ? and g.start <= ? ");
			FeatureList<Gene> snpFeatureList;
			for(Region region: regions) {
				if(region != null) {
					prepQuery.setParams(region.getChromosome(), ""+region.getStart(), ""+region.getEnd());
					snpFeatureList = new FeatureList((List<Feature>)prepQuery.execute(new BeanArrayListHandler(Gene.class)));
					if(snpFeatureList != null && snpFeatureList.size() > 0) {
						snpList.add(snpFeatureList);
					}else {
						snpList.add(null);
					}
				}else {
					snpList.add(null);
				}
			}
			prepQuery.close();
		}
		return snpList;
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

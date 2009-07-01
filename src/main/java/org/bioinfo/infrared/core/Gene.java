package org.bioinfo.infrared.core;

import java.util.HashMap;

import org.bioinfo.infrared.common.feature.CodingFeature;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.core.dbsql.ExonDBManager;
import org.bioinfo.infrared.core.dbsql.GeneDBManager;
import org.bioinfo.infrared.core.dbsql.TranscriptDBManager;
import org.bioinfo.infrared.core.dbsql.XRefDBManager;
import org.bioinfo.infrared.variation.SNP;

public class Gene extends CodingFeature{

//	private String id;
	private String biotype;
	private String extenalSymbol;
	
	private FeatureList<Transcript> transcripts;
	private FeatureList<Exon> exons;
	private FeatureList<SNP> snps;
	private HashMap<String, FeatureList<XRef>> xrefs;
	
	public Gene(String geneId, String chromosome, Integer start, Integer end, String strand, String biotype) {
		super(geneId, chromosome, start, end, strand);
		this.setBiotype(biotype);
	}
	
	public Gene(String geneId, String chromosome, Integer start, Integer end, String strand, String biotype, String sequence) {
		super(geneId, chromosome, start, end, strand, sequence);
		this.setBiotype(biotype);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getId()).append("\t").append(super.toString()).append("\t").append(biotype);
		return sb.toString();
	}

	/**
	 * @return the transcripts
	 */
	public FeatureList<Transcript> getTranscripts() {
		if(transcripts == null) {
			TranscriptDBManager tf = new TranscriptDBManager(getRosettaDBConnector());
			try {
				transcripts = tf.getTranscriptListById(getId());
//				transcripts = tf.getTranscriptListById(id).getElements();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return transcripts;
	}

	/**
	 * @return the transcripts
	 */
	public FeatureList<Exon> getExons() {
		if(exons == null) {
			ExonDBManager ef = new ExonDBManager(getRosettaDBConnector());
			try {
				exons = ef.getAllById(getId());
//				exons = ef.getExonListById(id).getElements();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return exons;
	}
	
	/**
	 * @return the snps
	 */
	public FeatureList<SNP> getSnps() {
		if(snps == null) {
			
		}
		return snps;
	}
	
	/**
	 * @return the xrefs
	 */
	public HashMap<String,FeatureList<XRef>> getXrefs() {
		if(xrefs == null) {
			XRefDBManager xref = new XRefDBManager(getRosettaDBConnector());
		}
		return xrefs;
	}

	
	@Override
	public String getSequence() {
		if(sequence == null) {
			GeneDBManager gf = new GeneDBManager(getRosettaDBConnector());
			try {
				sequence = gf.getSequenceByEnsemblId(getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return sequence;
	}

	
	/**************************************************
	 * ************************************************
	 * *******	GETTERS AND SETTERS	*******************
	 * ************************************************
	 **************************************************/
	


	/**
	 * @param biotype the biotype to set
	 */
	public void setBiotype(String biotype) {
		this.biotype = biotype;
	}


	/**
	 * @return the biotype
	 */
	public String getBiotype() {
		return biotype;
	}

	/**
	 * @param transcripts the transcripts to set
	 */
	public void setTranscripts(FeatureList<Transcript> transcripts) {
		this.transcripts = transcripts;
	}

	/**
	 * @param exons the exons to set
	 */
	public void setExons(FeatureList<Exon> exons) {
		this.exons = exons;
	}

	/**
	 * @param snps the snps to set
	 */
	public void setSnps(FeatureList<SNP> snps) {
		this.snps = snps;
	}

	/**
	 * @param xrefs the xrefs to set
	 */
	public void setXrefs(HashMap<String,FeatureList<XRef>> xrefs) {
		this.xrefs = xrefs;
	}

	/**
	 * @param extenalSymbol the extenalSymbol to set
	 */
	public void setExtenalSymbol(String extenalSymbol) {
		this.extenalSymbol = extenalSymbol;
	}

	/**
	 * @return the extenalSymbol
	 */
	public String getExtenalSymbol() {
		return extenalSymbol;
	}

}

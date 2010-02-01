package org.bioinfo.infrared.regulatory;

import org.bioinfo.infrared.common.feature.RegulatoryFeature;


public class Triplex extends RegulatoryFeature {

	private String geneStableId;
	private int relativeStart;
	private int relativeEnd;
	private int length;
	
	
	public Triplex(String id) {
		super(id);
	}

	public Triplex(Integer id, String geneStableId, Integer relativeStart, Integer relativeEnd, String chromosome, Integer absoluteStart, Integer absoluteEnd, String strand, Integer length, String sequence){
		super(""+id, chromosome, absoluteStart, absoluteEnd, strand, sequence);
		this.geneStableId = geneStableId;
		this.relativeStart = relativeStart;
		this.relativeEnd = relativeEnd;
		this.length = length;
	}
	
	@Override
	public String toString(){
		if(length > 20) {
			return id+"\t"+geneStableId+"\t"+relativeStart+"\t"+relativeEnd+"\t"+chromosome+"\t"+start+"\t"+end+"\t"+strand+"\t"+length+"\t"+sequence.substring(0, 20)+"...";
		}else {
			return id+"\t"+geneStableId+"\t"+relativeStart+"\t"+relativeEnd+"\t"+chromosome+"\t"+start+"\t"+end+"\t"+strand+"\t"+length+"\t"+sequence;
		}
	
	}

	/**
	 * @return the geneStableId
	 */
	public String getGeneId() {
		return geneStableId;
	}


	/**
	 * @param geneStableId the geneStableId to set
	 */
	public void setGeneId(String geneId) {
		this.geneStableId = geneId;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}


	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}


	/**
	 * @return the relativeStart
	 */
	public int getRelativeStart() {
		return relativeStart;
	}


	/**
	 * @param relativeStart the relativeStart to set
	 */
	public void setRelativeStart(int relativeStart) {
		this.relativeStart = relativeStart;
	}


	/**
	 * @return the relativeEnd
	 */
	public int getRelativeEnd() {
		return relativeEnd;
	}


	/**
	 * @param relativeEnd the relativeEnd to set
	 */
	public void setRelativeEnd(int relativeEnd) {
		this.relativeEnd = relativeEnd;
	}

}

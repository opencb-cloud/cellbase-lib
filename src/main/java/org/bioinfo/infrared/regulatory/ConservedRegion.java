package org.bioinfo.infrared.regulatory;

import org.bioinfo.infrared.common.feature.RegulatoryFeature;

public class ConservedRegion extends RegulatoryFeature {

	private int length;
	
	public ConservedRegion(Integer id, String chromosome, Integer start, Integer end, String strand, Integer length, String sequence){
		super(""+id, chromosome, start, end, strand, sequence);
		this.length = length;
	}
	
	@Override
	public String toString(){
		if(length > 20) {
			return id+"\t"+chromosome+"\t"+start+"\t"+end+"\t"+strand+"\t"+length+"\t"+sequence.substring(0, 20)+"...";
		}else {
			return id+"\t"+chromosome+"\t"+start+"\t"+end+"\t"+strand+"\t"+length+"\t"+sequence;
		}
		
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
	
	
}

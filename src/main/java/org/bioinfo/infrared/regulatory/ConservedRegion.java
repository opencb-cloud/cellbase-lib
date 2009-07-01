package org.bioinfo.infrared.regulatory;

import org.bioinfo.infrared.common.feature.RegulatoryFeature;

public class ConservedRegion extends RegulatoryFeature {

	private int length;
	
	public ConservedRegion(Integer id, String chromosome, Integer start, Integer end, String strand, String sequence, Integer length){
		super(""+id, chromosome, start, end, strand, sequence);
		this.length = length;
	}
	
	@Override
	public String toString(){
		if(length > 20) {
			return id+"\t"+chromosome+"\t"+start+"\t"+end+"\t"+strand+"\t"+sequence.substring(0, 20)+"...\t"+length;
		}else {
			return id+"\t"+chromosome+"\t"+start+"\t"+end+"\t"+strand+"\t"+sequence+"\t"+length;
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

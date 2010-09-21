package org.bioinfo.infrared.variation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bioinfo.db.api.PreparedQuery;
import org.bioinfo.db.handler.MatrixHandler;
import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.common.DBManager;
import org.bioinfo.infrared.core.feature.Position;
import org.bioinfo.infrared.core.variation.TranscriptConsequenceType;

public class TranscriptConsequenceTypeDBManager extends DBManager {

	public TranscriptConsequenceTypeDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	
//	Select returns:
//	| ENSG00000166086 | ENST00000441717 | 133938820 | 134021636 |           133938979 |         134019076 | 1      | ENSE00001099652 | 134015841 | 134015940 |           134015841 |         134015940 |     0 | GTGTTCACTGCTGTTCACAAGGACGACTCTGGGCAGTACTACTGCATTGCTTCCAATGACGCAGGCTCAGCCAGGTGTGAGGAGCAGGAGATGGAAGTCT
//	| ENSG00000166086 | ENST00000441717 | 133938820 | 134021636 |           133938979 |         134019076 | 1      | ENSE00001099651 | 134018442 | 134018571 |           134018442 |         134018571 |     1 | ATGACCTGAACATTGGCGGAATTATTGGGGGGGTTCTGGTTGTCCTTGCTGTACTGGCCCTGATCACGTTGGGCATCTGCTGTGCATACAGACGTGGCTACTTCATCAACAATAAACAGGATGGAGAAAG
//	| ENSG00000166086 | ENST00000441717 | 133938820 | 134021636 |           133938979 |         134019076 | 1      | ENSE00001099650 | 134018659 | 134018713 |           134018659 |         134018713 |     2 | TTACAAGAACCCAGGGAAACCAGATGGAGTTAACTACATCCGCACTGACGAGGAG
	public List<List<TranscriptConsequenceType>> getConsequenceType(Position position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getConsequenceType(Arrays.asList(position));
	}
	
	public List<List<TranscriptConsequenceType>> getConsequenceType(List<Position> positions) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		List<List<TranscriptConsequenceType>> transcriptConsequenceTypeList = new ArrayList<List<TranscriptConsequenceType>>(positions.size());
//		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery("select g.stable_id, g.start, g.end, g.strand, t.stable_id, e.stable_id, e.start, e.end, et.coding_region_start, et.coding_region_end, et.phase, es.sequence from gene g, transcript t, exon2transcript et, exon e, exon_sequence es where g.position_prefix= ? and g.start-5000 <= ? and g.end+5000 >= ? and g.gene_id=t.gene_id and t.transcript_id=et.transcript_id and et.exon_id=e.exon_id and e.exon_id=es.exon_id");	
		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery("select g.stable_id, t.stable_id, t.start, t.end, t.coding_region_start, t.coding_region_end, t.strand, e.stable_id, e.start, e.end, et.coding_region_start, et.coding_region_end, et.phase, es.sequence from gene g, transcript t, transcript_position_prefix tpp, exon2transcript et, exon e, exon_sequence es where tpp.position_prefix = ? and tpp.transcript_id=t.transcript_id and t.start-5000 <= ? and t.end+5000 >= ? and g.gene_id=t.gene_id and t.transcript_id=et.transcript_id and et.exon_id=e.exon_id and e.exon_id=es.exon_id order by g.stable_id, t.stable_id, e.start");
		Object[][] result;
		List<TranscriptConsequenceType> transcriptConsquenceTypes = new ArrayList<TranscriptConsequenceType>();
		int transcriptStart, transcriptEnd;
		int exonStart, exonEnd;
		int codingRegionStart, codingRegionEnd;
		int strand;
		TranscriptConsequenceType upstream = null;
		TranscriptConsequenceType downstream = null;
		TranscriptConsequenceType fivePrimeUtr = null;
		TranscriptConsequenceType threePrimeUtr = null;
		TranscriptConsequenceType exonSpliceSite = null;
		TranscriptConsequenceType essentialSpliceSite = null;
		TranscriptConsequenceType intronic = null;
		TranscriptConsequenceType intergenic = null;
		
		String prevTranscript = "";
		int prevExonStart = 0;
		int prevExonEnd = 0;
		
		for(Position position: positions) {
			if(position.getPosition() >= 1000000) {
				prepQuery.setParams(position.getChromosome()+":"+String.valueOf(position.getPosition()).substring(0, 3), ""+position.getPosition(), ""+position.getPosition());
			}else {
				prepQuery.setParams("0:000", ""+position.getPosition(), ""+position.getPosition());
			}
			
			transcriptConsquenceTypes = new ArrayList<TranscriptConsequenceType>();
			upstream = null;
			downstream = null;
			fivePrimeUtr = null;
			threePrimeUtr = null;
			exonSpliceSite = null;
			essentialSpliceSite = null;
			intronic = null;
			intergenic = null;
			
			result = (Object[][])prepQuery.execute(new MatrixHandler());
			if(result != null && result.length > 0) {
				prevTranscript = "";
				for(int i=0; i<result.length; i++) {
					transcriptStart = Integer.parseInt(result[i][2].toString());
					transcriptEnd = Integer.parseInt(result[i][3].toString());
					codingRegionStart = Integer.parseInt(result[i][4].toString());
					codingRegionEnd = Integer.parseInt(result[i][5].toString());
					strand = Integer.parseInt(result[i][6].toString());
					exonStart = Integer.parseInt(result[i][8].toString());;
					exonEnd = Integer.parseInt(result[i][9].toString());;
				
					if(strand == 1) {
						if(position.getPosition() < transcriptStart && position.getPosition() > transcriptStart-5000) {
							upstream = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "UPSTREAM");
							continue;
						}
						if(position.getPosition() > transcriptEnd && position.getPosition() < transcriptEnd+5000) {
							downstream = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "DOWNSTREAM");
						}
						if(position.getPosition() >= transcriptStart && position.getPosition() < codingRegionStart) {
							fivePrimeUtr = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "5PRIME_UTR");
						}
						if(position.getPosition() > codingRegionEnd && position.getPosition() <= transcriptEnd) {
							threePrimeUtr = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "3PRIME_UTR");
						}
						
						
						if(exonEnd > codingRegionStart && exonEnd < codingRegionEnd && position.getPosition() >= exonEnd-3 && position.getPosition() <= exonEnd) {
							exonSpliceSite = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "SPLICE_SITE");
						}
						if(exonEnd > codingRegionStart && exonEnd < codingRegionEnd && position.getPosition() >= exonEnd+3 && position.getPosition() <= exonEnd+8) {
							exonSpliceSite = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "SPLICE_SITE");
							intronic = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "INTRONIC");
						}
						if(exonStart > codingRegionStart && exonStart < codingRegionEnd && position.getPosition() >= exonStart && position.getPosition() <= exonStart+3) {
							exonSpliceSite = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "SPLICE_SITE");
						}
						if(exonStart > codingRegionStart && exonStart < codingRegionEnd && position.getPosition() >= exonStart-8 && position.getPosition() <= exonStart-3) {
							exonSpliceSite = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "SPLICE_SITE");
							intronic = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "INTRONIC");
						}
						if(exonEnd > codingRegionStart && exonEnd < codingRegionEnd && position.getPosition() >= exonEnd+1 && position.getPosition() <= exonEnd+2) {
							essentialSpliceSite = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "ESSENTIAL_SPLICE_SITE");
							intronic = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "INTRONIC");
						}
						if(exonStart > codingRegionStart && exonStart < codingRegionEnd && position.getPosition() >= exonStart-2 && position.getPosition() <= exonStart-1) {
							essentialSpliceSite = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "ESSENTIAL_SPLICE_SITE");
							intronic = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "INTRONIC");
						}
						
						
						if(result[i][1].toString().equals(prevTranscript)) {
							if(position.getPosition() > codingRegionStart && position.getPosition() < codingRegionEnd && position.getPosition() > prevExonEnd && position.getPosition() < exonStart) {
								intronic = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "INTRONIC");
							}
						}else {
							prevTranscript = result[i][1].toString();
						}
						prevExonStart = exonStart;
						prevExonEnd = exonEnd;

						
					}else {
						if(position.getPosition() > transcriptEnd  && position.getPosition() < transcriptEnd+5000) {
							upstream = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "UPSTREAM");
						}
						if(position.getPosition() < transcriptStart && position.getPosition() > transcriptStart-5000) {
							downstream = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "DOWNSTREAM");
						}
						if(position.getPosition() > codingRegionEnd && position.getPosition() <= transcriptEnd) {
							fivePrimeUtr = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "5PRIME_UTR");
						}
						if(position.getPosition() >= transcriptStart && position.getPosition() < codingRegionStart) {
							threePrimeUtr = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "3PRIME_UTR");
						}
						
						
						if(exonEnd > codingRegionStart && exonEnd < codingRegionEnd && position.getPosition() >= exonEnd-3 && position.getPosition() <= exonEnd) {
							exonSpliceSite = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "SPLICE_SITE");
						}
						if(exonEnd > codingRegionStart && exonEnd < codingRegionEnd && position.getPosition() >= exonEnd+3 && position.getPosition() <= exonEnd+8) {
							exonSpliceSite = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "SPLICE_SITE");
							intronic = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "INTRONIC");
						}
						if(exonStart > codingRegionStart && exonStart < codingRegionEnd && position.getPosition() >= exonStart && position.getPosition() <= exonStart+3) {
							exonSpliceSite = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "SPLICE_SITE");
						}
						if(exonStart > codingRegionStart && exonStart < codingRegionEnd && position.getPosition() >= exonStart-8 && position.getPosition() <= exonStart-3) {
							exonSpliceSite = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "SPLICE_SITE");
							intronic = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "INTRONIC");
						}
						if(exonEnd > codingRegionStart && exonEnd < codingRegionEnd && position.getPosition() >= exonEnd+1 && position.getPosition() <= exonEnd+2) {
							essentialSpliceSite = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "ESSENTIAL_SPLICE_SITE");
							intronic = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "INTRONIC");
						}
						if(exonStart > codingRegionStart && exonStart < codingRegionEnd && position.getPosition() >= exonStart-2 && position.getPosition() <= exonStart-1) {
							essentialSpliceSite = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "ESSENTIAL_SPLICE_SITE");
							intronic = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "INTRONIC");
						}
						
						if(result[i][1].toString().equals(prevTranscript)) {
							if(position.getPosition() > codingRegionStart && position.getPosition() < codingRegionEnd && position.getPosition() > prevExonEnd && position.getPosition() < exonStart) {
								intronic = new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "INTRONIC");
							}
						}else {
							prevTranscript = result[i][1].toString();
						}
						prevExonStart = exonStart;
						prevExonEnd = exonEnd;
						
					}
					
//					transcriptConsquenceTypes.add(new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), result[i][2].toString()));
				}
			}else {
				intergenic = new TranscriptConsequenceType("", "", "INTERGENIC");
			}
			
			
			if(upstream != null) {
				transcriptConsquenceTypes.add(upstream);	
			}
			if(downstream != null) {
				transcriptConsquenceTypes.add(downstream);	
			}
			if(fivePrimeUtr != null) {
				transcriptConsquenceTypes.add(fivePrimeUtr);	
			}
			if(threePrimeUtr != null) {
				transcriptConsquenceTypes.add(threePrimeUtr);	
			}
			if(exonSpliceSite != null) {
				transcriptConsquenceTypes.add(exonSpliceSite);	
			}
			if(essentialSpliceSite != null) {
				transcriptConsquenceTypes.add(essentialSpliceSite);	
			}
			if(intronic != null) {
				transcriptConsquenceTypes.add(intronic);	
			}
			if(intergenic != null) {
				transcriptConsquenceTypes.add(intergenic);	
			}
			
			
			transcriptConsequenceTypeList.add(transcriptConsquenceTypes);
		}
		
		prepQuery.close();
		return transcriptConsequenceTypeList;
	}
	
}

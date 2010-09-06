package org.bioinfo.infrared.variation.dbsql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bioinfo.db.api.PreparedQuery;
import org.bioinfo.db.handler.MatrixHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.core.Position;
import org.bioinfo.infrared.variation.TranscriptConsequenceType;

public class VariationPositionDBManager extends DBManager {

	public VariationPositionDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
//	Select returns:
//		| ENSG00000000003 | 99883667 | 99894988 | -1     | ENST00000373020 | ENSE00000673408 | 99888928 | 99889026 |            99888928 |          99889026 |     0 | ATTAAGAACAGCTTTAAGAATAATTATGAGAAGGCTTTGAAGCAGTATAACTCTACAGGAGATTATAGAAGCCATGCAGTAGACAAGATCCAAAATACG
//		| ENSG00000000003 | 99883667 | 99894988 | -1     | ENST00000373020 | ENSE00000673407 | 99888402 | 99888536 |            99888402 |          99888536 |     0 | TTGCATTGTTGTGGTGTCACCGATTATAGAGATTGGACAGATACTAATTATTACTCAGAAAAAGGATTTCCTAAGAGTTGCTGTAAACTTGAAGATTGTACTCCACAGAGAGATGCAGACAAAGTAAACAATGAA
//		| ENSG00000000003 | 99883667 | 99894988 | -1     | ENST00000373020 | ENSE00000401072 | 99887482 | 99887565 |            99887482 |          99887565 |     0 | GGTTGTTTTATAAAGGTGATGACCATTATAGAGTCAGAAATGGGAGTCGTTGCAGGAATTTCCTTTGGAGTTGCTTGCTTCCAA
	
	public List<List<TranscriptConsequenceType>> getConsequenceType(List<Position> positions) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		List<List<TranscriptConsequenceType>> transcriptConsequenceTypeList = new ArrayList<List<TranscriptConsequenceType>>(positions.size());
		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery("select g.stable_id, g.start, g.end, g.strand, t.stable_id, e.stable_id, e.start, e.end, et.coding_region_start, et.coding_region_end, et.phase, es.sequence from gene g, transcript t, exon2transcript et, exon e, exon_sequence es where g.position_prefix= ? and g.start-5000 <= ? and g.end+5000 >= ? and g.gene_id=t.gene_id and t.transcript_id=et.transcript_id and et.exon_id=e.exon_id and e.exon_id=es.exon_id");	
		
		Object[][] result;
		List<TranscriptConsequenceType> transcriptConsquenceTypes = new ArrayList<TranscriptConsequenceType>();
		int geneStart, geneEnd;
		int exonStart, exonEnd;
		int codingRegionStart, codingRegionEnd;
		int strand;
		TranscriptConsequenceType upstream = null;
		TranscriptConsequenceType downstream = null;
		
		for(Position position: positions) {
			prepQuery.setParams(position.getChromosome()+":"+String.valueOf(position.getPosition()).substring(0, 3), ""+position.getPosition(), ""+position.getPosition());
			transcriptConsquenceTypes = new ArrayList<TranscriptConsequenceType>();
			upstream = null;
			downstream = null;

			result = (Object[][])prepQuery.execute(new MatrixHandler());
			if(result != null && result.length > 0) {
				for(int i=0; i<result.length; i++) {
					strand = Integer.parseInt(result[i][3].toString());
					if(position.getPosition() < Integer.parseInt(result[i][1].toString())) {
						upstream = new TranscriptConsequenceType(result[i][0].toString(), result[i][4].toString(), "UPSTREAM");
					}
					if(position.getPosition() > Integer.parseInt(result[i][2].toString())) {
						downstream = new TranscriptConsequenceType(result[i][0].toString(), result[i][4].toString(), "DOWNSTREAM");
					}
					if(position.getPosition() >= Integer.parseInt(result[i][1].toString()) ) {
						upstream = new TranscriptConsequenceType(result[i][0].toString(), result[i][4].toString(), "5PRIME_UTR");
					}
					
//					transcriptConsquenceTypes.add(new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), result[i][2].toString()));
				}
			}else {
				// repetir con position-5000
			}
			
			if(upstream != null) {
				transcriptConsquenceTypes.add(upstream);	
			}
			if(downstream != null) {
				transcriptConsquenceTypes.add(downstream);	
			}
			
			transcriptConsequenceTypeList.add(transcriptConsquenceTypes);
		}
		
		prepQuery.close();
		return transcriptConsequenceTypeList;
	}
	
}

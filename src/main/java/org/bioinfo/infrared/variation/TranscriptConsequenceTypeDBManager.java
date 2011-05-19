package org.bioinfo.infrared.variation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bioinfo.commons.utils.ListUtils;
import org.bioinfo.db.api.PreparedQuery;
import org.bioinfo.db.handler.MatrixHandler;
import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.common.DBManager;
import org.bioinfo.infrared.core.common.utils.DNASequenceUtils;
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
	public List<TranscriptConsequenceType> getConsequenceType(Position position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getConsequenceTypes(Arrays.asList(position)).get(0);
	}

	public List<List<TranscriptConsequenceType>> getConsequenceTypes(List<Position> positions) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getConsequenceTypes(positions, ListUtils.toStringList(ListUtils.initialize(positions.size(), null)));
	}

	public List<List<TranscriptConsequenceType>> getConsequenceTypes(List<Position> positions, List<String> alternateAllele) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		List<List<TranscriptConsequenceType>> transcriptConsequenceTypeList = new ArrayList<List<TranscriptConsequenceType>>(positions.size());
		//		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery("select g.stable_id, g.start, g.end, g.strand, t.stable_id, e.stable_id, e.start, e.end, et.coding_region_start, et.coding_region_end, et.phase, es.sequence from gene g, transcript t, exon2transcript et, exon e, exon_sequence es where g.position_prefix= ? and g.start-5000 <= ? and g.end+5000 >= ? and g.gene_id=t.gene_id and t.transcript_id=et.transcript_id and et.exon_id=e.exon_id and e.exon_id=es.exon_id");	
		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery("select g.stable_id, t.stable_id, t.chromosome, t.start, t.end, t.coding_region_start, t.coding_region_end, t.strand, e.stable_id, e.start, e.end, et.coding_region_start, et.coding_region_end, et.phase, es.sequence from gene g, transcript t, transcript_position_prefix tpp, exon2transcript et, exon e, exon_sequence es where tpp.position_prefix = ? and tpp.transcript_id=t.transcript_id and t.chromosome = ? and t.start-5000 <= ? and t.end+5000 >= ? and g.gene_id=t.gene_id and t.transcript_id=et.transcript_id and et.exon_id=e.exon_id and e.exon_id=es.exon_id order by g.stable_id, t.stable_id, e.start");
		Object[][] result;
		List<TranscriptConsequenceType> transcriptConsquenceTypes = new ArrayList<TranscriptConsequenceType>();
		String transcriptChromosome;
		int transcriptStart, transcriptEnd;
		int exonStart, exonEnd;
		int codingRegionStart, codingRegionEnd;
		int strand, phase;
		String exonSequence;
		TranscriptConsequenceType upstream = null;
		TranscriptConsequenceType downstream = null;
		TranscriptConsequenceType fivePrimeUtr = null;
		TranscriptConsequenceType threePrimeUtr = null;
		TranscriptConsequenceType exonSpliceSite = null;
		TranscriptConsequenceType essentialSpliceSite = null;
		TranscriptConsequenceType intronic = null;
		TranscriptConsequenceType synonymousCoding = null;
		TranscriptConsequenceType nonSynonymousCoding = null;
		TranscriptConsequenceType withinNonCodingGene = null;
		TranscriptConsequenceType intergenic = null;

		String prevTranscript = "";
		int prevExonStart = 0;
		int prevExonEnd = 0;

		Position position;
//		for(Position position: positions) {
		for(int pos=0; pos<positions.size(); pos++) {
			position = positions.get(pos);
			if(position.getPosition() >= 1000000) {
				prepQuery.setParams(position.getChromosome()+":"+String.valueOf(position.getPosition()).substring(0, 3), position.getChromosome(), ""+position.getPosition(), ""+position.getPosition());
			}else {
				prepQuery.setParams("0:000", position.getChromosome(), ""+position.getPosition(), ""+position.getPosition());
			}

			transcriptConsquenceTypes = new ArrayList<TranscriptConsequenceType>();
			upstream = null;
			downstream = null;
			fivePrimeUtr = null;
			threePrimeUtr = null;
			exonSpliceSite = null;
			essentialSpliceSite = null;
			intronic = null;
			synonymousCoding = null;
			nonSynonymousCoding = null;
			withinNonCodingGene = null;
			intergenic = null;

			result = (Object[][])prepQuery.execute(new MatrixHandler());
			//			System.out.println("TranscriptConsequenceTypeDBManager: "+result.length+" results for: "+position.toString());
			if(result != null && result.length > 0) {
				prevTranscript = "";
				for(int i=0; i<result.length; i++) {
					//					for(int j=0;j<result[i].length; j++) {
					//						System.out.print(result[i][j]+" ");
					//					}
					//					System.out.println();
					//					g.stable_id, t.stable_id, t.chromosome, t.start, t.end, t.coding_region_start, t.coding_region_end, t.strand, e.stable_id, e.start, e.end, et.coding_region_start, et.coding_region_end, et.phase, es.sequence
					transcriptChromosome = result[i][2].toString();
					transcriptStart = Integer.parseInt(result[i][3].toString());
					transcriptEnd = Integer.parseInt(result[i][4].toString());
					codingRegionStart = Integer.parseInt(result[i][5].toString());
					codingRegionEnd = Integer.parseInt(result[i][6].toString());
					strand = Integer.parseInt(result[i][7].toString());
					exonStart = Integer.parseInt(result[i][9].toString());
					exonEnd = Integer.parseInt(result[i][10].toString());
					phase = Integer.parseInt(result[i][13].toString());
					exonSequence = result[i][14].toString();

					if(strand == 1) {
						if(position.getPosition() < transcriptStart && position.getPosition() >= transcriptStart-5000) {
							upstream = new TranscriptConsequenceType("UPSTREAM", result[i][1].toString(), result[i][0].toString());
							//							if(!transcriptConsquenceTypes.contains(upstream)) {
							//								transcriptConsquenceTypes.add(upstream);
							//							}
							//							continue;
						}
						if(position.getPosition() > transcriptEnd && position.getPosition() <= transcriptEnd+5000) {
							downstream = new TranscriptConsequenceType("DOWNSTREAM", result[i][1].toString(), result[i][0].toString());
							//							if(!transcriptConsquenceTypes.contains(downstream)) {
							//								transcriptConsquenceTypes.add(downstream);
							//							}
							//							transcriptConsquenceTypes.add(new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), "DOWNSTREAM"));
						}

						// codingRegionStart == 0 and codingRegionEnd ==0  in no protein coding transcripts, no UTRs can exist 
						if(codingRegionStart != 0 && position.getPosition() >= transcriptStart && position.getPosition() < codingRegionStart) {
							fivePrimeUtr = new TranscriptConsequenceType("5PRIME_UTR", result[i][1].toString(), result[i][0].toString());
						}
						if(codingRegionEnd != 0 && position.getPosition() > codingRegionEnd && position.getPosition() <= transcriptEnd) {
							threePrimeUtr = new TranscriptConsequenceType("3PRIME_UTR", result[i][1].toString(), result[i][0].toString());
						}

						// codingRegionStart == 0 and codingRegionEnd ==0  in no protein coding transcripts, then 'WITHIN_NON_CODING_GENE' 
						if(codingRegionStart == 0 && codingRegionEnd == 0 && position.getPosition() >= transcriptStart && position.getPosition() <= transcriptEnd) {
							withinNonCodingGene = new TranscriptConsequenceType("WITHIN_NON_CODING_GENE", result[i][1].toString(), result[i][0].toString());
						}

						// codingRegionStart == 0 and codingRegionEnd ==0  in no protein coding transcripts, then check for synonymous/non-synonymous 
						if(codingRegionStart != 0 && codingRegionEnd != 0 && position.getPosition() >= transcriptStart && position.getPosition() <= transcriptEnd) {
							if(position.getPosition() >= exonStart && position.getPosition() <= exonEnd) {
								int exonOffset = position.getPosition() - exonStart + phase;
//								System.out.println(position.getPosition()+"-"+exonStart+"-"+exonEnd+"-"+exonSequence);
								String refCodon = exonSequence.substring(exonOffset, exonOffset+3).replace('T', 'U');
								String altCodon = exonSequence.substring(exonOffset, exonOffset+3).replace('T', 'U');
								System.out.println(refCodon+":"+altCodon);
								
								if(DNASequenceUtils.codonToAminoacid.get(refCodon).equals(DNASequenceUtils.codonToAminoacid.get(altCodon))) {
									synonymousCoding = new TranscriptConsequenceType("SYNONYMOUS_CODING", result[i][1].toString(), result[i][0].toString());
								}else {
									nonSynonymousCoding = new TranscriptConsequenceType("NON_SYNONYMOUS_CODING", result[i][1].toString(), result[i][0].toString());
								}
							}else {

							}
						}



						if(exonEnd > codingRegionStart && exonEnd < codingRegionEnd && position.getPosition() >= exonEnd-3 && position.getPosition() <= exonEnd) {
							exonSpliceSite = new TranscriptConsequenceType("SPLICE_SITE", result[i][1].toString(), result[i][0].toString());
						}
						if(exonEnd > codingRegionStart && exonEnd < codingRegionEnd && position.getPosition() >= exonEnd+3 && position.getPosition() <= exonEnd+8) {
							exonSpliceSite = new TranscriptConsequenceType("SPLICE_SITE", result[i][1].toString(), result[i][0].toString());
							intronic = new TranscriptConsequenceType("INTRONIC", result[i][1].toString(), result[i][0].toString());
						}
						if(exonStart > codingRegionStart && exonStart < codingRegionEnd && position.getPosition() >= exonStart && position.getPosition() <= exonStart+3) {
							exonSpliceSite = new TranscriptConsequenceType("SPLICE_SITE", result[i][1].toString(), result[i][0].toString());
						}
						if(exonStart > codingRegionStart && exonStart < codingRegionEnd && position.getPosition() >= exonStart-8 && position.getPosition() <= exonStart-3) {
							exonSpliceSite = new TranscriptConsequenceType("SPLICE_SITE", result[i][1].toString(), result[i][0].toString());
							intronic = new TranscriptConsequenceType("INTRONIC", result[i][1].toString(), result[i][0].toString());
						}
						if(exonEnd > codingRegionStart && exonEnd < codingRegionEnd && position.getPosition() >= exonEnd+1 && position.getPosition() <= exonEnd+2) {
							essentialSpliceSite = new TranscriptConsequenceType("ESSENTIAL_SPLICE_SITE", result[i][1].toString(), result[i][0].toString());
							intronic = new TranscriptConsequenceType("INTRONIC", result[i][1].toString(), result[i][0].toString());
						}
						if(exonStart > codingRegionStart && exonStart < codingRegionEnd && position.getPosition() >= exonStart-2 && position.getPosition() <= exonStart-1) {
							essentialSpliceSite = new TranscriptConsequenceType("ESSENTIAL_SPLICE_SITE", result[i][1].toString(), result[i][0].toString());
							intronic = new TranscriptConsequenceType("INTRONIC", result[i][1].toString(), result[i][0].toString());
						}


						if(result[i][1].toString().equals(prevTranscript)) {
							if(position.getPosition() > codingRegionStart && position.getPosition() < codingRegionEnd && position.getPosition() > prevExonEnd && position.getPosition() < exonStart) {
								intronic = new TranscriptConsequenceType("INTRONIC", result[i][1].toString(), result[i][0].toString());
							}
						}else {
							prevTranscript = result[i][1].toString();
						}
						prevExonStart = exonStart;
						prevExonEnd = exonEnd;


					}else {
						if(position.getPosition() > transcriptEnd  && position.getPosition() < transcriptEnd+5000) {
							upstream = new TranscriptConsequenceType("UPSTREAM", result[i][1].toString(), result[i][0].toString());
							//							if(!transcriptConsquenceTypes.contains(upstream)) {
							//								transcriptConsquenceTypes.add(upstream);
							//							}
						}
						if(position.getPosition() < transcriptStart && position.getPosition() > transcriptStart-5000) {
							downstream = new TranscriptConsequenceType("DOWNSTREAM", result[i][1].toString(), result[i][0].toString());
							//							if(!transcriptConsquenceTypes.contains(downstream)) {
							//								transcriptConsquenceTypes.add(downstream);
							//							}
						}

						// codingRegionStart == 0 and codingRegionEnd ==0  in no protein coding transcripts, no UTRs can exist 
						if(codingRegionEnd != 0 && position.getPosition() > codingRegionEnd && position.getPosition() <= transcriptEnd) {
							fivePrimeUtr = new TranscriptConsequenceType("5PRIME_UTR", result[i][1].toString(), result[i][0].toString());
						}
						if(codingRegionStart != 00 && position.getPosition() >= transcriptStart && position.getPosition() < codingRegionStart) {
							threePrimeUtr = new TranscriptConsequenceType("3PRIME_UTR", result[i][1].toString(), result[i][0].toString());
						}

						// codingRegionStart == 0 and codingRegionEnd ==0  in no protein coding transcripts, then 'WITHIN_NON_CODING_GENE' 
						if(codingRegionStart == 0 && codingRegionEnd == 0 && position.getPosition() >= transcriptStart && position.getPosition() <= transcriptEnd) {
							withinNonCodingGene = new TranscriptConsequenceType("WITHIN_NON_CODING_GENE", result[i][1].toString(), result[i][0].toString());
						}




						if(exonEnd > codingRegionStart && exonEnd < codingRegionEnd && position.getPosition() >= exonEnd-3 && position.getPosition() <= exonEnd) {
							exonSpliceSite = new TranscriptConsequenceType("SPLICE_SITE", result[i][1].toString(), result[i][0].toString());
						}
						if(exonEnd > codingRegionStart && exonEnd < codingRegionEnd && position.getPosition() >= exonEnd+3 && position.getPosition() <= exonEnd+8) {
							exonSpliceSite = new TranscriptConsequenceType("SPLICE_SITE", result[i][1].toString(), result[i][0].toString());
							intronic = new TranscriptConsequenceType("INTRONIC", result[i][1].toString(), result[i][0].toString());
						}
						if(exonStart > codingRegionStart && exonStart < codingRegionEnd && position.getPosition() >= exonStart && position.getPosition() <= exonStart+3) {
							exonSpliceSite = new TranscriptConsequenceType("SPLICE_SITE", result[i][1].toString(), result[i][0].toString());
						}
						if(exonStart > codingRegionStart && exonStart < codingRegionEnd && position.getPosition() >= exonStart-8 && position.getPosition() <= exonStart-3) {
							exonSpliceSite = new TranscriptConsequenceType("SPLICE_SITE", result[i][1].toString(), result[i][0].toString());
							intronic = new TranscriptConsequenceType("INTRONIC", result[i][1].toString(), result[i][0].toString());
						}
						if(exonEnd > codingRegionStart && exonEnd < codingRegionEnd && position.getPosition() >= exonEnd+1 && position.getPosition() <= exonEnd+2) {
							essentialSpliceSite = new TranscriptConsequenceType("ESSENTIAL_SPLICE_SITE", result[i][1].toString(), result[i][0].toString());
							intronic = new TranscriptConsequenceType("INTRONIC", result[i][1].toString(), result[i][0].toString());
						}
						if(exonStart > codingRegionStart && exonStart < codingRegionEnd && position.getPosition() >= exonStart-2 && position.getPosition() <= exonStart-1) {
							essentialSpliceSite = new TranscriptConsequenceType("ESSENTIAL_SPLICE_SITE", result[i][1].toString(), result[i][0].toString());
							intronic = new TranscriptConsequenceType("INTRONIC", result[i][1].toString(), result[i][0].toString());
						}

						if(result[i][1].toString().equals(prevTranscript)) {
							if(position.getPosition() > codingRegionStart && position.getPosition() < codingRegionEnd && position.getPosition() > prevExonEnd && position.getPosition() < exonStart) {
								intronic = new TranscriptConsequenceType("INTRONIC", result[i][1].toString(), result[i][0].toString());
							}
						}else {
							prevTranscript = result[i][1].toString();
						}
						prevExonStart = exonStart;
						prevExonEnd = exonEnd;

					}

					//					transcriptConsquenceTypes.add(new TranscriptConsequenceType(result[i][0].toString(), result[i][1].toString(), result[i][2].toString()));
					if(upstream != null && !transcriptConsquenceTypes.contains(upstream)) {
						transcriptConsquenceTypes.add(upstream);
						upstream = null;
					}
					if(downstream != null && !transcriptConsquenceTypes.contains(downstream)) {
						transcriptConsquenceTypes.add(downstream);
						downstream = null;
					}
					if(fivePrimeUtr != null && !transcriptConsquenceTypes.contains(fivePrimeUtr)) {
						transcriptConsquenceTypes.add(fivePrimeUtr);
						fivePrimeUtr = null;
					}
					if(threePrimeUtr != null && !transcriptConsquenceTypes.contains(threePrimeUtr)) {
						transcriptConsquenceTypes.add(threePrimeUtr);
						threePrimeUtr = null;
					}
					if(exonSpliceSite != null && !transcriptConsquenceTypes.contains(exonSpliceSite)) {
						transcriptConsquenceTypes.add(exonSpliceSite);	
						exonSpliceSite = null;
					}
					if(essentialSpliceSite != null && !transcriptConsquenceTypes.contains(essentialSpliceSite)) {
						transcriptConsquenceTypes.add(essentialSpliceSite);
						essentialSpliceSite = null;
					}
					if(intronic != null && !transcriptConsquenceTypes.contains(intronic)) {
						transcriptConsquenceTypes.add(intronic);
						intronic = null;
					}
					if(synonymousCoding != null && !transcriptConsquenceTypes.contains(synonymousCoding)) {
						transcriptConsquenceTypes.add(synonymousCoding);	
						synonymousCoding = null;
					}
					if(nonSynonymousCoding != null && !transcriptConsquenceTypes.contains(nonSynonymousCoding)) {
						transcriptConsquenceTypes.add(nonSynonymousCoding);	
						nonSynonymousCoding = null;
					}
					if(withinNonCodingGene != null && !transcriptConsquenceTypes.contains(withinNonCodingGene)) {
						transcriptConsquenceTypes.add(withinNonCodingGene);	
						withinNonCodingGene = null;
					}
					if(intergenic != null && !transcriptConsquenceTypes.contains(intergenic)) {
						transcriptConsquenceTypes.add(intergenic);	
						intergenic = null;
					}

				}
			}else {
				intergenic = new TranscriptConsequenceType("", "", "INTERGENIC");
				transcriptConsquenceTypes.add(intergenic);	
			}

			transcriptConsequenceTypeList.add(transcriptConsquenceTypes);
		}

		prepQuery.close();
		return transcriptConsequenceTypeList;
	}

}

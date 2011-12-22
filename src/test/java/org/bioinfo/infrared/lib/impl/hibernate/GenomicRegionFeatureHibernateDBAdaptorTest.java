package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.HashMap;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.RegulatoryRegion;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.Tfbs;
import org.bioinfo.infrared.core.cellbase.Transcript;
import org.bioinfo.infrared.lib.api.GenomicRegionFeatureDBAdaptor;
import org.bioinfo.infrared.lib.common.GenomicRegionFeatures;
import org.bioinfo.infrared.lib.common.Region;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GenomicRegionFeatureHibernateDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new HibernateDBAdaptorFactory();

	private GenomicRegionFeatureDBAdaptor genomicRegionFeatureDBAdaptor;

	private long startExecTime;

	@Before
	public void beforeTestStart() {
		genomicRegionFeatureDBAdaptor = dbAdaptorFact.getFeatureMapDBAdaptor("hsapiens");
		startExecTime = System.currentTimeMillis();
	}

	@After
	public void afterTestStart() {
		dbAdaptorFact.close();
	}

	
//	@Test
//	public void testGenomicRegionGetByRegion() {
//		GenomicRegionFeatures maps = this.genomicRegionFeatureDBAdaptor.getByRegion("1", 4985398, 4987398);
//		print("testGenomicRegionGetByRegion", maps);
//	}
	
	
//	@Test
//	public void testGenomicRegionGetByRegionSource() {
//		GenomicRegionFeatures maps = this.genomicRegionFeatureDBAdaptor.getByRegion(new Region("2", 127436121, 127436121));
//		print("testGenomicRegionGetByRegionSource", maps);
//	}
	
	@Test
	public void testTFBSType() {
		GenomicRegionFeatures maps = this.genomicRegionFeatureDBAdaptor.getByRegion(new Region("1", 19229439,19229439));
		print("testGenomicRegionGetByRegionSource", maps);
	}
	
	
//	@Test
//	public void testConsequenceType() {
//		HashMap<String, List<String>> result =  this.genomicRegionFeatureDBAdaptor.getConsequenceType("1",19229436, "g");
//		for (String key : result.keySet()) {
//			System.out.println(key + " " + result.get(key));
//		}
//	}
	
	
	private void print(String title, GenomicRegionFeatures maps){
		
		long executionTime = System.currentTimeMillis() - startExecTime;
		System.out.println("\nTest:" + title);
		if (maps.getGenes() != null){
			System.out.println("\n\tGENES (" + maps.getGenes().size() + ")");
			System.out.println("\t--------------------------------------");
			for (Gene gene : maps.getGenes()) {
				System.out.println("\t\t" + gene.getStableId() + "\t" + gene.getStart() + "\t" + gene.getEnd());
			}
		}
		else{
			System.out.println("\n\tGENES: NULL");
		}
		
		if (maps.getTranscripts() != null){
			System.out.println("\n\tTRANSCRIPT (" + maps.getTranscripts().size() + ")");
			System.out.println("\t--------------------------------------");
			for (Transcript transcript : maps.getTranscripts()) {
				System.out.println("\t\t" + transcript.getStableId() + "\t" + transcript.getStart() + "\t" + transcript.getEnd());
			}
		}
		else{
			System.out.println("\n\tTRANSCRIPT: NULL");
		}
		
		
		if (maps.getExons() != null){
			System.out.println("\n\tEXON (" + maps.getExons().size() + ")");
			System.out.println("\t--------------------------------------");
			for (Exon exon : maps.getExons()) {
				System.out.println("\t\t" +exon.getStableId() + "\t" + exon.getStart() + "\t" + exon.getEnd());
			}
		}
		else{
			System.out.println("\n\tEXON: NULL");
		}
		
		if (maps.getSnp() != null){
			System.out.println("\n\tSNP (" + maps.getSnp().size() + ")");
			System.out.println("\t--------------------------------------");
			for (Snp snp : maps.getSnp()) {
				System.out.println("\t\t" + snp.getName() + "\t" + snp.getStart() + "\t" + snp.getEnd());
			}
		}
		else{
			System.out.println("\n\tSNP : NULL");
		}
		
		if (maps.getTfbs() != null){
			System.out.println("\n\t TFBS (" + maps.getTfbs().size() + ")");
			System.out.println("\t--------------------------------------");
			for (Tfbs tfbs : maps.getTfbs()) {
				System.out.println("\t\t" + tfbs.getTfbsId() + "\t" + tfbs.getTfName()+ "\t" + tfbs.getTargetGeneName() + "\t" + tfbs.getCellType());
			}
		}
		else{
			System.out.println("\n\tTFBS: NULL");
		}
		
		if (maps.getRegulatoryRegion() != null){
			System.out.println("\n\t REGULATORY (" + maps.getRegulatoryRegion().size() 
					+ ")  Histones ("+  maps.getHistones().size()
					+ ")  Open  Chromatin ("+  maps.getOpenChromatin().size() 
					+ ")  Transcription Factor ("+  maps.getTranscriptionFactor().size() 
					+ ") Polymerasa ("+  maps.getPolimerase().size() + ")");
			
			System.out.println("\t--------------------------------------");
			for (RegulatoryRegion re : maps.getRegulatoryRegion()) {
				System.out.println("\t\t" + re.getName() + "\t" + re.getType());
			}
		}
		else{
			System.out.println("\n\tREGULATORY: NULL");
		}
		
		System.out.println("\n\nTest executed in: "+ executionTime +" ms");
		
	}
	
	
}

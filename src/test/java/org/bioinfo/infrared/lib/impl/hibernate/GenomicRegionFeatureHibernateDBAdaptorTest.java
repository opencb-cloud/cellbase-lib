package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.lib.api.GenomicRegionFeatureDBAdaptor;
import org.bioinfo.infrared.lib.common.GenomicVariant;
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
		startExecTime = System.currentTimeMillis();
	}

	@After
	public void afterTestStart() {
		long executionTime = System.currentTimeMillis() - startExecTime;
		System.out.println("Test executed in: "+ executionTime +" ms");
	}


//	@Test
//	public void testGenomicRegionGetMirna() {
//		MirnaDBAdaptor adaptor = dbAdaptorFact.getMirnaDBAdaptor("hsa");
//		List<MiRnaTarget> result = adaptor.getAllByMirbaseId("asdsad");
//		
//		System.out.println(result);
//	}
	
//	@Test
//	public void testGenomicRegionGetMirna() {
//		GeneDBAdaptor adaptor = dbAdaptorFact.getGeneDBAdaptor("hsa");
//		List<Gene> result = adaptor.getAllByTf("USF1");
//		
//		System.out.println(result);
//	}
	
	
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
	
//	@Test
//	public void testTFBSType() {
//		GenomicRegionFeatures maps = this.genomicRegionFeatureDBAdaptor.getByRegion(new Region("1", 19229439,19229439));
//		print("testGenomicRegionGetByRegionSource", maps);
//	}
	
	
//	@Test
//	public void testConsequenceType() {
////		HashMap<String, List<String>> result =  this.genomicRegionFeatureDBAdaptor.getConsequenceType("1",19229436, "g");
////		for (String key : result.keySet()) {
////			System.out.println(key + " " + result.get(key));
////		}
//		List<GenomicVariant> variants = new ArrayList<GenomicVariant>();
//		variants.add(new GenomicVariant("1", 27852654, "T"));
//		
//		GenomicVariantEffect gv = new GenomicVariantEffect("hsa");
//		try {
//			gv.getConsequenceType(variants);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
//	@Test
//	public void Variant() {
//		int maximum = 200000000;
//		try {
//			FileUtils.touch(new File("/tmp/genomeVariantTest.txt"));
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		
//		
//		List<GenomicVariant> variants = new ArrayList<GenomicVariant>();
//		
//		for (int i = 0; i < 10000; i++) {
//			variants.add(new GenomicVariant("1", (int)(Math.random()*maximum) + i, "g"));
//		}
//		
//		System.out.println("Creadas " + variants.size() + " variantes");
//		
//		GenomicVariantEffect gv = new GenomicVariantEffect("hsa");
//		gv.writeConsequenceType(variants, new File("/tmp/genomeVariantTest.txt"));
//		
//		
//	}
	
//	private void print(String title, GenomicRegionFeatures maps){
//		
//		long executionTime = System.currentTimeMillis() - startExecTime;
//		System.out.println("\nTest:" + title);
//		if (maps.getGenes() != null){
//			System.out.println("\n\tGENES (" + maps.getGenes().size() + ")");
//			System.out.println("\t--------------------------------------");
//			for (Gene gene : maps.getGenes()) {
//				System.out.println("\t\t" + gene.getStableId() + "\t" + gene.getStart() + "\t" + gene.getEnd());
//			}
//		}
//		else{
//			System.out.println("\n\tGENES: NULL");
//		}
//		
//		if (maps.getTranscripts() != null){
//			System.out.println("\n\tTRANSCRIPT (" + maps.getTranscripts().size() + ")");
//			System.out.println("\t--------------------------------------");
//			for (Transcript transcript : maps.getTranscripts()) {
//				System.out.println("\t\t" + transcript.getStableId() + "\t" + transcript.getStart() + "\t" + transcript.getEnd());
//			}
//		}
//		else{
//			System.out.println("\n\tTRANSCRIPT: NULL");
//		}
//		
//		
//		if (maps.getExons() != null){
//			System.out.println("\n\tEXON (" + maps.getExons().size() + ")");
//			System.out.println("\t--------------------------------------");
//			for (Exon exon : maps.getExons()) {
//				System.out.println("\t\t" +exon.getStableId() + "\t" + exon.getStart() + "\t" + exon.getEnd());
//			}
//		}
//		else{
//			System.out.println("\n\tEXON: NULL");
//		}
//		
//		if (maps.getSnp() != null){
//			System.out.println("\n\tSNP (" + maps.getSnp().size() + ")");
//			System.out.println("\t--------------------------------------");
//			for (Snp snp : maps.getSnp()) {
//				System.out.println("\t\t" + snp.getName() + "\t" + snp.getStart() + "\t" + snp.getEnd());
//			}
//		}
//		else{
//			System.out.println("\n\tSNP : NULL");
//		}
//		
//		if (maps.getTfbs() != null){
//			System.out.println("\n\t TFBS (" + maps.getTfbs().size() + ")");
//			System.out.println("\t--------------------------------------");
//			for (Tfbs tfbs : maps.getTfbs()) {
//				System.out.println("\t\t" + tfbs.getTfbsId() + "\t" + tfbs.getTfName()+ "\t" + tfbs.getTargetGeneName() + "\t" + tfbs.getCellType());
//			}
//		}
//		else{
//			System.out.println("\n\tTFBS: NULL");
//		}
//		
//		if (maps.getRegulatoryRegion() != null){
//			System.out.println("\n\t REGULATORY (" + maps.getRegulatoryRegion().size() 
//					+ ")  Histones ("+  maps.getHistones().size()
//					+ ")  Open  Chromatin ("+  maps.getOpenChromatin().size() 
//					+ ")  Transcription Factor ("+  maps.getTranscriptionFactor().size() 
//					+ ") Polymerasa ("+  maps.getPolimerase().size() + ")");
//			
//			System.out.println("\t--------------------------------------");
//			for (RegulatoryRegion re : maps.getRegulatoryRegion()) {
//				System.out.println("\t\t" + re.getName() + "\t" + re.getType());
//			}
//		}
//		else{
//			System.out.println("\n\tREGULATORY: NULL");
//		}
//		
//		System.out.println("\n\nTest executed in: "+ executionTime +" ms");
//		
//	}
	
	
}

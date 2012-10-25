package org.bioinfo.infrared.lib.io.parse;

import java.io.File;
import java.io.IOException;

import org.bioinfo.formats.exception.FileFormatException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FeatureToJsonParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		try {
			FeatureToJsonParser ftjp = new FeatureToJsonParser();
			ftjp.gffParseToJson(new File("/home/echirivella/Downloads/AnnotatedFeatures2.gff"),new File("/tmp/GFFannotedTest.json"));
			//ftjp.bedParseToJson(new File("/home/echirivella/Downloads/36way_gerp_elements.Homo_sapiens2.bed"),new File("/tmp/BEDannotedTest.json"));
			ftjp.bedParseToJson(new File("/home/echirivella/Downloads/Homo_sapiens2.bed"),new File("/tmp/BEDannotedTest.json"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

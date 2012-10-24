package org.bioinfo.infrared.lib.io.parse;

import java.io.File;
import java.io.IOException;

import org.bioinfo.commons.io.utils.IOUtils;
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
			String jsonString = ftjp.gffParseToJson(new File("/home/echirivella/Downloads/AnnotatedFeatures.gff"));
			
			IOUtils.write(new File("/tmp/AnnotedTest.json"), jsonString);
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

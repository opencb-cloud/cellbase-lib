package org.bioinfo.infrared.lib.io.parse;

import java.io.File;
import java.io.IOException;

import org.bioinfo.commons.io.utils.IOUtils;
import org.bioinfo.formats.exception.FileFormatException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CoreParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParseToJson() {
		GeneJsonParser coreParser = new GeneJsonParser();
		try {
			String jsonString = coreParser.parseToJson(new File("/home/imedina/Downloads/Homo_sapiens.GRCh37.69_50000.gtf"));
			IOUtils.write(new File("/tmp/hsapiens_core.json"), jsonString);
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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

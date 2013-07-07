package org.bioinfo.cellbase.lib.impl.dbquery;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QueryOptionsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQueryOptionsString() {	
		String json = "{'id' : 'ENSE00002608543', 'names': ['name1', name2], obj: {'attr': 'attrValue'}, bool: true,'chromosome' : 'HG104_HG975_PATCH','start' : 144795188,'end' : 144796068,'strand' : '+','genomicCodingStart' : 0,'genomicCodingEnd' : 0,'cdnaCodingStart' : 0,'cdnaCodingEnd' : 0,'cdsStart' : 0,'cdsEnd' : 0,'phase' : -1,'exonNumber' : 6,'sequence' : ''}";
		
		QueryOptions q = new QueryOptions(json);
		System.out.println(q.get("chromosome"));
		System.out.println(q.getClass().getSimpleName());
		System.out.println(q.getInt("start"));
		System.out.println((q.getList("names")).get(0));
		System.out.println((q.getBoolean("bool")));
		System.out.println((q.getMap("obj")).get("attr"));
//		fail("Not yet implemented");
	}

}

package org.bioinfo.cellbase.lib.impl.mongo;

import org.bioinfo.cellbase.lib.api.GeneDBAdaptor;
import org.bioinfo.cellbase.lib.api.TranscriptDBAdaptor;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.common.core.Gene;
import org.bioinfo.cellbase.lib.common.core.Transcript;
import org.bioinfo.cellbase.lib.impl.DBAdaptorFactory;
import org.bioinfo.cellbase.lib.impl.mongodb.MongoDBAdaptorFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TranscriptMongoDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new MongoDBAdaptorFactory();
	private TranscriptDBAdaptor transcriptDBAdaptor;
	private long startExecTime;
	private String species = "hsapiens";
	
	@Before
	public void beforeTestStart() {
        transcriptDBAdaptor = dbAdaptorFact.getTranscriptDBAdaptor(species, "v3");
		startExecTime = System.currentTimeMillis();
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {

	}

    @Test
    public void testXref() {
//        List<List<Transcript>> transcripts = transcriptDBAdaptor.getAllByNameList(Arrays.asList("brca2"),Arrays.asList(""));
//        System.out.println(transcripts.size());
//        System.out.println(transcripts.get(0).size());

    }
}

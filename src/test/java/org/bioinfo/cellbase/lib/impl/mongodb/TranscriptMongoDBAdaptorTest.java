package org.bioinfo.cellbase.lib.impl.mongodb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.bioinfo.cellbase.lib.api.TranscriptDBAdaptor;
import org.bioinfo.cellbase.lib.impl.DBAdaptorFactory;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryOptions;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TranscriptMongoDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new MongoDBAdaptorFactory();
	private TranscriptDBAdaptor transcriptDBAdaptor;
	private long startExecTime;
	private String species = "hsapiens";

    private static ObjectMapper jsonObjectMapper;
    private static ObjectWriter jsonObjectWriter;

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

    @Test
    public void getAllByIdTest() throws JsonProcessingException {
        QueryResponse response = transcriptDBAdaptor.getAllById("ENST00000343281", null);
//        System.out.println(jsonObjectWriter.writeValueAsString(response));
        System.out.println(response.toJson());
    }
}

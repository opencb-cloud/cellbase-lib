package org.bioinfo.cellbase.lib.impl.mongodb;

import org.bioinfo.cellbase.lib.api.RegulatoryRegion.TfbsDBAdaptor;
import org.bioinfo.cellbase.lib.common.Position;
import org.bioinfo.cellbase.lib.impl.DBAdaptorFactory;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryOptions;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TfbsMongoDBAdaptorTest {

	private DBAdaptorFactory dbAdaptorFact = new MongoDBAdaptorFactory();
	private TfbsDBAdaptor tfbsDBAdaptor;
	private long startExecTime;
	private String species = "hsapiens";
	
	@Before
	public void beforeTestStart() {
        tfbsDBAdaptor = dbAdaptorFact.getTfbsDBAdaptor(species, "v3");
		startExecTime = System.currentTimeMillis();
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testGetAllById() {
		QueryResponse qr = tfbsDBAdaptor.getAllById("HNF4A", new QueryOptions());
		System.out.println(qr.toJson());

	}
    @Test
	public void testGetAllByTargetGeneId() {
//		QueryResponse qr = tfbsDBAdaptor.getAllByTargetGeneId("ENST00000544455", new QueryOptions());
		QueryResponse qr2 = tfbsDBAdaptor.getAllByTargetGeneIdList(Arrays.asList("ENST00000544455","BRCA2"), new QueryOptions());
//		System.out.println(qr.toJson());
		System.out.println(qr2.toJson());
	}
    @Test
    public void testGetAllByPosition() {
        Position p = new Position("1", 601156);
        QueryResponse qr = tfbsDBAdaptor.getAllByPosition(p, new QueryOptions());
        System.out.println(qr.toJson());
    }
    @Test
    public void testGetAllByPositionList() {
        Position p1 = new Position("1", 601156);
        Position p2 = new Position("2", 601750);
        Position p3 = new Position("7", 601132);

        List<Position> positions = new ArrayList<>();
        positions.add(p1);
        positions.add(p2);
        positions.add(p3);

        QueryResponse qr = tfbsDBAdaptor.getAllByPositionList(positions, new QueryOptions());
        System.out.println(qr.toJson());
    }
}

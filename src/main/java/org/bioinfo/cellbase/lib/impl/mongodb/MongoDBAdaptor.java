package org.bioinfo.cellbase.lib.impl.mongodb;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import com.mongodb.*;
import org.bioinfo.cellbase.lib.common.IntervalFeatureFrequency;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.impl.DBAdaptor;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryOptions;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResponse;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResult;
import org.bioinfo.commons.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class MongoDBAdaptor extends DBAdaptor {

    protected String species;
    protected String version;

    //	private MongoOptions mongoOptions;
    //	protected MongoClient mongoClient;
    protected DB db;
    protected DBCollection mongoDBCollection;
    protected static Map<String, Number> cachedQuerySizes = new HashMap<String, Number>();

    protected ObjectMapper jsonObjectMapper;

    static {
        // reading application.properties file
        resourceBundle = ResourceBundle.getBundle("org.bioinfo.cellbase.lib.impl.mongodb.conf.application");
        try {
            if (applicationProperties == null) {
                applicationProperties = new Config(resourceBundle);
            } else {
                // applicationProperties object must have been filled in DBAdpator class,
                // then just append MongoDB properties
                String key;
                Set<String> keys = resourceBundle.keySet();
                Iterator<String> keysIter = keys.iterator();
                while (keysIter.hasNext()) {
                    key = keysIter.next();
                    applicationProperties.put(key, resourceBundle.getObject(key));
                }
            }
        } catch (IOException e) {
            applicationProperties = new Config();
            e.printStackTrace();
        }
    }

    //	public MongoDBAdaptor(String species, String version) {
    //		logger.info("Species: "+species+" Version: "+version);
    //		this.mongoOptions = new MongoOptions();
    //		this.mongoOptions.setAutoConnectRetry(true);
    //		this.mongoOptions.setConnectionsPerHost(40);
    //		try {
    //			this.mongoClient = new MongoClient("mem15", mongoOptions);
    //		} catch (UnknownHostException e) {
    //			e.printStackTrace();
    //		}
    //	}

    public MongoDBAdaptor(DB db) {
        this.db = db;
    }

    public MongoDBAdaptor(DB db, String species, String version) {
        this.db = db;
        this.species = species;
        this.version = version;
        //		logger.warn(applicationProperties.toString());
        initSpeciesVersion(species, version);

        jsonObjectMapper = new ObjectMapper();
    }

    private void initSpeciesVersion(String species, String version) {
        if (species != null && !species.equals("")) {
            // if 'version' parameter has not been provided the default version is selected
            if (this.version == null || this.version.trim().equals("")) {
                this.version = applicationProperties.getProperty(species + ".DEFAULT.VERSION").toUpperCase();
                //				logger.debug("HibernateDBAdaptorFactory in createSessionFactory(): 'version' parameter is null or empty, it's been set to: '"+version+"'");
            }
        }
    }

    //	protected Session openSession() {
    //		if(session == null) {
    //			logger.debug("HibernateDBAdaptor: Session is null");
    //			session = sessionFactory.openSession();
    //		}else {
    //			if(!session.isOpen()) {
    //				logger.debug("HibernateDBAdaptor: Session is closed");
    //				session = sessionFactory.openSession();
    //			}else {
    //				logger.debug("HibernateDBAdaptor: Session is already open");
    //			}
    //		}
    //
    //		return session;
    //	}

//	protected QueryResult executeQuery(DBObject query, List<String> excludeFields, List<String> includeFields) {
//		long timeStart = System.currentTimeMillis();
//		QueryResult queryResult = new QueryResult();
//		DBCursor cursor = null;
//		
//		// Select which fields are excluded and included
//		BasicDBObject returnFields = new BasicDBObject("_id", 0);
//		if(excludeFields != null && excludeFields.size() > 0) {
//			for (String field : excludeFields) {
//				returnFields.put(field, 0);
//			}
//		} 
//		if(includeFields != null && includeFields.size() > 0) {
//			for (String field : includeFields) {
//				returnFields.put(field, 1);
//			}
//		}
//
//		// Execute query and calculate time
//		long dbTimeStart = System.currentTimeMillis();
//		cursor = mongoDBCollection.find(query, returnFields);
//		long dbTimeEnd = System.currentTimeMillis();
//		queryResult.setDBTime(dbTimeEnd - dbTimeStart);
//
//		try {
//			if(cursor != null) {
//				BasicDBList list = new BasicDBList();
//				while (cursor.hasNext()) {
//					list.add(cursor.next());
//				}
//				queryResult.setResult(list.toString());
//			}
//		} finally {
//			if(cursor != null) {
//				cursor.close();
//			}
//		}
//		long timeEnd = System.currentTimeMillis();
//		queryResult.put("time", timeEnd - timeStart);
//
//		return queryResult;
//	}

    protected QueryResponse executeQuery(Object id, DBObject query, List<String> excludeFields, List<String> includeFields, QueryOptions options) {
        return executeQueryList(Arrays.asList(id), Arrays.asList(query), excludeFields, includeFields, options);
    }

    protected QueryResponse executeQueryList(List<? extends Object> ids, List<DBObject> queries, List<String> excludeFields, List<String> includeFields, QueryOptions options) {
        long timeStart = System.currentTimeMillis();
        QueryResponse queryResponse = new QueryResponse();
        DBCursor cursor = null;

        // Select which fields are excluded and included in MongoDB query
        BasicDBObject returnFields = new BasicDBObject("_id", 0);
        // Read and process 'exclude' field from 'options' object
        if ((options != null && options.getList("include") != null) && options.getList("include").size() > 0 || (includeFields != null && includeFields.size() > 0)) {
            if (options != null && options.getList("include") != null) {
                List<Object> excludedOptionFields = (List<Object>) options.getList("include");
                if (excludedOptionFields != null && excludedOptionFields.size() > 0) {
                    for (Object field : excludedOptionFields) {
                        returnFields.put(field.toString(), 1);
                    }
                }
            }
            if (includeFields != null && includeFields.size() > 0) {
                for (String field : includeFields) {
                    returnFields.put(field, 1);
                }
            }
        } else {
            if (options != null && options.getList("exclude") != null) {
//			String[] excludedOptionFields = options.getString("exclude", "").split(","); 
                List<Object> excludedOptionFields = (List<Object>) options.getList("exclude");
                if (excludedOptionFields != null && excludedOptionFields.size() > 0) {
                    for (Object field : excludedOptionFields) {
                        returnFields.put(field.toString(), 0);
                    }
                }
            }
            if (excludeFields != null && excludeFields.size() > 0) {
                for (String field : excludeFields) {
                    returnFields.put(field, 0);
                }
            }
        }


//		BasicDBList list2 = new BasicDBList();
        long dbTimeStart, dbTimeEnd;
        for (int i = 0; i < queries.size(); i++) {
            DBObject query = queries.get(i);
            QueryResult queryResult = new QueryResult();
            // Execute query and calculate time
            dbTimeStart = System.currentTimeMillis();
            cursor = mongoDBCollection.find(query, returnFields);
            dbTimeEnd = System.currentTimeMillis();

            BasicDBList list = new BasicDBList();
            try {
                if (cursor != null) {
                    list = new BasicDBList();
                    while (cursor.hasNext()) {
                        list.add(cursor.next());
                    }
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
            queryResult.setDBTime((dbTimeEnd - dbTimeStart));
            queryResult.setResult(list);    //.toString()
//			list2.add(list);
            // Save QueryResult into QueryResponse object
            queryResponse.put(ids.get(i).toString(), queryResult);
        }
        long timeEnd = System.currentTimeMillis();


        // Check if 'metadata' field must be returned
        if (options != null && options.getBoolean("metadata", true)) {
            queryResponse.getMetadata().put("queryIds", ids);
            queryResponse.getMetadata().put("time", timeEnd - timeStart);
        } else {
            queryResponse.removeField("metadata");
        }

//		QueryResponse q = new QueryResponse();
//		q.put("results", list2);
//		q.removeField("metadata");
        return queryResponse;
    }

    protected QueryResponse executeAggregation(Object id, DBObject[] operations, List<String> excludeFields, List<String> includeFields, QueryOptions options) {
        ArrayList<DBObject[]> operationsList = new ArrayList<>();
        operationsList.add(operations);
        return executeAggregationList(Arrays.asList(id), operationsList, excludeFields, includeFields, options);
    }

    protected QueryResponse executeAggregationList(List<? extends Object> ids, List<DBObject[]> operationsList, List<String> excludeFields, List<String> includeFields, QueryOptions options) {
        long timeStart = System.currentTimeMillis();
        QueryResponse queryResponse = new QueryResponse();
        AggregationOutput aggregationOutput;

        long dbTimeStart, dbTimeEnd;
        for (int i = 0; i < operationsList.size(); i++) {
            DBObject[] operations = operationsList.get(i);
            DBObject firstOperation;
            DBObject[] additionalOperations;


            // Mongo aggregate method signature is :public AggregationOutput aggregate( DBObject firstOp, DBObject ... additionalOps)
            // so the operations array must be decomposed, TODO check operations length
            firstOperation = operations[0];
            additionalOperations = Arrays.copyOfRange(operations, 1, operations.length);

            QueryResult queryResult = new QueryResult();
            // Execute query and calculate time
            dbTimeStart = System.currentTimeMillis();
            aggregationOutput = mongoDBCollection.aggregate(firstOperation, additionalOperations);
//            cursor = mongoDBCollection.find(query, returnFields);
            dbTimeEnd = System.currentTimeMillis();

            BasicDBList list = new BasicDBList();
            try {
                if (aggregationOutput != null) {
                    list = new BasicDBList();
                    Iterator<DBObject> results = aggregationOutput.results().iterator();
                    while (results.hasNext()) {
                        list.add(results.next());
                    }
                }
            } finally {

            }
            queryResult.setDBTime((dbTimeEnd - dbTimeStart));
            queryResult.setResult(list);    //.toString()
//			list2.add(list);
            // Save QueryResult into QueryResponse object
            queryResponse.put(ids.get(i).toString(), queryResult);
        }
        long timeEnd = System.currentTimeMillis();

        // Check if 'metadata' field must be returned
        if (options != null && options.getBoolean("metadata", true)) {
            queryResponse.getMetadata().put("queryIds", ids);
            queryResponse.getMetadata().put("time", timeEnd - timeStart);
        } else {
            queryResponse.removeField("metadata");
        }

        return queryResponse;
    }

    protected BasicDBList executeQueryList(DBObject query, List<String> excludeFields, List<String> includeFields) {
        BasicDBList queryResult = null;
        DBCursor cursor = null;

        // Select which fields are excluded and included
        BasicDBObject returnFields = new BasicDBObject("_id", 0);
        if (excludeFields != null && excludeFields.size() > 0) {
            for (String field : excludeFields) {
                returnFields.put(field, 0);
            }
        }
        if (includeFields != null && includeFields.size() > 0) {
            for (String field : includeFields) {
                returnFields.put(field, 1);
            }
        }

        // Execute query and calculate time
        cursor = mongoDBCollection.find(query, returnFields);

        try {
            if (cursor != null) {
                queryResult = new BasicDBList();
                while (cursor.hasNext()) {
                    queryResult.add(cursor.next());
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return queryResult;
    }


    //	protected List<?> execute(Criteria criteria){
    //		List<?> result = criteria.list();
    //		return result;
    //	}
    //
    //	protected List<?> executeAndClose(Criteria criteria){
    //		List<?> result = criteria.list();
    //		//		closeSession();
    //		return result;
    //	}
    //
    //
    //	protected List<?> execute(Query query){
    //		List<?> result = query.list();
    //		return result;
    //	}
    //
    //	protected List<?> executeAndClose(Query query){
    //		List<?> result = query.list();
    //		//		closeSession();
    //		return result;
    //	}

    //	protected void closeSession() {
    //		if(session != null && session.isOpen()) {
    //			session.close();
    //		}
    //	}
    //
    //	@SuppressWarnings("unchecked")
    //	protected String getDatabaseQueryCache(String key) {
    //		Criteria criteria = this.openSession().createCriteria(Metainfo.class)
    //			.add(Restrictions.eq("property", key));
    //		List<Metainfo> metaInfoList = (List<Metainfo>) executeAndClose(criteria);
    //		if(metaInfoList != null && metaInfoList.size() > 0) {
    //			return metaInfoList.get(0).getValue();
    //		}else {
    //			return null;
    //		}
    //	}
    //
    //	protected void putDatabaseQueryCache(String key, String value) {
    ////		Query query = this.openSession().createQuery("insert into Metainfo (property, value) values ('"+key+"', '"+value+"')");
    ////		query.executeUpdate();
    //
    //		Session session = this.openSession();
    //		session.beginTransaction();
    //		session.save( new Metainfo( key, value ) );
    //		session.getTransaction().commit();
    //		session.close();
    //	}

    /**
     * For histograms
     */
    protected List<IntervalFeatureFrequency> getIntervalFeatureFrequencies(Region region, int interval, List<Object[]> objectList, int numFeatures, double maxSnpsInterval) {

        int numIntervals = (region.getEnd() - region.getStart()) / interval + 1;
        List<IntervalFeatureFrequency> intervalFeatureFrequenciesList = new ArrayList<IntervalFeatureFrequency>(numIntervals);

        //		BigInteger max = new BigInteger("-1");
        //		for(int i=0; i<objectList.size(); i++) {
        //			if(((BigInteger)objectList.get(i)[1]).compareTo(max) > 0) {
        //				max = (BigInteger)objectList.get(i)[1];
        //			}
        //		}
        float maxNormValue = 1;

        if (numFeatures != 0) {
            maxNormValue = (float) maxSnpsInterval / numFeatures;
        }

        int start = region.getStart();
        int end = start + interval;
        for (int i = 0, j = 0; i < numIntervals; i++) {
            if (j < objectList.size() && ((BigInteger) objectList.get(j)[0]).intValue() == i) {
                if (numFeatures != 0) {
                    intervalFeatureFrequenciesList.add(new IntervalFeatureFrequency(start, end, ((BigInteger) objectList.get(j)[0]).intValue()
                            , ((BigInteger) objectList.get(j)[1]).intValue()
                            , (float) Math.log(((BigInteger) objectList.get(j)[1]).doubleValue()) / numFeatures / maxNormValue));
                } else {    // no features for this chromosome
                    intervalFeatureFrequenciesList.add(new IntervalFeatureFrequency(start, end, ((BigInteger) objectList.get(j)[0]).intValue()
                            , ((BigInteger) objectList.get(j)[1]).intValue()
                            , 0));
                }
                j++;
            } else {
                intervalFeatureFrequenciesList.add(new IntervalFeatureFrequency(start, end, i, 0, 0.0f));
            }
            //			System.out.println(intervalFeatureFrequenciesList.get(i).getStart()+":"+intervalFeatureFrequenciesList.get(i).getEnd()+":"+intervalFeatureFrequenciesList.get(i).getInterval()+":"+ intervalFeatureFrequenciesList.get(i).getAbsolute()+":"+intervalFeatureFrequenciesList.get(i).getValue());

            start += interval;
            end += interval;
        }

        return intervalFeatureFrequenciesList;
    }


    protected List<IntervalFeatureFrequency> getIntervalFeatureFrequencies(Region region, int interval, List<Object[]> objectList) {

        int numIntervals = (region.getEnd() - region.getStart()) / interval + 1;
        List<IntervalFeatureFrequency> intervalFeatureFrequenciesList = new ArrayList<IntervalFeatureFrequency>(numIntervals);

        BigInteger max = new BigInteger("-1");
        for (int i = 0; i < objectList.size(); i++) {
            if (((BigInteger) objectList.get(i)[1]).compareTo(max) > 0) {
                max = (BigInteger) objectList.get(i)[1];
            }
        }

        int start = region.getStart();
        int end = start + interval;
        for (int i = 0, j = 0; i < numIntervals; i++) {
            if (j < objectList.size() && ((BigInteger) objectList.get(j)[0]).intValue() == i) {
                intervalFeatureFrequenciesList.add(new IntervalFeatureFrequency(start, end, ((BigInteger) objectList.get(j)[0]).intValue()
                        , ((BigInteger) objectList.get(j)[1]).intValue()
                        , ((BigInteger) objectList.get(j)[1]).floatValue() / max.floatValue()));
                j++;
            } else {
                intervalFeatureFrequenciesList.add(new IntervalFeatureFrequency(start, end, i, 0, 0.0f));
            }
            //			System.out.println(intervalFeatureFrequenciesList.get(i).getStart()+":"+intervalFeatureFrequenciesList.get(i).getEnd()+":"+intervalFeatureFrequenciesList.get(i).getInterval()+":"+ intervalFeatureFrequenciesList.get(i).getAbsolute()+":"+intervalFeatureFrequenciesList.get(i).getValue());

            start += interval;
            end += interval;
        }

        return intervalFeatureFrequenciesList;
    }

    //	/**
    //	 * @return the sessionFactory
    //	 */
    //	public SessionFactory getSessionFactory() {
    //		return sessionFactory;
    //	}
    //
    //	/**
    //	 * @param sessionFactory the sessionFactory to set
    //	 */
    //	public void setSessionFactory(SessionFactory sessionFactory) {
    //		this.sessionFactory = sessionFactory;
    //	}

    /**
     * @return the species
     */
    public String getSpecies() {
        return species;
    }

    /**
     * @param species the species to set
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

}

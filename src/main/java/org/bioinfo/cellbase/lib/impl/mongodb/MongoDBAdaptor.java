package org.bioinfo.cellbase.lib.impl.mongodb;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.bioinfo.cellbase.lib.common.IntervalFeatureFrequency;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.impl.DBAdaptor;
import org.bioinfo.commons.Config;

import com.mongodb.DB;
import com.mongodb.DBCollection;

public class MongoDBAdaptor extends DBAdaptor{

	protected String species;
	protected String version;

//	private MongoOptions mongoOptions;
//	protected MongoClient mongoClient;
	protected DB db;
	protected DBCollection mongoDBCollection;
	protected static Map<String, Number> cachedQuerySizes = new HashMap<String, Number>();

	static {
		// reading application.properties file
		resourceBundle = ResourceBundle.getBundle("org.bioinfo.cellbase.lib.impl.mongodb.conf.application");
		try {
			if(applicationProperties == null) {
				applicationProperties = new Config(resourceBundle);				
			}else {
				// applicationProperties object must have been filled in DBAdpator class,
				// then just append MongoDB properties
				String key;
				Set<String> keys = resourceBundle.keySet();
				Iterator<String> keysIter = keys.iterator();
				while(keysIter.hasNext()) {
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
	}

	private void initSpeciesVersion(String species, String version) {
		if(species != null && !species.equals("")) {
			// if 'version' parameter has not been provided the default version is selected
			if(this.version == null || this.version.trim().equals("")) {
				this.version = applicationProperties.getProperty(species+".DEFAULT.VERSION").toUpperCase();
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
	protected List<IntervalFeatureFrequency> getIntervalFeatureFrequencies(Region region , int interval, List<Object[]> objectList, int numFeatures, double maxSnpsInterval) {

		int numIntervals = (region.getEnd()-region.getStart())/interval +1;
		List<IntervalFeatureFrequency> intervalFeatureFrequenciesList = new ArrayList<IntervalFeatureFrequency>(numIntervals);

		//		BigInteger max = new BigInteger("-1");
		//		for(int i=0; i<objectList.size(); i++) {
		//			if(((BigInteger)objectList.get(i)[1]).compareTo(max) > 0) {
		//				max = (BigInteger)objectList.get(i)[1];
		//			}
		//		}
		float maxNormValue = 1;

		if(numFeatures != 0) {
			maxNormValue = (float)maxSnpsInterval / numFeatures;			
		}

		int start = region.getStart();
		int end = start + interval;
		for(int i=0, j=0; i < numIntervals; i++) {
			if(j < objectList.size() && ((BigInteger)objectList.get(j)[0]).intValue() == i) {
				if(numFeatures != 0) {
					intervalFeatureFrequenciesList.add(new IntervalFeatureFrequency(start, end, ((BigInteger)objectList.get(j)[0]).intValue()
							,((BigInteger)objectList.get(j)[1]).intValue() 
							, (float)Math.log(((BigInteger)objectList.get(j)[1]).doubleValue()) / numFeatures / maxNormValue));
				}else {	// no features for this chromosome
					intervalFeatureFrequenciesList.add(new IntervalFeatureFrequency(start, end, ((BigInteger)objectList.get(j)[0]).intValue()
							,((BigInteger)objectList.get(j)[1]).intValue() 
							,0));
				}
				j++;
			}else {
				intervalFeatureFrequenciesList.add(new IntervalFeatureFrequency(start, end, i, 0, 0.0f));
			}
			//			System.out.println(intervalFeatureFrequenciesList.get(i).getStart()+":"+intervalFeatureFrequenciesList.get(i).getEnd()+":"+intervalFeatureFrequenciesList.get(i).getInterval()+":"+ intervalFeatureFrequenciesList.get(i).getAbsolute()+":"+intervalFeatureFrequenciesList.get(i).getValue());

			start += interval;
			end += interval;
		}

		return intervalFeatureFrequenciesList;
	}


	protected List<IntervalFeatureFrequency> getIntervalFeatureFrequencies(Region region , int interval, List<Object[]> objectList) {

		int numIntervals = (region.getEnd()-region.getStart())/interval +1;
		List<IntervalFeatureFrequency> intervalFeatureFrequenciesList = new ArrayList<IntervalFeatureFrequency>(numIntervals);

		BigInteger max = new BigInteger("-1");
		for(int i=0; i<objectList.size(); i++) {
			if(((BigInteger)objectList.get(i)[1]).compareTo(max) > 0) {
				max = (BigInteger)objectList.get(i)[1];
			}
		}

		int start = region.getStart();
		int end = start + interval;
		for(int i=0, j=0; i < numIntervals; i++) {
			if(j < objectList.size() && ((BigInteger)objectList.get(j)[0]).intValue() == i) {
				intervalFeatureFrequenciesList.add(new IntervalFeatureFrequency(start, end, ((BigInteger)objectList.get(j)[0]).intValue()
						,((BigInteger)objectList.get(j)[1]).intValue() 
						,((BigInteger)objectList.get(j)[1]).floatValue() / max.floatValue()));
				j++;
			}else {
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


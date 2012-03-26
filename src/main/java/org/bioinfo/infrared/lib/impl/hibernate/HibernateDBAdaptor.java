package org.bioinfo.infrared.lib.impl.hibernate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.lib.common.IntervalFeatureFrequency;
import org.bioinfo.infrared.lib.common.Region;
import org.bioinfo.infrared.lib.impl.DBAdaptor;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class HibernateDBAdaptor extends DBAdaptor{

	protected String species;
	protected String version;
	
	private SessionFactory sessionFactory;
	private Session session;

	//	public HibernateDBAdaptor() {
	//
	//	}

	protected static Map<String, Integer> cachedQuerySizes = new HashMap<String, Integer>();
	
	public HibernateDBAdaptor(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public HibernateDBAdaptor(SessionFactory sessionFactory, String species, String version) {
		this.sessionFactory = sessionFactory;
		this.species = species;
		this.version = version;
	}

	protected Session openSession() {
		if(session == null) {
			logger.debug("HibernateDBAdaptor: Session is null");
			session = sessionFactory.openSession();
		}else {
			if(!session.isOpen()) {
				logger.debug("HibernateDBAdaptor: Session is closed");
				session = sessionFactory.openSession();
			}else {
				logger.debug("HibernateDBAdaptor: Session is already open");
			}
		}

		return session;
	}

	protected List<?> execute(Query query){
		List<?> result = query.list();
		return result;
	}


	protected List<?> executeAndClose(Criteria criteria){
		List<?> result = criteria.list();
		closeSession();
		return result;
	}

	protected List<?> executeAndClose(Query query){
		List<?> result = query.list();
		closeSession();
		return result;
	}

	protected void closeSession() {
		if(session != null && session.isOpen()) {
			session.close();
		}
	}
	
	/**
	 * For histograms
	 */
	protected List<IntervalFeatureFrequency> getIntervalFeatureFrequencies(Region region , int interval, List<Object[]> objectList, int numFeatures) {
		
		int numIntervals = (region.getEnd()-region.getStart())/interval +1;
		List<IntervalFeatureFrequency> intervalFeatureFrequenciesList = new ArrayList<IntervalFeatureFrequency>(numIntervals);
		
		BigInteger max = new BigInteger("-1");
		for(int i=0; i<objectList.size(); i++) {
			if(((BigInteger)objectList.get(i)[1]).compareTo(max) > 0) {
				max = (BigInteger)objectList.get(i)[1];
			}
		}
		float maxNormValue = 1;
		
		if(numFeatures != 0) {
			maxNormValue = max.floatValue() / numFeatures;			
		}
		
		int start = region.getStart();
		int end = start + interval;
		for(int i=0, j=0; i < numIntervals; i++) {
			if(j < objectList.size() && ((BigInteger)objectList.get(j)[0]).intValue() == i) {
				if(numFeatures != 0) {
					intervalFeatureFrequenciesList.add(new IntervalFeatureFrequency(start, end, ((BigInteger)objectList.get(j)[0]).intValue()
							,((BigInteger)objectList.get(j)[1]).intValue() 
							,((BigInteger)objectList.get(j)[1]).floatValue() / numFeatures / maxNormValue));
				}else {	// no features for this chromosome
					intervalFeatureFrequenciesList.add(new IntervalFeatureFrequency(start, end, ((BigInteger)objectList.get(j)[0]).intValue()
							,((BigInteger)objectList.get(j)[1]).intValue() 
							,0));
				}
				j++;
			}else {
				intervalFeatureFrequenciesList.add(new IntervalFeatureFrequency(start, end, i, 0, 0.0f));
			}
			System.out.println(intervalFeatureFrequenciesList.get(i).getStart()+":"+intervalFeatureFrequenciesList.get(i).getEnd()+":"+intervalFeatureFrequenciesList.get(i).getInterval()+":"+ intervalFeatureFrequenciesList.get(i).getAbsolute()+":"+intervalFeatureFrequenciesList.get(i).getValue());
			
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
	
	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

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


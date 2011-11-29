package org.bioinfo.infrared.lib.impl.hibernate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.bioinfo.commons.Config;
import org.bioinfo.infrared.lib.api.ExonDBAdaptor;
import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.api.ProteinDBAdaptor;
import org.bioinfo.infrared.lib.api.SnpDBAdaptor;
import org.bioinfo.infrared.lib.api.TranscriptDBAdaptor;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateDBAdaptorFactory extends DBAdaptorFactory {

//	private static Map<String, HibernateDBAdaptor> sessionFactories;
	private static Map<String, SessionFactory> sessionFactories;
	private static Config applicationProperties;
	private static ResourceBundle resourceBundle;

	static {
//		sessionFactories = new HashMap<String, HibernateDBAdaptor>(20);
		sessionFactories = new HashMap<String, SessionFactory>(10);

		// reading application.properties file
		resourceBundle = ResourceBundle.getBundle("org.bioinfo.infrared.lib.impl.hibernate.conf.application");
		try {
			applicationProperties = new Config(resourceBundle);
		} catch (IOException e) {
			applicationProperties = new Config();
			e.printStackTrace();
		}
	}

	private String getSpeciesVersionPrefix(String species, String version) {
		String speciesPrefix = null;
		if(species != null && !species.equals("")) {
			// coding an alias to application code species
			species = speciesAlias.get(species);

			// if 'version' parameter has not been provided the default version is selected
			if(version == null || version.trim().equals("")) {
				version = applicationProperties.getProperty(species+".DEFAULT.VERSION").toUpperCase();
				logger.debug("HibernateDBAdaptorFactory in createSessionFactory(): 'version' parameter is null or empty, it's been set to: '"+version+"'");
			}

			// setting database configuration for the 'species.version'
			speciesPrefix = species.toUpperCase() + "." + version.toUpperCase();
		}
		return speciesPrefix;
	}

	private SessionFactory createSessionFactory(String speciesVersionPrefix) {
		logger.debug("HibernateDBAdaptorFactory in getGeneDBAdaptor(): creating Hibernate SessionFactory object for SPECIES.VERSION: '"+speciesVersionPrefix+"' ...");
		long t1 = System.currentTimeMillis();

		// initial load and setup from hibernate.cfg.xml
		Configuration cfg = new Configuration().configure("cell_db_v1_hibernate.cfg.xml");
		if(speciesVersionPrefix != null && !speciesVersionPrefix.trim().equals("")) {
			// read DB configuration for that SPECIES.VERSION, by default PRIMARY_DB is selected 
			String dbPrefix = applicationProperties.getProperty(speciesVersionPrefix+".DB", "PRIMARY_DB");
			cfg.setProperty("hibernate.default_catalog", applicationProperties.getProperty(speciesVersionPrefix+".DATABASE"));
			cfg.setProperty("hibernate.connection.username", applicationProperties.getProperty(dbPrefix + ".USERNAME"));
			cfg.setProperty("hibernate.connection.password", applicationProperties.getProperty(dbPrefix + ".PASSWORD"));
			cfg.setProperty("hibernate.connection.url", "jdbc:"+ applicationProperties.getProperty(dbPrefix+".DRIVER_CLASS")+"://"+applicationProperties.getProperty(dbPrefix + ".HOST")+":"+applicationProperties.getProperty(dbPrefix + ".PORT", "3306")+"/"+applicationProperties.getProperty(speciesVersionPrefix+".DATABASE"));
//			cfg.setProperty("hibernate.connection.pool_size", "30");
		}else {
			logger.debug("HibernateDBAdaptorFactory in createSessionFactory(): 'species' parameter is null or empty");
		}

		SessionFactory sessionFactory = cfg.buildSessionFactory();
		//logger.debug("HibernateDBAdaptorFactory in getGeneDBAdaptor(): Hibernate SessionFactory object for '"+speciesVersionPrefix+"' created in "+(System.currentTimeMillis()-t1)+" ms");

		return sessionFactory;
	}


	@Override
	public void setConfiguration(Properties properties) {
		if(properties != null) {
			if(applicationProperties == null) {
				applicationProperties = new Config();
			}
			for(Object key: properties.keySet()) {
				applicationProperties.setProperty((String) key, properties.getProperty((String) key));
			}
		}
	}

	@Override
	public void close() {
//		for(HibernateDBAdaptor adaptor: sessionFactories.values()) {
//			if(adaptor != null && adaptor.getSessionFactory() != null && !adaptor.getSessionFactory().isClosed()) {
//				adaptor.getSessionFactory().close();
//			}
//		}
		for(SessionFactory adaptor: sessionFactories.values()) {
			if(adaptor != null && !adaptor.isClosed()) {
				adaptor.close();
			}
		}
	}


	@Override
	public GeneDBAdaptor getGeneDBAdaptor(String species) {
		return getGeneDBAdaptor(species, null);
	}

	@Override
	public GeneDBAdaptor getGeneDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)) {
			SessionFactory sessionFactory = createSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (GeneDBAdaptor) new GeneHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix));
	}


	@Override
	public TranscriptDBAdaptor getTranscriptDBAdaptor(String species) {
		return getTranscriptDBAdaptor(species, null);
	}
	
	@Override
	public TranscriptDBAdaptor getTranscriptDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species,version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)){
			SessionFactory sessionFactory  = createSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (TranscriptDBAdaptor) new TranscriptHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix));
	}
	
	
	@Override
	public ExonDBAdaptor getExonDBAdaptor(String species) {
		return getExonDBAdaptor(species, null);
	}
	@Override
	public ExonDBAdaptor getExonDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species,version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)){
			SessionFactory sessionFactory  = createSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (ExonDBAdaptor) new ExonHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix));
	}

	@Override
	public GenomicRegionFeatureHibernateDBAdaptor getFeatureMapDBAdaptor(String species) {
		return getFeatureMapDBAdaptor(species, null);
	}
	@Override
	public GenomicRegionFeatureHibernateDBAdaptor getFeatureMapDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species,version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)){
			SessionFactory sessionFactory  = createSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (GenomicRegionFeatureHibernateDBAdaptor) new GenomicRegionFeatureHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix));
	}
	
	
	@Override
	public ProteinDBAdaptor getProteinDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ProteinDBAdaptor getProteinDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	@Override
	public SnpDBAdaptor getSnpDBAdaptor(String species) {
		return getSnpDBAdaptor(species, null);
	}
	
	@Override
	public SnpDBAdaptor getSnpDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)) {
			SessionFactory sessionFactory = createSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		
		return (SnpDBAdaptor) new SnpHibernateDBAdapator(sessionFactories.get(speciesVersionPrefix));
	}

	
	@Override
	public GenomeSequenceDBAdaptor getGenomeSequenceDBAdaptor(String species) {
		return getGenomeSequenceDBAdaptor(species, null);
	}
	
	@Override
	public GenomeSequenceDBAdaptor getGenomeSequenceDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)) {
			SessionFactory sessionFactory = createSessionFactory(speciesVersionPrefix);
//			sessionFactories.put(speciesVersionPrefix, new GenomeSequenceDBAdaptor(sessionFactory));
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
//		return (GenomeSequenceDBAdaptor) sessionFactories.get(speciesVersionPrefix);
		return (GenomeSequenceDBAdaptor) new GenomeSequenceDBAdaptor(sessionFactories.get(speciesVersionPrefix));
	}

	
	@Override
	public GeneDBAdaptor getCytobandDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneDBAdaptor getChromosomeDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	@Override
	public GeneDBAdaptor getXRefDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}


}

package org.bioinfo.infrared.lib.impl.hibernate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.bioinfo.commons.Config;
import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.impl.DBAdaptorFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateDBAdaptorFactory extends DBAdaptorFactory {

	//	public static GeneDBAdaptor getGeneDBAdaptor(String species) {
	//		return null;
	//	}

	private static Map<String, HibernateDBAdaptor> adaptors;
	private static Config applicationProperties;
	private static ResourceBundle resourceBundle;

	static {
		adaptors = new HashMap<String, HibernateDBAdaptor>(20);

		// reading application.properties file
		resourceBundle = ResourceBundle.getBundle("org.bioinfo.infrared.lib.impl.hibernate.conf.application");
		try {
			applicationProperties = new Config(resourceBundle);
		} catch (IOException e) {
			applicationProperties = new Config();
			e.printStackTrace();
		}
	}

	private SessionFactory init(String key) {
		//		InputStream is = this.getClass().getResourceAsStream("conf/hibernate.cfg.xml");
		//		String hibernateXfgXML = IOUtils.toString(is);
		//		logger.debug(hibernateXfgXML);
		//		this.sessionFactory = cfg.configure(hibernateXfgXML).buildSessionFactory();
		Configuration cfg = new Configuration().configure();

		// setting database configuration for the 'species.version'
		if(key != null && resourceBundle.containsKey(key.toUpperCase()+".DATABASE")) {
			String prefix = key.toUpperCase()+".";
			cfg.setProperty("hibernate.connection.username", getApplicationPropertyValue(prefix+"USERNAME"));
			cfg.setProperty("hibernate.connection.password", getApplicationPropertyValue(prefix+"PASSWORD"));
			cfg.setProperty("hibernate.connection.url", resourceBundle.getString("PASSWORD"));
			cfg.setProperty("hibernate.connection.username", getApplicationPropertyValue(prefix+"USERNAME"));
			cfg.setProperty("hibernate.connection.pool_size", getApplicationPropertyValue(prefix+"POOL_SIZE"));
			


			cfg.setProperty("hibernate.default_catalog", resourceBundle.getString("PASSWORD"));
			
		}

		SessionFactory sessionFactory = cfg.buildSessionFactory();
		return sessionFactory;
	}
	
	@Deprecated
	private String getApplicationPropertyValue(String key) {
		if(applicationProperties.containsKey(applicationProperties.getProperty(key))) {
			return applicationProperties.getProperty(applicationProperties.getProperty(key));
		}else {
			return applicationProperties.getProperty(key, "not found");
		}
	}

	@Override
	public void setConfiguration(Properties properties) {
		// TODO Auto-generated method stub	
	}

	@Override
	public GeneDBAdaptor getGeneDBAdaptor(String species) {
		if(!adaptors.containsKey(species)) {
			SessionFactory s = init();
			adaptors.put(species, new GeneHibernateDBAdaptor(s));
		}
		return (GeneDBAdaptor) adaptors.get(species);
	}

	@Override
	public GeneDBAdaptor getTranscriptDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneDBAdaptor getExonDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneDBAdaptor getProteinDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneDBAdaptor getSnpDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
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
	public GeneDBAdaptor getGenomeDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneDBAdaptor getXRefDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

}

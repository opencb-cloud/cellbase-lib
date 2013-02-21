package org.bioinfo.cellbase.lib.impl.hibernate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.bioinfo.cellbase.lib.api.BioPaxDBAdaptor;
import org.bioinfo.cellbase.lib.api.CpGIslandDBAdaptor;
import org.bioinfo.cellbase.lib.api.CytobandDBAdaptor;
import org.bioinfo.cellbase.lib.api.ExonDBAdaptor;
import org.bioinfo.cellbase.lib.api.GeneDBAdaptor;
import org.bioinfo.cellbase.lib.api.GenomeSequenceDBAdaptor;
import org.bioinfo.cellbase.lib.api.GenomicVariantEffectDBAdaptor;
import org.bioinfo.cellbase.lib.api.MirnaDBAdaptor;
import org.bioinfo.cellbase.lib.api.MutationDBAdaptor;
import org.bioinfo.cellbase.lib.api.PathwayDBAdaptor;
import org.bioinfo.cellbase.lib.api.ProteinDBAdaptor;
import org.bioinfo.cellbase.lib.api.RegulatoryRegionDBAdaptor;
import org.bioinfo.cellbase.lib.api.SnpDBAdaptor;
import org.bioinfo.cellbase.lib.api.StructuralVariationDBAdaptor;
import org.bioinfo.cellbase.lib.api.TfbsDBAdaptor;
import org.bioinfo.cellbase.lib.api.TranscriptDBAdaptor;
import org.bioinfo.cellbase.lib.api.XRefsDBAdaptor;
import org.bioinfo.cellbase.lib.impl.DBAdaptorFactory;
import org.bioinfo.commons.Config;
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
		resourceBundle = ResourceBundle.getBundle("org.bioinfo.cellbase.lib.impl.hibernate.conf.application");
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
//				logger.debug("HibernateDBAdaptorFactory in createSessionFactory(): 'version' parameter is null or empty, it's been set to: '"+version+"'");
			}

			// setting database configuration for the 'species.version'
			speciesPrefix = species.toUpperCase() + "." + version.toUpperCase();
		}
		
		return speciesPrefix;
	}

	private SessionFactory createCellBaseSessionFactory(String speciesVersionPrefix) {
//		logger.debug("HibernateDBAdaptorFactory in getGeneDBAdaptor(): creating Hibernate SessionFactory object for SPECIES.VERSION: '"+speciesVersionPrefix+"' ...");
//		long t1 = System.currentTimeMillis();
System.out.println(speciesVersionPrefix+"=>"+applicationProperties.getProperty(speciesVersionPrefix+".DATABASE"));
		// initial load and setup from hibernate.cfg.xml
		Configuration cfg = new Configuration().configure("cellbase-hibernate.cfg.xml");
		if(speciesVersionPrefix != null && !speciesVersionPrefix.trim().equals("")) {
			// read DB configuration for that SPECIES.VERSION, by default PRIMARY_DB is selected 
			String dbPrefix = applicationProperties.getProperty(speciesVersionPrefix+".DB", "PRIMARY_DB");
			cfg.setProperty("hibernate.default_catalog", applicationProperties.getProperty(speciesVersionPrefix+".DATABASE"));
			cfg.setProperty("hibernate.connection.username", applicationProperties.getProperty(dbPrefix + ".USERNAME"));
			cfg.setProperty("hibernate.connection.password", applicationProperties.getProperty(dbPrefix + ".PASSWORD"));
			cfg.setProperty("hibernate.connection.url", "jdbc:"+ applicationProperties.getProperty(dbPrefix+".DRIVER_CLASS")+"://"+applicationProperties.getProperty(dbPrefix + ".HOST")+":"+applicationProperties.getProperty(dbPrefix + ".PORT", "3308")+"/"+applicationProperties.getProperty(speciesVersionPrefix+".DATABASE"));
//			cfg.setProperty("hibernate.connection.pool_size", "30");
		}else {
			logger.debug("HibernateDBAdaptorFactory in createSessionFactory(): 'species' parameter is null or empty");
		}

		SessionFactory sessionFactory = cfg.buildSessionFactory();
		//logger.debug("HibernateDBAdaptorFactory in getGeneDBAdaptor(): Hibernate SessionFactory object for '"+speciesVersionPrefix+"' created in "+(System.currentTimeMillis()-t1)+" ms");

		return sessionFactory;
	}

	private SessionFactory createBioPaxSessionFactory(String speciesVersionPrefix) {
//		logger.debug("HibernateDBAdaptorFactory in getGeneDBAdaptor(): creating Hibernate SessionFactory object for SPECIES.VERSION: '"+speciesVersionPrefix+"' ...");
//		long t1 = System.currentTimeMillis();

		// initial load and setup from hibernate.cfg.xml
		Configuration cfg = new Configuration().configure("biopax-hibernate.cfg.xml");
		if(speciesVersionPrefix != null && !speciesVersionPrefix.trim().equals("")) {
			// read DB configuration for that SPECIES.VERSION, by default PRIMARY_DB is selected 
			String dbPrefix = "BIOPAX_DB";
			cfg.setProperty("hibernate.default_catalog", applicationProperties.getProperty(dbPrefix+".DATABASE"));
			cfg.setProperty("hibernate.connection.username", applicationProperties.getProperty(dbPrefix + ".USERNAME"));
			cfg.setProperty("hibernate.connection.password", applicationProperties.getProperty(dbPrefix + ".PASSWORD"));
			cfg.setProperty("hibernate.connection.url", "jdbc:"+ applicationProperties.getProperty(dbPrefix+".DRIVER_CLASS")+"://"+applicationProperties.getProperty(dbPrefix + ".HOST")+":"+applicationProperties.getProperty(dbPrefix + ".PORT", "3308")+"/"+applicationProperties.getProperty(dbPrefix+".DATABASE"));
//			cfg.setProperty("hibernate.connection.pool_size", "30");
		}else {
			logger.debug("HibernateDBAdaptorFactory in createSessionFactory(): 'species' parameter is null or empty");
		}

		logger.debug("creating BioPax SessionFactory");
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		logger.debug("creating BioPax SessionFactory... done");
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
	public void open(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)) {
			SessionFactory sessionFactory = createCellBaseSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
	}

	@Override
	public void close() {
//		for(HibernateDBAdaptor adaptor: sessionFactories.values()) {
//			if(adaptor != null && adaptor.getSessionFactory() != null && !adaptor.getSessionFactory().isClosed()) {
//				adaptor.getSessionFactory().close();
//			}
//		}
		for(SessionFactory sessionFactory: sessionFactories.values()) {
			if(sessionFactory != null && !sessionFactory.isClosed()) {
				sessionFactory.close();
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
			SessionFactory sessionFactory = createCellBaseSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (GeneDBAdaptor) new GeneHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix), speciesAlias.get(species), version);
	}


	@Override
	public TranscriptDBAdaptor getTranscriptDBAdaptor(String species) {
		return getTranscriptDBAdaptor(species, null);
	}
	
	@Override
	public TranscriptDBAdaptor getTranscriptDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species,version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)){
			SessionFactory sessionFactory  = createCellBaseSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (TranscriptDBAdaptor) new TranscriptHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix), speciesAlias.get(species), version);
	}
	
	
	@Override
	public ExonDBAdaptor getExonDBAdaptor(String species) {
		return getExonDBAdaptor(species, null);
	}
	@Override
	public ExonDBAdaptor getExonDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species,version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)){
			SessionFactory sessionFactory  = createCellBaseSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (ExonDBAdaptor) new ExonHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix), speciesAlias.get(species), version);
	}

	
//	@Override
//	public GenomicRegionFeatureHibernateDBAdaptor getFeatureMapDBAdaptor(String species) {
//		return getFeatureMapDBAdaptor(species, null);
//	}
//	@Override
//	public GenomicRegionFeatureHibernateDBAdaptor getFeatureMapDBAdaptor(String species, String version) {
//		String speciesVersionPrefix = getSpeciesVersionPrefix(species,version);
//		if(!sessionFactories.containsKey(speciesVersionPrefix)){
//			SessionFactory sessionFactory  = createCellBaseSessionFactory(speciesVersionPrefix);
//			sessionFactories.put(speciesVersionPrefix, sessionFactory);
//		}
//		return (GenomicRegionFeatureHibernateDBAdaptor) new GenomicRegionFeatureHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix), species, version);
//	}
	
	
	@Override
	public GenomicVariantEffectDBAdaptor getGenomicVariantEffectDBAdaptor(String species) {
		return getGenomicVariantEffectDBAdaptor(species, null);
	}
	@Override
	public GenomicVariantEffectDBAdaptor getGenomicVariantEffectDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species,version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)) {
			SessionFactory sessionFactory  = createCellBaseSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (GenomicVariantEffectDBAdaptor) new GenomicVariantEffectHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix), speciesAlias.get(species), version);
	}
	
	
	@Override
	public ProteinDBAdaptor getProteinDBAdaptor(String species) {
		return getProteinDBAdaptor(species, null);
	}
	
	@Override
	public ProteinDBAdaptor getProteinDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species,version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)){
			SessionFactory sessionFactory  = createCellBaseSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (ProteinDBAdaptor) new ProteinHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix), speciesAlias.get(species), version);
	}

	
	@Override
	public SnpDBAdaptor getSnpDBAdaptor(String species) {
		return getSnpDBAdaptor(species, null);
	}
	
	@Override
	public SnpDBAdaptor getSnpDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)) {
			SessionFactory sessionFactory = createCellBaseSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
//		else {
//			if(sessionFactories.get(speciesVersionPrefix).isClosed()) {
//				SessionFactory sessionFactory = createSessionFactory(speciesVersionPrefix);
//				sessionFactories.put(speciesVersionPrefix, sessionFactory);
//			}
//		}
		
		return (SnpDBAdaptor) new SnpHibernateDBAdapator(sessionFactories.get(speciesVersionPrefix), speciesAlias.get(species), version);
	}

	
	@Override
	public GenomeSequenceDBAdaptor getGenomeSequenceDBAdaptor(String species) {
		return getGenomeSequenceDBAdaptor(species, null);
	}
	
	@Override
	public GenomeSequenceDBAdaptor getGenomeSequenceDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)) {
			SessionFactory sessionFactory = createCellBaseSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (GenomeSequenceDBAdaptor) new GenomeSequenceHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix), speciesAlias.get(species), version);
	}

	@Override
	public CytobandDBAdaptor getCytobandDBAdaptor(String species){
		return getCytobandDBAdaptor(species, null);
		
	}
	
	@Override
	public CytobandDBAdaptor getCytobandDBAdaptor(String species, String version){
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)) {
			System.out.println(speciesVersionPrefix);
			SessionFactory sessionFactory = createCellBaseSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (CytobandDBAdaptor) new CytobandHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix), speciesAlias.get(species), version);
	}
	

	@Override
	public GeneDBAdaptor getChromosomeDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public XRefsDBAdaptor getXRefDBAdaptor(String species){
		return getXRefDBAdaptor(species, null);
	}
	
	@Override
	public XRefsDBAdaptor getXRefDBAdaptor(String species, String version){
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)) {
			SessionFactory sessionFactory = createCellBaseSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (XRefsDBAdaptor) new XRefsHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix), speciesAlias.get(species), version);
	}

	
	@Override
	public TfbsDBAdaptor getTfbsDBAdaptor(String species) {
		return getTfbsDBAdaptor(species, null);
	}

	@Override
	public TfbsDBAdaptor getTfbsDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)) {
			SessionFactory sessionFactory = createCellBaseSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (TfbsDBAdaptor) new TfbsHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix), speciesAlias.get(species), version);
	}
	
	
	@Override
	public RegulatoryRegionDBAdaptor getRegulatoryRegionDBAdaptor(String species) {
		return getRegulatoryRegionDBAdaptor(species, null);
	}

	@Override
	public RegulatoryRegionDBAdaptor getRegulatoryRegionDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)) {
			SessionFactory sessionFactory = createCellBaseSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (RegulatoryRegionDBAdaptor) new RegulatoryRegionHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix), speciesAlias.get(species), version);
	}

	
	@Override
	public MirnaDBAdaptor getMirnaDBAdaptor(String species) {
		return this.getMirnaDBAdaptor(species, null);
	}

	@Override
	public MirnaDBAdaptor getMirnaDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)) {
			SessionFactory sessionFactory = createCellBaseSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
//		System.out.println("sessionFactories " + sessionFactories.get(speciesVersionPrefix));
		return (MirnaHibernateDBAdaptor) new MirnaHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix), speciesAlias.get(species), version);
	}

	
	@Override
	public BioPaxDBAdaptor getBioPaxDBAdaptor(String species) {
		return this.getBioPaxDBAdaptor(species, null);
	}

	@Override
	public BioPaxDBAdaptor getBioPaxDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version)+".BP";
		if(!sessionFactories.containsKey(speciesVersionPrefix)) {
			SessionFactory sessionFactory = createBioPaxSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (BioPaxDBAdaptor) new BioPaxHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix), speciesAlias.get(species), version);
	}
	
	@Override
	public MutationDBAdaptor getMutationDBAdaptor(String species) {
		return this.getMutationDBAdaptor(species, null);
	}

	@Override
	public MutationDBAdaptor getMutationDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)) {
			SessionFactory sessionFactory = createCellBaseSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (MutationHibernateDBAdaptor) new MutationHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix), speciesAlias.get(species), version);
	}
	
	
	@Override
	public CpGIslandDBAdaptor getCpGIslandDBAdaptor(String species) {
		return this.getCpGIslandDBAdaptor(species, null);
	}

	@Override
	public CpGIslandDBAdaptor getCpGIslandDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)) {
			SessionFactory sessionFactory = createCellBaseSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (CpGIslandHibernateDBAdaptor) new CpGIslandHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix), speciesAlias.get(species), version);
	}

	
	@Override
	public StructuralVariationDBAdaptor getStructuralVariationDBAdaptor(String species) {
		return this.getStructuralVariationDBAdaptor(species, null);
	}
	
	@Override
	public StructuralVariationDBAdaptor getStructuralVariationDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if(!sessionFactories.containsKey(speciesVersionPrefix)) {
			SessionFactory sessionFactory = createCellBaseSessionFactory(speciesVersionPrefix);
			sessionFactories.put(speciesVersionPrefix, sessionFactory);
		}
		return (StructuralVariationDBAdaptor) new StructuralVariationHibernateDBAdaptor(sessionFactories.get(speciesVersionPrefix), speciesAlias.get(species), version);
	}

	@Override
	public PathwayDBAdaptor getPathwayDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PathwayDBAdaptor getPathwayDBAdaptor(String species,
			String version) {
		// TODO Auto-generated method stub
		return null;
	}

}

package org.bioinfo.cellbase.lib.impl.mongodb;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.bioinfo.cellbase.lib.api.ChromosomeDBAdaptor;
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

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

public class MongoDBAdaptorFactory extends DBAdaptorFactory {

	private static Map<String, DB> mongoDBFactory;
	// private static Config applicationProperties;
	private static ResourceBundle resourceBundle;

	static {
		// mongoDBFactory = new HashMap<String, HibernateDBAdaptor>(20);
		mongoDBFactory = new HashMap<String, DB>(10);

		// reading application.properties file
		resourceBundle = ResourceBundle.getBundle("org.bioinfo.cellbase.lib.impl.mongodb.conf.application");
		try {
			applicationProperties = new Config(resourceBundle);
		} catch (IOException e) {
			applicationProperties = new Config();
			e.printStackTrace();
		}
	}

	private DB createCellBaseMongoDB(String speciesVersionPrefix) {
		// logger.debug("HibernateDBAdaptorFactory in getGeneDBAdaptor(): creating Hibernate SessionFactory object for SPECIES.VERSION: '"+speciesVersionPrefix+"' ...");
		// long t1 = System.currentTimeMillis();
		System.out.println(speciesVersionPrefix + "=>"
				+ applicationProperties.getProperty(speciesVersionPrefix + ".DATABASE"));
		// initial load and setup from hibernate.cfg.xml
		// Configuration cfg = new
		// Configuration().configure("cellbase-hibernate.cfg.xml");
		MongoClient mc = null;
		DB db = null;
		if (speciesVersionPrefix != null && !speciesVersionPrefix.trim().equals("")) {
			// read DB configuration for that SPECIES.VERSION, by default
			// PRIMARY_DB is selected
			String dbPrefix = applicationProperties.getProperty(speciesVersionPrefix + ".DB", "PRIMARY_DB");
			try {
				MongoClientOptions mongoClientOptions = new MongoClientOptions.Builder()
						.connectionsPerHost(
								applicationProperties.getIntProperty(speciesVersionPrefix + ".MAX_POOL_SIZE", 10))
						.connectTimeout(applicationProperties.getIntProperty(speciesVersionPrefix + ".TIMEOUT", 10000))
						.build();

				mc = new MongoClient(new ServerAddress(applicationProperties.getProperty(dbPrefix + ".HOST",
						"localhost"), applicationProperties.getIntProperty(dbPrefix + ".PORT", 27017)),
						mongoClientOptions);
				System.out.println(applicationProperties.getProperty(speciesVersionPrefix + ".DATABASE"));
				db = mc.getDB(applicationProperties.getProperty(speciesVersionPrefix + ".DATABASE"));
				// db.authenticate(applicationProperties.getProperty(dbPrefix+".USERNAME"),
				// applicationProperties.getProperty(dbPrefix+".PASSWORD").toCharArray());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		} else {
			logger.debug("HibernateDBAdaptorFactory in createSessionFactory(): 'species' parameter is null or empty");
		}

		return db;
	}

	@Override
	public void setConfiguration(Properties properties) {
		if (properties != null) {
			if (applicationProperties == null) {
				applicationProperties = new Config();
			}
			for (Object key : properties.keySet()) {
				applicationProperties.setProperty((String) key, properties.getProperty((String) key));
			}
		}
	}

	@Override
	public void open(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if (!mongoDBFactory.containsKey(speciesVersionPrefix)) {
			DB db = createCellBaseMongoDB(speciesVersionPrefix);
			mongoDBFactory.put(speciesVersionPrefix, db);
		}
	}

	@Override
	public void close() {
		for (DB sessionFactory : mongoDBFactory.values()) {
			if (sessionFactory != null) {
				sessionFactory.cleanCursors(true);
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
		if (!mongoDBFactory.containsKey(speciesVersionPrefix)) {
			DB db = createCellBaseMongoDB(speciesVersionPrefix);
			mongoDBFactory.put(speciesVersionPrefix, db);
		}
		return (GeneDBAdaptor) new GeneMongoDBAdaptor(mongoDBFactory.get(speciesVersionPrefix),
				speciesAlias.get(species), version);
	}

	@Override
	public TranscriptDBAdaptor getTranscriptDBAdaptor(String species) {
		return getTranscriptDBAdaptor(species, null);
	}

	@Override
	public TranscriptDBAdaptor getTranscriptDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if (!mongoDBFactory.containsKey(speciesVersionPrefix)) {
			DB db = createCellBaseMongoDB(speciesVersionPrefix);
			mongoDBFactory.put(speciesVersionPrefix, db);
		}
		return (TranscriptDBAdaptor) new TranscriptMongoDBAdaptor(mongoDBFactory.get(speciesVersionPrefix),
				speciesAlias.get(species), version);
	}

	@Override
	public ChromosomeDBAdaptor getChromosomeDBAdaptor(String species) {
		return getChromosomeDBAdaptor(species, null);
	}

	@Override
	public ChromosomeDBAdaptor getChromosomeDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if (!mongoDBFactory.containsKey(speciesVersionPrefix)) {
			DB db = createCellBaseMongoDB(speciesVersionPrefix);
			mongoDBFactory.put(speciesVersionPrefix, db);
		}
		return (ChromosomeDBAdaptor) new ChromosomeMongoDBAdaptor(mongoDBFactory.get(speciesVersionPrefix),
				speciesAlias.get(species), version);
	}

	@Override
	public ExonDBAdaptor getExonDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExonDBAdaptor getExonDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenomicVariantEffectDBAdaptor getGenomicVariantEffectDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenomicVariantEffectDBAdaptor getGenomicVariantEffectDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SnpDBAdaptor getSnpDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenomeSequenceDBAdaptor getGenomeSequenceDBAdaptor(String species) {
		return getGenomeSequenceDBAdaptor(species, null);
	}

	@Override
	public GenomeSequenceDBAdaptor getGenomeSequenceDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if (!mongoDBFactory.containsKey(speciesVersionPrefix)) {
			DB db = createCellBaseMongoDB(speciesVersionPrefix);
			mongoDBFactory.put(speciesVersionPrefix, db);
		}
		return (GenomeSequenceDBAdaptor) new GenomeSequenceMongoDBAdaptor(mongoDBFactory.get(speciesVersionPrefix),
				speciesAlias.get(species), version);
	}

	@Override
	public CytobandDBAdaptor getCytobandDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CytobandDBAdaptor getCytobandDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XRefsDBAdaptor getXRefDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XRefsDBAdaptor getXRefDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TfbsDBAdaptor getTfbsDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TfbsDBAdaptor getTfbsDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegulatoryRegionDBAdaptor getRegulatoryRegionDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegulatoryRegionDBAdaptor getRegulatoryRegionDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MirnaDBAdaptor getMirnaDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MirnaDBAdaptor getMirnaDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MutationDBAdaptor getMutationDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MutationDBAdaptor getMutationDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CpGIslandDBAdaptor getCpGIslandDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CpGIslandDBAdaptor getCpGIslandDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StructuralVariationDBAdaptor getStructuralVariationDBAdaptor(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StructuralVariationDBAdaptor getStructuralVariationDBAdaptor(String species, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PathwayDBAdaptor getPathwayDBAdaptor(String species) {
		return getPathwayDBAdaptor(species, null);
	}

	@Override
	public PathwayDBAdaptor getPathwayDBAdaptor(String species, String version) {
		String speciesVersionPrefix = getSpeciesVersionPrefix(species, version);
		if (!mongoDBFactory.containsKey(speciesVersionPrefix)) {
			DB db = createCellBaseMongoDB(speciesVersionPrefix);
			mongoDBFactory.put(speciesVersionPrefix, db);
		}
		return (PathwayDBAdaptor) new PathwayMongoDBAdaptor(mongoDBFactory.get(speciesVersionPrefix),
				speciesAlias.get(species), version);
	}

}

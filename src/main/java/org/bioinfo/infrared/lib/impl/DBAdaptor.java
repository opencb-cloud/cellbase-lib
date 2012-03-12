package org.bioinfo.infrared.lib.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.bioinfo.commons.Config;
import org.bioinfo.commons.log.Logger;
import org.hibernate.SessionFactory;

public abstract class DBAdaptor {

	protected Logger logger;
	
	private static ResourceBundle resourceBundle;
	protected static Config applicationProperties;
	
	static {
		// reading application.properties file
		resourceBundle = ResourceBundle.getBundle("org.bioinfo.infrared.lib.impl.hibernate.conf.application");
		try {
			applicationProperties = new Config(resourceBundle);
		} catch (IOException e) {
			applicationProperties = new Config();
			e.printStackTrace();
		}
	}
	
	public DBAdaptor() {
		logger = new Logger();
		logger.setLevel(Logger.INFO_LEVEL);
	}
	
	public Logger getLogger() {
		return logger;
	}
}

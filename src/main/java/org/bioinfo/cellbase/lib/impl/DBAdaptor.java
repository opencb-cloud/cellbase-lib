package org.bioinfo.cellbase.lib.impl;

import java.util.ResourceBundle;

import org.bioinfo.commons.Config;
import org.bioinfo.commons.log.Logger;

public abstract class DBAdaptor {

	protected Logger logger;
	
	protected static ResourceBundle resourceBundle;
	protected static Config applicationProperties;
	
	
	public DBAdaptor() {
		logger = new Logger();
		logger.setLevel(Logger.INFO_LEVEL);
	}
	
	public Logger getLogger() {
		return logger;
	}
}

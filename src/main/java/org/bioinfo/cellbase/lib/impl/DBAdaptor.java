package org.bioinfo.cellbase.lib.impl;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.bioinfo.commons.Config;

public abstract class DBAdaptor {

	protected Logger logger= Logger.getLogger(this.getClass().getSimpleName());
	
	protected static ResourceBundle resourceBundle;
	protected static Config applicationProperties;
	
	
	public DBAdaptor() {
//		logger = new Logger();
//		logger.setLevel(Logger.INFO_LEVEL);
	}
	
	public Logger getLogger() {
		return logger;
	}
}

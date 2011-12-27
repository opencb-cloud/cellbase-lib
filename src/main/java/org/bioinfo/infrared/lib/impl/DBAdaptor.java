package org.bioinfo.infrared.lib.impl;

import org.bioinfo.commons.log.Logger;

public abstract class DBAdaptor {

	protected Logger logger;
	
	public DBAdaptor() {
		logger = new Logger();
		logger.setLevel(Logger.INFO_LEVEL);
	}
	
	public Logger getLogger() {
		return logger;
	}
}

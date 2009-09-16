package org.bioinfo.infrared.tools;

import org.apache.commons.cli.OptionGroup;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.tool.GenericBioTool;
import org.bioinfo.tool.OptionFactory;

public abstract class InfraredTool extends GenericBioTool{

	protected DBConnector infraredDBConnector;
	protected String[] args;
	
	public InfraredTool(String[] args) {
		this.args = args;
		initCommonsOptions();
	}
	
	public abstract void initOptions();
	
	
	protected void initCommonsOptions() {
		options.addOption(OptionFactory.createOption("outdir", "o",  "outdir to store the results"));
		options.addOption(OptionFactory.createOption("species", "s", "The specie of the ids"));
		options.addOption(OptionFactory.createOption("tool", "t", "tool name"));
		options.addOption(OptionFactory.createOption("log", "l", "tool name", false));
		
		OptionGroup inputData = new OptionGroup();
		inputData.addOption(OptionFactory.createOption("outdir", "o",  "outdir to store the results"));
		inputData.addOption(OptionFactory.createOption("outdir", "o",  "outdir to store the results"));
		inputData.addOption(OptionFactory.createOption("outdir", "o",  "outdir to store the results"));
		inputData.addOption(OptionFactory.createOption("outdir", "o",  "outdir to store the results"));
		options.addOptionGroup(inputData);
	}
	
	public void run() {
		super.run();
	}
	
	protected void printUsage() {
		printUsage("./infrared.sh");
	}
	
}

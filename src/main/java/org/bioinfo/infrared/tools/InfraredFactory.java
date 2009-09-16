package org.bioinfo.infrared.tools;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.bioinfo.tool.OptionFactory;

public class InfraredFactory {

	private static Options options;
	
	public static InfraredTool createTool(String[] args) {
		InfraredTool infraredTool = null;
		
		CommandLineParser parser = new PosixParser();
		
		options = new Options();
		options.addOption(OptionFactory.createOption("tool", "to", "tool info"));
		
		String toolName;
		try {
			CommandLine cmd = parser.parse( options, args, true);
			if(cmd.hasOption("tool")) {
				toolName = cmd.getOptionValue("tool");

				if(toolName.equalsIgnoreCase("id-converter")) {
					return new IdConverter(args);
				}
				
				if(toolName.equalsIgnoreCase("clustering")) {
					return new IdConverter(args);
				}
				
				printUsage();
				return null;
			}else {
				printUsage();
			}
		} catch (ParseException e) {
			System.out.println(e.toString());
			printUsage();
		}
		return infraredTool;
	}
	
	
	private static void printUsage() {
		HelpFormatter h = new HelpFormatter();
		h.printHelp("./infrared.sh", options, true );
	}
}

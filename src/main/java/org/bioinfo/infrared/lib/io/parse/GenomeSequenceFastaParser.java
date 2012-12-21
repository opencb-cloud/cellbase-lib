package org.bioinfo.infrared.lib.io.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GenomeSequenceFastaParser {

	private int chunkSize = 2000;
	
	public GenomeSequenceFastaParser() {
		
	}
	
	public void parseToJson(File genomeReferenceFastaFile, File outJsonFile) {

		try {
			String line;
			StringBuilder sb = new StringBuilder();
			BufferedReader br = Files.newBufferedReader(Paths.get(genomeReferenceFastaFile.toURI()), Charset.defaultCharset());
			while((line = br.readLine()) != null) {
				if(line != null && !line.startsWith(">")) {
					sb.append(line);
				}else {
					// new chromosome
					// save data
					int start=0;
					while(start<sb.length()) {
						if(start == 0) {
							// first chunk contains chunkSize-1 nucleotides as index start at position 1
							System.out.println(sb.substring(0, chunkSize-1));
							start += chunkSize-1;
						}else {
							System.out.println(sb.substring(start, start+chunkSize)+"\n");
							start += chunkSize;
						}
//						if(start > 20000) return;
					}
					sb.delete(0, sb.length());
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

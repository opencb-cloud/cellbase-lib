package org.bioinfo.infrared.lib.io.output;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.Gene;

public class StringWriter {

	public static String serialize(Gene gene) {
		StringBuilder sb = new StringBuilder();
		sb.append(gene.getStableId()).append("\t").append(gene.getExternalName()).append("\t").append(gene.getExternalDb()).append("\t").append(gene.getBiotype()).append("\t");
		sb.append(gene.getChromosome()).append("\t").append(gene.getStart()).append("\t").append(gene.getEnd()).append("\t");
		sb.append(gene.getStrand()).append("\t").append(gene.getStatus()).append("\t").append(gene.getDescription());
		return sb.toString();
	}

	public static String serialize(List<Gene> genes) {
		StringBuilder sb = new StringBuilder();
		for(Gene gene: genes) {
			sb.append(gene.getStableId()).append("\t").append(gene.getExternalName()).append("\t").append(gene.getExternalDb()).append("\t").append(gene.getBiotype()).append("\t");
			sb.append(gene.getChromosome()).append("\t").append(gene.getStart()).append("\t").append(gene.getEnd()).append("\t");
			sb.append(gene.getStrand()).append("\t").append(gene.getStatus()).append("\t").append(gene.getDescription()).append("\n");
		}
		return sb.toString().trim();
	}

}

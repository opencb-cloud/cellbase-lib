package org.bioinfo.infrared.serialization;

import java.util.ArrayList;
import java.util.List;
/*
import org.bioinfo.infrared.core.Cytoband;
import org.bioinfo.infrared.core.Exon;
import org.bioinfo.infrared.core.Exon2transcript;
import org.bioinfo.infrared.core.Gene;
import org.bioinfo.infrared.core.GenomeSequence;
import org.bioinfo.infrared.core.Transcript;
*/

public class InfraredSerializer {
	/*
	
	public static String serialize(Gene gene){
		return new StringBuilder().append(gene.getStableId()).append("\t").append(gene.getExternalName()).append("\t").append(gene.getExternalDb()).append("\t").append(gene.getBiotype()).append("\t").append(gene.getStatus()).append("\t").append(gene.getChromosome()).append("\t").append(gene.getStart()).append("\t").append(gene.getEnd()).append("\t").append(gene.getStrand()).append("\t").append(gene.getSource()).append("\t").append(gene.getDescription()).toString(); 
	}
	
	public static String serialize(Exon exon){
		return new StringBuilder().append(exon.getStableId()).append("\t").append(exon.getChromosome()).append("\t").append(exon.getStart()).append("\t").append(exon.getEnd()).append("\t").append(exon.getStrand()).toString(); 
	}
	
	public static String serialize(Cytoband cytoband){
		return new StringBuilder().append(cytoband.getCytoband()).append("\t").append(cytoband.getChromosome()).append("\t").append(cytoband.getStart()).append("\t").append(cytoband.getEnd()).append("\t").append(cytoband.getStain()).toString();
	}
	
	public static String serialize(GenomeSequence genomeSequence){
		return new StringBuilder().append(genomeSequence.getId().getChromosome()).append("\t").append(genomeSequence.getId().getChunk()).append("\t").append(genomeSequence.getStart()).append("\t").append(genomeSequence.getEnd()).append(genomeSequence.getSequence()).toString();
	}
	
	public static String serialize(Transcript transcript){
		return new StringBuilder().append(transcript.getStableId()).append("\t")
								  .append(transcript.getExternalName()).append("\t")
								  .append(transcript.getExternalDb()).append("\t")
								  .append(transcript.getBiotype()).append("\t")
								  .append(transcript.getStatus()).append("\t")
								  .append(transcript.getChromosome()).append("\t")
								  .append(transcript.getStart()).append("\t")
								  .append(transcript.getEnd()).append("\t")
								  .append(transcript.getStrand()).append("\t")
								  .append(transcript.getCodingRegionStart()).append("\t")
								  .append(transcript.getCodingRegionEnd()).append("\t")
								  .append(transcript.getCdnaCodingStart()).append("\t")
								  .append(transcript.getCdnaCodingEnd()).append("\t")
								  .append(transcript.getDescription()).append("\t")
								  .toString();
	}
	
	public static String serialize(Exon2transcript exon2transcript){
		return new StringBuilder().append(exon2transcript.getGenomicCodingStart()).append("\t")
								  .append(exon2transcript.getGenomicCodingEnd()).append("\t")
								  .append(exon2transcript.getCdnaCodingStart()).append("\t")
								  .append(exon2transcript.getCdnaCodingEnd()).append("\t")
								  .append(exon2transcript.getCdsStart()).append("\t")
								  .append(exon2transcript.getCdsEnd()).append("\t")
								  .append(exon2transcript.getPhase()).append("\t")
								  .append(exon2transcript.getIsConstitutive()).append("\t")
								  .toString();
	}
	
	public static String serialize(List<?> list){
		StringBuilder sb = new StringBuilder();
		for (Object object : list) {
			if (object instanceof Gene){
				sb.append(InfraredSerializer.serialize((Gene) object)).append("\n");
				continue;
			}
			if (object instanceof Exon){
				sb.append(InfraredSerializer.serialize((Exon) object)).append("\n");
				continue;
			}
			if (object instanceof Cytoband){
				sb.append(InfraredSerializer.serialize((Cytoband) object)).append("\n");
				continue;
			}
			if (object instanceof GenomeSequence){
				sb.append(InfraredSerializer.serialize((GenomeSequence) object)).append("\n");
				continue;
			}
			if (object instanceof Transcript){
				sb.append(InfraredSerializer.serialize((Transcript) object)).append("\n");
				continue;
			}
			if (object instanceof Exon2transcript){
				sb.append(InfraredSerializer.serialize((Exon2transcript) object)).append("\n");
				continue;
			}
			if (object instanceof List){
				sb.append(InfraredSerializer.serialize((List)object));
				continue;
			}
		}
		return sb.toString();
	}*/
}

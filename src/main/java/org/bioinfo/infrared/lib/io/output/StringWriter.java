package org.bioinfo.infrared.lib.io.output;

import java.util.Iterator;
import java.util.List;

import org.bioinfo.infrared.core.biopax.v3.BioEntity;
import org.bioinfo.infrared.core.biopax.v3.NameEntity;
import org.bioinfo.infrared.core.biopax.v3.Pathway;
import org.bioinfo.infrared.core.cellbase.ConsequenceType;
import org.bioinfo.infrared.core.cellbase.Cytoband;
import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.FeatureMap;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.GenomeSequence;
import org.bioinfo.infrared.core.cellbase.MirnaDisease;
import org.bioinfo.infrared.core.cellbase.MirnaGene;
import org.bioinfo.infrared.core.cellbase.MirnaMature;
import org.bioinfo.infrared.core.cellbase.MirnaTarget;
import org.bioinfo.infrared.core.cellbase.Protein;
import org.bioinfo.infrared.core.cellbase.ProteinFeature;
import org.bioinfo.infrared.core.cellbase.ProteinXref;
import org.bioinfo.infrared.core.cellbase.Pwm;
import org.bioinfo.infrared.core.cellbase.RegulatoryRegion;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.Tfbs;
import org.bioinfo.infrared.core.cellbase.Transcript;
import org.bioinfo.infrared.core.cellbase.Xref;
import org.bioinfo.infrared.lib.common.GenomicVariantConsequenceType;
import org.bioinfo.infrared.lib.impl.hibernate.GenomicRegionFeatures;

public class StringWriter {

	public static String serialize(Gene gene){
		return new StringBuilder().append(gene.getStableId()).append("\t").append(gene.getExternalName()).append("\t").append(gene.getExternalDb()).append("\t").append(gene.getBiotype()).append("\t").append(gene.getStatus()).append("\t").append(gene.getChromosome()).append("\t").append(gene.getStart()).append("\t").append(gene.getEnd()).append("\t").append(gene.getStrand()).append("\t").append(gene.getSource()).append("\t").append(gene.getDescription()).toString(); 
	}

	public static String serialize(Exon exon){
		return new StringBuilder().append(exon.getStableId()).append("\t").append(exon.getChromosome()).append("\t").append(exon.getStart()).append("\t").append(exon.getEnd()).append("\t").append(exon.getStrand()).toString(); 
	}

	public static String serialize(Cytoband cytoband){
		return new StringBuilder().append(cytoband.getCytoband()).append("\t").append(cytoband.getChromosome()).append("\t").append(cytoband.getStart()).append("\t").append(cytoband.getEnd()).append("\t").append(cytoband.getStain()).toString();
	}

	public static String serialize(Xref xref){
		return new StringBuilder()
		.append(xref.getDisplayId()).append("\t")
		.append(xref.getDescription()).toString();
	}



	public static String serialize(GenomeSequence genomeSequence){
		return new StringBuilder().append(genomeSequence.getId().getChromosome()).append("\t").append(genomeSequence.getId().getChunk()).append("\t").append(genomeSequence.getStart()).append("\t").append(genomeSequence.getEnd()).append(genomeSequence.getSequence()).toString();
	}

	public static String serialize(Snp snp){
		return new StringBuilder().append(snp.getName()).append("\t")
				.append(snp.getChromosome()).append("\t")
				.append(snp.getStart()).append("\t")
				.append(snp.getDisplayConsequence()).append("\t")
				.append(snp.getDisplaySoConsequence()).append("\t")
				.append(snp.getSequence())
				.toString(); 
	}

	public static String serialize(ConsequenceType obj) {
		return join("\t", obj.getSoAccession(), obj.getSoTerm(), obj.getFeatureSoTerm(), obj.getDisplayTerm(), ""+obj.getRank(), obj.getNcbiTerm(), obj.getLabel(), obj.getDescription());
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
				.append(transcript.getDescription())
				.toString();


	}


	public static String serialize(MirnaGene obj) {
		return join("\t", obj.getMirbaseId(), obj.getMirbaseAcc(), obj.getStatus(), obj.getSequence(), obj.getSource());
	}

	public static String serialize(MirnaMature obj) {
		return join("\t", obj.getMirbaseId(), obj.getMirbaseAcc(), obj.getSequence());
	}


	public static String serialize(MirnaTarget object) {
		return new StringBuilder().append(object.getMirbaseId()).append("\t")
				.append(object.getGeneTargetName()).append("\t")
				.append(object.getChromosome()).append("\t")
				.append(object.getStart()).append("\t")
				.append(object.getEnd()).append("\t")
				.append(object.getStrand()).append("\t")
				.append(object.getPubmedId()).append("\t")
				.append(object.getSource())
				.toString(); 
	}

	private static Object serialize(MirnaDisease mirnaDisease) {
		return new StringBuilder().append(mirnaDisease.getMirbaseId()).append("\t")
				.append(mirnaDisease.getDiseaseName()).append("\t")
				.append(mirnaDisease.getDescription())
				.toString(); 
	}


	public static String serialize(Tfbs tfbs){
		return new StringBuilder().append(tfbs.getTfName()).append("\t")
				.append(tfbs.getTargetGeneName()).append("\t")
				.append(tfbs.getChromosome()).append("\t")
				.append(tfbs.getStart()).append("\t")
				.append(tfbs.getEnd()).append("\t")
				.append(tfbs.getCellType()).append("\t")
				.append(tfbs.getSequence()).append("\t")
				.append(tfbs.getScore())
				.toString(); 
	}


	public static String serialize(Pwm pwm){
		return new StringBuilder().append(pwm.getTfName()).append("\t")
				.append(pwm.getType()).append("\t")
				.append(pwm.getFrequencies()).append("\t")
				.append(pwm.getDescription()).append("\t")
				.append(pwm.getSource()).append("\t")
				.append(pwm.getLength()).append("\t")
				.append(pwm.getAccession()).append("\t")
				.toString();  
	}

	private static Object serialize(GenomicRegionFeatures object) {
		StringBuilder sb = new StringBuilder();
		for (FeatureMap featureMap : object.featuresMap) {
			sb.append(featureMap.getFeatureId()).append("\t")
			.append(featureMap.getChromosome()).append("\t")
			.append(featureMap.getStart()).append("\t")
			.append(featureMap.getEnd()).append("\t")
			.append(featureMap.getFeatureType()).append("\t").append("\n");

		}
		return sb.toString();
	}

	private static Object serialize(RegulatoryRegion obj) {
		return join("\t", obj.getName(), obj.getType(), obj.getChromosome(), String.valueOf(obj.getStart()), String.valueOf(obj.getEnd()), obj.getCellType(), obj.getSource());
	}

	public static String serialize(Protein obj) {
		return join("\t", obj.getPrimaryAccession(), obj.getName(), obj.getFullName(), obj.getGeneName(), obj.getOrganism());
	}

	public static String serialize(ProteinFeature obj) {
		return join("\t", obj.getType(), ""+obj.getStart(), ""+obj.getEnd(), obj.getOriginal(), obj.getVariation(), obj.getIdentifier(), obj.getDescription());
	}

	public static String serialize(ProteinXref obj) {
		return join("\t", obj.getName(), obj.getSource());
	}


	public static String serialize(GenomicVariantConsequenceType genomicVariantConsequenceType){
		return genomicVariantConsequenceType.toString();
	}

	public static String serialize(String string){
		return string;
	}

	private static Object serialize(Pathway object) {
		StringBuilder sb = new StringBuilder();
		if(object != null) {
//			sb.append("#id").append("\t").append("name").append("\t").append("description").append("\n");
			sb.append(object.getPkPathway()).append("\t").append(getFirstName(object.getBioEntity())).append("\t").append(object.getBioEntity().getComment());
		}
		return sb.toString();
	}


	public static String serialize(List<?> list){
		StringBuilder sb = new StringBuilder();
		for (Object object : list) {
			if (object instanceof Gene){
				sb.append(StringWriter.serialize((Gene) object)).append("\n");
				continue;
			}
			if (object instanceof Exon){
				sb.append(StringWriter.serialize((Exon) object)).append("\n");
				continue;
			}
			if (object instanceof Cytoband){
				sb.append(StringWriter.serialize((Cytoband) object)).append("\n");
				continue;
			}
			if (object instanceof GenomeSequence){
				sb.append(StringWriter.serialize((GenomeSequence) object)).append("\n");
				continue;
			}
			if (object instanceof Transcript){
				sb.append(StringWriter.serialize((Transcript) object)).append("\n");
				continue;
			}

			if (object instanceof Snp){
				sb.append(StringWriter.serialize((Snp) object)).append("\n");
				continue;
			}

			if (object instanceof ConsequenceType){
				sb.append(StringWriter.serialize((ConsequenceType) object)).append("\n");
				continue;
			}

			if (object instanceof String){
				sb.append(StringWriter.serialize((String) object)).append("\n");
				continue;
			}

			if (object instanceof Xref){
				sb.append(StringWriter.serialize((Xref)object)).append("\n");
				continue;
			}

			if (object instanceof Tfbs){
				sb.append(StringWriter.serialize((Tfbs)object)).append("\n");
				continue;
			}

			if (object instanceof RegulatoryRegion){
				sb.append(StringWriter.serialize((RegulatoryRegion)object)).append("\n");
				continue;
			}

			if (object instanceof MirnaGene){
				sb.append(StringWriter.serialize((MirnaGene)object)).append("\n");
				continue;
			}

			if (object instanceof MirnaMature){
				sb.append(StringWriter.serialize((MirnaMature)object)).append("\n");
				continue;
			}

			if (object instanceof MirnaTarget){
				sb.append(StringWriter.serialize((MirnaTarget)object)).append("\n");
				continue;
			}

			if (object instanceof MirnaDisease){
				sb.append(StringWriter.serialize((MirnaDisease)object)).append("\n");
				continue;
			}

			if (object instanceof Pwm){
				sb.append(StringWriter.serialize((Pwm)object)).append("\n");
				continue;
			}

			if (object instanceof Protein){
				sb.append(StringWriter.serialize((Protein)object)).append("\n");
				continue;
			}

			if (object instanceof ProteinFeature){
				sb.append(StringWriter.serialize((ProteinFeature)object)).append("\n");
				continue;
			}

			if (object instanceof ProteinXref){
				sb.append(StringWriter.serialize((ProteinXref)object)).append("\n");
				continue;
			}

			if (object instanceof GenomicVariantConsequenceType){
				sb.append(StringWriter.serialize((GenomicVariantConsequenceType)object)).append("\n");
				continue;
			}

			if (object instanceof GenomicRegionFeatures){
				sb.append(StringWriter.serialize((GenomicRegionFeatures)object)).append("\n");
				continue;
			}

			if (object instanceof List){
				sb.append(StringWriter.serialize((List)object));
				continue;
			}

			// BioPax objects
			if (object instanceof Pathway){
				sb.append(StringWriter.serialize((Pathway)object)).append("\n");
				continue;
			}

		}
		return sb.toString();
	}



	public static String getFirstName(BioEntity entity) {
		String name = "NO-NAME";
		try {
			String aux = "";
			Iterator it = entity.getNameEntities().iterator();
			NameEntity ne = null;
			while (it.hasNext()) {
				ne = (NameEntity) it.next();
				if (name.equalsIgnoreCase("NO-NAME") || ne.getNameEntity().length()<name.length()) {
					name = ne.getNameEntity();
				}
			}
			name = name.replace("\"", "'");
		} catch (Exception e) {
			name = "NO-NAME";
		}
		return name;
	}
	



	private static String join(String sep, String ... items) {
		StringBuilder stringBuilder = new StringBuilder();
		for(int i=0; i<items.length-1; i++) {
			stringBuilder.append(items[i]).append(sep);
		}
		stringBuilder.append(items[items.length-1]);
		return stringBuilder.toString();
	}

}

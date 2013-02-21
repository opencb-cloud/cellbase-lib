package org.bioinfo.cellbase.lib.io.output;

import java.util.Iterator;
import java.util.List;

import org.bioinfo.cellbase.lib.common.GenomeSequenceFeature;
import org.bioinfo.cellbase.lib.common.GenomicVariantConsequenceType;
import org.bioinfo.cellbase.lib.common.SnpRegulatoryConsequenceType;
import org.bioinfo.cellbase.lib.impl.hibernate.GenomicRegionFeatures;
import org.bioinfo.infrared.core.biopax.v3.BioEntity;
import org.bioinfo.infrared.core.biopax.v3.NameEntity;
import org.bioinfo.infrared.core.biopax.v3.Pathway;
import org.bioinfo.infrared.core.cellbase.ConsequenceType;
import org.bioinfo.infrared.core.cellbase.ConservedRegion;
import org.bioinfo.infrared.core.cellbase.CpGIsland;
import org.bioinfo.infrared.core.cellbase.Cytoband;
import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.FeatureMap;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.GenomeSequence;
import org.bioinfo.infrared.core.cellbase.MirnaDisease;
import org.bioinfo.infrared.core.cellbase.MirnaGene;
import org.bioinfo.infrared.core.cellbase.MirnaMature;
import org.bioinfo.infrared.core.cellbase.MirnaTarget;
import org.bioinfo.infrared.core.cellbase.MutationPhenotypeAnnotation;
import org.bioinfo.infrared.core.cellbase.Protein;
import org.bioinfo.infrared.core.cellbase.ProteinFeature;
import org.bioinfo.infrared.core.cellbase.ProteinInteraction;
import org.bioinfo.infrared.core.cellbase.ProteinXref;
import org.bioinfo.infrared.core.cellbase.Pwm;
import org.bioinfo.infrared.core.cellbase.RegulatoryRegion;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.SnpPhenotypeAnnotation;
import org.bioinfo.infrared.core.cellbase.SnpPopulationFrequency;
import org.bioinfo.infrared.core.cellbase.SnpToTranscriptConsequenceType;
import org.bioinfo.infrared.core.cellbase.StructuralVariation;
import org.bioinfo.infrared.core.cellbase.Tfbs;
import org.bioinfo.infrared.core.cellbase.Transcript;
import org.bioinfo.infrared.core.cellbase.Xref;

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
//		return new StringBuilder().append(genomeSequence.getId().getChromosome()).append("\t").append(genomeSequence.getStart()).append("\t").append(genomeSequence.getEnd()).append("\t").append(genomeSequence.getSequence()).toString();
		StringBuilder sb = new StringBuilder().append(">").append(genomeSequence.getId().getChromosome()).append("_").append(genomeSequence.getStart()).append("_").append(genomeSequence.getEnd()).append("\n");
		int length = genomeSequence.getSequence().length();
		int cont=0;
		while(cont <= length) {
			if(cont+60 > length) {
				sb.append(genomeSequence.getSequence().substring(cont, length));				
			}else {
				sb.append(genomeSequence.getSequence().substring(cont, cont+60)).append("\n");
			}
			cont += 60;
		}
		return sb.toString();
	}
	
	public static String serialize(GenomeSequenceFeature genomeSequence){
//		return new StringBuilder().append(genomeSequence.getId().getChromosome()).append("\t").append(genomeSequence.getStart()).append("\t").append(genomeSequence.getEnd()).append("\t").append(genomeSequence.getSequence()).toString();
		StringBuilder sb = new StringBuilder().append(">").append(genomeSequence.getChromosome()).append("_").append(genomeSequence.getStart()).append("_").append(genomeSequence.getEnd()).append("_").append(genomeSequence.getStrand()).append("\n");
		int length = genomeSequence.getSequence().length();
		int cont=0;
		while(cont <= length) {
			if(cont+60 > length) {
				sb.append(genomeSequence.getSequence().substring(cont, length));				
			}else {
				sb.append(genomeSequence.getSequence().substring(cont, cont+60)).append("\n");
			}
			cont += 60;
		}
		return sb.toString();
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
	
	public static String serialize(SnpPhenotypeAnnotation snpPhenotypeAnnotation){
		return new StringBuilder().append(snpPhenotypeAnnotation.getSnp() != null ? snpPhenotypeAnnotation.getSnp().getName() : "").append("\t")
			.append(snpPhenotypeAnnotation.getSource()).append("\t")
			.append(snpPhenotypeAnnotation.getAssociatedGeneName()).append("\t")
			.append(snpPhenotypeAnnotation.getAssociatedVariantRiskAllele()).append("\t")
			.append(snpPhenotypeAnnotation.getRiskAlleleFrequencyInControls()).append("\t")
			.append(snpPhenotypeAnnotation.getPValue()).append("\t")
			.append(snpPhenotypeAnnotation.getPhenotypeName()).append("\t")
			.append(snpPhenotypeAnnotation.getPhenotypeDescription()).append("\t")
			.append(snpPhenotypeAnnotation.getStudyName()).append("\t")
			.append(snpPhenotypeAnnotation.getStudyType()).append("\t")
			.append(snpPhenotypeAnnotation.getStudyUrl()).append("\t")
			.append(snpPhenotypeAnnotation.getStudyDescription())
			.toString(); 
	}
	
	public static String serialize(MutationPhenotypeAnnotation mutationPhenotypeAnnotation){
		return new StringBuilder().append(mutationPhenotypeAnnotation.getChromosome()).append("\t")
			.append(mutationPhenotypeAnnotation.getStart()).append("\t")
			.append(mutationPhenotypeAnnotation.getEnd()).append("\t")
			.append(mutationPhenotypeAnnotation.getGeneName()).append("\t")
			.append(mutationPhenotypeAnnotation.getUniprotName()).append("\t")
			.append(mutationPhenotypeAnnotation.getEnsemblTranscript()).append("\t")
			.append(mutationPhenotypeAnnotation.getPrimarySite()).append("\t")
			.append(mutationPhenotypeAnnotation.getSiteSubtype()).append("\t")
			.append(mutationPhenotypeAnnotation.getPrimaryHistology()).append("\t")
			.append(mutationPhenotypeAnnotation.getMutationCds()).append("\t")
			.append(mutationPhenotypeAnnotation.getMutationAa()).append("\t")
			.append(mutationPhenotypeAnnotation.getMutationDescription()).append("\t")
			.append(mutationPhenotypeAnnotation.getMutationZigosity()).append("\t")
			.append(mutationPhenotypeAnnotation.getPubmedId()).append("\t")
			.append(mutationPhenotypeAnnotation.getDescription()).append("\t")
			.append(mutationPhenotypeAnnotation.getSource())
			.toString(); 
	}

	public static String serialize(ConsequenceType obj) {
		StringBuilder sb = new StringBuilder();
		if(obj.getSnpToTranscripts() != null) {
			sb.append(obj.getSnpToTranscriptConsequenceTypes().iterator().next().getSnpToTranscript().getSnp().getName()).append("\n");
			sb.append(obj.getSnpToTranscriptConsequenceTypes().iterator().next().getConsequenceType().toString());
		}
		return join("\t", obj.getSoAccession(), obj.getSoTerm(), obj.getFeatureSoTerm(), obj.getDisplayTerm(), ""+obj.getRank(), obj.getNcbiTerm(), obj.getLabel(), obj.getDescription());
	}
	
	public static String serialize(SnpToTranscriptConsequenceType obj) {
		StringBuilder sb = new StringBuilder();
		if(obj.getSnpToTranscript() != null) {
			if(obj.getSnpToTranscript().getSnp() != null) {			
				sb.append(obj.getSnpToTranscript().getSnp().getName()).append("\t");
				sb.append(obj.getSnpToTranscript().getSnp().getChromosome()).append("\t");
				sb.append(obj.getSnpToTranscript().getSnp().getStart()).append("\t");
				sb.append(obj.getSnpToTranscript().getSnp().getEnd()).append("\t");
				sb.append(obj.getSnpToTranscript().getSnp().getStrand()).append("\t");
				sb.append(obj.getSnpToTranscript().getSnp().getAlleleString()).append("\t");
			
				
				if(obj.getSnpToTranscript().getTranscript() != null) {
					sb.append(obj.getSnpToTranscript().getTranscript().getStableId()).append("\t");					
					sb.append(obj.getSnpToTranscript().getTranscript().getExternalName()).append("\t");
				}else {
					sb.append("").append("\t");
					sb.append("").append("\t");
				}
				if(obj.getConsequenceType() != null) {
					sb.append(obj.getConsequenceType().getSoAccession()).append("\t");
					sb.append(obj.getConsequenceType().getSoTerm()).append("\t");
					sb.append(obj.getConsequenceType().getLabel()).append("\t");
					sb.append(obj.getConsequenceType().getDescription());
				}else {
					sb.append("").append("\t");
					sb.append("").append("\t");
					sb.append("").append("\t");
					sb.append("");
				}
			}
			
		}
		return sb.toString();
	}

	public static String serialize(SnpRegulatoryConsequenceType snpRegulatoryConsequenceType){
		return new StringBuilder().append(snpRegulatoryConsequenceType.getSnpName()).append("\t")
			.append(snpRegulatoryConsequenceType.getFeatureName()).append("\t")
			.append(snpRegulatoryConsequenceType.getFeatureType()).append("\t")
			.append(snpRegulatoryConsequenceType.getChromosome()).append("\t")
			.append(snpRegulatoryConsequenceType.getStart()).append("\t")
			.append(snpRegulatoryConsequenceType.getEnd()).append("\t")
			.append(snpRegulatoryConsequenceType.getStrand()).append("\t")
			.append(snpRegulatoryConsequenceType.getTranscriptStableId()).append("\t")
			.append(snpRegulatoryConsequenceType.getGeneStableId()).append("\t")
			.append(snpRegulatoryConsequenceType.getGeneName()).append("\t")
			.append(snpRegulatoryConsequenceType.getBiotype()).toString(); 
	}
	
	public static String serialize(SnpPopulationFrequency snpPopulationFrequency){
		StringBuilder sb = new StringBuilder();
		sb.append(snpPopulationFrequency.getSnp().getName()).append("\t");
		sb.append(snpPopulationFrequency.getPopulation()).append("\t");
		sb.append(snpPopulationFrequency.getSource()).append("\t");
		sb.append(snpPopulationFrequency.getRefAllele()).append("\t");
		sb.append(snpPopulationFrequency.getRefAlleleFrequency()).append("\t");
		sb.append(snpPopulationFrequency.getOtherAllele()).append("\t");
		sb.append(snpPopulationFrequency.getOtherAlleleFrequency()).append("\t");
		sb.append(snpPopulationFrequency.getRefAlleleHomozygote()).append("\t");
		sb.append(snpPopulationFrequency.getRefAlleleHomozygoteFrequency()).append("\t");
		sb.append(snpPopulationFrequency.getAlleleHeterozygote()).append("\t");
		sb.append(snpPopulationFrequency.getAlleleHeterozygoteFrequency()).append("\t");
		sb.append(snpPopulationFrequency.getOtherAlleleHomozygote()).append("\t");
		sb.append(snpPopulationFrequency.getOtherAlleleHeterozygoteFrequency());
		return sb.toString();
	}
	
	public static String serialize(StructuralVariation obj){
		return join("\t", obj.getDisplayId(), obj.getChromosome(), ""+obj.getStart(), ""+obj.getEnd(), obj.getStrand(), obj.getSoTerm(), obj.getStudyName(), obj.getStudyUrl(), obj.getStudyDescription(), obj.getSource(), obj.getSourceDescription());
	}
	
	
	
	public static String serialize(Transcript transcript){
		if(transcript.getGene() != null) {
			System.out.println("serialize transcript");
		}
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

	public static String serialize(CpGIsland obj){
		return join("\t", ""+obj.getCpgId(), obj.getChromosome(), ""+obj.getStart(), ""+obj.getEnd(), obj.getName(), ""+obj.getLength(), ""+obj.getCpgNum(), ""+obj.getGcNum(), ""+obj.getPerCpG(), ""+obj.getPerGc(), ""+obj.getObservedExpectedRatio());
	}
	
	public static String serialize(ConservedRegion obj){
		return join("\t", ""+obj.getConservedRegionId(), obj.getChromosome(), ""+obj.getStart(), ""+obj.getEnd(), obj.getStrand(), ""+obj.getLength(),
				""+obj.getLowerLimitVertebrate(), ""+obj.getDataRangeVertebrate(), ""+obj.getSumDataVertebrate(), ""+obj.getSumSquareVertebrate(),
				""+obj.getLowerLimitMammal(), ""+obj.getDataRangeMammal(), ""+obj.getSumDataMammal(), ""+obj.getSumSquareMammal(),
				""+obj.getLowerLimitPrimate(), ""+obj.getDataRangePrimate(), ""+obj.getSumDataPrimate(), ""+obj.getSumSquarePrimate(), obj.getMethod());
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

	public static String serialize(ProteinInteraction obj) {
		return join("\t", ""+obj.getProteinByProteinId1().getPrimaryAccession(), ""+obj.getProteinByProteinId2().getPrimaryAccession(), ""+obj.getIntactId(), obj.getMultiplicity(), obj. getInteractionType(), 
				obj.getDetectionMethod(), obj.getNegative(), obj.getCellTypeShort(), obj.getCellTypeLong(), obj.getTissueShort(), obj.getTissueLong(), obj.getHostOrganismShort(), obj.getHostOrganismLong(),
				obj.getPubmed(), obj.getSource());
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
			if (object instanceof GenomeSequenceFeature){
				sb.append(StringWriter.serialize((GenomeSequenceFeature) object)).append("\n");
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
			if (object instanceof SnpToTranscriptConsequenceType){
				sb.append(StringWriter.serialize((SnpToTranscriptConsequenceType) object)).append("\n");
				continue;
			}			
			if (object instanceof SnpRegulatoryConsequenceType){
				sb.append(StringWriter.serialize((SnpRegulatoryConsequenceType) object)).append("\n");
				continue;
			}			
			if (object instanceof SnpPopulationFrequency){
				sb.append(StringWriter.serialize((SnpPopulationFrequency) object)).append("\n");
				continue;
			}			
			if (object instanceof SnpPhenotypeAnnotation){
				sb.append(StringWriter.serialize((SnpPhenotypeAnnotation) object)).append("\n");
				continue;
			}			
			if (object instanceof MutationPhenotypeAnnotation){
				sb.append(StringWriter.serialize((MutationPhenotypeAnnotation) object)).append("\n");
				continue;
			}
			if (object instanceof StructuralVariation){
				sb.append(StringWriter.serialize((StructuralVariation) object)).append("\n");
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
			
			if (object instanceof CpGIsland){
				sb.append(StringWriter.serialize((CpGIsland)object)).append("\n");
				continue;
			}
			
			if (object instanceof ConservedRegion){
				sb.append(StringWriter.serialize((ConservedRegion)object)).append("\n");
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
			
			if (object instanceof ProteinInteraction){
				sb.append(StringWriter.serialize((ProteinInteraction)object)).append("\n");
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
//				System.out.println("en StringWriter: List");
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

package org.bioinfo.infrared.lib.impl.mongo;

import java.util.List;

import org.bioinfo.infrared.core.biopax.v3.BioEntity;
import org.bioinfo.infrared.core.biopax.v3.Catalysis;
import org.bioinfo.infrared.core.biopax.v3.Complex;
import org.bioinfo.infrared.core.biopax.v3.Control;
import org.bioinfo.infrared.core.biopax.v3.Conversion;
import org.bioinfo.infrared.core.biopax.v3.DataSource;
import org.bioinfo.infrared.core.biopax.v3.Dna;
import org.bioinfo.infrared.core.biopax.v3.Dnaregion;
import org.bioinfo.infrared.core.biopax.v3.GeneticInteraction;
import org.bioinfo.infrared.core.biopax.v3.Interaction;
import org.bioinfo.infrared.core.biopax.v3.MolecularInteraction;
import org.bioinfo.infrared.core.biopax.v3.Pathway;
import org.bioinfo.infrared.core.biopax.v3.PhysicalEntity;
import org.bioinfo.infrared.core.biopax.v3.Protein;
import org.bioinfo.infrared.core.biopax.v3.Rna;
import org.bioinfo.infrared.core.biopax.v3.Rnaregion;
import org.bioinfo.infrared.core.biopax.v3.SmallMolecule;
import org.bioinfo.infrared.core.biopax.v3.TemplateReaction;
import org.bioinfo.infrared.lib.api.BioPaxDBAdaptor;
import org.bioinfo.infrared.lib.common.ComplexComponent;
import org.bioinfo.infrared.lib.common.DataSourceStats;
import org.hibernate.SessionFactory;

class PathwayMongoDBAdaptor extends MongoDBAdaptor implements BioPaxDBAdaptor {

//	private final static int CHUNK_SIZE = 2000;

	
	public PathwayMongoDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public PathwayMongoDBAdaptor(SessionFactory sessionFactory, String species, String version) {
		super(sessionFactory, species, version);
	}
	
	
	private int getChunk(int position){
		return (position / applicationProperties.getIntProperty("CELLBASE."+version.toUpperCase()+".GENOME_SEQUENCE.CHUNK_SIZE", 2000));
	}

	private int getOffset(int position){
		return ((position) % applicationProperties.getIntProperty("CELLBASE."+version.toUpperCase()+".GENOME_SEQUENCE.CHUNK_SIZE", 2000));
	}
	

	@Override
	public boolean isDna(PhysicalEntity physicalEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDna(int physicalEntityId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Dna getDna(PhysicalEntity ph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDnaregion(PhysicalEntity physicalEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDnaregion(int physicalEntityId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Dnaregion getDnaregion(PhysicalEntity ph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRna(PhysicalEntity physicalEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRna(int physicalEntityId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Rna getRna(PhysicalEntity ph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRnaregion(PhysicalEntity physicalEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRnaregion(int physicalEntityId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Rnaregion getRnaregion(PhysicalEntity ph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSmallMolecule(PhysicalEntity physicalEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSmallMolecule(int physicalEntityId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SmallMolecule getSmallMolecule(PhysicalEntity ph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfComplexes(DataSource ds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isComplex(PhysicalEntity physicalEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isComplex(int physicalEntityId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Complex getComplex(PhysicalEntity ph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Complex getComplex(int complexId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Complex getComplex(String complexName, String dataSourceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ComplexComponent> getComplexComponents(String complexName,
			String dataSourceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ComplexComponent> getComplexComponents(Complex input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pathway> getPathways(String complexName, String dataSourceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Interaction> getInteractions(Complex input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pathway> getPathways(Complex input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Protein> getProteins(Complex input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(Complex input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toStringComplexes(List<Complex> input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toJson(Complex input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toJsonComplexes(List<Complex> input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfProteins(DataSource ds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isProtein(PhysicalEntity physicalEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isProtein(int physicalEntityId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Protein getProtein(PhysicalEntity ph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Protein getProtein(int proteinId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Protein getProteinByXrefId(String proteinId, DataSource ds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getProteinReferenceNames(Protein protein) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Complex> getComplexes(Protein input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Interaction> getInteractions(Protein input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pathway> getPathways(Protein input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCatalysis(Control control) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCatalysis(int controlId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Catalysis getCatalysis(Control c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isControl(Interaction interaction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isControl(int interactionId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Control getControl(Interaction ph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Protein> getProteins(Control input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isConversion(Interaction interaction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConversion(int interactionId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Conversion getConversion(Interaction ph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Protein> getProteins(Conversion input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGeneticInteraction(Interaction interaction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGeneticInteraction(int interactionId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GeneticInteraction getGeneticInteraction(Interaction ph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMolecularInteraction(Interaction interaction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMolecularInteraction(int interactionId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MolecularInteraction getMolecularInteraction(Interaction ph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTemplateReaction(Interaction interaction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTemplateReaction(int interactionId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TemplateReaction getTemplateReaction(Interaction ph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfPhysicalEntities(DataSource ds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Interaction> getInteractions(PhysicalEntity input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pathway> getPathways(PhysicalEntity input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfGenes(DataSource ds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfInteractions(DataSource ds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Protein> getProteins(Interaction input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(Interaction input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toStringInteractions(List<Interaction> input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toJson(Interaction input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toJsonInteractions(List<Interaction> input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfPathways(DataSource ds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Pathway getPathway(String pathwayName, String dataSourceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pathway getPathway(int pathwayId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pathway> getPathways(String dataSourceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pathway> getPathways(String dataSourceName, String search,
			boolean onlyTopLevel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Interaction> getInteractions(Pathway input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Protein> getProteins(Pathway input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(Pathway input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toStringPathways(List<Pathway> input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toJson(Pathway input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toJsonPathways(List<Pathway> input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfCellularLocations(DataSource ds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfPublicationXrefs(DataSource ds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DataSource> getDataSources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataSource getDataSource(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataSourceStats getDataSourceStats(String dataSourceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataSourceStats getDataSourceStats(DataSource ds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirstName(BioEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	


}

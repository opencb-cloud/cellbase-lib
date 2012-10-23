package org.bioinfo.infrared.lib.io.parse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.lib.common.BiopaxPathway;
import org.bioinfo.infrared.lib.common.Interaction;
import org.bioinfo.infrared.lib.common.SubPathway;


public class BiopaxPathwayParser {
	
	BioPax bioPax = null;
	
	public BiopaxPathwayParser() {
	}

	public String parseToJson(File file) throws IOException, SecurityException, NoSuchMethodException {

		String filename = "/mnt/commons/formats/reactome/Homo sapiens.owl";

		try {
			BioPaxParser parser = new BioPaxParser(filename);
			bioPax = parser.parse();
			
			List<BiopaxPathway> pathwayList = new ArrayList<BiopaxPathway>();
			List<DBObject> dbObjList = new ArrayList<DBObject>();
			Gson g = new Gson();
			
			System.out.println("Pathway list: ");
			int cont = 0;
//			String pathway = "Initiation_of_checkpoint_signal_from_defective_kinetochores";
			for(String pathway: bioPax.getPathwayList() ) {
				System.out.println("Pathway: "+pathway);
				if(cont++ == 1) break;
				
				String name = bioPax.getElementMap().get(pathway).getId();
				Map<String, List<String>> params = bioPax.getElementMap().get(pathway).getParams();
				List<String> species = params.get("organism-id");
				List<String> displayName = params.get("displayName");
				List<String> xref = params.get("xref-id");
				BiopaxPathway p = new BiopaxPathway(name, "Reactome", "Reactome", "39", species, displayName, xref);
				List<String> pathwayComponents = params.get("pathwayComponent-id");
				
				// loop pathway components
				if(pathwayComponents != null) {
					for(String component: pathwayComponents) {
						String type = bioPax.getElementMap().get(component).getBioPaxClassName();
						if(type.equalsIgnoreCase("Pathway")) {
							SubPathway sp = searchSubPathways(component, p);
							if(sp != null) p.subPathways.add(sp);
						}
						else if(type.equalsIgnoreCase("GeneticInteraction") || type.equalsIgnoreCase("MolecularInteraction") || type.equalsIgnoreCase("TemplateReaction") || type.equalsIgnoreCase("Catalysis") || type.equalsIgnoreCase("Modulation") || type.equalsIgnoreCase("TemplateReactionRegulation") || type.equalsIgnoreCase("BiochemicalReaction") || type.equalsIgnoreCase("ComplexAssembly") || type.equalsIgnoreCase("Degradation") || type.equalsIgnoreCase("Transport") || type.equalsIgnoreCase("TransportWithBiochemicalReaction")) {
							Map<String, List<String>> interactionParams = bioPax.getElementMap().get(component).getParams();
							p.interactions.add(new Interaction(bioPax.getElementMap().get(component).getId(), type, interactionParams));
//							System.out.println("Found interaction: "+type);
							p.allInteractionsIDs.add(bioPax.getElementMap().get(component).getId());
							
							addPhysicalEntities(component, p, false);
						}
//						else if(type.equalsIgnoreCase("PhysicalEntity") || type.equalsIgnoreCase("Complex") || type.equalsIgnoreCase("DNA") || type.equalsIgnoreCase("DNARegion") || type.equalsIgnoreCase("Protein") || type.equalsIgnoreCase("RNA") || type.equalsIgnoreCase("RNARegion") || type.equalsIgnoreCase("SmallMolecule")) {
//							System.out.println("Found physical entity: "+type);
//							p.physicalEntities.add(new PhysicalEntity(bioPax.getElementMap().get(component).getId()));
//						}
					}
				}
				pathwayList.add(p);
				dbObjList.add((DBObject)JSON.parse(g.toJson(p)));
				
				System.out.println("JSON: ");
				System.out.println(g.toJson(p));
				
				BSONObject b = (BSONObject)JSON.parse(g.toJson(p));
				System.out.println("BSON: ");
				System.out.println(BSON.encode(b));
				
			}
//			String json = g.toJson(pathwayList);
//			System.out.println(json);

//			coll.insert(dbObjList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


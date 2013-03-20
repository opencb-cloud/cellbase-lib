package org.bioinfo.cellbase.lib.api;

import java.util.List;

import org.bioinfo.cellbase.lib.common.core.Chromosome;
import org.bioinfo.cellbase.lib.common.core.Cytoband;

public interface ChromosomeDBAdaptor {

	Chromosome getChromosomeByName(String name);

	List<Chromosome> getChromosomeByNameList(List<String> nameList);

	List<Cytoband> getCytobandByName(String name);

	List<List<Cytoband>> getCytobandByNameList(List<String> nameList);

	List<Chromosome> getChromosomes();

	List<String> getChromosomeNames();
}

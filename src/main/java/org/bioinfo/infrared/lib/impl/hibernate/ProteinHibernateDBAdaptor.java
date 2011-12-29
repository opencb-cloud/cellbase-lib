package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.Protein;
import org.bioinfo.infrared.lib.api.ProteinDBAdaptor;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.SessionFactory;

public class ProteinHibernateDBAdaptor extends HibernateDBAdaptor implements ProteinDBAdaptor {

	public ProteinHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public List<String> getAllIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getInfoByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getFullInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getFullInfoByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Region getRegionById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Region> getAllRegionsByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSequenceById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllSequencesByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Protein> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllUniprotIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Protein getAllByUniprotId(String uniprotId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Protein> getAllByUniprotIdList(List<String> uniprotIdList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Protein> getAllByProteinName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Protein>> getAllByProteinNameList(List<String> nameList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllEnsemblIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Protein getAllByEnsemblGene(String ensemblId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Protein> getAllByEnsemblGeneList(List<String> ensemblIdList) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public Protein getAllByEnsemblTranscriptId(String transcriptId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Protein> getAllByEnsemblTranscriptIdList(List<String> transcriptIdList) {
		// TODO Auto-generated method stub
		return null;
	}

}

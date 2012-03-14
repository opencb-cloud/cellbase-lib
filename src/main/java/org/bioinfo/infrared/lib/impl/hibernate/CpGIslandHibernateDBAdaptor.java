package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.CpGIsland;
import org.bioinfo.infrared.lib.api.CpGIslandDBAdaptor;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class CpGIslandHibernateDBAdaptor extends HibernateDBAdaptor implements CpGIslandDBAdaptor{

	public CpGIslandHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public CpGIslandHibernateDBAdaptor(SessionFactory sessionFactory, String species, String version) {
		super(sessionFactory, species, version);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CpGIsland> getAllByRegion(Region region) {
		Query query = this.openSession().createQuery("select cpgi from CpGIsland cpgi where cpgi.chromosome= :CHROMOSOME and cpgi.start < :END and cpgi.end > :START")
				.setParameter("CHROMOSOME", region.getChromosome())
				.setParameter("START", region.getStart())
				.setParameter("END", region.getEnd());
		return (List<CpGIsland>) executeAndClose(query);
	}

	@Override
	public List<List<CpGIsland>> getAllByRegionList(List<Region> regionList) {
		List<List<CpGIsland>> result = new ArrayList<List<CpGIsland>>(regionList.size());
		for (Region region : regionList) {
			result.add(this.getAllByRegion(region));
		}
		return result;
	}

}

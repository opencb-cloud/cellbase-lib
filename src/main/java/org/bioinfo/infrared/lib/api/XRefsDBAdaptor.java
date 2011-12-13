package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.Dbname;
import org.bioinfo.infrared.core.cellbase.Xref;
import org.bioinfo.infrared.lib.common.XRefs;

public interface XRefsDBAdaptor {


	public List<Dbname> getAllDBNames();

	public List<Dbname> getAllDBNamesById(String id);

	public List<String> getAllTypes();

	public List<Dbname> getAllDBNamesByType(String type);

	public List<String> getAllIdsByDBName(String dbname);
	

	public List<Xref> getById(String id);
	
	public List<List<Xref>> getAllByIdList(List<String> ids);
	
	public XRefs getById(String id, String type);
	
	public List<XRefs> getAllByIdList(List<String> ids, String type);

	
	public List<Xref> getByDBName(String id, String dbname);

	public List<List<Xref>> getAllByDBName(List<String> ids, String dbname);

	public List<Xref> getByDBNameList(String id, List<String> dbnames);

	public List<List<Xref>> getAllByDBNameList(List<String> ids, List<String> dbnames);


	public XRefs getByDBName(String id, String dbname, String type);

	public List<XRefs> getAllByDBName(List<String> ids, String dbname, String type);

	public XRefs getByDBNameList(String id, List<String> dbnames, String type);

	public List<XRefs> getAllByDBNameList(List<String> ids, List<String> dbnames, String type);

	
}

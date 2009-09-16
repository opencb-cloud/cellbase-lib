package org.bioinfo.infrared.common.dbsql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.bioinfo.commons.utils.StringUtils;
import org.bioinfo.db.DBConnection;


public class DBConnector {

	private DBConnection dbConnection;
	private Properties props = new Properties();
	
	private String host;
	private String port;
	private String database;
	private String user;
	private String password;
	private String specie;
	
	public DBConnector() {
		// get parameters from the property file
		loadConfig(new File(System.getenv("ROSETTA_HOME")+"/conf/db.conf"));
		createDBConnection();
	}
	public DBConnector(String specie) {
		this.setSpecie(specie);
		// get parameters from the property file
		loadConfig(new File(System.getenv("ROSETTA_HOME")+"/conf/db.conf"));
		createDBConnection();
	}
	
	public DBConnector(File propertyFile) {
		// get parameters from the property file
		loadConfig(propertyFile);
		createDBConnection();
	}
	
	public DBConnector(String specie, File propertyFile) {
		this.setSpecie(specie);
		// get parameters from the property file
		loadConfig(propertyFile);
		createDBConnection();
	}
	
	public DBConnector(String specie, String host, String port, String user, String passwd) {
		// get parameters from the property file
		loadConfig(new File(System.getenv("ROSETTA_HOME")+"/conf/db.conf"));
		// sobreescribo algunos parametros
		this.host = host;
		this.port = port;
		this.specie = specie;
		this.user = user;
		this.password = passwd;
		createDBConnection();
	}
	
	public DBConnector(String specie, String host, String port, String user, String passwd, File propertyFile) {
		// get parameters from the property file
		loadConfig(propertyFile);
		// sobreescribo algunos parametros
		this.host = host;
		this.port = port;
		this.specie = specie;
		this.user = user;
		this.password = passwd;
		createDBConnection();
	}
	
	private void loadConfig(File propertyFile) {
		try {
			props.load(new FileInputStream(propertyFile));
			if(specie == null) {
				this.specie = props.getProperty("DEFAULT.SPECIES");
			}
			if(isValidSpecie(specie)) {
				this.host = props.getProperty("ROSETTA.HOSTNAME");
				this.port = props.getProperty("ROSETTA.PORT");
				this.database = props.getProperty("ROSETTA."+specie.toUpperCase()+".DATABASE");
				this.user = props.getProperty("ROSETTA.USER");
				this.password = props.getProperty("ROSETTA.PASSWORD");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void createDBConnection() {
		if(getDbConnection() == null) {
			setDbConnection(new DBConnection("mysql",host, port, database, user, password));
		}
	}
	
	public void setAutoConnectAndDisconnect(boolean auto) {
		getDbConnection().setAutoConnectAndDisconnect(auto);
	}
	
	public void disconnect() throws SQLException {
		getDbConnection().disconnect();
	}
	public List<String> getAllChromosomes() {
		return null;
	}
	
	public List<String> getAvailableDBs() {
		return StringUtils.toList((String) props.get("ROSETTA."+specie.toUpperCase()+".AVAILABLE.DBS"));
	}
	
	public boolean isValidSpecie(String specie) {
		return StringUtils.toList((String) props.get("ROSETTA.SPECIES")).contains(specie);
	}
	
	/**
	 * @param dbConnection the dbConnection to set
	 */
	public void setDbConnection(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
	/**
	 * @return the dbConnection
	 */
	public DBConnection getDbConnection() {
		return dbConnection;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(host).append("\t").append(port).append("\t").append(database).append("\t").append(user).append("\t").append(password).append("\n");
		return sb.toString();
	}
	/**
	 * @param specie the specie to set
	 */
	public void setSpecie(String specie) {
		this.specie = specie;
	}
	/**
	 * @return the specie
	 */
	public String getSpecie() {
		return specie;
	}
}

package com.dao.dbutil;

import java.sql.Connection;



import org.apache.log4j.Logger;

import com.servlet.Controller;

public class DBUtil {
	private static final Logger logger = Logger.getLogger(DBUtil.class);

	
	public DBUtil() {
	}
	public static Connection getConnection() {
		try {
			Connection con = Controller.dataSource.getConnection();
			logger.debug("Connection retrieved");
			return con;
		}
		catch (Exception e){
			logger.error("cannot obtain connection",e);
			return null;
		}
	}
}

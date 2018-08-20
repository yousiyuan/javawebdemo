package org.lnson.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

public final class C3P0Manager {

	private static Log4jUtils log4j = Log4jUtils.getLog4jUtil();
	private static Map<String, ComboPooledDataSource> dataSourceMaps = new HashMap<String, ComboPooledDataSource>();

	/**
	 * 创建连接池的新实例
	 */
	public static void newInstance(String namedConfig) throws SQLException {
		if (dataSourceMaps.containsKey(namedConfig)) {
			String message = "DataSource " + namedConfig + " already exist, please close first.";
			log4j.error(message);
			throw new SQLException(message);
		}

		// 创建连接池对象
		ComboPooledDataSource dataSource = new ComboPooledDataSource(namedConfig);

		// 尝试从连接池中获取一个连接数据库的对象
		try {
			Connection connection = dataSource.getConnection();
			connection.close();
		} catch (SQLException ex) {
			DataSources.destroy(dataSource);

			String message = "DataSource " + namedConfig + " open error :" + ex.toString();
			log4j.error(message);
			throw new SQLException(message);
		}

		// 将连接池放入缓存中
		dataSourceMaps.put(namedConfig, dataSource);
	}

	/**
	 * 释放连接池对象
	 */
	public static void releaseInstance(String namedConfig) throws SQLException {
		ComboPooledDataSource dataSource = dataSourceMaps.remove(namedConfig);
		if (dataSource != null) {
			dataSource.close();
			DataSources.destroy(dataSource);
		}
	}

	/**
	 * 从指定的连接池对象中获得一个连接数据库的实例
	 */
	public static Connection getConnection(String namedConfig) throws SQLException {
		ComboPooledDataSource dataSource = dataSourceMaps.get(namedConfig);
		if (dataSource == null)
			return null;
		return dataSource.getConnection();
	}

	/**
	 * 应用程序停止时释放一切与连接池相关的资源
	 * 
	 * @throws SQLException
	 */
	public static void destroy() throws SQLException {
		Iterator<Entry<String, ComboPooledDataSource>> iter = dataSourceMaps.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, ComboPooledDataSource> entry = iter.next();
			String namedConfig = entry.getKey();
			releaseInstance(namedConfig);
		}
		dataSourceMaps.clear();
		
		// 注销数据库驱动
		Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			DriverManager.deregisterDriver(driver);
		}
	}
}

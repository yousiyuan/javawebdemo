package org.lnson.util;

import java.sql.Connection;
import java.sql.SQLException;

public final class C3p0OracleUtils {

	private final static String ORACLEPOOLCONFIG = "oracle_dbpool_config";
	
	static {
		try {
			C3P0Manager.newInstance(ORACLEPOOLCONFIG);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从连接池中取用一个连接
	 */
	public static Connection getConnection() throws SQLException {
		return C3P0Manager.getConnection(ORACLEPOOLCONFIG);
	}

	/**
	 * 释放连接回连接池
	 * <p>
	 * 此处有必要说明，这里调用close方法释放连接回连接池，而并非是将Connection对象在物理内存中销毁，
	 * 因为C3P0早就在内部重写了Connection对象的close()方法
	 * </p>
	 */
	public static void close(Connection conn) throws SQLException {
		if (conn != null)
			conn.close();
	}

}

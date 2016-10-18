package com.liudecai.utils.jdbc;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.mysql.jdbc.Connection;

/**
 * 重写QueryRunner
 * 
 * bath:批处理
 * update:增、删、改 操作
 * query:查询操作
 * @author liudecai  
 * @email 1911939348@qq.com
 */
public class TxQueryRunner extends QueryRunner {

	/* 批处理
	 * (non-Javadoc)
	 * @see org.apache.commons.dbutils.QueryRunner#batch(java.lang.String, java.lang.Object[][])
	 */
	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		Connection con = (Connection) JdbcUtils.getConnection();
		int[] result = super.batch(con, sql, params);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		Connection con = (Connection) JdbcUtils.getConnection();
		T result = super.query(con, sql, rsh, params);
		JdbcUtils.releaseConnection(con);
		return result;
	}
	
	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection con = (Connection) JdbcUtils.getConnection();
		T result = super.query(con, sql, rsh);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int update(String sql) throws SQLException {
		Connection con =  (Connection) JdbcUtils.getConnection();
		int result = super.update(con, sql);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		Connection con = (Connection) JdbcUtils.getConnection();
		int result = super.update(con, sql, param);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		Connection con = (Connection) JdbcUtils.getConnection();
		int result = super.update(con, sql, params);
		JdbcUtils.releaseConnection(con);
		return result;
	}
}

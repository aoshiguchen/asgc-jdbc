package com.github.aoshiguchen.framework.jdbc.jdbctemplate;

import java.sql.Connection;
import java.util.List;

public interface IJdbcOperations {

	public <T> T execute(IJdbcCallback<T> callback);
	public int executeUpdate(final Connection conn ,final String sql,final Object[] params);
	public <T> T executeQeury(final Connection conn,final Class<T> clazz,final String sql,final Object[] params);
	public <T> List<T> executeQeuryForList(final Connection conn,final Class<T> clazz,final String sql,final Object[] params);

}


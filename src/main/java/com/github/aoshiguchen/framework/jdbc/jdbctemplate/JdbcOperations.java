package com.github.aoshiguchen.framework.jdbc.jdbctemplate;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.github.aoshiguchen.framework.jdbc.util.CacheUtil;
import com.github.aoshiguchen.framework.jdbc.util.ClassUtil;
import com.github.aoshiguchen.framework.jdbc.util.TypeUtil;

public class JdbcOperations implements IJdbcOperations{
	
	private Logger logger = Logger.getLogger(JdbcOperations.class);
	
	private static JdbcOperations instance;
	
	private JdbcOperations(){
		
	}
	
	public static synchronized JdbcOperations getInstance() {
		if (null == instance) {
			instance = new JdbcOperations();
		}

		return instance;
	}
	
	@Override
	public <T> T execute(IJdbcCallback<T> callback) {
		T res = null;
		try {
			res = callback.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int executeUpdate(final Connection conn,final String sql,final Object[] params) {
		return this.execute(new PreparedStatementCallback<Integer>(){
			
			@Override
			public Integer execute(PreparedStatement ps) {
				int res = -1;
				
				try{
					res =  ps.executeUpdate();
				}catch(SQLException e){
					e.printStackTrace();
				}
				
				return res;
			}

			@Override
			public Object[] getParams() {
				
				return params;
			}

			@Override
			public String getSql() {
				
				return sql;
			}

			@Override
			public Connection getConnection(){

				return conn;
			}
			
		});
	}

	@Override
	public <T> T executeQeury(final Connection conn, final Class<T> clazz,final String sql, final Object[] params) {
		return this.execute(new PreparedStatementCallback<T>() {

			@Override
			public T execute(PreparedStatement ps) {
				T obj = null;
				
				try{
					ResultSet resultSet = ps.executeQuery();
					
					if(resultSet.next()){
						
						if(TypeUtil.isBaseClass(clazz)){
							Object value = resultSet.getObject(1);
							obj = ClassUtil.valueOf(clazz, value);
						}else if(TypeUtil.isMap(clazz)){
							Map<String,Object> map = new HashMap<String,Object>();
							obj = (T)map;
							
							ResultSetMetaData rsmd = resultSet.getMetaData();
							int columnCount = rsmd.getColumnCount();
							
							for(int i = 1;i <= columnCount;i++){
								String name = rsmd.getColumnName(i);
								Object value = resultSet.getObject(i);
																
								map.put(CacheUtil.getFieldNameByColumn(name), value);							
							}
							
						}else{
							
							obj = clazz.newInstance();
							
							ResultSetMetaData rsmd = resultSet.getMetaData();
							int columnCount = rsmd.getColumnCount();
							
							for(int i = 1;i <= columnCount;i++){
								String name = rsmd.getColumnName(i);
								Object value = resultSet.getObject(i);
																
								ClassUtil.setFieldValue(clazz,name,obj, value);								
							}
							
						}
						
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				return obj;
			}

			@Override
			public Connection getConnection() {

				return conn;
			}

			@Override
			public Object[] getParams() {

				return params;
			}

			@Override
			public String getSql() {

				return sql;
			}
			
		});
	}

	@Override
	public <T> List<T> executeQeuryForList(final Connection conn,final Class<T> clazz, final String sql, final Object[] params) {
		return this.execute(new PreparedStatementCallback<List<T>>() {

			@Override
			public List<T> execute(PreparedStatement ps) {
				List<T> res = new ArrayList<T>();
				
				try{
					ResultSet resultSet = ps.executeQuery();
					ResultSetMetaData rsmd = resultSet.getMetaData();
					int columnCount = rsmd.getColumnCount();
					
					while(resultSet.next()){
						T obj = null;
						
						if(TypeUtil.isBaseClass(clazz)){
							Object value = resultSet.getObject(1);
							obj = ClassUtil.valueOf(clazz, value);
						}else if(TypeUtil.isMap(clazz)){
							Map<String,Object> map = new HashMap<String,Object>();
							obj = (T)map;
							
							for(int i = 1;i <= columnCount;i++){
								String name = rsmd.getColumnName(i);
								Object value = resultSet.getObject(i);
																
								map.put(CacheUtil.getFieldNameByColumn(name), value);							
							}
						}else{
							System.out.println(clazz);
							obj = clazz.newInstance();
							
							for(int i = 1;i <= columnCount;i++){
								String name = rsmd.getColumnName(i);
								Object value = resultSet.getObject(i);
																
								ClassUtil.setFieldValue(clazz,name,obj, value);	
							}
						}
						
						res.add(obj);
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				return res;
			}

			@Override
			public Connection getConnection() {

				return conn;
			}

			@Override
			public Object[] getParams() {

				return params;
			}

			@Override
			public String getSql() {

				return sql;
			}
			
		});
	}
}

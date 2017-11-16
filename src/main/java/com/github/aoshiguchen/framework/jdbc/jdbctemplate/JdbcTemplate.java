package com.github.aoshiguchen.framework.jdbc.jdbctemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.github.aoshiguchen.framework.jdbc.entity.IPo;
import com.github.aoshiguchen.framework.jdbc.entity.SqlParams;
import com.github.aoshiguchen.framework.jdbc.util.CacheUtil;

public class JdbcTemplate{
	
	private DataSource dataSource = CacheUtil.dataSource;
	private IJdbcOperations jdbcOperations;
	private static JdbcTemplate instance;
	
	private JdbcTemplate(){
		jdbcOperations = JdbcOperations.getInstance();
	}
	
	public static synchronized JdbcTemplate getInstance() {
		if (null == instance) {
			instance = new JdbcTemplate();
		}

		return instance;
	}
	
	public int update(String sql,Object ...params){
		int res = -1;
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			res = jdbcOperations.executeUpdate(conn,sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public int update(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return update(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public int update(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return update(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public <T> T query(Class<T> clazz,String sql,Object ...params){
		T res = null;
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			res = jdbcOperations.executeQeury(conn,clazz,sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public <T> T query(Class<T> clazz,String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return query(clazz,sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public <T> T query(Class<T> clazz,String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return query(clazz,sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public byte queryForByte(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForByte(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public byte queryForByte(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForByte(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public byte queryForByte(String sql,Object ...params){
		
		return query(byte.class,sql,params);
	}
	
	public short queryForShort(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForShort(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public short queryForShort(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForShort(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public short queryForShort(String sql,Object ...params){
		
		return query(short.class,sql,params);
	}
	
	public int queryForInt(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForInt(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public int queryForInt(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForInt(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public int queryForInt(String sql,Object ...params){
		
		return query(int.class,sql,params);
	}
	
	public Long queryForLong(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForLong(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public Long queryForLong(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForLong(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public Long queryForLong(String sql,Object ...params){
		
		return query(long.class,sql,params);
	}
	
	public float queryForFloat(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForFloat(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public float queryForFloat(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForFloat(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public float queryForFloat(String sql,Object ...params){
		
		return query(float.class,sql,params);
	}
	
	public double queryForDouble(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForDouble(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public double queryForDouble(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForDouble(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public double queryForDouble(String sql,Object ...params){
		
		return query(double.class,sql,params);
	}
	
	public char queryForChar(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForChar(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public char queryForChar(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForChar(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public char queryForChar(String sql,Object ...params){
		
		return query(char.class,sql,params);
	}
	
	public boolean queryForBoolean(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForBoolean(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public boolean queryForBoolean(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForBoolean(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public boolean queryForBoolean(String sql,Object ...params){
		
		return query(boolean.class,sql,params);
	}
	
	public String queryForString(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForString(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public String queryForString(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForString(sql,sqlParams.getParamList());
	}
	
	public String queryForString(String sql,Object ...params){
		
		return query(String.class,sql,params);
	}
	
	public Map<String,Object> queryForMap(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForMap(sql, sqlParams.getParamList());
	}
	
	public Map<String,Object> queryForMap(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForMap(sql,sqlParams.getParamList());
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> queryForMap(String sql,Object ...params){
		
		return (Map<String,Object>)query(HashMap.class,sql,params);
	}
	
	public <T> List<T> queryForList(Class<T> clazz,String sql,Object ...params){
		List<T> res = null;
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			res = jdbcOperations.executeQeuryForList(conn,clazz,sql, params);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public <T> List<T> queryForList(Class<T> clazz,String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForList(clazz,sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public <T> List<T> queryForList(Class<T> clazz,String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForList(clazz,sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public List<Byte> queryForListByte(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForListByte(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public List<Byte> queryForListByte(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForListByte(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public List<Byte> queryForListByte(String sql,Object ...params){
		
		return queryForList(byte.class,sql,params);
	}
	
	public List<Short> queryForListShort(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForListShort(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public List<Short> queryForListShort(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForListShort(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public List<Short> queryForListShort(String sql,Object ...params){
		
		return queryForList(short.class,sql,params);
	}
	
	public List<Integer> queryForListInt(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForListInt(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public List<Integer> queryForListInt(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForListInt(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public List<Integer> queryForListInt(String sql,Object ...params){
		
		return queryForList(int.class,sql,params);
	}
	
	public List<Long> queryForListLong(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForListLong(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public List<Long> queryForListLong(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForListLong(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public List<Long> queryForListLong(String sql,Object ...params){
		
		return queryForList(long.class,sql,params);
	}
	
	public List<Float> queryForListFloat(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForListFloat(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public List<Float> queryForListFloat(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForListFloat(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public List<Float> queryForListFloat(String sql,Object ...params){
		
		return queryForList(float.class,sql,params);
	}
	
	public List<Double> queryForListDouble(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForListDouble(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public List<Double> queryForListDouble(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForListDouble(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public List<Double> queryForListDouble(String sql,Object ...params){
		
		return queryForList(double.class,sql,params);
	}
	
	public List<Character> queryForListChar(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForListChar(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public List<Character> queryForListChar(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForListChar(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public List<Character> queryForListChar(String sql,Object ...params){
		
		return queryForList(char.class,sql,params);
	}
	
	public List<Boolean> queryForListBoolean(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForListBoolean(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public List<Boolean> queryForListBoolean(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForListBoolean(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public List<Boolean> queryForListBoolean(String sql,Object ...params){
		
		return queryForList(boolean.class,sql,params);
	}
	
	public List<String> queryForListString(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForListString(sqlParams.getSql(), sqlParams.getParamList());
	}
	
	public List<String> queryForListString(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForListString(sql,sqlParams.getParamList());
	}
	
	public List<String> queryForListString(String sql,Object ...params){
		
		return queryForList(String.class,sql,params);
	}
	
	public List<Map> queryForListMap(String sql,Object ...params){
		
		
		return queryForList(Map.class, sql, params);
	}
	
	public List<Map> queryForListMap(String sql,Map<String,Object> params){
		SqlParams sqlParams = new SqlParams(sql, params);
		
		return queryForListMap(sqlParams.getSql(),sqlParams.getParamList());
	}
	
	public List<Map> queryForListMap(String sql,IPo model){
		SqlParams sqlParams = new SqlParams(sql, model);
		
		return queryForListMap(sqlParams.getSql(),sqlParams.getParamList());
	}
	
}

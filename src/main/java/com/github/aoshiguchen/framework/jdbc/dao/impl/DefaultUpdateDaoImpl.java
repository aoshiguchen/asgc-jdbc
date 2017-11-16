package com.github.aoshiguchen.framework.jdbc.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.aoshiguchen.framework.jdbc.dao.ISql;
import com.github.aoshiguchen.framework.jdbc.jdbctemplate.JdbcTemplate;
import com.github.aoshiguchen.framework.jdbc.util.CacheUtil;
import com.github.aoshiguchen.framework.jdbc.util.ClassUtil;
import com.github.aoshiguchen.framework.jdbc.util.ObjectUtil;
import com.github.aoshiguchen.framework.jdbc.util.StringUtil;

public class DefaultUpdateDaoImpl extends AbstractUpdateDaoImpl{
	
	private Class<?> entityClass;
	protected  String tbName = "";
	
	private ISql sql;
		
	public DefaultUpdateDaoImpl(Class<?> entityClass,String tbName) {
		this.tbName = tbName;
		this.entityClass = entityClass;
		this.sql = CacheUtil.getSqlBuilder(entityClass);
	}
	
	public DefaultUpdateDaoImpl(Class<?> entityClass) {
		this.tbName = CacheUtil.getTableNameByClass(entityClass);
		this.entityClass = entityClass;
		this.sql = CacheUtil.getSqlBuilder(entityClass);
	}
	
	@Override
	public Long getRecordCount() {

		return JdbcTemplate.getInstance().queryForLong(sql.getRecordCount());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll() {
				
		 return  JdbcTemplate.getInstance().queryForList((Class<T>)entityClass,sql.findAll());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T findById(String id) {

		return JdbcTemplate.getInstance().query((Class<T>)entityClass,sql.findById(id));
	}
	

	@Override
	public <T> T find(String id) {

		return findById(id);
	}

	@Override
	public <T> T findSingle(String id) {

		return findById(id);
	}

	@Override
	public <T> T findSingle(T po) {
		
		return findSingle(po,ClassUtil.getFieldNameSet(po.getClass()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T findSingle(T po, Set<String> filterField) {
		if(filterField.size() == 0){
			return findSingle(po);
		}
		
		Map<String,Object> params = new HashMap<>();
		
		for(String item : filterField){
			params.put(item, ClassUtil.getFieldValue(po,item));
		}
		
		List<T> res = JdbcTemplate.getInstance().queryForList((Class<T>)po.getClass(),sql.find(filterField,params));
		
		if(null == res || res.size() == 0){
			return null;
		}
		
		return res.get(0);
	}

	@Override
	public <T> T findSingle(T po, String... field) {
		
		return findSingle(po, ObjectUtil.arrayToSet(field));
	}

	@Override
	public <T> List<T> findMulti(T po) {
		
		return findMulti(po,ClassUtil.getFieldNameSet(po.getClass()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findMulti(T po, Set<String> filterField) {
		if(filterField.size() == 0){
			return findAll();
		}

		Map<String,Object> params = new HashMap<>();
		
		for(String item : filterField){
			params.put(item, ClassUtil.getFieldValue(po,item));
		}
		
		List<T> res = JdbcTemplate.getInstance().queryForList((Class<T>)po.getClass(),sql.find(filterField,params));
		
		return res;
	}

	@Override
	public <T> List<T> findMulti(T po, String... field) {
		
		return findMulti(po, ObjectUtil.arrayToSet(field));
	}

	@Override
	public <T> int delete(T po) {

		return deleteById((String)ClassUtil.getFieldValue(po,"id"));
	}

	@Override
	public int deleteById(String id) {

		return JdbcTemplate.getInstance().update(sql.deleteById(id));
	}

	@Override
	public <T> int delete(T po, Set<String> filterField) {
		if(filterField.size() == 0){
			return delete(po);
		}
		
		Map<String,Object> params = new HashMap<>();
		
		for(String item : filterField){
			params.put(item, ClassUtil.getFieldValue(po,item));
		}
		
		return JdbcTemplate.getInstance().update(sql.delete( filterField,params));
	}

	@Override
	public <T> int delete(T po, String... field) {
		
		return delete(po, ObjectUtil.arrayToSet(field));
	}

	@Override
	public int deleteAll() {

		return  JdbcTemplate.getInstance().update(sql.deleteAll());
	}

	@Override
	public int delete(String id) {

		return deleteById(id);
	}

	@Override
	public <T> int update(T po) {

		return update(po,ClassUtil.getFieldNameSet(po.getClass()));
	}

	@Override
	public <T> int update(T po, Set<String> filterField) {

		if(null == filterField || filterField.size() == 0){
			return update(po);
		}

		Map<String,Object> params = new HashMap<>();
		
		for(String item : filterField){
			if(!item.equals("id")){
				params.put(item, ClassUtil.getFieldValue(po,item));
			}
		}

		params.put("id",ClassUtil.getFieldValue(po,"id"));
		
		return JdbcTemplate.getInstance().update(sql.update(filterField,params));
	}

	@Override
	public <T> int update(T po, String... field) {

		return update(po, ObjectUtil.arrayToSet(field));
	}

	@Override
	public <T> T create(T po) {

		return create(po,ClassUtil.getFieldNameSet(po.getClass()));
	}

	@Override
	public <T> T create(T po, Set<String> filterField) {
		if(null == filterField || filterField.size() == 0){
			return create(po);
		}
		
		String id = StringUtil.getRandomUuidString();
		Map<String,Object> params = new HashMap<>();

		params.put("id",id);
		for(String item : filterField){
			if(!item.equals("id")){
				params.put(item, ClassUtil.getFieldValue(po,item));	
			}
		}
		
		int count = JdbcTemplate.getInstance().update(sql.create(filterField,params));
		
		if(1 == count){
			return findById(id);
		}
		
		return null;
	}

	@Override
	public <T> T create(T po, String... field) {

		return create(po, ObjectUtil.arrayToSet(field));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findPage(int beginNo, int pageSize) {
		
		return  JdbcTemplate.getInstance().queryForList((Class<T>)entityClass,sql.findPage(),beginNo,pageSize);
	}

	@Override
	public <T> List<T> findPage(T po, int beginNo, int pageSize) {
		
		return findPage(po,beginNo, pageSize,ClassUtil.getFieldNameSet(po.getClass()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findPage(T po, int beginNo, int pageSize,
			Set<String> filterField) {
		
		if(filterField.size() == 0){
			return findAll();
		}

		Map<String,Object> params = new HashMap<>();
		
		for(String item : filterField){
			params.put(item, ClassUtil.getFieldValue(po,item));
		}
		
		params.put("beginNo", beginNo);
		params.put("pageSize", pageSize);
				
		List<T> res = JdbcTemplate.getInstance().queryForList((Class<T>)po.getClass(),sql.findPage(filterField,params));
		
		return res;
	}

	@Override
	public <T> List<T> findPage(T po, int beginNo, int pageSize,
			String... field) {
		
		return findPage(po, beginNo, pageSize, ObjectUtil.arrayToSet(field));
	}
	
	
}


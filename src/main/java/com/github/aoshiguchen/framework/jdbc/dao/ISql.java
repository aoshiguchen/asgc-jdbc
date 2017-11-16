package com.github.aoshiguchen.framework.jdbc.dao;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * sql生成器接口
 * @author aoshiguchen
 * @time 2017-04-01
 */
public interface ISql {
	
	public String getRecordCount();
	public String findAll();
	public String findById(String id);
	public String find(Set<String> filterField,Map<String,Object> params);
	public String deleteById(String id);
	public String delete(Set<String> filterField,Map<String,Object> params);
	public String deleteAll();
	public String update(Set<String> filterField,Map<String,Object> params);
	public String create(Set<String> filterField,Map<String,Object> params);
	public String findPage();
	public String findPage(Set<String> filterField,Map<String,Object> params);
	
	public String getColumnByFieldName(String name);
	public Field getFieldByColumn(String column);
	
	
}
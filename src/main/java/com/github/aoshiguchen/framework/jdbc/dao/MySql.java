package com.github.aoshiguchen.framework.jdbc.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.aoshiguchen.framework.jdbc.util.ObjectUtil;
import com.github.aoshiguchen.framework.jdbc.util.StringUtil;
import com.github.aoshiguchen.framework.jdbc.util.TypeUtil;

public class MySql extends AbstractSqlBuilder{

	public MySql(Class<?> clazz) {
		super(clazz);
	}

	@Override
	public String getRecordCount() {
		
		return "select count(id) from `" + tableName + "`";
	}

	@Override
	public String findAll() {
		
		return "select * from `" + tableName + "`";
	}

	@Override
	public String findById(String id) {

		if(id == null){
			return "select * from `" + tableName + "` where id is null";
		}
		
		return "select * from `" + tableName + "` where id = " + getSqlValue(id);
	}

	@Override
	public String find(Set<String> filterField,Map<String,Object> params) {
		
		String sqlPrefix = "select * from `" + tableName + "` where ";
		StringBuffer sbSuffix = new StringBuffer();
		String sqlSuffix = "";
		
		for(String item : filterField){
			Object val = params.get(item);
			
			if(null == val){
				sbSuffix.append(getColumnByFieldName(item) + " is null and ");
			}else{
				sbSuffix.append(getColumnByFieldName(item) + " = " + val + " and ");
			}
		}
		
		sqlSuffix = sbSuffix.toString();
		
		if(sqlSuffix.endsWith("and ")){
			sqlSuffix = sqlSuffix.substring(0,sqlSuffix.length() - 4);
		}
		
		return sqlPrefix + sqlSuffix;
	}

	@Override
	public String deleteById(String id) {
		
		if(id == null){
			return "delete from `" + tableName + "` where id is null";
		}

		return "delete from `" + tableName + "` where id = " + getSqlValue(id);
	}

	@Override
	public String delete(Set<String> filterField,Map<String,Object> params) {
		
		String sqlPrefix = "delete from `" + tableName + "` where ";
		StringBuffer sbSuffix = new StringBuffer();
		String sqlSuffix = "";
		
		for(String item : filterField){
			Object val = params.get(item);
			if(val == null){
				sbSuffix.append(getColumnByFieldName(item) + " is null and ");
			}else{
				sbSuffix.append(getColumnByFieldName(item) + " = " + getSqlValue(val) + " and ");
			}
			
		}
		
		sqlSuffix = sbSuffix.toString();
		
		if(sqlSuffix.endsWith("and ")){
			sqlSuffix = sqlSuffix.substring(0,sqlSuffix.length() - 4);
		}
		
		return sqlPrefix + sqlSuffix;
	}

	@Override
	public String deleteAll() {
		
		return "delete `" + tableName + "`";
	}

	@Override
	public String update(Set<String> filterField,Map<String,Object> params) {
		
		String sqlPrefix = "update `" + tableName + "` set ";
		StringBuffer sbSuffix = new StringBuffer();
		String sqlSuffix = "";
		
		for(String item : filterField){
			if(!item.equals("id")){
				Object val = params.get(item);
				sbSuffix.append(getColumnByFieldName(item) + " = " + getSqlValue(val) + " , ");
			}
		}
		
		sqlSuffix = sbSuffix.toString();
		
		if(sqlSuffix.endsWith(", ")){
			sqlSuffix = sqlSuffix.substring(0,sqlSuffix.length() - 2);
		}
		
		Object val = params.get("id");
		if(val == null){
			sqlSuffix += " where id is null";
		}else{
			sqlSuffix += " where id = " + getSqlValue(val);
		}
		
		return sqlPrefix + sqlSuffix;
	}

	@Override
	public String create(Set<String> filterField,Map<String,Object> params) {
		
		String sql = "";
		StringBuffer sbSql = new StringBuffer();
		
		sbSql.append("insert into `" + tableName + "` (");
		List<String> fieldList = ObjectUtil.setToList(filterField);
		
		for(String field : fieldList){
			sbSql.append(getColumnByFieldName(field)).append(",");
		}
		
		if(sbSql.charAt(sbSql.length() - 1) == ','){
			sbSql.deleteCharAt(sbSql.length() - 1);
		}
		
		sbSql.append(") values (");
		
		for(String item : filterField){
			sbSql.append(getSqlValue(params.get(item)) + " ,");

		}
			
		if(sbSql.charAt(sbSql.length() - 1) == ','){
			sbSql.deleteCharAt(sbSql.length() - 1);
		}
		
		sbSql.append(")");
		
		return sbSql.toString();
	}

	@Override
	public String findPage() {
		
		return "select * from `" + tableName + "` limit ?,?";
	}

	@Override
	public String findPage(Set<String> filterField,Map<String,Object> params) {
		
		String sqlPrefix = "select * from `" + tableName + "` where ";
		StringBuffer sbSuffix = new StringBuffer();
		String sqlSuffix = "";
		
		for(String item : filterField){
			Object val = params.get(item);
			if(val == null){
				sbSuffix.append(getColumnByFieldName(item) + " = is null and ");
			}else{
				sbSuffix.append(getColumnByFieldName(item) + " = " + val + " and ");
			}
		}
		
		sqlSuffix = sbSuffix.toString();
		
		if(sqlSuffix.endsWith("and ")){
			sqlSuffix = sqlSuffix.substring(0,sqlSuffix.length() - 4);
		}
		
		Object beginNo = params.get("beginNo");
		Object pageSize = params.get("pageSize");
		
		sqlSuffix += " limit beginNo = " + beginNo + ", pageSize = " + pageSize;
		
		return sqlPrefix + sqlSuffix;
	}
	
	private Object getSqlValue(Object obj){
		
		if(TypeUtil.isType(String.class,obj.getClass())){
			return "'" + obj + "'";
		}
		
		return obj;
	}

}


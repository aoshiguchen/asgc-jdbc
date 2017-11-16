package com.github.aoshiguchen.framework.jdbc.dao;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.github.aoshiguchen.framework.jdbc.annotation.Column;
import com.github.aoshiguchen.framework.jdbc.annotation.NotColumn;
import com.github.aoshiguchen.framework.jdbc.util.CacheUtil;
import com.github.aoshiguchen.framework.jdbc.util.StringUtil;

/**
 * 抽象的sql生成器
 * @author aoshiguchen
 * @time 2017-03-12	
 */
public class AbstractSqlBuilder implements ISql{
	
	protected Class<?> clazz;
	protected String tableName;
	protected Map<String,Field> nameFieldMap = new HashMap<>();
	protected Map<Field,String> fieldNameMap = new HashMap<>();
	
	protected Map<Field,String> fieldColumnMap = new HashMap<>();
	protected Map<String,Field> columnFieldMap = new HashMap<>();
	
	protected Map<String,String> fieldNameColumnMap = new HashMap<>();
	protected Map<String,String> columnFieldNameMap = new HashMap<>();
	
	protected boolean isInit = false;
	
	private AbstractSqlBuilder(){
		
	}
	
	public AbstractSqlBuilder(Class<?> clazz){
		
		this(clazz,CacheUtil.getTableNameByClass(clazz));
		
	}
	
	public AbstractSqlBuilder(Class<?> clazz,String tableName){
		
		this.clazz = clazz;
		this.tableName = tableName;
		
		if(null == clazz){
			throw new RuntimeException("class is null !");
		}
		
		if(StringUtil.isEmpty(tableName)){
			throw new RuntimeException("tableName is null !");
		}
		
		CacheUtil.addClassSqlMap(clazz,this);
		CacheUtil.addTableNameSqlMap(tableName, this);
	}
	
	protected void init(){
		
		if(isInit)return;
		isInit = true;
		
		Class<?> tmpClass = clazz;
		
		while(tmpClass != Object.class){
			Field[] fields = tmpClass.getDeclaredFields();
			
			for(Field item : fields){
				nameFieldMap.put(item.getName(), item);
				fieldNameMap.put(item, item.getName());
				
				if(!item.isAnnotationPresent(NotColumn.class)){
					if(item.isAnnotationPresent(Column.class)){
						Column column = item.getAnnotation(Column.class);
						
						fieldColumnMap.put(item, column.value());
						columnFieldMap.put(column.value(), item);
						
						fieldNameColumnMap.put(item.getName(), column.value());
						columnFieldNameMap.put(column.value(), item.getName());
					}else{
						if(null != CacheUtil.fieldNameMapping){
							String colum = CacheUtil.fieldNameMapping.codeToDb(item.getName());
							
							fieldColumnMap.put(item, colum);
							columnFieldMap.put(colum, item);
							
							fieldNameColumnMap.put(item.getName(), colum);
							columnFieldNameMap.put(colum, item.getName());
						}else{
							fieldColumnMap.put(item, item.getName());
							columnFieldMap.put(item.getName(), item);
							
							fieldNameColumnMap.put(item.getName(), item.getName());
							columnFieldNameMap.put(item.getName(), item.getName());
						}
					}
				}
			}
			
			tmpClass = tmpClass.getSuperclass();
		}
	}

	public String getRecordCount() {

		return null;
	}

	public String findAll() {

		return null;
	}

	public String findById(String id) {

		return null;
	}

	public String find(Set<String> filterField,Map<String,Object> params) {

		return null;
	}

	public String deleteById(String id) {

		return null;
	}

	public String delete(Set<String> filterField,Map<String,Object> params) {

		return null;
	}

	public String deleteAll() {

		return null;
	}

	public String update(Set<String> filterField,Map<String,Object> params) {

		return null;
	}

	public String create(Set<String> filterField,Map<String,Object> params) {

		return null;
	}

	public String findPage() {

		return null;
	}

	public String findPage(Set<String> filterField,Map<String,Object> params) {

		return null;
	}
	
	public String getColumnByFieldName(String name){
		init();
		
		return fieldNameColumnMap.get(name);
	}
	
	public Field getFieldByColumn(String column){
		init();
		
		return columnFieldMap.get(column);
	}
}

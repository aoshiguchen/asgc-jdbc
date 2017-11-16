package com.github.aoshiguchen.framework.jdbc.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.aoshiguchen.framework.jdbc.annotation.Table;
import com.github.aoshiguchen.framework.jdbc.dao.ISql;
import com.github.aoshiguchen.framework.jdbc.dao.MySql;
import com.github.aoshiguchen.framework.jdbc.entity.BigHumpUnderlineNameMapping;
import com.github.aoshiguchen.framework.jdbc.entity.Conf;
import com.github.aoshiguchen.framework.jdbc.entity.DefaultNameMapping;
import com.github.aoshiguchen.framework.jdbc.entity.NameMapping;
import com.github.aoshiguchen.framework.jdbc.entity.SmallHumpUnderlineNameMapping;

public class CacheUtil {
	
	private static final Map<String,NameMapping> nameMapping;
	private static final Map<Class<?>,String> classTableMap = new ConcurrentHashMap<>();
	private static final Map<String,Class<?>> tableClassMap = new ConcurrentHashMap<>();
	
	public static final Map<Class<?>,ISql> classSqlMap = new ConcurrentHashMap<>();
	public static final Map<String,ISql> tableNameSqlMap = new ConcurrentHashMap<>();
	
	public static DataSource dataSource;
	
	private static Conf config = null;
	
	private static String dbType = Consts.DB.Type.MYSQL;
		
	public static NameMapping classNameMapping;
	public static NameMapping fieldNameMapping;
	
	static{
		nameMapping = new HashMap<>();
		nameMapping.put(NameMapping.Type.DEFAULT, new DefaultNameMapping());
		nameMapping.put(NameMapping.Type.BIG_HUMP_UNDERLINE, new BigHumpUnderlineNameMapping());
		nameMapping.put(NameMapping.Type.SMALL_HUMP_UNDERLINE, new SmallHumpUnderlineNameMapping());
				
//		classNameMapping = nameMapping.get(NameMapping.Type.BIG_HUMP_UNDERLINE);
//		fieldNameMapping = nameMapping.get(NameMapping.Type.SMALL_HUMP_UNDERLINE);
	}
	
	/**
	 * 根据Class对象获取对应的表名
	 * 1、如果有Table注解，则取注解值为对应表名
	 * 2、如果没有注解，则根据映射类型获取表名
	 * @param clazz
	 * @return
	 */
	public static String getTableNameByClass(Class<?> clazz){
		String tbName = "";
		
		if(classTableMap.containsKey(clazz)){
			return classTableMap.get(clazz);
		}
		
		if(clazz.isAnnotationPresent(Table.class)){
			
			Table table = clazz.getAnnotation(Table.class);
			tbName = table.value();
			
		}else{
			String[] s = clazz.getName().split("\\.");
			
			String pre = s[s.length - 1].substring(0,2);
			
			if(pre.equals("Po")){
				tbName = s[s.length - 1].substring(2);
			}else{
				tbName = s[s.length - 1];
			}
			
			if(null != classNameMapping){
				tbName = classNameMapping.codeToDb(tbName);
			}
		}
		
		classTableMap.put(clazz, tbName);
		tableClassMap.put(tbName, clazz);
		
		return tbName;
	}
	
	/**
	 * 根据表名获取对应的类
	 * 1、如果有Table注解，则取注解值为对应表名
	 * 2、如果没有注解，则根据映射类型获取表名
	 * @param clazz
	 * @return
	 */
	public static Class<?> getClassByTableName(String tbName){
		Class<?> clazz = null;
		
		if(tableClassMap.containsKey(tbName)){
			return tableClassMap.get(tbName);
		}
		
		if(null != classNameMapping){
			tbName = classNameMapping.dbToCode(tbName);
		}
		
		return clazz;
	}

	public static String getFieldNameByColumn(String column){
		
		if(null != fieldNameMapping){
			return fieldNameMapping.dbToCode(column);
		}
		
		return column;
	}
	
	public static void addClassSqlMap(Class<?> clazz,ISql isql){
		classSqlMap.put(clazz, isql);
	}
	
	public static void addTableNameSqlMap(String tableName,ISql isql){
		tableNameSqlMap.put(tableName, isql);
	}
	
	public static void setConfig(Conf config){
		
		if(null == config){
			return;
		}
		
		CacheUtil.config = config;
		
		switch (config.getDbType()) {
			case Consts.DB.Type.MYSQL:
				CacheUtil.dbType = Consts.DB.Type.MYSQL;
				break;
			case Consts.DB.Type.ORACLE:
				CacheUtil.dbType = Consts.DB.Type.ORACLE;		
					break;
			case Consts.DB.Type.SQLSERVER:
				CacheUtil.dbType = Consts.DB.Type.SQLSERVER;
				break;
		}
		
		switch (config.getClassNameMappingType()) {
			case Consts.Properties.Val.BIG_HUMP_UNDERLINE:
				classNameMapping = nameMapping.get(NameMapping.Type.BIG_HUMP_UNDERLINE);
				break;
			case Consts.Properties.Val.SMALL_HUMP_UNDERLINE:
				classNameMapping = nameMapping.get(NameMapping.Type.SMALL_HUMP_UNDERLINE);
				break;
			case Consts.Properties.Val.DEFAULE:
				classNameMapping = nameMapping.get(NameMapping.Type.DEFAULT);
				break;
		}
		
		switch (config.getFieldNameMappingType()) {
			case Consts.Properties.Val.BIG_HUMP_UNDERLINE:
				fieldNameMapping = nameMapping.get(NameMapping.Type.BIG_HUMP_UNDERLINE);
				break;
			case Consts.Properties.Val.SMALL_HUMP_UNDERLINE:
				fieldNameMapping = nameMapping.get(NameMapping.Type.SMALL_HUMP_UNDERLINE);
				break;
			case Consts.Properties.Val.DEFAULE:
				fieldNameMapping = nameMapping.get(NameMapping.Type.DEFAULT);
				break;
		}
	}
	
	public static ISql getSqlBuilder(Class<?> clazz){
		
		if(classSqlMap.containsKey(clazz)){
			return classSqlMap.get(clazz);
		}
		
		ISql isql = null;
		switch (CacheUtil.dbType ) {
			case Consts.DB.Type.MYSQL:
				isql = new MySql(clazz);
				break;
		}
		
		return isql;
	}
}

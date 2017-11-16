package com.github.aoshiguchen.framework.jdbc.util;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.github.aoshiguchen.framework.jdbc.dao.AbstractSqlBuilder;
import com.github.aoshiguchen.framework.jdbc.dao.ISql;

public class ClassUtil {
	
	public static <T> T valueOf(Class<T> clazz,Object obj){
		T res = null;
				
		Method method = null;
		

		method = getMethod(clazz,"valueOf", obj.getClass());

		if(null == method){
			method = getMethod(clazz,"valueOf",TypeUtil.getBaseClass(obj.getClass()));
		}
		
		if(null == method){
			method = getMethod(clazz,"valueOf",Object.class);
		}
		
		if(null == method){
			method = getMethod(clazz,"valueOf",String.class);
			if(null != method){
				try {
					res = (T)method.invoke(null,String.valueOf(obj));
					return res;
				} catch (Exception e) {

				} 
			}
		}
		
		if(null == method){
			throw new RuntimeException();
		}
		
		try {
			res = (T) method.invoke(null, obj);
		} catch (Exception e) {

		} 
		
		return res;
	}
	
	public static Method getMethod(Class<?> clazz,String name,Class<?> type){
		
		Method method = null;
		
		try {
			clazz = TypeUtil.getPackClass(clazz);
			method = clazz.getDeclaredMethod(name, type);
		} catch (NoSuchMethodException e) {

		} 
		
		return method;
	}
	
	public static List<String> getFieldNameList(Class<?> clazz){
		if(null != clazz){
			List<String> list = new ArrayList<>();
			
			while(clazz != Object.class){
				Field[] fields = clazz.getDeclaredFields();
				
				for(Field item : fields){
					list.add(item.getName());
				}
				
				clazz = clazz.getSuperclass();
			}
			
			return list;
		}
		
		return null;
	}
	
	public static Set<String> getFieldNameSet(Class<?> clazz){
		
		return ObjectUtil.listToSet(getFieldNameList(clazz));
	}
	
	public static Field getField(Class<?> clazz,String fieldName){
		Field field = null;
		
		while(clazz != Object.class){
			
			try {
				field = clazz.getDeclaredField(fieldName);
				
				break;
				
			} catch (Exception e) {
				//e.printStackTrace();
			} 
			
			clazz = clazz.getSuperclass();
		}
		
		return field;
	}
	
	public static Method getMethod(Class<?> clazz,String methodName,Class<?> ...parameterTypes){
		Method method = null;
		
		while(clazz != Object.class){
			
			try {
				method = clazz.getDeclaredMethod(methodName, parameterTypes);
				
				break;
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			clazz = clazz.getSuperclass();
		}
		
		return method;
	}
	
	public static void setFieldValue(Class<?> clazz,Field field,Object obj,Object value){
				
		if(field != null){
			
			try {
				
				field.setAccessible(true);
				
				field.set(obj, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void setFieldValue(Class<?> clazz,String column,Object obj,Object value){
		//兼容直接使用jdbcTemplate进行数据库操作
		if(!CacheUtil.classSqlMap.containsKey(clazz)){
			new AbstractSqlBuilder(clazz);
		}
		
		ISql sql = CacheUtil.classSqlMap.get(clazz);
		
		Field field = sql.getFieldByColumn(column);
		
		setFieldValue(clazz, field, obj, value);
	}
	
	public static Object getFieldValue(Object obj,String fieldName){
		Object res = null;
		
		Class<?> clazz = obj.getClass();
		Field field = getField(clazz, fieldName);

		try {
			field.setAccessible(true);
			
			res = field.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return res;
	}
	
	public static boolean isWrapClass(Class<?> clz) { 
       
		return clz.isPrimitive();
	} 
}

package com.github.aoshiguchen.framework.jdbc.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TypeUtil {
	
	private static final Set<Class<?>> baseClass = new HashSet<>();
	
	private static final Map<Class<?>,Class<?>> packClassMap = new HashMap<>();
	
	private static final Map<Class<?>,Class<?>> baseClassMap = new HashMap<>();

	static{
		//----------------------------------------------------------
		
		baseClass.add(byte.class);
		baseClass.add(short.class);
		baseClass.add(int.class);
		baseClass.add(long.class);
		baseClass.add(float.class);
		baseClass.add(double.class);
		baseClass.add(char.class);
		baseClass.add(boolean.class);
		
		baseClass.add(Byte.class);
		baseClass.add(Short.class);
		baseClass.add(Integer.class);
		baseClass.add(Long.class);
		baseClass.add(Float.class);
		baseClass.add(Double.class);
		baseClass.add(Character.class);
		baseClass.add(Boolean.class);
		
		baseClass.add(String.class);
		
		//----------------------------------
		
		packClassMap.put(byte.class, Byte.class);
		packClassMap.put(short.class, Short.class);
		packClassMap.put(int.class, Integer.class);
		packClassMap.put(long.class, Long.class);
		packClassMap.put(float.class, Float.class);
		packClassMap.put(double.class, Double.class);
		packClassMap.put(char.class, Character.class);
		packClassMap.put(boolean.class, Boolean.class);
		
		packClassMap.put(Byte.class, Byte.class);
		packClassMap.put(Short.class, Short.class);
		packClassMap.put(Integer.class, Integer.class);
		packClassMap.put(Long.class, Long.class);
		packClassMap.put(Float.class, Float.class);
		packClassMap.put(Double.class, Double.class);
		packClassMap.put(Character.class, Character.class);
		packClassMap.put(Boolean.class, Boolean.class);
		
		packClassMap.put(String.class, String.class);
		
		//----------------------------------
		
		baseClassMap.put(byte.class, byte.class);
		baseClassMap.put(short.class, short.class);
		baseClassMap.put(int.class, int.class);
		baseClassMap.put(long.class, long.class);
		baseClassMap.put(float.class, float.class);
		baseClassMap.put(double.class, double.class);
		baseClassMap.put(char.class, char.class);
		baseClassMap.put(boolean.class, boolean.class);
		
		baseClassMap.put(Byte.class, byte.class);
		baseClassMap.put(Short.class, short.class);
		baseClassMap.put(Integer.class, int.class);
		baseClassMap.put(Long.class, long.class);
		baseClassMap.put(Float.class, float.class);
		baseClassMap.put(Double.class, double.class);
		baseClassMap.put(Character.class, char.class);
		baseClassMap.put(Boolean.class, boolean.class);
		
		baseClassMap.put(String.class, String.class);
	}
	
	/**
	 * 判断是否是java基本数据类型(包括基本数据类型的包装类型)
	 * @param clazz
	 * @return
	 */
	public static boolean isBaseClass(Class<?> clazz){
		
		return baseClass.contains(clazz);
	}
	
	/**
	 * 判断是否是java包装类型（不包括基本数据类型的包装类型）
	 * @param clazz
	 * @return
	 */
	public static boolean isPackClass(Class<?> clazz){
		
		return !isBaseClass(clazz);
	}
	
	public static Class<?> getPackClass(Class<?> clazz){
		
		if(packClassMap.containsKey(clazz)){
			return packClassMap.get(clazz);
		}
		
		return clazz;
	}
	
	public static Class<?> getBaseClass(Class<?> clazz){
		
		if(baseClassMap.containsKey(clazz)){
			return baseClassMap.get(clazz);
		}
		
		return clazz;
	}
	
	public static boolean isType(Class<?> su,Class<?> ch){
		
		su = getPackClass(su);
		ch = getPackClass(ch);
		
		if(su == ch){
			return true;
		}
		
		while(ch != Object.class){
			ch = ch.getSuperclass();

			if(su == ch){
				return true;
			}
			
			if(su.isInterface()){
				Class<?>[] interfaces = ch.getInterfaces();
				for(Class<?> inter : interfaces){
					if(inter == su){
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public static boolean isMap(Class<?> clazz){
		return isType(Map.class,clazz);
	}
}

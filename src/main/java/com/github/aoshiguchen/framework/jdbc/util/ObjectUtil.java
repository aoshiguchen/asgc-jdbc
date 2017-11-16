package com.github.aoshiguchen.framework.jdbc.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.aoshiguchen.framework.jdbc.entity.BigHumpUnderlineNameMapping;
import com.github.aoshiguchen.framework.jdbc.entity.DefaultNameMapping;
import com.github.aoshiguchen.framework.jdbc.entity.NameMapping;
import com.github.aoshiguchen.framework.jdbc.entity.SmallHumpUnderlineNameMapping;

public class ObjectUtil {
	
	public static <T> List<T> setToList(Set<T> set){
		List<T> list = new ArrayList<T>();
		
		for(T t : set){
			list.add(t);
		}
		
		return list;
	}
	
	public static <T> Set<T> arrayToSet(T[] array){
		
		Set<T> res= new HashSet<T>();
		
		for(T item : array){
			res.add(item);
		}
		
		return res;
	}
	
	public static <T> List<T> arrayToList(T[] array){
		List<T> res = new ArrayList<T>();
		
		for(T item : array){
			res.add(item);
		}
		
		return res;
	}
	
	public static <T> Set<T> listToSet(List<T> list){
		Set<T> res = new HashSet<T>();
		
		for(T item : list){
			res.add(item);
		}
		
		return res;
	}
	
}


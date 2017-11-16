package com.github.aoshiguchen.framework.jdbc.util;

import java.lang.reflect.Field;
import com.github.aoshiguchen.framework.jdbc.annotation.Dao;
import com.github.aoshiguchen.framework.jdbc.dao.proxy.DefaultUpdateDao;

public class DaoUtil {
	
	public static void inject(Object obj){
		Class<?> cls = obj.getClass();

		for(Field field : cls.getDeclaredFields()){
			
			if(field.isAnnotationPresent(Dao.class)){
				
				Dao daoAnnotation = field.getAnnotation(Dao.class);
				Class<?> clazz = daoAnnotation.value();
								
				try {
					if(!field.isAccessible()){
						field.setAccessible(true);
					}
					
					field.set(obj,new DefaultUpdateDao(clazz));
					
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				
			}
			
		}
		
	}
	
}

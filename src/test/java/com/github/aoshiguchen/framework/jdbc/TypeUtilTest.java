package com.github.aoshiguchen.framework.jdbc;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.github.aoshiguchen.framework.jdbc.util.TypeUtil;

public class TypeUtilTest {

	@Test
	public void test1(){
		System.out.println(TypeUtil.isType(Object.class,String.class));
		System.out.println(TypeUtil.isType(Object.class,Integer.class));
		System.out.println(TypeUtil.isType(Map.class,HashMap.class));
	}
	
}


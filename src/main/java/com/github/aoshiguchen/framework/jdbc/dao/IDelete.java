package com.github.aoshiguchen.framework.jdbc.dao;


import java.util.Set;

public interface IDelete{
	public int deleteById(String id);
	public <T> int delete(T po);
	public int delete(String id);
	public  <T> int delete(T po,Set<String> filterField);
	public  <T> int delete(T po,String ...field);
	public int deleteAll();
}


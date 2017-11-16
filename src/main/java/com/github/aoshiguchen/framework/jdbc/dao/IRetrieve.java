package com.github.aoshiguchen.framework.jdbc.dao;

import java.util.List;
import java.util.Set;

public interface IRetrieve{
	public Long getRecordCount();
	
	public <T> List<T> findAll();	
	public <T> T find(String id);
	public <T> T findById(String id);
	public <T> T findSingle(String id);
	public <T> T findSingle(T po);
	public <T> T findSingle(T po,Set<String> filterField);
	public <T> T findSingle(T po,String ...field);	
	public <T> List<T> findMulti(T po);
	public <T> List<T> findMulti(T po,Set<String> filterField);
	public <T> List<T> findMulti(T po,String ...field);
	
	public <T> List<T> findPage(int beginNo,int pageSize);
	public <T> List<T> findPage(T po,int beginNo,int pageSize);
	public <T> List<T> findPage(T po,int beginNo,int pageSize,Set<String> filterField);
	public <T> List<T> findPage(T po,int beginNo,int pageSize,String ...field);
}


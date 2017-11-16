package com.github.aoshiguchen.framework.jdbc.dao;

import java.util.Set;

public interface IUpdate{
	public <T> int update(T po);
	public <T> int update(T po,Set<String> filterField);
	public <T> int update(T po,String ...field);
}


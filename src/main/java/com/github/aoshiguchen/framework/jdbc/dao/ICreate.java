package com.github.aoshiguchen.framework.jdbc.dao;

import java.util.Set;

public interface ICreate{
	public <T> T create(T po);
	public <T> T create(T po,Set<String> filterField);
	public <T> T create(T po,String ...field);
}


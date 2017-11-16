package com.github.aoshiguchen.framework.jdbc.entity;

public class DefaultNameMapping implements NameMapping{

	@Override
	public String codeToDb(String name) {

		return name;
	}

	@Override
	public String dbToCode(String name) {

		return name;
	}

}


package com.github.aoshiguchen.framework.jdbc.entity;

public class Conf {

	private String classNameMappingType;
	
	private String fieldNameMappingType;
	
	private String dbType;

	public String getClassNameMappingType() {
		return classNameMappingType;
	}

	public void setClassNameMappingType(String classNameMappingType) {
		this.classNameMappingType = classNameMappingType;
	}

	public String getFieldNameMappingType() {
		return fieldNameMappingType;
	}

	public void setFieldNameMappingType(String fieldNameMappingType) {
		this.fieldNameMappingType = fieldNameMappingType;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

}

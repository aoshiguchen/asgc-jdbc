package com.github.aoshiguchen.framework.jdbc.entity;

/**
 * 名称映射
 * @author aoshiguchen
 * @time 2017-03-12	
 */
public interface NameMapping {
	
	public interface Type{
		
		public final String DEFAULT = "default";
		public final String BIG_HUMP_UNDERLINE = "big_hump_underline";
		public final String SMALL_HUMP_UNDERLINE = "small_hump_underline";
		
	}
	
	public String codeToDb(String name);
	public String dbToCode(String name);
	
}


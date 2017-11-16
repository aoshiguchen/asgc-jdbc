package com.github.aoshiguchen.framework.jdbc.util;

public interface Consts {

	public static final char UNDERLINE = '_';
	
	public interface Properties{
		
		public interface Key{
			public final String CLASS_NAME_MAPPING_TYPE = "NameMapping.Type.Class";
			public final String FIELD_NAME_MAPPING_TYPE = "NameMapping.Type.Field";
			public final String DB_TYPE = "DB.Type";
		}
		
		public interface Val{
			public final String BIG_HUMP_UNDERLINE = "big_hump_underline";
			public final String SMALL_HUMP_UNDERLINE = "small_hump_underline";
			public final String DEFAULE = "default";
		}
		
	}
	
	public interface DB{
		
		public interface Type{
			
			public final String MYSQL = "mysql";
			public final String SQLSERVER = "sqlserver";
			public final String ORACLE = "oracle";
			
		}
		
	}
}

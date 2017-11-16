package com.github.aoshiguchen.framework.jdbc;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.aoshiguchen.framework.configure.ConfigUtil;
import com.github.aoshiguchen.framework.configure.annotation.Config;
import com.github.aoshiguchen.framework.jdbc.entity.Conf;
import com.github.aoshiguchen.framework.jdbc.entity.User;
import com.github.aoshiguchen.framework.jdbc.jdbctemplate.JdbcTemplate;
import com.github.aoshiguchen.framework.jdbc.util.CacheUtil;

public class JdbcTemplateTest {

	@Config("jdbc.url")
	private String url;
	
	@Config("jdbc.user")
	private String username;
	
	@Config("jdbc.password")
	private String password;
	
	@Config("DB.Type")
	private String dbType;
	
	@Config("NameMapping.Type.Class")
	private String clsMapType;
	
	@Config("NameMapping.Type.Field")
	private String fieldMapType;
	
	{
		ConfigUtil.inject("jdbc",this);
		
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		System.out.println(dataSource);
		
		CacheUtil.setConfig(new Conf(){
			{
				this.setDbType(dbType);
				this.setClassNameMappingType(clsMapType);
				this.setFieldNameMappingType(fieldMapType);
			}
		});
		
		CacheUtil.dataSource = dataSource;
	}
	
	@Test
	public void test1(){
		JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
		
		System.out.println(jdbcTemplate.query(User.class,"select * from user where id = ?",1));
		System.out.println(jdbcTemplate.queryForInt("select count(id) from user"));
		
		System.out.println(jdbcTemplate.queryForString("select name from user where id = ?",1));
		System.out.println(jdbcTemplate.queryForString("select name from user where id =:id",new HashMap<String,Object>(){
			{
				this.put("id","1");
			}
		}));
		
		System.out.println(jdbcTemplate.queryForMap("select * from user where id = ?","2"));
		System.out.println(jdbcTemplate.queryForList(HashMap.class,"select * from user"));
		
		System.out.println(jdbcTemplate.queryForListMap("select * from user"));
		
	}
	
	@Test
	public void test2(){
		JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
		
		System.out.println(jdbcTemplate.queryForListString("select name from user"));
		System.out.println(jdbcTemplate.queryForListInt("select count(id) from user"));
	}
}

package com.github.aoshiguchen.framework.jdbc;

import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.aoshiguchen.framework.configure.ConfigUtil;
import com.github.aoshiguchen.framework.configure.annotation.Config;
import com.github.aoshiguchen.framework.jdbc.annotation.Dao;
import com.github.aoshiguchen.framework.jdbc.dao.IUpdatable;
import com.github.aoshiguchen.framework.jdbc.dao.proxy.DefaultUpdateDao;
import com.github.aoshiguchen.framework.jdbc.entity.Conf;
import com.github.aoshiguchen.framework.jdbc.entity.User;
import com.github.aoshiguchen.framework.jdbc.util.CacheUtil;
import com.github.aoshiguchen.framework.jdbc.util.DaoUtil;

public class UpdatebleTest {
	
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
	
	@Dao(User.class)
	private IUpdatable dao;
	
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
		DaoUtil.inject(this);
	}

	@Test
	public void test1(){
		IUpdatable userDao = new DefaultUpdateDao(User.class);
		
		System.out.println(userDao.findAll());
	}
	
	@Test
	public void test2(){
		System.out.println(dao.findAll());
		System.out.println(dao.find("1"));
		User user = new User();
		user.setName("7");
		System.out.println(dao.findSingle(user, "name"));
	}
	
	@Test
	public void test3(){
		User user = new User();
		user.setName("asgc");
		user.setCode("aa");
		user.setUserType("10");
		
		User u = dao.create(user);
		System.out.println(u);
	}
	
	@Test
	public void test4(){
		User user = new User();
		user.setId("1");
		user.setName("hello");
		user.setUserType("3");
		
		System.out.println(dao.update(user,"userType"));
	}
	
	@Test
	public void test5(){
		System.out.println(dao.delete("1"));
	}
}

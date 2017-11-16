package com.github.aoshiguchen.framework.jdbc.dao.proxy;

import com.github.aoshiguchen.framework.jdbc.dao.IUpdatable;
import com.github.aoshiguchen.framework.jdbc.dao.impl.DefaultUpdateDaoImpl;

public class DefaultUpdateDao extends AbstractUpdateDao{

	public DefaultUpdateDao(IUpdatable impl) {
		super(impl);
	}
	
	public DefaultUpdateDao(Class<?> entityClass) {
		super(new DefaultUpdateDaoImpl(entityClass));
	}
	
	public DefaultUpdateDao(Class<?> entityClass,String tbName) {
		super(new DefaultUpdateDaoImpl(entityClass, tbName));
	}
}

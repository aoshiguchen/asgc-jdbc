package com.github.aoshiguchen.framework.jdbc.dao.proxy;


import java.util.List;
import java.util.Set;

import com.github.aoshiguchen.framework.jdbc.dao.IUpdatable;

public abstract class AbstractUpdateDao implements IUpdatable{
	
	protected IUpdatable impl;
	
	public AbstractUpdateDao(IUpdatable impl){
		this.impl = impl;
	}

	public int deleteById(String id) {
		return impl.deleteById(id);
	}

	public <T> int delete(T po) {
		return impl.delete(po);
	}

	public <T> int delete(T po, Set<String> filterField) {
		return impl.delete(po, filterField);
	}

	public <T> int delete(T po, String... field) {
		return impl.delete(po, field);
	}

	public <T> List<T> findAll() {
		return impl.findAll();
	}

	public <T> T find(String id) {
		return impl.find(id);
	}

	public <T> T findById(String id) {
		return impl.findById(id);
	}

	public <T> T findSingle(String id) {
		return impl.findSingle(id);
	}

	public <T> T findSingle(T po) {
		return impl.findSingle(po);
	}

	public <T> T findSingle(T po, Set<String> filterField) {
		return impl.findSingle(po, filterField);
	}

	public <T> T findSingle(T po, String... field) {
		return impl.findSingle(po, field);
	}

	public <T> List<T> findMulti(T po) {
		return impl.findMulti(po);
	}

	public <T> List<T> findMulti(T po, Set<String> filterField) {
		return impl.findMulti(po, filterField);
	}

	public <T> List<T> findMulti(T po, String... field) {
		return impl.findMulti(po, field);
	}

	public <T> T create(T po) {
		return impl.create(po);
	}

	public <T> T create(T po, Set<String> filterField) {
		return impl.create(po, filterField);
	}

	public <T> T create(T po, String... field) {
		return impl.create(po, field);
	}

	public <T> int update(T po) {
		return impl.update(po);
	}

	public <T> int update(T po, Set<String> filterField) {
		return impl.update(po, filterField);
	}

	public <T> int update(T po, String... field) {
		return impl.update(po, field);
	}

	public int deleteAll() {
		return impl.deleteAll();
	}

	public int delete(String id) {
		return impl.delete(id);
	}

	public <T> List<T> findPage(int beginNo, int pageSize) {
		return impl.findPage(beginNo, pageSize);
	}

	public <T> List<T> findPage(T po, int beginNo, int pageSize) {
		return impl.findPage(po, beginNo, pageSize);
	}

	public <T> List<T> findPage(T po, int beginNo, int pageSize,
			Set<String> filterField) {
		return impl.findPage(po, beginNo, pageSize, filterField);
	}

	public <T> List<T> findPage(T po, int beginNo, int pageSize,
			String... field) {
		return impl.findPage(po, beginNo, pageSize, field);
	}

	public Long getRecordCount() {
		return impl.getRecordCount();
	}

}

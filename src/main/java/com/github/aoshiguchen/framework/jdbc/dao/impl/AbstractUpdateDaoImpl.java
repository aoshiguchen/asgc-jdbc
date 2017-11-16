package com.github.aoshiguchen.framework.jdbc.dao.impl;


import java.util.List;
import java.util.Set;

import com.github.aoshiguchen.framework.jdbc.dao.IUpdatable;

public abstract class AbstractUpdateDaoImpl implements IUpdatable{

	public <T> List<T> findAll() {
		return null;
	}

	public <T> T find(String id) {
		return null;
	}

	public <T> T findById(String id) {
		return null;
	}

	public <T> T findSingle(String id) {
		return null;
	}

	public <T> T findSingle(T po) {
		return null;
	}

	public <T> T findSingle(T po, Set<String> filterField) {
		return null;
	}

	public <T> T findSingle(T po, String... field) {
		return null;
	}

	public <T> List<T> findMulti(T po) {
		return null;
	}

	public <T> List<T> findMulti(T po, Set<String> filterField) {
		return null;
	}

	public <T> List<T> findMulti(T po, String... field) {
		return null;
	}

	public int deleteById(String id) {
		return 0;
	}

	public <T> int delete(T po) {
		return 0;
	}

	public <T> int delete(T po, Set<String> filterField) {
		return 0;
	}

	public <T> int delete(T po, String... field) {
		return 0;
	}

	public <T> int update(T po) {
		return 0;
	}

	public <T> int update(T po, Set<String> filterField) {
		return 0;
	}

	public <T> int update(T po, String... field) {
		return 0;
	}

	public <T> T create(T po) {
		return null;
	}

	public <T> T create(T po, Set<String> filterField) {
		return null;
	}

	public <T> T create(T po, String... field) {
		return null;
	}

	public int deleteAll() {
		return 0;
	}

	public int delete(String id) {
		return 0;
	}

	public <T> List<T> findPage(int beginNo, int pageSize) {
		return null;
	}

	public <T> List<T> findPage(T po, int beginNo, int pageSize) {
		return null;
	}

	public <T> List<T> findPage(T po, int beginNo, int pageSize,
			Set<String> filterField) {
		return null;
	}

	public <T> List<T> findPage(T po, int beginNo, int pageSize,
			String... field) {
		return null;
	}
	
}

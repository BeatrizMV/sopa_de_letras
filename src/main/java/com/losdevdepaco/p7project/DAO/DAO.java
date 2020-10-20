package com.losdevdepaco.p7project.DAO;

	import java.lang.reflect.InvocationTargetException;
	import java.util.List;
	import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;

	
		public interface DAO<T> {
			public int add(T t) throws DuplicateKeyException;
			public void saveAll();
		    public T get(String id); 
		    public List<T> list();
		    public boolean loadData();

		}
	


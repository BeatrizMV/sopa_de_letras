package com.losdevdepaco.p7project.DAO;

	import java.util.List;

	
		public interface DAO<T> {
			public int add(T t) throws DuplicateEntityException;
			public void saveAll();
		    public T get(String id); 
		    public List<T> list();
		    public boolean loadData();

		}
	


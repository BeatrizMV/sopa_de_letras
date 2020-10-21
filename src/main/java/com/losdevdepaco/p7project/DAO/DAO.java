package com.losdevdepaco.p7project.dao;

	import java.util.List;

	
		public interface DAO<T> {
			public int add(T t) throws DuplicateEntityException;
			public void saveAll(); //este yo lo eliminaria porque no tiene sentido, ya que en el de arriba ya añade
		    public T get(String id); 
		    public List<T> list();
		    public boolean loadData(); // este lo quitaria porque tampoco tiene sentido ya que con listar por id y listar todos ya te cargan los datos
		    //Nos faltria eliminar y modificar

		}
	


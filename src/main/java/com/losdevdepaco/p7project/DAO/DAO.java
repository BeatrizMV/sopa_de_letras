package com.losdevdepaco.p7project.dao;

import java.util.List;

public interface DAO<T> {
	// Insertar
	public void insert(T t);

	// Borrar
	public void delete(T t);

	// Listar
	public List<T> getall();

	// Listar por id
	public T get(String id);

	// Modificar
	public void update(T t);
}
package com.losdevdepaco.p7project.DAO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import com.losdevdepaco.p7project.model.Partida;

public class PartidaDAO implements DAO<Partida>{

	// public List<Partida> partida; 
	
	@Override
	public void create(Partida t) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Partida> findById(int id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateFieldById(int field, String value, int idArchivo) throws DaoException, NoSuchMethodException,
			InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Partida> listAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}

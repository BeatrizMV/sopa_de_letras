package com.losdevdepaco.p7project.DAO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import com.losdevdepaco.p7project.model.Usuario;

public class UsuarioDAO implements DAO<Usuario>{

	
	// public List<Usuario> usuario; 
	
	@Override
	public void create(Usuario t) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Usuario> findById(int id) throws DaoException {
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
	public List<Usuario> listAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.losdevdepaco.p7project.DAO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;

import com.losdevdepaco.p7project.model.Usuario;

public class UsuarioDAO implements DAO<Usuario>{

	
	public List<Usuario> usuario;

	@Override
	public int add(Usuario t) throws DuplicateKeyException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void saveAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> list() {
		// TODO Auto-generated method stub
		return this.usuario;
	}

	@Override
	public boolean loadData() {
		// TODO Auto-generated method stub
		return false;
	} 
	

}

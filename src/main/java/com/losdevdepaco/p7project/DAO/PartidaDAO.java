package com.losdevdepaco.p7project.DAO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;

import com.losdevdepaco.p7project.model.Partida;

public class PartidaDAO implements DAO<Partida>{

	public List<Partida> partida;

	@Override
	public int add(Partida t) throws DuplicateKeyException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void saveAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Partida get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Partida> list() {
		// TODO Auto-generated method stub
		return this.partida;
	}

	@Override
	public boolean loadData() {
		// TODO Auto-generated method stub
		return false;
	} 
	
	
}

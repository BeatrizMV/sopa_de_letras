package com.losdevdepaco.p7project.DAO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;

import com.losdevdepaco.p7project.model.Palabra;

public class PalabraDAO implements DAO<Palabra>{

	public List<Palabra> palabra;

	@Override
	public int add(Palabra t) throws DuplicateKeyException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void saveAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Palabra get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Palabra> list() {
		// TODO Auto-generated method stub
		return this.palabra;
	}

	@Override
	public boolean loadData() {
		// TODO Auto-generated method stub
		return false;
	
	}
	
}

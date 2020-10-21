package com.losdevdepaco.p7project.dao;

import java.util.List;

import com.losdevdepaco.p7project.model.Palabra;

public class PalabraDAO implements DAO<Palabra>{

	public List<Palabra> palabra;

	@Override
	public int add(Palabra t) throws DuplicateEntityException {
		return 0;
	}

	@Override
	public void saveAll() {
		
	}

	@Override
	public Palabra get(String id) {
		return null;
	}

	@Override
	public List<Palabra> list() {
		return this.palabra;
	}

	@Override
	public boolean loadData() {
		return false;
	
	}
	
}

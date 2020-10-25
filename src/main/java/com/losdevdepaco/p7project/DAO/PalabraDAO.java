package com.losdevdepaco.p7project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.losdevdepaco.p7project.db.DBconnection;
import com.losdevdepaco.p7project.model.Palabra;

public class PalabraDAO implements DAO<Palabra>{
	
	private DBconnection conexion = new DBconnection();

	//insertar una palabra
	@Override
	public void insert(Palabra t) {
		 Connection connection = null;
	        try {
	            connection = conexion.connect();
	            PreparedStatement stmt = connection.prepareStatement("insert into "+"palabra"+" ("+
	                    // campos, respetar el orden
	                    "palabra"+")"+
	                    " values (?) ");
	            // datos, respetar el orden
	            stmt.setString(1, t.getPalabra());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	           e.printStackTrace();
	        } finally {
	            conexion.disconnect();
	        }
	    }
	
	//Borrar palabra
	@Override
	public void delete(Palabra t) {
		 Connection connection = null;
	        try {
	            connection = conexion.connect();
	            PreparedStatement stmt = connection.prepareStatement("delete from " + "palabra" + " where " + "palabra" + "= ?");
	            stmt.setString(1, t.getPalabra());
	            int updated = stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            conexion.disconnect();
	        }
	    }      

	@Override
	public List<Palabra> getall() {
		return null;
	}

	@Override
	public Palabra get(String id) {
		return null;
	}

	@Override
	public void update(Palabra t) {
		
	}


	
}

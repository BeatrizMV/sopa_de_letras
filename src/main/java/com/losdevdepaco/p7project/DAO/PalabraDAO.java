package com.losdevdepaco.p7project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.losdevdepaco.p7project.db.DBconnection;
import com.losdevdepaco.p7project.model.Palabra;

public class PalabraDAO implements DAO<Palabra> {

	private DBconnection conexion = new DBconnection();

	// insertar una palabra
	@Override
	public void insert(Palabra t) {
		Connection connection = null;
		try {
			connection = conexion.connect();
			PreparedStatement stmt = connection.prepareStatement("insert into " + "palabra" + " (" +
			// campos, respetar el orden
					"palabra" + ")" + " values (?) ");
			// datos, respetar el orden
			stmt.setString(1, t.getPalabra());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.disconnect();
		}
	}

	// Borrar palabra
	@Override
	public void delete(Palabra t) {
		Connection connection = null;
		try {
			connection = conexion.connect();
			PreparedStatement stmt = connection
					.prepareStatement("delete from " + "palabra" + " where " + "palabra" + "= ?");
			stmt.setString(1, t.getPalabra());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.disconnect();
		}
	}

	//listar todas las palabras
	@Override
	public List<Palabra> getall() {
		Connection connection = null;
		List<Palabra> retList = new ArrayList<>();
		try {
			connection = conexion.connect();
			Statement stmt = connection.createStatement();
			String query = "select * from palabra";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				Palabra p = new Palabra(rs.getString(1));
				retList.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.disconnect();
		}
		
		return retList;
	}

	//listar por id la palabra
	@Override
	public Palabra get(String id) {
		Connection connection = null;
		Palabra retPalabra = null;
        try {
            connection = conexion.connect();

            PreparedStatement stmt = connection.prepareStatement("Select * from " + "palabra"+ " where "+"palabra"+ " = ? LIMIT 1");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                retPalabra = new Palabra(rs.getString(1));
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
            conexion.disconnect();
        }
        return retPalabra;
	}

	//modificar palabra
	@Override
	public void update(Palabra t) {
		Connection connection = null;
		try {
			connection = conexion.connect();
			PreparedStatement stmt = connection.prepareStatement("Update " + "palabra "  + "set " +												
					"palabra " +" = ?" +
					" where "+"palabra "+"= ?");
			stmt.setString(1, t.getPalabra());
			stmt.setString(2, t.getPalabra());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.disconnect();
		}
	}

}

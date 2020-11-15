package com.losdevdepaco.p7project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.losdevdepaco.p7project.db.DBconnection;
import com.losdevdepaco.p7project.model.Usuario;

@Repository
public class UsuarioDAO implements DAO<Usuario>{
	
	private DBconnection conexion = new DBconnection();

	@Override
	public void insert(Usuario t) {
		Connection connection = null;
		try {
			connection = conexion.connect();
			PreparedStatement stmt = connection.prepareStatement("insert into " + "usuario" + " (" +
			// campos, respetar el orden
					"nombre"+", "+"email"+")" + " values (?,?) ");
			// datos, respetar el orden
			stmt.setObject(1, t.getNombre());
			stmt.setString(2, t.getCorreo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.disconnect();
		}
	}
	
	@Override
	public void delete(Usuario t) {
		Connection connection = null;
		try {
			connection = conexion.connect();
			PreparedStatement stmt = connection
					.prepareStatement("delete from " + "usuario" + " where " + "idUsuario" + "= ?");
			stmt.setInt(1, t.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.disconnect();
		}
	}

	@Override
	public List<Usuario> getall() {
		Connection connection = null;
		List<Usuario> retList = new ArrayList<>();
		try {
			connection = conexion.connect();
			Statement stmt = connection.createStatement();
			String query = "select * from usuario";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				Usuario u = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3)); 
				retList.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.disconnect();
		}
		
		return retList;
	}

	@Override
	public Usuario get(String id) {
		Connection connection = null;
		Usuario retUsuario= null;
		
        try {
            connection = conexion.connect();

            PreparedStatement stmt = connection.prepareStatement("Select * from " + "usuario"+ " where "+"idUsuario"+ " = ? LIMIT 1");
            stmt.setInt(1, Integer.valueOf(id));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	retUsuario = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
            conexion.disconnect();
        }
        
        return retUsuario;
	}

	@Override
	public void update(Usuario t) {
		Connection connection = null;
		try {
			connection = conexion.connect();
			PreparedStatement stmt = connection.prepareStatement("Update " + "usuario "  + "set " +	
					"nombre " +" = ?, " +
					"email " +" = ? " +
					"where "+"idUsuario "+" = ?");
			
			stmt.setString(1, t.getNombre());
			stmt.setString(2, t.getCorreo());
			stmt.setInt(3, t.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.disconnect();
		}
	}
}

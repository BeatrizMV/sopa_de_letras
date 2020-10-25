package com.losdevdepaco.p7project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.losdevdepaco.p7project.db.DBconnection;
import com.losdevdepaco.p7project.model.Partida;
import com.losdevdepaco.p7project.model.Usuario;

public class PartidaDAO implements DAO<Partida>{
	
	private DBconnection conexion = new DBconnection();

	@Override
	public void insert(Partida t) {
		Connection connection = null;
		try {
			connection = conexion.connect();
			PreparedStatement stmt = connection.prepareStatement("insert into " + "partida" + " (" +
			// campos, respetar el orden
					"fecha"+", "+"puntuacion"+", "+"tiempo" + ", " + "idUsuario" + ")" + " values (?,?,?,?) ");
			// datos, respetar el orden
			stmt.setObject(1, t.getFecha());
			stmt.setInt(2, t.getPuntuacion());
			stmt.setInt(3, t.getTiempo());
			stmt.setInt(4, t.getUser().getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.disconnect();
		}
	}
	
	@Override
	public void delete(Partida t) {
		Connection connection = null;
		try {
			connection = conexion.connect();
			PreparedStatement stmt = connection
					.prepareStatement("delete from " + "partida" + " where " + "idPartida" + "= ?");
			stmt.setInt(1, t.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.disconnect();
		}
	}

	private Usuario getUsuario(int idUsuario) {
		Connection connection = null;
		Usuario user = null;
		try {
			connection = conexion.connect();
			Statement stmt = connection.createStatement();
			String query = "select * from usuario where idUsuario=" + idUsuario;
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				user = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.disconnect();
		}
		
		return user;
	}
	
	@Override
	public List<Partida> getall() {
		Connection connection = null;
		List<Partida> retList = new ArrayList<>();
		Map<Integer, Partida> resultMap = new HashMap<>();
		try {
			connection = conexion.connect();
			Statement stmt = connection.createStatement();
			String query = "select * from partida";
			ResultSet rs = stmt.executeQuery(query);
			
			
			
			while(rs.next()) {
				//Metemos un null temporalmente para luego tras obtener el usuario introducirlo
				Partida p = new Partida(rs.getInt(1), rs.getDate(2).toLocalDate(), rs.getInt(3), rs.getInt(4), null); 
				resultMap.put(rs.getInt(5), p);
				retList.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.disconnect();
		}
		
		for(Map.Entry<Integer, Partida> entry : resultMap.entrySet()) {
			Usuario u = this.getUsuario(entry.getKey());
			entry.getValue().setUser(u);
		}
		
		return retList;
	}

	@Override
	public Partida get(String id) {
		Connection connection = null;
		Partida retPartida = null;
		int userId = 0;
        try {
            connection = conexion.connect();

            PreparedStatement stmt = connection.prepareStatement("Select * from " + "partida"+ " where "+"idPartida"+ " = ? LIMIT 1");
            stmt.setInt(1, Integer.valueOf(id));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                retPartida = new Partida(rs.getInt(1), rs.getDate(2).toLocalDate(), rs.getInt(3), rs.getInt(4), null);
                userId = rs.getInt(5);
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
            conexion.disconnect();
        }
        
        Usuario u = this.getUsuario(userId);
        retPartida.setUser(u);
        return retPartida;
	}

	@Override
	public void update(Partida t) {
		Connection connection = null;
		try {
			connection = conexion.connect();
			PreparedStatement stmt = connection.prepareStatement("Update " + "partida "  + "set " +	
					"fecha " +" = ?, " +
					"puntuacion " +" = ?, " +
					"tiempo " +" = ?, " +
					"idUsuario " +" = ?" +
					" where "+"idPartida "+"= ?");
			
			stmt.setObject(1, t.getFecha());
			stmt.setInt(2, t.getPuntuacion());
			stmt.setInt(3, t.getTiempo());
			stmt.setInt(4, t.getUser().getId());
			stmt.setInt(5, t.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.disconnect();
		}
		
	}

		
}

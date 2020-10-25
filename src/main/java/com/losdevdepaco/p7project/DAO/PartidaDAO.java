package com.losdevdepaco.p7project.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import com.losdevdepaco.p7project.model.Partida;
import com.losdevdepaco.p7project.db.DBconnection;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Partida> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partida get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Partida t) {
		// TODO Auto-generated method stub
		
	}

	
	//Para insertar una partida
	/*
	@Override
	public int insert(Partida t) throws DuplicateEntityException {
		DBconnection dbc = new DBconnection();
		Connection cn = dbc.connect();
		String query = "call insertPartida(?, ?, ?, ?, @id)";
		int newId = -1;
		try {
			PreparedStatement st = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, t.getId());
			st.setObject(2, t.getFecha());
			st.setInt(3, t.getPuntuacion());
			st.setInt(4, t.getTiempo());
			
			st.executeQuery();
			Statement st1 = cn.createStatement();
			ResultSet rs = st1.executeQuery("select @id");
			if (rs.next()) {
			  newId = rs.getInt(1);
			}
			cn.close();
			loadData();
			return newId;
		}
		catch(SQLException e) {
			System.out.print("Error al insertar los datos de la partida: " + e.getMessage());
			return 0;
		}		
	}



	//Nos devuelve todas las partidas
	@Override
	public boolean loadData() {
		List <Partida> partidas = new ArrayList<Partida>();
		DBconnection dbc = new DBconnection();
		Connection cn = dbc.connect();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Partida");
			while (rs.next()) {
				Partida partida = new Partida();
				partida.setId(rs.getInt("id"));  
				partida.setFecha ((LocalDate) rs.getObject("fecha"));
				partida.setPuntuacion(rs.getInt("puntuacion"));
				partida.setTiempo(rs.getInt("tiempo"));

				partidas.add(partida);
			}
			cn.close();
			return true;
		}
		catch(SQLException e) {
			System.out.print("Error al obtener los datos de partidas: " + e.getMessage());
			return false;
		}
	} 
	*/
		
}

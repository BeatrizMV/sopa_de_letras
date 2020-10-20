package com.losdevdepaco.p7project.DAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;

import com.losdevdepaco.p7project.model.Partida;

public class PartidaDAO implements DAO<Partida>{

	public List<Partida> partida;

	@Override
	public int add(Partida t) throws DuplicateKeyException {
		//DBhelpers dbh = new DBhelpers();
		//Connection cn = dbh.connect();
		String query = "call insertPartida(?, ?, ?, ?, @id)";
		int newId = -1;
		try {
			PreparedStatement st = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, partida.getId());  //?
			//st. Localdate //????
			st.setInt(3, partida.getPuntuacion()); //?
			st.setInt(4, partida.getTiempo()); //? 
			
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
			return newId;
		}		return 0;
	}

	@Override
	public void saveAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Partida get(String id) {
		int partidaId = Integer.parseInt(id);
		return partida.stream().filter(partida -> partida.getId() == partidaId).findFirst().orElse(null);
	}

	@Override
	public List<Partida> list() {
		return this.partida;
	}

	@Override
	public boolean loadData() {
		partida = new ArrayList<Partida>();
		//DBhelpers dbh = new DBhelpers();
		//Connection cn = dbh.connect();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Partida");
			while (rs.next()) {
				Partida partida = new Partida();
				partida.setId(rs.getInt("id"));  
				partida.setFecha(rs.LocaleDate("date"));  ///??
				partida.setPuntuacion(rs.getInt("puntuacion"));
				partida.setTiempo(rs.getInt("tiempo"));

				partida.add(partida);
			}
			cn.close();
			return true;
		}
		catch(SQLException e) {
			System.out.print("Error al obtener los datos de partidas: " + e.getMessage());
			return false;
	} 
	
	
}

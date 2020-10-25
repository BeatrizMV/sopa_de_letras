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

import org.springframework.dao.DuplicateKeyException;

import com.losdevdepaco.p7project.db.DBconnection;
import com.losdevdepaco.p7project.model.Partida;
import com.losdevdepaco.p7project.model.Usuario;

public class UsuarioDAO implements DAO<Usuario>{

	@Override
	public void insert(Usuario t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Usuario t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Usuario t) {
		// TODO Auto-generated method stub
		
	}
	
	//public List<Usuario> usuario;
/*
	@Override
	public int insert(Usuario t) throws DuplicateEntityException {
		DBconnection dbc = new DBconnection();
		Connection cn = dbc.connect();
				String query = "call insertUsuario(?, ?, ?, ?, @id)";
				int newId = -1;
				try {
					PreparedStatement st = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					st.setInt(1, t.getId());  //?
					st.setString(2, t.getNombre());    //?
					st.setString(3, t.getCorreo());  //?
					
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
					System.out.print("Error al insertar los datos del usuario: " + e.getMessage());
					return 0;
				}		
	}


	@Override
	public boolean loadData() {
		List <Usuario> usuarios = new ArrayList<Usuario>();
		DBconnection dbc = new DBconnection();
		Connection cn = dbc.connect();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Usuario");
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));  
				usuario.setNombre(rs.getString("nombre"));
				usuario.setCorreo(rs.getString("correo"));
				//usuario.setPartidas(rs.get("partidas")); //Aqui hay que hacer otra query para que nos coja todas las partidas
				usuarios.add(usuario);
			}
			cn.close();
			return true;
		}
		catch(SQLException e) {
			System.out.print("Error al obtener los datos del usuario: " + e.getMessage());
			return false;
	} 
	
	} 
	*/

}

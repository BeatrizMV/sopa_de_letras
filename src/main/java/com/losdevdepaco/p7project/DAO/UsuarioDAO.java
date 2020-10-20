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
import com.losdevdepaco.p7project.model.Usuario;

public class UsuarioDAO implements DAO<Usuario>{

	
	public List<Usuario> usuario;

	@Override
	public int add(Usuario t) throws DuplicateKeyException {
		//DBhelpers dbh = new DBhelpers();
		//Connection cn = dbh.connect();
				String query = "call insertUsuario(?, ?, ?, ?, @id)";
				int newId = -1;
				try {
					PreparedStatement st = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					st.setInt(1, usuario.getId());  //?
					st.setString(2, usuario.getNombre());    //?
					st.setString(3, usuario.getCorreo());  //?
					
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
					return newId;
				}		return 0;
	}

	@Override
	public void saveAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario get(String id) {
		int usuarioId = Integer.parseInt(id);
		return usuario.stream().filter(usuario -> usuario.getId() == usuarioId).findFirst().orElse(null);
	}

	@Override
	public List<Usuario> list() {
		return this.usuario;
	}

	@Override
	public boolean loadData() {
		usuario = new ArrayList<Usuario>();
		//DBhelpers dbh = new DBhelpers();
		//Connection cn = dbh.connect();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Usuario");
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));  
				usuario.setNombre(rs.getString("nombre");
				usuario.setCorreo(rs.getString("correo"));
				usuario.setPartidas(rs.getArray("partidas")); //? get arraylist

				usuario.add(usuario);
			}
			cn.close();
			return true;
		}
		catch(SQLException e) {
			System.out.print("Error al obtener los datos del usuario: " + e.getMessage());
			return false;
	} 
	
	} 
	

}

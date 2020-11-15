package com.losdevdepaco.p7project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.losdevdepaco.p7project.controller.ldap.LdapManager;
import com.losdevdepaco.p7project.dao.UsuarioDAO;
import com.losdevdepaco.p7project.model.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private LdapManager ldapManager;
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	public boolean authenticate(String userName, String password) {
		return ldapManager.authenticate(userName, password);
	}
	
	public void createInDbIfNot(String fullUserName) {
		String convertedEmail = this.convertToEmail(fullUserName);
		List<Usuario> usuarios = usuarioDao.getall();
		if(usuarios == null || usuarios.isEmpty()) {
			System.out.println("No hay usuarios en la bbdd");
		} else {
			boolean existe = usuarios.stream().anyMatch(usu -> {
				return usu.getCorreo().equals(convertedEmail);
			});
			if(!existe) {
				usuarioDao.insert(new Usuario(fullUserName, convertedEmail));
				System.out.println("Creado usuario en db con nombre " + fullUserName
						+ " y correo " + convertedEmail);
			} else {
				System.out.println("El usuario " + fullUserName 
						+ " con correo " + convertedEmail +" existe en db");
			}
		}
	}
	
	private String convertToEmail(String fullUserName) {
		String primerBloque = fullUserName.replace(" ", "_").toLowerCase();
		return primerBloque + "@sopaletras.com";
	}
}

package com.losdevdepaco.p7project.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.losdevdepaco.p7project.controller.ldap.LdapManager;
import com.losdevdepaco.p7project.dao.PalabraDAO;
import com.losdevdepaco.p7project.dao.PartidaDAO;
import com.losdevdepaco.p7project.dao.UsuarioDAO;
import com.losdevdepaco.p7project.model.LoginData;
import com.losdevdepaco.p7project.model.Palabra;
import com.losdevdepaco.p7project.model.Partida;
import com.losdevdepaco.p7project.model.Usuario;

@Controller
public class HomeController {
	
	@Autowired
	private LdapManager ldapManager;
	
	//mostrar login.jsp
	@RequestMapping(value="/login")
	public String login(Model model) {
		
		/* UsuarioDAO dao = new UsuarioDAO();
		Usuario u = new Usuario("Marina", "marina@gmail.com");
		dao.insert(u);
		return "hola"; */
		
		/*PartidaDAO p = new PartidaDAO();
		Usuario u = new Usuario(1, "Eric", "eramos@gmail.com", new ArrayList<Partida>());
		Partida pb = new Partida(LocalDate.now(),28,1, u);
		p.insert(pb);
		return "hola";*/
		
		PalabraDAO dao = new PalabraDAO();
		//Palabra pb = new Palabra("palabros");
		//p.insert(pb);
		
		//List<Palabra> result = dao.getall();
		//if(result == null) {
		//	System.out.println("Null returned");
		//} else if(result.isEmpty()) {
		//	System.out.println("Empty result");
		//} else {
		//	for(Palabra p : result) {
		//		System.out.println(p.getPalabra());
		//	}
		//}

		dao.update(new Palabra("Patata"));
		/*if(p == null) {
			System.out.println("Null returned");
		} else {
			System.out.println("Successful return of palabra");
			System.out.println(p.getPalabra());
		}
		*/
		
		return "hola"; 
		
		//model.addAttribute("loginData", new LoginData());
		//return "login";
		
		
	}
	
	//los datos llegan aqui con el submit del formulario del login.jsp
	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	public ModelAndView authenticate(@ModelAttribute("loginData") LoginData loginData) {
		System.out.println("Received login data:" 
								+ loginData.getUserName()
								+ " "
								+ loginData.getPassword());
		boolean isAuthenticated = ldapManager.authenticate(loginData.getUserName(),	loginData.getPassword());
		ModelAndView ret = null;
		if(isAuthenticated) {
			ret = new ModelAndView("userMainScreen");
			ret.addObject("userName", loginData.getUserName());
		} else {
			ret = new ModelAndView("login");
			ret.addObject("loginData", new LoginData());
		}
		return ret;
	}
	
}

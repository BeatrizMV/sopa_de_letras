package com.losdevdepaco.p7project.controller;

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
import com.losdevdepaco.p7project.model.LoginData;
import com.losdevdepaco.p7project.model.Palabra;

@Controller
public class HomeController {
	
	@Autowired
	private LdapManager ldapManager;
	
	//mostrar login.jsp
	@RequestMapping(value="/login")
	public String login(Model model) {
		PalabraDAO p = new PalabraDAO();
		Palabra pb = new Palabra("palabros");
		p.delete(pb);
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

package com.losdevdepaco.p7project.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		model.addAttribute("loginData", new LoginData());
		return "login";
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
	
	//boton para cargar una nueva partida
	@RequestMapping(value="/new-game", method = RequestMethod.POST)
	public ModelAndView createGame() {
		System.out.println("New Game Called");
		
		PalabraDAO palabras = new PalabraDAO();
		List<Palabra> todasPalabras = palabras.getall();
		Random aleatorio = new Random();
		
		int numero = 6;
		List<Palabra> pSeleccionadas = new ArrayList<>();
		
		for (int i = 0; i < numero; i++) {
			int nAleatorio = aleatorio.nextInt(todasPalabras.size());
			Palabra p = todasPalabras.get(nAleatorio);
			pSeleccionadas.add(p);
		}
		
		ModelAndView ret = new ModelAndView("userMainScreen");
		SopaDeLetras s = new SopaDeLetras(0, 0, 'a', 0, "", new ArrayList<PosicionPalabra>());
		ret.addObject("tabla", s.getTabla());
		
		List<String> listaPalabras = Arrays.asList("aristocracia",
				"burguesia",
				"clorofila",
				"comercializacion",
				"desarrollador",
				"hambruna");
		
		ret.addObject("palabras", listaPalabras);
		
		char [][] tabla = s.getTabla();
		String htmlTabla = "<table style=\"float: left; width: 40%;\" border=\"1\">";
		
		for (int i = 0; i < 15; i++) {
			htmlTabla+="<tr>";
			for (int j = 0; j < 20; j++) {
				htmlTabla+="<td style='text-align:center' class='celda' id="+(i)+"_"+(j)+">"+tabla[i][j]+"</td>";
			}
			htmlTabla+="</tr>";
		}
		htmlTabla += "</table>";
		ret.addObject("htmlTabla", htmlTabla);
		return ret;
	}
	
}


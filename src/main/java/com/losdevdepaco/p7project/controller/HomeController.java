package com.losdevdepaco.p7project.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.losdevdepaco.p7project.controller.ldap.LdapManager;
import com.losdevdepaco.p7project.dao.PalabraDAO;
import com.losdevdepaco.p7project.dto.PalabraCheckDto;
import com.losdevdepaco.p7project.model.LoginData;
import com.losdevdepaco.p7project.model.Palabra;
import com.losdevdepaco.p7project.service.PartidaService;
import com.losdevdepaco.p7project.service.UsuarioService;
import com.losdevdepaco.p7project.utils.SopaLetrasRenderer;
import com.losdevdepaco.p7project.controller.SPPalabra;

@Controller
public class HomeController {
		
	@Autowired
	private PartidaService partidaService;
	
	@Autowired
	private UsuarioService usuarioService;

	// mostrar login.jsp
	@RequestMapping(value = "/login")
	public ModelAndView login(Model model) {
		model.addAttribute("loginData", new LoginData());
		ModelAndView ret = new ModelAndView("login");
		return ret;
	}
	

	// los datos llegan aqui con el submit del formulario del login.jsp
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ModelAndView authenticate(@ModelAttribute("loginData") LoginData loginData, 
			HttpServletResponse response) {
		System.out.println("Received login data:" + loginData.getUserName() + " " + loginData.getPassword());
		boolean isAuthenticated = usuarioService.authenticate(loginData.getUserName(), loginData.getPassword());

		ModelAndView ret = null;
		if (isAuthenticated) {
			//Creamos cookie para el usuario
			response.addCookie(new Cookie("spuser", loginData.getUserName()));
			usuarioService.createInDbIfNot(loginData.getUserName());
			ret = new ModelAndView("userMainScreen");
			
			ret = crearPartida(ret, loginData.getUserName());		
		} else {
			ret = new ModelAndView("login");
			//ret.addObject("loginData", new LoginData());
			ret.addObject("loginError", "Error al hacer login!!!");
		}
		return ret;
	}

	private ModelAndView crearPartida(ModelAndView ret, String userName) {
		SopaDeLetras s = partidaService.crearPartida(userName);		
		
		ret.addObject("tabla", s.getTabla());
		ret.addObject("palabras", s.getListaPalabras());
		ret.addObject("userName", userName);

		char[][] tabla = s.getTabla();
		String htmlTabla = SopaLetrasRenderer.renderizarTabla(tabla);
		ret.addObject("htmlTabla", htmlTabla);
		return ret;
	}

	private String getUserNameFromCookies(Cookie[] cookies) {
		if(cookies != null) {
            Cookie cookie = null;
            for (int i = 0; i < cookies.length; i++) {
               cookie = cookies[i];
               if(cookie.getName().equals("spuser")) {
            	   return cookie.getValue();
               }
            }
            System.out.println("No se ha encontrado ninguna cookie con nombre: " + "spuser");
            return "";
         } else {
            System.out.println("No hay cookies");
            return "";
         }
	}
	
	// boton para cargar una nueva partida
	@RequestMapping(value = "/new-game", method = RequestMethod.POST)
	public ModelAndView createGame(HttpServletRequest request) {
		System.out.println("New Game Called");

		String userName = getUserNameFromCookies(request.getCookies());
		
		ModelAndView ret = new ModelAndView("userMainScreen");
		
		ret = crearPartida(ret, userName);

		return ret;
	}

	// Comprobacion de la palabra en la sopa de letras
	@RequestMapping(value = "/checkWord", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public PalabraCheckDto checkWord(@RequestBody PalabraCheckDto palabra, 
			HttpServletRequest request) {
		System.out.println("Llegado a checkWord con " + palabra);
		
		String userName = getUserNameFromCookies(request.getCookies());
		
		SopaDeLetras spEnUso = partidaService.getPartidaEnCurso(userName);

		// Comprobar aqui si la palabra existe en la sopa de letras
		// Se deberia de contabilizar que palabras quedan y devolver si se ha
		// finalizado la sopa de letras o no
		String aComprobar = new String(palabra.getPalabraComprobar());
		boolean acierto = spEnUso.comprobarAcierto(aComprobar);
		if (acierto) {
			spEnUso.anadirAcierto(aComprobar);
		}
		int restantes = spEnUso.getRestantes();

		PalabraCheckDto dto = new PalabraCheckDto();
		dto.setCorrecto(acierto);
		dto.setPalabrasRestantes(restantes);
		if(acierto) {
			dto.setPalabraRecienAcertada(aComprobar.toLowerCase());
		}
		
		if(restantes == 0) {
			partidaService.finalizarPartida(userName);
			int segundosUtilizados = partidaService.calcularSegundosPartida(userName);
			dto.setSegundosUtilizados(segundosUtilizados);
			dto.setPuntos(partidaService.calcularPuntuacionPartida(userName));
		}

		System.out.println("Devolvemos respuesta: " + dto);
		return dto;
	}
	
}

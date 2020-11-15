package com.losdevdepaco.p7project.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
	public String login(Model model) {
		model.addAttribute("loginData", new LoginData());
		return "login";
	}

	// los datos llegan aqui con el submit del formulario del login.jsp
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ModelAndView authenticate(@ModelAttribute("loginData") LoginData loginData) {
		System.out.println("Received login data:" + loginData.getUserName() + " " + loginData.getPassword());
		boolean isAuthenticated = usuarioService.authenticate(loginData.getUserName(), loginData.getPassword());
		ModelAndView ret = null;
		if (isAuthenticated) {
			usuarioService.createInDbIfNot(loginData.getUserName());
			ret = new ModelAndView("userMainScreen");
			ret.addObject("userName", loginData.getUserName());
		} else {
			ret = new ModelAndView("login");
			ret.addObject("loginData", new LoginData());
		}
		return ret;
	}

	// boton para cargar una nueva partida
	@RequestMapping(value = "/new-game", method = RequestMethod.POST)
	public ModelAndView createGame() {
		System.out.println("New Game Called");

		SopaDeLetras s = partidaService.crearPartida("Oriol Vila");		
		ModelAndView ret = new ModelAndView("userMainScreen");
		ret.addObject("tabla", s.getTabla());
		ret.addObject("palabras", s.getListaPalabras());

		char[][] tabla = s.getTabla();
		String htmlTabla = SopaLetrasRenderer.renderizarTabla(tabla);
		ret.addObject("htmlTabla", htmlTabla);

		// Guardamos la sopa de letras que estamos utilizando en la variable estatica
		//spEnUso = s;

		return ret;
	}

	// Comprobacion de la palabra en la sopa de letras
	@RequestMapping(value = "/checkWord", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public PalabraCheckDto checkWord(@RequestBody PalabraCheckDto palabra) {
		System.out.println("Llegado a checkWord con " + palabra);
		
		SopaDeLetras spEnUso = partidaService.getPartidaEnCurso("Oriol Vila");

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
			partidaService.finalizarPartida("Oriol Vila");
			int segundosUtilizados = partidaService.calcularSegundosPartida("Oriol Vila");
			dto.setSegundosUtilizados(segundosUtilizados);
			dto.setPuntos(partidaService.calcularPuntuacionPartida("Oriol Vila"));
		}

		System.out.println("Devolvemos respuesta: " + dto);
		return dto;
	}
	
}

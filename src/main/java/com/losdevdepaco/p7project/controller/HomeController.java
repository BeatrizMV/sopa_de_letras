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
import com.losdevdepaco.p7project.controller.SPPalabra;

@Controller
public class HomeController {

	private static final int VALOR_BASE = 10000000; 
	
	private static SopaDeLetras spEnUso;
	private static LocalDateTime inicioPartida;

	@Autowired
	private LdapManager ldapManager;

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
		boolean isAuthenticated = ldapManager.authenticate(loginData.getUserName(), loginData.getPassword());
		ModelAndView ret = null;
		if (isAuthenticated) {
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
		inicioPartida = LocalDateTime.now();

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

		List<SPPalabra> listaPalabras = Arrays
				.asList(new SPPalabra[] { 
						new SPPalabra(0, 0, 0, "aristocracia"), 
						new SPPalabra(0, 0, 0, "burguesia"),
						new SPPalabra(0, 0, 0, "clorofila"), 
						new SPPalabra(0, 0, 0, "comercializacion"),
						new SPPalabra(0, 0, 0, "desarrollador"), 
						new SPPalabra(0, 0, 0, "hambruna") });
		
		ModelAndView ret = new ModelAndView("userMainScreen");
		SopaDeLetras s = new SopaDeLetras(0, 0, 'a', 0, "", listaPalabras);
		ret.addObject("tabla", s.getTabla());

		
		 List<String> listaPalabrasStrings = Arrays.asList("aristocracia", "burguesia",
		 "clorofila", "comercializacion", "desarrollador", "hambruna");
		

		

		ret.addObject("palabras", listaPalabrasStrings);

		char[][] tabla = s.getTabla();
		String htmlTabla = "<table id=\"sopa-letras\" class=\"sp__table\">";

		for (int i = 0; i < 15; i++) {
			htmlTabla += "<tr>";
			for (int j = 0; j < 20; j++) {
				htmlTabla += "<td class='celda' id=" + (i) + "_" + (j) + ">" + tabla[i][j]
						+ "</td>";
			}
			htmlTabla += "</tr>";
		}
		htmlTabla += "</table>";
		ret.addObject("htmlTabla", htmlTabla);

		// Guardamos la sopa de letras que estamos utilizando en la variable estatica
		spEnUso = s;

		return ret;
	}

	// Comprobacion de la palabra en la sopa de letras
	@RequestMapping(value = "/checkWord", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public PalabraCheckDto checkWord(@RequestBody PalabraCheckDto palabra) {
		System.out.println("Llegado a checkWord con " + palabra);

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
			int segundosUtilizados = calcularSegundosPartida();
			dto.setSegundosUtilizados(segundosUtilizados);
			dto.setPuntos(calcularPuntuacion(segundosUtilizados));
		}

		System.out.println("Devolvemos respuesta: " + dto);
		return dto;
	}
	
	private int calcularSegundosPartida() {
		LocalDateTime finPartida = LocalDateTime.now();
		long seconds = ChronoUnit.SECONDS.between(inicioPartida, finPartida);
		return (int) seconds;
	}
	
	private int calcularPuntuacion(int segundos) {
		return VALOR_BASE / segundos;
	}

}

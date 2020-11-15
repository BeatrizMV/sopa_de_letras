package com.losdevdepaco.p7project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.QualifiedIdentifier;
import org.springframework.stereotype.Service;

import com.losdevdepaco.p7project.controller.PartidaEnCurso;
import com.losdevdepaco.p7project.controller.SopaDeLetras;
import com.losdevdepaco.p7project.dao.PalabraDAO;
import com.losdevdepaco.p7project.dao.PartidaDAO;
import com.losdevdepaco.p7project.dao.UsuarioDAO;
import com.losdevdepaco.p7project.model.Palabra;
import com.losdevdepaco.p7project.model.Partida;
import com.losdevdepaco.p7project.model.Usuario;

@Service
public class PartidaService {
	
	private static final int VALOR_BASE = 10000000; 
	private static final int CANTIDAD_PALABRAS = 6;
	
	@Autowired
	private PalabraDAO palabraDao;
	
	@Autowired
	private PartidaDAO partidaDao;
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	private Map<String, PartidaEnCurso> partidasEnCurso = new HashMap<>();
	
	public List<Palabra> obtenerPalabrasAleatorias(){
		List<Palabra> todasPalabras = palabraDao.getall();
		Random aleatorio = new Random();

		int numero = 6;
		List<Palabra> pSeleccionadas = new ArrayList<>();

		for (int i = 0; i < numero; i++) {
			int nAleatorio = aleatorio.nextInt(todasPalabras.size());
			Palabra p = todasPalabras.get(nAleatorio);
			pSeleccionadas.add(p);
		}
		return pSeleccionadas;
	}
	
	public SopaDeLetras crearPartida(String userIdentifier) {
		List<String> palabraStrs = this.obtenerPalabrasAleatorias().stream().map(pal -> {
			return pal.getPalabra();
		}).collect(Collectors.toList());
		PartidaEnCurso partidaEnCurso = new PartidaEnCurso(palabraStrs);
		partidasEnCurso.put(userIdentifier, partidaEnCurso);
		return partidaEnCurso.getSopaDeLetrasEnUso();
	}
	
	public SopaDeLetras getPartidaEnCurso(String userIdentifier) {
		return partidasEnCurso.get(userIdentifier).getSopaDeLetrasEnUso();
	}
	
	public void finalizarPartida(String userIdentifier) {
		PartidaEnCurso p = partidasEnCurso.get(userIdentifier);
		p.finalizarPartida();
		guardarPartidaEnDb(p, userIdentifier);
	}
	
	private void guardarPartidaEnDb(PartidaEnCurso pFinalizada, String userIdentifier) {
		
		//Usuario usuario = this.obtenerUsuarioDesdeDb(userIdentifier);
		Usuario usuario = this.obtenerUsuarioDesdeDb("UPDATED EMAIL");
		if(usuario != null) {
			Partida p = new Partida(pFinalizada.getInicioPartida().toLocalDate(), 
					this.calculoPuntuacion(pFinalizada.getSegundosPartida()),
					pFinalizada.getSegundosPartida(),
					usuario
					);
			partidaDao.insert(p);
			System.out.println("Partida guardada en db");
		} 		
	}
	
	private Usuario obtenerUsuarioDesdeDb(String identificador) {
		// Usaremos el email como identificador
		List<Usuario> usuarios = this.usuarioDao.getall();
		if(usuarios == null || usuarios.isEmpty()) {
			System.out.println("Usuarios no encontrados");
			return null;
		} else {
			List<Usuario> matching = usuarios.stream().filter(usr -> {
				return usr.getCorreo().equals(identificador);
			}).collect(Collectors.toList());
			if(matching.isEmpty()) {
				System.out.println("No existe usuario que coincida en email:" + identificador);
				return null;
			} else {
				return matching.get(0);
			}
		}
	}
	
	public int calcularSegundosPartida(String userIdentifier) {
		PartidaEnCurso p = partidasEnCurso.get(userIdentifier);
		return p.getSegundosPartida();
	}
	
	public int calcularPuntuacionPartida(String userIdentifier) {
		PartidaEnCurso p = partidasEnCurso.get(userIdentifier);
		int segundos = this.calcularSegundosPartida(userIdentifier);
		if(segundos == 0) {
			return 0;
		} else {
			return this.calculoPuntuacion(segundos);
		}
	}
	
	private int calculoPuntuacion(int segundos) {
		return VALOR_BASE / segundos;
	}
}

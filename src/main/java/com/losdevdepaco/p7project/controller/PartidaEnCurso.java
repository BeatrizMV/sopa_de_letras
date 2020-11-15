package com.losdevdepaco.p7project.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class PartidaEnCurso {

	private final SopaDeLetras sopaDeLetrasEnUso;
	private final LocalDateTime inicioPartida;
	private LocalDateTime finPartida;
	
	public PartidaEnCurso(List<String> palabras) {
		this.sopaDeLetrasEnUso = new SopaDeLetras(palabras);
		this.inicioPartida = LocalDateTime.now();
	}

	public SopaDeLetras getSopaDeLetrasEnUso() {
		return sopaDeLetrasEnUso;
	}
	
	public void finalizarPartida() {
		this.finPartida = LocalDateTime.now();
	}
	
	public int getSegundosPartida() {
		if(this.finPartida == null) {
			System.out.println("Partida no finalizada aún");
			return 0;
		} else {
			long seconds = ChronoUnit.SECONDS.between(this.inicioPartida, this.finPartida);
			return (int) seconds;
		}
	}

	public LocalDateTime getInicioPartida() {
		return inicioPartida;
	}
	
	
	
}

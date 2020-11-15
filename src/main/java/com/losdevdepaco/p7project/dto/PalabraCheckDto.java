package com.losdevdepaco.p7project.dto;

import java.util.Arrays;

public class PalabraCheckDto {
	private char[] palabraComprobar;
	private boolean isCorrecto;
	private int palabrasRestantes = -1;
	private int puntos = 0;
	private int segundosUtilizados = 0;
	private String palabraRecienAcertada;
	
	public PalabraCheckDto() {
		
	}

	public char[] getPalabraComprobar() {
		return palabraComprobar;
	}

	public void setPalabraComprobar(char[] palabraComprobar) {
		this.palabraComprobar = palabraComprobar;
	}

	public boolean isCorrecto() {
		return isCorrecto;
	}

	public void setCorrecto(boolean isCorrecto) {
		this.isCorrecto = isCorrecto;
	}

	public int getPalabrasRestantes() {
		return palabrasRestantes;
	}

	public void setPalabrasRestantes(int palabrasRestantes) {
		this.palabrasRestantes = palabrasRestantes;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getSegundosUtilizados() {
		return segundosUtilizados;
	}

	public void setSegundosUtilizados(int segundosUtilizados) {
		this.segundosUtilizados = segundosUtilizados;
	}

	public String getPalabraRecienAcertada() {
		return palabraRecienAcertada;
	}

	public void setPalabraRecienAcertada(String palabraRecienAcertada) {
		this.palabraRecienAcertada = palabraRecienAcertada;
	}

	@Override
	public String toString() {
		return "PalabraCheckDto [palabraComprobar=" + Arrays.toString(palabraComprobar) + ", isCorrecto=" + isCorrecto
				+ ", palabrasRestantes=" + palabrasRestantes + ", puntos=" + puntos + ", segundosUtilizados="
				+ segundosUtilizados + ", palabraRecienAcertada=" + palabraRecienAcertada + "]";
	}

}

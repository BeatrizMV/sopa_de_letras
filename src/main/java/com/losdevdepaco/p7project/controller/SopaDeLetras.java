package com.losdevdepaco.p7project.controller;

import java.util.List;

public class SopaDeLetras {
	
	private int finlas;
	private int columnas;
	private char letras;
	private int posicionFija;
	private String caracteres;
	private List<PosicionPalabra> posiciones;
	
	public SopaDeLetras(int finlas, int columnas, char letras, int posicionFija, String caracteres,
			List<PosicionPalabra> posiciones) {
		this.finlas = finlas;
		this.columnas = columnas;
		this.letras = letras;
		this.posicionFija = posicionFija;
		this.caracteres = caracteres;
		this.posiciones = posiciones;
	}

	public int getFinlas() {
		return finlas;
	}

	public void setFinlas(int finlas) {
		this.finlas = finlas;
	}

	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

	public char getLetras() {
		return letras;
	}

	public void setLetras(char letras) {
		this.letras = letras;
	}

	public int getPosicionFija() {
		return posicionFija;
	}

	public void setPosicionFija(int posicionFija) {
		this.posicionFija = posicionFija;
	}

	public String getCaracteres() {
		return caracteres;
	}

	public void setCaracteres(String caracteres) {
		this.caracteres = caracteres;
	}

	public List<PosicionPalabra> getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(List<PosicionPalabra> posiciones) {
		this.posiciones = posiciones;
	}

	@Override
	public String toString() {
		return "SopaDeLetras [finlas=" + finlas + ", columnas=" + columnas + ", letras=" + letras + ", posicionFija="
				+ posicionFija + ", caracteres=" + caracteres + ", posiciones=" + posiciones + "]";
	}
	
	

}

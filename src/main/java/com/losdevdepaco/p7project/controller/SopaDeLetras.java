package com.losdevdepaco.p7project.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.losdevdepaco.p7project.utils.GeneradorTabla;

public class SopaDeLetras {
	
	private static final int ALTO_SP = 15;
	private static final int ANCHO_SP = 20;
	
	private int filas;
	private int columnas;
	private char letras;
	private int posicionFija;
	private String caracteres;
	private List<SPPalabra> posiciones;
	
	//TODO Esto deberia de sustituir la lista de arriba
	private List<String> palabras;
	
	private List<String> acertadas = new ArrayList<>();
	
	public SopaDeLetras(int filas, int columnas, char letras, int posicionFija, String caracteres,
			List<SPPalabra> posiciones) {
		this.filas = filas;
		this.columnas = columnas;
		this.letras = letras;
		this.posicionFija = posicionFija;
		this.caracteres = caracteres;
		this.posiciones = posiciones;
	}
	
	public SopaDeLetras(List<String> palabrasAleatorias) {
		// No tiene mucho sentido usar SPPalabra para guardar las posiciones. 
		// Podemos solo detectar que las letras coinciden
		// con nuestras palabras, ya que podrian aparecer en varias partes de la tabla
		// Guardar como SPPalabra de momento
		
		//Usando las palabras predefinidas de momento
		 List<String> listaPalabrasStrings = Arrays.asList("aristocracia", "burguesia",
		 "clorofila", "comercializacion", "desarrollador", "hambruna");
		 this.posiciones = listaPalabrasStrings.stream().map(str -> {
				return new SPPalabra(str);
			}).collect(Collectors.toList());
		
		 //Utilizar esto cuando se vaya a autogenerar la sopa de letras
		 this.posiciones = palabrasAleatorias.stream().map(str -> {
			return new SPPalabra(str);
		}).collect(Collectors.toList()); 
		 
		 this.palabras = palabrasAleatorias;
	}

	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
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

	public List<SPPalabra> getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(List<SPPalabra> posiciones) {
		this.posiciones = posiciones;
	}
	
	public char [][] getTabla(){
						   //1,	2, 	3,	4,	5	 6	 7	  8	  9	 10  11	 12  13  14  15  16  17  18  19  20		
		/* char [][] tabla = {{'S','D','X','A','O','D','A','D','O','R','M','G','L','Z','K','N','O','A','O','Z'}, 
						   {'O','A','C','O','M','E','R','C','I','A','L','I','Z','A','C','I','O','N','E','Q'}, 
						   {'V','R','T','D','D','S','I','Z','E','H','K','C','L','A','A','O','F','O','L','L'}, 
						   {'I','I','M','I','Q','A','L','Z','K','B','B','D','E','J','I','N','I','K','O','A'}, 
						   {'P','S','A','N','O','R','O','Z','K','F','O','N','D','W','S','J','E','J','X','N'}, 
						   {'A','T','N','E','J','R','H','P','A','Z','A','B','B','D','E','J','X','N','I','K'}, 
						   {'R','O','F','R','A','O','M','D','P','H','A','M','B','R','U','N','A','E','J','X'}, 
						   {'O','C','A','I','G','L','N','C','A','K','R','M','P','R','G','D','K','N','L','B'}, 
						   {'M','R','N','L','E','L','X','N','I','K','O','A','O','Z','R','F','O','N','D','W'}, 
						   {'H','A','O','L','R','A','L','I','F','O','R','O','L','C','U','K','O','A','O','Z'}, 
						   {'L','C','S','O','F','D','L','L','D','W','H','J','P','R','B','D','K','N','L','B'},
						   {'L','I','S','O','F','O','L','L','D','W','H','J','D','O','R','M','G','L','Z','K'},
						   {'L','A','S','O','F','R','L','L','D','W','H','J','L','A','S','O','F','O','L','L'},
						   {'L','A','S','O','F','O','L','L','D','W','H','J','E','J','X','N','I','K','O','A'},
						   {'L','A','S','O','F','O','L','L','D','W','H','J','N','L','Z','K','B','B','D','E'},
						   {'O','R','E','N','A','R','F','E','R','K','N','L','B','D','E','J','X','N','I','K'}};
		*/
		char[][] tabla = GeneradorTabla.crearTabla(ALTO_SP, ANCHO_SP, this.palabras);
		GeneradorTabla.imprimirTabla(ALTO_SP, ANCHO_SP, tabla);
		return tabla;
	}
	
	public List<String> getListaPalabras(){
		//return this.posiciones.stream().map(spPalabra -> {
		//	return spPalabra.getContenido();
		//}).collect(Collectors.toList());
		return this.palabras;
	}
	
	public boolean comprobarAcierto(String palabraAComprobar) {
		return this.posiciones.stream().anyMatch(pal -> {
			return pal.getContenido().toLowerCase().equals(palabraAComprobar.toLowerCase());
		});
	}
	
	public void anadirAcierto(String acertado) {
		this.acertadas.add(acertado);
	}
	
	public int getRestantes() {
		return this.posiciones.size() - this.acertadas.size();
	}

	@Override
	public String toString() {
		return "SopaDeLetras [filas=" + filas + ", columnas=" + columnas + ", letras=" + letras + ", posicionFija="
				+ posicionFija + ", caracteres=" + caracteres + ", posiciones=" + posiciones + "]";
	}
	
	

}

package com.losdevdepaco.p7project.controller;

public class SPPalabra {
	
	private int posX = 0;
	private int posY = 0;
	private int forma = 0;
	private String contenido;
	
	public SPPalabra(int posX, int posY, int forma, String contenido) {
		this.posX = posX;
		this.posY = posY;
		this.forma = forma;
		this.contenido = contenido;
	}
	
	public SPPalabra (String contenido) {
		this.contenido = contenido;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getForma() {
		return forma;
	}

	public void setForma(int forma) {
		this.forma = forma;
	}
	
	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	@Override
	public String toString() {
		return "Palabra [posX=" + posX + ", posY=" + posY + ", forma=" + forma + ", contenido=" + contenido + "]";
	}
		
}

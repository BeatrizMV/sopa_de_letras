package com.losdevdepaco.p7project.controller;

public class PosicionPalabra {

	private int pos;
	private Palabra palabra;
	private int xx;
	private int yy;
	
	public PosicionPalabra(int pos, Palabra palabra, int xx, int yy) {;
		this.pos = pos;
		this.palabra = palabra;
		this.xx = xx;
		this.yy = yy;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public Palabra getPalabra() {
		return palabra;
	}

	public void setPalabra(Palabra palabra) {
		this.palabra = palabra;
	}

	public int getXx() {
		return xx;
	}

	public void setXx(int xx) {
		this.xx = xx;
	}

	public int getYy() {
		return yy;
	}

	public void setYy(int yy) {
		this.yy = yy;
	}

	@Override
	public String toString() {
		return "PosicionPalabra [pos=" + pos + ", palabra=" + palabra + ", xx=" + xx + ", yy=" + yy + "]";
	}
	
	
}

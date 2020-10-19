package com.losdevdepaco.p7project.controller;

public class Palabra {
	
	private int posX;
	private int posY;
	private int forma;
	
	public Palabra(int posX, int posY, int forma) {
		this.posX = posX;
		this.posY = posY;
		this.forma = forma;
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

	@Override
	public String toString() {
		return "Palabra [posX=" + posX + ", posY=" + posY + ", forma=" + forma + "]";
	}
		
}

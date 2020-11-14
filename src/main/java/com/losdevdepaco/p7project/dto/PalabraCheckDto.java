package com.losdevdepaco.p7project.dto;

import java.util.Arrays;

public class PalabraCheckDto {
	private char[] palabraComprobar;
	private boolean isCorrecto;
	private int palabrasRestantes = -1;
	
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

	@Override
	public String toString() {
		return "PalabraCheckDto [palabraComprobar=" + Arrays.toString(palabraComprobar) + ", isCorrecto=" + isCorrecto
				+ ", palabrasRestantes=" + palabrasRestantes + "]";
	}
}

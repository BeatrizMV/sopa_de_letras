package com.losdevdepaco.p7project.model;

import java.util.List;

public class Usuario {
	
	private int id;
	private String nombre;
	private String correo;	
	private List<Partida> partidas;
	
	public Usuario(int id, String nombre, String correo, List<Partida> partidas) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.partidas = partidas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public List<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", partidas=" + partidas + "]";
	}
	
}

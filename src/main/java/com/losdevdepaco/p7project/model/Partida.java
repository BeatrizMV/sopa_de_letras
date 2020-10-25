package com.losdevdepaco.p7project.model;

import java.time.LocalDate;

public class Partida {
	
	private int id;
	private LocalDate fecha;
	private int puntuacion;
	private int tiempo;
	private Usuario user;
	
	public Partida(int id, LocalDate fecha, int puntuacion, int tiempo, Usuario user) {
		this.id = id;
		this.fecha = fecha;
		this.puntuacion = puntuacion;
		this.tiempo = tiempo;
		this.user = user;
	}
	
	public Partida(LocalDate fecha, int puntuacion, int tiempo, Usuario user) {
		this.fecha = fecha;
		this.puntuacion = puntuacion;
		this.tiempo = tiempo;
		this.user = user;
	}

	public Partida() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	@Override
	public String toString() {
		return "Partida [id=" + id + ", fecha=" + fecha + ", puntuacion=" + puntuacion + ", tiempo=" + tiempo + "]";
	}
	
}

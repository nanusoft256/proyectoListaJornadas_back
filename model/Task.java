package com.julio.shedulator.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {
	//lo que vamos a devolver
	@JsonProperty("hrs_jornada")
	private Integer hrsJornada;
	
	@JsonProperty("dias_termino")
	private Integer diasTermino;
	
	@JsonProperty("jornadas")
	private List <Jornada> jornadas;

	public Integer getHrsJornada() {
		return hrsJornada;
	}

	public void setHrsJornada(Integer hrsJornada) {
		this.hrsJornada = hrsJornada;
	}

	public Integer getDiasTermino() {
		return diasTermino;
	}

	public void setDiasTermino(Integer diasTermino) {
		this.diasTermino = diasTermino;
	}

	public List<Jornada> getJornadas() {
		return jornadas;
	}

	public void setJornadas(List<Jornada> jornadas) {
		this.jornadas = jornadas;
	}
	
}

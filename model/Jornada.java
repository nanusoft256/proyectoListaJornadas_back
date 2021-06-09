package com.julio.shedulator.model;

import java.util.List;

public class Jornada {
	
	private Integer id;
	
	private List<String> tareas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getTareas() {
		return tareas;
	}

	public void setTareas(List<String> tareas) {
		this.tareas = tareas;
	}
	
	
}

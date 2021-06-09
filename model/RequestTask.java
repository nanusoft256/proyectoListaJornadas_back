package com.julio.shedulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestTask {
	
	@JsonProperty("task_id")
	private String id;
	
	@JsonProperty("task_name")
	private String name;
	
	@JsonProperty("duration")
	private Integer duration;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	
}

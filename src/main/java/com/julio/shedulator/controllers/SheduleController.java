package com.julio.shedulator.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.julio.shedulator.model.Jornada;
import com.julio.shedulator.model.RequestTask;
import com.julio.shedulator.model.Task;

@RestController
public class SheduleController {

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/task")
	public @ResponseBody Task createTaskShedule(@RequestBody List<RequestTask> request) {

		int horasJornada = 8;
		double suma = 0;
		int diasTermino = 0;
		int duracion;
		int id = 0;

		List<Jornada> jornadas = new ArrayList<>();
		List<String> tareas;
		List<RequestTask> tareasOrdenadas = request.stream()
                .sorted(Comparator.comparingInt(RequestTask::getDuration).reversed())
                .collect(Collectors.toList());

		double sum = tareasOrdenadas.stream().mapToDouble(RequestTask::getDuration).sum();
		diasTermino = (int) Math.ceil(sum / horasJornada);

		int indiceTareas = 0;
		int tamTareas = tareasOrdenadas.size();
		for (int i=0; i<diasTermino; i++) {
			id+=1;
			Jornada jornada = new Jornada();
			tareas = new ArrayList<>();
			duracion = tareasOrdenadas.get(indiceTareas).getDuration();
			while((suma + duracion)<=8 && indiceTareas < tamTareas) {
				suma += duracion;
				jornada.setId(id);
				tareas.add(tareasOrdenadas.get(indiceTareas).getId());
				jornada.setTareas(tareas);
				indiceTareas ++;
			}

			jornadas.add(jornada);
			suma=0;
		}

		Task task = new Task();
		task.setHrsJornada(horasJornada);
		task.setDiasTermino(diasTermino);
		task.setJornadas(jornadas);

		return task;
	}
}
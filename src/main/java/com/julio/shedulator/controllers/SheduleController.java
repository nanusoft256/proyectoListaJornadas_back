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

import com.julio.shedulator.model.Task;
import com.julio.shedulator.model.Jornada;
import com.julio.shedulator.model.RequestTask;

@RestController
public class SheduleController {
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/task")
	public @ResponseBody Task createTaskShedule(@RequestBody List<RequestTask> request) {
		
		int horasJornada = 8;
		
		List<RequestTask> tareasOrdenadas = request.stream()
                .sorted(Comparator.comparingInt(RequestTask::getDuration).reversed())
                .collect(Collectors.toList());	
	    
	    return agruparTareasPorJornada(horasJornada, tareasOrdenadas);
	    
	}
	
	private static Task agruparTareasPorJornada(int horasJornada, List<RequestTask> requestTask) {
	    
	    ArrayList<ArrayList<Integer>> groups = new ArrayList<>();
	    ArrayList<ArrayList<String>> listaIDs = new ArrayList<>();
	    Task tareasPorJornada = new Task();
	    List<Jornada> jornadas = new ArrayList<>();
	    Jornada jornada = new Jornada();
	    List<String> tareas = new ArrayList<>();;
	    ArrayList<Integer> sums = new ArrayList<>();
	    
	    groups.add(new ArrayList<>());
	    listaIDs.add(new ArrayList<>());
	    int id = 1;
	    int diasTermino = 0;
	    jornada.setId(id);
	    jornada.setTareas(tareas);
	    jornadas.add(jornada);
	    sums.add(0);
	    
	    while (!requestTask.isEmpty()) {
	        int n = requestTask.get(0).getDuration(); 
	        String l = requestTask.get(0).getId(); 
	        if (n > horasJornada) {
	            String mensaje = "Duracion de jornada es superior a la cantida de horas por dia";
	            throw new IllegalArgumentException(mensaje);
	        }
	        boolean match = false;
    	        for (int i = 0; i < sums.size(); i++) {
 	            if (sums.get(i) + n <= horasJornada) {
	                sums.set(i, sums.get(i) + n);
	                groups.get(i).add(n);
	                listaIDs.get(i).add(l);
	                jornadas.get(i).getTareas().add(l);
	                match = true;
	                break;
	            }
	        }
 	        if (!match) {
	            ArrayList<Integer> e = new ArrayList<>();
	            ArrayList<String> f = new ArrayList<>();
	            tareas = new ArrayList<>();
	            Jornada j = new Jornada();
	            id +=1;
	            j.setId(id);
	            tareas.add(l);
	    	    j.setTareas(tareas);
	    	    jornadas.add(j);
	            e.add(n);
	            f.add(l);
	            groups.add(e);
	            listaIDs.add(f);
	            sums.add(n);
	        }
 	       requestTask.remove(0);
	    }
	    diasTermino = groups.size();
	    tareasPorJornada.setHrsJornada(horasJornada);
	    tareasPorJornada.setDiasTermino(diasTermino);
	    tareasPorJornada.setJornadas(jornadas);
	  
 	    return tareasPorJornada;
	}
}

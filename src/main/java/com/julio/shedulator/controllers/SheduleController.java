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
import com.julio.shedulator.model.RequestTask;

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
		
		List<RequestTask> tareasOrdenadas = request.stream()
                .sorted(Comparator.comparingInt(RequestTask::getDuration).reversed())
                .collect(Collectors.toList());
		
		ArrayList<ArrayList<String>> a = grouping(horasJornada, tareasOrdenadas);
	    System.out.println(a.get(2).get(0));
			

	    Task task = new Task();
	    task.setHrsJornada(horasJornada);
	    task.setDiasTermino(diasTermino);
		return task;
	}
	
	private static ArrayList<ArrayList<String>> grouping(int limit, List<RequestTask> input) {
	    // Sort the input array.
	    //Collections.sort(input, Collections.reverseOrder());
	    // Copy the int[] to an ArrayList<Integer>
	    //ArrayList<Integer> input = new ArrayList<>(Arrays.asList(tareasOrdenadas));

	    // Initialize the groups
	    ArrayList<ArrayList<Integer>> groups = new ArrayList<>();
	    ArrayList<ArrayList<String>> listaIDs = new ArrayList<>();
	    groups.add(new ArrayList<>());
	    listaIDs.add(new ArrayList<>());
	    // Initialize the sums of the groups, to increase performance (I guess).
	    ArrayList<Integer> sums = new ArrayList<>();
	    sums.add(0);

	    // Iterate through the input array until there is no number
	    // left in it (that means we just added all the numbers
	    // into our groups array).
	    while (!input.isEmpty()) {
	        int n = input.get(0).getDuration(); // Store the number to 'n', to shortcut.
	        String l = input.get(0).getId(); // Store the number to 'n', to shortcut.
	        if (n > limit) {
	            String mensaje = "Duracion de jornada es superior a la cantida de horas por dia";
	            throw new IllegalArgumentException(mensaje);
	        }
	        boolean match = false;
	        // Search the next groups and check if our current
	        // number ('n') fits.
	        for (int i = 0; i < sums.size(); i++) {
	            if (sums.get(i) + n <= limit) {
	                // If it fits, then add the number to the group.
	                sums.set(i, sums.get(i) + n);
	                groups.get(i).add(n);
	                listaIDs.get(i).add(l);
	                match = true;
	                break;
	            }
	        }
	        // If 'n' doesn't fit in any group, create a new one.
 	        if (!match) {
	            ArrayList<Integer> e = new ArrayList<>();
	            ArrayList<String> f = new ArrayList<>();
	            e.add(n);
	            f.add(l);
	            groups.add(e);
	            listaIDs.add(f);
	            sums.add(n);
	        }
	        // Remove our number.
	        input.remove(0);
	    }
	    System.out.println(groups.size());
 	    return listaIDs;
	}
}

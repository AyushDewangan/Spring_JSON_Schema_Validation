package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	/*
	 * http://localhost:8080/task	(POST)
	 * Add reponse entity
	 * */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Task createTask(@RequestBody Task task) {
		return taskService.addTask(task);
	}
	
	/*
	 * http://localhost:8080/task	(GET)
	 * Add reponse entity
	 * */
	@GetMapping										
	@ResponseStatus(HttpStatus.FOUND)
	public List<Task> findAllTask(){
		return taskService.findAllTask();
	}
	
	/*
	 * http://localhost:8080/task/f0d2714a	(POST)
	 * Add reponse entity
	 * */
	@PostMapping("/{taskId}")
	@ResponseStatus(HttpStatus.FOUND)
	public Task findById(@PathVariable String taskId) {
		return taskService.findById(taskId);
	}
	
	/*
	 * http://localhost:8080/task/severity/1		(GET)
	 * Add reponse entity
	 * */
	@GetMapping("/severity/{severity}")
	@ResponseStatus(HttpStatus.FOUND)
	public List<Task> findBySeverity(@PathVariable int severity) {
		return taskService.findBySeverity(severity);
	}
	
	/*
	 * http://localhost:8080/task/assignee/Chandrakar	(GET)
	 * Add reponse entity
	 * */
	@GetMapping("/assignee/{assignee}")
	@ResponseStatus(HttpStatus.FOUND)
	public List<Task> findByAssignee(@PathVariable String assignee) {
		return taskService.findByAssignee(assignee);
	}
	
	/*
	 * http://localhost:8080/task  (PUT)
	 * Add reponse entity
	 * */
	@PutMapping	
	@ResponseStatus(HttpStatus.OK)
	public Task updateTask(@RequestBody Task taskRequest) {

		return taskService.updateTask(taskRequest);
	}
	
	/*
	 * http://localhost:8080/task/e8985dbb (DELETE)
	 * Add reponse entity
	 * */
	@DeleteMapping("/{taskId}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteTask(@PathVariable String taskId) {
		
		return taskService.deleteTask(taskId);
	}
	
	
	
	
	
	
	
	
	
}
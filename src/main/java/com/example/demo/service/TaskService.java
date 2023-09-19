package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.JsonValidator.JsonValidation;
import com.example.demo.Repository.TaskRepository;
import com.example.demo.model.Task;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	// CRUD

	public Task addTask(Task task) {
		System.out.println("enter in addTask method");
		task.setTaskId(UUID.randomUUID().toString().split("-")[0]);

		// Convert the 'createdTask' object back to JSON
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String taskJson = objectMapper.writeValueAsString(task);
			System.out.println("Request JSON: " + taskJson);

			JsonValidation jv = new JsonValidation();
			jv.checkValidation(taskJson);
		} catch (Exception e) {
			System.out.println("Exception occured  : " + e);
		}

		return taskRepository.save(task);
	}

	public List<Task> findAllTask() {
		return taskRepository.findAll();
	}

	public Task findById(String taskId) {
		return taskRepository.findById(taskId).get();
	}

	public List<Task> findBySeverity(int severity) {
		return taskRepository.findBySeverity(severity);
	}

	// Try to write custom query
	public List<Task> findByAssignee(String assignee) {
		return taskRepository.findByAssignee(assignee);
	}

	public Task updateTask(Task taskRequest) {
		// get the exist data from db then update that
		Task existingTask = taskRepository.findById(taskRequest.getTaskId()).get();
		existingTask.setAssignee(taskRequest.getAssignee());
		existingTask.setDescription(taskRequest.getDescription());
		existingTask.setStoryPoint(taskRequest.getStoryPoint());
		existingTask.setSeverity(taskRequest.getSeverity());
		return taskRepository.save(existingTask);
	}

	public String deleteTask(String taskId) {
		taskRepository.deleteById(taskId);
		return "Task Id : " + taskId;
	}

}
package com.bdi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bdi.exception.DataNotFoundException;
import com.bdi.model.Task;
import com.bdi.repository.TaskRepository;

@Service
public class TaskService {
	
	private String str = "Data is not found";

	@Autowired
	TaskRepository taskRepository;
	
	 public boolean isIdAbsent(Long id) {
	        return !taskRepository.existsById(id);
	 }
	
	public Task createTask(Task task) {
		return this.taskRepository.save(task);
	}
	
	public Task getTaskById(Long id) throws DataNotFoundException {
		if(id==null) {
			throw new DataNotFoundException("please Enter id");
		}
		Task tasks =  this.taskRepository.findById(id).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,str);
		});
		return tasks;
	}
	
	public List<Task> getAllTask(){
		return this.taskRepository.findAll();
	}
	
	public Task updateTaskById(Task task,Long id) throws DataNotFoundException {
		if(id==null) {
			throw new DataNotFoundException("please Enter id");
		}
		Task tasks = this.taskRepository.findById(id).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,str);
		});
		return tasks;
		
	}
	
	public void deleteTaskById(Long id) throws DataNotFoundException {
		if(id==null) {
			throw new DataNotFoundException("please Enter id");
		}
		Task tasks = this.taskRepository.findById(id).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,str);
		});
		this.taskRepository.deleteById(tasks.getTaskId());
	}
	
}

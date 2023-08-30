package com.bdi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdi.exception.DataNotFoundException;
import com.bdi.model.Task;
import com.bdi.service.TaskService;
import com.bdi.wrapper.ResponseWrapper;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createTask(@RequestBody Task task){
		ResponseWrapper responseWrapper = new ResponseWrapper();
		responseWrapper.setMessage("Data is created Successfully..");
		responseWrapper.setData(this.taskService.createTask(task));
		return new ResponseEntity<>(responseWrapper,HttpStatus.OK);
	}
	
	@GetMapping("/getTaskById/{id}")
	public ResponseEntity<?> getTaskById(@PathVariable Long id) throws DataNotFoundException{
		boolean isAbsent = taskService.isIdAbsent(id);
		if(isAbsent) {
			return ResponseEntity.ok("id is not present in the database");
		}else {
			ResponseWrapper responseWrapper = new ResponseWrapper();
			responseWrapper.setMessage("Data is get successfully..");
			responseWrapper.setData(this.taskService.getTaskById(id));
			return new ResponseEntity<>(responseWrapper,HttpStatus.OK);
		}
	}
	
	@GetMapping("/getAllTasks")
	public ResponseEntity<?> getAllTasks(){
		ResponseWrapper responseWrapper = new ResponseWrapper();
		responseWrapper.setMessage("All Task retrived successfully..");
		responseWrapper.setData(this.taskService.getAllTask());
		return new ResponseEntity<>(responseWrapper,HttpStatus.OK);
	}
	
	@PutMapping("/updateTaskById/{id}")
	public ResponseEntity<?> updateTaskById(@RequestBody Task task, @PathVariable Long id) throws DataNotFoundException{
		boolean isAbsent = taskService.isIdAbsent(id);
		if(isAbsent) {
			return ResponseEntity.ok("id is not present in the database");
		}else {
			ResponseWrapper responseWrapper = new ResponseWrapper();
			responseWrapper.setMessage("Task is updated Successfully.");
			responseWrapper.setData(this.taskService.updateTaskById(task, id));
			return new ResponseEntity<>(responseWrapper,HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/deleteTaskById/{id}")
	public ResponseEntity<?> deleteTaskById(@PathVariable Long id) throws DataNotFoundException{
		boolean isAbsent = taskService.isIdAbsent(id);
		if(isAbsent) {
			return ResponseEntity.ok("id is not present in the database");
		}else {
			ResponseWrapper responseWrapper = new ResponseWrapper();
			responseWrapper.setMessage("Task is deleted Successfully");
			this.taskService.deleteTaskById(id);
			responseWrapper.setData("Done...");
			return new ResponseEntity<>(responseWrapper,HttpStatus.OK);
		}
		
		
		
	}
	
	
}

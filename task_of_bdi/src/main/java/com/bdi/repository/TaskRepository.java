package com.bdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bdi.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{


}

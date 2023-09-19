package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.example.demo.model.Task;

//@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

	List<Task> findBySeverity(int severity);

//	@Query("assignee: ?0, otherFieldName : ?1")		// field name and ?0 first parameter, ?1 second parameter
//	@Query("assignee: ?0")
	List<Task> findByAssignee(String assignee);

}
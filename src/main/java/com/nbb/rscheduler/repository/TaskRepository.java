package com.nbb.rscheduler.repository;

import java.util.Set;

import com.nbb.rscheduler.entity.Task;

public interface TaskRepository {

	public Set<Task> getTopTasks();

}

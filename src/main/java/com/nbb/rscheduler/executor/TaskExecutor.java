package com.nbb.rscheduler.executor;

import java.util.Set;

import com.nbb.rscheduler.entity.Execution;
import com.nbb.rscheduler.entity.Task;

public interface TaskExecutor {

	public void executeTask(Set<Task> todo, Execution exe);

}

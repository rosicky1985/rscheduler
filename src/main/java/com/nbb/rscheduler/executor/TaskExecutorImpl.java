package com.nbb.rscheduler.executor;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nbb.rscheduler.entity.Execution;
import com.nbb.rscheduler.entity.Task;

public class TaskExecutorImpl implements TaskExecutor {
	private Integer threadSize = 10;
	private ExecutorService executorService;
	private TaskRunner taskRunner;

	@Override
	public void executeTask(Set<Task> todo, Execution exe) {
		if (executorService == null)
			executorService = Executors.newFixedThreadPool(threadSize);
		executorService
				.execute(taskRunner.runTask(todo.iterator().next(), exe));
	}

}

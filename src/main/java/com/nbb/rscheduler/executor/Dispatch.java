package com.nbb.rscheduler.executor;

import java.util.HashSet;
import java.util.Set;

import com.nbb.rscheduler.entity.Execution;
import com.nbb.rscheduler.entity.Message;
import com.nbb.rscheduler.entity.Task;
import com.nbb.rscheduler.repository.ExecutionRepository;
import com.nbb.rscheduler.repository.MessageRepository;
import com.nbb.rscheduler.repository.TaskRepository;

public class Dispatch extends Thread {
	private MessageRepository messageRepository;
	private ExecutionRepository executionRepository;
	private TaskRepository taskRepository;
	private TaskExecutor taskExetutor;
	private long dispatchInterval;
	;


	@Override
	public void run() {
		Set<Task> topTasks = taskRepository.getTopTasks();
		while (true) {
			Set<Execution> unresolvedExecutions = executionRepository
					.getUnresolveExecution();
			if (unresolvedExecutions == null
					|| unresolvedExecutions.size() == 0) {
				try {
					Thread.sleep(dispatchInterval);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
				Set<Task> todo = new HashSet<Task>();
				for (Execution exe : unresolvedExecutions) {
					if (exe != null) {
						Set<Message> messages = messageRepository
								.getMessages(exe);
						if (topTasks != null && topTasks.size() != 0) {
							for (Task task : topTasks) {
								findTodo(todo, task, messages);
							}
						}
					}
					if (todo != null && todo.size() != 0) {
						taskExetutor.executeTask(todo, exe);
					}
				}

			}
		}
	}

	private void findTodo(Set<Task> todo, Task task, Set<Message> messages) {
		if (task != null) {
			Message taskmessage = findMessageByTask(task, messages);
			if (taskmessage == null && task.getRequired() != null
					&& task.getRequired().size() != 0) {
				boolean allSubReady = true;
				for (Task sub : task.getRequired()) {
					if (sub != null) {
						if (findMessageByTask(task, messages) == null) {
							allSubReady = false;
							break;
						}
					}
				}
				if (allSubReady) {
					todo.add(task);
				}
			}
		}
		if (task.getRequired() != null) {
			for (Task sub : task.getRequired()) {
				findTodo(todo, sub, messages);
			}
		}
	}

	private Message findMessageByTask(Task task, Set<Message> messages) {
		// TODO Auto-generated method stub
		return null;
	}
}

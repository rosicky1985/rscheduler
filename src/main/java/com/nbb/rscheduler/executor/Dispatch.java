package com.nbb.rscheduler.executor;

import java.util.HashSet;
import java.util.Iterator;
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

	@Override
	public void run() {
		while (true) {
			Set<Execution> unresolvedExecutions = executionRepository
					.getUnresolveExecution();
			if (unresolvedExecutions == null
					|| unresolvedExecutions.size() == 0) {
				try {
					Thread.sleep(dispatchInterval);
				} catch (InterruptedException e) {
					e.printStackTrace();
					continue;
				}
				Set<Task> todo = new HashSet<Task>();
				for (Execution exe : unresolvedExecutions) {
					if (exe != null) {
						Set<Message> messages = messageRepository
								.getMessages(exe);
						findTodoByExecution(todo, messages);
					}
					if (todo != null && todo.size() != 0) {
						taskExetutor.executeTask(todo, exe);
					}
				}

			}
		}
	}

	public MessageRepository getMessageRepository() {
		return messageRepository;
	}

	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public ExecutionRepository getExecutionRepository() {
		return executionRepository;
	}

	public void setExecutionRepository(ExecutionRepository executionRepository) {
		this.executionRepository = executionRepository;
	}

	public TaskRepository getTaskRepository() {
		return taskRepository;
	}

	public void setTaskRepository(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public TaskExecutor getTaskExetutor() {
		return taskExetutor;
	}

	public void setTaskExetutor(TaskExecutor taskExetutor) {
		this.taskExetutor = taskExetutor;
	}

	public long getDispatchInterval() {
		return dispatchInterval;
	}

	public void setDispatchInterval(long dispatchInterval) {
		this.dispatchInterval = dispatchInterval;
	}

	public void findTodoByExecution(Set<Task> todo, Set<Message> messages) {
		if (taskRepository.getTopTasks() != null && taskRepository.getTopTasks().size() != 0) {
			for (Task task : taskRepository.getTopTasks()) {
				findTodo(todo, task, messages);
			}
		}
	}

	public void findTodo(Set<Task> todo, Task task, Set<Message> messages) {
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
		if (messages == null || messages.size() == 0)
			return null;
		Iterator<Message> itr = messages.iterator();
		while (itr.hasNext()) {
			Message message = itr.next();
			if (task.equals(message.getTask())) {
				return message;
			}
		}
		return null;
	}
}

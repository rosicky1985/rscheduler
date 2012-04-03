package com.nbb.rscheduler.executor;

import com.nbb.rscheduler.entity.Execution;
import com.nbb.rscheduler.entity.Message;
import com.nbb.rscheduler.entity.Task;
import com.nbb.rscheduler.repository.MessageRepository;

public class TaskRunner {
	private MessageRepository messageRepository;

	public Runnable runTask(final Task task, final Execution exe) {
		return new Runnable() {
			@Override
			public void run() {
				Message message = new Message(task,exe);
				boolean success = true;
				try {
					runTask(task, exe);
				} catch (Exception e) {
					success = false;
				}
				if (success) {
					message.success();
					messageRepository.submit(message);
					return;
				}else{
					message.fail();
					//TODO retires
				}
				
			}
		};
	}

}

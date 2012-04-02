package com.nbb.rscheduler.executor;

import java.util.Set;

import com.nbb.rscheduler.entity.Execution;
import com.nbb.rscheduler.entity.Message;
import com.nbb.rscheduler.repository.ExecutionRepository;
import com.nbb.rscheduler.repository.MessageRepository;

public class Dispatch extends Thread {
	private MessageRepository messageRepository;
	private ExecutionRepository executionRepository;
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
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
				for (Execution exe : unresolvedExecutions) {
					Set<Message> message = messageRepository.getMessages(exe);
				}
			}
		}
	}
}

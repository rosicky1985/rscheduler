package com.nbb.rscheduler.repository;

import java.util.Set;

import com.nbb.rscheduler.entity.Execution;
import com.nbb.rscheduler.entity.Message;

public interface MessageRepository {

	public Set<Message> getMessages(Execution exe);

}

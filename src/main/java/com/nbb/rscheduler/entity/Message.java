package com.nbb.rscheduler.entity;

import java.util.Date;

public class Message {
	public static final String STATUS_OK = "SUCCESS";
	public static final String STATUS_FAIL = "FAIL";
	private Task task;
	private Date created;
	private Date completed;
	private Execution execution;
	private String status;

	public Message(Task task2, Execution exe) {
		this.task = task2;
		this.execution = exe;
		this.created = new Date();
	}

	public Date getCreated() {
		return created;
	}

	public Date getCompleted() {
		return completed;
	}

	public Message() {

	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Execution getExecution() {
		return execution;
	}

	public void setExecution(Execution execution) {
		this.execution = execution;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void success() {
		this.status = Message.STATUS_OK;
		this.completed = new Date();
	}
	
	public void fail(){
		this.status = Message.STATUS_FAIL;
		this.completed = new Date();
	}
}

package com.nbb.rscheduler.entity;


public class Message {
	public static final String STATUS_OK = "SUCCESS";
	public static final String STATUS_FAIL = "FAIL";
	private Task task;
	private Execution execution;
	private String status;

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
}

package com.nbb.rscheduler.entity;

import java.util.Set;

public class Task {
	private Set<Task> required = null;
	private String name;
	private String bean;
	private String method;
	public String getBean() {
		return bean;
	}

	public void setBean(String bean) {
		this.bean = bean;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Set<Task> getRequired() {
		return required;
	}

	public void setRequired(Set<Task> required) {
		this.required = required;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

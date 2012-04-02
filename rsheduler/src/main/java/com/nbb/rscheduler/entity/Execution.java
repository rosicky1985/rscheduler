package com.nbb.rscheduler.entity;

import java.util.Map;

public class Execution {
	private String id;
	private Map<String, Object> params;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}

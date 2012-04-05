package com.nbb.rscheduler.repository;

import java.util.Set;

import com.nbb.rscheduler.entity.Execution;

public interface ExecutionRepository {

	public Set<Execution> getUnresolveExecution();

}

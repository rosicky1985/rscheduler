package com.nbb.rscheduler.executor;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.nbb.rscheduler.entity.Task;
import com.nbb.rscheduler.repository.TaskRepository;

public class DispatchTest {
	private Dispatch dispatch = new Dispatch();
	Task engineLog = new Task("EngineLog");
	Task log = new Task("FetchingLog");
	Task bidata = new Task("BiData");
	Task standyse = new Task("Standyse");
	Task aggregation = new Task("Aggregation");
	Task aggregationToDay = new Task("aggregationToDay");
	Task exporting = new Task("Exporting");
	Task compress = new Task("Compress");
	Task lineB1 = new Task("lineB1");
	Task lineB2 = new Task("lineB2");

	@Before
	public void init() {
		log.setRequired(new HashSet<Task>(Arrays.asList(engineLog)));
		standyse.setRequired(new HashSet<Task>(Arrays.asList(log, bidata)));
		aggregation.setRequired(new HashSet<Task>(Arrays.asList(standyse)));
		aggregationToDay.setRequired(new HashSet<Task>(Arrays
				.asList(aggregation)));
		exporting.setRequired(new HashSet<Task>(Arrays.asList(standyse)));
		compress.setRequired(new HashSet<Task>(Arrays.asList(exporting)));
		lineB2.setRequired(new HashSet<Task>(Arrays.asList(lineB1)));
		dispatch.setTaskRepository(new TaskRepository() {
			@Override
			public Set<Task> getTopTasks() {
				return new HashSet<Task>(Arrays.asList(lineB2, compress,
						aggregationToDay));
			}

		});
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

package com.chattiez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chattiez.dao.JobsDao;
import com.chattiez.model.Jobs;
@RestController 
public class JobController {
	@Autowired
	JobsDao jobsdao;
	@RequestMapping(value="/addJobs",headers="accept=Application/json",method=RequestMethod.POST)
	public void saveJobs(@RequestBody Jobs jobs)
	{
		jobsdao.addJobs(jobs);

	}
	
	@RequestMapping(value="/getJobs",headers="accept=Application/json",method=RequestMethod.GET)
	public List<Jobs> getJobs()
	{
		List<Jobs> jobs=jobsdao.viewJobs();
		return jobs;
	}
	
	
	@RequestMapping(value="/deleteJob",headers="accept=Application/json",method=RequestMethod.DELETE)
	public void deleteJob(@RequestBody Jobs jobs){
		jobsdao.deleteJob(jobs);
	}
	
	@RequestMapping(value="/updateJobs",headers="accept=Application/json",method=RequestMethod.PUT)
	public void updateJobs (@RequestBody Jobs jobs){
		jobsdao.updateJobs(jobs);
	}
	
}

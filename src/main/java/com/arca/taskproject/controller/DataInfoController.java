package com.arca.taskproject.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arca.taskproject.service.DataInfoService;



@RestController
@RequestMapping("/data")
public class DataInfoController {
	
	//@Autowired
	DataInfoService dataInfoService;
	
    @Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    Job processJob;

	
	@GetMapping("/")
	public ResponseEntity getConnection() {	
		try {
			startJob();
			return ResponseEntity.ok("server is working...");
			
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("error server!");
		}
	}
	
	public void startJob() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
		System.out.println("jobParameters "+jobParameters+", dataLoad "+processJob);
        jobLauncher.run(processJob, jobParameters);
	}

}

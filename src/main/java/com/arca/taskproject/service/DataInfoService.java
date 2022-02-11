package com.arca.taskproject.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.arca.taskproject.entity.DataInfo;
import com.arca.taskproject.repository.DataInfoRepository;


@Service
public class DataInfoService {
	
	@Autowired
	DataInfoRepository dataInfoRepository;
	
	public DataInfo create(DataInfo dataInfo) {
		System.out.println("dataInfo "+dataInfo);
		return dataInfoRepository.save(dataInfo);
	}
	
	

}

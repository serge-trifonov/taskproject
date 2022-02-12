package com.arca.taskproject.batch.writer;

import java.util.List;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arca.taskproject.entity.DataInfo;
import com.arca.taskproject.service.DataInfoService;

@Component
public class DataInfoWriter implements ItemWriter<DataInfo>{

	@Autowired
	DataInfoService dataInfoService;
	
	private JobExecution jobExecution;

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
	    jobExecution = stepExecution.getJobExecution();
	    System.out.println("jobExecution "+jobExecution);
	    
       // System.out.println(Math.round(reads / jobComplete * 100));
	}
	
	@Override
	public void write(List<? extends DataInfo> items) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("item :"+items);
		
        //System.out.println(reads);
		for(DataInfo dataInfo: items) {
			dataInfoService.create(dataInfo);
		}
		Long jobComplete = (Long) jobExecution.getExecutionContext().get("jobComplete");
        double reads = 0;       
        for (StepExecution step : jobExecution.getStepExecutions()) {
            reads = reads + step.getReadCount();
        }
        System.out.println("completed : "+reads / jobComplete * 100);
		
	}

}

package com.arca.taskproject.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.arca.taskproject.batch.listener.StepItemReadListener;
import com.arca.taskproject.batch.processor.DataInfoValidationProcessor;
import com.arca.taskproject.batch.reader.DataInfoReader;
import com.arca.taskproject.batch.writer.DataInfoWriter;
import com.arca.taskproject.entity.DataInfo;
import com.arca.taskproject.test.JobCompletionListener;

@Configuration
public class BatchConfiguration {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataInfoWriter dataInfoWriter;
	
	@Autowired
	private DataInfoValidationProcessor dataInfoValidationProcessor;
	
	@Autowired
	private DataInfoReader dataInfoReader;
	
	@Bean
	public SkipPolicy fileVerificationSkipper() {
	    return new FileVerificationSkipper();
	}
	
	@Bean
	public Step orderStep1() {
		Step step=stepBuilderFactory.get("data-transaction-file-load")
				.<DataInfo,DataInfo>chunk(100)
				.listener(new StepItemReadListener())
				.reader(reader()).faultTolerant().skipPolicy(fileVerificationSkipper())
				.processor(dataInfoValidationProcessor)
				.writer(dataInfoWriter)
				.build();
		System.out.println("step "+step);
		
		return step;//jobBuilderFactory.get("dataLoad").start(step).build();
	}
	
	@Bean
	public Job processJob() {
		System.out.println("in process job");
		Job job =  jobBuilderFactory.get("processJob")
				.listener(listener())
				.flow(orderStep1()).end().build();
		System.out.println("job : "+job);
		return job;
	}

	@Bean
	public JobExecutionListener listener() {
		System.out.println("listener");
		return new JobCompletionListener();
	}
	
	@SuppressWarnings("unchecked")
	@Bean
	public FlatFileItemReader<DataInfo> reader() 
	{
	  //Create reader instance
	  FlatFileItemReader<DataInfo> reader = new FlatFileItemReader<DataInfo>();
	   
	  //Set input file location
	  reader.setResource(new FileSystemResource("data-test.txt"));
	   
	  //Set number of lines to skips. Use it if file has header rows.  
	   
	  //Configure how each line will be parsed and mapped to different values
	  reader.setLineMapper(new DefaultLineMapper() {
	    {
	      //3 columns in each row
	      setLineTokenizer(new DelimitedLineTokenizer() {
	        {
	          setNames(new String[]{"time", "valeur", "origine"});
	        }
	      });
	      //Set values in Employee class
	      setFieldSetMapper(new BeanWrapperFieldSetMapper<DataInfo>() {
	        {
	          setTargetType(DataInfo.class);
	        }
	      });
	    }
	  });
	  return reader;
	}
}



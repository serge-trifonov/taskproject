package com.arca.taskproject.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arca.taskproject.entity.DataInfo;
import com.arca.taskproject.service.DataInfoService;

@Component
public class DataInfoWriter implements ItemWriter<DataInfo>{

	@Autowired
	DataInfoService dataInfoService;
	
	@Override
	public void write(List<? extends DataInfo> items) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("item :"+items);
		for(DataInfo dataInfo: items) {
			dataInfoService.create(dataInfo);
		}
		
		
	}

}

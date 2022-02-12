package com.arca.taskproject.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.arca.taskproject.entity.DataInfo;

@Component
public class DataInfoValidationProcessor implements  ItemProcessor<DataInfo, DataInfo>{

	@Override
	public DataInfo process(DataInfo item) {
		
		//System.out.println("data validation is processing...");
		//System.out.println("Vitem "+item);
		boolean ok=(item.getTime()!=null)&&(item.getValeur()>=0&&item.getValeur()<=100)&&(item.getOrigine()!=null);
	//	System.out.println(ok+" success validated");
		if(ok) {
			//System.out.println(item+" success validated");
			return item;
		}
		return null;
	}

}

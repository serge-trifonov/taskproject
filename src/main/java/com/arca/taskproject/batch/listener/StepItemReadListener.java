package com.arca.taskproject.batch.listener;

import org.springframework.batch.core.ItemReadListener;

import com.arca.taskproject.entity.DataInfo;

public class StepItemReadListener implements ItemReadListener<DataInfo> {

	@Override
	public void beforeRead() {
		System.out.println("ItemReadListener - beforeRead");
	}

	@Override
	public void afterRead(DataInfo item) {
		System.out.println("ItemReadListener - afterRead");
	}

	@Override
	public void onReadError(Exception ex) {
		System.out.println("ItemReadListener - onReadError");
	}
}
package com.arca.taskproject.batch.listener;

import org.springframework.batch.core.ItemReadListener;

import com.arca.taskproject.entity.DataInfo;

public class StepItemReadListener implements ItemReadListener<DataInfo> {

	@Override
	public void beforeRead() {
	}

	@Override
	public void afterRead(DataInfo item) {
	}

	@Override
	public void onReadError(Exception ex) {
	}
}
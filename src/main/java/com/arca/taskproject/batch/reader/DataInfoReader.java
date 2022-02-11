package com.arca.taskproject.batch.reader;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import com.arca.taskproject.entity.DataInfo;


@Component
public class DataInfoReader implements ItemReader<DataInfo> {

	@Override
	public DataInfo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		/*DataInputStream reader=new DataInputStream(new FileInputStream("data-test.txt"));
		
		System.out.println("data is reading....");
		
		
		
		String time="";
		byte value=0;
		String country="";
		
		DataInfo dataInfo=null;
		
		
		
		while(reader.available()>0) {
			
			System.out.println("hello "+reader.readLine());
			
			try {
				
				time=reader.readUTF();
				value=reader.readByte();
				country=reader.readUTF();
				
				dataInfo=new DataInfo(time,value,country);
			}
			catch(IOException e) {
				//Logger à implementer
			}
			
			System.out.println(dataInfo); */
		
		DataInfo dataInfo=null;
		
		Path path = Paths.get("data-test.txt");
	    try(BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))){
	      String currentLine = null;
	      while((currentLine = reader.readLine()) != null){//while there is content on the current line
	        String [] data = currentLine.split(","); // print the current line
	        if (data.length > 2) {
	        	dataInfo = new DataInfo(data[0], Byte.parseByte(data[1]), data[2]);
	        }
	        return dataInfo;
	      }
	    }catch(IOException ex){
	      ex.printStackTrace(); //handle an exception here
	    }
		return null;	
	}

}

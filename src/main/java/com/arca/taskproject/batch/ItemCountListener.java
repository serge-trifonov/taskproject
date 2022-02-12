package com.arca.taskproject.batch;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;


public class ItemCountListener implements ChunkListener {
   
  @Override
  public void beforeChunk(ChunkContext context) {
  }
 
  @Override
  public void afterChunk(ChunkContext context) {
     
    int count = context.getStepContext().getStepExecution().getReadCount();
    System.out.println("count "+count);
  }
   
  @Override
  public void afterChunkError(ChunkContext context) {
  }
}

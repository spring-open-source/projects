
package nl.amis.download;

import java.io.File;
import java.util.Properties;
import javax.batch.api.Batchlet;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("MakeDirectoryBatchlet")
public class MakeDirectoryBatchlet implements Batchlet {

    @Inject
    JobContext jobCtx;

    @Override
    public String process() throws Exception {
        Properties jobParameters = BatchRuntime.getJobOperator().getParameters(jobCtx.getExecutionId());
        String downloadDirectoryRoot = jobCtx.getProperties().getProperty("downloadDirectory");
        System.out.println("Create temporary download directory "+downloadDirectoryRoot +"/job"+jobCtx.getExecutionId());
        new File(downloadDirectoryRoot +"/job"+jobCtx.getExecutionId()).mkdirs();
        return "done";
    }

    @Override
    public void stop() throws Exception {
        
    }
  
    public MakeDirectoryBatchlet() {}
    
    
}

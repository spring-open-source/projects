/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amis.download;

import java.util.Properties;
import javax.batch.api.Batchlet;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("RemoveDirectoryBatchlet")
public class RemoveDirectoryBatchlet implements Batchlet {

    @Inject
    JobContext jobCtx;

    @Override
    public String process() throws Exception {
        Properties jobParameters = BatchRuntime.getJobOperator().getParameters(jobCtx.getExecutionId());
        String downloadDirectoryRoot = jobCtx.getProperties().getProperty("downloadDirectory");
        System.out.println("Remove directory "+downloadDirectoryRoot +"/job"+jobCtx.getExecutionId());

        return "done";
    }

    @Override
    public void stop() throws Exception {
        
    }
  
    public RemoveDirectoryBatchlet() {}
    
    
}

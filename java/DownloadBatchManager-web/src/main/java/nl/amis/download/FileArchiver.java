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



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Named("FileArchiver")
public class FileArchiver implements Batchlet {

    @Inject
    JobContext jobCtx;

    @Override
    public String process() throws Exception {
        Properties jobParameters = BatchRuntime.getJobOperator().getParameters(jobCtx.getExecutionId());
        System.out.println("Archive files in directory for job " + jobCtx.getExecutionId());
        String downloadDirectory = jobCtx.getProperties().getProperty("downloadDirectory") 
                + File.separator + "job" + jobCtx.getExecutionId();
        String outputZipFile = jobCtx.getProperties().getProperty("archivesDirectory") 
                + File.separator + "job" + jobCtx.getExecutionId() + ".zip";
        List<String> fileList = generateFileList(new File(downloadDirectory), downloadDirectory);
        zipIt(outputZipFile, downloadDirectory, fileList);
        return "done";
    }

    @Override
    public void stop() throws Exception {
    }
 
    /**
     * Zip it
     * @param zipFile output ZIP file location
     */
    public void zipIt(String zipFile, String sourceFolder, List<String> fileList) {
        byte[] buffer = new byte[1024];
        try {
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            System.out.println("Output to Zip : " + zipFile);
            for (String file : fileList) {
                System.out.println("File Added : " + file);
                ZipEntry ze = new ZipEntry(file);
                zos.putNextEntry(ze);
                FileInputStream in =
                        new FileInputStream(sourceFolder + File.separator + file);
                int len;
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                in.close();
            }
            zos.closeEntry();
            zos.close();
            System.out.println("Done with creating the file archive");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Traverse a directory and get all files, and add the file into fileList
     */
    public List<String> generateFileList(File node, String sourceFolder) {
    List<String> fileList = new ArrayList<String>();
        //add file only
        if (node.isFile()) {
            fileList.add(generateZipEntry(node.getAbsoluteFile().toString(), sourceFolder));
        }
        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename : subNote) {
                generateFileList(new File(node, filename), sourceFolder);
            }
        }
        return fileList;
    }

    /**
     * Format the file path for zip
     *
     * @param file file path
     * @return Formatted file path
     */
    private String generateZipEntry(String file, String sourceFolder) {
        return file.substring(sourceFolder.length() + 1, file.length());
    }
}

package com.singlton.springboot;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HelloController {
	
	@RequestMapping("/custom_dropbox")
	String custom_dropbox(){
	    return "custom_dropbox";
	}
	
	
	
    @RequestMapping(value = "/" )
	public String req( HttpServletRequest request, HttpServletResponse response, Model model) {
		return "index";
	}
    
    @RequestMapping("/try")
	String tryData(){
	    return "try";
	}
    
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
	String uploadMultipleFileHandler(
			@RequestParam("fileNM") String[] fileNM,
			@RequestParam("fileData") MultipartFile[] fileData
		) {
		
    	String serverFolder = "D:/upload";
		File serverFolderDir = new File(serverFolder);
		if (!serverFolderDir.exists()){
			serverFolderDir.mkdirs();
		}
		
		String message = "";
		for (int i = 0; i < fileData.length; i++) {
			MultipartFile file = fileData[i];
			String name = file.getOriginalFilename();
			String filePath = serverFolder + "/" + name;
			System.out.println("Path ::: " + filePath + " Name ::: "+ name + " Size ::: " + file.getSize() );
			
			try {
				String folderPath = filePath.substring(0, filePath.lastIndexOf("/") );
				String fileName = filePath.substring( filePath.lastIndexOf("/") , filePath.length() );
				
				File dirTemp = new File(folderPath);
				if ( !dirTemp.exists() ){
					dirTemp.mkdirs();
					System.out.println("folderPath ::: "+ folderPath + " Created...");
				}
				
				byte[] bytes = file.getBytes();
				System.out.println("folderPath + fileNM[i] ::: " + folderPath + "/" + fileNM[i]);
				File serverFile = new File( folderPath + "/" + fileNM[i] );
				BufferedOutputStream stream = new BufferedOutputStream( new FileOutputStream(serverFile) );
				stream.write(bytes);
				stream.close();
				
				message = message + "You successfully uploaded file=" + name + "";
			} catch (Exception e) {
				e.printStackTrace();
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
    
    
	@RequestMapping("/index_new")
	String index_new(){
	    return "index_main";
	}
	@RequestMapping("/uploadFolder")
	public String upload( HttpServletRequest request , HttpServletResponse response, Model model) throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");
		PrintWriter outNet = response.getWriter();

		int id = Integer.parseInt(request.getParameter("fileupload"));
		String filePath = java.net.URLDecoder.decode(request
				.getParameter("path"), "UTF-8");
		String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
		String loadPath = "E:\\temp";
		InputStream in = request.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(in);
		File file = new File(loadPath);
		if (!file.exists()) {
			file.mkdir();
		}
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(new File(loadPath + fileName)));
		int b = 0;
		while ((b = bis.read()) != -1) {
			bos.write(b);
			bos.flush();
		}
		bis.close();
		bos.close();
		in.close();
		System.out.println("Done");
		return "Done";
	} 
}


@RestController
class ReqController{
	@RequestMapping(value = "req" , produces = MediaType.APPLICATION_JSON_VALUE )
	public String req(
			@RequestParam(name="name", required=false, defaultValue="1")String data,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		
		System.out.println("S.URL : "+request.getRequestURI()+" Data : "+data+" Time : "+new java.util.Date());
		
		String result = ( ReqContainer.addRequestData(request) ) ? "1" : "0";
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("E.URL : "+request.getRequestURI()+" Data : "+data+" Time : "+new java.util.Date());
		
		if( Integer.parseInt(request.getParameter("name")) == 10 ){
	    	for (HttpServletRequest bean : ReqContainer.getRequestData()) {
				System.out.println("IN >>> "+bean.getParameter("name"));
			}
	    }
		return result;
	}
	
}


class ReqContainer{
	private static ReqContainer instance = null;
	private static int numRequests = 0;
	private static LinkedHashSet<HttpServletRequest> requestData = null;
	
	private ReqContainer() {
		numRequests = 0;
		requestData = new LinkedHashSet<HttpServletRequest>();
	}
	public static synchronized ReqContainer getInstance() {
	    if (instance == null){
	        instance = new ReqContainer();
	    }
	    return instance;
	}
	
	public static  boolean addRequestData(HttpServletRequest object) {
	    if (instance == null){
	        instance = new ReqContainer();
	    }
	    System.out.println("Request Count : " +numRequests+ " Size("+requestData.size()+")"+" Before Add("+object.getParameter("name")+")");
	    requestData.add(object);
	    numRequests++;
	    System.out.println("Request Count : " +numRequests+ " Size("+requestData.size()+")"+" After Add("+object.getParameter("name")+")");
	    
	    return true;
	}
	
	public static synchronized boolean removeRequestData(HttpServletRequest object) {
	    if (instance == null){
	        instance = new ReqContainer();
	    }
	    System.out.println("Request Count : " +numRequests+ " Size("+requestData.size()+")"+" Before Remove("+object.getParameter("name")+")");
	    requestData.remove(object);
	    numRequests--;
	    System.out.println("Request Count : " +numRequests+ " Size("+requestData.size()+")"+" After Remove("+object.getParameter("name")+")");
	    return true;
	}
	
	public static synchronized int getNumRequests(){
		System.out.println("Request Count : " +numRequests);
		return numRequests;
	}
	
	public static synchronized LinkedHashSet<HttpServletRequest> getRequestData(){
		System.out.println("getRequestData() Request Count : " +numRequests+ " Size("+requestData.size()+")");
		return requestData;
	}
}

package com.singlton.springboot;

import javax.servlet.ServletRequestEvent;
import javax.servlet.annotation.WebListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);
    }
    
}

@Configuration
@WebListener
class MyRequestContextListener extends RequestContextListener {
	@Override
	public void requestInitialized(ServletRequestEvent requestEvent) {
		String nm = requestEvent.getServletRequest().getParameter("name");
		
		System.out.println("Name : "+nm+ " >>> "+ (nm != null && (!nm.contains("null"))) );
		
		if(nm != null && (!nm.contains("null"))){
			//super.requestInitialized(requestEvent);
			return;
		}else{
			super.requestInitialized(requestEvent);
			//return;
		}
	}
}
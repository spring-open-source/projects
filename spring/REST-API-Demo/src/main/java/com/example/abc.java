package com.example;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class abc{
	
	@CrossOrigin(origins = "*")
	@RequestMapping(name="/a", method = RequestMethod.POST)
	public @ResponseBody String a(@PathParam(value="a") String a, HttpServletRequest request){
		System.out.println("req_POST ::: " +request.getRequestURI());
		System.out.println("a : "+a);
		return "a : "+a;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(name="/a", method = RequestMethod.GET)
	public @ResponseBody String ab(@PathParam(value="a") String a, HttpServletRequest request){
		System.out.println("req_GET ::: " +request.getRequestURI());
		System.out.println("a : "+a);
		return "a : "+a;
	}
	
}


/*class xyz{
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(name="/b")
	public @ResponseBody String b(@PathParam(value="b") String b, HttpServletRequest request){
		System.out.println("reqb ::: " +request.getRequestURI());
		System.out.println("b : "+b);
		return "b : "+b;
	}
	
}*/


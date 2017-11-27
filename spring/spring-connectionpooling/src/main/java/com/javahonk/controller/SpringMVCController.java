package com.javahonk.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javahonk.dao.IPersonDAO;

/**
 * @author Java Honk
 *
 */
@Controller
public class SpringMVCController {
	
	@Autowired 
	IPersonDAO personDao;
	
	@RequestMapping(value = "/databasetest")
	public @ResponseBody List<Map<String, Object>> databasetest() {
		
		return personDao.selectAllPerson();
	}

}

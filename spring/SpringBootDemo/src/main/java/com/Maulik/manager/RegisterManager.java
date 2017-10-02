package com.Maulik.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.Maulik.Repository.RegisterRepository;
import com.Maulik.dto.Register;
import com.Maulik.model.createRegisterRequest;

@Controller
public class RegisterManager 
{
	@Autowired
	RegisterRepository registerrepository;

	public Register Register_user(createRegisterRequest form) 
	{	
		Register register = null;
	
			register=new Register();
			register.setFirstname(form.getFirstname());
			register.setLastname(form.getLastname());
			register.setEmail(form.getEmail());
			
			/*BCryptPasswordEncoder pass=new BCryptPasswordEncoder();*/
			//pass.encode(form.getPassword());
			/*String password=pass.encode(form.getPassword());
			System.out.println("encode Pass === "+password);*/
			
			register.setPassword(form.getPassword());
			register.setMobile(form.getMobile());
			
			registerrepository.save(register);
			
	
		
		return register;
		
	}

	public List<Register> getallUser() 
	{
		return (List<Register>) registerrepository.findAll();
	}

	public void edit_user(createRegisterRequest form) {
		Register register=new Register();
		
		register.setId(form.getId());
		register.setFirstname(form.getFirstname());
		register.setLastname(form.getLastname());
		register.setEmail(form.getEmail());
		register.setPassword(form.getPassword());
		register.setMobile(form.getMobile());
		
		registerrepository.save(register);
	}

	public Register login_user(String email, String password) 
	{
	//	System.out.println("password"+password);
		
		return registerrepository.login_user(email,password);
	}

	public void delete_user(createRegisterRequest form) 
	{
		registerrepository.delete(form.getId());
	}
	
}

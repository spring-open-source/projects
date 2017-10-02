package com.Maulik.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Maulik.dto.Register;
import com.Maulik.manager.RegisterManager;
import com.Maulik.model.GetallRegister;
import com.Maulik.model.createRegisterRequest;

@Service
public class RegisterServices 
{
	@Autowired
	RegisterManager registermanager;

	public Register Register_user(createRegisterRequest form) 
	{	
		return registermanager.Register_user(form);
	}

	public GetallRegister getallUser() 
	{	
		Iterator<Register> ult=registermanager.getallUser().iterator();
		
		List<Register> ulist = new ArrayList<Register>();
		while (ult.hasNext()) 
		{
			ulist.add(ult.next());
		}
		
		GetallRegister getallreg=new GetallRegister();
		getallreg.setRegister_user(ulist);
		return getallreg;
	}

	public void edit_user(createRegisterRequest form) 
	{
		registermanager.edit_user(form);
	}

	public Register login_user(String email, String password) 
	{
		return registermanager.login_user(email,password);
	}


	public void delete_user(createRegisterRequest form) 
	{
		registermanager.delete_user(form);
	}

}

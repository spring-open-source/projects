package com.Maulik.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.Maulik.Repository.RegisterRepository;
import com.Maulik.dto.Register;
import com.Maulik.model.createRegisterRequest;
import com.Maulik.model.loginRequest;
import com.Maulik.services.RegisterServices;

@Controller
public class RegisterController {
	@Autowired
	private RegisterServices registerservices;

	@Autowired
	private Environment env;

	@Autowired
	private RegisterRepository registerRepository;

	
	@RequestMapping("/page")
	public String page() {
		return "page";
	}
	
	
/*	@RequestMapping(value = "/generate/pdf.htm", method = RequestMethod.GET)
	 ModelAndView generatePdf(HttpServletRequest request,
	   HttpServletResponse response, HttpSession session , Model model) throws Exception {
	  System.out.println("Calling generatePdf()...");
	  Long id=(Long) session.getAttribute("id");
		
		if(id!=null)
		{
		
		Register r=registerRepository.findOne(id);
		model.addAttribute("Login_success", "Login_success");
		model.addAttribute("user_detail", r);
		model.addAttribute("email", r.getFirstname());
		
		ModelAndView modelAndView = new ModelAndView("pdfView", "command",model);
		return modelAndView;
		}
		else
		{
			return null;
		}
	 }
	
	*/
	@RequestMapping("/")
	public ModelAndView getCreateIndexView() {
	
	/*	String HostUrl="D:"+File.separator+"Data"+File.separator+"files"+File.separator+"1.mp4";
		
		File file=new File(HostUrl);
		System.out.println("File Path ::: "+file.getAbsolutePath());
		System.out.println("File Name ::: "+file.getName());
		
		// For Linux
		String[] cmd = {
			    "/bin/sh",
			    "-c",
			    "identify -list font | grep -i -w \'Font: "  + file.getAbsolutePath() + "\'"
			};
		Runtime.getRuntime().exec(cmd);
		
		// For Window
		try {
			Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL \"" +file + "\"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
	
/*		Runtime runtime = Runtime.getRuntime();     //getting Runtime object
		String HostUrl="D:"+File.separator+"Data"+File.separator+"files"+File.separator+"1.mp4";
	    String[] s = new String[] {"C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe", HostUrl};
	 
	    try {
			Process process = runtime.exec(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     */
		
		ModelMap model = new ModelMap();
		model.addAttribute("Main_Page", "Main_Page");
		model.addAttribute("form", new loginRequest());
		return new ModelAndView("/login", model);
	}

	@RequestMapping("/index")
	public ModelAndView getCreateIndexView_index() {
		ModelMap model = new ModelMap();
		model.addAttribute("Main_Page", "Main_Page");
		model.addAttribute("form", new loginRequest());
		return new ModelAndView("login", model);
	}

	@RequestMapping(value="/login_user")
	public ModelAndView getlogin_user_dashboard(HttpSession session) {
		ModelMap model = new ModelMap();
		Long id=(Long) session.getAttribute("id");
		
		if(id!=null)
		{
		
		Register r=registerRepository.findOne(id);
		model.addAttribute("Login_success", "Login_success");
		model.addAttribute("user_detail", r);
		model.addAttribute("email", r.getFirstname());
		
		return new ModelAndView("dashboard", model);
		}
		else
		{
			model.addAttribute("Main_Page", "Main_Page");
			model.addAttribute("form", new loginRequest());
			return new ModelAndView("login", model);
		}

	}

	
	@RequestMapping("/dashboard" )
	public ModelAndView getCreateIndexView_dashboard(HttpSession session) {
		ModelMap model = new ModelMap();
		
		Long id=(Long) session.getAttribute("id");
		
		if(id!=null)
		{
		
		Register r=registerRepository.findOne(id);
		model.addAttribute("Login_success", "Login_success");
		model.addAttribute("user_detail", r);
		model.addAttribute("email", r.getFirstname());
		
		return new ModelAndView("dashboard", model);
		}
		else
		{
			model.addAttribute("Main_Page", "Main_Page");
			model.addAttribute("form", new loginRequest());
			return new ModelAndView("login", model);
		}
	}

	@RequestMapping("/register_here")
	public ModelAndView getCreateRegisterView() {
		ModelMap model = new ModelMap();
		model.addAttribute("register_Page", "register_Page");
		model.addAttribute("form", new createRegisterRequest());
		return new ModelAndView("register", model);
	}

	@RequestMapping(value = "/register_user", method = RequestMethod.POST)
	public ModelAndView RegisterUser(
			@ModelAttribute("form") @Valid createRegisterRequest form,
			BindingResult result) throws IOException {
			ModelMap model= new ModelMap();
			
			if(result.hasErrors()) {
				return new ModelAndView("register", model);
			} else {
			
			String barcodeString = "0101000001";
		    Code128Bean barcode128Bean = new Code128Bean();
		    
		  //  barcode128Bean.setCodeset(Code128Constants.CODESET_B);
		    final int dpi = 100;

			  //Configure the barcode generator
			    //adjust barcode width here
			  barcode128Bean.setModuleWidth(UnitConv.in2mm(5.0f / dpi)); 
			  barcode128Bean.doQuietZone(false);
	
			  //Open output file
			  File outputFile = new File("D:/barcode.png");
			  OutputStream out = new FileOutputStream(outputFile);
			  try 
			  {
			      BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(
			              out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
	
			      barcode128Bean.generateBarcode(canvasProvider,barcodeString);
	
			      canvasProvider.finish();
			  } 
			  finally 
			  {
			      out.close();
			  }
				
				
		/*if(result.hasErrors())
		{
			return new ModelAndView("register", model);
		}
			*/
			registerservices.Register_user(form);
		//	model.addAttribute("users", "Users");
		//	model.addAttribute("user_list", registerservices.getallUser().getRegister_user());
			return new ModelAndView("index", model);

			}
	}

/*	@RequestMapping(value = "RegisterByAjax", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Object insertdata(createRegisterRequest form,Model model) {
		Object object=null;
		Register r=registerservices.Register_user(form);
		Long i=r.getId();
		
	//	System.out.println("Registe ====="+r);
		if(i > 0){
			 object ="Success";
			 System.out.println("inserted data successfully......!!!!!!");
			 return object;
			 
		 }else{
			
			 object ="0";
			 System.out.println("inserted data not successfully......!!!!!");
			 return object;
		 }
	}*/

	@RequestMapping(value = "/get_user", method = RequestMethod.GET)
	public ModelAndView login_user_view(
			@ModelAttribute("form") createRegisterRequest form,
			HttpSession session) {

		ModelMap model = new ModelMap();
		model.addAttribute("users", "Users");
		model.addAttribute("user_list", registerservices.getallUser().getRegister_user());
		return new ModelAndView("index", model);
	}

	// get all user by ajax and json
/*	@RequestMapping(value = "getallUser", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<Register> getallUser(HttpServletRequest re,HttpServletResponse ro, Model model) {

		List<Register> userList = registerservices.getallUser().getRegister_user();
	
		return userList;
	}
	*/
	@RequestMapping(value = "/edit_user", method = RequestMethod.GET)
	public ModelAndView Edit_user_view(
			@ModelAttribute("form") createRegisterRequest form,
			HttpSession session) {

		Register reg = registerRepository.findOne(form.getId());

		ModelMap model = new ModelMap();
		model.addAttribute("edits_user", "Users_edit");
		model.addAttribute("id", reg.getId());
		model.addAttribute("form", reg);
		return new ModelAndView("index", model);
	}

	@RequestMapping(value = "/edit_user_one", method = RequestMethod.GET)
	public ModelAndView Edit_user_one_view(
			@ModelAttribute("form") @Valid createRegisterRequest form,
			HttpSession session,BindingResult bindingResult) {
		ModelMap model = new ModelMap();
		if (bindingResult.hasErrors()) {
			return new ModelAndView("index", model);
		}
		Register reg = registerRepository.findOne(form.getId());

		
		model.addAttribute("edits_user_one", "Users_edit");
		model.addAttribute("id", reg.getId());
		model.addAttribute("form", reg);
		return new ModelAndView("index", model);
	}
	@RequestMapping(value = "/delete_user", method = RequestMethod.GET)
	public String Delete_user_view(
			@ModelAttribute("form") @Valid createRegisterRequest form,
			HttpSession session) {
		
		registerservices.delete_user(form);

		return "redirect:/get_user";
	}	
	
	@RequestMapping(value = "/delete_user_one", method = RequestMethod.GET)
	public String Delete_user_one_view(
			@ModelAttribute("form") @Valid createRegisterRequest form,
			HttpSession session) {
		
		registerservices.delete_user(form);

		session.removeAttribute("id");

		return "redirect:/index";
	//	return "redirect:/get_user";
	}	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String Logout(HttpSession session) {
		session.removeAttribute("id");

		return "redirect:/index";
	}

	@RequestMapping(value = "/edit_user", method = RequestMethod.POST)
	public String edit_user_update(
			@ModelAttribute("form") @Valid createRegisterRequest form,
			BindingResult result) {
		if(result.hasErrors())
		{
			return "redirect:/index";
		}
		
		registerservices.edit_user(form);
		return "redirect:/get_user";
	}

	@RequestMapping(value = "/edit_user_one", method = RequestMethod.POST)
	public ModelAndView edit_user_one_update(
			@ModelAttribute("form") @Valid createRegisterRequest form,
			BindingResult result, HttpSession session) {
		registerservices.edit_user(form);

		Register reg = registerRepository.findOne(form.getId());
		ModelMap model = new ModelMap();
		model.addAttribute("user_detail", reg);
		model.addAttribute("Login_success", "Login_success");
		return new ModelAndView("index", model);
	}

	@RequestMapping(value = "/login_user", method = RequestMethod.POST)
	public ModelAndView login_user_view(
			@ModelAttribute("form") @Valid loginRequest loginrequest,
			BindingResult result, HttpSession session) {
		ModelMap model = new ModelMap();
		if(result.hasErrors())
		{
		//	model.addAttribute("form", new loginRequest());
			return new ModelAndView("login", model);
		}
		else
		{
		
		Register r = registerservices.login_user(loginrequest.getEmail(),
				loginrequest.getPassword());
		if (r != null) {
			session.setAttribute("id", r.getId());
			model.addAttribute("Login_success", "Login_success");
			model.addAttribute("user_detail", r);
			model.addAttribute("email", r.getFirstname());
			
			return new ModelAndView("dashboard", model);
		//	return new ModelAndView("dashboard", model);
		} else {
			model.addAttribute("Login_fail", "Login_fail");
			return new ModelAndView("login", model);
		}
		}
		
	}

/*	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(
			@RequestParam("uploadfile") MultipartFile uploadfile) {

		try {
			// Get the filename and build the local file path
			String filename = uploadfile.getOriginalFilename();
			String directory = env.getProperty("maulik.paths.uploadedFiles");
			String filepath = Paths.get(directory, filename).toString();

			// Save the file locally
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File(filepath)));

			stream.write(uploadfile.getBytes());
			stream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	} // method uploadFile
*/
}

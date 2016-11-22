package com.nic.upload;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	@RequestMapping(value = "/saveuploads" , method = RequestMethod.POST)
	public String upload(
			@RequestParam(value = "file" , required = false) MultipartFile file , 
				HttpServletRequest request , ModelMap model){
		System.out.println("¿ªÊ¼");
		String path = request.getSession().getServletContext().getRealPath("file");
		String fileName = file.getOriginalFilename();
		
		System.out.println(fileName);
		System.out.println(path);
		File targetfile = new File(path , fileName);
			if(!targetfile.exists()){
				targetfile.mkdirs();
			}
			
			try{
				file.transferTo(targetfile);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		model.addAttribute("fileUrl" , request.getContextPath() + "/file/" + fileName);
		return "result";
		
	}
}

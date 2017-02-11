package com.transporter.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.transporter.service.AccidentReportService;
import com.transporter.util.ImageUpload;

@Controller
@RequestMapping(value="/accident")
public class AccidentController {

    @Autowired
    ServletContext context;
	
	private static final String SAVETOPATH = "/accident";
	
	@Autowired
	private AccidentReportService accidentService;

	@RequestMapping(value = "/report/new", method = RequestMethod.GET)
	public String testPath(){
		return "upload";
	}

	@RequestMapping(value = "/report/upload", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") MultipartFile file) throws IOException{
		ImageUpload imageUpload = new ImageUpload();
		String savePath = imageUpload.save(file, SAVETOPATH, context);
		if(!savePath.isEmpty()){
			System.out.println(savePath);
		}else{
			System.out.println("failure");
		}
		return "upload";
	}

	@RequestMapping(value = "/report/new", method = RequestMethod.POST)
	public @ResponseBody String report(
			@RequestParam double lat,
			@RequestParam double lng,
			@RequestParam @DateTimeFormat(pattern="DD/MM/YYYY HH:mm") Date accidentDateTime,
			@RequestParam String accidentCause,
			@RequestParam MultipartFile accidentImage) {

		accidentService.add(lat, lng, accidentDateTime, accidentCause, accidentImage);
		
		return "OK";
	}
	
}

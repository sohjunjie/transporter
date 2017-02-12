package com.transporter.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.transporter.service.AccidentReportService;

@Controller
@RequestMapping(value="/accident")
public class AccidentController {
	
	@Autowired
	private AccidentReportService accidentReportService;

	@RequestMapping(value = "/report/new", method = RequestMethod.GET)
	public String testPath(){
		return "upload";
	}

	@RequestMapping(value = "/report/upload", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") MultipartFile file, Map<String, Object> map) {
		boolean success = accidentReportService.add(0, 0, new Date(), "Test", file);
		if(success){
			map.put("feedback", "image uploaded");
		}else{
			map.put("feedback", "image fail to upload");
		}
		return "upload";
	}

	@RequestMapping(value = "/report/new", method = RequestMethod.POST)
	public @ResponseBody String report(
			@RequestParam double lat,
			@RequestParam double lng,
			@RequestParam @DateTimeFormat(pattern="dd/MM/yyyy HH:mm") Date accidentDateTime,
			@RequestParam String accidentCause,
			@RequestParam MultipartFile accidentImage) {

		boolean success = accidentReportService.add(lat, lng, accidentDateTime, accidentCause, accidentImage);
		if(success){
			return "OK";
		}else{
			return "";
		}

	}
	
}

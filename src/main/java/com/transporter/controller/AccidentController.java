package com.transporter.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	private AccidentReportService accidentService;

	@RequestMapping(value = "/report/new", method = RequestMethod.GET)
	public String testPath(){
		return "upload";
	}

	@RequestMapping(value = "/report/upload", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") MultipartFile file, Map<String, Object> map) {
		boolean success = accidentService.add(0, 0, new Date(), "Test", file);
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
			@RequestParam @DateTimeFormat(pattern="DD/MM/YYYY HH:mm") Date accidentDateTime,
			@RequestParam String accidentCause,
			@RequestParam MultipartFile accidentImage) {

		boolean success = accidentService.add(lat, lng, accidentDateTime, accidentCause, accidentImage);
		if(success){
			return "OK";
		}else{
			return "";
		}

	}
	
}

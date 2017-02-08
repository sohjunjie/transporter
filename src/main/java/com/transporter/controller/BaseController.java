package com.transporter.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.transporter.model.Student;
import com.transporter.model.user.AuthenticatedUser;
import com.transporter.model.user.LTAPersonnel;
import com.transporter.service.AuthenticatedUserService;
import com.transporter.service.StudentService;

@Controller
public class BaseController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private AuthenticatedUserService authUserService;

	@RequestMapping("/")
	public String indexPage(Map<String, Object> map){
		return "home";
	}

	// AJAX HANDLER FOR LOGIN
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestParam String usernameOrEmail, @RequestParam String password, HttpSession session) {
		
		AuthenticatedUser verifiedUser = authUserService.getAuthUserByLoginDetails(usernameOrEmail, password);
		if(verifiedUser != null && verifiedUser instanceof LTAPersonnel){
			LTAPersonnel verifiedLTAUser = (LTAPersonnel) verifiedUser;
			session.setAttribute("user", verifiedLTAUser);
			session.setAttribute("username", verifiedLTAUser.getUsername());
			session.setAttribute("userfullname", verifiedLTAUser.getFullName());
			return "OK";
		}else{
			session.invalidate();
			return "Invalid login details";
		}

	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(Map<String, Object> map, HttpSession session){

		session.invalidate();
		return "redirect:/";

	}
	
	@RequestMapping("/index")
	public String setupForm(Map<String, Object> map, HttpSession session){
		Student student = new Student();
		map.put("student", student);
		map.put("studentList", studentService.getAllStudent());
		
		System.out.println(session.getAttribute("firstname"));
		
		return "student";
	}
	
	@RequestMapping(value="/student.do", method=RequestMethod.POST)
	public String doActions(@ModelAttribute Student student, BindingResult result, @RequestParam String action, Map<String, Object> map, HttpSession session){
		
		session.setAttribute("firstname", student.getFirstname());
		
		Student studentResult = new Student();
		switch(action.toLowerCase()){	//only in Java7 you can put String in switch
		case "add":
			studentService.add(student);
			studentResult = student;
			break;
		case "edit":
			studentService.edit(student);
			studentResult = student;
			break;
		case "delete":
			studentService.delete(student.getStudentId());
			studentResult = new Student();
			break;
		case "search":
			Student searchedStudent = studentService.getStudent(student.getStudentId());
			studentResult = searchedStudent!=null ? searchedStudent : new Student();
			break;
		}
		map.put("student", studentResult);
		map.put("studentList", studentService.getAllStudent());
		return "student";
	}
	
}

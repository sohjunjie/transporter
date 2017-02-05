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

import com.transporter.form.LoginBean;
import com.transporter.model.Student;
import com.transporter.model.user.AuthenticatedUser;
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
		return "index";
	}
	
	@RequestMapping("/authuser")
	public String setupAuthForm(Map<String, Object> map){
		AuthenticatedUser authUser = new AuthenticatedUser();
		map.put("authUser", authUser);
		map.put("authUserList", authUserService.getAllAuthUser());
		return "user";
	}
	
	@RequestMapping("/index")
	public String setupForm(Map<String, Object> map, HttpSession session){
		Student student = new Student();
		map.put("student", student);
		map.put("studentList", studentService.getAllStudent());
		
		System.out.println(session.getAttribute("firstname"));
		
		return "student";
	}
	
	@RequestMapping("/home")
	public String homePage(Map<String, Object> map){
		return "home";
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
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute(value="loginBean") LoginBean loginBean, Map<String, Object> map, HttpSession session){
		
		session.setAttribute("username", loginBean.getUsernameOrEmail());

		session.removeAttribute("username");
		
		return "index";
	}
	
}

package com.test.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.model.Student;

@Controller
public class RestController {
	
	@RequestMapping(value = "/getme",method = RequestMethod.GET)
	@ResponseBody
	public List<String> getMe() {
		String s = "hello";
		String s1 = "welcome to";
		String s2 = "Spring MVC";
		List<String> list = new ArrayList<String>();
		list.add(s);
		list.add(s1);
		list.add(s2);
		return list;
	}
	
	@RequestMapping(value = "/getString",method = RequestMethod.GET)
	@ResponseBody
	public String getString() {
		String s = "hello ";
		String s1 = " welcome to ";
		String s2 = " Spring MVC ";
		
		return s+s1+s2;
	}
	
	@ResponseBody
	@RequestMapping("/getStudent")
	public List<Student> getStudent(){
		
		List<Student> l = new ArrayList<Student>();
		
		Student s = new Student();
		s.setId(1);
		s.setName("manu");
		s.setMobile("");
		s.setAddress("bangalore");
		l.add(s);
		
		s = new Student();
		s.setId(2);
		s.setName("Jon");
		s.setMobile("");
		s.setAddress("UK");
		l.add(s);
		
		return l;
	}

}

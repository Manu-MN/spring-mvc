package com.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.DAO.StudentDaoImp;
import com.demo.model.Student;
import com.demo.response.ResponseModel;

@Controller
public class StudentCtr {
	
	@Autowired
	StudentDaoImp studentDaoImp;
	
	@RequestMapping(value = "/save" ,method = RequestMethod.POST ,consumes = "application/json")
	@ResponseBody
	public Map<String,Object> save(@Valid @RequestBody Student student) {
		
		boolean status = false;
		Map<String,Object> Res = new HashMap<String,Object>();
		
		status = studentDaoImp.save(student);
		
		if(status) {
			Res.put("id", student.getId());
			Res.put("status", HttpStatus.FOUND);
			return Res;
		}
		Res.put("status", HttpStatus.NOT_FOUND);
		return Res;
		
	}
	
	@RequestMapping(value = "/getStudents")
	@ResponseBody
	public List<Student> getStudents() {
		
		List<Student> students = studentDaoImp.getStudents();
		return students;
	}
	
	@RequestMapping(value = "/updateStudent" )
	@ResponseBody
	public Map<String, Object> update(@RequestBody Student student){
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		boolean status = studentDaoImp.update(student);
		if(status) {
			result.put("Id", student.getId());
			result.put("Status", "updated");
			return result;
		}
		else {
			result.put("Id", student.getId());
			result.put("Status", "failed");
			result.put("Error", "Invalid Student ID");
			return result;
		}
		
	}
	
	@RequestMapping(value = "/getStudentById/{id}")
	@ResponseBody
	public ResponseModel getStudentById(@PathVariable int id) {
		
		Student student = studentDaoImp.getStudentById(id);
		ResponseModel responseModel = new ResponseModel();
		if(student != null) {
			responseModel.setStudent(student);
			responseModel.setStatus("Success");
			responseModel.setHttpStatus(HttpStatus.FOUND);
			return responseModel;
		}
		else {
			responseModel.setStatus("Failed , Invalid ID!!!!");
			responseModel.setHttpStatus(HttpStatus.NOT_FOUND);
			return responseModel;
		}	
	}
	
	@RequestMapping(value = "/deleteStudent/{id}")
	@ResponseBody
	public Map<Object, Object> deleteStudent(@PathVariable int id){
		Map<Object, Object> result = new HashMap<Object, Object>();
		
		boolean status = studentDaoImp.deleteStudent(id);
		
		if (status) {
			result.put("Status", "Deleted");
			result.put("Status Code", HttpStatus.OK);
			return result;
		}
		else {
			result.put("Error", "invalid ID");
			result.put("Status", HttpStatus.NOT_FOUND);
			return result;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/pagination" )
	public List<Student> pagination(@RequestParam("pageNo") int pageNo){
		
		int pageLimit = 5;
		List<Student> students = null;
		
		if(pageNo == 1) {
			students = studentDaoImp.pagination(0, pageLimit);
			return students;
		}
		else {
			int firstPage = (pageNo - 1) * pageLimit;
			students = studentDaoImp.pagination(firstPage, pageLimit);
			return students;
		}	
	}

}

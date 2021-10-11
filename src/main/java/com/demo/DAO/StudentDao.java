package com.demo.DAO;

import java.util.List;

import com.demo.model.Student;

public interface StudentDao {
	
	boolean save(Student student);
	List<Student> getStudents();
	boolean update(Student student);
	Student getStudentById(int id);
	boolean deleteStudent(int id);
	List<Student> pagination(int firstPage,int lastPage);

}

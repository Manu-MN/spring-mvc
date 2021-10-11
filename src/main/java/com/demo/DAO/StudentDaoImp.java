package com.demo.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.demo.hibernate.HibernateUtils;
import com.demo.model.Student;

@Service
public class StudentDaoImp implements StudentDao {

	Session session;
	Transaction transaction;
	
	public boolean save(Student student) {
		
		session = HibernateUtils.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		int saved = (Integer) session.save(student);
		transaction.commit();
		session.close();
		if( saved != 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Student> getStudents() {
		
		session = HibernateUtils.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		List<Student> students = new ArrayList<Student>();
		
		try {
			students = session.createQuery("FROM Student").list();
			transaction.commit();
			session.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return students;
	}

	public boolean update(Student student) {

		session = HibernateUtils.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		try {
//			Student student1 = session.get(Student.class, student.getId());
//			student1.setName(student.getName());
//			student1.setMobile(student.getMobile());
//			student1.setAddress(student.getAddress());
			
			session.update(student);
			transaction.commit();
			session.close();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Student getStudentById(int id) {
		
		session = HibernateUtils.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		try {
			Student student = (Student) session.createQuery("from Student where id = '"+id+"'").getSingleResult();
			transaction.commit();
			session.close();
			return student;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteStudent(int id) {

		session = HibernateUtils.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		try {
			Student student = session.get(Student.class, id);
			session.delete(student);
			transaction.commit();
			session.close();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public List<Student> pagination(int firstPage, int lastPage) {

		try {
			
			session = HibernateUtils.getSessionFactory().openSession();
			transaction  = session.beginTransaction();
			
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("FROM Student");
			query.setFirstResult(firstPage);
			query.setMaxResults(lastPage);
			
			@SuppressWarnings("unchecked")
			List<Student> studentList = query.list();
			return studentList;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

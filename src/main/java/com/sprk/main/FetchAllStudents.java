package com.sprk.main;

import java.util.List;

import com.sprk.connection.CreateConnection;
import com.sprk.dao.StudentDao;
import com.sprk.dao.StudentDaoImpl;
import com.sprk.entity.Student;

public class FetchAllStudents {
	
	public static void main(String[] args) {
		
		try {
			StudentDao studentDao = new StudentDaoImpl(CreateConnection.getConnection());
			
			List<Student> students = studentDao.getAllStudents();
			
			for(Student tempStudent:students) {
				System.out.println("~~~Student Info~~~");
				System.out.println(tempStudent);
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
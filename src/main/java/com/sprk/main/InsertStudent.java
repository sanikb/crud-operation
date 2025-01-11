package com.sprk.main;


import java.util.Scanner;

import com.sprk.connection.CreateConnection;
import com.sprk.dao.StudentDao;
import com.sprk.dao.StudentDaoImpl;
import com.sprk.entity.Student;

public class InsertStudent {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			StudentDao studentDao = new StudentDaoImpl(CreateConnection.getConnection());
			
			String firstName;
			System.out.println("Enter  first name: ");
			firstName = sc.next();
			
			String lastName;
			System.out.println("Enter last name: ");
			lastName = sc.next();
			
			System.out.println("Enter gender: ");
			String gender = sc.next();
			
			System.out.println("Enter  email: ");
			String email = sc.next();
			
			 // Create a new Student object
            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setGender(gender);
            student.setEmail(email);
            
            // Save the student using the DAO
            int result = studentDao.saveStudent(student);
			if (result > 0) {
				System.out.println("Student saved successfully");
			} else {
				System.out.println("Something went wrong");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
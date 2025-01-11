package com.sprk.main;

import java.util.Scanner;

import com.sprk.connection.CreateConnection;
import com.sprk.dao.StudentDao;
import com.sprk.dao.StudentDaoImpl;
import com.sprk.entity.Student;

public class UpdateStudent {

	public static void main(String[] args) {
		int rollNo;

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter roll no: ");
		rollNo = sc.nextInt();

		try {
			StudentDao dao = new StudentDaoImpl(CreateConnection.getConnection());
			Student student = dao.getStudentByRollNo(rollNo);

			if (student.getRollNo() != 0) {
				StudentDao dao2 = new StudentDaoImpl(CreateConnection.getConnection());

				Student updatedStudent = new Student();
				updatedStudent.setRollNo(student.getRollNo());
				String firstName;
				System.out.println("Enter updated first name: ");
				firstName = sc.next();
//				
				String lastName;
				System.out.println("Enter updated last name: ");
				lastName = sc.next();
				
				System.out.println("Enter updated gender: ");
				String gender = sc.next();
				System.out.println("Enter updated email: ");
				String email = sc.next();
				updatedStudent.setFirstName(firstName);
				updatedStudent.setLastName(lastName);
				updatedStudent.setGender(gender);
				updatedStudent.setEmail(email);
				
				
				int result = dao2.updateStudent(updatedStudent);
				if (result > 0) {
					System.out.println("Student with roll no:" + rollNo + " updated successfully!");
				} else {
					System.out.println("Something bad happen");
				}
			} else {
				System.out.println("Student with roll no:" + rollNo + " not found!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
package com.sprk.main;

import java.util.Scanner;

import com.sprk.connection.CreateConnection;
import com.sprk.dao.StudentDao;
import com.sprk.dao.StudentDaoImpl;
import com.sprk.entity.Student;

public class FetchByRollNo {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			
			StudentDao dao = new StudentDaoImpl(CreateConnection.getConnection());
			System.out.println("enter rollNo :");
			int rollNo = sc.nextInt();
			Student student = dao.getStudentByRollNo(rollNo);

			if (student.getRollNo() != 0) {
				System.out.println(student);
			} else {
				System.out.println("Student not found!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
package com.sprk.main;

import java.util.Scanner;

import com.sprk.connection.CreateConnection;
import com.sprk.dao.StudentDao;
import com.sprk.dao.StudentDaoImpl;
import com.sprk.entity.Student;

public class DeleteStudent {

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
				int result = dao2.deleteStudentByRollNo(rollNo);
				if (result > 0) {
					System.out.println("Student with roll no:" + rollNo + " deleted successfully!");
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
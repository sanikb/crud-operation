package com.sprk.main;

import java.util.List;
import java.util.Scanner;

import com.sprk.connection.CreateConnection;
import com.sprk.dao.StudentDao;
import com.sprk.dao.StudentDaoImpl;
import com.sprk.entity.Student;

public class CrudApp {
	
	private static StudentDao getStudentDao() throws Exception{
	return new StudentDaoImpl(CreateConnection.getConnection());
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		try {
			int choice;
			do {
				System.out.println(
						"Welcome to Student App\n1. Register New Student\n2. View All Student\n3. View Student By Rollno\n4. Update Student\n5. Delete Student\n6. Exit");
				choice = sc.nextInt();
				sc.nextLine();
				switch(choice) {
				case 1:
					InsertStudent();
					break;
				case 2:
					getAllStudentList();
					break;
				case 3:
					getStudentByRollNo();
					break;
				case 4:
					updateStudent();
					break;
				case 5:
					deleteStudent();
					break;
				case 6:
					System.out.println("Thank you for usng App");
					System.exit(0);
					break;
				default:
					System.out.println("Choose correct option");
					break;
				}	
			}while(choice !=6);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void deleteStudent() throws Exception{
		int rollNo;

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter roll no: ");
		 rollNo = sc.nextInt();
		  StudentDao dao = getStudentDao();
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
		
	}

	private static void updateStudent() throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter roll number of the student to update:");
		int rollNo = sc.nextInt();
		 sc.nextLine();
		 StudentDao dao = getStudentDao();
		 Student updatedStudent = dao.getStudentByRollNo(rollNo);
		 if (updatedStudent != null) {
			 
		        // Prompt for new details
		        System.out.println("Enter new first name : "+ updatedStudent.getFirstName());
		        String firstName = sc.nextLine();
		        System.out.println("Enter new last name  : " + updatedStudent.getLastName());
		        String lastName = sc.nextLine();
		        System.out.println("Enter new email      : " + updatedStudent.getEmail());
		        String email = sc.nextLine();
		        System.out.println("Enter new gender     : " + updatedStudent.getGender());
		        String gender = sc.nextLine();

		        // Update the student object
		        updatedStudent.setFirstName(firstName);
		        updatedStudent.setLastName(lastName);
		        updatedStudent.setEmail(email);
		        updatedStudent.setGender(gender);
		        dao.getAllStudents();

		        // Perform the update operation
		        int result = dao.updateStudent(updatedStudent);
		        if (result > 0) {
		            System.out.println("Student with roll number " + rollNo + " updated successfully!");
		        } else {
		            System.out.println("Failed to update the student. Please try again.");
		        }
		    } else {
		        System.out.println("Student with roll number " + rollNo + " not found!");
		    }
		 
	}

	private static void getStudentByRollNo() throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.println("enter rollNo :");
		int rollNo = sc.nextInt();
		StudentDao dao = getStudentDao();
		Student student = dao.getStudentByRollNo(rollNo);

		if (student.getRollNo() != 0) {
			System.out.println(student);
		} else {
			System.out.println("Student not found!");
		}
		
	}

	private static void getAllStudentList() throws Exception {
		
		StudentDao dao = getStudentDao();
		List<Student> allStudent = dao.getAllStudents();
		for(Student tempStudent:allStudent) {
			System.out.println("\n"+tempStudent+"\n");
		}
	}

	private static void InsertStudent() throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter first name:");
		String firstName = sc.next();
		System.out.println("Enter last name:");
		String lastName = sc.next();
		System.out.println("Enter gender:");
		String gender = sc.next();
		System.out.println("Enter email:");
		String email = sc.next();
		
		Student student = new Student(0,firstName,lastName,email,gender);
		StudentDao dao = getStudentDao();
		int res = dao.saveStudent(student);
		if(res>0) {
			System.out.println("Student Saved Successfully\n\n");
			getAllStudentList();
		}else {
			System.out.println("Something bad happed");
		}
		
	}

}

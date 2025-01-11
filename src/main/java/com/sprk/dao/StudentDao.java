package com.sprk.dao;

import java.sql.SQLException;
import java.util.List;

import com.sprk.entity.Student;

public interface StudentDao {
	
	List<Student> getAllStudents() throws SQLException;

	int saveStudent(Student student) throws Exception;
	
	Student getStudentByRollNo(int rollNo) throws Exception;

	int deleteStudentByRollNo(int rollNo) throws Exception;
	
	int updateStudent(Student student) throws Exception;
}

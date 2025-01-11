package com.sprk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sprk.entity.Student;

public class StudentDaoImpl implements StudentDao {

	private Connection conn;

	public StudentDaoImpl(Connection conn) {
		this.conn = conn;

	}

	@Override
	public List<Student> getAllStudents() throws SQLException {
		String sql = "select * from student";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<Student> students = new ArrayList<>();

		while (rs.next()) {
			Student student = new Student();
			student.setRollNo(rs.getInt(1));
			student.setFirstName(rs.getString(2));
			student.setLastName(rs.getString(3));
			student.setEmail(rs.getString(4));
			student.setGender(rs.getString(5));

			students.add(student);
		}

		closeAll(ps, rs, conn);
		return students;
	}

	@Override
	public int saveStudent(Student student) throws Exception {
		String sql = "insert into student(firstName,lastName,email,gender) values (?,?,?,?)";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, student.getFirstName());
		ps.setString(2, student.getLastName());
		ps.setString(3, student.getEmail());
		ps.setString(4, student.getGender());

		int result = ps.executeUpdate();
		closeAll(ps, null, conn);
		return result;
	}

	@Override
	public Student getStudentByRollNo(int rollNo) throws Exception {
		String sql = "select * from student where rollNo = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, rollNo);
		ResultSet rs = ps.executeQuery();

		Student student = new Student();
		if (rs.next()) {
			student.setRollNo(rs.getInt(1));
			student.setFirstName(rs.getString(2));
			student.setLastName(rs.getString(3));
			student.setEmail(rs.getString(4));
			student.setGender(rs.getString(5));

		}

		closeAll(ps, rs, conn);
		return student;
	}

	@Override
	public int deleteStudentByRollNo(int rollNo) throws Exception {
		String sql = "delete from student where rollNo = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1,rollNo);
		int result = ps.executeUpdate();
		
		return result;
	}

	private void closeAll(PreparedStatement ps, ResultSet rs, Connection conn) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (conn != null) {
			conn.close();
		}

	}

	@Override
	public int updateStudent(Student student) throws Exception {
		String sql = "update student set firstName = ?, lastName = ?, email = ?, gender = ? where rollNo = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
//		
		ps.setString(1, student.getFirstName());
		ps.setString(2, student.getLastName());
		ps.setString(3, student.getEmail());
		ps.setString(4, student.getGender());
		ps.setInt(5, student.getRollNo());

		int result = ps.executeUpdate();
		closeAll(ps, null, conn);
		return result;

	}}
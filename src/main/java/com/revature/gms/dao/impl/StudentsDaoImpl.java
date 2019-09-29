package com.revature.gms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.gms.dao.StudentsDao;
import com.revature.gms.exception.DBException;
import com.revature.gms.model.Departments;
import com.revature.gms.model.Students;
import com.revature.gms.model.Users;
import com.revature.gms.util.DbUtils;

public class StudentsDaoImpl implements StudentsDao{
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	public List<Students> viewAllStudents()
	{
		List<Students> studentsList=new ArrayList<Students>();
		try {
			connection = DbUtils.getConnection();
			String sql = "select s.regno,s.name,s.fathername,d.id,d.name,s.dateofbirth,s.address,s.active from students as s, departments as d where s.department=d.id order by s.regno;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Departments departments=new Departments();
				Students students=new Students();
				
				students.setName(resultSet.getString("s.name"));
				students.setRegistrationNumber(resultSet.getInt("s.regno"));
				students.setFatherName(resultSet.getString("s.fathername"));
				departments.setId(resultSet.getInt("d.id"));
				departments.setName(resultSet.getString("d.name"));
				students.setDepartment(departments);
				students.setDateOfBirth(""+resultSet.getDate("s.dateofbirth"));
				students.setAddress(resultSet.getString("s.address"));
				students.setActive(resultSet.getBoolean("s.active"));
				studentsList.add(students);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to view marks", e);
		} finally {
			DbUtils.close(connection, preparedStatement);
		}
		return studentsList;
		
	}

	public Students checkStudentById(int studentId) {
		Departments departments=new Departments();
		Students students=new Students();
		try {
			connection = DbUtils.getConnection();
			String sql = "select s.regno,s.name,s.fathername,d.id,d.name,s.dateofbirth,s.address,s.active from students as s, departments as d where s.regno=? and s.department=d.id order by s.regno";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, studentId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				
				students.setName(resultSet.getString("s.name"));
				students.setRegistrationNumber(resultSet.getInt("s.regno"));
				System.out.println("student registration number:"+resultSet.getInt("s.regno"));
				students.setFatherName(resultSet.getString("s.fathername"));
				departments.setId(resultSet.getInt("d.id"));
				departments.setName(resultSet.getString("d.name"));
				students.setDepartment(departments);
				students.setDateOfBirth(""+resultSet.getDate("s.dateofbirth"));
				students.setAddress(resultSet.getString("s.address"));
				students.setActive(resultSet.getBoolean("s.active"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to view marks", e);
		} finally {
			DbUtils.close(connection, preparedStatement);
		}
		return students;
	}
	
	public int insert(Students students) {
		int id = 0;
		try {
			System.out.println(students.getDateOfBirth());

			connection = DbUtils.getConnection();
			String sql = "insert into students (name,fathername,department,dateofbirth,address) values(?,?,?,str_to_date(?,'%Y-%m-%d'),?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, students.getName());
			preparedStatement.setString(2, students.getFatherName());
			preparedStatement.setInt(3, students.getDepartment().getId());
			preparedStatement.setString(4, ""+students.getDateOfBirth());
			
			preparedStatement.setString(5, students.getAddress());
			int rows = preparedStatement.executeUpdate();
			if(rows==1) {
				id=findIdByNames(students.getName(),students.getFatherName());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to insert",e);
		}
		finally {
			DbUtils.close(connection, preparedStatement);
		}
		return id;
		}

	private int findIdByNames(String name, String fatherName) {
		int id = 0;
		try {
			connection = DbUtils.getConnection();
			String sql = "select regno from students where name=? and fathername=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, fatherName);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				id=resultSet.getInt("regno");
			}
		}
		catch(Exception e) {
			throw new DBException("Unable to find id by mail",e);
		}
		finally {
			DbUtils.close(connection, preparedStatement,resultSet);
		}
		return id;

	}
	public List<Students> viewStudents() {
		List<Students> studentsList=null;
		try {
			studentsList=new ArrayList<Students>();
			connection = DbUtils.getConnection();
			String sql = "select s.regno,s.name,s.fathername,d.id,d.name,s.dateofbirth,s.address,s.active from students as s, departments as d where s.department=d.id order by s.id";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Departments departments=new Departments();
				Students students=new Students();
				
				students.setName(resultSet.getString("s.name"));
				students.setRegistrationNumber(resultSet.getInt("s.regno"));
				students.setFatherName(resultSet.getString("s.fathername"));
				departments.setId(resultSet.getInt("d.id"));
				departments.setName(resultSet.getString("d.name"));
				students.setDepartment(departments);
				students.setDateOfBirth(""+resultSet.getDate("s.dateofbirth"));
				students.setAddress(resultSet.getString("s.address"));
				students.setActive(resultSet.getBoolean("s.active"));
				studentsList.add(students);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to view marks", e);
		} finally {
			DbUtils.close(connection, preparedStatement);
		}
		return studentsList;
	}
	
	
}

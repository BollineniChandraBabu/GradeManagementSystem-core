package com.revature.gms.services;

import java.util.List;
import java.util.Scanner;


import com.revature.gms.dao.impl.GradesDaoImpl;
import com.revature.gms.dao.impl.MarksDaoImpl;
import com.revature.gms.dao.impl.UsersDaoImpl;
import com.revature.gms.exception.DBException;
import com.revature.gms.exception.ServiceException;
import com.revature.gms.model.Grades;
import com.revature.gms.model.Marks;
import com.revature.gms.model.Students;
import com.revature.gms.util.Logger;

public class MarksServices {
Marks marks=new Marks();
MarksDaoImpl marksDAOImpl = new MarksDaoImpl();

UsersDaoImpl usersDaoImpl=new UsersDaoImpl();
StudentServices studentServices=new StudentServices();
Grades grades=new Grades();
List<Marks> marksList;
Scanner scanner=new Scanner(System.in);
	public List<Marks> findMaxMarks() throws ServiceException {
		try {
			marksList = marksDAOImpl.findMaxMarks();
		} catch (DBException e) {
			Logger.error(e);
			throw new ServiceException("Unable to login");
			
		}
		return marksList;
	}
	public List<Marks> viewAllMarks() throws ServiceException {
		try {
			marksList = marksDAOImpl.viewAllMarks();	
			
			
			
			
		}
			catch (DBException e) {
				Logger.error(e);
				throw new ServiceException("Unable to View" +e);	
			}
		return marksList;

	}
	public List<Marks> viewMarksByGrade(char grade) throws ServiceException {
		try {
			List<Students> studentsList=studentServices.getStudents();
			GradesDaoImpl gradesDaoImpl=new GradesDaoImpl();
			List<Marks> marksList;
			Logger.info("-------------------------------------------------------------------------------");
			for(Students students:studentsList)
			{
				int average=marksDAOImpl.getAverage(students.getRegistrationNumber());
				String grade1=gradesDaoImpl.getGrade(average);
				if(grade1.trim().charAt(0)==grade)
				{
					marksList=marksDAOImpl.getMarksById(students.getRegistrationNumber());
					for(Marks marks:marksList)
					{
						Logger.info("\nStudent Id :"+marks.getStudent().getRegistrationNumber()+"  Student Name :"+marks.getStudent().getName()+"\n");
						break;
					}
					for(Marks marks:marksList)
					{
						Logger.info(marks.getSubjects().getName() +":"+marks.getMarks() +"\t");
					}
					Logger.info("\n-------------------------------------------------------------------------------");					
				}
			}
		} catch (DBException e) {
			throw new ServiceException("Unable to View" +e);
			
		}
		return marksList;
	}
	public Marks checkAvailability(int studentId,int subjectId) throws ServiceException {
			return marksDAOImpl.checkAvailability(studentId,subjectId);
	}
	public boolean insertOrUpdate(Marks marks) throws ServiceException {
		boolean result=false;
		try 
		{
			result=marksDAOImpl.insertMarks(marks);
			
		}
		catch (DBException e) {
			e.printStackTrace();
			result=marksDAOImpl.updateMarks(marks);
			throw new ServiceException("Unable to View" +e);
		}
		return result;
	}
	
	
	public int getNumber() 
	{
		boolean result=false;
		int number = 0;
		while(result!=true) {
		try{
			String s=scanner.next();
			number=Integer.parseInt(s);
			result=true;
			break;
		}
		catch(Exception e) 
		{
			Logger.error("enter correct choice....");
			result=false;
		}}
		return number;
	}
	public Marks getMarks(int i)
	{
		return marks;

	}
public int marksValidator() {
	
	int number;
	while(true)
	{
		number=getNumber();
		if(number>=0 && number<=100 )
		{
			break;
		}
		else 
		{
			Logger.error("marks should be >=0 and <= 100");
		}
		
	}
	return number;
	}
public Object viewBySubjectCode(int subjectCode) {
	GradesDaoImpl gradesDaoImpl=new GradesDaoImpl();

	marksList = marksDAOImpl.viewBySubjectCode(subjectCode);
	Logger.info("------------------------------------------------------");
	for(Marks marks:marksList) {
		Logger.info("subject code :"+marks.getSubjects().getId());
		Logger.info("subject name :"+marks.getSubjects().getName());
		break;
	}
	Logger.info("ID\tName\tmarks\tGrade");
	for(Marks marks1:marksList) {
	Logger.info(marks1.getStudent().getId()+"\t"+marks1.getStudent().getName()+"\t"+marks1.getMarks()+"\t"+gradesDaoImpl.getGrade(marks1.getMarks()));
	}
	Logger.info("------------------------------------------------------");
return marksList;
	
}
public List<Marks> viewBySubjectName(String subjectName) {
	GradesDaoImpl gradesDaoImpl=new GradesDaoImpl();
	marksList = marksDAOImpl.viewBySubjectName(subjectName);
	Logger.info("------------------------------------------------------");
	
	Logger.info("ID\tName\tsubject code\tsubject name\tmarks\tGrade");
	for(Marks marks1:marksList) {
	Logger.info(marks1.getStudent().getId()+"\t"+marks1.getStudent().getName()+"\t\t"+marks1.getSubjects().getId()+"\t    "+marks1.getSubjects().getName()+"\t"+marks1.getMarks()+"\t"+gradesDaoImpl.getGrade(marks1.getMarks()));
	}
	Logger.info("------------------------------------------------------");
return marksList;
	
}
public boolean checkSubjectById(int subjectId) {
	return marksDAOImpl.checkSubjectById(subjectId);
}

}

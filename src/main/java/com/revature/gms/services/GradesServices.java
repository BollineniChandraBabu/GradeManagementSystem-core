package com.revature.gms.services;

import java.util.List;
import java.util.Scanner;

import com.revature.gms.dao.impl.GradesDaoImpl;
import com.revature.gms.exception.DBException;
import com.revature.gms.exception.ServiceException;

import com.revature.gms.model.Grades;
import com.revature.gms.util.Logger;
import com.revature.gms.validator.GradeValidator;

public class GradesServices {
	Scanner scanner=new Scanner(System.in);
	Grades grades=new Grades();
	GradeValidator gradeValidator=new GradeValidator();
	GradesDaoImpl gradeDaoImpl = new GradesDaoImpl();

	boolean result =false;
			public List<Grades> viewGrades() throws ServiceException {
				List<Grades> gradesList=null;
				try {
					gradesList=gradeDaoImpl.viewGrades();
					
				} catch (DBException e) {
					Logger.error(e);
					throw new ServiceException("Unable to view");
					
				}
				return gradesList;
			}
			public boolean checkGradeAvailability(char grade) throws ServiceException {
				try {
					result=gradeDaoImpl.checkGradeAvailability(grade);
				} catch (DBException e) {
					Logger.error(e);
					throw new ServiceException("Unable to view");
				}
				return result;
			}
			public char getGrade() throws ServiceException
			{
				char grade=0;
				while(true) {
				Logger.info("enter one grade to view marks....");
				grade=scanner.next().trim().charAt(0);
				boolean result=gradeDaoImpl.checkGradeAvailability(grade);
				if(result) {break;}
				else
				{
					Logger.error("grade not available....");
					Logger.error("available grades.....\n------------------------------");
					viewGrades();
					Logger.error("------------------------------");
				}
				}
				return grade;
			}
			public boolean updateGradeRange(Grades grades) throws ServiceException {
				
				GradesDaoImpl gradesDaoImpl=new GradesDaoImpl();
				
				return gradesDaoImpl.updateGrade(grades);
			
			}
				//AdminLogin adminLogin=new AdminLogin();
				//adminLogin.adminLogin();
			public String getGrade(int mark) {
				GradesDaoImpl gradesDaoImpl=new GradesDaoImpl();
				return gradesDaoImpl.getGrade(mark);
			}
			
			
}

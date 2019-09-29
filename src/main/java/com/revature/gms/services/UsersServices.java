package com.revature.gms.services;

import java.util.List;
import java.util.Scanner;

import com.revature.gms.dao.UsersDao;
import com.revature.gms.dao.impl.UsersDaoImpl;
import com.revature.gms.exception.DBException;
import com.revature.gms.exception.ServiceException;

import com.revature.gms.model.Users;
import com.revature.gms.util.Logger;
import com.revature.gms.validator.UsersValidator;

public class UsersServices {
	UsersValidator usersValidator=new UsersValidator();
	UsersDaoImpl userDAOImpl = new UsersDaoImpl();
	MarksServices marksServices=new MarksServices();
	Scanner scanner=new Scanner(System.in);
Users users=new Users();

	public Users login(String email, String password) throws ServiceException {
		users = null;
		try {
			users = userDAOImpl.login(email,password);
		} catch (DBException e) {
			Logger.error(e);
			throw new ServiceException("Unable to login");
			
		}
		return users;

	}

	public int insert(Users users) throws ServiceException {
		int id;
		try {
			
			id = userDAOImpl.insert(users);
		} catch (DBException e) {
			Logger.error(e);
			throw new ServiceException("Unable to insert new users");	
		}
		return id;
	}
	public void validatingUser(Users users) throws ServiceException
	{
		if (users!= null) {
			if (users.isRoles()==true) {
				
			} else if (users.isRoles()==false) {
				
			}
		} else {
			Logger.info("Invalid Login Credentials");	
		}
	}

	public boolean validateName(String name) {
		boolean result=usersValidator.validateName(name);
		return result;
	}

	public String getEmail() {
		boolean result=false;
		String email;
		while(true) {
			Logger.info("enter your email");
			email=scanner.next();
			result=usersValidator.emailValidator(email);
			if(result==true) { 
			break;
			}
			else { Logger.error("please enter valid email id....");}
			}
		return email;
	}

	public boolean validateDate(String sDate) {
		boolean result=usersValidator.dateValidator(sDate);
		return result;
	}

	public boolean checkByMailId(String mailId) throws DBException
	{boolean result=false;
		try {
			result = userDAOImpl.checkByMailId(mailId);
		} catch (DBException e) {
			Logger.error(e);
			throw new DBException("Unable to insert new users");
			
		}
		return result;
	}
	
	
	public boolean activateAccount(int eid, String mail, String password) throws ServiceException {
		return userDAOImpl.activateAccount(mail,eid,password);
	}

	public int findIdByMail(String string) {
		return userDAOImpl.findIdByMail(string);
	}

	public  List<Users> viewAllUsers() {
		return userDAOImpl.viewAllUsers();
	}
	
}

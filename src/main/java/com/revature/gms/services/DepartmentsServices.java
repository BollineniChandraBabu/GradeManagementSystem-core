package com.revature.gms.services;

import java.util.List;

import com.revature.gms.dao.impl.DepartmentsDaoImpl;
import com.revature.gms.model.Departments;
import com.revature.gms.util.Logger;

public class DepartmentsServices {
DepartmentsDaoImpl departmentsDaoImpl=new DepartmentsDaoImpl();

public boolean checkDepartment(int id) 
{boolean result=false;
result=departmentsDaoImpl.checkDepartment(id);
return result;	
}
public List<Departments> viewDepartments()
{
List<Departments> departmentsList=departmentsDaoImpl.viewDepartments();
	for(Departments departments:departmentsList)
	{
		Logger.info(departments.getId() +":" +departments.getName());
	}
	return departmentsList;
}


}

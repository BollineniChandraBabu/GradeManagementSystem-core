package com.revature.gms.model;

public class Marks {
private int id;
private Students student;
private Subjects subjects;
private int marks;
private String grade;
public Students getStudent() {
	return student;
}
public void setStudent(Students student) {
	this.student = student;
}
public Subjects getSubjects() {
	return subjects;
}
public void setSubjects(Subjects subjects) {
	this.subjects = subjects;
}
public int getMarks() {
	return marks;
}
public void setMarks(int marks) {
	this.marks = marks;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getGrade() {
	return grade;
}
public void setGrade(String grade) {
	this.grade = grade;
}


}

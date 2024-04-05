//Name: Abigail Allen
//Student Id: 500956726

import java.util.ArrayList;

// Make class Student implement the Comparable interface
// Two student objects should be compared by their name
public class Student //COMPLETED
{
  private String name;
  private String id;
  public  ArrayList<CreditCourse> courses;
  
  
  public Student(String name, String id)
  {
	 this.name = name;
	 this.id   = id;
	 courses   = new ArrayList<CreditCourse>();
  }
  
  //REturns the Id of a student
  public String getId()
  {
	  return id;
  }
  
  //Returns the name of a student
  public String getName()
  {
	  return name;
  }
  
  // add a credit course to list of courses for this student
  public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade) //DONE
  {
	// create a CreditCourse object
	  CreditCourse newStudent = new CreditCourse(courseName, courseCode,descr, format, sem, grade);
	  
	  // set course active
	  newStudent.setActive();
	  
	  // add to courses array list
	  courses.add(newStudent);
  }

  
  // Prints a student transcript
  // Prints all completed (i.e. non active) courses for this student (course code, course name, 
  // semester, letter grade
  public void printTranscript() //DONE
  {
	  String grade;
	  //Search through the students courses to get the grade of each one 
	  for (int i=0; i<courses.size(); i++)
	  {
		  //Gets the grade of the student from CreditCourse class and prints it with the rest of the information
		  grade=courses.get(i).convertNumericGrade(courses.get(i).grade);
		  System.out.println(courses.get(i).getInfo()+" Grade "+grade);
	  }
  }
  
  // Prints all active courses this student is enrolled in
  // see variable active in class CreditCourse
  public void printActiveCourses() //DONE
  {
	  //Iterate through the student's courses, and only print the student's courses that are active
	  for (int i=0; i<courses.size(); i++)
	  {
		  if (courses.get(i).active)
			  System.out.println(courses.get(i).getDescription());
	  }
  }
  
  // Drop a course (given by courseCode)
  public void removeActiveCourse(String courseCode) //DONE
  {
	  //Iterate through the student's courses
	  for (int i=0; i<courses.size(); i++)
	  {
		// Find the credit course in courses arraylist above and remove it
		// only remove it if it is an active course
		  if (courses.get(i).getCode().equalsIgnoreCase(courseCode))
		  {
			  //If it is found set is as inactive and remove it
			  courses.get(i).setInactive();
			  courses.remove(i);
		  }
	  }
  }
  
  //Return the toString method for this student that says their Id and their Name
  public String toString()
  {
	  return "Student ID: " + id + " Name: " + name;
  }
  
  // Override equals method inherited from superclass Object
  public boolean equals(Object other) //DONE
  {
	  //Cast other parameter to a local Student reference variable so it can be used
	  Student newOther = (Student) other;
	  
	  // if student names are equal *and* student ids are equal (of "this" student
	  // and "other" student) then return true
	  if (this.name.equalsIgnoreCase(newOther.name)==true && this.id.equals(newOther.id)==true)
	  {
		  //Return true if student names and student id's are the same
		  return true;
	  }
	  
	  //Otherwise return false if the names and id's are not equal
	  return false;
  }
}

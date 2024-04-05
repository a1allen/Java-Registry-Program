import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Active University Course
//An active course object is a course that is currently operating
public class ActiveCourse extends Course
{
	//This class has an arrayList named "students" that has the name and Id of each student enrolled in each active course
	private ArrayList<Student> students; 
	//Semester gives what time of the year that the course is running at
	private String semester;
	public int lectureStart;
	public int lectureDuration;
	public String lectureDay;
	//Variable used to determine if the course has been added to the schedule or not
	public boolean added=false;
	
   public ActiveCourse(String name, String code, String descr, String fmt,String semester,ArrayList<Student> students)
   {
	   super(name,code,descr,fmt);
	   this.semester=semester;
	//Copy original students array list being passed in into new arraylist of students
	   this.students = new ArrayList<Student>(students);
   }

   
   public String getSemester()
   {
	   return semester;
   }
   
   // Prints the students in this course  (name and student id)
   public void printClassList()//DONE
   {
	   //Iterate through each student in the active course and print out their name and id ONLY if the course is active 
	   for (int i = 0; i < students.size(); i++)
	   {
		   System.out.println("Student ID: " + students.get(i).getId() + " Name: " + students.get(i).getName() );   
	   }
   }
   
   // Prints the grade of each student in this course (along with name and student id)
   // 
   public void printGrades()//DONE
   {
	 //Iterate through each student in the active course and print out their name, id and their grade
	   for (int i = 0; i < students.size(); i++)
	   {
		   System.out.println(students.get(i).getId() +" " + students.get(i).getName()+ " "+ getGrade(students.get(i).getId()));   
	   }
   }
   
   // Returns the numeric grade in this course for this student
   // If student not found in course, return 0 
   public double getGrade(String studentId)//DONE
   {
	   //Iterate through the students in the active course
	   for (int i=0;i<students.size();i++)
	   {
		   //Find the course code in credit course that matches this course
		   if (students.get(i).getId().equals(studentId))
		   {
			   //Iterate through the student's courses to find the course that is equal to the course in this class
			   for (int j=0;j<students.get(i).courses.size();j++)
			   {
				   //If the course is found, return the grade of the student from the credit course class
				   if (students.get(i).courses.get(j).getCode().equalsIgnoreCase(getCode()))
				   {
					   return (students.get(i).courses.get(j).grade);
				   }
			   }
		   }
	   }
	   //Return 0 if not found
	   return 0;
   }
   
   // Returns a String containing the course information as well as the semester and the number of students 
   // enrolled in the course
   // must override method in the superclass Course and use super class method getDescription()
   public String getDescription()//DONE
   {
	   //Return the description of the course from the courses superclass using inheritance
	   return super.getDescription()+" "+semester+" Enrolment: "+students.size()+"\n";
   }
   
   //Added method into active course to remove student from Students arraylist
   public void removeStudent(String id)
   {
	   for (int i=0;i<students.size();i++)
	   {
		   if (students.get(i).getId().equals(id))
		   {
			   students.remove(i);
		   }
	   }
   }
   
   public void addStudent(String id, Student element)
   {
	   //Add the student to the active course
	   students.add(element);   
   }
    
   
   // Sort the students in the course by name using the Collections.sort() method with appropriate arguments
   // Make use of a private Comparator class below
   public void sortByName()
   {
	   //Call the sort class that will be used to sort through the names of the students in arrayList
	   Collections.sort(students, new NameComparator());
   }
   
   
   // Fill in the class so that this class implement the Comparator interface
   // This class is used to compare two Student objects based on student name
   private class NameComparator implements Comparator<Student>
   {
	   //Compare student 1 and student 2 names with each other
	public int compare(Student o1, Student o2) 
	{
		//If name 1 is seen as less than name 2, return negative 1
		if (o1.getName().compareToIgnoreCase(o2.getName())<o2.getName().compareToIgnoreCase(o1.getName()))
		{
			return -1;
		}
		//If name 1 is seen as more than name 2, return 1
		if (o1.getName().compareToIgnoreCase(o2.getName())>o2.getName().compareToIgnoreCase(o1.getName()))
		{
			return 1;
		}
		//If the two Id's are equal to each other return 0
		return 0;
	}
   }
   
   // Sort the students in the course by student id using the Collections.sort() method with appropriate arguments
   // Make use of a private Comparator class below
   public void sortById()
   {
	 //Call the sort class that will be used to sort through the Id's of the students in arrayList
	   Collections.sort(students, new IdComparator());
   }
   
   // Fill in the class so that this class implement the Comparator interface
   // This class is used to compare two Student objects based on student id
   private class IdComparator implements Comparator<Student>
   {
	   //Compare the Id's of student 1 and student 2 as objects
	   public int compare(Student o1, Student o2) 
		{

		   //If Id 1 is less than Id 2, return negative 1
			if (o1.getId().compareToIgnoreCase(o2.getId())<o2.getId().compareToIgnoreCase(o1.getId()))
			{
				return -1;
			}
			//If Id 1 is more than Id 2, return 1
			if (o1.getId().compareToIgnoreCase(o2.getId())>o2.getId().compareToIgnoreCase(o1.getId()))
			{
				return 1;
			}
			//If the two Id's are equal to each other return 0
			return 0;
		}
   }
}

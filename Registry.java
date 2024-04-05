import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

//Registry is the class that contains a treemap of all Students in the university and ActiveCourse is their courses
public class Registry
{
	//Gives access to courses of a student instance
   TreeMap<String,Student> students = new TreeMap<String,Student>();
   //Gives access to students of a course instance
   TreeMap<String,ActiveCourse> courses = new TreeMap<String,ActiveCourse>();
   
   public Registry() throws IOException
   {
	   //Read student names from a file with exception handling
		   //Get the new file object
		   Scanner scanner=new Scanner(new File("students.txt"));
		   int numLine=0;
		   int words=0;
		   
		   //Read the file line by line to see if the information is correctly entered 
		   while (scanner.hasNextLine())
		   {
			   //Code used to count the number of words and lines
			   String line=scanner.nextLine();
			   //StringTokenizer used to check the amount of words that are in this line
			   StringTokenizer st = new StringTokenizer(line) ; 
			   words+=st.countTokens();
			   numLine++;
			   
			   //Sees if there are more or less than two additional words on each line
			   //If less that two additional words were in one line or more than 2 additional words are
			   //in one line an exception will be thrown
			   if (words!=numLine*2)
				{
					throw new IOException();
				}
			   
			   //Code used to set the name and id that were collected from the line in the text file
			   String name=st.nextToken();
			   String id=st.nextToken();
			   
			   //Make a student instance
			   Student student=new Student(name,id);
			   
			   //Add student to the TreeMap
			   students.put(id,student); 
		   }
		   
		   //-------------------------------------------------
		   //ADD STUDENTS TO THEIR COURSES
		   
		// sort the students alphabetically - see class Student
		   
		   ArrayList<Student> list = new ArrayList<Student>();
		   int count=1;
		   
		   // Add some active courses with students
		   //CPS209
		   String courseName = "Computer Science II";
		   String courseCode = "CPS209";
		   String descr = "Learn how to write complex programs!";
		   String format = "3Lec 2Lab";
		   
		   for (String key : students.keySet())
		   {
			   if (count>=2 && count <=4)
			   {
				   list.add(students.get(key));
				// Add course to student list of courses
				   students.get(key).addCourse(courseName,courseCode,descr,format,"W2020", 0); 
			   }
			   count++;
		   } 
		   ActiveCourse addActive = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
		   //Add element to the TreeMap
		   courses.put(courseCode,addActive);
		  
		   // CPS511
		   list.clear();
		   courseName = "Computer Graphics";
		   courseCode = "CPS511";
		   descr = "Learn how to write cool graphics programs";
		   format = "3Lec";
		   
		   count=1;
		   for (String key : students.keySet())
		   {
			   if (count==1 || count==5 || count==6)
			   {
				   list.add(students.get(key));
				// Add course to student list of courses
				   students.get(key).addCourse(courseName,courseCode,descr,format,"F2020", 0); 
			   }
			   count++;
		   } 
		   addActive = new ActiveCourse(courseName,courseCode,descr,format,"F2020",list);
		   //Add element to the TreeMap
		   courses.put(courseCode,addActive);

		   
		   // CPS643
		   list.clear();
		   courseName = "Virtual Reality";
		   courseCode = "CPS643";
		   descr = "Learn how to write extremely cool virtual reality programs";
		   format = "3Lec 2Lab";
		   
		   count=1;
		   for (String key : students.keySet())
		   {
			   if (count==1 || count==2 || count==4 || count==6)
			   {
				   list.add(students.get(key));
				// Add course to student list of courses
				   students.get(key).addCourse(courseName,courseCode,descr,format,"W2020", 0); 
			   }
			   count++;
		   } 
		   addActive = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
		   //Add element to the TreeMap
		   courses.put(courseCode,addActive);
		   
		   
		   // CPS706
		   	list.clear();
		    courseName = "Computer Networks";
		    courseCode = "CPS706";
		    descr = "Learn about Computer Networking";
		    format = "3Lec 1Lab";
		    
		    addActive = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
		    courses.put(courseCode,addActive);
		    
		    
		    // CPS616
		    list.clear();
		    courseName = "Algorithms";
		    courseCode = "CPS616";
		    descr = "Learn about Algorithms";
		    format = "3Lec 1Lab";
		    
		    addActive = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
		    courses.put(courseCode,addActive);


   }
   
   // Add new student to the registry (students arrayList above) 
   public boolean addNewStudent(String name, String id) //DONE
   {
	   // Create a new student object
	   Student newStudent = new Student(name,id);
	   for (String key : students.keySet())
	   {
		   // check to ensure student is not already in registry
		  if (newStudent.equals(students.get(key)))
		  {
			  //If the student is found, return false
			  return false;
		  }
	   }
	   
	   // if the student is not found, add them and return true
		   students.put(id,newStudent);
		   return true;
   }
   
   // Remove student from registry 
   public boolean removeStudent(String studentId) //DONE
   {
	   // Find student in students treemap
	   // If found, remove this student and return true
	   for (String key : students.keySet())
	   {
		   if (studentId.equals(key))
		   {
			   students.remove(key);
			   return true;
		   }
	   }
	   return false;
   }
   
   // Print all registered students
   public void printAllStudents()//DONE
   {
	   //Interate through all students in registry and print their information
	   for (String key : students.keySet())
	   {
		   System.out.println("ID: " + key + " Name: " + students.get(key).getName());   
	   }
   }
   
   
   // Given a studentId and a course code, add student to the active course
   public void addCourse(String studentId, String courseCode)//************************************
   {
	   
	   boolean found=false;
	   //Check if student has already taken this course or not
	   for (String key : students.keySet())
	   {
		   if (key.equals(studentId))
		   {
			   //Look at their credit course list to see if student has taken this course
			   for (int k=0;k<students.get(key).courses.size();k++)
			   {
				   if (students.get(key).courses.get(k).getCode().equalsIgnoreCase(courseCode))
				   {
					   found=true;
				   }
			   }
		   }
	   }
	   
	   //If the student does not have this course in their credit courses, add them to the course
	   if (!found)
	   {
		 //Add the student to the students array in ActiveCourse
		   //Iterate through all active courses to find the matching course code
		   String courseNum="";
		   
		   for (String key : courses.keySet())
		   {
			   if (key.equalsIgnoreCase(courseCode))
			   {
				   courseNum=key;
				   //Find the matching student Id from the student class
				   for (String key2 : students.keySet())
				   {
					   //Get the student object from the student class
					   if (key2.equals(studentId))
					   {
						   //Pass the student object to the method in ActiveCourse to use as a parameter in ActiveCourse
						   courses.get(key).addStudent(studentId, students.get(key2));
					   }
				   } 
			   }
		   }
		   
		   //Add the student to the courses array in the Student class
		   for (String key : students.keySet())
		   {
			   if (key.equals(studentId))
			   {
				   //Get necessary variables to pass to Student addCourse method
				   String courseName = courses.get(courseNum).getName();
				   //Uses an added method to ActiveCourse
				   String descr = courses.get(courseNum).getSentence();
				   String format = courses.get(courseNum).getFormat();
				   String sem = courses.get(courseNum).getSemester();
				   //Go to student method to addCourse
				   students.get(key).addCourse(courseName, courseCode, descr, format, sem, 0);
			   }
		   } 
	   }
	   else
	   {
		   System.out.println("Student has already taken this course.");
	   }
   }
                                                                    
   
   // Given a studentId and a course code, drop student from the active course
   public void dropCourse(String studentId, String courseCode)//************************************
   {
	   //Iterate through students in registry to find the student to be removed in registry
	   for (String key : students.keySet())
	   {
		   //If the student if found, remove them from the active course with the course code that was entered
		   if (key.equals(studentId))
		   {
			   students.get(key).removeActiveCourse(courseCode);
		   }
	   }
	   
	   //Find the active course
	   for (String key : courses.keySet())
	   {
		   //If the course is found remove the student from the course
		   if (key.equalsIgnoreCase(courseCode))
		   {
			   courses.get(key).removeStudent(studentId);
		   }
	   }
   }
   
   
   // Print all active courses
   public void printActiveCourses()//DONE
   {
	   for (String key : courses.keySet())
	   {
		   ActiveCourse ac = courses.get(key);
		   System.out.println(ac.getDescription());
	   }
   }
   
   
   // Print the list of students in an active course
   public void printClassList(String courseCode)//DONE
   {
	   boolean found=false;
	   
	   for (String key : courses.keySet())
	   {
		   //Once the active course if found, print the class list from a method in ActiveCourse
		   ActiveCourse ac = courses.get(key);
		   if (ac.getCode().equalsIgnoreCase(courseCode))
		   {
			   ac.printClassList();
			   found=true;
			   break;
		   }
	   }
	   
	   //If the course code was not found, display an error message to the user to let them know
	   if (!found)
	   {
		   System.out.println("This course code is not active or does not exist.");
	   }
   }
   
   
   // Given a course code, find course and sort class list by student name
   public void sortCourseByName(String courseCode)//DONE
   {
	   //Look for the Active course by courseCode
	   for (String key : courses.keySet())
	   {
		   //If the course code is found, called the sortbyName method in ActiveCourse to sort the class by name
		   if (key.equalsIgnoreCase(courseCode))
		   {
			   courses.get(key).sortByName();
		   }
	   }
   }
   
   
   // Given a course code, find course and sort class list by student name
   public void sortCourseById(String courseCode)//DONE
   {
	   //Look for active course by course code
	   for (String key : courses.keySet())
	   {
		 //If the course code is found, called the sortbyId method in ActiveCourse to sort the class by Id
		   if (key.equalsIgnoreCase(courseCode))
		   {
			   courses.get(key).sortById();
		   }
	   }
   }
   
   
   // Given a course code, find course and print student names and grades
   public void printGrades(String courseCode)//DONE
   {
	   //Iterate through the active courses
	   for (String key : courses.keySet())
	   {
		   //If the active course is found, call the method in active course to print the grade of each student
		   if (key.equalsIgnoreCase(courseCode))
		   {
			   courses.get(key).printGrades();
		   }
	   }
   }
   
   
   // Given a studentId, print all active courses of student
   public void printStudentCourses(String studentId)//DONE
   {
	   //Look for the student in the student class
	   for (String key : students.keySet())
	   {
		   //if the student is found, class the method in the Student class to print the active courses
		   if (key.equals(studentId))
		   {
			   students.get(key).printActiveCourses();
		   }
	   }
   }
   
   
   // Given a studentId, print all completed courses and grades of student
   public void printStudentTranscript(String studentId)//DONE
   {
	   boolean found=false;
	   //Find the student in the student class
	   for (String key : students.keySet())
	   {
		   //If the student is found, print all their active and inactive courses
		   if (key.equals(studentId))
		   {
			   //Set up an instance of the class to get access to the student class 
			   Student student = students.get(key);
			   student.printTranscript();
			   found=true;
			   break;
		   }
	   }

	   //If not found display an error message to the user to let them know
	   if (!found)
	   {
		   System.out.println("ID does not exist in registry.");
	   }
	   
   }
   
   // Given a course code, student id and numeric grade
   // set the final grade of the student
   public void setFinalGrade(String courseCode, String studentId, double grade)//DONE
   {
	   String idNum="";
	   int courseNum=-1;
	   
	   //Find the student in the registry
	   for (String key : students.keySet())
	   {
		   //If student is found then keep the index that the student was found at
		   if (key.equals(studentId))
		   {
			   idNum=key;
			   
		   }
	   }
	   
	   //If the iD index number was changed from -1 then the student exists, find the matching coursecode for the student
	   if (!idNum.equals(""))
	   {
		   for (int j=0;j<students.get(idNum).courses.size();j++)
		   {
			   //if the CourseCode is found and is an ACTIVE course, put the courseindex to the coursenum
			   if ((students.get(idNum).courses.get(j).getCode().equalsIgnoreCase(courseCode)) && students.get(idNum).courses.get(j).active)
			   {
				   courseNum=j;
			   }
		   }
	   }
	   else
	   {
		   //if not found print an error message
		   System.out.println("Student number does not exist in the registry");
		   return;
	   }
	   
	   //If the student exists and they are taking that course, set the final grade of the student
	   //and set the course as inactive
	   if (courseNum!=-1) 
	   {
		   students.get(idNum).courses.get(courseNum).grade=grade;
		   students.get(idNum).courses.get(courseNum).setInactive();
		   
	   }
	   else 
	   {
		   System.out.println("Student is currently not enrolled in this course");
	   }

   }  

}

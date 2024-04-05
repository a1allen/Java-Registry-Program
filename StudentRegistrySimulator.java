//Name: Abigail Allen
//Student Id: 500956726

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

//Main method for this program
public class StudentRegistrySimulator 
{
  public static void main(String[] args) throws IOException
  {
	  try 
	  {
	  Registry registry = new Registry();
	  Scheduler scheduler= new Scheduler(registry.courses);
	  
	  Scanner scanner = new Scanner(System.in);
	  System.out.print(">");
	  
	  while (scanner.hasNextLine())
	  {
		  String inputLine = scanner.nextLine();
		  if (inputLine == null || inputLine.equals("")) continue;
		  
		  Scanner commandLine = new Scanner(inputLine);
		  
		  //Command is the three letter word the user enters to specify the action they want to do
		  String command = commandLine.next();
		  
		  if (command == null || command.equals("")) continue;
		  
		//PRINT ALL STUDENTS IN REGISTRY
		  else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))
		  {
			  registry.printAllStudents();
		  }
		  
		  //QUIT
		  else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT"))
			  return;
		  
		  //ADDS A STUDENT TO THE REGISTRY
		  //EXPECTED INPUT: NAME, ID
		  else if (command.equalsIgnoreCase("REG"))//DONE****************************
		  {
			  //Declare variables that will be used beforehand
			  String name="";
			  boolean gotName=false;
			  boolean gotId=false;
			  
			  //Check for first null character
			  if (commandLine.hasNext())
			  {
				  //If not null, get the name, and set gotName to true
				  gotName=true;
				  name= commandLine.next();
				  //Check for second null character
				  if (commandLine.hasNext())
				  {
					  //If not null, set getId to true so that the code after can run
					  gotId=true;
				  }
				  else
				  {
					  System.out.println("Please enter an Id into the field");
				  }
			  }
			  else
			  {
				  System.out.println("Please enter a name into the field");
			  }
			  
			  //If a name was entered and an Id was entered, then the code can run and the information will be checked for validity
			  if (gotName==true && gotId==true)
			  {
				//Set up necessary variables
				  String id=commandLine.next();
				  
				  //If valid is true, add the student to the registry
				  if (isStringOnlyAlphabet(name)==true && isNumeric(id)==true)
				  {
					  //Check if student was already registered in the system or not
					  if (registry.addNewStudent(name, id))
					  {
						  System.out.println("Student was successfully added.");
					  }
					  //If student already exists display an error message
					  else
					  {
						  System.out.println("Student already exists inside of the registry.");
					  }
				  }
				  else if (isStringOnlyAlphabet(name)==false && isNumeric(id)==true)
				  {
					  System.out.println("Invalid characters in Name "+name);
				  }
				  else if (isStringOnlyAlphabet(name)==true && isNumeric(id)==false)
				  {
					  System.out.println("Invalid characters/length in ID "+id);
					  System.out.println("Ensure that ID is 5 digits long.");
				  }
				  else
				  {
					  System.out.println("Invalid characters in Name "+name+" and ID "+id);
				  }
			  } 
		  }
		  
		  //DELETES A STUDENT FROM THE REGISTRY
		  //EXPECTED INPUT: ID
		  else if (command.equalsIgnoreCase("DEL"))//DONE****************************
		  {
			  //Check for null character
			  if (!commandLine.hasNext())
			  {
				  System.out.println("Please enter an Id");
			  }
			  else
			  {
				  String id=commandLine.next();
				  
				  //If the number is all made of digits, the id will be checked to see if it exists in registry
				  if (isNumeric(id)==true)
				  {
					  //If this id is not in registry an error message will be shown to the user
					  if (registry.removeStudent(id)==false)
					  {
						  System.out.println("Student ID was not found in registry");
					  }
					  //If the id was found in registry, it is deleted and the student is removed
					  else
					  {
						  System.out.println("Student has been deleted");
					  }
				  }
				  else
				  {
					  //If the Id is invalid, display a message to the user
					  System.out.println("Invalid characters/length in ID "+id);
					  System.out.println("ID must be 5 digits long.");
				  }
			  }
		  }
		  
		  
		  //ADDS A STUDENT TO A COURSE
		  //EXPECTED INPUT: ID, COURSE CODE
		  else if (command.equalsIgnoreCase("ADDC"))
		  {
			  String id="";
			  boolean gotId=false;
			  boolean gotCourse=false;
			  
			  //Check for first null character
			  if (commandLine.hasNext())
			  {
				  //If not null, get the id, and set gotId to true
				  gotId=true;
				  id= commandLine.next();
				  //Check for second null character
				  if (commandLine.hasNext())
				  {
					  //If not null, set getCourse to true so that the code after can run
					  gotCourse=true;
				  }
				  else
				  {
					  System.out.println("Please enter a course into the field");
				  }
			  }
			  else
			  {
				  System.out.println("Please enter an Id into the field");
			  }
			  
			//If an Id was entered and a course was entered, then the code can run and the information will be checked for validity
			  if (gotId==true && gotCourse==true)
			  {
				//Set up necessary variables to be used 
				  String course=commandLine.next();
				  
				//If user input is good, call method in registry
				  if (isNumeric(id)==true)
				  {
					  registry.addCourse(id, course);
				  }
				  else
				  {
					//If the Id is not valid, user will be displayed with an error message
					  System.out.println("Invalid characters/length in ID "+id);
					  System.out.println("ID must be 5 digits long.");
				  }
			  }
		  }
		  
		  
		  //DROPS A STUDENT FROM A COURSE
		  //EXPECTED INPUT: ID, COURSE CODE
		  else if (command.equalsIgnoreCase("DROPC"))
		  {
			  String id="";
			  boolean gotId=false;
			  boolean gotCourse=false;
			  
			  //Check for first null character
			  if (commandLine.hasNext())
			  {
				  //If not null, get the id, and set gotId to true
				  gotId=true;
				  id= commandLine.next();
				  //Check for second null character
				  if (commandLine.hasNext())
				  {
					  //If not null, set getCourse to true so that the code after can run
					  gotCourse=true;
				  }
				  else
				  {
					  System.out.println("Please enter a course into the field");
				  }
			  }
			  else
			  {
				  System.out.println("Please enter an Id into the field");
			  }
			  
			//If an Id was entered and a course was entered, then the code can run and the information will be checked for validity
			  if (gotId==true && gotCourse==true)
			  {
				//Set up necessary variables to be used 
				  String course=commandLine.next();
				  
				  //If user input is good, call method in registry
				  if (isNumeric(id)==true)
				  {
					  registry.dropCourse(id, course);
				  }
				  //If the Id is not valid, user will be displayed with an error message
				  else
				  {
					  System.out.println("Invalid characters/length in ID "+id);
					  System.out.println("ID must be 5 digits long.");
				  }
			  }
		  }
		  
		  
		  //PRINTS ALL ACTIVE COURSES AND THEIR DESCRIPTIONS
		  //EXPECTED INPUT: NONE
		  else if (command.equalsIgnoreCase("PAC"))//DONE****************************
		  {
			  registry.printActiveCourses();
		  }	
		  
		  
		  //PRINTS THE CLASS LIST FOR A COURSE
		  //EXPECTED INPUT: COURSE CODE
		  else if (command.equalsIgnoreCase("PCL"))//DONE****************************
		  {
			  //Check for null character
			  if (!commandLine.hasNext())
			  {
				  System.out.println("Please enter a course code into the field");
			  }
			  //If not null, proceed to next instruction
			  else
			  {
				  // get course code string
				  // print class list (i.e. students) for this course
				  String course= commandLine.next();
				  
				  registry.printClassList(course);
			  } 
		  }
		  
		  //PRINTS THE GRADE AND ID FOR ALL STUDENTS IN THE ACTIVE COURSE
		  //EXPECTED INPUT: COURSE CODE
		  else if (command.equalsIgnoreCase("PGR"))//DONE***************************************************
		  {	  
			  boolean goOn=true;

			  //Check for null character
			  if (!commandLine.hasNext())
			  {
				  goOn=false;
			  }
			 
			  //If there is a null character, display error message to the user
			  if (goOn!=true)
			  {
				  System.out.println("Please enter a course code.");
			  }
			  //If no null character, set up appropriate variables with user input
			  else
			  {
				  String courseCode= commandLine.next();
				  //Call method in registry
				  registry.printGrades(courseCode);
			  }

			  // get course code string
			  // print name, id and grade of all students in active course
		  }
		  
		  //PRINT A STUDENTS ACTIVE COURSES
		  //EXPECTED INPUT: ID
		  else if (command.equalsIgnoreCase("PSC"))//DONE**********************************
		  {
			  //Check for null character
			  //If null, display an error message to the user
			  if (!commandLine.hasNext())
			  {
				  System.out.println("Please enter a student Id");
			  }
			  else
			  {
				//If not null, get the necessary input from the user and put to variables
				  String id=commandLine.next();
				  
				//Check if the Id is all numbers
				  if (isNumeric(id)==true)
				  {
					  //If Id is all numbers, call method in registry
					  registry.printStudentCourses(id);
				  }
				  else
				  {
					  //If the Id is not all numbers, or the Id is not the correct amount of numbers,
					  //error message will be displayed to the user
					  System.out.println("Invalid characters/length in ID "+id);
					  System.out.println("ID must be 5 digits long.");
				  }
			  }
		  }
		  
		  //PRINT A STUDENTS TRANSCRIPT (PRINT THEIR ACTIVE AND INACTIVE COURSES AND THEIR GRADE)
		  //EXPECTED INPUT: ID
		  else if (command.equalsIgnoreCase("PST"))//DONE**********************************
		  {
			  //Check for null character
			  //If null, display an error message to the user
			  if (!commandLine.hasNext())
			  {
				  System.out.println("Please enter a student Id");
			  }
			  else
			  {
				  //If not null, get the necessary input from the user and put to variables
				  String id=commandLine.next();
				  
				  //Check if the Id is all numbers
				  if (isNumeric(id)==true)
				  {
					  //If Id is all numbers, call method in registry
					  registry.printStudentTranscript(id);
				  }
				  //If the Id is not all numbers, or the Id is not the correct amount of numbers,
				  //error message will be displayed to the user
				  else
				  {
					  System.out.println("Invalid characters/length in ID "+id);
					  System.out.println("ID must be 5 digits long.");
				  }
			  }
			  
			  // get student id string
			  // print student transcript
			  
		  }
		  
		  //SET THE FINAL GRADE OF A STUDENT
		  //EXPECTED INPUT: COURSE CODE, ID, GRADE(NUMBER)
		  else if (command.equalsIgnoreCase("SFG"))//TODO
		  {
			  boolean goOn=true;
			  
			  //Check for null character. If there i a null character, goOn will be false 
			  if (!commandLine.hasNext())
			  {
				  goOn=false;
			  }
			 
			  //If goOn is false, display error message to the user
			  if (goOn!=true)
			  {
				  System.out.println("Please enter course code, student ID and numeric grade.");
			  }
			  //If goOn is true, the program will continue with the necessary input
			  else
			  {
				  //Get the courseCode, id and grade from the user
				  String courseCode= commandLine.next();
				  String id= commandLine.next();
				  double grade=commandLine.nextDouble();
				  
				  //Make sure that the two last fields are numbers
				  if (isNumeric(id)!=true)
				  {
					  System.out.println("Invalid characters in ID.");
				  }
				  //If they are numbers and everything is entered properly , then set the final grade of the student
				  else
				  {
					  registry.setFinalGrade(courseCode, id, grade);
				  }
			  }
			  
			  // set final grade of student
			  // get course code, student id, numeric grade
			  // use registry to set final grade of this student (see class Registry)
		  }
		  
		  //SORT THE STUDENTS IN A CLASS BY NAME
		  //EXPECTED INPUT: COURSE CODE
		  else if (command.equalsIgnoreCase("SCN"))
		  {
			  //Check for a null character
			  if (commandLine.hasNext())
			  {
				// If no null character, get course code
				  String courseCode= commandLine.next();
				  //Call method in registry
				  registry.sortCourseByName(courseCode);
			  }
			  else
				//If a null character was entered, display an error message to the user
			  {
				  System.out.println("Please enter a course code.");
			  }
		  }
		  
		  //SORT THE STUDENTS IN A CLASS BY ID
		  //EXPECTED INPUT: COURSE CODE
		  else if (command.equalsIgnoreCase("SCI"))
		  {
			  //Check for a null character
			  if (commandLine.hasNext())
			  {
				  //If no null character, assign input as the courseCode
				  String courseCode= commandLine.next();
				  //Call method in registry
				  registry.sortCourseById(courseCode);
			  }
			  else
			//If a null character was entered, display an error message to the user
			  {
				  System.out.println("Please enter a course code.");
			  }

		  }
		  
		  //ADDS A CLASS TO THE SCHEDULE AND CHECKS IF IT IS ALLOWED TO BE ADDED
		  //EXPECTED INPUT: COURSE CODE, DAY, TIME, LECTURE DURATION
		  else if (command.equalsIgnoreCase("SCH"))
		  {	  
			  
			  String message="Unknown Course: ";
			  try
			  {
				  String courseCode=commandLine.next();
				  String day=commandLine.next();
				  int time=commandLine.nextInt();
				  int duration=commandLine.nextInt();
				  
				  message+=courseCode;
				  
				  scheduler.setDayAndTime(courseCode, day, time, duration);
			  }
			  catch (UnknownCourse e)
			  {
				  System.out.println(message);//+courseCode);
			  }
			  catch (InvalidDay e)
			  {
				  System.out.println("Invalid Lecture Day");
			  }
			  catch (InvalidTime e)
			  {
				  System.out.println("Invalid Lecture Start Time");
			  }
			  catch (InvalidDuration e)
			  {
				  System.out.println("Invalid Lecture Duration");
			  }
			  catch (LectureTimeCollision e)
			  {
				  System.out.println("Lecture Time Collision");
			  }
			  catch (RuntimeException e)
			  {
				  System.out.println("Please enter all necessary information: course code, day, time, duration");
			  }
		  }
		  
		  //CLEAR THE SCHEDULE
		  //EXPECTED INPUT:COURSE CODE
		  else if (command.equalsIgnoreCase("CSCH"))
		  {
			  try
			  {
				  String courseCode=commandLine.next();
				  scheduler.clearSchedule(courseCode);
			  }
			  catch (RuntimeException e)
			  {
				  System.out.println("Please enter a course code");
			  }
			  
		  }
		  
		  //PRINT THE SCHEDULE
		  //EXPECTED INPUT: NONE
		  else if (command.equalsIgnoreCase("PSCH"))
		  {
			  scheduler.printSchedule();
		  }
		  
		  //Expect more input from the user 
		  System.out.print("\n>");
	  }
	  
	  }
	   catch (FileNotFoundException e) 
	   {
		   System.out.println("students.txt File Not Found");
	   } 
	   //If the format of the file is bad throw an exception
	   catch (IOException e)
	   {
		   System.out.println("Bad File Format students.txt");
	   }
  }
  
  private static boolean isStringOnlyAlphabet(String str) 
  { 
	  
	//Variable to determine if valid or not
	  boolean valid=true;
	  
	  //Turn name into an array to check each character individually
	  char[] nameArray=str.toCharArray();
	  
	  //If a char is not a letter, the user will be told their input is invalid
	  for (char letter: nameArray)
	  {
		  if(!Character.isLetter(letter))
		  {
			  valid=false;
			  break;
		  }
	  }
      // write method to check if string str contains only alphabetic characters 
	  return valid;
  } 
  
  
  public static boolean isNumeric(String str)
  {
	  
	  boolean valid=true;
	  
	//Turn ID into an array to check each number individually
	  char[] idArray=str.toCharArray();
	  
	  if (idArray.length!=5)
	  {
		  return false;
	  }
	  
	  //If char is not a digit, the user will be told their input is invalid  
	  for (char number: idArray)
	  {
		  if(!Character.isDigit(number))
		  {
			  valid=false;
			  break;
		  }
	  }  
	  
      // write method to check if string str contains only numeric characters
	  return valid;
  }
}

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class Scheduler 
{
    // In main() after you create a Registry object, create a Scheduler object and pass in the courses arraylist/treemap
	// If you do not want to try using a Map then uncomment
	// the line below and comment out the TreeMap line
	
	//ArrayList<Student> students;
	
	//Treemap that connects to the ActiveCourse schedule
	TreeMap<String,ActiveCourse> schedule;
	
	//Constructor method for active course
	public Scheduler(TreeMap<String,ActiveCourse> courses)
	{
	  schedule = courses;
	}
	
	//Sets the day and time for the course that is entered
	//Handles the exceptions in the main method 
	public void setDayAndTime(String courseCode, String day, int startTime, int duration)
	{
		boolean courseFound=false;
		boolean collision=false;
		
		//Check to see if the course can be found in ActiveCourse
		for (String key:schedule.keySet())
		{
			if (key.equalsIgnoreCase(courseCode)) 
			{
				courseFound=true;
			}
		}
		
		//Bring up an exception if the course was not found
		if (courseFound==false)
		{
			throw new UnknownCourse();
		}
		
		//Check to see if the day is valid or not
		if (!(day.equalsIgnoreCase("Mon") || day.equalsIgnoreCase("Tue") || day.equalsIgnoreCase("Wed") ||
			day.equalsIgnoreCase("Thu") || day.equalsIgnoreCase("Fri")))
		{
			throw new InvalidDay();
		}
		
		//Check to see if time is valid or not 
		if (!(startTime>=800 && startTime<1700))
		{
			throw new InvalidTime();
		}
		
		//Check to see if the duration is valid or not
		if ((!(duration>=1 && duration<=3))|| startTime+(duration*100)>1700)
		{
			throw new InvalidDuration();
		}
		
		for (String key:schedule.keySet())
		{
			//See if the course has been added to the schedule or not
			if (schedule.get(key).added!=false)
			{
				//See if the course is in the same day
				if (schedule.get(key).lectureDay.equalsIgnoreCase(day))
				{
					//Get the end time of each lecture by adding the duration  onto the lecture time
					int thisEndTime=startTime+(duration*100);
					int otherEndTime=schedule.get(key).lectureStart+(schedule.get(key).lectureDuration*100);
					
					//See if any of the times conflict with each other
					for (int i=startTime; i<=thisEndTime-100; i+=100)
			    	{
			    		for (int j=schedule.get(key).lectureStart; j<=otherEndTime-100; j+=100)
			    		{
			    			if (i==j)
			    			{
			    				//Ensure that the course that is taking up this time slot is not the same course
			    				//that is trying to be rescheduled
			    				if (!key.equalsIgnoreCase(courseCode))
			    				{
			    					collision=true;
			    				}
			    			}
			    		}
			    	}
				}
			}
		}
		
		//If collision is put to true, there is a collision and an error will be thrown
		if (collision==true)
		{
			throw new LectureTimeCollision();
		}
				
		//If no exceptions have been thrown the variables can be set
		clearSchedule(courseCode); //Clear the course code from the schedule so it can be overwritten
				
		//Set all of the variables 
		for (String key:schedule.keySet())
		{
			if (key.equalsIgnoreCase(courseCode))
			{
				schedule.get(key).added=true;
				schedule.get(key).lectureStart=startTime;
				schedule.get(key).lectureDuration=duration;
				schedule.get(key).lectureDay=day;
			}
		}
		
		
	}
	
	//Clear the schedule for the selected course
	public void clearSchedule(String courseCode)
	{
		for (String key:schedule.keySet())
		{
			//Find the matching course and set each of the variables on the schedule
			if (key.equalsIgnoreCase(courseCode))
			{
				schedule.get(key).added=false;
				schedule.get(key).lectureDay="";
				schedule.get(key).lectureStart=0;
				schedule.get(key).lectureDuration=0;
			}
		}
	}
		
	//Print the schedule
	public void printSchedule()
	{
		//Variable used to keep track of days index, also known as the X value for 2D array
		int dayX=0;
		//Variable used to keep track of hours index, also known as the Y value for 2D array
		int hourY=0;
		//Make a variable to find the startTime of the class
		int startTime=0;
		//Make a variable to find the endTime of the class
		int endTime=0;
		//Make array for days in the week
		String[] days= {"Mon","Tue","Wed","Thu","Fri"};
		//Make 2D array to use for displaying the schedule
		String[][] calendar = new String [5][9];
		//Initialize all values in the 2D array to 6 blank characters
		for (int y=0;y<=8;y++)
    	{
    		for (int x=0;x<=4;x++)
    		{
    			calendar[x][y]="      ";
    		}
    	}

		//Go through each day of the week from the days array
		for (String day:days)
		{
			//Go through each one of the courses
			for (String key:schedule.keySet())
			{
				//Check to see if this course has been added to the schedule yet, and see if it is 
				//scheduled to be on the current day that the array is on
				if (schedule.get(key).added==true && schedule.get(key).lectureDay.equalsIgnoreCase(day))
				{
					//Get the start time of the class
					startTime=schedule.get(key).lectureStart;
					//Get the end time of this class
					endTime=schedule.get(key).lectureStart+(schedule.get(key).lectureDuration*100);
					
					//Loop through the different times to see when the class is scheduled to happen
					for (int i=800; i<=1600; i+=100)
					{
						//If the correct hour is found, add the class to the 2D array calendar
						if (i>=startTime && i<endTime)
						{
							calendar[dayX][hourY]=key;
						}
						//Increment the hour variable by 1
						hourY++;
					}
					hourY=0;
				}
			}
			//Increment the day variable by 1
			dayX++;
		}
		
		//Print the headers for the calendar
		System.out.printf("%12s"+"%9s"+"%9s"+"%9s"+"%9s"+"\n", "Mon","Tue","Wed","Thu","Fri");
		int count=0;
		
		//Do a loop to print out each line of the calendar
		for (int i=800; i<=1600; i+=100)
		{
			String line="";
			//Add 0 before 800 and 900 so it is 4 digits long like the other times
			if (i==800 || i==900)
			{
				line+="0";
			}
			//For this specific line, print the values from x index 0-4 
			line+=i+"    "+calendar[0][count]+"   "+calendar[1][count]+"   "+calendar[2][count]+"   "+calendar[3][count]+"   "+calendar[4][count];
			//Print the calendar line
			System.out.println(line);
			//Increment the counter so that the next times can be shown 
			count++;
		}
	}
	
}


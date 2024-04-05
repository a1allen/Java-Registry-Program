//Creditcourse is a record of what courses you have done and it inherits class Course
public class CreditCourse extends Course //COMPLETED
{
	private String  semester;
	public  double  grade;
	public  boolean active;
	
	//Calls the super class constructor to inherit 
	public CreditCourse(String name, String code, String descr, String fmt,String semester, double grade) //DONE
	{
		super(name,code,descr,fmt);
		this.semester=semester;
		grade=0;
		active=true;
	}
	
	//Returns if the course is active or not
	public boolean getActive() //DONE
	{
		return active;
	}
	
	//Sets the course to active
	public void setActive() //DONE
	{
		active=true;
	}
	
	//sets the course as inactive meaning the student no longer takes the course
	public void setInactive() //DONE
	{
		active=false;
	}
	
	//Return the display of the students grade with the semester and the information of the course
	public String displayGrade() //DONE?
	{
		//Get the grade that the student achieved from Course class
		String letterGrade=super.convertNumericGrade(grade);
		
		// Change line below and print out info about this course plus which semester and the grade achieved
		// make use of inherited method in super class
		return super.getInfo()+ "\n" + semester + "\n" + letterGrade;
	}
	
}
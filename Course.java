//Class course keeps the necessary information of a course
//ActiveCourse and CreditCourse extend this class Course
public class Course //COMPLETED
{
	private String code;
	private String name;
	private String description;
	private String format;
	   
	public Course()
	{
	  this.code        = "";
	  this.name        = "";
	  description = "";
	  this.format      = "";
	}
	   
	public Course(String name, String code, String descr, String fmt)
	{
	  this.code        = code;
	  this.name        = name;
	  description = descr;
	  this.format      = fmt;
	}
	
	//Added method to return the description ONLY, in the form of a sentence
	public String getSentence()
	{
		return description;
	}
	   
	//Return the course code
	public String getCode()
	{
	   return code;
	}
	  
	//Return the course name
	public String getName()
	{
	  return name;
	}
	 
	//Return the format of the course
	public String getFormat()
	{
	  return format;
	}
	  
	//Return the description of the course
	public String getDescription()
	{
	  return code +" - " + name + "\n" + description + "\n" + format;
	}
	
	//Return info about this course
	 public String getInfo()
	 {
	   return code +" - " + name;
	 }
	 
	 // static method to convert numeric score to letter grade string 
	 // e.g. 91 --> "A+"
	 public static String convertNumericGrade(double score)
	 {
		 //Convert the percent grade number to it's appropriate letter
		 //Method accepts a double but returns a String
		 	if (score >= 97.0)
	    	{
	    		return "A+" ;
	    	}	
	    	else if (score >= 93.0)
	    	{
	    		return "A" ;
	    	}
	    	else if (score >= 90.0)
	    	{
	    		return "A-" ;
	    	}
	    	//Letter B
	    	else if (score >= 87.0)
	    	{
	    		return "B+" ;
	    	}
	    	else if (score >= 83.0)
	    	{
	    		return "B" ;
	    	}
	    	else if (score >= 80.0)
	    	{
	    		return "B-" ;
	    	}
	    	//Letter C
	    	else if (score >= 77.0)
	    	{
	    		return "C+" ;
	    	}
	    	else if (score >= 73.0)
	    	{
	    		return "C" ;
	    	}
	    	else if (score >= 70.0)
	    	{
	    		return "C-" ;
	    	}
	    	//Letter D
	    	else if (score >= 67.0)
	    	{
	    		return "D+" ;
	    	}
	    	else if (score >= 63.0)
	    	{
	    		return "D" ;
	    	}
	    	else if (score >=60.0)
	    	{
	    		return "D-" ;
	    	}
	    	else
	    	{
	    		return "F" ;
	    	}	 
	
	 }
	 
}

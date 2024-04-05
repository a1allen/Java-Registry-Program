import java.io.IOException;

//Has a collection of classes used to handle each RunTimeException in RegistrySimulator

//Exception used if the course entered is unknown
class UnknownCourse extends RuntimeException
{
    public UnknownCourse() {}
    public UnknownCourse(String message) 
    {
	super(message) ;
    }
}

//Exception used if the day entered is invalid (was not Mon, Tue, Wed, Thu, or FRi)
class InvalidDay extends RuntimeException
{
    public InvalidDay() {}
    public InvalidDay(String message) 
    {
	super(message) ;
    }
}

//Exception used if the time entered was invalid (was not between 800 to 1600)
class InvalidTime extends RuntimeException
{
    public InvalidTime() {}
    public InvalidTime(String message) 
    {
	super(message) ;
    }
}

//Exception used if the duration entered was not between 1 and 3 hours
class InvalidDuration extends RuntimeException
{
    public InvalidDuration() {}
    public InvalidDuration(String message) 
    {
	super(message) ;
    }
}

//Exception used if the entered class collides with the time of another class
class LectureTimeCollision extends RuntimeException
{
    public LectureTimeCollision() {}
    public LectureTimeCollision(String message) 
    {
	super(message) ;
    }
}











import java.io.*;
import java.sql.*;

public class StudentDatabaseCaller
{
	public static void main(String[] args) throws SQLException, IOException
	{
		String DepartmentCourses = "";
		String StudentCourses = "";
		String Students = "";
		
		StudentDatabaseFunctions.Connect();
		if(args.length > 0)
		{
			if(args[0].equals("AddStudent"))
			{
				StudentDatabaseFunctions.AddStudent(Integer.parseInt(args[1]), args[2], args[3]);
			}
			else if(args[0].equals("AddCourse"))
			{
				StudentDatabaseFunctions.AddCourse(Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3], Integer.parseInt(args[4]));
			}
			else if(args[0].equals("AddEnrollment"))
			{
				StudentDatabaseFunctions.AddEnrollment(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
			}
			else if(args[0].equals("PrintDepartmentCourses"))
			{
				DepartmentCourses = StudentDatabaseFunctions.PrintDepartmentCourses(Integer.parseInt(args[1]));
				System.out.println("<HTML><BODY><p>");
				System.out.println(DepartmentCourses + "<br></p>");
				System.out.println("</BODY></HTML>");
				
			}
			else if(args[0].equals("PrintStudentCourses"))
			{
				StudentCourses = StudentDatabaseFunctions.PrintStudentCourses(Integer.parseInt(args[1]));
				System.out.println("<HTML><BODY><p>");
				System.out.println(StudentCourses + "<br></p>");
				System.out.println("</BODY></HTML>");
				
			}
			else if(args[0].equals("PrintStudents"))
			{
				Students = StudentDatabaseFunctions.PrintTable("Students");
				System.out.println("<HTML><BODY><p>");
				System.out.println(Students + "<br></p>");
				System.out.println("</BODY></HTML>");
			}
		}
	}
}
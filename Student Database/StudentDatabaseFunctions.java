import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.*;
import java.time.*;

public class StudentDatabaseFunctions
{
	private static Connection connection;
    private static Statement statement;
	
	public StudentDatabaseFunctions()
	{
        connection = null;
        statement = null;
    }
	
	// Connect to the database
    public static void Connect() throws SQLException
	{
		String Username = "jsh024";
		String mysqlPassword = "jsh911m2";
        try
		{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + Username + "?" +
                    "user=" + Username + "&password=" + mysqlPassword);
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/" + Username +
            //      "?user=" + Username + "&password=" + mysqlPassword);
        }
        catch (Exception e)
		{
            throw e;
        }
    }

    // Disconnect from the database
    public static void Disconnect() throws SQLException
	{
        connection.close();
        statement.close();
    }
	
	// Execute an SQL query passed in as a String parameter
    // and print the resulting relation
    public static void query(String q)
	{
        try
		{
			statement = connection.createStatement();	
            ResultSet resultSet = statement.executeQuery(q);
            System.out.println("\n---------------------------------");
            System.out.println("Query: \n" + q + "\n\nResult: ");
            print(resultSet);
        }
        catch (SQLException e)
		{
            e.printStackTrace();
        }
    }

    // Print the results of a query with attribute names on the first line
    // Followed by the tuples, one per line
    public static String print(ResultSet resultSet) throws SQLException
	{
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numColumns = metaData.getColumnCount();
        String Headers = printHeader(metaData, numColumns);
        String Records = printRecords(resultSet, numColumns);
		return Headers + Records;
    }

    // Print the attribute names
    public static String printHeader(ResultSetMetaData metaData, int numColumns) throws SQLException
	{
		String Headers = "";
        for (int i = 1; i <= numColumns; i++)
		{
            if (i > 1)
			{
				Headers += ",  ";
			}
            Headers += metaData.getColumnName(i);
        }
		Headers += "<br>\n";
        return Headers;
    }

    // Print the attribute values for all tuples in the result
    public static String printRecords(ResultSet resultSet, int numColumns) throws SQLException
	{
        String columnValue = "";
        while (resultSet.next())
		{
            for (int i = 1; i <= numColumns; i++)
			{
                if (i > 1)
				{
                    columnValue += ",  ";
				}
                columnValue += resultSet.getString(i);
            }
			columnValue += "<br>\n";
        }
		return columnValue;
    }
	
	//Adds a student to the Students relation
	public static void AddStudent(int StudentID, String StudentName, String Major) throws IOException
	{
		String Insert = "INSERT into Students values (" + StudentID + ",'" + StudentName + "','" + Major + "')";
		try
		{
			statement = connection.createStatement();
            statement.executeUpdate(Insert);
        }
		catch (SQLException e) 
		{
            e.printStackTrace();
        }
	}
	
	//Adds a course to the Courses relation
	public static void AddCourse(int DeptCode, int CourseNum, String Title, int CreditHours) throws IOException
	{
		String Insert = "INSERT into Courses values (" + DeptCode + "," + CourseNum + ",'" + Title + "'," + CreditHours + ")";
		try
		{
			statement = connection.createStatement();
            statement.executeUpdate(Insert);
        }
		catch (SQLException e) 
		{
            e.printStackTrace();
        }
	}
	
	//Adds an application to the Enrollment relation
	public static void AddEnrollment(int StudentID, int DeptCode, int CourseNum) throws IOException
	{
		String CountQuery = "SELECT COUNT(*) FROM Enrollment;";
		
		try
		{
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(CountQuery);
			resultSet.next();
			int EnrollmentCount = (Integer.parseInt(resultSet.getObject(1).toString()));
			String Insert = "INSERT into Enrollment values (" + EnrollmentCount + "," + StudentID + "," + DeptCode + "," + CourseNum + ")";
            statement.executeUpdate(Insert);
        }
		catch (SQLException e) 
		{
            e.printStackTrace();
        }
	}
	
	//Print every course in the specified department
	public static String PrintDepartmentCourses(int DeptCode) throws IOException
	{
		String SQLQuery = "SELECT * FROM Courses WHERE DeptCode = " + DeptCode;
		try
		{
			statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQLQuery);
            if(resultSet.next() != false)
			{
				resultSet.previous();
				return print(resultSet);
			}
			else
			{
				return "";
			}
        }
        catch (SQLException e)
		{
            e.printStackTrace();
        }
		return "";
	}
	
	//Print every course a specified student is enrolled in
	public static String PrintStudentCourses(int StudentID) throws IOException
	{
		String SQLQuery = "SELECT DISTINCT c.DeptCode, c.CourseNum, c.Title, c.CreditHours FROM Enrollment e, Courses c, Students s WHERE e.StudentID = " + StudentID + " AND e.StudentID = s.StudentID";
		try
		{
			statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQLQuery);
            if(resultSet.next() != false)
			{
				resultSet.previous();
				return print(resultSet);
			}
			else
			{
				return "";
			}
        }
        catch (SQLException e)
		{
            e.printStackTrace();
        }
		return "";
	}
	
	//Print every relation in the specified table
	public static String PrintTable(String Table) throws IOException
	{
		String SQLQuery = "SELECT * FROM " + Table;
		try
		{
			statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQLQuery);
            if(resultSet.next() != false)
			{
				resultSet.previous();
				return print(resultSet);
			}
			else
			{
				return "";
			}
        }
        catch (SQLException e)
		{
            e.printStackTrace();
        }
		return "";
	}
}
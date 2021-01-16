<html>
<body>
<h3>Click the button to return to the home page:</h3>

<button onclick="ReturnHome()">Return to Home Page</button>

<h3>Enter the code for the department you would like to view the courses for:</h3>

<form action="DisplayDepartmentCourses.php" method="post">
    Department Code: <input type="number" min="1" name="deptcode"><br>
    <input name="submit" type="submit" >
</form>
<br><br>

</body>
</html>

<script>
function ReturnHome()
{
	location.replace("HomePage.html")
}
</script>

<?php
if (isset($_POST['submit'])) 
{
    // replace ' ' with '\ ' in the strings so they are treated as single command line args
	$functioncall = "PrintDepartmentCourses";
    $deptcode = escapeshellarg($_POST[deptcode]);

    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar StudentDatabaseCaller ' . $functioncall . ' ' . $deptcode;

    // remove dangerous characters from command to protect web server
    $command = escapeshellcmd($command);
    //echo "<p>command: $command <p>";
 
    // run program
	echo "Here are the courses that were found for the entered department. <br>";
    system($command);
	echo "<br>";
	
	//$DepartmentCourses = $_POST['DepartmentCourses'];
	
	//echo "{$DepartmentCourses}";
}
?>


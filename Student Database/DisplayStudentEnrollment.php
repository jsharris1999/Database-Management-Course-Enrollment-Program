<html>
<body>
<h3>Click the button to return to the home page:</h3>

<button onclick="ReturnHome()">Return to Home Page</button>

<h3>Enter id number for the student you would like to view the courses for:</h3>

<form action="DisplayStudentEnrollment.php" method="post">
    IDNumber: <input type="number" min="1" name="idnumber"><br>
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
	$functioncall = "PrintStudentCourses";
    $idnumber = escapeshellarg($_POST[idnumber]);

    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar StudentDatabaseCaller ' . $functioncall . ' ' . $idnumber;

    // remove dangerous characters from command to protect web server
    $command = escapeshellcmd($command);
    //echo "<p>command: $command <p>";
 
    // run program
	echo "Here are the courses that were found for the entered student. <br>";
    system($command);
	echo "<br>";
	
	//$StudentCourses = $_GET['StudentCourses'];
	
	//echo "{$StudentCourses}";
}
?>
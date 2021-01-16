<html>
<body>
<h3>Click the button to return to the home page:</h3>

<button onclick="ReturnHome()">Return to Home Page</button>

<h3>Enter information about the course to add to the database:</h3>

<form action="AddCourse.php" method="post">
    Department Code: <input type="number" min="1" name="deptcode"><br>
    Course Number: <input type="number" min="1" name="coursenum"><br>
    Title: <input type="text" name="title"><br>
	Credit Hours: <input type="number" min="1" max="5" name="credit"><br>
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
	$functioncall = "AddCourse";
    $deptcode = escapeshellarg($_POST[deptcode]);
    $coursenum = escapeshellarg($_POST[coursenum]);
    $title = escapeshellarg($_POST[title]);
	$credit = escapeshellarg($_POST[credit]);

    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar StudentDatabaseCaller ' . $functioncall . ' ' . $deptcode . ' ' . $coursenum . ' ' . $title . ' ' . $credit;

    // remove dangerous characters from command to protect web server
    $command = escapeshellcmd($command);
    //echo "<p>command: $command <p>";
 
    // run program
    system($command);
	
	echo "Your entry is being added.";
}
?>
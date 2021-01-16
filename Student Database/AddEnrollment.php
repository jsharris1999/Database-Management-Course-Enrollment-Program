<html>
<body>
<h3>Click the button to return to the home page:</h3>

<button onclick="ReturnHome()">Return to Home Page</button>

<h3>Enter information about the enrollment to add to the database:</h3>

<form action="AddEnrollment.php" method="post">
    IDNumber: <input type="number" min="1" name="idnumber"><br>
    Department Code: <input type="number" min="1" name="deptcode"><br>
    Course Number: <input type="number" min="1" name="coursenum"><br>
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
	$functioncall = "AddEnrollment";
    $idnumber = escapeshellarg($_POST[idnumber]);
    $deptcode = escapeshellarg($_POST[deptcode]);
    $coursenum = escapeshellarg($_POST[coursenum]);

    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar StudentDatabaseCaller ' . $functioncall . ' ' . $idnumber . ' ' . $deptcode . ' ' . $coursenum;

    // remove dangerous characters from command to protect web server
    $command = escapeshellcmd($command);
    //echo "<p>command: $command <p>";
 
    // run program
    system($command);
	
	echo "Your entry is being added.";
}
?>
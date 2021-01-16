<html>
<body>
<h3>Click the button to return to the home page:</h3>

<button onclick="ReturnHome()">Return to Home Page</button>

<h3>Enter information about the student to add to the database:</h3>

<form action="AddStudent.php" method="post">
    IDNumber: <input type="number" min="1" name="idnumber"><br>
    Name: <input type="text" name="name"><br>
    Major: <input type="text" name="major"><br>
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
	$functioncall = "AddStudent";
    $idnumber = escapeshellarg($_POST[idnumber]);
    $name = escapeshellarg($_POST[name]);
    $major = escapeshellarg($_POST[major]);

    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar StudentDatabaseCaller ' . $functioncall . ' ' . $idnumber . ' ' . $name . ' ' . $major;

    // remove dangerous characters from command to protect web server
    $command = escapeshellcmd($command);
    //echo "<p>command: $command <p>";
 
    // run program
    system($command);
	
	echo "Your entry is being added.";
}
?>
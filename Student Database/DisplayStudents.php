<html>
<body>
<h3>Click the button to return to the home page:</h3>

<button onclick="ReturnHome()">Return to Home Page</button>

<h3>Here are the student currently entered in our database:</h3>

<?php

// replace ' ' with '\ ' in the strings so they are treated as single command line args
$functioncall = "PrintStudents";

$command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar StudentDatabaseCaller ' . $functioncall;

// remove dangerous characters from command to protect web server
$command = escapeshellcmd($command);
//echo "<p>command: $command <p>";

// run program
system($command);
echo "<br>";

?>

<script>
function ReturnHome()
{
	location.replace("HomePage.html")
}
</script>

</body>
</html>
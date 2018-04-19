<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // collect value of input field
    $Lat=  $_POST['field'];
    $Long =  $_POST['field1'];


    if (empty($Lat) && empty($Long)) {
        echo "empty";
    } else {
        echo $Lat;
    }


    $mysqli = new mysqli('localhost','root','','ytapp');


	if ($mysqli->connect_error) {

    die('Error : ('. $mysqli->connect_errno .') '. $mysqli->connect_error);

	}


	$insert_row = $mysqli->query("INSERT INTO `ytapp`.`location` (`latitude`, `longitude`) VALUES ('$Lat', '$Long')");

	if($insert_row){
    header('Location: http://localhost/YTAPP/index');
	}else{
    die('Error : ('. $mysqli->errno .') '. $mysqli->error);
	}

	$mysqli->close();
}

?>
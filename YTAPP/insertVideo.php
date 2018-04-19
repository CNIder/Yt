<?php


    // collect value of input field
    $VideoID =  $_POST['field'];
    $Nome =  $_POST['field1'];
    $Duracao =  $_POST['field2'];
    $DataCreated =  $_POST['field3'];
    $author =  $_POST['field4'];

    if (empty($VideoID) && empty($Nome) && empty($Duracao) && empty($DataCreated) && empty($author)) {
        echo "empty";
    } else {
        echo $author;
    }


    $mysqli = new mysqli('localhost','root','','ytapp');


	if ($mysqli->connect_error) {

    die('Error : ('. $mysqli->connect_errno .') '. $mysqli->connect_error);

	}


	$insert_row = $mysqli->query("INSERT INTO video (videoID,nome,duracao,data_create,autor) VALUES ('$VideoID','$Nome',$Duracao,'$DataCreated','$author')");

	if($insert_row){
    print 'INSERIDO COM SUCESSO';
	}else{
    die('Error : ('. $mysqli->errno .') '. $mysqli->error);
	}

	$mysqli->close();


?>
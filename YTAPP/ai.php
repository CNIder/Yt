 <?php

//This script is designed by Android-Examples.com
//Define your host here.
$hostname = "localhost";
//Define your database username here.
$username = "root";
//Define your database password here.
$password = "";
//Define your database name here.
$dbname = "ytapp";
 
 $con = mysqli_connect($hostname,$username,$password,$dbname);
 
   $VideoID =  $_POST['field'];
    $Nome =  $_POST['field1'];
    $Duracao =  $_POST['field2'];
    $DataCreated =  $_POST['field3'];
    $author =  $_POST['field4'];
 
 
 $Sql_Query = "INSERT INTO video (videoID,nome,duracao,data_create,autor) VALUES ('$VideoID','$Nome',$Duracao,'$DataCreated','$author')";
 

 if(mysqli_query($con,$Sql_Query)){
 
 echo 'Data Inserted Successfully';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($con);
?>
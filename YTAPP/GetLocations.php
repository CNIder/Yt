<?php
//these are the server details
//the username is root by default in case of xampp
//password is nothing by default
//and lastly we have the database named android. if your database name is different you have to change it 
$servername = "localhost";
$username = "root";
$password = "";
$database = "ytapp";
 
 
//creating a new connection object using mysqli 
$conn = new mysqli($servername, $username, $password, $database);
 
//if there is some error connecting to the database
//with die we will stop the further execution by displaying a message causing the error 
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
 
//if everything is fine
 
//creating an array for storing the data 
$heroes = array(); 
 
//this is our sql query 
$sql = "SELECT latitude,longitude FROM location";
 
//creating an statment with the query
$stmt = $conn->prepare($sql);
 
//executing that statment
$stmt->execute();
 
//binding results for that statment 
$stmt->bind_result($latitude,$logitude);
 
//looping through all the records
while($stmt->fetch()){
 
 //pushing fetched data in an array 
 $temp = [
 'latitude'=>$latitude,
 'logitude'=>$logitude
 ];
 
 //pushing the array inside the hero array 
 array_push($heroes, $temp);
}


//displaying the data in json format 
echo json_encode($heroes);

?>
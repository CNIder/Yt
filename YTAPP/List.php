<?php
//Open a new connection to the MySQL server
$mysqli = new mysqli('localhost','root','','ytapp');

//Output any connection error
if ($mysqli->connect_error) {
    die('Error : ('. $mysqli->connect_errno .') '. $mysqli->connect_error);
}

//MySqli Select Query
$results = $mysqli->query("SELECT * FROM video");

print '<table border="1"';
while($row = $results->fetch_array()) {
    print '<tr>';
    print '<td>'.$row["videoID"].'</td>';
    print '<td>'.$row["nome"].'</td>';
    print '<td>'.$row["duracao"].'</td>';
    print '<td>'.$row["data_create"].'</td>';
    print '<td>'.$row["autor"].'</td>';
    print '</tr>';

}   
print '</table>';

// Frees the memory associated with a result
$results->free();
// close connection 
$mysqli->close();
?>
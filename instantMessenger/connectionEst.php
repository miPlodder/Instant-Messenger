<?php

$db = "instantMessenger";
$admin = "root";
$adminPassword = "15bce1317";
$serverName = "localhost";

// Create connection
$conn = new mysqli($serverName, $admin, $adminPassword, $db);
// Check connection
if ($conn->connect_error) {
    //die("Connection failed: " . $conn->connect_error);
}	

?>
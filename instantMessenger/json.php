<?php

echo "no post request";
//if($_SERVER["REQUEST_METHOD"]=="POST"){
if(true){
	echo "executed the post request";
	require "connectionEst.php";
	echo "executed the post request";
	//insertData();
	echo "executed the post request";
	showData();
	
}

function insertData(){

	global $conn;
	$userName = $_POST["userName"];
	$password = $_POST["password"];
	$email = $_POST["email"];
	$phoneNumber = $_POST["phoneNumber"];


	$sql = "INSERT INTO userDetail(userName,password,email,phoneNumber) values ('$userName','$password','$email','$phoneNumber')";
	if($conn->query($sql) == true ){
		echo "Account Created";
	}
	else{
		echo "Problem Creating Account";
	}

	//$mysqli_close($conn);
}

function showData(){

	global $conn;
	$sql = "SELECT * from userDetail;";
	$result = mysqli_query($conn,$sql);
	$numRow = mysqli_num_rows($result);

	$retVal = array();

	if($numRow > 0){
		while($row = mysqli_fetch_assoc($result))
			$retVal[] = $row ;
			//echo $row;
	}


	header('Content-Type:application/json');
	echo "\n\n".json_encode(array("details"=>$retVal));

}

?>
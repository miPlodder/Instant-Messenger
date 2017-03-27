<?php 

require 'connectionEst.php';

//php body

if(isset($_POST["email"])){

	//echo "success";
	checkData();

}
else {

	//echo "failure";
	$temp["id"] = "else";
	$json[] = $temp ;
	echo json_encode($json);

}

//checkData();

//functions
function checkData(){


	//echo $_POST["email"];
	global $conn ;
	$email = $_POST["email"];//"saksham@gmail.com";//
	$password = $_POST["password"];//"123";//;

	$sql = "select id from userDetail where email = '$email' and password = '$password'";
	$result = $conn->query($sql);
	
	$json = array();
	

	if($result->num_rows > 0){

		while($row = mysqli_fetch_assoc($result)){
			
			$json[] = $row;
			
			}
	}
	else{

		//echo "Login Failed, Try Again";
		$temp["id"] = "failed";
		$json[] = $temp ;


	}


	//$json["id"] = "failed";
	//echo $json["id"];

	//echo "sdsds";
	echo json_encode($json);

}



?>
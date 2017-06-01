<?php

	require 'connectionEst.php';

	if(isset($_POST["userId"]))
		
		getContacts();
	
	else{

		getContacts();

	}


	function getContacts(){

		//$userId = "66";//$_POST["userId"];
		
		global $conn;

		$sql = "select username,phoneNumber from userDetail";// WHERE id <>'$userId'";

		$result = $conn->query($sql);

		if($conn->query($sql)){


		}else{

		}

		$json = array();

		while($row = mysqli_fetch_assoc($result)){


			$json[] = $row;			


		}

		echo json_encode($json);





	}







?>
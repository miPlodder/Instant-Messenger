<?php

	require 'connectionEst.php';

	getContacts();

	function getContacts(){

		global $conn;

		$sql = "select username,phoneNumber from userDetail ";

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
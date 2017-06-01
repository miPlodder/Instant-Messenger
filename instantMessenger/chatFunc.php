<?php

	require 'connectionEst.php';

	//-----------commit
	$phoneNumber = $_POST["phoneNumberR"];
		//$sql = "SELECT id from userDetail where phoneNumber = '$phoneNumberR'";
	$sql = "SELECT id from userDetail where phoneNumber = '$phoneNumber'";
		$result = $conn->query($sql) ;
		global $receiver;
		while($id = $result->fetch_assoc()){

			$receiver = $id["id"];
			//echo "done";
			//echo $receiver;

		}

		//-----------commit

	if($_POST["msg"] == ""){
		//if(true){

		getMessage();

	}
	else if(isset($_POST["msg"])){


		insertMessage();
		getMessage();

	}


	else{	}

	function getMessage(){

		global $conn ;
		$sender = $_POST["sender"];
		$msg = $_POST["msg"];
		$phoneNumberR = $_POST["phoneNumberR"];
		global $receiver;
		/*$sender = "66";
		$msg = "hello";
		echo $receiver;*/
		//$phoneNumberR = $_POST["phoneNumberR"];

		$sql = "SELECT msg from chat where (sender = '$sender' or receiver = '$sender') and (receiver = '$receiver' or sender = '$receiver')";
		
		$result = $conn->query($sql) ;
		$json = array();

		while($message = $result->fetch_assoc()){

			$json[] = $message; 

		}
		
		echo json_encode($json);

	}

	function insertMessage(){

		global $conn;

		$sender = $_POST["sender"];
		$msg = $_POST["msg"];
		global $receiver ;
		//$msg = "hello";


		if(!empty($sender) && !empty($msg)){

			//insert data into the table chat
			$sql = "INSERT INTO chat(sender,receiver,msg) values ('$sender','$receiver','$msg')";

			if($conn->query($sql)){
				
				//echo 'inserted';
				//return true;
				//inserted data
			}
			else{
				//not inserted data
				//echo 'not inserted'.$conn->error;
				//return $conn->error;
			}




		}
		else{

			//return false ;

		}



	}





?>
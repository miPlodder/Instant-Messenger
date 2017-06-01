<?php

//post = [userName=>"",password=>"",email=>"",phoneNumber=>""];
//post is an assocaitive array

require 'connectionEst.php';

//php body here 



		
		//checking for response 
		if(isset($_POST["userName"])){
			
			//echo "set";
			insertData();
			
		}
		else{

			echo "Problem Creating Account";
		
		}







//all funtions here

function insertData(){

		global $conn;

		$userName = $_POST["userName"];
		$password = $_POST["password"];
		$email = $_POST["email"];
		$phoneNumber = $_POST["phoneNumber"];

		$sql = "INSERT INTO userDetail(userName,password,email,phoneNumber) values ('$userName','$password','$email','$phoneNumber')";
		
		if($conn->query($sql) == true ){
			
			if($conn->query($sql) == true ){

				echo "Account Created";

			}
		}
		else{
			echo "Problem Creating Account";
		}

}

$conn->close();



?> 

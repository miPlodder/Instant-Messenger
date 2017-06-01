<?php

	require 'chatFunc.php';
		
	if(isset($_POST['submit'])){

		if(sendMessage($_POST['sender'],$_POST['message']))
			echo "message send";
		else
			echo "unable to send";
	}


?>

<div id="messages" height="1000">
<?php
		
	$messages = getMessage();
	
	foreach($messages as $message){

		echo '<strong>'.$message['sender'].'Send<br/></strong>';
		echo $message['message'].'<br><br>';
			}

?>


</div>

<form action="index.php" method="POST">

		<label>Enter name<input type ="text" name ="sender"></label>
		<label>Enter message<input type ="text" name ="message"></label>
		<input type="submit" name="submit" value="send message"/>		
</form>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>generateRequest() : HOME</title>
    <script src="jquery-1.11.3.js"></script>
</head>
<body>
<span id="driver">generateRequest()</span>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
    $("#driver").click(function(event){
    	generateRequest();
    });
 });
function generateRequest(){
	var i = 1;
	for (i = 0; i < 10; i++) {
		$.ajax({
	        async: true,
	        type: "GET",
	        data:{name:i+1},
	        url: "req",
	        success: function (response) {
	            console.log(response);
	        }
	    });
	}
}
</script>
</body>
</html>
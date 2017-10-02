<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>

<span onclick="loadDoc();">page</span>
<span onclick="loadDoc1();">page1</span>
<script>
function loadDoc() {
	
	
	$.ajax({
		type: 'GET',
		  url: "http://localhost:9090/a",data:{a: 'a8080'}
		  
		})
		  .done(function( data ) {
		   alert("Yuppiee...");
		  });
	}
	
function loadDoc1() {
	
	$.ajax({
		type: 'POST',
		  url: "http://localhost:9090/b?b=xyz"
		  
		})
		  .done(function( adataa ) {
		   alert("Yuppiee12...");
		  });
}
</script>

</body>
</html>
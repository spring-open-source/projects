<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Page</title>
<style>
.error {
color: #ff0000;
font-style: italic;
}
</style>
<script type="text/javascript">

function Register_here() 
{ 
	
  var fn=document.getElementById('firstname').value;	
  var ln=document.getElementById('lastname').value;	
  var e=document.getElementById('email').value;	
  var p=document.getElementById('password').value;	
  var m=document.getElementById('mobile').value;	
  
  var url="/register_user?firstname="+fn+"&&lastname="+ln+"&&email="+e+"&&password="+"&&mobile="+m;
 
  if(window.XMLHttpRequest)
  {  
	  request=new XMLHttpRequest();    
  }  
	  else if(window.ActiveXObject){  
	  request=new ActiveXObject("Microsoft.XMLHTTP");  
	  }  
	  try  
	  {  
	  request.onreadystatechange=getInfo;  
	  request.open("GET",url,true);  
	  request.send();  
	  }  
	  catch(e)  
	  {  
	  alert("Unable to connect to server");  
	  }  
}

	  function getInfo(){  
	  if(request.readyState==4){  
	  var val=request.responseText;  
	  document.getElementById('user_list').innerHTML=val;  
	  }  
 }

</script>

</head>
<body>
<center>
<form:form modelAttribute="form" method="POST" action="/register_user" >

				<h2>Register Page</h2>

				<table>
					
					<tr>
						<td><form:label path="firstname">First name</form:label></td>
						<td><div>
								<form:input path="firstname" placeholder="First Name"/>
								<form:errors path="firstname" cssClass="error"/>
							</div></td>
					</tr>

					<tr>
						<td><form:label path="lastname">Last Name</form:label></td>
						<td><div>
							<%-- 	<form:hidden path="id" value="${id}" /> --%>
								<form:input path="lastname" />
								<form:errors path="lastname" cssClass="error"/>

							</div></td>
					</tr>

					<tr>
						<td><form:label path="email">Email</form:label></td>
						<td><div>
								<form:input path="email" />
								<form:errors path="email" cssClass="error"/>
							</div></td>
					</tr>
					<tr>
						<td><form:label path="password">Password</form:label></td>
						<td><div>
								<form:password path="password" />
								<form:errors path="password" cssClass="error"/>
							</div></td>
					</tr>
					<tr>
						<td><form:label path="mobile">Mobile</form:label></td>
						<td><div>
								<form:input path="mobile" />
								<form:errors path="mobile" cssClass="error"/>
							</div></td>
					</tr>


					<tr>
						<td colspan="2" align="center">
							<div>
								<input type="submit" value="Register Here"  />
							</div>
						</td>
					</tr>



				</table>
			</form:form>
			<br>
			<div id="user_list">

		</div>
			
			</center>
</body>
</html>
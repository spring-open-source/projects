<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
<script>
function printPage() {
    window.print();
}
</script>



<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<!-- <script type="text/javascript">
function RegisterByAjax(){
	 alert('hi maulik');
	var fn = $("#firstname").val();
	var ln = $("#lastname").val();
	var e = $("#email").val();
	var p = $("#password").val();
	var m = $("#mobile").val();
$.ajax({
    	   type: "POST",
    	   url: "RegisterByAjax",
    	   dataType: "json",  
    	   data:{firstname: fn , lastname: ln , email: e ,password: p , mobile:m },
    	   success: function(response){
    	   // alert(response)
    	    alert('hi');  
    	    if(response == "Success" )
    	    		{
    	    			alert('Inserted Successfully');
    	    			getallUser(); 
    	    		}
    	   },
    	   error: function(){      
    	    alert('Error while request..');
    	   }
    	  });
}

function getallUser(){
    $.ajax({
            type:"GET",
            url:"getallUser",
            dataType: "json",
            success:function(userList)
            { 
               alert(userList);
          	   var rows = '';
                $.each( userList , function( index, item ) {
        	  	  	rows += '<tr><td>' + item.id + '</td>';
        	  	  	rows += '<td>' + item.firstname + '</td>';
        	  	  	rows += '<td>' + item.lasrname + '</td>';
        	  	  	rows += '<td>' + item.email + '</td>';
        	  	  	rows += '<td>' + item.password + '</td>';
        	  		rows += '<td>' + item.mobile + '</td>';
        	  	  });
        	  	  $('#tblProducts').html(rows); 
            },
            error:function(xmlHttpRequest, textStatus, errorThrown)
            {
                   alert("error");
            }
    });
}
</script>
 -->
</head>
<body>
	<center>
	<input type="button" value="Print this page" onclick="printPage()" />
		<c:if test="${Login_fail!=null}">
			<h3>Login fail</h3>
		</c:if>
		<c:if test="${Main_Page!=null}">

			<form:form action="login_user" method="POST" modelAttribute="form">
				<h2>Login Page</h2>
				<table>
					<tr>
						<td><form:label path="email">Email</form:label></td>
						<td><div>
								<form:input path="email" />
								<form:errors path="email" />

							</div></td>
					</tr>

					<tr>
						<td><form:label path="password">Password</form:label></td>
						<td><div>
								<form:password path="password" />
								<form:errors path="password" />
							</div></td>
					</tr>

					<tr>
						<td colspan="2" align="center">
							<div>
								<input type="submit" value="sign in" />
							</div>
						</td>
					</tr>


					<tr>
						<td><form:label path="password">Register Here</form:label></td>
						<td><div>
								<a href="<spring:url value="/register_here" />"> Register
									Here</a>

							</div></td>
					</tr>

					<tr>
						<td><form:label path="password"> Get All User Here</form:label></td>
						<td><div>
								<a href="<spring:url value="/get_user" />"> Get All User
									Here</a>

							</div></td>
					</tr>


				</table>
			</form:form>

		</c:if>

		<c:if test="${users!=null}">

			<h2>All User Display</h2>
			<table border="1" width="60%">

				<tr>
					<th>Id</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>email</th>
					<th>Mobile No</th>
					<th>edit</th>
					<th>Delete</th>

				</tr>

				<c:forEach items="${user_list}" var="user_lt">
					<tr>
						<td><c:out value="${user_lt.getId()}" /></td>
						<td><c:out value="${user_lt.getFirstname()}" /></td>
						<td><c:out value="${user_lt.getLastname()}" /></td>
						<td><c:out value="${user_lt.getEmail()}" /></td>
						<td><c:out value="${user_lt.getMobile()}" /></td>
						<td><a
							href="<spring:url value="edit_user?id=${user_lt.getId()}"/>">Edit</a>
						</td>
						<td><a href="<spring:url value='/delete_user?id=${user_lt.getId()}' />">Delete</a></td>
					</tr>
				</c:forEach>

				<tr>

					<td colspan="7" align="center">Login Here :- <a
						href="<spring:url value="/index" />"> Login Here</a>

					</td>
				</tr>
				<tr>
					<td colspan="7" align="center">Register Here :- <a
						href="<spring:url value="/register_here" />"> Register Here</a>


					</td>
				</tr>
			</table>



		</c:if>

		<c:if test="${edits_user!=null}">

			<form:form method="POST" action="/edit_user" modelAttribute="form">

				<h2>User Edit Page</h2>

				<table>
					<form:hidden path="password" />
					<tr>
						<td><form:label path="firstname">First name</form:label></td>
						<td><div>
								<form:input path="firstname" />
								<form:errors path="firstname"/>
							</div></td>
					</tr>

					<tr>
						<td><form:label path="lastname">Last Name</form:label></td>
						<td><div>
								<form:hidden path="id" value="${id}" />
								<form:input path="lastname" />

							</div></td>
					</tr>

					<tr>
						<td><form:label path="email">Email</form:label></td>
						<td><div>
								<form:input path="email" />

							</div></td>
					</tr>

					<tr>
						<td><form:label path="mobile">Mobile</form:label></td>
						<td><div>
								<form:input path="mobile" />

							</div></td>
					</tr>

					<tr>
						<td colspan="2" align="center">
							<div>
								<input type="submit" value="Update" />
							</div>
						</td>
					</tr>



				</table>
			</form:form>

		</c:if>


		<c:if test="${edits_user_one!=null}">

			<form:form method="POST" action="/edit_user_one"
				modelAttribute="form">

				<h2>User Edit Page</h2>

				<table>
					<form:hidden path="id" value="${id}" />
					<form:hidden path="password" />
					<tr>
						<td><form:label path="firstname">First name</form:label></td>
						<td><div>
								<form:input path="firstname" />

							</div></td>
					</tr>

					<tr>
						<td><form:label path="lastname">Last Name</form:label></td>
						<td><div>

								<form:input path="lastname" />

							</div></td>
					</tr>

					<tr>
						<td><form:label path="email">Email</form:label></td>
						<td><div>
								<form:input path="email" />

							</div></td>
					</tr>

					<tr>
						<td><form:label path="mobile">Mobile</form:label></td>
						<td><div>
								<form:input path="mobile" />

							</div></td>
					</tr>


					<tr>
						<td colspan="2" align="center">
							<div>
								<input type="submit" value="Update" />
							</div>
						</td>
					</tr>

				</table>
			</form:form>

		</c:if>

		<c:if test="${sessionScope.id!=null}">
			<c:if test="${Login_success!=null}">
				<h2 align="center">Welcome ${email}</h2>


				<center>


					<table border="1">
						<tr>
							<td>Id</td>
							<td>First Name</td>
							<td>Last Name</td>
							<td>Email</td>
							<td>Mobile</td>
							<td>Edit</td>
							<td>Delete</td>
						</tr>

						<tr>
							<td>${user_detail.getId()}</td>
							<td>${user_detail.getFirstname()}</td>
							<td>${user_detail.getLastname()}</td>
							<td>${user_detail.getEmail()}</td>
							<td>${user_detail.getMobile()}</td>
							<td><a href="<spring:url value='/edit_user_one?id=${user_detail.getId()}' />">edit</a></td>
							<td><a href="<spring:url value='/delete_user_one?id=${user_detail.getId()}' />">Delete</a></td>
						</tr>
					</table>
					<br> <a href="<spring:url value='/logout' />">Logout</a>
				</center>

			</c:if>
		</c:if>
	</center>
	
  <!-- Javascript functions -->
<!--   <script>
  
    // bind the on-change event for the input element (triggered when a file
    // is chosen)
    $(document).ready(function() {
      $("#upload-file-input").on("change", uploadFile);
    });
    
    /**
     * Upload the file sending it via Ajax at the Spring Boot server.
     */
    function uploadFile() {
      $.ajax({
        url: "/uploadFile",
        type: "POST",
        data: new FormData($("#upload-file-form")[0]),
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function () {
          // Handle upload success
          $("#upload-file-message").text("File succesfully uploaded");
        },
        error: function () {
          // Handle upload error
          $("#upload-file-message").text(
              "File not uploaded (perhaps it's too much big)");
        }
      });
    } // function uploadFile
  </script>
   -->
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.error {
	color: #ff0000;
	font-style: italic;
}
</style>
<script type="text/javascript">
	function maulik()
	{
		alert('ajax called');
		/* alert('Hello');
		var w1 = window.open('file:///D:/Data/Top%20Scorer%20Lesson%20Plan%20-%20Instruction%20Creation.pdf','wind1');

		
            String imgName="file:///D:/Data/Top%20Scorer%20Lesson%20Plan%20-%20Instruction%20Creation.pdf";
        
            BufferedImage bImage = ImageIO.read(new FileInputStream(imgName));//give the path of an image
              ByteArrayOutputStream baos = new ByteArrayOutputStream();
              ImageIO.write( bImage, "pdf", baos );
              baos.flush();
              byte[] imageInByteArray = baos.toByteArray();
          
              baos.close();                                   
       
             
		 w1.location.href='file:///D:/Data/Top%20Scorer%20Lesson%20Plan%20-%20Instruction%20Creation.pdf';  */
		/* w1.location.href='www.google.com'; */
        
	}
	
	</script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>eSense Admin Login</title>

<!-- Bootstrap Core CSS -->
<link href="../bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="../bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="../dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Please Sign In</h3>
					</div>
					<div class="panel-body">
						<form:form action="login_user" method="POST" modelAttribute="form">

							<c:if test="${Login_fail!=null}">
								<p style="color: red;" align="center">Enter Correct Email
									And Password</p>
							</c:if>

							<fieldset>
								<div class="form-group">
									<form:input path="email" class="form-control"
										placeholder="E-mail" />
									<form:errors path="email" cssClass="error" />
								</div>
								<div class="form-group">
									<form:password path="password" class="form-control"
										placeholder="Password" />
									<form:errors path="password" cssClass="error" />
								</div>
								<div class="checkbox">
									<label> <input name="remember" type="checkbox"
										value="Remember Me">Remember Me
									</label> <a href="#" onclick="maulik();">CLICK ME</a>

								</div>
								<!-- Change this to a button or input when using this as a form -->
								<input type="submit" value="Login"
									class="btn btn-lg btn-success btn-block">
								<%--  <form:button name="Login" value="Login"  class="btn btn-lg btn-success btn-block">Login</form:button> --%>
							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="../bower_components/jquery/dist/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>

</body>

</html>

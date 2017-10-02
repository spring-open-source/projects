<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="base_include.jsp" %>
<title>
	Sample Project
</title>
</head>

<body>
<%@ include file="base_header_sub.jsp" %>

	<div class="container">
		<br><br>
		<div class="page-header">
  			<h1>Blogs <small></small></h1>
		</div>
		
		<!-- FIRST SECTION -->
		<div class="container col-sm-9 panel panel-default" id="mainBlogSection">
			<div class="row panel-body">
  				<p>
  				Remember tab selected in Jquery tab. Use below script if you want to show same tab which was selected by user.
  				</p>
  				<span id="blgBody"></span>
			</div>
		</div>
		<!-- SECOND SECTION -->
		<div class="container col-sm-3 panel panel-default" id="optionalBlogSection">
			<div class="row panel-body">
  				<p>list 1</p>
  				<p>list 2</p>
  				<p>list 3</p>
  				<p>list 4</p>
  				<p>list 5</p>
  				<p>list 6</p>
  				<p>list 7</p>
  				<p>list 8</p>
			</div>
		
		</div>
	
	</div>
	
	<!-- == FOOTER ==  -->
	<%@ include file="base_footer.jsp" %>
	<!-- == FOOTER ==  -->
</body>

<script>
function loadBlog(){
	
	var bd = "";
	bd = "<p>\n<!doctype html>\n<html lang=en>\n<head>\n<meta charset=utf-8>\n<title>\n\tRemember Jquery Tab Selected</title> <link rel=stylesheet href=//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css> </head> <body onload=getActiveTab();> <div id=tabs> <ul> <li><a href=#tabs-1 onclick=setActiveTab(1);>TAB 1</a></li> <li><a href=#tabs-2 onclick=setActiveTab(2);>TAB 2</a></li> <li><a href=#tabs-3 onclick=setActiveTab(3);>TAB 3</a></li> </ul></p>";
	
	bd += "<p><!doctype html> <html lang=en> <head> <meta charset=utf-8> <title>Remember Jquery Tab Selected</title> <link rel=stylesheet href=//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css> </head> <body onload=getActiveTab();> <div id=tabs> <ul> <li><a href=#tabs-1 onclick=setActiveTab(1);>TAB 1</a></li> <li><a href=#tabs-2 onclick=setActiveTab(2);>TAB 2</a></li> <li><a href=#tabs-3 onclick=setActiveTab(3);>TAB 3</a></li> </ul></p>";
	
	bd += "<p><!doctype html> <html lang=en> <head> <meta charset=utf-8> <title>Remember Jquery Tab Selected</title> <link rel=stylesheet href=//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css> </head> <body onload=getActiveTab();> <div id=tabs> <ul> <li><a href=#tabs-1 onclick=setActiveTab(1);>TAB 1</a></li> <li><a href=#tabs-2 onclick=setActiveTab(2);>TAB 2</a></li> <li><a href=#tabs-3 onclick=setActiveTab(3);>TAB 3</a></li> </ul></p>";
	
	bd += "<p><!doctype html> <html lang=en> <head> <meta charset=utf-8> <title>Remember Jquery Tab Selected</title> <link rel=stylesheet href=//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css> </head> <body onload=getActiveTab();> <div id=tabs> <ul> <li><a href=#tabs-1 onclick=setActiveTab(1);>TAB 1</a></li> <li><a href=#tabs-2 onclick=setActiveTab(2);>TAB 2</a></li> <li><a href=#tabs-3 onclick=setActiveTab(3);>TAB 3</a></li> </ul></p>";
	
	bd += "<p><!doctype html> <html lang=en> <head> <meta charset=utf-8> <title>Remember Jquery Tab Selected</title> <link rel=stylesheet href=//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css> </head> <body onload=getActiveTab();> <div id=tabs> <ul> <li><a href=#tabs-1 onclick=setActiveTab(1);>TAB 1</a></li> <li><a href=#tabs-2 onclick=setActiveTab(2);>TAB 2</a></li> <li><a href=#tabs-3 onclick=setActiveTab(3);>TAB 3</a></li> </ul></p>";
	
	$('#blgBody').text(bd)
}

loadBlog();
</script>
</html>
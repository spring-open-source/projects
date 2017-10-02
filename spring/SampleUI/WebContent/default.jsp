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
<%@ include file="base_header_main.jsp" %>
	<div class="container" id="homeContainer">
		
<!-- =========================================  COURESL      ============= -->
		<br><br>		
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
			</ol>
			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="images/11.jpg" alt="Image 1" style="max-height: 485px;" width="460" height="485px">
				</div>
				<div class="item">
					<img src="images/22.jpg" alt="Image 1" style="max-height: 485px;" width="460" height="485px">
				</div>
			</div>
			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a>
			<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next"> 
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
<!-- =========================================  COURESL ENDS ============= -->
		
		<div class="text-center" id="aboutdiv">
			<h2><span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>
				About <small>CompaNY</small>
			</h2>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit... 
			Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...Ut enim ad minim veniam,</p> 
			<p>quis nostrud exercitation ullamco laboris...</p>
		</div>
		
		
		<div class="row" id="servicesdiv">
			<div class="col-sm-12 text-center page-header">
				<h2>
					<span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span>
					Services <small>wHat wE Do...</small>
				</h2>
			</div>
			<div class="row">
				<div class="col-sm-4 text-center">
					<!-- <img src="images/1.jpg" class="img-thumbnail" alt="Cinque Terre" width="256" height="236"> -->
					<h3>Column 1</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
					<p>Ut enim ad minim veniam, quis nostrud exercitation ullamco
						laboris...</p>
				</div>
				<div class="col-sm-4 text-center">
					<!-- <img src="images/2.jpg" class="img-thumbnail" alt="Cinque Terre" width="256" height="236"> -->
					<h3>Column 2</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
					<p>Ut enim ad minim veniam, quis nostrud exercitation ullamco
						laboris...</p>
				</div>
				<div class="col-sm-4 text-center">
					<!-- <img src="images/1.jpg" class="img-thumbnail" alt="Cinque Terre" width="256" height="236"> -->
					<h3>Column 2</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
					<p>Ut enim ad minim veniam, quis nostrud exercitation ullamco
						laboris...</p>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-4 text-center">
					<!-- <img src="images/1.jpg" class="img-thumbnail" alt="Cinque Terre" width="256" height="236"> -->
					<h3>Column 1</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
					<p>Ut enim ad minim veniam, quis nostrud exercitation ullamco
						laboris...</p>
				</div>
				<div class="col-sm-4 text-center">
					<!-- <img src="images/2.jpg" class="img-thumbnail" alt="Cinque Terre" width="256" height="236"> -->
					<h3>Column 2</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
					<p>Ut enim ad minim veniam, quis nostrud exercitation ullamco
						laboris...</p>
				</div>
				<div class="col-sm-4 text-center">
					<!-- <img src="images/1.jpg" class="img-thumbnail" alt="Cinque Terre" width="256" height="236"> -->
					<h3>Column 2</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
					<p>Ut enim ad minim veniam, quis nostrud exercitation ullamco
						laboris...</p>
				</div>
			</div>
		</div>
		
		<div class="row" id="teamdiv">
			<div class="col-sm-12 text-center page-header">
				<h2>
					<span class="glyphicon glyphicon-th" aria-hidden="true"></span>
					Team <small>ouR growInG tEam...</small>
				</h2>
			</div>
			<div class="col-sm-4 text-center">
				<img src="images/1.jpg" class="img-circle" alt="Cinque Terre" width="256" height="236">
				<h3>Column 1</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
				<p>Ut enim ad minim veniam, quis nostrud exercitation ullamco
					laboris...</p>
			</div>
			<div class="col-sm-4 text-center">
				<img src="images/2.jpg" class="img-circle" alt="Cinque Terre" width="256" height="236">
				<h3>Column 2</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
				<p>Ut enim ad minim veniam, quis nostrud exercitation ullamco
					laboris...</p>
			</div>
			<div class="col-sm-4 text-center">
				<img src="images/1.jpg" class="img-circle" alt="Cinque Terre" width="256" height="236">
				<h3>Column 2</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
				<p>Ut enim ad minim veniam, quis nostrud exercitation ullamco
					laboris...</p>
			</div>
		</div>
		
		<div class="text-center page-header" id="skillsdiv">
			<h2>
				<span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span>
				Skills <small>what we better do...</small>
			</h2>
		</div>
		<div class="row text-center">
			<p>
				<span class="col-sm-2">&nbsp;</span>
				<span class="col-sm-2">Skill 1</span>
				<span class="col-sm-2">Skill 2</span>
				<span class="col-sm-2">Skill 3</span>
				<span class="col-sm-2">Skill 4</span>
				<span class="col-sm-2">&nbsp;</span>
			</p> 
		</div>
		
		<div class="row" id="contactusdiv">
			<div class="col-sm-12 text-center page-header">
				<h2>
					<span class="glyphicon glyphicon-phone-alt" aria-hidden="true"></span>
					Contact us <small>if your head is puzzeling around...</small>
				</h2>
			</div>
			<div class="col-sm-12 text-center">
				<form role="form">
				  <div class="col-sm-6 form-group">
				    <!-- <label for="email">Email address:</label> -->
				    <input type="email" class="form-control" id="email" placeholder="Email address">
				  </div>
				  <div class="col-sm-6 form-group">
				    <!-- <label for="subject">Subject:</label> -->
				    <input type="text" class="form-control" id="pwd" placeholder="Subject">
				  </div>
				  <div class="col-sm-12 form-group">
				    <!-- <label for="message">Message:</label> -->
				    <textarea class="form-control" rows="5" id="comment" placeholder="Message"></textarea>
				  </div>
				  <div class="col-sm-12 form-group">
				  	<button type="submit" class="btn btn-default">Submit</button>
				  </div>
				</form>
			</div>
		</div>
	</div>
	
	
	
	<div class="container" id="blogsContainer">
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

</html>
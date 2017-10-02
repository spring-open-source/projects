<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header col-sm-5">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="default.jsp">
				<span class="glyphicon glyphicon-king" aria-hidden="true"></span>
				Sample Project
			</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse col-sm-7">
			<ul class="nav navbar-nav">
				<li>
					<a href="#" onclick="gotoHome();">
						<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
						Home
					</a>
				</li>
				<li>
					<a href="#" onclick="gotoAbout();">
						<span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>
						About
					</a>
				</li>
				<!-- <li>
					<a href="default.jsp">
						<span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span>
						Portfolio
					</a>
				</li> -->
				<li>
					<a href="#" onclick="gotoServices();">
						<span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span>
						Services
					</a>
				</li>
				<li>
					<a href="#" onclick="gotoTeam();">
						<span class="glyphicon glyphicon-th" aria-hidden="true"></span>
						Team
					</a>
				</li>
				<li>
					<a href="#" onclick="gotoSkills();">
						<span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span>
						Skills
					</a>
				</li>
				<li>
					<!-- <a href="blogs.jsp">
						<span class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>
						Blogs
					</a> -->
					<a href="#" onclick="gotoBlogs();">
						<span class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>
						Blogs
					</a>
				</li>
				<li>
					<a href="#" onclick="gotoContactUs();">
						<span class="glyphicon glyphicon-phone-alt" aria-hidden="true"></span>
						Contact Us
					</a>
				</li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>

<script>

function gotoHome(){
	showMainDiv();
	$('html, body').animate({
        scrollTop: $('#myCarousel').offset().top - 70
    }, 1700);
}
function gotoAbout(){
	showMainDiv();
	$('html, body').animate({
        scrollTop: $('#aboutdiv').offset().top - 70
    }, 1700);
}
function gotoServices(){
	showMainDiv();
	$('html, body').animate({
        scrollTop: $('#servicesdiv').offset().top
    }, 1700);
}
function gotoTeam(){
	showMainDiv();
	$('html, body').animate({
        scrollTop: $('#teamdiv').offset().top
    }, 1700);
}
function gotoSkills(){
	showMainDiv();
	$('html, body').animate({
        scrollTop: $('#skillsdiv').offset().top
    }, 1700);
}
function gotoContactUs(){
	showMainDiv();
	$('html, body').animate({
        scrollTop: $('#contactusdiv').offset().top
    }, 1700);
}

function showMainDiv(){
	$( "#blogsContainer" ).fadeOut( "slow", function() {
	});
	/* $( "#homeContainer" ).fadeIn( "slow", function() {
	}); */	
}
function gotoBlogs(){
	/* $( "#homeContainer" ).fadeOut( "slow", function() {
	}); */
	$( "#blogsContainer" ).fadeIn( "slow", function() {
	});
	
	$('html, body').animate({
        scrollTop: $('#blogsContainer').offset().top
    }, 1700);
	
	loadBlog();
}
showMainDiv();

function loadBlog(){
	
	var bd = "";
	bd = "<p>\n<!doctype html>\n<html lang=en>\n<head>\n<meta charset=utf-8>\n<title>\n\tRemember Jquery Tab Selected</title> <link rel=stylesheet href=//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css> </head> <body onload=getActiveTab();> <div id=tabs> <ul> <li><a href=#tabs-1 onclick=setActiveTab(1);>TAB 1</a></li> <li><a href=#tabs-2 onclick=setActiveTab(2);>TAB 2</a></li> <li><a href=#tabs-3 onclick=setActiveTab(3);>TAB 3</a></li> </ul></p>";
	
	bd += "<p><!doctype html> <html lang=en> <head> <meta charset=utf-8> <title>Remember Jquery Tab Selected</title> <link rel=stylesheet href=//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css> </head> <body onload=getActiveTab();> <div id=tabs> <ul> <li><a href=#tabs-1 onclick=setActiveTab(1);>TAB 1</a></li> <li><a href=#tabs-2 onclick=setActiveTab(2);>TAB 2</a></li> <li><a href=#tabs-3 onclick=setActiveTab(3);>TAB 3</a></li> </ul></p>";
	
	bd += "<p><!doctype html> <html lang=en> <head> <meta charset=utf-8> <title>Remember Jquery Tab Selected</title> <link rel=stylesheet href=//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css> </head> <body onload=getActiveTab();> <div id=tabs> <ul> <li><a href=#tabs-1 onclick=setActiveTab(1);>TAB 1</a></li> <li><a href=#tabs-2 onclick=setActiveTab(2);>TAB 2</a></li> <li><a href=#tabs-3 onclick=setActiveTab(3);>TAB 3</a></li> </ul></p>";
	
	bd += "<p><!doctype html> <html lang=en> <head> <meta charset=utf-8> <title>Remember Jquery Tab Selected</title> <link rel=stylesheet href=//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css> </head> <body onload=getActiveTab();> <div id=tabs> <ul> <li><a href=#tabs-1 onclick=setActiveTab(1);>TAB 1</a></li> <li><a href=#tabs-2 onclick=setActiveTab(2);>TAB 2</a></li> <li><a href=#tabs-3 onclick=setActiveTab(3);>TAB 3</a></li> </ul></p>";
	
	bd += "<p><!doctype html> <html lang=en> <head> <meta charset=utf-8> <title>Remember Jquery Tab Selected</title> <link rel=stylesheet href=//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css> </head> <body onload=getActiveTab();> <div id=tabs> <ul> <li><a href=#tabs-1 onclick=setActiveTab(1);>TAB 1</a></li> <li><a href=#tabs-2 onclick=setActiveTab(2);>TAB 2</a></li> <li><a href=#tabs-3 onclick=setActiveTab(3);>TAB 3</a></li> </ul></p>";
	
	$('#blgBody').text(bd)
}

</script>



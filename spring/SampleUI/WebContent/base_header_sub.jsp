<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header col-sm-9">
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
		<div id="navbar" class="collapse navbar-collapse col-sm-3">
			<ul class="nav navbar-nav">
				<li>
					<a href="default.jsp">
						<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
						Home
					</a>
				</li>
				<li class="active">
					<a href="#">
						<span class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>
						Blogs
					</a>
				</li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>

<script>

function gotoHome(){
	$('html, body').animate({
        scrollTop: $('#myCarousel').offset().top - 70
    }, 2000);
}
function gotoAbout(){
	$('html, body').animate({
        scrollTop: $('#aboutdiv').offset().top - 70
    }, 2000);
}
function gotoServices(){
	$('html, body').animate({
        scrollTop: $('#servicesdiv').offset().top
    }, 2000);
}
function gotoTeam(){
	$('html, body').animate({
        scrollTop: $('#teamdiv').offset().top
    }, 2000);
}
function gotoSkills(){
	$('html, body').animate({
        scrollTop: $('#skillsdiv').offset().top
    }, 2000);
}
function gotoContactUs(){
	$('html, body').animate({
        scrollTop: $('#contactusdiv').offset().top
    }, 2000);
}

</script>
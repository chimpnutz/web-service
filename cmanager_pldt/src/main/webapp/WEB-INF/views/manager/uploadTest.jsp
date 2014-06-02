<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>

<html lang="en">
<head>
	<meta charset="utf-8" />

	
	
	<style type="text/css">
		* {
			margin: 0;
			padding: 0;
			outline: 0;
		}
		
		html, body{
			height: 100%;
			background: #F2F2F2;
			width:100%;
		}

		body, a{
			font: normal 16px Helvetica, Verdana, Geneva, sans-serif;
			color: #3F3F3F;		
		}
		
		.container{
			min-height: 100%;
			height: auto !important;
			height: 100%;
			margin: 0 auto -30px;
			width:100%;
			background-color: #f1f1f1;
		}
		
		.container:after{
			content: '';
			display: block;
			height: 30px;
			clear: both;
		}
		
		.footer{
			height: 12px;
			padding: 8px 0;
			background: #FFF;
			border-top: 1px solid #51C1F1;
			font-size: 12px;
			text-align: center;
			width:100%;
		}
	</style>


	<link rel="stylesheet" href="resources/css/istyle.css" />

	<link rel="stylesheet" href="resources/css2/managertable.css" />

	
	<link rel="stylesheet" href="resources/css/font-awesome.css" />
<link rel="stylesheet" href="resources/css/font-awesome.min.css" />
<link rel="stylesheet" href="resources/css/recentmenus.css" />
	<link rel="stylesheet" href="resources/css/nav.css" />
	<title>Circles</title>
</head>
<body>
	<div class="container">
		<div class="header clearfix">
			
			
			<div class="nav">
				
				<ul class="menu">
					<li><a href="salesmtd"><i class="fa fa-usd"></i>&nbsp;SALES</a></li>
					<li><a href="agent"><i  class="icon-agent" style="font-size:20px;"></i>&nbsp;AGENTS</a></li>		
					<li><a href="recent"  class="selected"><i class="fa fa-clock-o"></i>&nbsp;RECENT</a></li>	
							<li><a href="settings"><i class="fa fa-mobile-phone"></i>&nbsp;MOBILE</a></li>		
					<li><a href="logout"><i class="fa fa-laptop"></i>&nbsp;Log out</a></li>
					
				</ul>
			</div><!-- End of Navigation -->
			



		</div><!-- End of Header -->
	
			<div class="allapps">

		<section id="main">
		<article class="module width_3_quarter">
			<div class="linespace">
			</div>
			<div id="tab1" class="tab_content">

			
			<c:forEach items="${application}" var="applications">
					${applications.getApplication_id()}<br>
					${applications.getFirstName()}&nbsp;${applications.getLastName()}<br>
					${applications.getPlan_code()}<br>
					${applications.getPhone_code()}<br>
					${applications.getStatus()}<br>
					${applications.getCreated()}<br>

			</c:forEach>

		
			
		
			
		</div><!-- end of tab_container -->
		
		</article><!-- end of content manager article -->



		<div class="spacer"></div>
	</section>



			</div>

	</div><!-- End of Container -->
	
	<div class="footer">
		<p>&copy; 2013 Circles</p>
	</div>
</body>
</html>
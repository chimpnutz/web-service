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
			<table class="tablesorter" > 
			<thead> 
				<tr> 
   					<th>Application ID</th>
    				<th>Name of Applicant</th> 
    				<th>Plan Code</th> 
    				<th>Phone Code</th> 
    				<th>Status</th>
    				<th>Date </th>
    			
				</tr> 
			</thead> 
			<tbody> 
			
			<c:forEach items="${application}" var="applications">
			
				<tr>
					
						<td><a href="view?applicationid=${applications.getApplication_id()}" style="font-size:12px">${applications.getApplication_id()}</a></td>
						<td>${applications.getFirstName()}&nbsp;${applications.getLastName()}</td>
						<td>${applications.getPlan_code()}</td>
						<td>${applications.getPhone_code()}</td>
						<c:if test="${applications.getStatus().equals('0')}">
						<td>Submitted</td>
						</c:if>
						<c:if test="${applications.getStatus().equals('1')}">
						<td>Approved</td>
						</c:if>
						<c:if test="${applications.getStatus().equals('2')}">
						<td>Ongoing</td>
						</c:if>
						<c:if test="${applications.getStatus().equals('3')}">
						<td>Declined</td>
						</c:if>
						<td>${applications.getCreated()}</td>
					
				</tr>
			</c:forEach>
			</tbody> 
			</table>
		
			
		
			
		</div><!-- end of tab_container -->
		
		</article><!-- end of content manager article -->



		<div class="spacer"></div>
	</section>



			</div>

			<div class="recentmenus">
			<ul class="cssmenu2">
	<li class="allapp"><a href="recent" class="selected2" title="allapp"><span class="displace2">AllApp</span></a></li>
	<li class="approved"><a href="approved" title="approved"><span class="displace2">approved</span></a></li>
	<li class="encoded"><a href="ongoing" title="encoded"><span class="displace2">encoded</span></a></li>
	<li class="notapproved"><a href="notapproved" title="notapproved"><span class="displace2">not approved</span></a></li>
			
</ul>
			</div>


	</div><!-- End of Container -->
	
	<div class="footer">
		<p>&copy; 2013 Circles</p>
	</div>
</body>
</html>
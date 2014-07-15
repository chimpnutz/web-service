<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
			margin-top: 80px;
			width: 1350px;
		}

		
	</style>

		<link rel="stylesheet" href="resources/css/istyle.css" type="text/css"/>

	

		<link rel="stylesheet" href="resources/css2/agenttable.css" type="text/css"/>

	
	<link rel="stylesheet" href="resources/css/font-awesome.css" type="text/css"/>
<link rel="stylesheet" href="resources/css/font-awesome.min.css" type="text/css"/>
	<link rel="stylesheet" href="resources/css/agent.css" type="text/css"/>
	<link rel="stylesheet" href="resources/css/agentlist.css" type="text/css"/>
	<title>Circles</title>
</head>
<body>
	<div class="container">
<div class="boxes"></div>
		<div class="header clearfix">
			
			
			<div class="nav">
				
				<ul class="menu">
					<li><a href="salesmtd.html"><i class="fa fa-usd"></i>&nbsp;SALES</a></li>
					<li><a href="agent.html" class="selected"><i class="icon-agent" style="font-size:20px;"></i>&nbsp;AGENTS</a></li>		
					<li><a href="recent.html"><i class="fa fa-clock-o"></i>&nbsp;RECENT</a></li>
							<li><a href="viewproduct"><i class="fa fa-cubes"></i>&nbsp;PRODUCTS</a></li>		
				<li><a href="logout.html"><i class="fa fa-laptop"></i>&nbsp;LOG OUT</a></li>
					
				</ul>
			</div><!-- End of Navigation -->
			
		</div><!-- End of Header -->
<c:if test="${role=='manager' }">
		<div class="application">
		<!--<text class="preactive"><a href="agent.html" >Applications</a></text>-->
		<text class="preactive"><a href="addagent.html">Add Agent</a></text>
		<!-- <text  style="margin-left:15px;"><a href="addagent.html">Add Agent</a></text> -->
		</div>


<table cellspacing="0" cellpadding="0" border="0" style=" margin-left:auto; margin-right:auto; margin-top:50px;">
<c:forEach var="data" items="${agent}">
   <tbody>
      <tr height="35">
           <td height="35" width="120"> ${data.username}</td>
               <td width="800px">
		<article class="module width_3_quarter">
			
			<div id="tab1" class="tab_content">
			<table class="tablesorter" > 
			<thead> 
					<tr > 
   					<th> </th>
    				<th>Jan</th> 
    				<th>Feb</th> 
    				<th>Mar </th> 
    				<th>Apr </th>
    				<th>May </th>
    				<th>Jun </th>
    				<th>Jul </th>
    				<th>Aug </th>
    				<th>Sep </th>
    				<th>Oct </th>
    				<th>Nov </th>
    				<th>Dec </th>
    				<th>Total </th>

				</tr> 
			</thead> 
			<tbody> 
				<tr class="colorth"> 
   					<td></td> 
    				<td>400</td> 
    				<td>400</td> 
    				<td>400</td> 
    			  	<td>400</td> 
    			  	<td>400</td>
    			  	<td>400</td>
    			  	<td>400</td>
    			  	<td>400</td>
    			  	<td>400</td>
    			  	<td>400</td>
					<td>400</td>
					<td>400</td>
					<td>400</td>
				</tr> 
				<tr class="colortd1"> 
   					<td></td> 
    				<td>300</td> 
    				<td>300</td> 
    				<td>300</td>  
    			  	<td>300</td> 
    			  	<td>300</td> 
    			  	<td>300</td> 
    			  	<td>300</td> 
    			  	<td>300</td> 
    			  	<td>300</td> 
    			  	<td>300</td> 
					<td>300</td> 
					<td>300</td> 
					<td>300</td> 
				</tr> 

				<tr class="colortd2"> 
   					<td></td> 
    				<td>100%</td> 
    				<td>100%</td> 
    				<td>100%</td> 
    			  	<td>100%</td> 
    			  	<td>100%</td> 
    			  	<td>100%</td> 
    			  	<td>100%</td> 
    			  	<td>100%</td> 
    			  	<td>100%</td> 
    			  	<td>100%</td> 
					<td>100%</td> 
					<td>100%</td> 
					<td>100%</td> 
				</tr> 
			
			</tbody> 
			</table>
		</div>
		</article>
	</td>  	
		
</tr>
</tbody>
</c:forEach>
</table>
</c:if>
<c:if test="${role=='encoder' }">
<center style="margin-top:100px; font-size:20px;">Access Denied</center>
</c:if>
	</div><!-- End of Container -->



	

	<div class="clearme"></div>
	<c:if test="${role=='manager' }">
	<div class="footer">
		<p>&copy; 2013 Circles</p>
	</div>
	</c:if>
	<c:if test="${role=='encoder' }">
	<div class="footer" style="margin-top:00px">
		<p>&copy; 2013 Circles</p>
	</div>
	</c:if>
</body>
</html>
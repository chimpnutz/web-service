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
			width:1360px;
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
			width:1360px;
		}
.nla{

width:500px;
}

.tatatext{
	  line-height: 1.75em;
	font-family: helvetica !important;
	font-weight: lighter !important;
	font-size: 14px;

}



  .centerside{


 margin-left: 205px;
  min-height:300px;
  width: 350px;
  display: inline-block; 
  margin-top: 15px;

  padding-left: 30px;
  padding-top: 30px;
 }


 .addbot{
  margin-bottom: 20px;
 }

 .amiddle{
 	text-align: center;
 	vertical-align: middle;
 }


 .adjustheight{
 	min-height: 520px !important;
 }
 .search-box{
 	width: 400px !important; 

 }
 .newbox{
 	width: 700px !important;
 }
.search-box,.close-icon {
	position: relative;
	padding: 10px;
}
.search-box {
	border: 1px solid #ccc;
  outline: 0;
  border-radius: 0px;
}
.search-box:focus {
	border: 1px solid #bebede;
}
.close-icon {
	position:absolute;
	border:1px solid transparent;
	background-color: transparent;
	display: inline-block;
	vertical-align: middle;
  outline: 0;
  cursor: pointer;
  margin-left:164px;
  margin-top:-42px;
 
}
.close-icon:after {

	content: "X";
	display: block;
	width: 15px;
	height: 15px;
	background-color: #FA9595;
	z-index:1;
	bottom: 0;
	margin: auto;
	padding: 2px;
	text-align: center;
	color: white;
	font-weight: normal;
	font-size: 12px;
	box-shadow: 0 0 2px #E50F0F;
	cursor: pointer;
}
.search-box:not(:valid) ~ .close-icon {
	display: none;
}

.blocking{
	width:350px;
	min-height:50px;
	display:inline-table;
	text-align:left;
}

	</style>
	

       	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css' />
		
<!--  -->

	<link rel="stylesheet" href="resources/css/istyle.css" />

	<link rel="stylesheet" href="resources/css/structure2.css" />

	
	<link rel="stylesheet" href="resources/css/font-awesome.css" />
<link rel="stylesheet" href="resources/css/font-awesome.min.css" />

	<link rel="stylesheet" href="resources/css/nav.css" />
	
	<title>Circles</title>
</head>
<body>
	<div class="container">
<div class="boxes"> </div>
		<div class="header clearfix">
			
			
			<div class="nav">
				
				<ul class="menu">
					<li><a href="salesmtd"><i class="fa fa-usd"></i>&nbsp;SALES</a></li>
					<li><a href="agent"><i  class="icon-agent" style="font-size:20px;"></i>&nbsp;AGENTS</a></li>		
					<li><a href="recent" ><i class="fa fa-clock-o"></i>&nbsp;RECENT</a></li>	
							<li><a href="viewproduct"  class="selected"><i class="fa fa-cubes"></i>&nbsp;PRODUCTS</a></li>		
					<li><a href="logout"><i class="fa fa-laptop"></i>&nbsp;LOG OUT</a></li>
					
				</ul>
	</div><!-- End of Navigation -->
			



		</div><!-- End of Header -->
	
			

<div  class="box white adjustheight newbox" style="text-align:center;">
	 

<c:forEach var="data" items="${devices}">
		
		<div>
		<div style="padding-bottom:30px;color:#666666;"></div>
			<font face="Helvetica" size="4" color="#666666"><b>${data.device_name}</b></font>	
		<div style="padding-bottom:30px;color:#666666;"></div>
			<img src="resources/uploaded/${data.device_image}" width="250px" height="250px">
		<div style="padding-bottom:30px;color:#666666;"></div>
			<font face="Helvetica" size="4" color="#666666"><b>Description:</b></font>
			<div style="padding-bottom:30px;color:#666666;"></div>
			<div class="blocking">			
				${data.description}			
			</div>	
		</div>
	</c:forEach>


</div>










		
	</div><!-- End of Container -->
	
	<div class="footer">
		<p>&copy; 2013 Circles</p>
	</div>

	


</body>
</html>
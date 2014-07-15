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
 	min-height: 300px !important;
 }
 .search-box{
 	width: 300px !important; 

 }
 .newbox{
 	width: 700px !important;
 	
 }
/*xbox*/

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
  margin-left:67px;
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

	</style>
	

       	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css' />
		


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
	 


<form action="saveDevice"  method="POST" ENCTYPE="multipart/form-data">
	<table style="margin-left:80px;">
	<tr>
		<td colspan="2">
			<c:if test="${check=='success'}">
				<label style="margin-top:-10px;width:687px;background-color:green;font-size:12px;"><font color="#FFFFFF">${valid}</font></label>
			</c:if>		
		</td>
	</tr>
	<tr>
		<td colspan="2">
	 		<label style="margin-top:70px;font-size:18px;"><font face="Helvetica" color="#666666">Add Device/Hardware</font></label>
	 	<td>
	 </tr>	
	 
	 <tr>
 		<td class="tdspace"></td>
	</tr>
	 
	 <tr>
	 	<td><font face="Helvetica" size="2" color="#666666"><b>Device/Hardware:</b></font></td>
	 	<td>
	 		<input class="search-box" value="" size="32" name="device_name" type="text" required/>
	 		<button class="close-icon" type="reset"></button>
	 		 <div style="padding-bottom:8px;color:#666666;"></div>
	 	</td>
	 </tr>
	 
	 <tr>
 		<td class="tdspace"></td>
	</tr>
       
     <tr>
     	<td valign="top"><font face="Helvetica" size="2" color="#666666"><b>Description:</b></font></td></td>
     	<td><textarea style="resize:none"  name="dev_desc"   cols="49" rows="10">
		</textarea></td>
     </tr> 
     
     <tr>
 		<td class="tdspace"></td>
	</tr>
    
     <tr>
     	<td><font face="Helvetica" size="2" color="#666666"><b>Image:</b></font></td></td>
     	<td align="left">
     		<input name="dev_image" type="file" id="file"/>  
     		 <div style="padding-bottom:8px;color:#666666;"> </div>
     	</td>
     	
     </tr>
     
    <tr>
 		<td class="tdspace"></td>
	</tr>
     
     <tr>
     	<td colspan="2">
     		<div style="margin-top:20px;">
	     		
	        	<button class="btn btn-blue" type="submit" name="add">Add</button>
	        	<a class="btn btn-red" onclick="goBack()">Cancel</a>
    		</div>
     	</td>
     	
     </tr>
     
     <tr>
 		<td class="tdspace">
 			<div style="margin-top:50px;"></div>
 		</td>
	</tr>

	</table>
</form>

</div>

 <!-- / jquery [required] -->
    <script src="resources/js/jquery.js" type="text/javascript"></script>

    <!-- / bootstrap [required] -->
    <script src="resources/js/bootstrap.js" type="text/javascript"></script>
    <!-- / modernizr -->
<script src="resources/js/jquery-1.7.2.js"></script>

<script>
function goBack() {
    window.history.back();
}
</script>





		
	</div><!-- End of Container -->
	
	<div class="footer">
		<p>&copy; 2013 Circles</p>
	</div>

	



</body>
</html>
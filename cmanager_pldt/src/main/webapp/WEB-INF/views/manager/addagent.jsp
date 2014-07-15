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
 	min-height: 250px !important;
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


	</style>
	

       	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css' />
		
<link rel="stylesheet" type="text/css" href="resources/css/xbox.css">

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
					<li><a href="agent"  class="selected"><i  class="icon-agent" style="font-size:20px;"></i>&nbsp;AGENTS</a></li>		
					<li><a href="recent" ><i class="fa fa-clock-o"></i>&nbsp;RECENT</a></li>	
							<li><a href="viewproduct" ><i class="fa fa-cubes"></i>&nbsp;PRODUCTS</a></li>		
					<li><a href="logout"><i class="fa fa-laptop"></i>&nbsp;LOG OUT</a></li>
					
				</ul>
			</div><!-- End of Navigation -->
			



		</div><!-- End of Header -->
	
			

<div  class="box white adjustheight newbox" style="text-align:center;">
	 


<form action="saveAgent"  method="POST">
		<table style="margin-left:auto; margin-top:35px; margin-right:auto;" width="600px" border="0" cellpadding="5" cellspacing="0" bgcolor="transparent" style="text-align:left;">
		<tr>
			<td colspan="3">
			<c:if test="${check=='success'}">
				<label style="margin-top:-10px;background-color:green;font-size:12px;"><font color="#FFFFFF">${valid}</font></label>
			</c:if>	
			<c:if test="${check=='fail'}">
				<label style="margin-top:-10px;background-color:red;font-size:12px;"><font color="#FFFFFF">${valid}</font></label>
			</c:if>			
				 <label style="margin-top:20px;font-size:18px;">Add Agent</label>
			</td>
		</tr>
		
		<tr>
    	<td colspan="3" align="right">
    		 <span style="color:red;"><small>*</small><font face="Helvetica" size="1" color="#666666">Indicates required field</font></span>
		    <div style="padding-bottom:12px;color:#666666;"></div>
    	</td>
    </tr>
		
		<tr>
			<td align="right">
				<font face="Helvetica" size="2" color="#666666"><b>Username:</b></font> <span style="color:red;"><small>*</small></span>
				<td align="center">
	 			<input id="field" class="search-box4" value="" size="52" name="agent_user" type="text" required/>
	 			<a class="close-icon4" id="reset"></a>
	 		</td>
			
		</tr>	

		<tr>
			<td align="right">
				<font face="Helvetica" size="2" color="#666666"><b>Email:</b></font> <span style="color:red;"><small>*</small></span>
				<td align="center">
	 			<input id="field2" class="search-box5" value="" size="52" name="agent_email" type="text" required/>
	 			<a class="close-icon5" id="reset2"></a>
	 		</td>
			
		</tr>	

		<tr>
			<td align="right">
				<font face="Helvetica" size="2" color="#666666"><b>First Name:</b></font> <span style="color:red;"><small>*</small></span>
				<td align="center">
	 			<input id="field3" class="search-box6" value="" size="52" name="agent_name" type="text" required/>
	 			<a class="close-icon6" id="reset3"></a>
	 		</td>
			
		</tr>	

		<tr>
			<td align="right">
				<font face="Helvetica" size="2" color="#666666"><b>Last Name:</b></font> <span style="color:red;"><small>*</small></span>
				<td align="center">
	 			<input id="field4" class="search-box7" value="" size="52" name="agent_last" type="text" required/>
	 			<a class="close-icon7" id="reset4"></a>
	 		</td>
			
		</tr>	

     	<tr>
			<td align="right">
				<font face="Helvetica" size="2" color="#666666"><b>Password:</b></font> <span style="color:red;"><small>*</small></span>
				<td align="center">
	 			<input id="field5" class="search-box9" value="" size="52" name="agent_pass" type="password" required/>
	 			<a class="close-icon9" id="reset5"></a>
	 		</td>
			
		</tr>	
		
		<tr>
			<td align="right">
				<font face="Helvetica" size="2" color="#666666"><b>Re-enter password:</b></font> <span style="color:red;"><small>*</small></span>
				<td align="center">
	 			<input id="field6" class="search-box10" value="" size="52" name="agent_repass" type="password" required/>
	 			<a class="close-icon10" id="reset6"></a>
	 		</td>
			
		</tr>	
       
        
        <tr>
        	<td colspan="3">
        		 <div style="margin-top:20px;">
        			<button class="btn btn-blue" type="submit" name="add">Add</button>
        			<div style="padding-bottom:20px;color:#666666;"></div>
    			</div>
        	</td>
        </tr>

		</table>
	 	
	
	
</form>

</div>










		
	</div><!-- End of Container -->
	
	<div class="footer">
		<p>&copy; 2013 Circles</p>
	</div>

<script src="resources/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
    document.getElementById('reset').onclick= function() {
        var field= document.getElementById('field');
        field.value= field.defaultValue;
    };
    document.getElementById('reset2').onclick= function() {
        var field= document.getElementById('field2');
        field.value= field.defaultValue;
    };
    document.getElementById('reset3').onclick= function() {
        var field= document.getElementById('field3');
        field.value= field.defaultValue;
    };
     document.getElementById('reset4').onclick= function() {
        var field= document.getElementById('field4');
        field.value= field.defaultValue;
    };
     document.getElementById('reset5').onclick= function() {
        var field= document.getElementById('field5');
        field.value= field.defaultValue;
    };
    document.getElementById('reset6').onclick= function() {
        var field= document.getElementById('field6');
        field.value= field.defaultValue;
    };
    document.getElementById('reset7').onclick= function() {
        var field= document.getElementById('field7');
        field.value= field.defaultValue;
    };
</script>	



</body>
</html>
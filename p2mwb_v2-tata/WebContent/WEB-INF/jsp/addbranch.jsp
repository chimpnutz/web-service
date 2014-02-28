<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="../css/images/favicon.ico" >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval() %>;URL='logout.html?expire=yes'">
<title>Pay PhilExchange</title>


<LINK href="../css/style.css" rel="stylesheet" type="text/css">
<LINK href="../css/form.css" rel="stylesheet" type="text/css">
<LINK href="../css/demo1.css" rel="stylesheet" type="text/css">
<link href="../css/unibar.css" rel="stylesheet" type="text/css" />

<script src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/dropdown.js"></script>


		<script type="text/javascript" charset="utf-8">
		
		$(document).ready(function()
		{ 
	
		    $("#managebranchesForm").validate({
		    	rules: {
		    	branchname: {
		        required: true
		        },
		        username: {
			    required: true
			    },
			    password: {
				required: true
				}, 
				confirmpassword: {
			    required: true,
			    equalTo: "#password"
				}
		    },  messages: {
		    	branchname: 
		        {
		
		      	  required: "Please Input Branch Name "

		        },
		        username: 
		        {
		
		      	  required: "Please Input Username "

		        },
		        password: 
		        {
		
		      	  required: "Please Input Password "

		        },
		        confirmpassword: 
		        {
		
		      	  required: "Please Confirm Your Password ",
		      	  equalTo:  "Password must be the same as above "

		        }   
		    }
		  });
		    
		    $('#branchname').val('');
		    $('#username').val('');
		    $('#password').val('');
		    $('#confirmpassword').val('');

			
		});
		
		</script>
		
		  <style type="text/css"> 
    label.error { color: red; font-family: Verdana; font-size: 11px;  line-height: 14px; margin-right: -160px;}
	  </style>


</head>

<body>

<div id="page">

<div id="unibarlogo"><a id="main" href="logout.html?redirect=main"><img src="../css/images/payexchangeinc.gif" alt="Pay Phil Exchange" border="0" style="margin-left:-5px;" /></a></div>

<div id="unibar">

<div class="unibarnav" id="payphilexchange">
<ul>
	<li><a href="#" rel="dropmenu1">About</a> |</li>
	<li><a id="prod" href="logout.html?redirect=products">Products & Services</a> |</li>
	<li><a id="sol" href="logout.html?redirect=solutions">Solutions Development</a> |</li>
    <li><a id="part" href="logout.html?redirect=partners">Partners</a></li>

</ul>
</div>

<!--1st drop down menu -->                                                   
<div id="dropmenu1" class="unibar_dropmenu">
<a id="lead" href="logout.html?redirect=leadership">Leadership</a>
<a id="car" href="logout.html?redirect=careers">Careers</a>
<a id="cont" href="logout.html?redirect=contact">Contact us</a>
</div>

<script type="text/javascript">

cssdropdown.startchrome("payphilexchange")

</script>

</div>

<div id="wrapper"><br>

 <core:import url="menu.jsp"></core:import>

<div style="clear:both"></div>
<div id="container3">
<div id="content">


<core:if test="${user == 'user'}">

<br>

<center>Cannot Access Page</center>

<br><br>

</core:if>

<core:if test="${user == 'manager' }">


<p class="text18_tungsten">Add Branch<br /></p>

<div class="text10_red">${msg}</div>

<form:form action=""  commandName="managebranchesForm"  id="managebranchesForm">

		
		 <table width="50%" border="0" cellspacing="3" cellpadding="3">     
		 
		 
    <tr>
    
    <td class="text12_tungsten">Branch Name</td>
    <td><form:input path="branchname" size="30" style="background-color:white;" id="branchname" class="branchname"/></td>

   	
   </tr>	
   
 
    <tr>
    
    <td class="text12_tungsten">Username</td>
    <td><form:input path="username" size="30" style="background-color:white;" id="username" class="username"/></td>

   	
   </tr>	
   
	<tr>
	 
	<td class="text12_tungsten"><label for="pass1">Password</label></td>
	<td><form:password path="password" size="30" style="background-color:white;" id="password" class="password" /></td>
	
	</tr>
	
	<tr>
	 
	<td class="text12_tungsten"><label for="pass1">Confirm Password</label></td>
	<td><form:password path="confirmpassword" size="30" style="background-color:white;" id="confirmpassword" class="confirmpassword"/></td>
	
	</tr>
				 
	  <tr>
    <td>&nbsp;</td>
    <td style="float:left;"><input type="image" value="Submit" src="../css/images/submit2_button.png" style="margin-left:140px;"/></td>
    </tr>			 
                             
         </table>
                         
</form:form>

</core:if>



</div>
</div>



<div style="clear:both"></div>
<div id="footer"></div>

</div>
</div>
</body>
</html>
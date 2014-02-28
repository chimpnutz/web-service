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
<script type='text/javascript' src='../js2/jquery.simplemodal.js'></script>
<script type='text/javascript' src='../js2/basic2.js'></script>
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
			
		});
		
		</script>
		
		  <style type="text/css"> 
    label.error { color: red; font-family: Verdana; font-size: 11px;  line-height: 14px; margin-right: -160px;}
	  </style>
	  
	  <style type="text/css">

#basic-modal-content {display:none;}

/* Overlay */
#simplemodal-overlay {background-color:#000; cursor:wait;}

/* Container */
#simplemodal-container {height:300px;  width:500px; color:#cccccc; background-color:#ffffff; border:4px solid #444; padding:12px;}
#simplemodal-container .simplemodal-data {padding:8px;}
#simplemodal-container code {background:#141414; border-left:3px solid #65B43D; color:#bbb; display:block; font-size:12px; margin-bottom:12px; padding:4px 6px 6px;}
#simplemodal-container a {color:#ddd;}
#simplemodal-container a.modalCloseImg {background:url(../images/x.png) no-repeat; width:25px; height:29px; display:inline; z-index:3200; position:absolute; top:-15px; right:-16px; cursor:pointer;}
#simplemodal-container h3 {color:#84b8d9;}





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

				<center>
					<table width="500" border="0" cellpadding="0" cellspacing="0">
						<tr class="text12_tungsten_bold">			
					     	 <th>&nbsp;</th>	
					     	 <th>&nbsp;</th>	
					     	 <td>&nbsp;</td>
					         <th><a href="addbranch.html"><img src="../css/images/add_branches.png" alt="" width="25" height="25" border="0" /></a></th>					     
					   
						</tr>
					
						 <tr class="text12_tungsten_bold">			
					         <th>Partner ID</th>
					         <th>Partner Name</th>	
					         <th>Email Address</th>	
					         
					         <th>&nbsp;</th>					     
					   
						</tr>	
						  <tr>
							    <td>&nbsp;</td>
							    <td>&nbsp;</td>
							    <td>&nbsp;</td>
							    <td>&nbsp;</td>
							  </tr>
						<core:forEach var="data" items="${partners}" varStatus="count" >
						<tr class="text10_steel">												 
					         <td>${data.branchid}</td>
					         <td>${data.branchname}</td>
					         <td>${data.email}</td>
					         <td>
					         <div id='basic-modal'>
					         <input type="image" src="../css/images/edit.png" class="basic" name="basic[${count.index}]" value="edit">
					         <input type="hidden" name="partnerid[${count.index}]" id="partnerid" class="partnerid" value="${data.branchid}"/>
	
					         </div>
					         </td>
								
 					
						</tr>
						
							
							
							<div id="basic-modal-content">
			

							</div>
							
							
						</core:forEach>	
					
									
					</table>
					
					</center>

</core:if>



</div>
</div>



<div style="clear:both"></div>
<div id="footer"></div>

</div>
</div>
</body>
</html>
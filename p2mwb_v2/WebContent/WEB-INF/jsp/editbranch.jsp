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

<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.8.18.custom.min.js"></script>

<script src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
 

  <script>
  $(document).ready(function(){
	  
	  $.validator.addMethod("pass_not_the_same", function(value, element) {
		   return $('#oldpass').val() != $('#newpass').val()
		}, "* New Password and Old Password should not match");
	  
	  
	    $("#changepasswordForm").validate({
	  	  rules: {
	  	  oldpass: {
	      required: true,
	      pass_not_the_same: true
	      },
	      newpass: {
	      required: true,
	      pass_not_the_same: true
	      },
	      newpass2: {
		      required: true,
		      equalTo: "#newpass"
		      }
	  },  messages: {
		  oldpass:
	      {
	    	  required: "Please Input You Old Password"
	      },
	      newpass: 
	      {
	    	  required: "Please Input Your New Password " 
	      },    
	      newpass2:
	   	  {
	    	  required: "Please Confirm Your New Password ", 
	    	  equalTo:  "Password must be the same as above "
	   	  }
      }
	});
	});
  </script>


</head>

<body>

<div id="page">
<div id="header"><div style="margin-left:20px; padding-top:10px;"><a href="main.jsp"><img src="../css/images/payex-logo.png" /></a></div></div>
<div id="wrapper"><br>

<div align="center"><table align="center" style="width:900px; ">
	
	<tr>
		<td width="300" class="smalltext" style="float:left; text-align: left;"><img src="../css/images/user2.png" width="17" height="17"  /> Hi <span class="smalltext_orange"><%= session.getAttribute( "USER" ) %>, </span> you're logged in as 
		
		<core:if test="${user == 'user'}">
		
		<font color="silver"><%= session.getAttribute( "USERTYPE" ) %></font>
		
		</core:if>  
		
		<core:if test="${user == 'manager'}">
		
		<font color="#2b6991"><%= session.getAttribute( "USERTYPE" ) %></font>
	
		</core:if>  
		
		<br> <%= new java.text.SimpleDateFormat("MMMM dd,yyyy").format(new java.util.Date()) %></td>
        <td width="300" style="text-align:center;"><a href="main.jsp"><img title="main" onmouseover="this.src='../css/images/main-icon-on.png';" onmouseout="this.src='../css/images/main-icon-off.png';" src="../css/images/main-icon-off.png" alt="" width="37" height="38" border="0"></a></td>
        <td width="300" class="smalltext" valign="top" style="text-align:right;"><a href="logout.html"><img title="logout" onmouseover="this.src='../css/images/logout-on.png';" onmouseout="this.src='../css/images/logout.png';" src="../css/images/logout.png" alt="" width="32" height="32" border="0"></a></td>
	</tr>
    
</table>
</div>

<div style="clear:both"></div>
<div id="container">
<div id="content">
<div style="clear:both"></div>

   <core:if test="${user == 'user'}">

	<br> 
	
	<center>Cannot Access Page </center>
		
	<br> <br>
	
	</core:if>

   	<core:if test="${user == 'manager'}">
   	
   	<table width="90%" border="0" cellspacing="3" cellpadding="3">
   	
  	<form:form action=""  commandName="editBranch" id="editbranchnameForm">
   	
   	<p class="title">Edit Branch Name<br />
	  <br />
	</p>
	
	<core:if test="${status == 'fail'}">

	<div class="text">${msg}</div>
	
	<br /> 
	<br />
	
	</core:if>
	
	<core:if test="${status == 'success'}">

	<div class="text">${msg}</div>
	
	<br />
	<br />
	
	</core:if>
	
	<core:forEach var="data" items="${branch}">		

    <tr>
    
    <td width="150" class="text">Branch Id</td>
    <td><input type="text" style="background-color:white;" size="40"  value="${data.branchid}" disabled="disabled"/></td>
   	<form:hidden path="branchid" value="${data.branchid}"/>
   	
   </tr>	
   
	<tr>
	 
	<td class="text"><label for="pass1">Branch Name</label></td>
	<td><form:input path="branchname" style="background-color:white;" size="40" id="newpass"/>
	
	<span id="confirmMessage" class="confirmMessage"></span></td>
	
	</tr>

     <tr>
     <td>&nbsp;</td>
     <td><input type="image" value="Submit" src="../css/images/submit2_button.png" style="margin-left:110px;" name="changeotherpass"/></td>
     </tr>
     
     </core:forEach>
    </form:form>	
	</table>
 	
    
    </core:if>

 	 


</div>
</div>

<div style="clear:both"></div>
<div id="footer"></div>
</div><!--wrapper-->
</div><!--page-->

</body>



</html>
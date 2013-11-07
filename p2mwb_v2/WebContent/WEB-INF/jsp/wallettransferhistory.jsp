<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval() %>;URL='logout.html?expire=yes'">

<title>Pay PhilExchange</title>


<LINK href="../css/style.css" rel="stylesheet" type="text/css">
<LINK href="../css/form.css" rel="stylesheet" type="text/css">
<link href="../css/unibar.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/dropdown.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>

  <script>
  $(document).ready(function(){
	  
	  $("#main").click(function() 
			  {
				  var answer = confirm('Are you sure you want to leave this page?');
				  if (answer)
				  {
					return true;
				  }
				  else
				  {
				    return false;
				  }
			  });
	  
	  
	  $("#prod").click(function() 
	  {
		  var answer = confirm('Are you sure you want to leave this page?');
		  if (answer)
		  {
			return true;
		  }
		  else
		  {
		    return false;
		  }
	  });
	  
	  $("#sol").click(function() 
	  {
		  var answer = confirm('Are you sure you want to leave this page?');
		  if (answer)
		  {
			return true;
		  }
		  else
		  {
		    return false;
		  }
	  });
	  
	  $("#part").click(function() 
	  {
		  var answer = confirm('Are you sure you want to leave this page?');
		  if (answer)
		  {
			return true;
		  }
		  else
		  {
		    return false;
		  }
	  });
	  $("#lead").click(function() 
	  {
		  var answer = confirm('Are you sure you want to leave this page?');
		  if (answer)
		  {
			return true;
		  }
		  else
		  {
		    return false;
		  }
	  });
	  $("#car").click(function() 
	  {
		  var answer = confirm('Are you sure you want to leave this page?');
		  if (answer)
		  {
			return true;
		  }
		  else
		  {
		    return false;
		  }
	  });
	  $("#cont").click(function() 
	  {
		  var answer = confirm('Are you sure you want to leave this page?');
		  if (answer)
		  {
			return true;
		  }
		  else
		  {
		    return false;
		  }
	  });
  
	});
  
  </script>

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

<div align="center"><table align="center" style="width:900px; ">
	<tr>
		<td width="300" class="smalltext" style="float:left; text-align: left;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hi  <span class="smalltext_orange"><%= session.getAttribute( "USER" ) %>, </span> you're logged in as 
		
		<core:if test="${user == 'user'}">
		
		<font color="silver"><%= session.getAttribute( "USERTYPE" ) %></font>
		
		</core:if>  
		
		<core:if test="${user == 'manager'}">
		
		<font color="#2b6991"><%= session.getAttribute( "USERTYPE" ) %></font>
	
		</core:if>  
		
		</td>
        <td width="300" style="text-align:center;"><a href="main.jsp"><img title="main" onmouseover="this.src='../css/images/main-icon-on.png';" onmouseout="this.src='../css/images/main-icon-off.png';" src="../css/images/main-icon-off.png" alt="" width="37" height="38" border="0"></a></td>
        <td width="300" class="smalltext" valign="top" style="text-align:right;"><a href="logout.html"><img title="logout" onmouseover="this.src='../css/images/logout-on.png';" onmouseout="this.src='../css/images/logout.png';" src="../css/images/logout.png" alt="" width="32" height="32" border="0"></a></td>
	</tr>
    
</table>

<div class="smalltext" style="margin-top: -26px; margin-left: 60px; float:left;"><%= new java.text.SimpleDateFormat("MMMM dd,yyyy").format(new java.util.Date()) %></div>
<div><img style="margin-top: -35px; margin-left: 33px; float:left;" src="../css/images/user2.png" width="22" height="25" /></div>

</div>

<div style="clear:both"></div>
<div id="container">
<div id="content">


<core:if test="${user == 'user'}">
<br>

<center>Cannot Access Page</center>

<br>
<br>


</core:if>

<core:if test="${user == 'manager'}">

<p class="title">Wallet History</p>

<form:form action=""  commandName="wallettransferForm" id="wallettransferForm">
<table width="60%" border="0" align="left" cellpadding="3" cellspacing="0">      
                <tr>
                    <td width="70" class="text">Branch</td> 
                    <td width="150">
                    <form:select path="branchid" style="width: 150px">             
                    <form:options items="${fillbox}" />
				    </form:select></td>
				    <td width="50"><input type="image" value="Submit" src="../css/images/submit2_button.png" style="margin-left:-20px;"/></td>
                </tr>          
                
        </table>
</form:form>

<br /><br /><p></p>


</core:if>
</div></div>

<div id="footer"></div>
</div><!--wrapper-->
</div><!--page-->

</body>
</html>
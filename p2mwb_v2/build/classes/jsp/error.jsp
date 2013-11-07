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

<link href="../css/unibar.css" rel="stylesheet" type="text/css" />

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
        <td width="300" style="text-align:center;"><a href="main.jsp"><img title="main" onmouseover="this.src='../css/images/main-icon-on.png';" onmouseout="this.src='../css/images/main-icon-off.png';" src="../css/images/main-icon-off.png" alt="" width="37" height="38" border="0"></a></td>
  	</tr>
    
</table>
</div>

<div style="clear:both"></div>
<div id="container">
<div id="content">

<core:if test="${error != null}">

<h2>Internal Server Error.</h2>

<div>The server encountered an internal error or misconfiguration and was 
<br> unable to complete your request</div>

<br>

<div>Please contact the server administrator, info@mobilepayplus.net and
<br> inform them of the time the error occured, and anything you might have
<br> done that may have caused the error.</div>

<br>

<div>More information about this error may be available in the server error log.</div>

</core:if>

<core:if test="${error == null}">

<div class="text">Page Not Found.</div>

</core:if>



<br>

</div>

</div>

<div style="clear:both"></div>

<div id="footer"></div>

</div><!--wrapper-->

</div><!--page-->

</body>

</html>
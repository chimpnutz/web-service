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

<script src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
 

    <!-- jQuery -->

		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
		
		<!-- required plugins -->
		<script type="text/javascript" src="../js/date.js"></script>
		<!--[if lt IE 7]><script type="text/javascript" src="scripts/jquery.bgiframe.min.js"></script><![endif]-->
        
        <!-- jquery.datePicker.js -->
		<script type="text/javascript" src="../js/jquery.datePicker.js"></script>
		
		  <!-- datePicker required styles -->

		<link rel="stylesheet" type="text/css" media="screen" href="../css/datePicker.css">

		<script type="text/javascript" charset="utf-8">
			Date.firstDayOfWeek = 0;
			Date.format = 'mm/dd/yyyy';
			$(document).ready(function()
			{    
				var dateSelected = false;
				$('.date-pick').datePicker({				
					
					onSelect: function(dateText, inst) 
					{
				
					dateSelected = true;
					},

					startDate:'01/01/2000'
					
				
				});	
			    			   			    
			    $('#reportForm').submit(function() {
			    	 if($("#date1").val() == "" || $("#date2").val() == "" ) {
				    	  alert("Please Select Date");
						  return false;
				     }		    
			    });

            	
            });
		</script>
		
<link href="../css/unibar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/dropdown.js"></script>

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

<div id="wrapper"><br />
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

<br><br>

</core:if>


<core:if test="${user == 'manager'}">

<p class="title">Transaction Report<br />

<form:form action=""  commandName="reportForm" id="reportForm">
	<table width="70%" border="0" cellspacing="3" cellpadding="3">   
	
		<tr>
			<td class="text">From Date</td>
			<td><form:input path="frdate" size="30" style="background-color:white;" class="date-pick" name="date1" id="date1" readonly="true"/>
     			 </td>
		</tr>
		
		<tr>
			<td class="text">To Date</td>
			<td><form:input path="todate" size="30" style="background-color:white;"  class="date-pick" name="date2" id="date2" readonly="true"/>
				</td>
		</tr>
		
		<tr>
			<td class="text">Branch</td>
			<td colspan="3">          
                       
                    <form:select Style="width:200px" path="branch">             
                    <form:option value="NONE" label="All" />
                    <form:options items="${fillbox}" />
				    </form:select>
			</td>
		</tr>
	
		<tr>
			<td>&nbsp;</td>
			 <td><input type="image" value="Submit" src="../css/images/submit2_button.png" style="margin-left:110px;"/></td>
		</tr>
           
        </table>        
                       
                   
</form:form>


</core:if>


</div>
</div>

<div style="clear:both"></div>
<div id="footer"></div>
</div><!--wrapper-->
</div><!--page-->

</body>
</html>
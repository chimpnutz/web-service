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
<LINK href="../css/font.css" rel="stylesheet" type="text/css">
<link href="../css/unibar.css" rel="stylesheet" type="text/css" />

<link href="../css/tabs.css" rel="stylesheet" type="text/css" />
<link type='../text/css' href='../tabcss/tabcontent.css' rel='stylesheet' media='screen' />
<link type='text/css' href='../tabcss/basic.css' rel='stylesheet' media='screen' />
 

<script src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/epinvalidation.js"></script>


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
<p class="text18_tungsten">Epins Upload</p>

<core:if test="${user == 'viewer'}">

<br>

<center>Cannot Access Page</center>

<br>
<br>


</core:if>
${msg}

<core:if test="${user == 'manager'}">


	<core:if test="${type == 'bulk'}">
	
	    <ol id="toc">
	    <li ><a href="epins.html?individual">Individual Upload</a></li>
		<li class="current" ><a href="epins.html?bulk">Bulk Upload</a></li>
		</ol>
		
			
		<div class="content">
	
	<form:form action=""  commandName="epinForm" id="epinForm">


<div class="text10_red">${msg}</div>


<div id="tabcontentcontainer">
<div id="sc2" class="tabcontent">



<table width="70%" border="0" cellspacing="3" cellpadding="3">
  <tr>
    <td class="text12_tungsten" style="text-align: left; width:100px;">Email</td>
    <td style="float:left;"><form:input path="target" size="18" style="background-color:white;" id="target" class="target	" /> &nbsp; &nbsp;</td>
  </tr>
  <tr>
    <td class="text12_tungsten" style="text-align: left; width:100px;">Quantity</td>
    <td style="float:left;"><form:input path="quantity" size="18" style="background-color:white;" id="quantity" class="quantity	" /> &nbsp; &nbsp;</td>
  </tr>
  <tr>
    <td class="text12_tungsten" style="text-align: left; width:100px;">Denomination</td>
    <td style="float:left;">
   
    <form:select path="denom" id="denom" class="denom" style="width: 130px; text-align:left;"> 
    <form:option value="100" label="100" />
	<form:option value="300" label="300" />
	<form:option value="500" label="500" />			 
	</form:select> &nbsp; &nbsp;</td>
  </tr>
  
  <tr>
    <td class="text12_tungsten" style="text-align: left; width:100px;">Telco Type.</td>
  	<td style="float:left;">
    <form:select path="prodcode" id="prodcode" class="prodcode" style="width: 206px; text-align:left;"> 
    <form:option value="GHT" label="Globe" />
	<form:option value="TM" label="Touch Mobile" />		 
	</form:select> &nbsp; &nbsp;</td>
  </tr>
  
   <tr>
    <td class="text12_tungsten" style="text-align: left; width:100px;">Password</td>
    <td style="float:left;"><form:password path="password" size="18" style="background-color:white;" id="password" class="password	" /> &nbsp; &nbsp;</td>
  </tr>
  
   <tr>
    <td class="text12_tungsten" style="text-align: left; width:100px;">Confirm Password</td>
    <td style="float:left;"><form:password path="password2" size="18" style="background-color:white;" id="password2" class="password2	" /> &nbsp; &nbsp;</td>
  </tr>

  <tr>
    <td>&nbsp;</td>
    <td style="float:left;"><input type="image" value="Submit" name="submit" src="../css/images/submit2_button.png" style="margin-left:140px;"/></td>
    </tr>
</table>



</div><!--sc2-->
</div>

		<form:hidden path="type" value="bulk"  />

</form:form>

<br><br /><br />
	
	</div>
	
	</core:if>
	
		<core:if test="${type == 'individual'}">
	
	    <ol id="toc">
	    <li class="current"><a href="epins.html?individual">Individual Upload</a></li>
		<li><a href="epins.html?bulk">Bulk Upload</a></li>

		</ol>
	
		<div class="content">
	
	<form:form action=""  commandName="epinForm" id="epinForm">


<div class="text10_red">${msg}</div>


<div id="tabcontentcontainer">
<div id="sc2" class="tabcontent">

<table width="70%" border="0" cellspacing="3" cellpadding="3">
  <tr>
    <td class="text12_tungsten" style="text-align: left; width:100px;">Mobile No.</td>
    <td style="float:left;"><form:select path="prefix" style="width: 70px; text-align:left;" id="prefix" class="prefix"> <form:options items="${fillbox}" />
				    </form:select> &nbsp; &nbsp;<form:input path="mobnum" size="18" style="background-color:white;" id="mobnum" class="mobnum" maxlength="7"/></td>
  </tr>
  <tr>
    <td class="text12_tungsten" style="text-align: left; width:100px;">Denomination</td>
    <td style="float:left;">
    <form:select path="denom" id="denom" class="denom" style="width: 206px; text-align:left;"> 
    <form:option value="100" label="100" />
	<form:option value="300" label="300" />
	<form:option value="500" label="500" />			 
	</form:select> &nbsp; &nbsp;</td>
  </tr>
  <tr>
  	<td class="text12_tungsten" style="text-align: left; width:100px;">Telco Type.</td>
  	<td style="float:left;">
    <form:select path="prodcode" id="prodcode" class="prodcode" style="width: 206px; text-align:left;"> 
    <form:option value="GHT" label="Globe" />
	<form:option value="TM" label="Touch Mobile" />		 
	</form:select> &nbsp; &nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td style="float:left;"><input type="image" value="Submit" name="submit" src="../css/images/submit2_button.png" style="margin-left:140px;"/></td>
    </tr>
</table>



</div><!--sc2-->
</div>

						<form:hidden path="type" value="individual"  />

</form:form>

<br><br /><br />
	
	</div>


	</core:if>



 








</core:if>



</div>

</div>

<div style="clear:both"></div>

<div id="footer"></div>

</div><!--wrapper-->

</div><!--page-->

</body>

</html>
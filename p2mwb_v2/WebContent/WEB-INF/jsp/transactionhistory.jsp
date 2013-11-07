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
<link type='text/css' href='../tabcss/basic.css' rel='stylesheet' media='screen' />
<link type='text/css' href='../tabcss/tabcontent.css' rel='stylesheet' media='screen' />


<script src="../js2/jquery-1.8.2.js"></script>

<script type="text/javascript">
var initialtab=[1, "sc1"]
function cascadedstyle(el, cssproperty, csspropertyNS){
if (el.currentStyle)
return el.currentStyle[cssproperty]
else if (window.getComputedStyle){
var elstyle=window.getComputedStyle(el, "")
return elstyle.getPropertyValue(csspropertyNS)
}
}

var previoustab=""

function expandcontent(cid, aobject){
if (document.getElementById){
highlighttab(aobject)
detectSourceindex(aobject)
if (previoustab!="")
document.getElementById(previoustab).style.display="none"
document.getElementById(cid).style.display="block"
previoustab=cid
if (aobject.blur)
aobject.blur()
return false
}
else
return true
}

function highlighttab(aobject){
if (typeof tabobjlinks=="undefined")
collecttablinks()
for (i=0; i<tabobjlinks.length; i++)
tabobjlinks[i].style.backgroundColor=initTabcolor
var themecolor=aobject.getAttribute("theme")? aobject.getAttribute("theme") : initTabpostcolor
aobject.style.backgroundColor=document.getElementById("tabcontentcontainer").style.backgroundColor=themecolor
}

function collecttablinks(){
var tabobj=document.getElementById("tablist")
tabobjlinks=tabobj.getElementsByTagName("A")
}

function detectSourceindex(aobject){
for (i=0; i<tabobjlinks.length; i++){
if (aobject==tabobjlinks[i]){
tabsourceindex=i //source index of tab bar relative to other tabs
break;
}
}
}

function do_onload(){
var cookiename=(typeof persisttype!="undefined" && persisttype=="sitewide")? "tabcontent" : window.location.pathname
var cookiecheck=window.get_cookie && get_cookie(cookiename).indexOf("|")!=-1
collecttablinks()
initTabcolor=cascadedstyle(tabobjlinks[1], "backgroundColor", "background-color")
initTabpostcolor=cascadedstyle(tabobjlinks[0], "backgroundColor", "background-color")
if (typeof enablepersistence!="undefined" && enablepersistence && cookiecheck){
var cookieparse=get_cookie(cookiename).split("|")
var whichtab=cookieparse[0]
var tabcontentid=cookieparse[1]
expandcontent(tabcontentid, tabobjlinks[whichtab])
}
else
expandcontent(initialtab[1], tabobjlinks[initialtab[0]-1])
}

if (window.addEventListener)
window.addEventListener("load", do_onload, false)
else if (window.attachEvent)
window.attachEvent("onload", do_onload)
else if (document.getElementById)
window.onload=do_onload
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

<core:if test="${user == 'manager'}">


<p class="title" style="margin-left:10px;">Sales Report</p>

		<ul id="tablist">
		<li><a href="#" class="current" onClick="return expandcontent('sc1', this)">Consumer Sim Top-up History</a></li>
		<li><a href="#" onClick="return expandcontent('sc3', this)">Retailer Sim Top up History</a></li>
		</ul>
	
	
		
<div id="tabcontentcontainer" >
<div id="sc1" class="tabcontent">



					<table width="100%" align="center" style="margin-top:20px;">	
						<tr class ="tdheader">				
					         <td>Transaction Date</td>
					         <td>Trace Number</td>
					         <td>Mobile Number</td>
					         <td>Face Value</td>	
					         <td>Amount Deducted</td>
					         <td>Retained Earnings</td>
					         <td>Status</td>	
			     
					   
						</tr>	
						<core:forEach var="data" items="${logs}">
						<tr class="tddetails">												 
				 	  		 <td class="text">${data.txdate}</td>
					         <td>${data.tracenum}</td>
					         <td>${data.mobnum}</td>
					     	 <td>${data.amount}</td>
					     	 <td>${data.amountdeducted}</td>
					     	 <td>${data.retainedearnings}</td>
					         <td>${data.status}</td>
					         
						</tr>
						</core:forEach>	
													
					</table><br>

<!-- modal content -->



</div><!--sc1-->

<!--start of sc3-->


<div id="sc3" class="tabcontent">


					<table width="100%" align="center" style="margin-top:20px;">	
						<tr class ="tdheader">				
					         <td>Transaction Date</td>
					         <td>Trace Number</td>
					         <td>Mobile Number</td>
					         <td>Amount</td>	
					         <td>Status</td>	
			     
					   
						</tr>	
						<core:forEach var="data" items="${retailerlogs}">
						<tr class="tddetails">												 
				 	  		 <td class="text">${data.txdate}</td>
				 	  		 <td>${data.tracenum}</td>
					         <td>${data.mobnum}</td>
					     	 <td>${data.amount}</td>
					         <td>${data.status}</td>
					         
						</tr>
						</core:forEach>	
													
					</table><br>
   	



</div><!--sc3-->


</div><!--tabcontentcontainer-->


</core:if>

<core:if test="${user == 'user'}">


<p class="title">Top up Transaction History</p>

					<table width="100%" align="center">	
						<tr class ="tdheader">				
					         <td>Transaction Date</td>
					         <td>Trace Number</td>
					         <td>Mobile Number</td>
					         <td>Amount</td>	
					         <td>Status</td>	
			     
					   
						</tr>	
						<core:forEach var="data" items="${logs}">
						<tr class="tddetails">												 
				 	  		 <td class="text">${data.txdate}</td>
					         <td>${data.tracenum}</td>
					         <td>${data.mobnum}</td>
					     	 <td>${data.amount}</td>
					         <td>${data.status}</td>
					         
						</tr>
						</core:forEach>	
													
					</table><br>

</core:if>

</div>
</div>
			
				
<div style="clear:both"></div>
<div id="footer"></div>
</div><!--wrapper-->
</div><!--page-->

</body>
</html>
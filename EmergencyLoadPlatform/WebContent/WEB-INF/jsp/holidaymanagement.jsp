<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval() %>;URL='logout.html?expire=yes'">
<link rel="shortcut icon" href="../css/images/favicon.ico" >
<LINK rel="stylesheet" href="../css/style.css" type="text/css">
<LINK rel="stylesheet" href="../css/font.css" type="text/css">
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/ajax.js"></script>
<link rel="stylesheet" type="text/css" href="../css/verticalmenu.css">
<script type="text/javascript">
<!--
window.onload=montre;
function montre(id) {
var d = document.getElementById(id);
for (var i = 1; i<=10; i++) {
if (document.getElementById('smenu'+i)) {document.getElementById('smenu'+i).style.display='none';}
}
if (d) {d.style.display='block';}
}
//-->
</script>

<style type="text/css">
body {
	margin:0;
}
</style>

<title>Emergency Load</title>
</head>

<body>
<div id="page"><!----- Start of PAGE ----->
<div id="container"><!-----Start of Container ----->

   <core:import url="menu.jsp"></core:import>
	
	 <core:if test="${roleid == 1 || roleid == 2 || roleid == 3 ||  roleid == 8}">

	
   <div id="content" class="text12_tungsten">

      <table width="700"  border="0" align="center" cellpadding="3" cellspacing="0" id="usertable" class="usertable">
            <tr>
              <td colspan="5" class="text12_tungsten"><a href="holidaymanagement-add.html"><img src="../css/images/add.png" width="28" height="28" hspace="0" border="0" align="absmiddle" /></a><span class="lucida_12_tungsten_b">Add Holidays</span></td>
            </tr>
            
             <tr>
              
  			  <td class="text12_tungsten_bold">&nbsp;</td>
              <td class="text12_tungsten_bold">Holiday Start</td>
              <td class="text12_tungsten_bold">Holiday End</td>
             
            </tr>

              
            
              <core:forEach var="data" items="${holidaylist}">
              
            <tr class ="data"> 
              
			  <td class="text10_red"><input type="image" src="../css/images/x.png"  id = "delhol" class="delhol" name="delhol" value ="${data.id}"/></td>
              <td class="lucida_12_tungsten">${data.holiday_from}</td>
              <td class="lucida_12_tungsten">${data.holiday_to}</td>         
            </tr>
   
   
             </core:forEach>
             
          </table>
      
   </div>
  
  </core:if>
  
    	  <core:if test="${roleid == 4 || roleid == 5 || roleid == 6 || roleid == 7}">
	 
	 <br><br><br><br><br>
	
	<center>	<div class="text12_tungsten_bold"> Access is Denied </div></center>
	
	  </core:if>

  


</div><!----- End Of Container----->
<div id="footer"></div>
</div><!----- End of PAGE ----->


</body>
</html>

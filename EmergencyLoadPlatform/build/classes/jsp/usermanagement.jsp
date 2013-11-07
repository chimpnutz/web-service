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
	
	 <core:if test="${roleid == 4 || roleid == 5 || roleid == 1 || roleid == 2 || roleid == 3 ||  roleid == 8}">

	
   <div id="content" class="text12_tungsten">
    <form:form method="POST" commandName="usermanagementForm">
      <table width="700"  border="0" align="center" cellpadding="3" cellspacing="0" id="usertable" class="usertable">
            <tr>
              <td colspan="5" class="text12_tungsten"><a href="usermanagement-add.html"><img src="../css/images/add-user.png" width="35" height="39" hspace="5" border="0" align="absmiddle" /></a><span class="lucida_12_tungsten_b">Add User Access</span></td>
            </tr>
            
             <tr> 
              <td class="text12_tungsten_bold">Disable</td>
              <td class="text12_tungsten_bold">Username</td>
              <td class="text12_tungsten_bold">Company</td>
              <td class="text12_tungsten_bold">Role</td>
              <td class="text10_red">&nbsp;</td>
             
            </tr>

              
            
              <core:forEach var="data" items="${userlist}">
              
            <tr class ="data"> 
              <td class="text10_red"><input type="image" src="../css/images/x.png"  id = "deluser" class="deluser" value ="${data.userid}"/><input type="hidden"  id = "hiddenuserid" class="hiddenuserid" name="hiddenuserid" value ="${data.userid}"/></td>
              <td class="lucida_12_tungsten">${data.username}</td>
              <td class="lucida_12_tungsten">${data.companyname}</td>
              <td class="lucida_12_tungsten">${data.rolename}</td>
              <td align="center" class="text10_red"><a href="usermanagement-edit.html?userid=${data.userid}" class="text10_red">edit</a> |<a href="usermanagement-view.html?userid=${data.userid}" class="text10_red"> view</a></td>
            </tr>
   
   
             </core:forEach>
             
          </table>
          </form:form>   
   </div>
  
  </core:if>
  
    	  <core:if test="${ roleid == 6 || roleid == 7}">
	 
	 <br><br><br><br><br>
	
	<center>	<div class="text12_tungsten_bold"> Access is Denied </div></center>
	
	  </core:if>

  


</div><!----- End Of Container----->
<div id="footer"></div>
</div><!----- End of PAGE ----->


</body>
</html>

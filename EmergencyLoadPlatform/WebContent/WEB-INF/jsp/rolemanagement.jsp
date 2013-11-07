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
	
   <div id="content">
   <div id="prompt" class="text12_tungsten_bold"></div>
     <table width="700" border="0" align="center" cellpadding="5" cellspacing="0" id="roletable" class ="roletable">
            <tr>
              <td colspan="3"><div id='basic-modal'> <input type="image"  src="../css/images/add.png"  name="add"  id="addrole" class="addrole" value="Confirm" width="28" height="28" hspace="0" border="0" align="absmiddle"/> <span class="text12_tungsten_bold"> Role</span></div></td>
            </tr>
            <tr>
              <td class="text12_tungsten_bold">Role</td>
              <td class="text10_red">&nbsp;</td>
              <td class="text12_tungsten_bold">Delete</td>
      	   </tr>
      	   <core:forEach var="data" items="${roleslist}">
            <tr>
              <td width="250" class="text12_tungsten">${data.rolename}</td>
              <td width="200" class="text12_tungsten">&nbsp;</td>
              <!-----Start of Container <td width="200" class="text10_red"><a href="rolemanagement-info.html?roleid=${data.roleid}"  class="text12_tungsten">edit</a></td>----->
			  <td class="text10_red"><input type="image" src="../css/images/x.png"  id = "delrole" class="delrole"  value ="${data.roleid}"/><input type="hidden"  id = "hiddenroleid" class="hiddenroleid" name="hiddenuserid" value ="${data.roleid}"/></td>
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

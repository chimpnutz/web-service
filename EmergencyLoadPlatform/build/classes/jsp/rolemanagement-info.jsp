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
            <table width="650" border="0" align="center" cellpadding="3" cellspacing="0">
            <tr>
              <td width="250" class="text12_tungsten">Role Name</td>
              <td colspan="2" align="left" class="text12_tungsten_bold">Super Administrator</td>
            </tr>
            <tr>
              <td colspan="3" class="text12_tungsten"><img src="../css/images/add.png" width="28" height="28" align="absmiddle" /> <span class="text12_tungsten_bold">Privileges</span></td>
            </tr>
            <tr>
              <td class="text12_tungsten_bold"><span class="lucida_12_tungsten_b">Modules</span></td>
              <td class="text12_tungsten_bold"><span class="lucida_12_tungsten_b">Privileges</span></td>
              <td class="text12_tungsten_bold"><span class="lucida_12_tungsten_b">Disable</span></td>
            </tr>
            <tr>
              <td class="text12_tungsten">Access Management<br /></td>
              <td class="text10_red">Add | Edit | Delete</td>
              <td class="text10_red">x</td>
            </tr>
            <tr>
              <td class="text12_tungsten">Network Management<br /></td>
              <td class="text10_red">Add | Edit | Delete</td>
              <td class="text10_red">x</td>
            </tr>
            <tr>
              <td class="text12_tungsten">Credit Limit Management<br /></td>
              <td class="text10_red">Add | Edit | Delete</td>
              <td class="text10_red">x</td>
            </tr>
            <tr>
              <td class="text12_tungsten">Emergency Load Management<br /></td>
              <td class="text10_red">Add | Edit | Delete</td>
              <td class="text10_red">x</td>
            </tr>
            <tr>
              <td class="text12_tungsten"><select name="select" id="select">
                <option>Access Management</option>
                <option>Network Management</option>
                <option>Credit Limit Management</option>
              </select></td>
              <td class="text10_red"><span class="text12_tungsten">
                <select name="select2" id="select2">
                  <option>Add | Edit | Delete</option>
                </select>
              </span></td>
              <td class="text10_red"><img src="../css/images/add_button.png" width="73" height="39"/></td>
            </tr>
            <tr>
              <td colspan="3" class="text12_tungsten" align="center">&nbsp;</td>
            </tr>
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

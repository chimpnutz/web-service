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
<LINK rel="stylesheet" href="../css/demo1.css" type="text/css">
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<link rel="stylesheet" type="text/css" href="../css/verticalmenu.css">

<script type="text/javascript" src="../js/jquery.datePicker.js"></script>
<script type="text/javascript" src="../js/date.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="../css/datePicker.css">

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

<script type="text/javascript" charset="utf-8">
			
					$(document).ready(function()
			{    
				var dateToday = new Date();
				Date.firstDayOfWeek = 0;
				Date.format = 'yyyy-mm-dd';
				var dateSelected = false;
				$('.date-pick').datePicker({				
					minDate: dateToday,
					onSelect: function(dateText, inst) 
					{
				
					},

					
				
				});	
			    			   			    

            });
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

    <core:if test="${roleid == 1 || roleid == 2 || roleid == 3 || roleid == 4 || roleid ==5 || roleid == 8}">
   
   
	<div id="content">
	
	
	
	 <form:form action=""  commandName="holidaymanagementForm" id="holidaymanagementForm" class="holidaymanagementForm">
	
		<core:if test="${success == 'yes'}">
		<div class = "text12_tungsten_bold">Holiday Added Successfully</div>
		</core:if>
		
		<core:if test="${success == 'no'}">
		<div class = "text12_tungsten_bold">Adding of Holiday Added  Failed</div>
		</core:if>
       <table width="700" border="0" align="center" cellpadding="3" cellspacing="0">

           <tr>
             <td width="150" class="text12_tungsten_bold">Holiday Start</td>
             <td>
               <form:input path="holiday_from" class="date-pick" name="date1" id="date1" readonly="true"  />
            </td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Holiday End</td>
             <td><form:input path="holiday_to"  class="date-pick" name="date2" id="date2" readonly="true" /></td>
           </tr>
            
           <tr>
             <td class="text12_tungsten_bold">&nbsp;</td>
             <td>&nbsp;</td>
           </tr>
           
       </table> 
    <div align="center"><input type="image" src="../css/images/submit.png"/></div>
     </form:form>
		

        

    
    
    </div>
    
    </core:if>
    
    
	  <core:if test="${roleid == 4 || roleid == 5 || roleid == 6 || roleid == 7}">
	 
	 <br><br><br><br><br>
	
	<center>	<div class="text12_tungsten_bold"> Access is Denied </div></center>
	
	  </core:if>
    

	    <div align="center"></div>
         <br /><br />
    
	</div>
 


</div><!----- End Of Container----->
<div id="footer"></div>
</div><!----- End of PAGE ----->


</body>
</html>

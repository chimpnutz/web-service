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
<script type="text/javascript" src="../js/jquery.min.js"></script>

 

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
			
				$("#submitbtn").click(function() {
					
				if ($("#msisdn").val().length >0 || $("#date1").val().length > 0) {
					
				    
				  	return true;
				  	
				}
				alert("Please Input at least one text box.");
				return false;
				});
				
				
				var dateSelected = false;
				$('.date-pick').datePicker({				
					
					onSelect: function(dateText, inst) 
					{
				
					dateSelected = true;
					},

					startDate:'01/01/2000'
					
				
				});	
				
			    $("#txInquiryForm").validate({
				  	  rules: {
				  	  msisdn: {
				  	  maxlength: 11,
				      number: true
				      },		
				  },  messages: {
				      msisdn: 
				      {
				    	  number: "Enter a Valid Mobile No.", 
				          maxlength: "Please input the remaining mobile no."	
				      }    	 
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

<div id="wrapper"><br>

 <core:import url="menu.jsp"></core:import>

<div style="clear:both"></div>
<div id="container3">
<div id="content">


<core:if test="${user == 'user'}">

<br>

<center>Cannot Access Page</center>

<br><br>

</core:if>

<core:if test="${user == 'manager' || user == 'viewer'}">


<p class="text18_tungsten">Transaction Inquiry<br /></p>

<div class="text">${msgTxinquiry}</div>

<form:form action=""  commandName="txInquiryForm"  id="txInquiryForm">

		
		
		 <table width="50%" border="0" cellspacing="3" cellpadding="3">     
	 
                <tr>
                    <td class="text12_tungsten" style="text-align: left">Mobile no.</td>
                    <td align="left"><form:input path="msisdn" style="background-color:white; margin-left: 0px;" id="msisdn" maxlength="11" size="21"/></td>
                </tr> 
                 <tr>
                    <td class="text12_tungsten" style="text-align: left">Transaction Date</td>
                    <td align="left"><form:input path="transactiondate" style="background-color:white;" class="date-pick" name="date1" id="date1" readonly="true" /></td>
                </tr> 
                 <tr>
				    <td style="text-align: left">&nbsp;</td>
				    <td style="text-align: left"><input type="image" id ="submitbtn" value="Submit" src="../css/images/submit2_button.png" style="float:right; margin-right:40px;"/></td>
				 </tr>
				 
				 
                             
         </table>
                         
</form:form>

</core:if>



</div>
</div>



<div style="clear:both"></div>
<div id="footer"></div>

</div>
</div>
</body>
</html>
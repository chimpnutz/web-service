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

<script src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>

  <script>
  
  
  $(document).ready(function(){
	    $("#topupForm").validate({
	  	  rules: {
	  	  amount: {
	      required: true,
	      number: true
	      },
	      mobnum: {
	      required: true,
	      number: true,
	      maxlength: 7
	      }
	  },  messages: {
	      amount:
	      {
	    	  required: ('<span class="smalltext_red">Please Input Amount</span>'),
	    	  number:	('<span class="smalltext_red">Please Input Valid Amount</span>')
	      },
	      mobnum: 
	      {
	    	  number: ('<span class="smalltext_red">Enter a Valid Mobile No.</span>'),
	    	  required: ('<span class="smalltext_red">Please Input Mobile No.</span>'),	 
	          maxlength: ('<span class=smalltext_red">Please input the remaining mobile no.</span>')
	      }    	 
      }
	});
	    $('#mobnum').val('');
	    $('#amount').val('');
	    $('#message').val('');
	    
	    if(prodtype == 'LOAD')
	    {
	    	$('#amount').val('');
	    	$('#amount').removeAttr("readonly");  
	    }else
	    {
	    	
	    	$('#amount').attr("readonly", true); 
	    	
	    }
	    
	    $("#prodtype").change(function() { /* do something here */ 
	    	
		    var prodtype = $('#prodtype').val();
		    
		    if(prodtype == 'LOAD')
		    {
		    	$('#amount').val('');
		    	$('#amount').removeAttr("readonly");  
		    }else if (prodtype == 'none'){
		    		
		    	$('#amount').val('');
		    	$('#amount').attr("readonly", true); 
		    	
		    }
		    else
		    {
		    	
		        $.ajax(
		        		
		                {
		                	
		                type: "POST",
		                url: "addcredits.html?mode=getAmount",
		                data: "prodtype=" + prodtype,
		             	async: true,

		                success: 
		                function(response){
		         		

		        						if(response != null){
		        								        				
		        							$('#amount').val(response);
		        					    	$('#amount').attr("readonly", true); 
		        													
		        						}
		        						
		        						

		                },
		            
		                
		                error: function(e){
		                alert('Error: ' + e);
		                }
		                
		                
		                });
		        

		
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

<div align="center"><table align="center" style="width:900px; ">
	<tr>
		<td width="300" class="smalltext" style="float:left; text-align: left;">Hi  <span class="smalltext_orange"><%= session.getAttribute( "USER" ) %>, </span> you're logged in as 
		
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

<div class="smalltext" style="margin-top: -26px; margin-left: 63px; float:left;"><%= new java.text.SimpleDateFormat("MMMM dd,yyyy").format(new java.util.Date()) %></div>
<div><img style="margin-top: -35px; margin-left: 33px; float:left;" src="../css/images/user2.png" width="22" height="25" /></div>

</div>

<div style="clear:both"></div>
<div id="container3">
<div id="content">
<p class="title">Add Credits<br />
  <br />
  <span class="text">Your current balance is: </span><span class="bigtext_orange">PHP ${currentwallet}</span>
  <br />
</p>

<form:form action=""  commandName="topupForm" id="topupForm">

<core:if test="${status == 'success'}">

<div class="text-error">${msg}</div>

</core:if>

<core:if test="${status == 'fail'}">

<div class="text-error">${msg}</div>

</core:if>

<table width="90%" border="0" cellspacing="3" cellpadding="3">
  <tr>
    <td class="text" style="text-align: left; width:100px;">Mobile No.</td>
    <td style="float:left;"><form:select path="prefix" style="width: 70px; text-align:left;"><form:options items="${fillbox}" />
				    </form:select> &nbsp; &nbsp;<form:input path="mobnum" size="20" style="background-color:white;" id="mobnum" maxlength="7"/></td>
    </tr>
  <tr>
    <td class="text" style="text-align: left;">Amount</td>
    <td style="float:left;"><form:select id = "prodtype" path="prodtype" style="width: 110px; text-align:left;">
    				<form:option value="none" label="Select product type" />
    				<form:options items="${producttype}" />
				    </form:select> &nbsp; <form:input path="amount" size="14" style="background-color:white;" id="amount"/></td>
    
    

    </tr>
  <tr>
    <td valign="top" class="text" style="text-align: left;">Message</td>
    <td style="float:left;"><form:textarea path="message" rows="5" cols="28" style="background-color:white;" id="message"/></td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td style="float:left;"><input type="image" value="Submit" src="../css/images/submit2_button.png" style="margin-left:180px;"/></td>
    </tr>
</table>

</form:form>

<br><br /><br />

</div>

</div>

<div style="clear:both"></div>

<div id="footer"></div>

</div><!--wrapper-->

</div><!--page-->

</body>

</html>
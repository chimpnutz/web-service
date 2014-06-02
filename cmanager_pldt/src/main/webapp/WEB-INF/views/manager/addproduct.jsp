<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />

	
	

	
	<style type="text/css">
		* {
			margin: 0;
			padding: 0;
			outline: 0;
		}
		
		html, body{
			height: 100%;
			background: #F2F2F2;
			width:100%;
		}

		body, a{
			font: normal 16px Helvetica, Verdana, Geneva, sans-serif;
			color: #3F3F3F;		
		}
		
		.container{
			min-height: 100%;
			height: auto !important;
			height: 100%;
			margin: 0 auto -30px;
			width:100%;
			background-color: #f1f1f1;
		}
		
		.container:after{
			content: '';
			display: block;
			height: 30px;
			clear: both;
		}
		
		.footer{
			height: 12px;
			padding: 8px 0;
			background: #FFF;
			border-top: 1px solid #51C1F1;
			font-size: 12px;
			text-align: center;
			width:1360px;
			margin-top: 600px;
			position: absolute;
		}

.clearme
		{
			height: 30px;
			padding-top: 5px;
			
		}

.xspace{
    height: 80px;
}

.tdspace{
	height: 10px;
}
		

	</style>

		<style>
			canvas{
			}
		</style>


			<link rel="stylesheet" href="resources/css/istyle.css" />


			<link rel="stylesheet" href="resources/css/structure.css" />



	
		


	<link rel="stylesheet" href="resources/css/font-awesome.css" />
	<link rel="stylesheet" href="resources/css/font-awesome.min.css" />
	<link rel="stylesheet" href="resources/css/nav.css" />
	<title>Circles</title>
</head>
<body>
	<div class="container">
<div class="boxes"></div>
		<div class="header clearfix">
			
			
			<div class="nav">
				
				<ul class="menu">
					<li><a href="salesmtd" ><i class="fa fa-usd"></i>&nbsp;SALES</a></li>
					<li><a href="agent"><i class="icon-agent" style="font-size:20px;"></i>&nbsp;AGENTS</a></li>		
					<li><a href="recent"><i class="fa fa-clock-o"></i>&nbsp;RECENT</a></li>
					<li><a href="addproduct" class="selected"><i class="fa fa-clock-o"></i>&nbsp;ADD PRODUCTS</a></li>	
					<li><a href="logout"><i class="fa fa-laptop"></i>&nbsp;LOG OUT</a></li>
					
				</ul>
			</div><!-- End of Navigation -->
			

			
		</div><!-- End of Header -->
		





		<div class="spacer"></div>
	</section>


<div class="xspace"></div>

<c:if test="${role=='manager' }">
	<div class="footer">
		<p>&copy; 2013 Circles</p>
	</div>
</c:if>

<c:if test="${role=='encoder' }">
	<div class="footer" style="margin-top:485px">
		<p>&copy; 2013 Circles</p>
	</div>
</c:if>
<font face="Lucida Grande" size="4" color="#666666">${message }</font>


<c:if test="${role=='manager' }">
<form action="saveaddProduct"  method="POST" ENCTYPE="multipart/form-data" class="box login">
	<fieldset class="boxBody" style="border:0">
	 <table style="margin-left:auto; margin-top:35px; margin-right:auto;" width="800" border="0" cellpadding="5" cellspacing="0" bgcolor="transparent" style="text-align:left;">
    <tr>
      <td colspan="3"> </td>
    </tr>
    <tr >
      <td width="300">
        <font face="Lucida Grande" size="2" color="#666666"><b>Product Name</b></font> <span style="color:red;"><small>*</small></span>
      </td>
      <td colspan="2" style="">
        <input value="" size="52" name="product_name" type="text" />${message1 }
        <div style="padding-bottom:8px;color:#666666;"></div>
      </td>
    </tr>
    <tr >
      <td align="left">
        <font face="Lucida Grande" size="2" color="#666666"><b>Product Price</b></font> <span style="color:red;"><small>*</small></span>
      </td>
      <td colspan="2" id="td_element_field_1" style="">
        <input value="" size="52" name="product_price" type="text" />${message2 }
        <div style="padding-bottom:8px;color:#666666;"></div>
      </td>
    </tr>

       <tr>
<td><font face="Lucida Grande" size="2" color="#666666"><b>File</b></font></td>
<td><input name="filename" type="file" id="file"/></td>
</tr>
 <tr>
   <td class="tdspace"></td>
   </tr>
<tr valign="top">
			<td><font face="Lucida Grande" size="2" color="#666666"><b>Product Type :</b></font></td>
			<td>
			<select name="product_type">
				<option value='PLDT Landline'>PLDT Landline</option>
				<option value='PLDT HOME DSL'>PLDT HOME DSL</option>
				<option value='PLDT HOME Fibr'>PLDT HOME Fibr</option>
				<option value='PLDT HOME Telpad'>PLDT HOME Telpad</option>
				<option value='PLDT HOME Bundle'>PLDT HOME Bundle</option>
				<option value='PLDT HOME MyBro'>PLDT HOME MyBro</option>
				<option value='PLDT 2-in-1 Wireless Home Bundle'>PLDT 2-in-1 Wireless Home Bundle</option>
				<option value='KaAsenso'>KaAsenso</option>
			</select>
			</td>
		</tr>
	

     <tr>
   <td class="tdspace"></td>
   </tr>
<tr valign="top">
      <td align="left">
        <font face="Lucida Grande" size="2" color="#666666"><b>Description</b></font> <span style="color:red;"><small>*</small></span>
      </td>
      <td colspan="2">
        <textarea  name="product_desc"   cols="70" rows="10">
</textarea>${message5 }
        <div style="padding-bottom:8px;color:#666666;"></div>
      </td>
    </tr>

   <tr>
   <td class="tdspace"></td>
   </tr>
    <tr>
      <td colspan="2" align="center">
       <input name="submit" value="Submit" type="submit" />
       <input value="Clear" type="reset" />
      </td>
    </tr>
  </table>
	</fieldset>
</form>
</c:if>
  <c:if test="${role=='encoder' }">
<center style="margin-top:20px; font-size:20px;">Access Denied</center>
</c:if>




	</div><!-- End of Container -->
	







</body>
</html>
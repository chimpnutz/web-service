<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
  

<title>Circles</title>
<script> /* */ var s = document.URL; if(s.indexOf("saveCommentE")==-1){} else if(s.indexOf("updateStatus")==-1){} else{ history.go(-1); setTimeout(function(){ window.location.reload(1); }, 5000); } $('body').bind('beforeunload',function(){ $('.content').empty(); }); </script>

		<link rel="stylesheet" href="resources/css/reveal.css">	
	  	
		<!-- Attach necessary scripts -->
		<!-- <script type="text/javascript" src="jquery-1.4.4.min.js"></script> -->
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
		<script type="text/javascript" src="resources/js/jquery-1.6.min.js"></script>
		<script type="text/javascript" src="resources/js/jquery.reveal.js"></script>
		<link rel="stylesheet" href="resources/css/font-awesome.css" />
		<link rel="stylesheet" href="resources/css/font-awesome.min.css" />



<link rel="stylesheet" href="resources/css/table.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="resources/css/application.css" type="text/css" media="screen" title="default" />
<link rel="stylesheet" href="resources/css/tables.css" type="text/css" media="screen"/>

<link rel="stylesheet" href="resources/css/nav.css" type="text/css" media="screen"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>

<script src="resources/js/shadow.js"></script>
<script src="resources/js/shadow2.js"></script>

<script>
$(document).ready(function() {
$(window).scroll(function() {
if ($(this).scrollTop() > 1) {
$("#secondnav").css({
"box-shadow": "0 7px 8px rgba(0,0,0,0.10)",
"-webkit-box-shadow":"0 7px 8px rgba(0,0,0,0.10)"
});
}



else{
$("#secondnav").css({
	"box-shadow":"none",
	"-webkit-box-shadow":"none",
	"-o-box-shadow":"none"
});
}
});
});
</script>



<style type="text/css">
	.leftwing{
		position: absolute;
	}

</style>

<style type="text/css">
	#myMapModal{
	margin-top:20px;}

html, body, #map-canvas  {
  margin: 0;
  padding: 0;
  height: 100%;
}

#map-canvas {
  width:500px;
  height:480px;
}

.modal-open {
  overflow: hidden; }
  .nmodal-open .navbar-fixed-top,
  .nmodal-open .navbar-fixed-bottom {
    margin-right: 15px; }

body.modal-open {
  margin-right: 15px; }

.modal {

  display: none;
  /*overflow: auto;
  overflow-y: scroll;*/
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 99999 !important; }
  .modal.fade .modal-dialog {
    -webkit-transform: translate(0, -25%);
    -ms-transform: translate(0, -25%);
    transform: translate(0, -25%);
    -webkit-transition: -webkit-transform 0.3s ease-out;
    -moz-transition: -moz-transform 0.3s ease-out;
    -o-transition: -o-transform 0.3s ease-out;
    transition: transform 0.3s ease-out; }
  .modal.in .nmodal-dialog {
    -webkit-transform: translate(0, 0);
    -ms-transform: translate(0, 0);
    transform: translate(0, 0); }

.modal-dialog {
  margin-left: auto;
  margin-right: auto;
  width: auto;
  padding: 10px;
  z-index: 1050; }

.modal-content {
  position: relative;
  background-color: white;
  border: 1px solid #999999;
  border: 1px solid rgba(0, 0, 0, 0.2);
  
  -webkit-box-shadow: 0 3px 9px rgba(0, 0, 0, 0.5);
  box-shadow: 0 3px 9px rgba(0, 0, 0, 0.5);
  background-clip: padding-box;
  outline: none; }

.modal-backdrop {
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 1030;
  background-color: black; }
  .modal-backdrop.fade {
    opacity: 0;
    filter: alpha(opacity=0); }
  .modal-backdrop.in {
    opacity: 0.5;
    filter: alpha(opacity=50); }

.modal-header {
  padding: 15px;
  border-bottom: 1px solid #e5e5e5;
  min-height: 16.42857px; }

.modal-header .close {
  margin-top: -2px; }

.modal-title {
  margin: 0;
  line-height: 1.42857; }

.modal-body {
  position: relative;
  padding: 20px; }

.modal-footer {
  margin-top: 15px;
  padding: 19px 20px 20px;
  text-align: right;
  border-top: 1px solid #e5e5e5; }
  .modal-footer:before, .modal-footer:after {
    content: " ";
    /* 1 */
    display: table;
    /* 2 */ }
  .modal-footer:after {
    clear: both; }
  .modal-footer .btn + .btn {
    margin-left: 5px;
    margin-bottom: 0; }
  .modal-footer .btn-group .btn + .btn {
    margin-left: -1px; }
  .modal-footer .btn-block + .btn-block {
    margin-left: 0; }

@media screen and (min-width: 768px) {
  .modal-dialog {
    left: 50%;
    right: auto;
    width: 550px;
    padding-top: 30px;
    padding-bottom: 30px; }

  .modal-content {
    -webkit-box-shadow: 0 5px 15px rgba(0, 0, 0, 0.5);
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.5); } }
	
</style>



<!--<script type="text/javascript">

function returnfile() {
	var answer = confirm("Your about to return the file")
	if (answer){
		alert("File has been Return")
		window.location = "encoderrecent.html";
		
	}
	else{
		alert("Review it well")
	}
}

</script>//-->

<style type="text/css">
.commentbox{

	position: absolute;
	margin-top:10px;
}

.comments {
	
		 
		overflow-y:scroll; 
		margin-top: 40px;
	
		height: 200px;
		width: 550px;
		background-color: white ;
			
		-moz-border-radius: 5px;
		-webkit-border-radius: 5px;
		border-radius: 5px;
		/*-moz-box-shadow: 0 0 10px rgba(0,0,0,.4);
		-webkit-box-shadow: 0 0 10px rgba(0,0,0,.4);
		box-shadow: 0 0 10px rgba(0,0,0,.4);*/
		z-index: 99999999;
		}
.encname{
width:400px;
			margin-left: 400px;
			position: absolute;
			margin-top: 110px;
				z-index: 4;
		}
.encname2{
width:400px;
			margin-left: 400px;
			position: absolute;
			margin-top: 130px;
				z-index: 4;
		}
</style>
<script src="resources/js/bootstrap.js"></script>

</head>
<body> 






 	<div class="clear"></div>


	
<div class="clear">&nbsp;</div>
 
<!--  start nav-outer-repeat................................................................................................. START -->
<div class="nav-outer-repeat"> 
<!--  start nav-outer -->
<div class="nav-outer"> 

		<!-- start nav-right -->
		<div id="nav-right2">
		
			
			
	
			<div class="nav-divider">&nbsp;</div>
			<a href="logout.html" id="logout"><img src="resources/css/images/shared/nav/nav_logout.gif" width="64" height="14" alt="" /></a>
			<div class="clear">&nbsp;</div>
		
			
		
		</div>
		<!-- end nav-right -->


		<!--  start nav -->
		<div class="nav">
		<div class="table">
		
		<ul class="current"><li><a href="encoderrecent.html"><b>Recent</b><!--[if IE 7]><!--></a><!--<![endif]-->
		
		</ul>
		
		<div class="nav-divider">&nbsp;</div>
		                    
		<ul class="select"><li><a href="encoderreturn.html"><b>Return</b><!--[if IE 7]><!--></a><!--<![endif]-->
		
		</ul>
		<div class="nav-divider">&nbsp;</div>
		
		<ul class="select"><li><a href="encoderencode.html"><b>Encoded</b><!--[if IE 7]><!--></a><!--<![endif]-->
		
		</ul>
 <c:if test="${role=='encoder'}"> 
<c:forEach var="datas" items="${application}">
<c:if test="${datas.application_type=='Residential'}">
	<div class="encname"><p>${datas.getFirstName()}&nbsp;${datas.getLastName() }</p></div>
</c:if>	
<c:if test="${datas.application_type=='Business'}">
	<div class="encname"><p>${datas.companyauth}</p></div>
</c:if>	
	<div class="encname2"><p>${datas.getApplication_id()}</p></div>
</c:forEach>
</c:if>	
		<div class="clear"></div>
		</div>
		<div class="clear"></div>
		</div>
		<!--  start nav -->

</div>



 <div id="secondnav">

 </div>

<div id="whitecover">


 <c:if test="${role=='manager'}"> 
 <h1 style="margin-left:50px; margin-top:50px">Access Denied</h1>
 </c:if>


 <c:if test="${role=='encoder'}"> 
<c:forEach var="datas" items="${application}">
<form action="updateStatus" method="post" name="update" style="float:right; margin-right:100px; margin-top:60px;">
	<label>Status</label>: 
		<c:if test="${datas.getStatus()=='0'}">
		<select name="status" id="status">
		<option value="${datas.getStatus()}" disabled selected>----</option>

		<option value="2">Submitted</option>		
	
		<option value="4">Incomplete</option>
		</select>
		<script>
			$("#status").change(function(){
			  $("#submit").show();

			});
		</script>
		<input type="hidden" value="${datas.getApplication_id()}" name="application_id"/>
		<input type="submit" style="display:none" value="Post" name="submit" id="submit">
		</c:if>
		<c:if test="${datas.getStatus()=='1'}">
		<select name="status">
		<option value="${datas.getStatus()}">Approved</option>
		</select>
		<script>
			$("#status").change(function(){
			  $("#submit").show();

			});
		</script>
		<input type="hidden" value="${datas.getApplication_id()}" name="application_id"/>
		<input type="submit" style="display:none" value="Post" name="submit" id="submit">
		</c:if>
		<c:if test="${datas.getStatus()=='2'}">
			<select name="status" id="status">
		<option value="${datas.getStatus()}" disabled selected>Submitted</option>
		<option value="1">Approved</option>

		<option value="3">Declined</option>		
	
		<option value="4">Incomplete</option>
		
		</select>
		<script>
			$("#status").change(function(){
			  $("#submit").show();

			});
		</script>
		<input type="hidden" value="${datas.getApplication_id()}" name="application_id"/>
		<input type="submit" style="display:none" value="Post" name="submit" id="submit">
		</c:if>

		<c:if test="${datas.getStatus()=='3'}">
		<select name="status">
		<option value="${datas.getStatus()}">Declined</option>
		</select>
		</c:if>
		<c:if test="${datas.getStatus()=='4'}">
		<select name="status" id="status">
<option value="${datas.getStatus()}" disabled selected>Incomplete</option>
		<option value="1">Approved</option>	
		<option value="4">Declined</option>
		</select>
		<script>
			$("#status").change(function(){
			  $("#submit").show();

			});
		</script>
		<input type="hidden" value="${datas.getApplication_id()}" name="application_id"/>
		<input type="hidden" value="${data.product_name}" name="prod_name"/>
		<input type="hidden" value="${data.device_name}" name="dev_name"/>
		<input type="submit" style="display:none" value="Post" name="submit" id="submit">
		</c:if>
	</form>
</c:forEach>
</c:if>
</div>

<div class="clear"></div>
<!--  start nav-outer -->
</div>
<!--  start nav-outer-repeat................................................... END -->


<div id="topside">

	<!--  start footer-left -->
	<div id="top-center">

 <div class="fontsa">
  <c:if test="${role=='encoder'}"> 
	 <a href="" style="margin-left:75px;margin-top:35px; "><p class="currentselect">
Details </p>
</a>
<c:forEach var="data" items="${application}">	
 <a href="attachments2.html?appid=${data.getApplication_id()}" style="margin-top:35px;"><p style="margin-left:-65px;">
Attachements</p>
</a> 
</c:forEach>
</c:if>


</div>




	</div>
	
	<!--  end footer-left -->








<div id="centerside">
<div id="centerside-center">
 <c:if test="${role=='encoder'}"> 	



<div id="borderside">

<c:forEach var="data" items="${application}">	

<c:if test="${data.application_type=='Residential'}">
<div class="leftwing" style="margin-top:40px;margin-left:-10px;"><text style="color:#68b3ff;font-size:14px;">Agent Details</text></div>

<div class="leftwing" style="margin-top:120px;margin-left:-10px;"><text style="color:#68b3ff;font-size:14px;">Customer Personal Data</text></div>
<div class="leftwing" style="margin-top:140px;margin-left:-10px;"><text style="color:#68b3ff;font-size:14px;"></text></div>
<!-- <div class="leftwing" style="margin-top:325px;margin-left:-10px;"><text style="color:#68b3ff;font-size:14px;">Sketch from Google Map</text></div>-->

<div class="leftwing" style="margin-top:260px;margin-left:-10px;"><text style="color:#68b3ff;font-size:14px;">Home Ownership</text></div><!-- 365 -->

<div class="leftwing" style="margin-top:340px;margin-left:-10px;"><text style="color:#68b3ff;font-size:14px;">Secondary Contact Details</text></div> <!-- 410 -->

<div class="leftwing" style="margin-top:420px;margin-left:-10px;"><text style="color:#68b3ff;font-size:14px;">Spouse Personal Data</text></div> <!-- 495 -->
<div class="leftwing" style="margin-top:440px;margin-left:-10px;"><text style="color:#68b3ff;font-size:14px;">(*if Married)</text></div><!-- 515 -->
</c:if>


<c:if test="${data.application_type=='Business'}">
<div class="leftwing" style="margin-top:40px;margin-left:-10px;"><text style="color:#68b3ff;font-size:14px;">AGENT DETAILS</text></div>

<div class="leftwing" style="margin-top:120px;margin-left:-10px;"><text style="color:#68b3ff;font-size:14px;">COMPANY DATA</text></div>

<!-- <div class="leftwing" style="margin-top:325px;margin-left:-10px;"><text style="color:#68b3ff;font-size:14px;">Sketch from Google Map</text></div>-->

<div class="leftwing" style="margin-top:325px;margin-left:-10px;"><text style="color:#68b3ff;font-size:14px;">Secondary Contant Details</text></div>
<!-- 370px -->
</c:if> 















<div class="cutter">


</div>


<c:if test="${data.application_type=='Residential'}">
		<div class="bluebox" style="margin-left:190px">
<p style="margin-left:350px;">RESIDENTIAL APPLICATION					
 </p>
</div>
</c:if>
<c:if test="${data.application_type=='Business'}">
		<div class="bluebox" style="margin-left:190px">
<p style="margin-left:350px;">BUSINESS APPLICATION				
 </p>
</div>
</c:if>
<section id="main" style="margin-left:150px; margin-top:50px;">
		<article class="module width_app">
			<div class="linespace">
			</div>
			<div id="tab1" class="tab_content">
<c:if test="${data.application_type=='Residential'}">
			<table class="tablesorter" > 
			
				
		
			<tbody> 
				<tr>




							<td width="120px;"><b>Agent Name:&nbsp;</b>${data.user_id}</td>
							<td width="120px;"><b>Agent Contact Number:&nbsp;</b>${data.agent_no}</td>
					<!--    <td width="120px;"><b>Agent Code:&nbsp;</b></td>  -->	
						
					

					</tr>
				
					
					<tr>
						<td width="120px;"><b>Plan Type:&nbsp;</b>${data.product_name}</td>
						<td width="120px;"><b>DATE OF APPLICATION:&nbsp;</b><jsp:useBean id="dateValuess" class="java.util.Date" />
						<jsp:setProperty name="dateValuess" property="time" value="${data.getCreated()}" />
						<fmt:formatDate value="${dateValuess}" pattern="MM/dd/yyyy" /></td>
						
						<!--  <td width="120px;"><b>CIS No.:&nbsp;</b>3123232</td> -->

					</tr>
				



					<tr>
						<td width="120px;"><b>Title:&nbsp;</b>${data.title}</td>
						<td width="120px;"><b>Last Name:&nbsp;</b>${data.lastName}</td>
						<td width="120px;"><b>First Name:&nbsp;</b>${data.firstName}</td>

					</tr>
					
				
					
					<tr>						
						<td width="120px;"><b>Mother's Full Maiden Name&nbsp;</b>${data.mothers_maidenname}</td>
						<td width="120px;"><b>Birthdate:&nbsp;</b>${data.birthday}</td>
						<td width="120px;"><b>&nbsp;</b> </td>
					</tr>
					
				
					
					<tr>
						<td width="120px;"><b>Civil Status:&nbsp;</b>${data.civil_status}</td>
						<!-- <td width="120px;"><b>Blk&Lot/Room/Door/Stall/Hse#:&nbsp;</b>${data.housenumber}</td>-->
						<td width="120px;"><b>Address Line:&nbsp;</b>${data.addLine1}</td>
						<td width="120px;"><b>Sketch from Google Map&nbsp;&nbsp;</b><a href="#myMapModal" class="btn" data-toggle="modal"><i style="color:blue;" class="fa fa-map-marker"></i></a></td>
					</tr>
					
				
					
					<tr>
						<!--<td width="120px;"><b>Building/Apartment Name:&nbsp;</b>${data.building}</td>
						<td width="120px;"><b>Street Name:&nbsp;</b>${data.streetname}</td>
						<td width="120px;"><b>Subd/Village:&nbsp;</b>${data.village}</td>-->
					</tr>

					<tr>
						<td width="120px;"><b>Barangay:&nbsp;</b>${data.addBrgy}</td>
						<td width="120px;"><b>City/Town:&nbsp;</b>${data.addCity} City</td>
						<td width="120px;"><b>Region:&nbsp;</b>${data.addRegion}</td>
	
					</tr>

				<!--  
					<tr>
						<td width="120px;"><b>www.google.com/map/567893</td>
						
				
	
					</tr>
				-->

					<tr>
						<td width="120px;"><b>Owned/Living with Relatives/Rented:&nbsp;</b>${data.homeownership}</td>
						<td width="120px;"><b>Years of Stay:&nbsp;</b>${data.yearsofstay}</td>
						<td width="120px;"><b>Zip Code:&nbsp;</b>${data.zipCode}</td>	
					</tr>

					<tr>
						<td width="120px;"><b>Contact Person:&nbsp;</b>${data.firstName} ${data.lastName}</td>
						<td width="120px;"><b>Contact Number:&nbsp;</b>${data.contact_number}</td>
						<td width="120px;"><b>Email Address:&nbsp;</b>${data.email}</td>
	
					</tr>

					<!--  <tr>
						<td width="120px;"><b>Telephone/Cellular Number:&nbsp;</b>323232</td>
						
					<td width="120px;"><b>Employment Status:&nbsp;</b>323232</td>
					</tr> -->
					<tr>
						<td width="120px;"><b>First Name:&nbsp;</b>${data.spouse_title}</td>
						<td width="120px;"><b>First Name:&nbsp;</b>${data.spouse_firstName}</td>
						<td width="120px;"><b>Last Name:&nbsp;</b>${data.spouse_lastName}</td>
	
					</tr>

					<tr>
						<td width="120px;"><b>Address Line:&nbsp;</b>${data.spouse_addLine1}</td>
						<td width="120px;"><b>Region:&nbsp;</b>${data.spouse_region}</td>
						<td width="120px;"><b>Barangay:&nbsp;</b>${data.spouse_brgy}</td>
	
					</tr>


					<tr style="border-bottom:solid grey 1px">
						
						<td width="120px;"><b>City/Town:&nbsp;</b>${data.spouse_city}</td>
						<td width="120px;"><b>Zip Code:&nbsp;</b>${data.spouse_zipcode}</td>
					
					</tr>
					



					
					
			</tbody> 

			</table>
				
</c:if>
				




<c:if test="${data.application_type=='Business'}">




<table class="tablesorter" > 
			
				
		
			<tbody> 
				<tr>




							<td width="120px;"><b>Agent Name:&nbsp;</b>${data.user_id}</td>
					  <!-- <td width="120px;"><b>Agent Code:&nbsp;</b>Gomez</td> -->
						<td width="120px;"><b>Agent Contact Number:&nbsp;</b>${data.mobile}</td>
					

					</tr>
				
					
					<tr>
						<td width="120px;"><b>Plan Type:&nbsp;</b>${data.plan_code}</td>
						<td width="120px;"><b>DATE OF APPLICATION:&nbsp;</b><jsp:useBean id="dateValuesss" class="java.util.Date" />
						<jsp:setProperty name="dateValuesss" property="time" value="${data.getCreated()}" />
						<fmt:formatDate value="${dateValuesss}" pattern="MM/dd/yyyy" /></td>
						<!-- <td width="120px;"><b>CIS No.:&nbsp;</b>3123232</td> -->

					</tr>
				



					<tr>
						<td width="120px;"><b>Company Name:&nbsp;</b>${data.companyname}</td>
						<td width="120px;"><b>Nature of Business:&nbsp;</b>${data.companynature}</td>
						<td width="120px;"><b>Business Ownership:&nbsp;</b>${data.companyauth}</td>

					</tr>
					
				
					<!--  
					<tr>
						<td width="120px;"><b>Last Name:&nbsp;</b>${data.lastName}</td>
						<td width="120px;"><b>First Name:&nbsp;</b>${data.firstName}</td>
						<td width="120px;"><b>Middle Name:&nbsp;</b>${data.middleName}</td>
					</tr>
					-->
				
					
					<tr>
						<td width="120px;"><b>Position:&nbsp;</b>${data.companypos}</td>
						<!--<td width="120px;"><b>Blk&Lot/Room/Door/Stall/Hse#:&nbsp;</b>${data.housenumber}</td> -->
						<!-- <td width="120px;"><b>:&nbsp;</b>${data.plan_code}</td>  -->
						<td width="120px;"><b>Address Line:&nbsp;</b>${data.addLine1}</td>
						<td width="120px;"><b>Sketch from Google Map&nbsp;&nbsp;</b><i style="color:blue;" class="fa fa-map-marker"></i></td>
					</tr>
					
				
					
					<tr>
						<td width="120px;"><b>Building/Apartment Name:&nbsp;</b>${data.building}</td>
						<td width="120px;"><b>Street Name:&nbsp;</b>${data.streetname}</td>
						<td width="120px;"><b>Subd/Village:&nbsp;</b>${data.village}</td>
					</tr>

					<tr>
						<td width="120px;"><b>Barangay:&nbsp;</b>${data.addBrgy}</td>
						<td width="120px;"><b>City/Town:&nbsp;</b>${data.addCity} City</td>
						<td width="120px;"><b>Province:&nbsp;</b>${data.addRegion}</td>
	
					</tr>

				<!--  
					<tr>
						<td width="120px;"><b>www.google.com/map/567893</td>
						
				
	
					</tr>
				-->

					

					<tr style="border-bottom:solid grey 1px">
						<td width="120px;"><b>Contact Person:&nbsp;</b>${data.firstName} ${data.lastName}</td>
						<td width="120px;"><b>Contact Number:&nbsp;</b>${data.contact_number}</td>
						<td width="120px;"><b>Email Address:&nbsp;</b>${data.email}</td>
	
					</tr>
				<!-- 
					<tr style="border-bottom:solid grey 1px">
						<td width="120px;"><b>Telephone/Cellular Number:&nbsp;</b>323232</td>
						
			
						
						
						<td width="120px;"><b>Contact Number:&nbsp;</b>09292223389</td>
					</tr>
					 -->



			

					
					
			</tbody> 

			</table>
</c:if>

<input type = "hidden" id="lat" value="${data.lat}"/>
<input type = "hidden" id="lng"value="${data.lng }">


<div class="comments">

<section id="main">
		<article class="module width_app">
			<div class="linespace">
			</div>
			<div id="tab1" class="tab_content">
			
	
			
			<table class="tablesorter" > 
	
			<tbody> 
				
					<tr>
						<c:set var="count" value="1" />
							<c:forEach items="${comment}" var="data">
								<c:set var="count" value="${count + 1}"/>
								
								<td width="220px;" ><b style="color:blue;">${data.getUser_id()} on: <c:set var="now" value="${data.getCreated()}" />
								</b>${data.getContent()}</td>
								<tr><td width="100px" style="border-bottom:1px dotted grey">
								<small> <jsp:useBean id="dateValue" class="java.util.Date" />
								<jsp:setProperty name="dateValue" property="time" value="${data.getCreated()}" />
								<fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy HH:mm" /></small></td></tr>
					
							</c:forEach>
					
						
					</tr>	

					
	
			</tbody> 
			</table>
			</div>


					
</article>
</section>

</div>
<c:forEach var="datas" items="${application}">	
<div class="commentbox">
	<form action="saveCommentE" method="post">
						<textarea width="200px" rows="4" cols="60" name="content"></textarea>
						<br>
						<input type="hidden" name="user_id" value="${user}"/>
						<input type="hidden" name="application_id" value="${datas.application_id}"/>
						<input type="hidden" name="edited_by" value="${user}"/>
						<input type="submit" value="Comment" name="comment" >
					</form>

</div>
</c:forEach>


</div>
</article>




</section>












</c:forEach>


</div>
</c:if>




</div>


<c:if test="${role=='encoder'}">   
<!-- start footer -->         
<div id="footer2">
	<!--  start footer-left -->
	<div id="footer-center">
	<p>&copy; Circles 2013 </p>
	</div>
	
	<!--  end footer-left -->

</div>
</c:if>
<c:if test="${role=='manager'}">   
<!-- start footer -->         
<div id="footer2" style="margin-top:450px">
	<!--  start footer-left -->
	<div id="footer-center">
	<p>&copy; Circles 2013 </p>
	</div>
	
	<!--  end footer-left -->

</div>
</c:if>
<!-- end footer -->


</div>

<!--  end content-outer -->

 

</div>
		</div>


<div class="modal" id="myMapModal">
    <div class="modal-dialog">
        <div class="modal-content">
           <!--  <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">�</button>
                 <h4 class="modal-title">Modal title</h4>

            </div> -->
            <div class="modal-body">
                
                    <div class="row">
                        <div id="map-canvas" class=""></div>
                        
                        
                        
                        
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>

<script>

var x = document.getElementById('lat').value;
var y =document.getElementById('lng').value;

var map;        
var myCenter=new google.maps.LatLng(x,y);
var marker=new google.maps.Marker({
position:myCenter
});

initialize();
function initialize() {
var mapProp = {
center:myCenter,
zoom: 20,
draggable: false,
scrollwheel: false,
mapTypeId:google.maps.MapTypeId.ROADMAP
};

map=new google.maps.Map(document.getElementById("map-canvas"),mapProp);
marker.setMap(map);

google.maps.event.addListener(marker, 'click', function() {

infowindow.setContent(contentString);
infowindow.open(map, marker);

}); 
};
google.maps.event.addDomListener(window, 'load', initialize);

google.maps.event.addDomListener(window, "resize", resizingMap());

$('#myMapModal').on('show.bs.modal', function() {
//Must wait until the render of the modal appear, thats why we use the resizeMap and NOT resizingMap!! ;-)
resizeMap();
})

function resizeMap() {
if(typeof map =="undefined") return;
setTimeout( function(){resizingMap();} , 400);
}

function resizingMap() {
if(typeof map =="undefined") return;
var center = map.getCenter();
google.maps.event.trigger(map, "resize");
map.setCenter(center); 
}
</script>



                </div>
            </div>
          <!--  <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div> --> 
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->



</body>
</html>
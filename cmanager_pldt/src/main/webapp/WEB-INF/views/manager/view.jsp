<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="en">
<head>
	<meta charset="utf-8" />

	<script> /* */ var s = document.URL; if(s.indexOf("saveCommentM")==-1){} else{ history.go(-1); setTimeout(function(){ window.location.reload(1); }, 1000); } $('body').bind('beforeunload',function(){ $('.content').empty(); }); </script>
	
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
			width:1360px;
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
			/*width:1360px;*/
		}
.nla{

with:500px;
}

#myMapModal{
	margin-top:150px;}

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
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 1040; }
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
	<script src="resources/js/jquery-1.7.1.min.js"></script>
	<script src="resources/js/lightbox-2.6.min.js"></script>
	<link href="resources/css/lightbox2.css" rel="stylesheet" />
	<script src="resources/js/bootstrap.js"></script>

			<link rel="stylesheet" type="text/css" href="resources/css/wrapper.css" />
       	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css' />
		<script type="text/javascript" src="resources/js/modernizr.custom.79639.js"></script>


	<link rel="stylesheet" href="resources/css/istyle.css" />

	<link rel="stylesheet" href="resources/css2/managertable.css" />
	<link rel="stylesheet" href="resources/css/reveal2.css" />
	<script src="resources/js/jquery.reveal.js"></script>
	<link rel="stylesheet" href="resources/css/font-awesome.css" />
<link rel="stylesheet" href="resources/css/font-awesome.min.css" />
<link rel="stylesheet" href="resources/css/recentmenus.css" />
	<link rel="stylesheet" href="resources/css/nav.css" />
	<link rel="stylesheet" href="resources/css2/imagegal.css" />
	<title>Circles</title>
</head>
<body>
	<div class="container">
<div class="boxes"> </div>
		<div class="header clearfix">
			
			
			<div class="nav">
				
				<ul class="menu">
					<li><a href="salesmtd"><i class="fa fa-usd"></i>&nbsp;SALES</a></li>
					<li><a href="agent"><i  class="icon-agent" style="font-size:20px;"></i>&nbsp;AGENTS</a></li>		
					<li><a href="recent"  class="selected"><i class="fa fa-clock-o"></i>&nbsp;RECENT</a></li>	
					<li><a href="viewproduct"><i class="fa fa-cubes"></i>&nbsp;PRODUCTS</a></li>
					<li><a href="logout"><i class="fa fa-laptop"></i>&nbsp;LOG OUT</a></li>
					
				</ul>
			</div><!-- End of Navigation -->
			



		</div><!-- End of Header -->
	
			

<c:forEach var="applications" items="${application }">
		
			<div class="wrapper-demo" style="margin-left:170px">
								
					<div id="dd" class="wrapper-dropdown-21 activemode">
						<a href="view?applicationid=${applications.getApplication_id()}">
							Details
						</a>
					</div>
									
			</div>


					
					<div class="wrapper-demo" style="margin-left:420px;">
					<a href="attachments3?appid=${applications.getApplication_id()}"><div id="dd" class="wrapper-dropdown-2">Attachments
						
					</div></a>
				
							




				</div>


<div style="margin-left:700px;margin-top:50px; position:absolute;">
<text style="font-size:15px">${applications.getFirstName()}</text><text style="margin-left:10px;font-size:15px">${applications.getLastName()}</text><br/>
<text style="color:#808080; font-size:11px">Application ID:&nbsp;${applications.getApplication_id()}</text>
</div>
</c:forEach>

<section id="main" style="margin-top:100px;margin-bottom:15px;">
		<article class="module width_recenttable2" >
		
			<div id="tab1" class="tab_content">




<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->


<c:forEach var="data" items="${application}">

	<c:if test="${data.application_type=='Residential'}">

			<table class="tablesorter" > 
			<thead > 
				<tr> 
    				<th></th> 
    				<th style="padding-left:100px;color:#444444;">RESIDENTIAL APPLICATION</th> 
    				<th></th> 
    				<th></th>
    				<th></th>

				</tr> 
			</thead> 
			
					<tbody> 
				

					<tr>
						<td><b style="color:blue; font-size:12px">AGENT DETAILS</b></td>
						<td></td>
						<td></td>
					
					</tr>
					<tr>
						<td><b style="color:#808080; font-size:11px">Agent Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.user_id}</text></td>
						<!-- <td><b style="color:#808080; font-size:11px">Agent Code:&nbsp;&nbsp;</b><text style="color:#333333">1092201</text> </td> -->
						<td><b style="color:#808080; font-size:11px">Agent Contact Number:&nbsp;&nbsp;</b><text style="color:#333333">${data.agent_no}</text></td>
						<td></td>
					
					</tr>
			
					<tr>
						<td><b style="color:#808080; font-size:11px">Plan Type:&nbsp;&nbsp;</b><text style="color:#333333">${data.product_name}</text></td>
						<td><b style="color:#808080; font-size:11px">DATE OF APPLICATION:&nbsp;&nbsp;</b><text style="color:#333333">
						<jsp:useBean id="dateValuess" class="java.util.Date" />
						<jsp:setProperty name="dateValuess" property="time" value="${data.created}" />
						<fmt:formatDate value="${dateValuess}" pattern="MM/dd/yyyy" /></text></td>
						<!-- <td><b style="color:#808080; font-size:11px">CIS No.:&nbsp;&nbsp;</b><text style="color:#333333">123123123</text></td> -->
						<td></td>
					</tr>
					<tr>
						<td><b style="color:blue; font-size:12px">CUSTOMER PERSONAL DATA</b></td>
						<td></td>
						<td></td>
					
					</tr>
					<tr>
						<td><b style="color:#808080; font-size:11px">Title:&nbsp;&nbsp;</b><text style="color:#333333">${data.title}.</text></td>
						<td><b style="color:#808080; font-size:11px">Last Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.lastName}</text></td>
						<td><b style="color:#808080; font-size:11px">First Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.firstName}</text></td>
						
					</tr>
					
					<tr>						
						<td><b style="color:#808080; font-size:11px">Mother's Full Maiden Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.mothers_maidenname}</text></td>
						<td><b style="color:#808080; font-size:11px">Birthdate:&nbsp;&nbsp;</b><text style="color:#333333">${data.birthday}</text></td>
						<td></td>
						<!-- <td><b style="color:#808080; font-size:11px">Middle Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.middleName}</text></td> -->
						
					</tr>
					
					<tr>
						<td><b style="color:#808080; font-size:11px">Civil Status:&nbsp;&nbsp;</b><text style="color:#333333">${data.civil_status}</text></td>
						<td><b style="color:#808080; font-size:11px">Address Line:&nbsp;&nbsp;</b><text style="color:#333333">${data.addLine1}</text></td>
						<!--<td><b style="color:#808080; font-size:11px">Blk&Lot/Room/Door/Stall/Hse#:&nbsp;&nbsp;</b><text style="color:#333333">${data.housenumber}</text></td>-->
						<!-- <td><b style="color:#808080; font-size:11px">&nbsp;&nbsp;</b><text style="color:#333333">${data.plan_code}</text></td> -->
						<td><b style="color:#808080; font-size:11px">Sketch From Google Map:&nbsp;&nbsp;</b><text style="color:#333333"><a href="#myMapModal" class="btn" data-toggle="modal"><i style="color:blue;" class="fa fa-map-marker"></i></a></text></td>
					</tr>
					<!--
					<tr>
						<td><b style="color:#808080; font-size:11px">Building/Apartment Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.building}</text></td>
						<td><b style="color:#808080; font-size:11px">Street Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.streetname}</text></td>
						<td><b style="color:#808080; font-size:11px">Subd/Village:&nbsp;&nbsp;</b><text style="color:#333333">${data.village}</text></td>
						
					
				</tr>
				-->
				
				<tr>
						<td><b style="color:#808080; font-size:11px">Barangay:&nbsp;&nbsp;</b><text style="color:#333333">${data.addBrgy}</text></td>
						<td><b style="color:#808080; font-size:11px">City/Town:&nbsp;&nbsp;</b><text style="color:#333333">${data.addCity}</text></td>
						<td><b style="color:#808080; font-size:11px">Region:&nbsp;&nbsp;</b><text style="color:#333333">${data.addRegion}</text></td>
						

						
					</tr>
					
					<tr>
						<td><b style="color:#808080; font-size:11px">Zip Code:&nbsp;&nbsp;</b><text style="color:#333333">${data.zipCode }</text></td>
						<td><b style="color:#808080; font-size:11px">Owned/Living with Relatives/Rented:&nbsp;&nbsp;</b><text style="color:#333333">${data.homeownership}</text></td>
						<td><b style="color:#808080; font-size:11px">Years of Stay:&nbsp;&nbsp;</b><text style="color:#333333">${data.yearsofstay}</text></td>
						<td></td>
			
					</tr>
					

					<tr>
						<td><b style="color:blue; font-size:12px">Secondary Contact Details</b></td>
						<td></td>
						<td></td>
					
					</tr>
				
					
					<tr>
						<td><b style="color:#808080; font-size:11px">Contact Person:&nbsp;&nbsp;</b><text style="color:#333333">${data.firstName} ${data.lastName}</text></td>
						<td><b style="color:#808080; font-size:11px">Contact Number:&nbsp;&nbsp;</b><text style="color:#333333">${data.contact_number}</text></td>
						<td></td>
			

					</tr>
					
					<tr>
						<td><b style="color:#808080; font-size:11px">Email Address:&nbsp;&nbsp;</b><text style="color:#333333">${data.email}</text></td>
						<td><!-- <b style="color:#808080; font-size:11px">Telephone/Cellular Number:&nbsp;&nbsp;</b><text style="color:#333333">${data.plan_code}</text> --></td>
						<td><!-- <b style="color:#808080; font-size:11px">Employment Status:&nbsp;&nbsp;</b><text style="color:#333333">${data.plan_code}</text> --></td>

					</tr>
					
			
<tr>
						<td><b style="color:blue; font-size:12px">Spouse Personal Data (*if Married)</b></td>
						<td></td>
						<td></td>
					
					</tr>

					
					
					<tr>
						<td><b style="color:#808080; font-size:11px">Title:&nbsp;&nbsp;</b><text style="color:#333333">${data.spouse_title}</text></td>
						<td><b style="color:#808080; font-size:11px">First Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.spouse_firstName}</text></td>
						<td><b style="color:#808080; font-size:11px">Last Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.spouse_lastName}</text></td>

					</tr>
					
					<tr>
						<td><b style="color:#808080; font-size:11px">Address Line:&nbsp;&nbsp;</b><text style="color:#333333"></text>${data.spouse_addLine1}</td>
						<td><b style="color:#808080; font-size:11px">Region:&nbsp;&nbsp;</b><text style="color:#333333"></text>${data.spouse_region}</td>
						<td><b style="color:#808080; font-size:11px">Barangay:&nbsp;&nbsp;</b><text style="color:#333333"></text>${data.spouse_brgy}</td>
						

					</tr>
					
					<tr>
						<td><b style="color:#808080; font-size:11px">City/Town:&nbsp;&nbsp;</b><text style="color:#333333"></text>${data.spouse_city}</td>
						<td><b style="color:#808080; font-size:11px">Zip Code:&nbsp;&nbsp;</b><text style="color:#333333"></text>${data.spouse_zipcode}</td>
						<td></td>
					</tr>
					
					
			</tbody> 
			</table>
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
<!--Residention-->
</c:if>















<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->	<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->	<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->	<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->	<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->	




<c:if test="${data.application_type=='Business'}">
<table class="tablesorter" > 
			<thead > 
				<tr> 
    				<th></th> 
    				<th style="padding-left:0px;color:#444444;">BUSINESS APPLICATION</th> 
    				<th></th> 
    				<th></th>
    				<th></th>

				</tr> 
			</thead> 
			
					<tbody> 
				

					<tr>
						<td><b style="color:blue; font-size:12px">AGENT DETAILS</b></td>
						<td></td>
						<td></td>
					
					</tr>
					<tr>
						<td><b style="color:#808080; font-size:11px">Agent Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.user_id}</text></td>
						<!-- <td><b style="color:#808080; font-size:11px">Agent Code:&nbsp;&nbsp;</b><text style="color:#333333">${data.plan_code}</text> </td> -->
						<td><b style="color:#808080; font-size:11px">Agent Contact Number:&nbsp;&nbsp;</b><text style="color:#333333">${data.mobile}</text></td>
						<td></td>
					
					</tr>
			
					<tr>
						<td><b style="color:#808080; font-size:11px">Plan Type:&nbsp;&nbsp;</b><text style="color:#333333">${data.product_name}</text></td>
						<td><b style="color:#808080; font-size:11px">DATE OF APPLICATION:&nbsp;&nbsp;</b><text style="color:#333333">
						<jsp:useBean id="created" class="java.util.Date" />
						<jsp:setProperty name="created" property="time" value="${data.created}" />
						<fmt:formatDate value="${created}" pattern="MM/dd/yyyy" /></text></td></text></td>
						<!-- <td><b style="color:#808080; font-size:11px">CIS No.:&nbsp;&nbsp;</b><text style="color:#333333">${data.plan_code}</text></td> -->
						<td></td>
					</tr>
					<tr>
						<td><b style="color:blue; font-size:12px">COMPANY DATA</b></td>
						<td></td>
						<td></td>
					
					</tr>
					<tr>
						<td><b style="color:#808080; font-size:11px">Company Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.companyname}</text></td>
						<td><b style="color:#808080; font-size:11px">Nature of Business:&nbsp;&nbsp;</b><text style="color:#333333">${data.companynature}</text></td>
						<td><b style="color:#808080; font-size:11px">Business Ownership:&nbsp;&nbsp;</b><text style="color:#333333">${data.companyauth}</text></td>
						
					</tr>
					<!-- 	<tr>
						<td><b style="color:#808080; font-size:11px">Last Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.lastName}</text></td>
						<td><b style="color:#808080; font-size:11px">First Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.firstName}</text></td>
						<td><b style="color:#808080; font-size:11px">Middle Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.middleName}</text></td>
						
					</tr> -->
				
					
					<tr>
						<td><b style="color:#808080; font-size:11px">Position:&nbsp;&nbsp;</b><text style="color:#333333">${data.companypos}</text></td>
						<!--<td><b style="color:#808080; font-size:11px">Blk&Lot/Room/Door/Stall/Hse#:&nbsp;&nbsp;</b><text style="color:#333333">${data.plan_code}</text></td>-->
						<!-- <td><b style="color:#808080; font-size:11px">:&nbsp;&nbsp;</b><text style="color:#333333">${data.plan_code}</text></td> -->
						<td></td>
					
					</tr>
					
					<!-- <tr>
						<td><b style="color:#808080; font-size:11px">Building/Apartment Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.building}</text></td>
						<td><b style="color:#808080; font-size:11px">Street Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.streetname}</text></td>
						<td><b style="color:#808080; font-size:11px">Subd/Village:&nbsp;&nbsp;</b><text style="color:#333333">${data.village}</text></td>
						
					
					</tr> -->
				
				<tr>
						<td><b style="color:#808080; font-size:11px">Barangay:&nbsp;&nbsp;</b><text style="color:#333333">${data.addBrgy}</text></td>
						<td><b style="color:#808080; font-size:11px">City/Town:&nbsp;&nbsp;</b><text style="color:#333333">${data.addCity}</text></td>
						<td><b style="color:#808080; font-size:11px">Region:&nbsp;&nbsp;</b><text style="color:#333333">${data.addRegion}</text></td>
						

						
					</tr>
					 
					<tr>
					<td><b style="color:#808080; font-size:11px">Address Line:&nbsp;&nbsp;</b><text style="color:#333333">${data.addLine1}</text></td>
						<td><b style="color:#808080; font-size:11px">Sketch from Google Map&nbsp;&nbsp;</b><text style="color:#333333"><a href="#myMapModal" class="btn" data-toggle="modal"><i style="color:blue;" class="fa fa-map-marker"></i></a></text></td>
						<!-- <td></td>-->		
					</tr>
					

					<tr>
						<td><b style="color:blue; font-size:12px">Secondary Contact Details</b></td>
						<td></td>
						<td></td>
					
					</tr>
				
					
					<tr>
						<td><b style="color:#808080; font-size:11px">Contact Person:&nbsp;&nbsp;</b><text style="color:#333333">${data.contact_person}</text></td>
						<td><b style="color:#808080; font-size:11px">Contact Number:&nbsp;&nbsp;</b><text style="color:#333333">${data.contact_number}</text></td>
						<td></td>
			

					</tr>
					
					<tr>
						<td><b style="color:#808080; font-size:11px">Email Address:&nbsp;&nbsp;</b><text style="color:#333333">${data.email}</text></td>
						<td></td>
						<td></td>

					</tr>
					
		
					
			</tbody> 
			</table>

<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->	<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->				
<!--BUSINESS APPLICATION-->	

</c:if>








		<input type = "hidden" id="lat" value="${data.lat}"/>
		<input type = "hidden" id="lng"value="${data.lng }">
			
	<article class="module width_full" style="margin-bottom:20px; margin-left:10px; width:550px;">			
	<div class="linespace">
			</div>
			<div id="tab1" class="tab_content" style="height:200px; width:600px; overflow-x:scroll;">
			<table class="tablesorter" > 
			
			<c:set var="count" value="1" />
					<c:forEach items="${comment}" var="data">
						<c:set var="count" value="${count + 1}"/>
	
									<tbody> 
				<tr>   				
    				
<td><strong  style="color:#3B5998">${data.getUser_id()}:</strong>&nbsp;wrote on<c:set var="now" value="${data.getCreated()}" /><br/>
					${data.getContent()}
				</td> 
    					<td></td>
    				<td>
    				
    				<small><jsp:useBean id="dateValue" class="java.util.Date" />
				<jsp:setProperty name="dateValue" property="time" value="${data.getCreated()}" />
				<fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy HH:mm" /></small>

    				</td> 
    				</c:forEach>
    			
				</tr> 
				

			
			</tbody> 
			</table>
		
		</div><!-- end of tab_container -->
		<c:forEach var="data" items="${application }">
				<form action="saveCommentM" method="post" >
						<textarea rows="4" cols="50" name="content"></textarea>
						<br>
						<input type="hidden" name="user_id" value="${user}"/>
						<input type="hidden" name="application_id" value="${data.getApplication_id()}"/>
						<input type="hidden" name="edited_by" value="${user}"/>
						<input type="submit" value="Comment" name="comment" style="margin-top:5px; margin-bottom:10px;">
					</form>
		</c:forEach>					
				
		</article><!-- end of content manager article -->

	</section>
	

</c:forEach>

<div class="modal fade" id="myMapModal">
    <div class="modal-dialog">
        <div class="modal-content">
           <!--  <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
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





		
	</div><!-- End of Container -->
	
	<div class="footer">
		<p>&copy; 2013 Circles</p>
	</div>
	
	

	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

			<script type="text/javascript" src="resources/js/jquery.min.js"></script> 
			<script type="text/javascript">

			function DropDown(el) {
				this.dd = el;
				this.initEvents();
			}
			DropDown.prototype = {
				initEvents : function() {
					var obj = this;

					obj.dd.on('click', function(event){
						$(this).toggleClass('active');
						event.stopPropagation();
					});	
				}
			}

			$(function() {

				var dd = new DropDown( $('#dd') );

				$(document).click(function() {
					// all dropdowns
					$('.wrapper-dropdown-2').removeClass('active');
				});

			});

		</script>




</body>
</html>
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
			width:100%;
		}
	</style>
	<script src="resources/js/jquery-1.7.1.min.js"></script>
	<script src="resources/js/lightbox-2.6.min.js"></script>
	<link href="resources/css/lightbox.css" rel="stylesheet" />
	
	<link rel="stylesheet" href="resources/css/istyle.css" />

	<link rel="stylesheet" href="resources/css2/managertable.css" />

	
	<link rel="stylesheet" href="resources/css/font-awesome.css" />
<link rel="stylesheet" href="resources/css/font-awesome.min.css" />
<link rel="stylesheet" href="resources/css/recentmenus.css" />
	<link rel="stylesheet" href="resources/css/nav.css" />
	<link rel="stylesheet" href="resources/css2/imagegal.css" />
	<title>Circles</title>
</head>
<body>
	<div class="container">
		<div class="header clearfix">
			
			
			<div class="nav">
				
				<ul class="menu">
					<li><a href="salesmtd"><i class="fa fa-usd"></i>&nbsp;SALES</a></li>
					<li><a href="agent"><i  class="icon-agent" style="font-size:20px;"></i>&nbsp;AGENTS</a></li>		
					<li><a href="recent"  class="selected"><i class="fa fa-clock-o"></i>&nbsp;RECENT</a></li>	
							<li><a href="settings"><i class="fa fa-mobile-phone"></i>&nbsp;MOBILE</a></li>		
					<li><a href="logout"><i class="fa fa-laptop"></i>&nbsp;Log out</a></li>
					
				</ul>
			</div><!-- End of Navigation -->
			



		</div><!-- End of Header -->
	
		

		<section id="main">
		<article class="module" >
			<div class="linespace">
			</div>
			<div class="allapps">

		
			<c:forEach items="${application}" var="applications">
			
			<div id="tab1" class="tab_content" style="padding: 12px 12px 30px 12px">
			
				<table>
					<tr>
						<td><b>Application ID :</b> </td>
						<td>${applications.getApplication_id()}</td>
					</tr>
					<tr>
						<td><b>First Name:</b></td>  <td>${applications.getFirstName()}</td>
						<td><b>Middle Name:</b></td> <td>${applications.getMiddleName()} </td>
						<td><b>Last Name:</b></td> <td>${applications.getLastName()}</td>
					</tr>
				
					
					<tr>
						<td><b>Birthday:</b></td> <td>${applications.getBirthday()}</td>
						<td><b>Birth Place:</b></td> <td>${applications.getBirthplace() }</td>
						<td><b>Occupation:</b></td> <td>${applications.getOccupation() }</td>
					</tr>
				
					<tr>
						<td><b>Position Level:</b></td> <td>${applications.getPosition_level() }</td>
						<td><b>Tin No:</b></td> <td>${applications.getTin() }</td>
						<td><b>SSS/GSIS No:</b></td> <td>${applications.getSss() }</td>
					</tr>
					
				
					
					<tr>
						<td><b>Position Title:</b></td> <td>${applications.getPosition_title() }</td>
						<td><b>Tenure:</b></td> <td>${applications.getTenure() }</td>
						<td><b>Monthly Income:</b></td> <td>${applications.getMonthly_income() }</td>
					</tr>
					
				
					
					<tr>
						<td><b>Company Name:</b></td> <td>${applications.getCompany_name() }</td>
						<td><b>Business Nature:</b></td> <td>${applications.getBusiness_nature() }</td>
						<td><b>Civil Status:</b></td> <td>${applications.getCivil_status()}</td>
					</tr>
					
				
					
					<tr>
						<td><b>Gender:</b></td> <td>${applications.getGender() }</td>
						<td><b>Dependents:</b></td> <td>${applications.getDependents() }</td>
						<td><b>Other Phone Subscriptions:</b></td> <td>${applications.getOther_phone_subscriptions() }</td>
					</tr>
					
					
					
					<tr>
						<td><b>Source of Fund:</b></td> <td>${applications.getFundssource() }</td>
						<td><b>Mothers Maiden Name:</b></td> <td>${applications.getMothers_maidenname() }</td>
						
					</tr>
					
				
					
					<tr>
						<td><b>Spouse Firstname:</b></td> <td>${applications.getSpouse_firstName() }</td>
						<td><b>Spouse Middlename:</b></td> <td>${applications.getSpouse_middleName() }</td>
						<td><b>Spouse Lastname:</b></td> <td>${applications.getSpouse_lastName() }</td>
						<td><b>Spouse Birthday:</b></td> <td>${applications.getSpouse_birthday() }</td>
					</tr>
					
					
					
					<tr>
						<td><b>Phone Code:</b></td> <td>${applications.getPhone_code() }</td>
						<td><b>Plan Code:</b></td> <td>${applications.getPlan_code() }</td>
						<td><b>Status:</b></td> <td>${applications.getStatus() }</td>
						<td><b>Reference No.:</b></td> <td>${applications.getRef_no() }</td>
					</tr>
					
					
				
					<tr>
						<td><b>Email:</b></td> <td>${applications.getEmail() }</td>
						<td><b>Type:</b></td> <td>${applications.getType() }</td>
					</tr>
					
					
					
					<tr>
						<td><b>Region:</b></td> <td>${applications.getAddRegion() }</td>
						<td><b>City:</b></td> <td>${applications.getAddCity() }</td>						
						<td><b>Barangay:</b></td> <td>${applications.getAddBrgy() }</td>
					</tr>
					
					
					
					<tr>
						<td><b>Line Number 1:</b></td> <td>${applications.getAddLine1() }</td>
						<td><b>Line Number 2:</b></td> <td>${applications.getAddLine2() }</td>
						<td><b>Zip Code:</b></td> <td>${applications.getZipCode() }</td>
					</tr>
					
										
					<tr>
						<td><b>Work Region:</b></td> <td>${applications.getAddWorkRegion() }</td>
						<td><b>Work City:</b></td> <td>${applications.getAddWorkCity() }</td>
						<td><b>Work Barangay:</b></td> <td>${applications.getAddWorkBrgy() }</td>
						<td><b>Work Line Number 1:</b></td> <td>${applications.getAddWorkLine1() }</td>
						<td><b>Work Line Number 2:</b></td> <td>${applications.getAddWorkLine2() }</td>
						<td><b>Work Zip Code:</b></td> <td>${applications.getWorkZipCode() }</td>
					</tr>
					
					
					
					<tr>
						<td><b>Send Bill To:</b></td> <td>${applications.getSendBillTo() }</td>
						<td><b>Telephone No.:</b></td> <td>${applications.getTelephone() }</td>
						<td><b>Mobile No:</b></td> <td>${applications.getMobile() }</td>
					</tr>
					
					
					
					<tr>
						<td><b>Phone Retrieval Type:</b></td> <td>${applications.getPhone_retrieval_type() }</td>
						<td><b>Business Center Name:</b></td> <td>${applications.getBusiness_center_name() }</td>
						<td><b>Business Center Longitude:</b></td> <td>${applications.getBusiness_center_lng() }</td>
						<td><b>Business Center Latitude:</b></td> <td>${applications.getBusiness_center_lat() }</td>
					</tr>
					
					<tr>
						<td><b>Payment Type:</b></td> <td>${applications.getPayment_type() }</td>
						<td><b>Credit Card No.:</b></td> <td>${applications.getCc_number() }</td>
						<td><b>Credit Card Bank:</b></td> <td>${applications.getCc_bank() }</td>
						<td><b>PP Email:</b></td> <td>${applications.getPp_email() }</td>
						<td><b>PP Reference No.:</b></td> <td>${applications.getPp_refNo() }</td>
					</tr>
					
					<tr>
						<td><b>COD Amount:</b></td> <td>${applications.getCod_amt() }</td>
						
					</tr>
					
					
				</table>
				
				</div><!-- end of tab_container -->
				<hr>
				<h2>Image</h2>
				<center>
				<div id="tab1" class="tab_content" style="padding: 12px 12px 30px 12px">
				
				
				
				<table>
					
					<tr>	
						<th><b>Image</b></th>
						
						<td width="50px"></td>
						
						<th><b>Termination Image</b></th>
					</tr>
					
					<tr>	
					
						<c:if test = "${!empty applications.getImage() && !empty applications.getTermination_image()}">
								<td><a class="img" href="resources/uploaded/${applications.getImage() }" data-lightbox="${applications.getImage() } ">
								<img src="resources/uploaded/${applications.getImage()}"></a></td>	
								
								<td width="50px"> </td>
								
								<td><a class="img" href="resources/uploaded/${applications.getTermination_image()}" data-lightbox="${applications.getTermination_image() }">
								<img src="resources/uploaded/${applications.getTermination_image() }"></a></td>				
						</c:if>
						
						<c:if test = "${applications.getImage() == null}">
								<td><img src="resources/uploaded/filenotfound.jpg"></td>
								
								<td width="50px"> </td>
								
								<td><a class="img" href="resources/uploaded/${applications.getTermination_image()}" data-lightbox="${applications.getTermination_image() }">
								<img src="resources/uploaded/${applications.getTermination_image() }"></a></td>
						</c:if>
						
						<c:if test = "${applications.getTermination_image() == null}">
								<td><a class="img" href="resources/uploaded/${applications.getImage() }" data-lightbox="${applications.getImage() } ">
								<img src="resources/uploaded/${applications.getImage()}"></a></td>
								
								<td width="50px"> </td>
								
								<td><img src="resources/uploaded/filenotfound.jpg"></td>		
						</c:if>
					</tr>
					
					
				</table>
				
				</div><!-- end of tab_container -->
				</center>
				
				<hr>
				<h2>Doc Identity</h2>
				<center>
				<div id="tab1" class="tab_content" style="padding: 12px 12px 30px 12px">
				
				<table >
						<tr>
							<th><b>Doc Identity SSS</b></th>
							<td width="50px"> </td>	
							<th><b>Doc Identity PagIbig</b></th>
							<td width="50px"> </td>
							<th><b>Doc Identity PhilHealth</b></th>
						</tr>
						
						<tr>
							<c:choose>
							    <c:when test="${! empty applications.getDoc_identity_sss() && !empty applications.getDoc_identity_pagibig() && !empty applications.getDoc_identity_philhealth()}">
							    
									    <td>
										<a class="img" data-lightbox="${applications.getDoc_identity_sss() }" href="resources/uploaded/${applications.getDoc_identity_sss()}">
											<img src="resources/uploaded/${applications.getDoc_identity_sss()}">
										</a>
										</td>
										
										<td width="50px"> </td>	
										
										<td> 
										<a class="img" data-lightbox="${applications.getDoc_identity_pagibig() }" href="resources/uploaded/${applications.getDoc_identity_pagibig() }">
										<img src="resources/uploaded/${applications.getDoc_identity_pagibig()}">
										</a>
										</td>
										
										<td width="50px"> </td>	
										
										<td> <a class="img" data-lightbox="${applications.getDoc_identity_philhealth() }" href="resources/uploaded/${applications.getDoc_identity_philhealth()}">
										<img src="resources/uploaded/${applications.getDoc_identity_philhealth()}">
										</a>
										</td>
							    </c:when>
							    
							    <c:when test="${empty applications.getDoc_identity_sss() && empty applications.getDoc_identity_pagibig() && empty applications.getDoc_identity_philhealth()}">
							    
									     <td><img src="resources/uploaded/filenotfound.jpg"></td>
									     
									     <td width="50px"> </td>	
										
										 <td><img src="resources/uploaded/filenotfound.jpg"></td>
										 
										 <td width="50px"> </td>	
										
										 <td><img src="resources/uploaded/filenotfound.jpg"></td>
							    </c:when>
							    
							    <c:when test="${empty applications.getDoc_identity_sss()}">
							       <td><img src="resources/uploaded/filenotfound.jpg"></td>
							       
							       <td width="50px"> </td>	
							       
							       		<td> 
										<a class="img" data-lightbox="${applications.getDoc_identity_pagibig() }" href="resources/uploaded/${applications.getDoc_identity_pagibig() }">
										<img src="resources/uploaded/${applications.getDoc_identity_pagibig()}">
										</a>
										</td>
										
									<td width="50px"> </td>	
										
										<td> <a class="img" data-lightbox="${applications.getDoc_identity_philhealth() }" href="resources/uploaded/${applications.getDoc_identity_philhealth()}">
										<img src="resources/uploaded/${applications.getDoc_identity_philhealth()}">
										</a>
										</td>
							    </c:when>
							    
							    <c:when test="${empty applications.getDoc_identity_pagibig()}">
							       					       
							       <td> 
										<a class="img" data-lightbox="${applications.getDoc_identity_sss() }" href="resources/uploaded/${applications.getDoc_identity_sss() }">
										<img src="resources/uploaded/${applications.getDoc_identity_sss()}">
										</a>
										</td>
										
										<td width="50px"> </td>	
										
										<td><img src="resources/uploaded/filenotfound.jpg"></td>
										
										<td width="50px"> </td>	
										
										<td> <a class="img" data-lightbox="${applications.getDoc_identity_philhealth() }" href="resources/uploaded/${applications.getDoc_identity_philhealth()}">
										<img src="resources/uploaded/${applications.getDoc_identity_philhealth()}">
										</a>
										</td>
							    </c:when>
							    
							    <c:when test="${empty applications.getDoc_identity_philhealth()}">
							    
							       		<td>
							       		<a class="img" data-lightbox="${applications.getDoc_identity_sss() }" href="resources/uploaded/${applications.getDoc_identity_sss() }">
										<img src="resources/uploaded/${applications.getDoc_identity_sss()}">
										</a>
										</td>
										
										<td width="50px"> </td>	
							       					       
							       		<td> 
										<a class="img" data-lightbox="${applications.getDoc_identity_pagibig() }" href="resources/uploaded/${applications.getDoc_identity_pagibig() }">
										<img src="resources/uploaded/${applications.getDoc_identity_pagibig()}">
										</a>
										</td>
										
										<td width="50px"> </td>	
										
										<td><img src="resources/uploaded/filenotfound.jpg"></td>
										
							    </c:when>
							    
							    
							    <c:when test="${!empty applications.getDoc_identity_sss()}">
							    
							       		<td>
							       		<a class="img" data-lightbox="${applications.getDoc_identity_sss() }" href="resources/uploaded/${applications.getDoc_identity_sss() }">
										<img src="resources/uploaded/${applications.getDoc_identity_sss()}">
										</a>
										</td>
										
										<td width="50px"> </td>	
							       									    
							       		<td><img src="resources/uploaded/filenotfound.jpg"></td>
							       		
							       		<td width="50px"> </td>	
					
										<td><img src="resources/uploaded/filenotfound.jpg"></td>
										
							    </c:when>
							    
							    <c:when test="${!empty applications.getDoc_identity_pagibig()}">
							    		
							    		<td><img src="resources/uploaded/filenotfound.jpg"></td>
							    		
							    		<td width="50px"> </td>	
							    		
							       		<td>
							       		<a class="img" data-lightbox="${applications.getDoc_identity_pagibig() }" href="resources/uploaded/${applications.getDoc_identity_pagibig() }">
										<img src="resources/uploaded/${applications.getDoc_identity_pagibig()}">
										</a>
										</td>
										
										<td width="50px"> </td>	
							       									       				
										<td><img src="resources/uploaded/filenotfound.jpg"></td>
										
							    </c:when>
							    
							    <c:when test="${!empty applications.getDoc_identity_philhealth()}">
							    		
							    		<td><img src="resources/uploaded/filenotfound.jpg"></td>
							    		
							    		<td width="50px"> </td>	
							       									       				
										<td><img src="resources/uploaded/filenotfound.jpg"></td>
										
										<td width="50px"> </td>	
										
										<td>
							       		<a class="img" data-lightbox="${applications.getDoc_identity_philhealth() }" href="resources/uploaded/${applications.getDoc_identity_philhealth() }">
										<img src="resources/uploaded/${applications.getDoc_identity_philhealth()}">
										</a>
										</td>
										
							    </c:when>
							    
							</c:choose>
							
							
							
							
							
						</tr>
						
						<tr>
							<th><b>Doc Identity SSS No.:</b> ${applications.getDoc_identity_sss_no() }</th>
							<td width="50px"> </td>	
							<th><b>Doc Identity PagIbig No.:</b> ${applications.getDoc_identity_pagibig_no() }</th>
							<td width="50px"> </td>	
							<th><b>Doc Identity PhilHealth No.:</b> ${applications.getDoc_identity_philhealth_no() }</th>
						</tr>
						
						<tr height = 50px></tr>
					
					<tr>
						<c:if test="${!empty applications.getDoc_identity_tin() && !empty applications.getDoc_identity_driverslicense() && !empty applications.getDoc_identity_passport() }">
					
							<td><b>Doc Identity Tin</b> <a class="img" data-lightbox="${applications.getDoc_identity_tin() }" href="resources/uploaded/${applications.getDoc_identity_tin() }">
							<img src="resources/uploaded/${applications.getDoc_identity_tin()}">
							</a></td>
							
							<td width="50px"> </td>	
							
							<td><b>Doc Identity Drivers Licence</b><a class="img" data-lightbox="${applications.getDoc_identity_driverslicense() }" href="resources/uploaded/${applications.getDoc_identity_driverslicense() }">
							<img src="resources/uploaded/${applications.getDoc_identity_driverslicense()}">
								</a></td>
							
							<td width="50px"> </td>	
							
							<td><b>Doc Identity Passport</b> <a class="img" data-lightbox="${applications.getDoc_identity_passport() }" href="resources/uploaded/${applications.getDoc_identity_passport() }">
							<img src="resources/uploaded/${applications.getDoc_identity_passport()}">
								</a></td>
						</c:if>
						
						<c:if test="${empty applications.getDoc_identity_tin() && empty applications.getDoc_identity_driverslicense() && empty applications.getDoc_identity_passport() }">
					
							<td><b>Doc Identity Tin</b><img src="resources/uploaded/filenotfound.jpg"></td>
							
							<td width="50px"> </td>	
							
							<td><b>Doc Identity Drivers License</b><img src="resources/uploaded/filenotfound.jpg"></td>
							
							<td width="50px"> </td>	
							
							<td><b>Doc Identity Passport</b><img src="resources/uploaded/filenotfound.jpg"></td>
						</c:if>
						
						<c:if test="${!empty applications.getDoc_identity_tin()}">
						
							<td><b>Doc Identity getDoc_identity_tin</b> <a class="img" data-lightbox="${applications.getDoc_identity_tin() }" href="resources/uploaded/${applications.getDoc_identity_passport() }">
							<img src="resources/uploaded/${applications.getDoc_identity_tin()}">
							</a></td>
							
							<td width="50px"> </td>	
							
							<td><img src="resources/uploaded/filenotfound.jpg"></td>
							
							<td width="50px"> </td>	
							
							<td><img src="resources/uploaded/filenotfound.jpg"></td>
								
						</c:if>
						
						<c:if test="${!empty applications.getDoc_identity_driverslicense()}">							
							
							<td><img src="resources/uploaded/filenotfound.jpg"></td>
							
							<td width="50px"> </td>	
														
							<td><b>Doc Identity Drivers License</b> <a class="img" data-lightbox="${applications.getDoc_identity_driverslicense() }" href="resources/uploaded/${applications.getDoc_identity_driverslicense() }">
							<img src="resources/uploaded/${applications.getDoc_identity_driverslicense()}">
							</a></td>
														
							<td width="50px"> </td>	
							
							<td><img src="resources/uploaded/filenotfound.jpg"></td>
								
						</c:if>
						
						<c:if test="${!empty applications.getDoc_identity_passport()}">							
							
							<td><img src="resources/uploaded/filenotfound.jpg"></td>
							
							<td width="50px"> </td>	
														
							<td><b>Doc Identity Passport</b> <a class="img" data-lightbox="${applications.getDoc_identity_passport() }" href="resources/uploaded/${applications.getDoc_identity_passport() }">
							<img src="resources/uploaded/${applications.getDoc_identity_passport()}">
							</a></td>
														
							<td width="50px"> </td>	
							
							<td><img src="resources/uploaded/filenotfound.jpg"></td>
								
						</c:if>
					</tr>
					
					<tr>
						<td><b>Doc Identity Tin No.:</b> ${applications.getDoc_identity_tin_no() }</td>
						<td width="50px"> </td>	
						<td><b>Doc Identity Drivers Licence No.:</b> ${applications.getDoc_identity_driverslicense_no() }</td>
						<td width="50px"> </td>	
						<td><b>Doc Identity Passport No.:</b> ${applications.getDoc_identity_passport_no() }</td>
					</tr>
					
					<tr>
						
						<td><b>Doc Identity Company ID:</b> <a class="img" data-lightbox="${applications.getDoc_identity_companyid() }" href="resources/uploaded/${applications.getDoc_identity_companyid() }">
						<img src="resources/uploaded/${applications.getDoc_identity_companyid()}">
							</a></td>
						<td><b>Doc Identity Others:</b> <a class="img" data-lightbox="${applications.getDoc_identity_others() }" href="resources/uploaded/${applications.getDoc_identity_others() }">
						<img src="resources/uploaded/${applications.getDoc_identity_others()}">
							</a></td>
					</tr>
					
					<tr>
						<td><b>Doc Identity Company ID No.:</b> ${applications.getDoc_identity_companyid_no() }</td>
						<td><b>Doc Identity Others No.:</b> ${applications.getDoc_identity_others_no() }</td>
					</tr>
					
				</table>
					
				</div><!-- end of tab_container -->
				</center>
				
				<hr>
				<h2>Doc Address</h2>
				
				<div id="tab1" class="tab_content" style="padding: 12px 12px 30px 12px">
				<table >
					
					<tr>						
						<td><b>Doc Address Electricity:</b> <a class="img" data-lightbox="${applications.getDoc_address_electricity() }" href="resources/uploaded/${applications.getDoc_address_electricity() }">
						<img src="resources/uploaded/${applications.getDoc_address_electricity()}">
							</a></td>
						<td><b>Doc Address Water:</b> <a class="img" data-lightbox="${applications.getDoc_address_water() }" href="resources/uploaded/${applications.getDoc_address_water() }">
						<img src="resources/uploaded/${applications.getDoc_address_water()}">
							</a></td>
						<td><b>Doc Address ISP:</b> <a class="img" data-lightbox="${applications.getDoc_address_isp() }" href="resources/uploaded/${applications.getDoc_address_isp() }">
						<img src="resources/uploaded/${applications.getDoc_address_isp()}">
							</a></td>
					</tr>
					
					<tr>
						<td><b>Doc Address Electricity No.:</b> ${applications.getDoc_address_electricity_no() }</td>
						<td><b>Doc Address Water No.:</b> ${applications.getDoc_address_water_no() }</td>
						<td><b>Doc Address ISP No.:</b> ${applications.getDoc_address_isp_no() }</td>
					</tr>
					
					<tr>
						<td><b>Doc Address Cable:</b> <a class="img" data-lightbox="${applications.getDoc_address_cable() }" href="resources/uploaded/${applications.getDoc_address_cable() }">
						<img src="resources/uploaded/${applications.getDoc_address_cable()}">
							</a></td>
						<td><b>Doc Address Telecom:</b> <a class="img" data-lightbox="${applications.getDoc_address_telecom() }" href="resources/uploaded/${applications.getDoc_address_telecom() }">
						<img src="resources/uploaded/${applications.getDoc_address_telecom()}">
							</a></td>
						<td><b>Doc Address Bank Loan:</b> <a class="img" data-lightbox="${applications.getDoc_address_bankloan() }" href="/resources/uploaded/${applications.getDoc_address_bankloan() }">
						<img src="/resources/uploaded/${applications.getDoc_address_bankloan()}">
							</a></td>
						<td><b>Doc Address Others:</b> <a class="img" data-lightbox="${applications.getDoc_address_others() }" href="resources/uploaded/${applications.getDoc_address_others() }">
						<img src="resources/uploaded/${applications.getDoc_address_others()}">
							</a></td>
					</tr>
					
					<tr>
						<td><b>Doc Address Cable No.:</b> ${applications.getDoc_address_cable_no() }</td>
						<td><b>Doc Address Telecom No.:</b> ${applications.getDoc_address_telecom_no() }</td>
						<td><b>Doc Address Bank Loan No.:</b> ${applications.getDoc_address_bankloan_no() }</td>
						<td><b>Doc Address Others No.:</b> ${applications.getDoc_address_others_no() }</td>
					</tr>
					
				</table>
				
				</div><!-- end of tab_container -->
				
				<hr>
				<h2>Doc Income</h2>
				
				<div id="tab1" class="tab_content" style="padding: 12px 12px 30px 12px">
				
				<table>
					
					<tr>
						<td><b>Doc Income Bank Statement:</b> <a class="img" data-lightbox="${applications.getDoc_income_bankstatement() }" href="resources/uploaded/${applications.getDoc_income_bankstatement() }">
						<img src="resources/uploaded/${applications.getDoc_income_bankstatement()}">
							</a></td>
						<td><b>Doc Income Payslip:</b> <a class="img" data-lightbox="${applications.getDoc_income_payslip() }" href="resources/uploaded/${applications.getDoc_income_payslip() }">
						<img src="resources/uploaded/${applications.getDoc_income_payslip()}">
							</a></td>
						<td><b>Doc Income Security:</b> <a class="img" data-lightbox="${applications.getDoc_income_security() }" href="resources/uploaded/${applications.getDoc_income_security() }">
						<img src="resources/uploaded/${applications.getDoc_income_security()}">
							</a></td>
						<td><b>Doc Income Bonds:</b> <a class="img" data-lightbox="${applications.getDoc_income_bonds() }" href="resources/uploaded/${applications.getDoc_income_bonds() }">
						<img src="resources/uploaded/${applications.getDoc_income_bonds()}">
							</a></td>
					</tr>
					
					<tr>
						<td><b>Doc Income Bank Statement No.:</b> ${applications.getDoc_income_bankstatement_no() }</td>
						<td><b>Doc Income Payslip No.:</b> ${applications.getDoc_income_payslip_no() }</td>
						<td><b>Doc Income Security No.:</b> ${applications.getDoc_income_security_no() }</td>
						<td><b>Doc Income Bonds No.:</b> ${applications.getDoc_income_bonds_no() }</td>
					</tr>
					
					<tr>
						<td><b>Doc Income Stockcert:</b> <a class="img" data-lightbox="${applications.getDoc_income_stockcert() }" href="resources/uploaded/${applications.getDoc_income_stockcert() }">
						<img src="resources/uploaded/${applications.getDoc_income_stockcert()}">
							</a></td>
						<td><b>Doc Income Company Ownership:</b> <a class="img" data-lightbox="${applications.getDoc_income_companyownership() }" href="resources/uploaded/${applications.getDoc_income_companyownership() }">
						<img src="resources/uploaded/${applications.getDoc_income_companyownership()}">
							</a></td>
						<td><b>Doc Income Auto Charge:</b> <a class="img" data-lightbox="${applications.getDoc_income_autocharge() }" href="resources/uploaded/${applications.getDoc_income_autocharge() }">
						<img src="resources/uploaded/${applications.getDoc_identity_others()}">
							</a></td>
						<td><b>Doc Income Others:</b> <a class="img" data-lightbox="${applications.getDoc_income_others() }" href="resources/uploaded/${applications.getDoc_income_others() }">
						<img src="resources/uploaded/${applications.getDoc_income_others()}">
							</a></td>
					</tr>
					
					<tr>
						<td><b>Doc Income Stockcert No.:</b> ${applications.getDoc_income_stockcert_no() }</td>
						<td><b>Doc Income Company Ownership No.:</b> ${applications.getDoc_income_companyownership_no() }</td>
						<td><b>Doc Income Auto Charge No.:</b> ${applications.getDoc_income_autocharge_no() }</td>
						<td><b>Doc Income Others No.:</b> ${applications.getDoc_income_others_no() }</td>	
					</tr>
					
				</table>
				
				</div><!-- end of tab_container -->
				
				<hr>
				<h2>Doc Terms</h2>
				<center>
				<div id="tab1" class="tab_content" style="padding: 12px 12px 30px 12px">
				
				<table>
				
					<tr>
						<td><b><center>Doc Terms Sig. 1</center></b></td>
							<td width="50px"></td>
						<td><b><center>Doc Terms Sig. 2</center></b></td>
							<td width="50px"></td>
						<td><b><center>Doc Terms Sig. 3</center></b></td>
					</tr>
					
					<tr>
						<td> <a class="img" data-lightbox="${applications.getDoc_terms_sig1() }" href="resources/uploaded/${applications.getDoc_terms_sig1() }">
						<img src="resources/uploaded/${applications.getDoc_terms_sig1()}">
							</a></td>
							
							<td width="50px"></td>
							
						<td> <a class="img" data-lightbox="${applications.getDoc_terms_sig2() }" href="resources/uploaded/${applications.getDoc_terms_sig2() }">
						<img src="resources/uploaded/${applications.getDoc_terms_sig2()}">
							</a></td>
							
							<td width="50px"></td>
							
						<td> <a class="img" data-lightbox="${applications.getDoc_terms_sig3() }" href="resources/uploaded/${applications.getDoc_terms_sig3() }">
						<img src="resources/uploaded/${applications.getDoc_terms_sig3()}">
							</a></td>
					</tr>
					
					
					
				</table>
				
				</div><!-- end of tab_container -->
				</center>
				<hr>
				
				<div id="tab1" class="tab_content" style="padding: 12px 12px 30px 12px">
				
				<table>
					<tr>
						<td><b>Step 1 Done:</b></td> <td>${applications.getStep1Done() }</td>
						<td width="50px"></td>
						<td><b>Step 2 Done:</b></td> <td>${applications.getStep2Done() }</td>
						<td width="50px"></td>
						<td><b>Step 3 Done:</b></td> <td>${applications.getStep3Done() }</td>
					</tr>
					
					<tr>
						<td><b>Decline Remarks:</b></td> <td>${applications.getDecline_remarks() }</td>
						<td><b>Is Pushed:</b></td> <td>${applications.getIspushed() }</td>
						<td><b>Edited By:</b></td> <td>${applications.getEditedBy()}</td>
					</tr>
					
					<tr>
						<td><b>Update:</b></td> <td>${applications.getUpdate() }</td>
						<td><b>Created:</b></td> <td>${applications.getCreated() }</td>
						<td><b>Version:</b></td> <td>${applications.getVersion() }</td>
					</tr>
				</table>
				
				</div><!-- end of tab_container -->
				
				<center>
				<div id="tab1" class="tab_content" style="padding: 12px 12px 30px 12px">
					<div class="comment"> 
					<c:forEach items="${comment}" var="data">
						 <c:out value="1">x+1</c:out>
						${data.getUser_id()}:<br>
							${data.getContent()}<br>
						<span>date:${data.getCreated()}</span>
					</c:forEach>
					</div>
					<textarea rows="4" cols="70"></textarea>
					<br>
					<input type="submit" value="Comment" name="comment" >
					
				</div><!-- end of tab_container -->
				</center>
			</c:forEach>

		
			
		
			
		
		</div>
		</article><!-- end of content manager article -->



		<div class="spacer"></div>
	</section>



		

	</div><!-- End of Container -->
	
	<div class="footer">
		<p>&copy; 2013 Circles</p>
	</div>
</body>
</html>
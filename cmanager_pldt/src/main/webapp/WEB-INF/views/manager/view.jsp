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
			width:1360px;
		}
.nla{

with:500px;
}

	</style>
	<script src="resources/js/jquery-1.7.1.min.js"></script>
	<script src="resources/js/lightbox-2.6.min.js"></script>
	<link href="resources/css/lightbox2.css" rel="stylesheet" />
	

			<link rel="stylesheet" type="text/css" href="resources/css/wrapper.css" />
       	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css' />
		<script type="text/javascript" src="resources/js/modernizr.custom.79639.js"></script>


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
<div class="boxes"> </div>
		<div class="header clearfix">
			
			
			<div class="nav">
				
				<ul class="menu">
					<li><a href="salesmtd"><i class="fa fa-usd"></i>&nbsp;SALES</a></li>
					<li><a href="agent"><i  class="icon-agent" style="font-size:20px;"></i>&nbsp;AGENTS</a></li>		
					<li><a href="recent"  class="selected"><i class="fa fa-clock-o"></i>&nbsp;RECENT</a></li>	
							<li><a href="settings"><i class="fa fa-mobile-phone"></i>&nbsp;MOBILE</a></li>		
					<li><a href="logout"><i class="fa fa-laptop"></i>&nbsp;LOG OUT</a></li>
					
				</ul>
			</div><!-- End of Navigation -->
			



		</div><!-- End of Header -->
	
			

<c:forEach var="applications" items="${application }">
		
			<div class="wrapper-demo" style="margin-left:170px">
				
					<a href="view?applicationid=${applications.getApplication_id()}">
					<div id="dd" class="wrapper-dropdown-21 activemode">Details
						
					</div>
					</a>
				
				
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
	<c:if test="${data.application_type=='Personal'}">

			<table class="tablesorter" > 
			<thead > 
				<tr> 
    				<th></th> 
    				<th style="padding-left:70px;color:#444444;">RESIDENTIAL APPLICATION</th> 
    				<th></th> 
    				<th></th>
    				<th></th>

				</tr> 
			</thead> 
			
					<tbody> 
				

					<tr>
						<td><b style="color:#808080; font-size:12px">AGENT DETAILS</b></td>
						<td></td>
						<td></td>
					
					</tr>
					<tr>
						<td><b style="color:#808080; font-size:11px">Agent Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.user_id}</text></td>
						<!-- <td><b style="color:#808080; font-size:11px">Agent Code:&nbsp;&nbsp;</b><text style="color:#333333">1092201</text> </td> -->
						<td><b style="color:#808080; font-size:11px">Agent Contact Number:&nbsp;&nbsp;</b><text style="color:#333333">${data.mobile}</text></td>
						<td></td>
					
					</tr>
			
					<tr>
						<td><b style="color:#808080; font-size:11px">Plan Type:&nbsp;&nbsp;</b><text style="color:#333333">${data.plan_code}</text></td>
						<td><b style="color:#808080; font-size:11px">DATE OF APPLICATION:&nbsp;&nbsp;</b><text style="color:#333333">
						<jsp:useBean id="dateValuess" class="java.util.Date" />
						<jsp:setProperty name="dateValuess" property="time" value="${data.created}" />
						<fmt:formatDate value="${dateValuess}" pattern="MM/dd/yyyy" /></text></td>
						<!-- <td><b style="color:#808080; font-size:11px">CIS No.:&nbsp;&nbsp;</b><text style="color:#333333">123123123</text></td> -->
						<td></td>
					</tr>
					<tr>
						<td><b style="color:#808080; font-size:12px">CUSTOMER PERSONAL DATA</b></td>
						<td></td>
						<td></td>
					
					</tr>
					<tr>
						<td><b style="color:#808080; font-size:11px">Title:&nbsp;&nbsp;</b><text style="color:#333333">${data.title}.</text></td>
						<td><b style="color:#808080; font-size:11px">Last Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.lastName}</text></td>
						<td><b style="color:#808080; font-size:11px">First Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.firstName}</text></td>
						
					</tr>
					
					<tr>
						<td><b style="color:#808080; font-size:11px">Middle Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.middleName}</text></td>
						<td><b style="color:#808080; font-size:11px">Mother's Full Maiden Name&nbsp;&nbsp;</b><text style="color:#333333">${data.mothers_maidenname}</text></td>
						<td><b style="color:#808080; font-size:11px">Birthdate:&nbsp;&nbsp;</b><text style="color:#333333"><jsp:useBean id="bday" class="java.util.Date" />
						<jsp:setProperty name="bday" property="time" value="${data.birthday}" />
						<fmt:formatDate value="${bday}" pattern="MM/dd/yyyy" /></text></td>
						
					</tr>
					
					<tr>
						<td><b style="color:#808080; font-size:11px">Civil Status&nbsp;&nbsp;</b><text style="color:#333333">${data.civil_status}</text></td>
						<td><b style="color:#808080; font-size:11px">Blk&Lot/Room/Door/Stall/Hse#:&nbsp;&nbsp;</b><text style="color:#333333">${data.housenumber}</text></td>
						<!-- <td><b style="color:#808080; font-size:11px">&nbsp;&nbsp;</b><text style="color:#333333">${data.plan_code}</text></td> -->
						<td></td>
					</tr>
					
					<tr>
						<td><b style="color:#808080; font-size:11px">Building/Apartment Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.building}</text></td>
						<td><b style="color:#808080; font-size:11px">Street Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.streetname}</text></td>
						<td><b style="color:#808080; font-size:11px">Subd/Village:&nbsp;&nbsp;</b><text style="color:#333333">${data.village}</text></td>
						
					
				</tr>
				
				<tr>
						<td><b style="color:#808080; font-size:11px">Barangay:&nbsp;&nbsp;</b><text style="color:#333333">${data.addBrgy}</text></td>
						<td><b style="color:#808080; font-size:11px">City/Town:&nbsp;&nbsp;</b><text style="color:#333333">${data.addCity}</text></td>
						<td><b style="color:#808080; font-size:11px">Province:&nbsp;&nbsp;</b><text style="color:#333333">${data.addRegion}</text></td>
						

						
					</tr>
					
					<tr>
						<!--  <td><b style="color:#808080; font-size:11px">Sketch from Google Map:&nbsp;&nbsp;</b><text style="color:#333333">www.google.map/0202020</text></td>-->
						<td><b style="color:#808080; font-size:11px">Owned/Living with Relatives/Rented:&nbsp;&nbsp;</b><text style="color:#333333">${data.homeownership}</text></td>
						<td><b style="color:#808080; font-size:11px">Years of Stay:&nbsp;&nbsp;</b><text style="color:#333333">${data.yearsofstay}</text></td>
						<td></td>
			
					</tr>
					

					<tr>
						<td><b style="color:#808080; font-size:12px">Secondary Contact Details</b></td>
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
						<td><!-- <b style="color:#808080; font-size:11px">Telephone/Cellular Number:&nbsp;&nbsp;</b><text style="color:#333333">${data.plan_code}</text> --></td>
						<td><!-- <b style="color:#808080; font-size:11px">Employment Status:&nbsp;&nbsp;</b><text style="color:#333333">${data.plan_code}</text> --></td>

					</tr>
					
			
<tr>
						<td><b style="color:#808080; font-size:12px">Spouse Personal Data (*if Married)</b></td>
						<td></td>
						<td></td>
					
					</tr>

					
					
			<tr>
						<td><b style="color:#808080; font-size:11px">Full Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.spousefullname}</text></td>
						<td></td>
						<td></td>

					</tr>
					
					<tr>
						<td><b style="color:#808080; font-size:11px">Birthdate:&nbsp;&nbsp;</b><text style="color:#333333">
						<jsp:useBean id="bday2" class="java.util.Date" />
						<jsp:setProperty name="bday2" property="time" value="${data.spousebirthday}" />
						<fmt:formatDate value="${bday2}" pattern="MM/dd/yyyy" /></text></td>
						<td><b style="color:#808080; font-size:11px">Contact Number:&nbsp;&nbsp;</b><text style="color:#333333">${data.spousecontact}</text></td>
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
    				<th style="padding-left:70px;color:#444444;">BUSINESS APPLICATION</th> 
    				<th></th> 
    				<th></th>
    				<th></th>

				</tr> 
			</thead> 
			
					<tbody> 
				

					<tr>
						<td><b style="color:#808080; font-size:12px">AGENT DETAILS</b></td>
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
						<td><b style="color:#808080; font-size:11px">Plan Type:&nbsp;&nbsp;</b><text style="color:#333333">${data.plan_code}</text></td>
						<td><b style="color:#808080; font-size:11px">DATE OF APPLICATION:&nbsp;&nbsp;</b><text style="color:#333333">
						<jsp:useBean id="created" class="java.util.Date" />
						<jsp:setProperty name="created" property="time" value="${data.created}" />
						<fmt:formatDate value="${created}" pattern="MM/dd/yyyy" /></text></td></text></td>
						<!-- <td><b style="color:#808080; font-size:11px">CIS No.:&nbsp;&nbsp;</b><text style="color:#333333">${data.plan_code}</text></td> -->
						<td></td>
					</tr>
					<tr>
						<td><b style="color:#808080; font-size:12px">COMPANY DATA</b></td>
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
						<td><b style="color:#808080; font-size:11px">Blk&Lot/Room/Door/Stall/Hse#:&nbsp;&nbsp;</b><text style="color:#333333">${data.plan_code}</text></td>
						<!-- <td><b style="color:#808080; font-size:11px">:&nbsp;&nbsp;</b><text style="color:#333333">${data.plan_code}</text></td> -->
						<td></td>
					
					</tr>
					
					<tr>
						<td><b style="color:#808080; font-size:11px">Building/Apartment Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.building}</text></td>
						<td><b style="color:#808080; font-size:11px">Street Name:&nbsp;&nbsp;</b><text style="color:#333333">${data.streetname}</text></td>
						<td><b style="color:#808080; font-size:11px">Subd/Village:&nbsp;&nbsp;</b><text style="color:#333333">${data.village}</text></td>
						
					
				</tr>
				
				<tr>
						<td><b style="color:#808080; font-size:11px">Barangay:&nbsp;&nbsp;</b><text style="color:#333333">${data.addBrgy}</text></td>
						<td><b style="color:#808080; font-size:11px">City/Town:&nbsp;&nbsp;</b><text style="color:#333333">${data.addCity}</text></td>
						<td><b style="color:#808080; font-size:11px">Province:&nbsp;&nbsp;</b><text style="color:#333333">${data.addRegion}</text></td>
						

						
					</tr>
					<!--  
					<tr>
						<td><b style="color:#808080; font-size:11px">Sketch from Google Map:&nbsp;&nbsp;</b><text style="color:#333333">www.google.map/0202020</text></td>
						<td></td>
						<td></td>
			
					</tr>
					-->

					<tr>
						<td><b style="color:#808080; font-size:12px">Secondary Contact Details</b></td>
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
						<!-- <td><b style="color:#808080; font-size:11px">Telephone/Cellular Number:&nbsp;&nbsp;</b><text style="color:#333333">${data.plan_code}</text></td> -->
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
						<input type="hidden" name="user_id" value="${userid}"/>
						<input type="hidden" name="application_id" value="${data.getApplication_id()}"/>
						<input type="hidden" name="edited_by" value="${userid}"/>
						<input type="submit" value="Comment" name="comment" style="margin-top:5px; margin-bottom:10px;">
					</form>
		</c:forEach>					
				
		</article><!-- end of content manager article -->

	</section>


</c:forEach>






		
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
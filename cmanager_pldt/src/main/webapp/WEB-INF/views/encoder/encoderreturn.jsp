<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8' />
<title>Circles</title>
<link rel="stylesheet" href="resources/css/screen2.css" type="text/css" media="screen" title="default" />
<!-- <script type="text/javascript" src="resources/js/sortable.js"></script> -->
<script type="text/javascript" src="resources/js/sorttable.js"></script>

<style>
table.sortable th:not(.sorttable_sorted):not(.sorttable_sorted_reverse):after { 
    content: " \25B4\25BE"
}
</style>
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
			<a href="logout" id="logout"><img src="resources/css/images/shared/nav/nav_logout.gif" width="64" height="14" alt="" /></a>
			<div class="clear">&nbsp;</div>
		
			
		
		</div>
		<!-- end nav-right -->


		<!--  start nav -->
		<div class="nav">
		<div class="table">
		
		<ul  class="select"><li><a href="encoderrecent.html"><b>Recent</b><!--[if IE 7]><!--></a><!--<![endif]-->
		
		</ul>
		
		<div class="nav-divider">&nbsp;</div>
		                    
		<ul class="current"><li><a href="encoderreturn.html"><b>Return</b><!--[if IE 7]><!--></a><!--<![endif]-->
		
		</ul>
		<div class="nav-divider">&nbsp;</div>
		
		<ul class="select"><li><a href="encoderencode.html"><b>Encoded</b><!--[if IE 7]><!--></a><!--<![endif]-->
		
		</ul>



		<div class="clear"></div>
		</div>
		<div class="clear"></div>
		</div>
		<!--  start nav -->

</div>
<div class="clear"></div>
<!--  start nav-outer -->
</div>
<!--  start nav-outer-repeat................................................... END -->

 <div class="clear"></div>
 
<!-- start content-outer ........................................................................................................................START -->
<div id="content-outer">
<!-- start content -->
<div id="content">
<core:if test="${role == 'encoder'}">
	<!--  start page-heading -->
	<div id="page-heading">
		<h1>Return Applications</h1>
	</div>
	<!-- end page-heading -->

	<table border="0" width="100%" cellpadding="0" cellspacing="0" id="content-table">
	<tr>
		<th rowspan="3" class="sized"><img src="resources/css/images/shared/side_shadowleft2.jpg" width="20" height="300" alt="" /></th>
		<th class="topleft"></th>
		<td id="tbl-border-top">&nbsp;</td>
		<th class="topright"></th>
		<th rowspan="3" class="sized"><img src="resources/css/images/shared/side_shadowright2.jpg" width="20" height="300" alt="" /></th>
	</tr>
	<tr>
		<td id="tbl-border-left"></td>
		<td>
		<!--  start content-table-inner ...................................................................... START -->
		<div id="content-table-inner">
		
			<!--  start table-content  -->
			<div id="table-content">
			
				
		
		 
				<!--  start product-table ..................................................................................... -->
				<form id="mainform" action="">
				<table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table" class="sortable">
				<thead>
				<tr>
					<th class="table-header-repeat line-left"style="border-right: 1px solid #373737;"><a href="#">No.</a></th>
					<th class="table-header-repeat line-left minwidth-1"><a href="#">Name of Applicant</a>	</th>
					<th class="table-header-repeat line-left"><a href="#">Application ID</a></th>
					<th class="table-header-repeat line-left"><a href="#">Receipt Number</a></th>
					<!--<th class="table-header-repeat line-left"><a href="">Agent</a></th>-->
					<th class="table-header-repeat line-left"style="padding-right:1px"><a href="#">Status</a></th>
					<th class="table-header-repeat line-left" style="border-right: 1px solid #373737;"><a href="#">Date</a></th>
					
				</tr>
				</thead>
				<core:forEach var="data" items="${application}">
				<tr>
					<td>${data.count}</td>
					<core:if test="${data.application_type=='Personal'}">
					<td><a href="applicationform.html?appid=${data.application_id}">${data.getFirstName()}&nbsp;${data.getLastName()}</a></td>
					</core:if>
					<core:if test="${data.application_type=='Business'}">
					<td><a href="applicationform.html?appid=${data.application_id}">${data.companyauth}</a></td>						
					</core:if>
					<td><a href="applicationform.html?appid=${data.getApplication_id()}">${data.getApplication_id()}</a></td>
					<td><a href="applicationform.html?appid=${data.getApplication_id()}">${data.getRef_no()}</a></td>
					<!--<td><a href="applicationform.html?appid=${data.application_id}">${data.getUser_id()}</a></td>-->
					<core:if test="${applications.getStatus().equals('0')}">
						<td><a href="applicationform.html?appid=${data.application_id}" style="color:red">No Action</a></td>						</core:if>
						<core:if test="${data.getStatus().equals('1')}">
						<td><a href="applicationform.html?appid=${data.application_id}">Approved</a></td>
						</core:if>
						<core:if test="${data.getStatus().equals('2')}">
						<td><a href="applicationform.html?appid=${data.application_id}">Ongoing</a></td>
						</core:if>
						<core:if test="${data.getStatus().equals('3')}">
						<td><a href="applicationform.html?appid=${data.application_id}">Declined</a></td>
						</core:if>
					<td><a href="applicationform.html?appid=${data.application_id}"><jsp:useBean id="dateValue" class="java.util.Date" />
						<jsp:setProperty name="dateValue" property="time" value="${data.getCreated()}" />
						<fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy HH:mm" /></a></td>
					
				</tr>
				</core:forEach>

					
				</table>
				</form>
				<!--  end product-table................................... --> 
				
			</div>
			<!--  end content-table  -->
		
			
			
			
			
			<div class="clear"></div>
		 
		</div>
		<!--  end content-table-inner ............................................END  -->
		</td>
		<td id="tbl-border-right"></td>
	</tr>
	<tr>
		<th class="sized bottomleft"></th>
		<td id="tbl-border-bottom">&nbsp;</td>
		<th class="sized bottomright"></th>
	</tr>
	</table>
	<div class="clear">&nbsp;</div>
</core:if>
<core:if test="${role=='manager'}">
<h1>Access Denied!</h1>
</core:if>
</div>
<!--  end content -->
<div class="clear">&nbsp;</div>
</div>
<!--  end content-outer........................................................END -->

<div class="clear">&nbsp;</div>
    
      
 <core:if test="${role=='encoder'}">   
<!-- start footer -->         
<div id="footer">
	<!--  start footer-left -->
	<div id="footer-center">
	<p>&copy; Circles 2013 </p>
	</div>
	
	<!--  end footer-left -->
	<div class="clear">&nbsp;</div>
</div>
</core:if>
 <core:if test="${role=='manager'}">   
<!-- start footer -->         
<div id="footer" style="margin-top:450px">
	<!--  start footer-left -->
	<div id="footer-center">
	<p>&copy; Circles 2013 </p>
	</div>
	
	<!--  end footer-left -->
	<div class="clear">&nbsp;</div>
</div>
</core:if>
<!-- end footer -->
 
</body>
</html>
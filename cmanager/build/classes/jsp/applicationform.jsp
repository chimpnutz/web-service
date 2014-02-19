<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <meta charset='utf-8' />
<title>Circles</title>

		<link rel="stylesheet" href="../css/reveal.css">	
	  	
		<!-- Attach necessary scripts -->
		<!-- <script type="text/javascript" src="jquery-1.4.4.min.js"></script> -->
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
		<script type="text/javascript" src="../js/jquery-1.6.min.js"></script>
		<script type="text/javascript" src="../js/jquery.reveal.js"></script>





<link rel="stylesheet" href="../css/application.css" type="text/css" media="screen" title="default" />
<link rel="stylesheet" href="../css/tables.css" type="text/css" media="screen"/>

<link rel="stylesheet" href="../css/nav.css" type="text/css" media="screen"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>

<script src="../js/shadow.js"></script>
<script src="../js/shadow2.js"></script>

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


</head>
<body> 

 	<div class="clear"></div>


	
<div class="clear">&nbsp;</div>
 
<!--  start nav-outer-repeat................................................................................................. START -->
<div class="nav-outer-repeat"> 
<!--  start nav-outer -->
<div class="nav-outer"> 

		<!-- start nav-right -->
		<div id="nav-right">
		
			
	
			<div class="nav-divider">&nbsp;</div>
			<a href="logout.html" id="logout"><img src="../css/images/shared/nav/nav_logout.gif" width="64" height="14" alt="" /></a>
			<div class="clear">&nbsp;</div>
		
			
			

		
		</div>
		<!-- end nav-right -->


		<!--  start nav -->
		<div class="nav">



		<div class="table">	
		<ul class="current"><li><a href="encoder.html"><b>Recent</b><!--[if IE 7]><!--></a><!--<![endif]-->
		</ul>
		<div class="nav-divider">&nbsp;</div>                 
		<ul class="select"><li><a href="encoder-return.html"><b>Return</b><!--[if IE 7]><!--></a><!--<![endif]-->
		</ul>
		<div class="nav-divider">&nbsp;</div>
		<ul class="select"><li><a href="encoder-encode.html"><b>Encoded</b><!--[if IE 7]><!--></a><!--<![endif]-->
		</ul>
		<div class="clear"></div>
		</div>


		<div class="clear"></div>
		</div>




</div>
<div class="clear"></div>

</div><!--  start nav-outer-repeat................................................... END -->
         

<div id="topside">

	<!--  start footer-left -->
	<div id="top-center">
	 <div id="secondnav">
 </div>


 <div class="fontsa">
 <core:forEach var="data" items="${applist }">
	 <a href="applicationform.html?appid=${data.application_id}" style="margin-left:75px;margin-top:35px; "><p class="currentselect">
Details </p>
</a>
</core:forEach>
<a href="attachements.html" style="margin-top:35px;"><p style="margin-left:-65px;">
Attachements</p>
</a>


</div>


<div id="whitecover">
<div class="mimages">
<a href="#" class="big-link" data-reveal-id="myModal" data-animation="fade">
<img class="fade" src="../css/images/encoding3.png" style="cursor: pointer" width="120px" height="50px" />
</a>	
</div>

<div class="mimages2">
	<a href="#" class="big-link" data-reveal-id="myModal2" data-animation="fade">
<img class="fade" src="../css/images/return2.png" style="cursor: pointer" width="120px" height="50px" >
</a>
</div>
</div>



	</div>
	
	<!--  end footer-left -->








<div id="centerside">
<form:form commandName="applicationForm" id="applicationForm">
<core:forEach var="datas" items="${applist }">
<div id="centerside-center">
	
		<div id="myModal" class="reveal-modal">
					<p align="center" style="font-size:12px; font-family:arial">You're encoding the application of php name</p>
					<a href="encoderrecent.html">
					<button class="fsSubmitButton2" name="confirm" type="submit" title="confirm" style="margin-top:20px; margin-left:50px;cursor: pointer;  padding:2px;">Confirm</button></a>
		
					<a href="applicationform.html">
					<button class="fsSubmitButton" name="cancel" type="submit" title="cancel" style="padding:2px; cursor: pointer;margin-left:50px;">&nbsp;Cancel&nbsp;</button>
					</a>
					<a class="close-reveal-modal">&#215;</a>
		</div>

		<div id="myModal2" class="reveal-modal">
			<p align="center" style="font-size:12px; font-family:arial">You're returning the application of php name</p>


			<a href="encoder.html">


			<button class="fsSubmitButton2" name="confirm" type="submit" title="confirm"  style="margin-top:15px;cursor: pointer; margin-left:50px;  padding:2px;">Confirm</button></a>

			<a href="applicationform.html">
			<button class="fsSubmitButton" name="cancel" type="submit" title="cancel" style="padding:2px;cursor: pointer; margin-left:50px;">&nbsp;Cancel&nbsp;</button></a>
			<a class="close-reveal-modal">&#215;</a>
		</div>


		
<div id="borderside">



<div class="bluebox">
	<p> SMART POST INDIVIDUAL SERVICE APPLICATION FORM </p>
</div>

<div class="cutter">

</div>

<div class="leftmain">
	<p> Customer Personal<br/><br/> Information</p>
</div>



 

<div class="form1">

<text style="margin-left:60px; color: #999999;" >Last Name: <form:input path="lastName" style="margin-left:10px;" value="${datas.lastName }" readonly="true" /></text>
<text style="margin-left:75px;  color: #999999;" >First Name: <form:input path="firstName" style="margin-left:10px;" value="${datas.firstName }" readonly="true" /> </text>
<text style="margin-left:88px;  color: #999999;">Middle Name: <form:input path="middleName" style="margin-left:10px;" value="${datas.middleName }" readonly="true"/></text>

</div>



<div class="form2">
	
			
		
				
<text style="margin-left:72px;  color: #999999;">Birthdate: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:78px;  color: #999999;">Birth Place: <form:input path="birthPlace" style="margin-left:10px;" value="${datas}" readonly="true" /></text>
<text style="margin-left:98px;  color: #999999;">Occupation: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
				
</div>


<div class="form3">
			
<text style="margin-left:45px;  color: #999999;" >Position Level: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:96px;  color: #999999;">TIN NO: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:84px;  color: #999999;">SSS/GSIS NO: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
					
</div>


<div class="form4">

<text style="margin-left:52px;  color: #999999;" >Position Title: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:102px; color: #999999;">Tenure: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:74px;  color: #999999;">Monthly Income: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
				
</div>


<div class="form5">

<text style="margin-left:34px;  color: #999999;" >Company Name: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:30px;  color: #999999;">Nature of Business: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:64px;  color: #999999;">Credit Card Type: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
				
</div>



<div class="form6">

<text style="margin-left:13px;  color: #999999;">Credit Card Number: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:53px;  color: #999999;">Expiration Date: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:95px;  color: #999999;">Civil Status: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
					
</div>


<div class="form7">

<text style="margin-left:82px;  color: #999999;" >Gender: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:25px;  color: #999999;">No.of Dependencies: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:114px; color: #999999;">ID Type: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
				
</div>


<div class="form8">

<text style="margin-left:47px;  color: #999999;" >ID Date Expiry: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:78px;  color: #999999;">ID Number: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text  style="margin-left:71px;  color: #999999;">Car Ownership: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
				
</div>

<div class="form9">

<text style="margin-left:17px;  color: #999999;" >Years of Residence: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:40px;  color: #999999;">Home Ownership: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:1px; color: #999999;">	Other Telecom Subscription: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" />
				
</div>


<div class="form10">

<text style="color: #999999;">Mothers Maiden Name: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" />
<text style="margin-left:48px; color: #999999;" >Source of Funds: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
				
</div>


<div class="horizon">
</div>

<div class="bluebox2">

</div>
<div class="leftmain2">
<p> Contact Information</p>
</div>


<div class="form11">

<text style="margin-left:18px; color: #999999;" >Residence Number: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:35px; color: #999999;">Business Number: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:85px; color: #999999;">Fax Number: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>

</div>

<div class="form12">

<text  style="margin-left:41px; color: #999999;" >Mobile Number: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text  style="color: #999999;" >Existing Smart Mobile No: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" />
<text style="margin-left:72px; color: #999999;">Email Address: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>

</div>




<div class="horizon2">
</div>


<div class="bluebox3">

</div>

<div class="leftmain3">
<p> Spouse Information</p>
</div>


<div class="form13">

<text style="margin-left:64px; color: #999999;">Last Name: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:75px; color: #999999;" >First Name: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /></text>
<text style="margin-left:80px; color: #999999;">Middle Name: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>

</div>


<div class="form14">

<text style="margin-left:75px; color: #999999;">Birthdate: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:100px; color: #999999;">House: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:131px; color: #999999;">Unit: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
				
</div>




<div class="form15">

<text style="margin-left:88px; color: #999999;">Village: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:85px; color: #999999;">Barangay: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:131px; color: #999999;">City: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
				
</div>





<div class="horizon3">
</div>




<div class="bluebox4">

</div>

<div class="leftmain4">
<p> Nearest Relative Information</p>
</div>






<div class="form16">

<text style="margin-left:65px; color: #999999;">Last Name: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text  style="margin-left:79px; color: #999999;">First Name: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:73px; color: #999999;">Middle Name: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>

</div>


<div class="form17">

<text style="margin-left:77px; color: #999999;">Birthdate: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:103px; color: #999999;">House: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:124px; color: #999999;">Unit: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
				
</div>


<div class="form18">

<text style="margin-left:89px; color: #999999;" >Village: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:88px; color: #999999;">Barangay: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text  style="margin-left:126px; color: #999999;">City: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
				
</div>







<div class="horizon4">
</div>

<div class="bluebox5">

</div>



<div class="leftmain5">
<p> Customer Address <br/><br/> Information</p>
</div>


	

<div class="form19">

<text style="margin-left:89px; color: #999999;">House: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:120px; color: #999999;">Unit: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:107px; color: #999999;">Village: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
					
</div>



<div class="form20">

<text style="margin-left:73px; color: #999999;">Barangay: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text  style="margin-left:123px; color: #999999;">City: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:94px; color: #999999;">Zip Code: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
				
</div>








</div>
</div>



<div class="horizon4" style="margin-top:10px;"></div>

<div id="borderside2">

<div class="cutter2">

</div>

<div class="leftsideview">
<p> Bill Delivery Information</p>
</div>




<div class="form21">

<text style="margin-left:50px; color: #999999;" >Bills to be sent to my: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>

</div>

<div class="horizontal"></div>


<div class="leftsideview2">
<p> Awknowledgement</p>
</div>

<div class="form22">

<text style="margin-left:82px; color: #999999;" >Pre-termination: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /></text>
<text style="margin-left:20px; color: #999999;" >Pre-termination fee in Php: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>

</div>

<div class="horizontal2"></div>

<div class="leftsideview3">
<p>Automatic Debit Agreement  </p>
</div>



<div class="form23a">
<text style="margin-left:60px;" > <strong>Name of Card Holder: </strong></text>
</div>
<div class="form23">

<text style="margin-left:108px; color: #999999;" >Last Name: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /></text>
<text style="margin-left:105px; color: #999999;" >First Name: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:50px; color: #999999;" >Middle Name: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>

</div>

<div class="form24">

<text style="margin-left:115px; color: #999999;" >Birth Date: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /></text>

</div>



<div class="form25a">
<text style="margin-left:60px;" > <strong>Mothers Maiden Name: </strong></text>
</div>

<div class="form25">


<text style="margin-left:112px; color: #999999;" >Last Name: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /></text>
<text style="margin-left:105px; color: #999999;" >First Name: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:47px; color: #999999;" >Middle Name: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>

</div>



<div class="form26">
<text style="margin-left:62px; color: #999999;" >Credit Card Number: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /></text>
<text style="margin-left:75px; color: #999999;" >Card Expiry Date: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /></text>
<text style="margin-left:20px; color: #999999;" >Credit Card Issuer: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /></text>
</div>



<div class="leftsideview4">
<p>Smart Account Information </p>
</div>
<div class="horizontal3"></div>


<div class="form27a">
<text style="margin-left:60px; " ><strong>Name of Smart Sucscriber: </strong> </text>
</div>


<div class="form27">
<text style="margin-left:113px; color: #999999;" >Last Name: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /></text>
<text style="margin-left:105px; color: #999999;" >First Name: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:47px; color: #999999;" >Middle Name: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
</div>


<div class="form28">
<text style="margin-left:90px; color: #999999;" >Mobile Number: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /></text>
<text style="margin-left:75px; color: #999999;" >Account Number: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
</div>



<div class="leftsideview5">
<p>Smart Money Application</p>
</div>
<div class="horizontal4"></div>



<div class="form29a">
<text style="margin-left:60px; " ><strong>Applicants Virtual Smart Money Account Number: </strong></text> <input type="text" style="margin-left:10px; width:300px;" readonly> </text>
</div>


<div class="form29">
<text style="margin-left:68px; color: #999999;" >Referrence Number: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /></text>
<text style="margin-left:100px; color: #999999;" >SR Number: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
</div>





<div class="leftsideview6">
<p>Smart Postpaid Application</p>
</div>
<div class="horizontal5"></div>


<div class="form30">
<text style="margin-left:60px; color: #999999;" >Subscription Scheme: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /></text>
<text style="margin-left:43px; color: #999999;" >Subscription Package: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
<text style="margin-left:90px; color: #999999;" >Plan: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /> </text>
</div>


<div class="form31">
<text style="margin-left:130px; color: #999999;" >Handset: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /></text>
<text style="margin-left:90px; color: #999999;" >GSM Number: <form:input path="" style="margin-left:10px;" value="${datas}" readonly="true" /></text>
</div>

</div>
 
<!-- start footer -->         
<div id="footer2">
	<!--  start footer-left -->
	<div id="footer-center">
	<p>&copy; Circles 2013 </p>
	</div>
	
	<!--  end footer-left -->
	<div class="clear">&nbsp;</div>
</div>
<!-- end footer -->

</core:forEach>   
</form:form>

<!--  end content-outer -->

 </div>




</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8' />
<title>Circles</title>





<link rel="stylesheet" href="resources/css/tables.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="resources/css/attch.css" type="text/css" media="screen" title="default" />

	<!--  <link rel="stylesheet" href="resources/css/lightbox.css" media="screen"/> -->


	<script src="resources/js/jquery-1.10.2.min.js"></script>
	<!--  <script src="resources/js/lightbox-2.6.min.js"></script>  -->

	<script>
	var _gaq = _gaq || [];
	_gaq.push(['_setAccount', 'UA-2196019-1']);
	_gaq.push(['_trackPageview']);

	(function() {
		var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	})();
	</script>



<script src="resources/js/shadow.js"></script>

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

<style type="text/css">

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



            .viewer
            {
                height: 100%;
                position: relative;
                background-color: transparent;
            }
            
            .wrapper
            {
                border: 1px solid black;

                position: absolute;
                top: 5em;
                left: 1em;
                bottom: 1em;
                right: 1em;

                overflow: hidden; /*for opera*/
            }
            
            .toolbar
            {
                border: 1px solid black;

                position: absolute;
                top: 1em;
                left: 1em;
                right: 1em;
                height: 3em;
            }
            
 .modal .modal-header {
  border-bottom: none;
  position: relative;
}
.modal .modal-header .btn {
  position: absolute;
  top: 0;
  right: 0;
  margin-top: 0;
  border-top-left-radius: 0;
  border-bottom-right-radius: 0;
}
.modal .modal-footer {
  border-top: none;
  padding: 0;
}
.modal .modal-footer .btn-group > .btn:first-child {
  border-bottom-left-radius: 0;
}
.modal .modal-footer .btn-group > .btn:last-child {
  border-top-right-radius: 0;
}

.modal-open {
  overflow: hidden; }
  .modal-open .navbar-fixed-top,
  .modal-open .navbar-fixed-bottom {
    margin-right: 15px; }

body.modal-open {
 }

.modal {

  display: none;
  overflow: auto;
  overflow-y: scroll;
  position: fixed;
  margin-top: 40px;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 99999999999; }
  .modal.fade .modal-dialog {
    -webkit-transform: translate(0, -25%);
    -ms-transform: translate(0, -25%);
    transform: translate(0, -25%);
    -webkit-transition: -webkit-transform 0.3s ease-out;
    -moz-transition: -moz-transform 0.3s ease-out;
    -o-transition: -o-transform 0.3s ease-out;
    transition: transform 0.3s ease-out; }
  .modal.in .modal-dialog {
    -webkit-transform: translate(0, 0);
    -ms-transform: translate(0, 0);
    transform: translate(0, 0); }

.modal-dialog {
  margin-left: auto;
  margin-right: auto;
  width: 500px;

  z-index: 1050; }

.modal-content {
  position: relative;
  background-color: white;
  border: 1px solid #999999;
  border: 1px solid rgba(0, 0, 0, 0.2);
  border-radius: 6px;
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

  border-bottom: 1px solid #e5e5e5;
  min-height: 16.42857px; }

.modal-header .close {
  margin-top: -2px; }

.modal-title {
  margin: 0;
  line-height: 1.42857; }

.modal-body {

  height: 600px;
  width: 500px;
 }

.modal-footer {
  margin-top: 15px;
  
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
   
    
   }

  .modal-content {
    -webkit-box-shadow: 0 5px 15px rgba(0, 0, 0, 0.5);
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.5); } }

</style>


<script src="resources/js/jquery.js"></script>
<script src="resources/js/bootstrap.js"></script>

        <link rel="stylesheet" href="resources/css/jquery.iviewer.css" />
<script type="text/javascript" src="resources/js/jqueryui.js" ></script>
        <script type="text/javascript" src="resources/js/jquery.mousewheel.min.js" ></script>
        <script type="text/javascript" src="resources/js/jquery.iviewer.js" ></script>

<core:forEach var="dataz" items="${images}">
       <script type="text/javascript">
            jQuery(function($){

                
              	
              	var type = "${dataz.type}";
              	var img = "${dataz.getFilename()}";
              	
              	
              	if(type == 'identification'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
             		
              		
              		
                	viewer = $("#viewer1").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'proofofbilling'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer2").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'proofofincome'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer3").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'tin'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer4").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'driverslicense'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer5").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'passport'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer6").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'identity_others'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer7").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'electricity'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer8").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'water'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer9").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'isp'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer10").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'cable'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer11").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'telecom'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer12").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'bankloan'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer13").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'address_others'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer14").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'companyowner'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer15").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'payslip'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer16").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'security'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer17").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'bondno'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer18").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'stockcert'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer19").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'autocharge'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer20").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'income_others'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer21").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'sig1'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer22").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'sig2'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer23").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'sig3'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer24").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}
				
        		});
                
        </script>
</core:forEach>





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

<core:if test="${role=='encoder'}">
<core:forEach var="datas" items="${application }">
	<core:if test="${datas.application_type=='Residential'}">
	<div class="encname"><p>${datas.getFirstName()}&nbsp;${datas.getLastName() }</p></div>
</core:if>	
<core:if test="${datas.application_type=='Business'}">
	<div class="encname"><p>${datas.companyauth}</p></div>
</core:if>	
	
	<div class="encname2"><p>${datas.getApplication_id()}</p></div>
	</core:forEach>
</core:if>




		<div class="clear"></div>
		</div>
		<div class="clear"></div>
		</div>
		<!--  start nav -->

</div>


<div id="secondnav">
 </div>


<core:if test="${role=='encoder'}">
<div id="whitecover">





		
</div>
</core:if>


<div class="clear"></div>
<!--  start nav-outer -->
</div>
<!--  start nav-outer-repeat................................................... END -->

 <div class="clear"></div>
 
 <core:if test="${role=='encoder'}">
<div id="topside">

	<!--  start footer-left -->
	<div id="top-center">
	 



 <div class="fontsa">

 <core:forEach var="datas" items="${application}">
	 <a href="applicationform.html?appid=${datas.getApplication_id()}" style="margin-left:75px;margin-top:35px; "><p >
Details </p>
</a>
</core:forEach>
<a href="#" style="margin-top:35px;"><p class="currentselect" style="margin-left:-65px;">
Attachements</p>
</a>

</div>









	</div>
	</div>
</core:if>




<!-- start content-outer ........................................................................................................................START -->
<div id="content-outer" style="margin-top:-40px;">
<!-- start content -->
<div id="content">

	
<core:if test="${role=='encoder'}">
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
				
				<core:forEach var="data" items="${application}">
				
				<form id="mainform" action="" >
				<div id="main">
				<div style="float:left">
				<table border="0" width="255px" cellpadding="0" cellspacing="0" id="product-table">
				<tr>
					
					<th class="table-header-repeat line-left"><a href="">Identification</a>	</th>
				</tr>
														

							
								<tr>
																	
									
									<core:forEach var="dataz" items="${images}">
									
										<core:if test="${dataz.getType()=='identification'}">
										<td>	
											<p><b>${dataz.number }asdsad</p></b>
											<br/>
											<div style="float:right; margin-right:15px; margin-top:-40px;">
											<a data-toggle="modal" href="#myModal" ><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
											</div>
										</td>
										</core:if>
										</core:forEach>
									
											
								
								</tr>
																
				
				</table>
				</div>
					
				<div style="float:left">	
				<table border="0" width="255px" cellpadding="0" cellspacing="0" id="product-table">
				<tr>
					
					<th class="table-header-repeat line-left"><a href="">Proof Of Billing</a>	</th>
				</tr>
														

							
								<tr>
																	
								
								<core:forEach var="dataz" items="${images}">	
								<core:if test="${dataz.getType()=='proofofbilling'}">
									<td>
										<p><b>${dataz.number }asd</b></p>
										<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal2" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</core:if>
								</core:forEach>
							
				</table>	
					</div>
					
					<div style="float:left">	
				<table border="0" width="255px" cellpadding="0" cellspacing="0" id="product-table">
				<tr>
					
					<th class="table-header-repeat line-left"><a href="">Proof of Income</a>	</th>
				</tr>
														

							
								<tr>
																	
								
								<core:forEach var="dataz" items="${images}">		
									<core:if test="${dataz.getType()=='proofofincome'}">
									<td>
										<p><b>${data.number }asd</b></p>
										<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal3" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</core:if>	
								</core:forEach>	
								
																				
				</table>	
					</div>
					
					<div style="float:left">	
				<table border="0" width="255px" cellpadding="0" cellspacing="0" id="product-table">
				<tr>
					
					<th class="table-header-repeat line-left"><a href="">Terms of Use</a>	</th>
				</tr>
														

							
								<tr>
																	
									<core:if test="${data.doc_terms_sig1!=''}">
								<core:forEach var="dataz" items="${images}">	
								<core:if test="${dataz.getType()=='sig1'}">
									<td>
										<b><p>Signature 1</p></b>
										<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal22" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</core:if>
								</core:forEach>
								</core:if>
											
								
								</tr>
							
							
							
								<tr>
								
								<core:if test="${data.doc_terms_sig2!=''}">
								<core:forEach var="dataz" items="${images}">	
									<core:if test="${dataz.getType()=='sig2'}">
									<td>
										<b><p>Signature 2</p></b>
										<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal23" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</core:if>
								</core:forEach>
								</core:if>
								
								
								
								</tr>
								
								<tr>
								<core:if test="${data.doc_terms_sig3!=''}">	
								<core:forEach var="dataz" items="${images}">	
									<core:if test="${dataz.getType()=='sig3'}">
									<td>
										<b><p>Signature 3</p></b>
										<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal24" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</core:if>
								</core:forEach>
								</core:if>
								
								</tr>
							
								
				</table>	
					</div>
					
					</div>
					
					
					

				</form>
				</core:forEach>

				
				
				
			</div>
			<!--  end content-table  -->
		
			
			
			<!--  start paging..................................................... -->
			
			<!--  end paging................ -->
			
			<div class="clear"></div>
		 
		</div>










			<!--  end paging................ -->
			
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
	</core:if>

	<div class="clear">&nbsp;</div>

</div>
<!--  end content -->
<div class="clear">&nbsp;</div>
</div>
<!--  end content-outer........................................................END -->

<div class="clear">&nbsp;</div>

<div id="myModal" class="modal">
        <div class="modal-dialog">
          
 
                
                <div class="modal-body"  id="viewer1" class="viewer" >    </div>
    
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal2" class="modal">
        <div class="modal-dialog">
          
 
                
                <div class="modal-body"  id="viewer2" class="viewer" >    </div>
    
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal3" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer3" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal4" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer4" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal5" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer5" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal6" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer6" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal7" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer7" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal8" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer8" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal9" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer9" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal10" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer10" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal11" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer11" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal12" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer12" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal13" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer13" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal14" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer14" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal15" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer15" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal16" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer16" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal17" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer17" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal18" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer18" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal19" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer19" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal20" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer20" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal21" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer21" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal22" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer22" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal23" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer23" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->

<div id="myModal24" class="modal">
        <div class="modal-dialog">
          
 
                
     
    			<div class="modal-body"  id="viewer24" class="viewer" >    </div>
    			
            
               
 
           
        </div><!-- /.modal-dalog -->
</div><!-- /.modal -->
 

	<core:if test="${role=='manager'}">
	<h1 style="margin-top:100px; margin-left:50px">Access Denied</h1>
	</core:if> 
    
<!-- start footer -->  
<core:if test="${role=='encoder'}">       
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
<div id="footer" style="margin-top:430px">
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
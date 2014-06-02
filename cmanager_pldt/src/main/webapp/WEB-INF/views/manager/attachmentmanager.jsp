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
	</style>
		<!-- <link rel="stylesheet" href="resources/css/lightbox.css" media="screen"/> -->


	<script src="resources/js/jquery-1.10.2.min.js"></script>
	<!-- <script src="resources/js/lightbox-2.6.min.js"></script> -->

	<script>
	var _gaq = _gaq || [];
	_gaq.push(['_setAccount', 'UA-2196019-1']);
	_gaq.push(['_trackPageview']);

	(function() {
		var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	})();
	</script>	<link rel="stylesheet" type="text/css" href="resources/css/tblatc.css" />

			<link rel="stylesheet" type="text/css" href="resources/css/wrapper.css" />
       	

	<link rel="stylesheet" href="resources/css/istyle.css" />

	<link rel="stylesheet" href="resources/css2/managertable.css" />

	
	<link rel="stylesheet" href="resources/css/font-awesome.css" />
<link rel="stylesheet" href="resources/css/font-awesome.min.css" />
<link rel="stylesheet" href="resources/css/recentmenus.css" />
	<link rel="stylesheet" href="resources/css/nav.css" />
	
	<title>Circles</title>

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

<c:forEach var="dataz" items="${images}">
       <script type="text/javascript">
            jQuery(function($){

                
              	
              	var type = "${dataz.type}";
              	var img = "${dataz.getFilename()}";
              	
              	
              	if(type == 'sss'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
             		
              		
              		
                	viewer = $("#viewer1").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'pagibig'){
              		var viewer;
              		var img = "${dataz.getFilename()}";
              		
                	viewer = $("#viewer2").iviewer({
                        
                        src: "resources/uploaded/"+img,
 
               			 onStartDrag: function(ev, coords) { return true; },

                        initCallback: function(ev) {
                            this.container.context.iviewer = this;
                        }
                    	});
              
              	}else if(type == 'companyid'){
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
</c:forEach>	
	
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
							<li><a href="addproduct"><i class="fa fa-cubes"></i>&nbsp;ADD PRODUCTS</a></li>		
					<li><a href="logout"><i class="fa fa-laptop"></i>&nbsp;LOG OUT</a></li>
					
				</ul>
			</div><!-- End of Navigation -->
			



		</div><!-- End of Header -->
	
		
			<div class="wrapper-demo" style="margin-left:170px">
					<c:forEach items="${application}" var="applications">
					<a href="view?applicationid=${applications.getApplication_id()}">
					<div id="dd" class="wrapper-dropdown-21"  >Details
						
					</div>
					</a>
					</c:forEach>
				
				</div>


					<c:forEach items="${application}" var="applications">
					<div class="wrapper-demo" style="margin-left:420px;">
					<a href="attachments3?appid=${applications.getApplication_id()}"><div id="dd" class="wrapper-dropdown-2 activemode" >Attachments
						
					</div></a>
					</c:forEach>
						

				</div>

	<c:forEach items="${application}" var="applications">

<div style="margin-left:700px;margin-top:60px; position:absolute;">
<text style="font-size:15px">${applications.getFirstName()}</text><text style="margin-left:10px;font-size:15px">${applications.getLastName()}</text><br/>
</div><div style="margin-left:700px;margin-top:75px; position:absolute;">
<text style="color:#808080; font-size:11px">Application ID:&nbsp;${applications.getApplication_id()}</text>
</div>
</c:forEach>


<div id="content-table-inner">
		
			<!--  start table-content  -->
			<div id="table-content">
			
				
		
		 
				<!--  start product-table ..................................................................................... -->
				
				<c:forEach var="data" items="${application}">
				
				<form id="mainform" action="" >
					
				<div id="main" style="margin-left:150px; margin-top:130px">
				<div style="float:left">
				<table border="0" width="255px" cellpadding="0" cellspacing="0" id="product-table">
				<tr>
					
					<th class="table-header-repeat line-left"><a href="">Identification</a>	</th>
				</tr>
														

							
								<tr>
																	
									<c:if test="${data.doc_identity_sss_no!=''}">
									<c:forEach var="dataz" items="${images}">
										<c:if test="${dataz.getType()=='sss'}">
										<td>	
											<b><p>SSS</p></b>
											${data.doc_identity_sss_no}<br/>
											<div style="float:right; margin-right:15px; margin-top:-40px;">
											<a data-toggle="modal" href="#myModal" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
											</div>
										</td>
										</c:if>
										</c:forEach>
									</c:if>
											
								
								</tr>
							
							
							
								<tr>
								
								<c:if test="${data.doc_identity_pagibig_no!=''}">
								<c:forEach var="dataz" items="${images}">					
									<c:if test="${dataz.getType()=='pagibig'}">
									<td>
										<b><p>Pag-Ibig</p></b>
										${data.doc_identity_pagibig_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal2" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>
								</c:if>
								
								
								
								</tr>
								
								<tr>
								
								<c:if test="${data.doc_identity_companyid_no!=''}">
								<c:forEach var="dataz" items="${images}">					
									<c:if test="${dataz.getType()=='companyid'}">
									<td>
										<b><p>Pag-Ibig</p></b>
										${data.doc_identity_companyid_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal3" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>
								</c:if>
								
								
								
								</tr>
								
								<tr>
								<c:if test="${data.doc_identity_tin_no!=''}">	
								<c:forEach var="dataz" items="${images}">	
									<c:if test="${dataz.getType()=='tin'}">
									<td>
										<b><p>Tin</p></b>
										${data.doc_identity_tin_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal4" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>
								</c:if>
								
								</tr>
							
								<tr>
								<c:if test="${data.doc_identity_driverslicense_no!=''}">	
								<c:forEach var="dataz" items="${images}">				
									<c:if test="${dataz.getType()=='driverslicense'}">
									<td>
										<b><p>Drivers License</p></b>
										${data.doc_identity_driverslicense_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal5" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>	
								</c:forEach>	
								</c:if>

								
								</tr>
								
							
								
								<tr>	
								<c:if test="${data.doc_identity_passport_no!=''}">			
								<c:forEach var="dataz" items="${images}">			
									<c:if test="${dataz.getType()=='passport'}">
									<td>
										<b><p>Passport</p></b>
										${data.doc_identity_passport_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal6" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>	
								</c:forEach>
								</c:if>

								
									
								</tr>
												
							
								<tr>
								
								
								<c:if test="${data.doc_identity_others_no!=''}">
								<c:forEach var="dataz" items="${images}">
									<c:if test="${dataz.getType()=='identity_others'}">
									<td>
										<b><p><b>Others</b></p></b>
										${data.doc_identity_others_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal7" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>
								</c:if>
								
								
								</tr>
																
				
				</table>
				</div>
					
				<div style="float:left">	
				<table border="0" width="255px" cellpadding="0" cellspacing="0" id="product-table">
				<tr>
					
					<th class="table-header-repeat line-left"><a href="">Proof Of Billing</a>	</th>
				</tr>
														

							
								<tr>
																	
								<c:if test="${data.doc_address_electricity_no!=''}">
								<c:forEach var="dataz" items="${images}">	
								<c:if test="${dataz.getType()=='electricity'}">
									<td>
										<b><p>Electricity</p></b>
										${data.doc_address_electricity_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal8" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>
								</c:if>

											
								
								</tr>
							
							
							
								<tr>
								
								<c:if test="${data.doc_address_water_no!=''}">
								<c:forEach var="dataz" items="${images}">	
									<c:if test="${dataz.getType()=='water'}">
									<td>
										<b><p>Water</p></b>
										${data.doc_address_water_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal9" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>
								</c:if>
								
								
								
								</tr>
								
								<tr>
								<c:if test="${data.doc_address_isp_no!=''}">	
								<c:forEach var="dataz" items="${images}">	
									<c:if test="${dataz.getType()=='isp'}">
									<td>
										<b><p>ISP</p></b>
										${data.doc_address_isp_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal10" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>
								</c:if>	
								
								
								</tr>
							
								<tr>
								<c:if test="${data.doc_address_cable_no!=''}">	
								<c:forEach var="dataz" items="${images}">	
									<c:if test="${dataz.getType()=='cable'}">
									<td>
										<b><p>Telecom</p></b>
										${data.doc_address_cable_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal11" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>
								</c:if>
								
								
								</tr>
								
							
								
								<tr>	
									<c:if test="${data.doc_address_telecom_no!=''}">		
								<c:forEach var="dataz" items="${images}">	
									<c:if test="${dataz.getType()=='telecom'}">
									<td>
										<b><p>Cable</p></b>
										${data.doc_address_telecom_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal12" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>	
								</c:if>
									
								</tr>
												
							
								<tr>
									<c:if test="${data.doc_address_bankloan_no!=''}">	
									<c:forEach var="dataz" items="${images}">		
										<c:if test="${dataz.getType()=='bankloan'}">
										<td>
											<b><p>Bank Loan</p></b>
											${data.doc_address_bankloan_no}<br/>
											<div style="float:right; margin-right:15px; margin-top:-40px;">
											<a data-toggle="modal" href="#myModal13" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
											</div>
										</td>
										</c:if>	
									</c:forEach>
									</c:if>		
								</tr>
								
								<tr>
								<c:if test="${data.doc_address_others_no!=''}">
								<c:forEach var="dataz" items="${images}">
									<c:if test="${dataz.getType()=='address_others'}">
									<td>
										<b><p>Others</p></b>
										${data.doc_address_others_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal14" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>
								</c:if>
								</tr>
				</table>	
					</div>
					
					<div style="float:left">	
				<table border="0" width="255px" cellpadding="0" cellspacing="0" id="product-table">
				<tr>
					
					<th class="table-header-repeat line-left"><a href="">Proof of Income</a>	</th>
				</tr>
														

							
								<tr>
																	
									<c:if test="${data.doc_income_companyownership_no!=''}">
								<c:forEach var="dataz" items="${images}">		
									<c:if test="${dataz.getType()=='companyowner'}">
									<td>
										<b><p>Company Ownership</p></b>
										${data.doc_income_companyownership_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal15" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>	
								</c:forEach>	
								</c:if>
											
								
								</tr>
							
								
								<tr>
								<c:if test="${data.doc_income_payslip_no!=''}">
								<c:forEach var="dataz" items="${images}">	
									<c:if test="${dataz.getType()=='payslip'}">
									<td>
										<b><p>Payslip</p></b>
										${data.doc_income_payslip_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal16" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>
								</c:if>
								
								</tr>
							
								<tr>
								<c:if test="${data.doc_income_security_no!=''}">	
								<c:forEach var="dataz" items="${images}">	
									<c:if test="${dataz.getType()=='security'}">
									<td>
										<b><p>Security</p></b>
										${data.doc_income_security_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal17" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>	
								</c:if>
								
								</tr>
								
							
								
								<tr>	
								<c:if test="${data.doc_income_bonds_no!=''}">
								<c:forEach var="dataz" items="${images}">				
									<c:if test="${dataz.getType()=='bondno'}">
									<td>
										<b><p>Bonds</p></b>
										${data.doc_income_bonds_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal18" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>	
								</c:if>
								
								
									
								</tr>
												
							
								<tr>
								
								
								<c:if test="${data.doc_income_stockcert_no!=''}">		
								<c:forEach var="dataz" items="${images}">	
									<c:if test="${dataz.getType()=='stockcert'}">
									<td>
										<b><p>Stockcert</p></b>
										${data.doc_income_stockcert_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal19" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>	
								</c:if>	
								
								
								</tr>
								
								<tr>
								
								
								<c:if test="${data.doc_income_autocharge_no!=''}">		
								<c:forEach var="dataz" items="${images}">	
									<c:if test="${dataz.getType()=='autocharge'}">
									<td>
										<b><p>Stockcert</p></b>
										${data.doc_income_autocharge_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal20" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>	
								</c:if>	
								
								
								</tr>
								
								<tr>
								
								<c:if test="${data.doc_income_others_no!=''}">
								<c:forEach var="dataz" items="${images}">
									<c:if test="${dataz.getType()=='income_others'}">
									<td>
										<b><p>Others</p></b>
										${data.doc_income_others_no}<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal21" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>
								</c:if>
								
								</tr>													
				</table>	
					</div>
					
					<div style="float:left">	
				<table border="0" width="255px" cellpadding="0" cellspacing="0" id="product-table">
				<tr>
					
					<th class="table-header-repeat line-left"><a href="">Terms of Use</a>	</th>
				</tr>
														

							
								<tr>
																	
									<c:if test="${data.doc_terms_sig1!=''}">
								<c:forEach var="dataz" items="${images}">	
								<c:if test="${dataz.getType()=='sig1'}">
									<td>
										<b><p>Signature 1</p></b>
										<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal22" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>
								</c:if>
											
								
								</tr>
							
							
							
								<tr>
								
								<c:if test="${data.doc_terms_sig2!=''}">
								<c:forEach var="dataz" items="${images}">	
									<c:if test="${dataz.getType()=='sig2'}">
									<td>
										<b><p>Signature 2</p></b>
										<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal23" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>
								</c:if>
								
								
								
								</tr>
								
								<tr>
								<c:if test="${data.doc_terms_sig3!=''}">	
								<c:forEach var="dataz" items="${images}">	
									<c:if test="${dataz.getType()=='sig3'}">
									<td>
										<b><p>Signature 3</p></b>
										<br/>
										<div style="float:right; margin-right:15px; margin-top:-40px;">
										<a data-toggle="modal" href="#myModal24" data-lightbox="example-1"><img src="resources/uploaded/${dataz.getFilename()}" class="bilog" width="50px" height="50px" alt="" /></a>
										</div>
									</td>
									</c:if>
								</c:forEach>
								</c:if>
								
								</tr>
							
								
						</table>	
					</div>
					
					</div>	
					
					

				</form>
				</c:forEach>

				
				
				
			</div>
</div>

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

		
	</div><!-- End of Container -->
	
	<div class="footer">
		<p>&copy; 2013 Circles</p>
	</div>

	



</body>
</html>
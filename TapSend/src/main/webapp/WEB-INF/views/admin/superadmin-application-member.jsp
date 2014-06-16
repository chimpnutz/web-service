<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html><head><style id="flot-default-styles">.flot-tick-label {font-size:smaller;color:#545454;}</style>
    <title>Admin - Home</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- bootstrap -->
    <link href="resources/admin/css/bootstrap.css" rel="stylesheet">
    <link href="resources/admin/css/bootstrap-overrides.css" type="text/css" rel="stylesheet">

    <!-- libraries -->
    <link href="resources/admin/css/jquery-ui-1.css" rel="stylesheet" type="text/css">
    <link href="resources/admin/css/font-awesome.css" type="text/css" rel="stylesheet">

    <link href="resources/admin/css/font-awesome.min.css" type="text/css" rel="stylesheet">


    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="resources/admin/css/layout.css">
    <link rel="stylesheet" type="text/css" href="resources/admin/css/elements.css">
    <link rel="stylesheet" type="text/css" href="resources/admin/css/icons.css">

    <!-- this page specific styles -->
    <link rel="stylesheet" href="resources/admin/css/index.css" type="text/css" media="screen">

    <!-- open sans font -->
    <link href="resources/admin/css/css_002.css" rel="stylesheet" type="text/css">

    <!-- lato font -->
    <link href="resources/admin/css/css.css" rel="stylesheet" type="text/css">

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<style type="text/css">
    .mdfield{
        margin-top: 100px;
      


    }
.addcolorblue{
 color: #3fabff;
}


</style>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js">
</script>
<script>
$(document).ready(function(){
  $("#toggle1").click(function(){
    $(".toggle1").toggle();
  });
});
</script>

<script>
$(document).ready(function(){
  $("#toggle2").click(function(){
    $(".toggle2").toggle();
  });
});
</script>

<script>
$(document).ready(function(){
  $("#toggle3").click(function(){
    $(".toggle3").toggle();
  });
});
</script>

</head>
<body>
    <!-- navbar -->
    <header class="navbar navbar-inverse" role="banner">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" id="menu-toggler">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand  hidden-xs" href="#" style="padding:0px 20px !important;margin-top:15px;">
               Super Admin
            </a>
        </div>
        <!--hidden-xs-->
        <ul class="nav navbar-nav pull-right hidden-md hidden-xs hidden-sm">
          



            <!--hidden-xs hidden-sm-->
            <li class="settings hidden-md hidden-xs hidden-sm">
                <a href="logout.html" role="button">
                    <i class="fa fa-share"></i>
                </a>
            </li>
        </ul>
    </header>
    <!-- end navbar -->

    <!-- sidebar -->
    <div id="sidebar-nav">
        <ul id="dashboard-menu">
                    
          
 <li>
                <a  href="superadmin-home.html">
                    <i class="fa fa-home"></i>
                    <span>Home</span>
                   
                </a>
              
            </li>  

        
          
            
           


         

            
            <li class="hidden-lg vissible-xs">
                <a href="logout.html">
                    <i class="fa fa-share"></i>
                    <span>Log Out</span>
                </a>
            </li>
            
        </ul>
    </div>
    <!-- end sidebar -->


    <!-- main container -->
    <div class="content">

    

        <div id="pad-wrapper">

           
          


		<c:forEach var="user" items="${user }">
							
			            <c:if test="${user.type =='3'}">
							<c:forEach var="datas" items="${emp }">
								 <h4 class="panel-title pull-right"><strong>Employer:</strong> ${datas.firstName } ${datas.lastName }</h4>
							</c:forEach>
						 </c:if>					
					 </c:forEach>
<div class="container toggle2 toggle3">
    <div class="row">
        <div class="col-xs-12">
            <div class="invoice-title">
                  <a href="#" id="toggle1"><img  src="resources/admin/img/sss2.jpg" width="200px;" height="120px;"></a>
                  			
            </div>
            <hr>
      
        </div>
    </div>

<c:forEach items="${details}" var="data">   
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
				
	<c:forEach var="user" items="${user }">
						
		<c:if test="${user.type=='3'}">   
                <div class="panel-heading">
                    <h3 class="panel-title"><strong>MEMBERSHIP FORM E-1</strong></h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                    <td><strong  class="addcolorblue">SS NUMBER</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">SURNAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">GIVEN NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">MIDDLE NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">ADDRESS</strong></td>
                                    <td class="text-right"><strong  class="addcolorblue">POSTAL CODE</strong></td>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                    <td>${data.sss}</td>
                                    <td class="text-center">${data.lastName}</td>
                                    <td class="text-center">${data.firstName}</td>
                                       <td class="text-center">${data.middleName}</td>
                                       <td class="text-center">${data.addRegion}, ${data.addBrgy}, ${data.addCity} ${data.add_line1} ${data.add_line2}</td>
                                    <td class="text-right">${data.zipCode}</td>
                                </tr>

   </tbody>
                                     <thead>
                                <tr>
                                    <td><strong class="addcolorblue">SEX</strong></td>
                                    <td class="text-center"><strong class="addcolorblue">DATE OF BIRTH</strong></td>
                                    <td class="text-center"><strong class="addcolorblue">CIVIL STATUS</strong></td>
                                   <td class="text-center"><strong> </strong></td>
                                    <td class="text-center"><strong> </strong></td>
                                     <td class="text-center"><strong> </strong></td>
                                </tr>
                            </thead>

                                 <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                    <td>
									<c:if test="${data.gender=='0'}">
									Male
									</c:if>
									<c:if test="${data.gender=='1'}">
									Female
									</c:if>
								  </td>
                                    <td class="text-center">
										<jsp:useBean id="dateValue" class="java.util.Date" />
										<jsp:setProperty name="dateValue" property="time" value="${data.birthday }" />
										<fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy" />
									</td>
                                    <td class="text-center">${data.civilstatus}</td>
                                       <td class="text-center"></td>
                                       <td class="text-center"></td>
                                       <td class="text-center"></td>
                                </tr>

   </tbody>
                         







                        </table>




<c:if test="${data.type!= '4' && data.type != '1' && data.type != '2'}">

					<div class="panel-heading">
                  			  <h3 class="panel-title"><small>BENEFICIARIES</small></h3>
               			 </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                            
                           
                                <tr>       
                                 <c:forEach var="test" items="${ben}">
                                 	<c:if test="${not empty test.relation}">                      
                                    <td><strong class="addcolorblue">${fn:toUpperCase(test.relation)}</strong></td>
                                    </c:if>
                                  </c:forEach>
                                   <td class="text-center"><strong  class="addcolorblue"></strong></td>
                                </tr>
                                
                                
                            </thead>
                            <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
									 <c:forEach var="test" items="${ben}">
									 <c:if test="${not empty test.relation}">                        
                                    <td>${test.firstName} &nbsp; ${test.lastName}</td> 
                                    </c:if>                                
                                  </c:forEach>
								    
                               	<td class="text-center"><strong  class="addcolorblue"></strong></td>
                                </tr>

                            </tbody>

                        </table>


		
					



                    </div>
                </div>
       
               
                </c:if>
            </div>
        </div>
        </c:if>
        <c:if test="${user.type=='1'  ||  user.type =='2'}">
        
        	 <div class="panel-heading">
                    <h3 class="panel-title"><strong>Membership Form R-1</strong></h3>
                </div>
                <div class="panel-body">
                 <div class="panel-heading">
                    <h3 class="panel-title"><small>Employer Main Office Data</small></h3>
                </div>
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                    <td><strong  class="addcolorblue">NAME OF OWNER</strong></td>
                                    <c:if test="${user.type =='1'}">
                                    <td class="text-center"><strong  class="addcolorblue">NAME OF BUSINESS</strong></td>
                                    </c:if>
                                    <td class="text-center"><strong  class="addcolorblue">COMPANY ADDRESS</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">TIN</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">TELEPHONE NUMBER</strong></td>
                                         <td class="text-right"><strong  class="addcolorblue">MOBILE NUMBER</strong></td>
                                        
                                </tr>
                            </thead>
                            <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                        <td>${data.firstName} ${data.lastName}</td>	                                
		                                <c:if test="${user.type =='1'}">
		                                    <td class="text-center">${data.company_name }</td>
		                                </c:if>	                                
		                                 <c:if test="${user.type =='1'}">
		                                    <td class="text-center">${data.company_address }</td>
		                                </c:if>
		                                
		                                <c:if test="${user.type =='2' }">
		                                    <td class="text-center">${data.addRegion}, ${data.addBrgy}, ${data.addCity} ${data.add_line1} ${data.add_line2}</td>
		                                </c:if>                                  
	                                    <td class="text-center">${data.tin }</td>
	                                    <td class="text-center">${data.ofc_number}</td>
                              
                                    <td class="text-right">${data.mobile }</td>
                                </tr>

  					 </tbody>


   						<thead>
                                <tr>
                                    <td><strong  class="addcolorblue">EMAIL ADDRESS</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">SSS NUMBER</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">   </strong></td>
                                    <td class="text-center"><strong  class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong  class="addcolorblue"> </strong></td>
                                    
                                             <td class="text-right"><strong  class="addcolorblue"> </strong></td>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                    <td>${data.email}</td>
                                    <td class="text-center">${data.sss }</td>
                                    <td class="text-center"></td>
                                    <td class="text-center"></td>
                                    <td class="text-center"></td>
                                     
                                    <td class="text-right"></td>
                                </tr>

  						 </tbody>
                                    

                        </table>

            </div>
        </div>
        
        </c:if>
        </c:forEach>
    </div>
  	
   
    </c:forEach>
    </div>    
    
    </div>
    


</div>


























  <c:forEach items="${details}" var="data">     

<div class="container toggle1 toggle3">
    <div class="row">
        <div class="col-xs-12">
            <div class="invoice-title">
           
                   <a href="#" id="toggle2"><img src="resources/admin/img/philhealth.jpg" width="200px;" height="90px;"></a>
		
            </div>
             
            <hr>
      				
        </div>
    </div>
   
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><strong>Membership Form</strong></h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                    <td><strong  class="addcolorblue">PIN</strong></td>                                   
                                  <!-- <td class="text-center"><strong  class="addcolorblue">PURPOSE</strong></td> --> 
                                    <td class="text-center"><strong  class="addcolorblue">LAST NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">FIRST NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">MIDDLE NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">EXTENSION NAME</strong></td>
                                    <td class="text-right"></td>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                    <td>${data.philhealth }</td>
                                   <!-- <td class="text-center">${data.birthday }</td>-->
                                    <td class="text-center">${data.lastName }</td>
                                    <td class="text-center">${data.firstName }</td>
                                    <td class="text-center">${data.middleName }</td>
                                    <c:choose>
                                 		<c:when test="${not empty data.suffix}">
                                       	<td class="text-center">${data.suffix }</td>
                                       </c:when>
                                		<c:otherwise>
                                			<td class="text-center">None</td>
                                		</c:otherwise>
                              		   </c:choose>
                                   <td class="text-right"></td>
                                </tr>

   </tbody>
                                     <thead>
                                <tr>
                                    <td><strong  class="addcolorblue">DATE OF BIRTH</strong></td>
                                  <!--  <td class="text-center"><strong  class="addcolorblue">PLACE OF BIRTH</strong></td> -->
                                    <td class="text-center"><strong  class="addcolorblue">SEX</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">CIVIL STATUS</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">TIN</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue"></strong></td>
                                    <td class="text-right"></td>
                                </tr>
                            </thead>

                                 <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                    <td><jsp:useBean id="dateValue2" class="java.util.Date" />
										<jsp:setProperty name="dateValue2" property="time" value="${data.birthday }" />
										<fmt:formatDate value="${dateValue2}" pattern="MM/dd/yyyy" /></td>
                                   <!--  <td class="text-center">Nlex hospital</td>  -->
                                    <td class="text-center">
									<c:if test="${data.gender=='0'}">
									  Male
									  </c:if>
									  <c:if test="${data.gender=='1'}">
									  Female
									  </c:if>
									</td>
                                       <td class="text-center">${data.civilstatus}</td>                                       
                                       <td class="text-center">${data.tin}</td>
                                       <td class="text-center"></td>
                                       <td class="text-right"></td>
                                </tr>

                                </tbody>



                                <thead>
                                <tr>
                                    <td><strong  class="addcolorblue">ADDRESS</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">ZIP CODE</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">LANDLINE NUMBER</strong></td>
                                   <td class="text-center"><strong  class="addcolorblue">MOBILE NUMBER</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">EMAIL ADDRESS</strong></td>
                                     <td class="text-right"><strong></strong></td>
                                </tr>
                            </thead>

                                 <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                    <td>${data.addRegion}, ${data.addBrgy}, ${data.addCity} ${data.add_line1} ${data.add_line2}</td>
                                    <td class="text-center">${data.zipCode}</td>
                                    <td class="text-center">${data.landline}</td>
                                       <td class="text-center">${data.mobile}</td>
                                       <c:choose>
                                 		<c:when test="${not empty data.email}">
                                       	<td class="text-center">${data.email}</td>
                                       </c:when>
                                		<c:otherwise>
                                			<td class="text-center">None</td>
                                		</c:otherwise>
                              		   </c:choose>
                                       <td class="text-right"></td>
                                </tr>

                                </tbody>
                         
                         







                        </table>




				<c:forEach var="depend" items="${user}">
				   <c:if test="${depend.type=='3'}">
				    <c:forEach var="test" items="${ben}">
                                	<c:if test="${test.relation=='Spouse'}">
                          <div class="panel-heading" style="margin-top:50px;">
                   			 <h3 class="panel-title"><small>DECLARATION OF DEPENDENTS: Legal Spouse</small></h3>
               			  </div>
               	  					</c:if>
               	  	</c:forEach>
              <c:forEach var="test" items="${ben}">
                <c:if test="${test.relation=='Spouse'}">
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                          
                                    <td><strong  class="addcolorblue">LAST NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">FIRST NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">MIDDLE NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">EXTENSION NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong  class="addcolorblue"></strong></td>
                                    <td class="text-right"><strong  class="addcolorblue"></strong></td>
                           
                                </tr>
                            </thead>
                            <tbody>
                                <!--     -->
                                <tr>
                              
                                           <td>${test.lastName }</td>
		                                   <td class="text-center">${test.firstName }</td>
		                                   <td class="text-center">${test.middleName }</td>
	                                       <c:choose>
                                 		<c:when test="${not empty test.suffix}">
                                       	<td class="text-center">${test.suffix }</td>
                                       </c:when>
                                		<c:otherwise>
                                			<td class="text-center">None</td>
                                		</c:otherwise>
                              		   </c:choose> 
                              		                 
                                    

									
                                </tr>

                            </tbody>
                                    
                    







                        </table>
	
     </div>
   
                </div>
                 </c:if>            		
                                  </c:forEach>
                </c:if>
			</c:forEach>
			<c:forEach var="depend" items="${user}">
				   <c:if test="${depend.type=='3'}">
							<c:forEach var="test" items="${ben}">
                                	<c:if test="${test.relation=='Children'}">  	
	                                 <div class="panel-heading">
				                    <h3 class="panel-title"><small>DECLARATION OF DEPENDENTS: Legal Children</small></h3>
				                	</div>
			                		</c:if>
							</c:forEach>	
			<c:forEach var="test" items="${ben}">
                    <c:if test="${test.relation=='Children'}">		              
                        <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                	
                                    <td><strong  class="addcolorblue">LAST NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">FIRST NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">MIDDLE NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">EXTENSION NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong  class="addcolorblue"></strong></td>
                                    <td class="text-right"><strong  class="addcolorblue"></strong></td>
                                   
                                </tr>
                            </thead>
                            <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                  
                                           <td>${test.lastName }</td>
		                                   <td class="text-center">${test.firstName }</td>
		                                   <td class="text-center">${test.middleName }</td>
	                                       <c:choose>
                                 		<c:when test="${not empty test.suffix}">
                                       	<td class="text-center">${test.suffix }</td>
                                       </c:when>
                                		<c:otherwise>
                                			<td class="text-center">None</td>
                                		</c:otherwise>
                              		   </c:choose> 
                              		          
                                </tr>

                            </tbody>




                        </table>

                         </div>
   
                            </div>
                             </c:if>            		
                                  </c:forEach> 
</c:if>
</c:forEach>


				<c:forEach var="depend" items="${user}">
				   <c:if test="${depend.type=='3'}">
				   				<c:forEach var="test" items="${ben}">
                                	<c:if test="${test.relation=='Father'}">
                                	
	                                 <div class="panel-heading">
	                    				<h3 class="panel-title"><small>DECLARATION OF DEPENDENTS: Father Details</small></h3>
	                				</div>
		               			</c:if>
		               			</c:forEach>
		              <c:forEach var="test" items="${ben}">
                                	<c:if test="${test.relation=='Father'}">
                        <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                               
                                	
                                    <td><strong  class="addcolorblue">FATHERS LAST NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">FATHERS FIRST NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">FATHERS MIDDLE NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">EXTENSION NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong  class="addcolorblue"></strong></td>
                                    <td class="text-right"><strong  class="addcolorblue"></strong></td>
                                   
                                                
                                </tr>
                            </thead>
                            <tbody>
                           
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                    
                                           <td>${test.lastName }</td>
		                                   <td class="text-center">${test.firstName }</td>
		                                   <td class="text-center">${test.middleName }</td>
	                                       <c:choose>
                                 		<c:when test="${not empty test.suffix}">
                                       	<td class="text-center">${test.suffix }</td>
                                       </c:when>
                                		<c:otherwise>
                                			<td class="text-center">None</td>
                                		</c:otherwise>
                              		   </c:choose> 
                              		
                              		     
                                </tr>

                            </tbody>


                        </table>

                         </div>
   
                            </div>
                           </c:if>                             		          		
                                  </c:forEach>   
                                  
                                  
                                   
                          <c:forEach var="test" items="${ben}">
                                	<c:if test="${test.relation=='Mother'}">  
                             <div class="panel-heading">
	                    				<h3 class="panel-title"><small>DECLARATION OF DEPENDENTS: Mother Details</small></h3>
	                				</div>
	                		</c:if>
	                		</c:forEach>	
	               <c:forEach var="test" items="${ben}">
                                	<c:if test="${test.relation=='Mother'}">	
	                				 <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
   								
                                <tr>
                                    <td><strong  class="addcolorblue">MOTHERS LAST NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">MOTHERS FIRST NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">MOTHERS MIDDLE NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">EXTENSION NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong  class="addcolorblue"></strong></td>
                                    <td class="text-right"><strong  class="addcolorblue"></strong></td>
                                </tr>
                               
                            </thead>
                            <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                  
                                           <td>${test.lastName }</td>
		                                   <td class="text-center">${test.firstName }</td>
		                                   <td class="text-center">${test.middleName }</td>
	                                       <c:choose>
                                 		<c:when test="${not empty test.suffix}">
                                       	<td class="text-center">${test.suffix }</td>
                                       </c:when>
                                		<c:otherwise>
                                			<td class="text-center">None</td>
                                		</c:otherwise>
                              		   </c:choose> 
                              		      
                                </tr>

                            </tbody>

                        </table>

                         </div>
   
                            </div>
                             </c:if>            		
                                  </c:forEach>   
</c:if>
</c:forEach>




                             <!--  <div class="panel-heading">
                    <h3 class="panel-title"><small>MEMBERSHIP CATEGORY </small></h3>
                </div>
                        <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                      <td  class="text-center"><strong  class="addcolorblue">FORMAL ECONOMY</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">INFORMAL ECONOMY</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">INDIGENT</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">SPONSORED</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">LIFETIME MEMBER</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">DATE OF RETIREMENT</strong></td>
                                </tr>
                            </thead>
                            <tbody>
                               
                                <tr>
                                    <td class="text-center">LA</td>
                                    <td class="text-center">LA</td>
                                    <td class="text-center">LA</td>
                                       <td class="text-center">LA</td>
                                       <td class="text-center">LA</td>
                                        <td class="text-center">LA</td>
                                 
                                </tr>

                            </tbody>




                        </table>

                         </div>
   
                            </div>

-->

    
            </div>
            
        </div>
        
    </div>
    
</div>

</div>

</div>


</c:forEach>






 <c:forEach items="${details}" var="data">   

<div class="container toggle1 toggle2">
    <div class="row">
        <div class="col-xs-12">
            <div class="invoice-title">
                  <a href="#" id="toggle3"><img src="resources/admin/img/pag-ibig.gif" width="120px;" height="120px;"></a>

            </div>
            <hr>
      
        </div>
    </div>
    
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><strong>MEMBERSHIP CATEGORY</strong></h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                    <td width="160px;"><strong  class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">LAST NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">FIRST NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">MIDDLE NAME</strong></td>
                                    <td class="text-center"><strong  class="addcolorblue">NAME EXTENSION</strong></td>
                                  
                                </tr>
                            </thead>
                            <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                    <td>MEMBER:</td>
                                    <td class="text-center">${data.lastName }</td>
                                    <td class="text-center">${data.firstName }</td>                                     
                                       <td class="text-center">${data.middleName }</td>
                                        <c:choose>
                                 		<c:when test="${not empty data.suffix}">
                                       	<td class="text-center">${data.suffix }</td>
                                       </c:when>
                                		<c:otherwise>
                                			<td class="text-center">None</td>
                                		</c:otherwise>
                              		   </c:choose>
                                    <td class="text-right"></td>
                                </tr>
								<c:forEach var="depend" items="${user}">
				  					 <c:if test="${depend.type=='3'}">
                                 <tr>
                                    <td>FATHER:</td>
                                    <c:forEach var="test" items="${ben}">
                 					<c:choose>
                                 		<c:when test="${test.relation=='Father'}">
                                    	<td class="text-center">${test.lastName }</td>
                                    	<td class="text-center">${test.firstName }</td>
                                        <td class="text-center">${test.middleName }</td>
                                        <c:choose>
                                 		<c:when test="${not empty test.suffix}">
                                       	<td class="text-center">${test.suffix }</td>
                                       </c:when>
                                		<c:otherwise>
                                			<td class="text-center">None</td>
                                		</c:otherwise>
                              		   </c:choose>
                                    <td class="text-right"></td>
                                    	</c:when>
                                    	<c:otherwise>
                                			<td class="text-center">None</td>
		                                    <td class="text-center">None</td>
                                    	    <td class="text-center">None</td>
                                            <td class="text-center">None</td>
                                            <td class="text-center"></td>
                                            <td class="text-right"></td>
                                		</c:otherwise>
                              		   </c:choose>
                                  </c:forEach>    
                                    
                                </tr>

                                  <tr>
                                    <td>MOTHER (MAIDEN NAME):</td>
                                    <c:forEach var="test" items="${ben}">
                 					<c:choose>
                                 		<c:when test="${test.relation=='Mother'}">
                                    	<td class="text-center">${test.lastName }</td>
                                    	<td class="text-center">${test.firstName }</td>
                                        <td class="text-center">${test.middleName }</td>
                                        <c:choose>
                                 		<c:when test="${not empty test.suffix}">
                                       	<td class="text-center">${test.suffix }</td>
                                       </c:when>
                                		<c:otherwise>
                                			<td class="text-center">None</td>
                                		</c:otherwise>
                              		   </c:choose>
                                    <td class="text-right"></td>
                                    	</c:when>
                                    	<c:otherwise>
                                			<td class="text-center">None</td>
		                                    <td class="text-center">None</td>
                                    	<td class="text-center">None</td>
                                        <td class="text-center">None</td>
                                        <td class="text-center"></td>
                                    <td class="text-right"></td>
                                		</c:otherwise>
                              		   </c:choose>
                                  </c:forEach> 
                                </tr>


                                  <tr>
                                    <td>SPOUSE(IF MARRIED):</td>
                                    <c:forEach var="test" items="${ben}">
                 					<c:choose>
                                 		<c:when test="${test.relation=='Spouse'}">
                                    	<td class="text-center">${test.lastName }</td>
                                    	<td class="text-center">${test.firstName }</td>
                                        <td class="text-center">${test.middleName }</td>
                                        <c:choose>
                                 		<c:when test="${not empty test.suffix}">
                                       	<td class="text-center">${test.suffix }</td>
                                       </c:when>
                                		<c:otherwise>
                                			<td class="text-center">None</td>
                                		</c:otherwise>
                              		   </c:choose>
                                    <td class="text-right"></td>
                                    	</c:when>
                                    	<c:otherwise>
                                			<td class="text-center">None</td>
		                                    <td class="text-center">None</td>
                                    	    <td class="text-center">None</td>
                                            <td class="text-center">None</td>
                                            <td class="text-center"></td>
                                            <td class="text-right"></td>
                                		</c:otherwise>
                              		   </c:choose>
                                  </c:forEach> 
                                </tr>
							</c:if>
							</c:forEach>





   </tbody>
                                     <thead>
                                <tr>
                                    <td><strong class="addcolorblue">DATE OF BIRTH</strong></td>
                                    <td class="text-center"><strong class="addcolorblue">MARITAL STATUS</strong></td>
                                   <td class="text-center"><strong class="addcolorblue">CITIZENSHIP</strong></td>
                                    <td class="text-center"><strong class="addcolorblue">SEX</strong></td>
                                     <td class="text-center"><strong class="addcolorblue"></strong></td>
                                     <td class="text-center"><strong class="addcolorblue"></strong></td>
                                </tr>
                            </thead>

                                 <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                    <td><jsp:useBean id="dateValue3" class="java.util.Date" />
										<jsp:setProperty name="dateValue3" property="time" value="${data.birthday }" />
										<fmt:formatDate value="${dateValue3}" pattern="MM/dd/yyyy" /></td>
                                    <td class="text-center">${data.civilstatus }</td>                                   
                                       <td class="text-center">Filipino</td>
                                       <td class="text-center">
									   <c:if test="${data.gender=='0'}">
									   Male
									   </c:if>
									   <c:if test="${data.gender=='1'}">
									   Female
									   </c:if>
									   </td>
									   <td class="text-center"></td>
                                       <td class="text-center"></td>
                                </tr>

   </tbody>
                         



                                <thead>
                                <tr>
                                    <td><strong class="addcolorblue">TIN</strong></td>
                                    <td class="text-center"><strong class="addcolorblue">SSS/GSIS NUMBER</strong></td>
                                   <td class="text-center"><strong class="addcolorblue">EMPLOYEE NUMBER</strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                     <td class="text-center"><strong class="addcolorblue"></strong></td>
                                      <td class="text-center"><strong class="addcolorblue"></strong></td>
                                </tr>
                            </thead>

                                 <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                    <td>${data.tin }</td>
                                    <td class="text-center">${data.sss }</td>
                                    <td class="text-center">${data.pi_employee_number}</td>
                                       <td class="text-center"></td>
                                       <td class="text-center"></td>
                                       <td class="text-center"></td>
                                </tr>

   </tbody>
                         







                        </table>





                          <div class="panel-heading">
                    <h3 class="panel-title"  style="margin-top:50px;"><small>PRESENT HOME ADDRESS</small></h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                    <td><strong class="addcolorblue">ADDRESS</strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                    <td class="text-right"><strong class="addcolorblue"></strong></td>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                    <td>${data.addRegion}, ${data.addBrgy}, ${data.addCity} ${data.add_line1} ${data.add_line2}</td>
                                    <td class="text-center"></td>
                                    <td class="text-center"></td>
                                       <td class="text-center"></td>
                                       <td class="text-center">  </td>
                                    <td class="text-right"></td>
                                </tr>

                            </tbody>
                        </table>

                    </div>
                </div>





                          <div class="panel-heading">
                    <h3 class="panel-title"><small>CONTACT DETAILS</small></h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                    <td><strong class="addcolorblue">CELL PHONE</strong></td>
                                    <td class="text-center"><strong class="addcolorblue">BUSINESS (DIRECT LINE)</strong></td>
                                    <td class="text-center"><strong class="addcolorblue">EMAIL ADDRESS</strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                    <td class="text-right"><strong class="addcolorblue"></strong></td>
                              
                                </tr>
                            </thead>
                            <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                    <td>${data.mobile }</td>
                                    <td class="text-center">${data.landline }</td>                                   
                                       <c:choose>
                                 		<c:when test="${not empty data.email}">
                                       	<td class="text-center">${data.email}</td>
                                       </c:when>
                                		<c:otherwise>
                                			<td class="text-center">None</td>
                                		</c:otherwise>
                              		   </c:choose>
                              		    <td class="text-center"></td>
                                       <td class="text-right">  </td>
                                   
                                </tr>

                            </tbody>
                        </table>

                    </div>
                </div>





                  <div class="panel-heading">
                    <h3 class="panel-title"  ><small>PERMANENT HOME ADDRESS</small></h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                    <td><strong class="addcolorblue">ADDRESS</strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                    <td class="text-right"><strong class="addcolorblue"></strong></td>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                    <td>${data.addRegion}, ${data.addBrgy}, ${data.addCity} ${data.add_line1} ${data.add_line2}</td>
                                    <td class="text-center"></td>
                                    <td class="text-center"></td>
                                       <td class="text-center"></td>
                                       <td class="text-center">  </td>
                                    <td class="text-right"></td>
                                </tr>

                            </tbody>
                        </table>

                    </div>
                </div>






					<c:forEach var="depend" items="${user}">
				   <c:if test="${depend.type=='3' || depend.type=='1' || depend.type=='2'}">
                          <div class="panel-heading">
                    <h3 class="panel-title"><small>EMPLOYMENT/BUSINESS DETAILS</small></h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                    <td><strong class="addcolorblue">EMPLOYER/BUSINESS NAME</strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong class="addcolorblue"> </strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                    <td class="text-right"><strong class="addcolorblue"></strong></td>
                              
                                </tr>
                            </thead>
                            <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                	<c:forEach var="user" items="${user }">
										
							            <c:if test="${user.type =='3'}">
											<c:forEach var="datas" items="${emp }">
												 <td>${datas.firstName} ${datas.lastName}</td>
											</c:forEach>
										 </c:if>
										 
										 <c:if test="${user.type =='2'}">
										  		<c:forEach var="datas" items="${emp }">
												 <td>${datas.firstName} ${datas.lastName}</td>
											</c:forEach>
										  </c:if>
										 
										  <c:if test="${user.type =='1'}">
										  		<td>${data.company_name}</td>
										  </c:if>
									 </c:forEach>
                                    
                                    <td class="text-center"></td>
                                    <td class="text-center"></td>
                                       <td class="text-center"></td>
                                       <td class="text-center"></td>
                                       <td class="text-center"></td>
                                       <td class="text-right"></td>
                                   
                                </tr>

                            </tbody>
                        </table>

                    </div>
                </div>






				
                  <div class="panel-heading">
                    <h3 class="panel-title"><small>EMPLOYER/BUSINESS ADDRESS</small></h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                    <td><strong class="addcolorblue">COMPANY ADDRESS</strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                    <td class="text-center"><strong class="addcolorblue"></strong></td>
                                    <td class="text-right"><strong class="addcolorblue"></strong></td>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                
                                <tr><c:forEach var="user" items="${user }">
										
							            <c:if test="${user.type =='1'}">
		                                    <td>${data.company_address }</td>
		                                </c:if>
		                                
		                                <c:if test="${user.type =='2' || user.type =='3'}">
		                                    <td>${data.addRegion}, ${data.addBrgy}, ${data.addCity} ${data.add_line1} ${data.add_line2}</td>
		                                </c:if>
		                            </c:forEach>
		                                    <td class="text-center"></td>
		                                    <td class="text-center"></td>
		                                       <td class="text-center"></td>
		                                       <td class="text-center">  </td>
		                                    <td class="text-right"></td>
                                </tr>

                            </tbody>
                        </table>

                    </div>
                </div>

</c:if>
</c:forEach>

</div>




               <!-- <div class="panel-heading">
                    <h3 class="panel-title"><small>PREVIOUS EMPLOYMENT FROM DATE OF Pag-IBIG FUND MEMBERSHIP</small></h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                    <td><strong class="addcolorblue">EMPLOYER/BUSINESS NAME</strong></td>
                                    <td class="text-center"><strong class="addcolorblue">FROM</strong></td>
                                    <td class="text-center"><strong class="addcolorblue">TO</strong></td>
                               
                                    <td class="text-right"><strong class="addcolorblue">EMPLOYER/BUSINESS ADDRESS </strong></td>
                                </tr>
                            </thead>
                            <tbody>
                                
                                <tr>
                                    <td>WAWAWAW</td>
                                    <td class="text-center"></td>
                                    <td class="text-center"></td>
                                      
                                    <td class="text-right"></td>
                                </tr>

                            </tbody>
                        </table>

                    </div>
                </div>






                  <div class="panel-heading">
                    <h3 class="panel-title"><small>HEIRS</small></h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                    <td><strong class="addcolorblue">LAST NAME</strong></td>
                                    <td class="text-center"><strong class="addcolorblue">FIRST NAME</strong></td>
                                    <td class="text-center"><strong class="addcolorblue">NAME EXTENSION</strong></td>
                               
                                    <td class="text-center"><strong class="addcolorblue">MIDDLE NAME </strong></td>
                                    <td class="text-center"><strong class="addcolorblue">RELATIONSHIP</strong></td>
                                    <td class="text-right"><strong class="addcolorblue">DATE OF BIRTH</strong></td>
                                </tr>
                            </thead>
                            <tbody>
                             
                                <tr>
                                    <td>WAWAWAW</td>
                                    <td class="text-center"></td>
                                    <td class="text-center"></td>
                                      <td class="text-center"></td>
                                      <td class="text-center"></td>
                                    <td class="text-right"></td>
                                </tr>

                            </tbody>
                        </table>

                    </div>
                </div>


 -->   







            </div>
        </div>
    </div>
</div>
</div>



</c:forEach>
























    
            
            
       </div>
       
       </div>         












    <!-- scripts -->
    <script src="resources/admin/js/jquery-latest.js"></script>
    <script src="resources/admin/js/bootstrap.js"></script>
    <script src="resources/admin/js/jquery-ui-1.js"></script>
    <!-- knob -->
    <script src="resources/admin/js/jquery.js"></script>
    <!-- flot charts -->
    <script src="resources/admin/js/jquery_004.js"></script>
    <script src="resources/admin/js/jquery_003.js"></script>
    <script src="resources/admin/js/jquery_002.js"></script>
    <script src="resources/admin/js/theme.js"></script>


</body></html>
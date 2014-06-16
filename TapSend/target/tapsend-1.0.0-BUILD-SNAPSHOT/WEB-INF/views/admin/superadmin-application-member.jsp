<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html><head><style id="flot-default-styles">.flot-tick-label {font-size:smaller;color:#545454;}</style>
	<title>TapSend</title>
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
            <a class="navbar-brand  hidden-xs" href="superadmin-home.html" style="padding:0px 20px !important;margin-top:15px;">
               <p>Super Admin</p>
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

          <!--    <li>
                <a class="dropdown-toggle" href="#">
                    <i class="fa fa-group"></i>
                    <span>Users</span>
                    <i class="fa fa-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="superadmin-userlist.html">User list</a></li>
                    <li><a href="superadmin-adduser.html">New user form</a></li>
                   
                </ul>
            </li> -->
           
            
            
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

           
          

                <div class="col-md-4"></div>
                <div class="col-md-4">
                   
                  
                    <div class="row ctrls">
                        <!-- Split button -->
                     <!--   <div class="btn-group">
                        <a href="superadmin-home.html"> <button type="button" class="btn btn-default active">Recent</button></a>
                        </div>


                        <div class="btn-group">
                          <a href="superadmin-return.html"><button type="button" class="btn btn-default">Return</button></a>
                          
                          
                        </div>
                        <div class="btn-group">
                         <a href="superadmin-encoded.html"> <button type="button" class="btn btn-default ">Encoded</button></a>
                        </div>        -->            
              
                
                       </div>
                         </div>

                        <div class="col-md-4"></div>
                   <!-- end -->


                <!--start-->
                 <!-- SSS -->
       
		<c:forEach items="${details}" var="data">   			                   
				
            <div class="table-products space1">
            
                <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small>MEMBERSHIP FORM E-1</small></h4>
                    </div>
                    
                </div>



                <div id="no-more-tables">
            <table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                                <th class="col-md-2">
                                   <span class="line"></span>SS NUMBER

                                   
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>SURNAME 
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>GIVEN NAME
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>MIDDLE NAME 
                                </th>

                                 <th class="col-md-3">
                                    <span class="line"></span>ADDRESS
                                </th>

                                 <th class="col-md-1">
                                    <span class="line"></span>POSTAL CODE
                                </th>
                               

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="SS NUMBER">
                                  ${data.sss }
                                </td>
                                <td height="25px;" data-title="SURNAME">
                                	 ${data.lastName }
                                </td>
                                <td height="25px;" data-title="GIVEN NAME">
                                 	 ${data.firstName }
                                </td>
                                <td  height="25px;" data-title="MIDDLE NAME ">
                                 	${data.middleName }
                                   
                                </td>

                                 <td height="25px;" data-title="ADDRESS (NO. &  STREET ; CITY/TOWN & PROVINCE) ">
                              		 ${data.pi_address }
                                   
                                </td>
                                    <td height="25px;" data-title="POSTAL CODE">
                              		${data.zipCode }
                                   
                                </td>


                            </tr>
                         </tbody>  




                         <thead>
                            <tr>
                                <th class="col-md-2">
                                   <span class="line"></span>SEX
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>DATE OF BIRTH  
                                </th>
                                 <th class="col-md-2" >
                                    <span class="line"></span>CIVIL STATUS
                                </th>
                              <th class="col-md-2"></th>
                             <th class="col-md-2"></th>
                             <th class="col-md-2"></th> 
                               

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="SEX">
                                  ${data.gender }
                                </td>
                                <td height="25px;" data-title="DATE OF BIRTH">
                                 <jsp:useBean id="dateValue" class="java.util.Date" />
								<jsp:setProperty name="dateValue" property="time" value="${data.birthday }" />
								<fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy" />
                                </td>
                                <td height="25px;" data-title="CIVIL STATUS">
                                 ${data.civilstatus }
                                </td>
                                <td  height="25px;" data-title="">
                                 
                                   
                               </td>

                                 <td height="25px;" data-title="">
                              
                                   
                                </td>
                                    <td height="25px;" data-title="">
                              
                                   
                                </td> 
                                

                            </tr>
                         </tbody>      

                          
                         </table>

                    

 <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small>BENEFICIARIES</small></h4>
                    </div>
                   
                </div>




                    <table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                                <th class="col-md-2">
                                   <span class="line"></span>SPOUSE  
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>FATHER  
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>MOTHER 
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>OTHER BENEFICIARIES 
                                </th>

                                 <th class="col-md-3">
                                    <span class="line"></span>NAME
                                </th>

                                 <th class="col-md-1">
                                    <span class="line"></span>RELATIONSHIP
                                </th>
                               

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="SPOUSE">
                                  
                                </td>
                                <td height="25px;" data-title="FATHER">
                                	
                                </td>
                                <td height="25px;" data-title="MOTHER">
                                 
                                </td>
                                <td  height="25px;" data-title="OTHER BENEFICIARIES">
                                 
                                   
                                </td>

                                 <td height="25px;" data-title="NAME">
                              
                                   
                                </td>
                                    <td height="25px;" data-title="RELATIONSHIP">
                              
                                   
                                </td>


                            </tr>
                         </tbody>  

                         <thead>
                            <tr>
                                <th class="col-md-2">
                                   <span class="line"></span>CHILDREN  
                                </th>
                                <th class="col-md-2">
                                    <span class="line" ></span>DATE OF BIRTH  
                                </th>
                               <th class="col-md-2"></th>
                              <th class="col-md-2"></th>
                              <th class="col-md-2"></th>
                              <th class="col-md-2"></th> 
                               

                            </tr>
                        </thead>


                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="CHILDREN">
                                
                                </td>
                                <td height="25px;" data-title="DATE OF BIRTH">
                                
                                </td>
                              <td height="25px;" data-title=""></td>
                              <td height="25px;" data-title=""></td>
                              <td height="25px;" data-title=""></td>
                              <td height="25px;" data-title=""></td>
							

                            </tr>
                         </tbody>  


                        </table>


	

        </div>
            </div>
		


            <!--end-->
            <!-- for sss-->
          
		
			
			</c:forEach>
			



                    <!-- start -->
                <!--for phil health-->

               <c:forEach items="${details}" var="data">          
					


                   <div class="table-products space1">
            
                <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small>Membership Form PhilHealth</small></h4>
                    </div>
                    
                </div>



                <div id="no-more-tables">
            <table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                                <th class="col-md-2">
                                     <span class="line"></span>PIN
                                   
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>PURPOSE
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Last Name 
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>First Name
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Name Extension
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Middle Name
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="PIN">
                                ${data.pi_employee_number}
                                </td>
                                <td height="25px;" data-title="PURPOSE">
                               
                                </td>
                                <td height="25px;" data-title="Last Name">
                              ${data.lastName }
                                </td>
                                <td  height="25px;" data-title="First Name">
                                  ${data.firstName }
                                   
                                </td>

                                 <td height="25px;" data-title="Name Extension">
                              
                                  ${data.extension_name }
                                
                                   
                                </td>

                                 <td height="25px;" data-title="Middle Name">
                                 ${data.middleName }
                                   
                                </td>



                            </tr>
                         </tbody>



                           <thead>
                            <tr>
                                <th class="col-md-2">
                                     <span class="line"></span>Date of Birth
                                   
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Place of Birth
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Sex  
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Civil Status
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Nationality
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>TIN
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="Date of Birth">
                                 <jsp:useBean id="dateValue1" class="java.util.Date" />
								<jsp:setProperty name="dateValue1" property="time" value="${data.birthday }" />
								<fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy" />
                                </td>
                                <td height="25px;" data-title="Place of Birth">
                                
                                </td>
                                <td height="25px;" data-title="Sex">
                                ${data.gender }
                                </td>
                                <td  height="25px;" data-title="Civil Status">
                                  ${data.civilstatus }
                                   
                                </td>

                                 <td height="25px;" data-title="Nationality">
                                
                                   
                                </td>
                                
                                 <td height="25px;" data-title="TIN">
                                 ${data.tin }
                                   
                                </td>



                            </tr>
                         </tbody>



                           <thead>
                             <tr>
                                <th class="col-md-2" colspan="7">
                                   <span class="line"></span>Address
                                   
                                </th>
                                <!-- <th class="col-md-2">
                                    <span class="line"></span>Building Name
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Lot/Block/House  
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Street 
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Subdivision/Village 

                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Barangay 
                                </th>  -->

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="Unit/Floor/Room No." colspan="7">
                                  ${data.ph_employer_address } 
                                </td>
                               <!--  <td height="25px;" data-title="Building">
                                
                                </td>
                                <td height="25px;" data-title="Lot No.">
                                
                                </td>
                                <td  height="25px;" data-title="Block No.">
                                
                                   
                                </td>

                                 <td height="25px;" data-title="Phase No.">
                                
                                   
                                </td>
                                    <td height="25px;" data-title="House No.">
                                 
                                
                                </td>
                                    <td height="25px;" data-title="Street">
                                   
                                </td>--> 


                            </tr>
                         </tbody>



                          <thead>
                            <tr>
                                <!--   <th class="col-md-2">
                                     <span class="line"></span>City/Municipality
                                   
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Province 
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Country  
                                </th> -->
                                <th class="col-md-2" colspan="6">
                                    <span class="line"></span>Zip Code  
                                </th>
                              <!--  <th class="col-md-2">
                                    <span class="line"></span>

                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span> 
                                </th>
									-->  
                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                 <!--  <td height="25px;" data-title="City/Municipality">
                                
                                </td>
                                <td height="25px;" data-title="Province">
                               
                                </td>
                                <td height="25px;" data-title="Country">
                             
                                </td>
                                -->
                                <td  height="25px;" data-title="Zip Code" colspan="6">
                                 ${data.zipCode }
                                   
                                </td>

                              <!--    <td height="25px;" data-title="">
                                
                                   
                                </td>
                                
                                 <td height="25px;" data-title="">
                                
                                   
                                </td>  -->

                            </tr>
                         </tbody>


                              <thead>
                            <tr>
                                 <th class="col-md-2">
                                     <span class="line"></span>Landline Number
                                   
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Mobile Number 
                                </th>
                                 <th class="col-md-2" colspan="4">
                                    <span class="line"></span>E-mail Address   
                                </th>
                              <!--    <th class="col-md-2">
                                    <span class="line"></span> 
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>

                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span> 
                                </th> -->

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="Landline Number">
                                ${data.landline }
                                </td>
                                <td height="25px;" data-title="Mobile Number">
                               ${data.mobile }
                                </td>
                                <td height="25px;" data-title="E-mail Address" colspan="4">
                               ${data.email }
                                </td>
                              <!--   <td  height="25px;" data-title="">
                                 
                                   
                                </td>

                                 <td height="25px;" data-title="">
                                
                                   
                                </td>
                                
                                 <td height="25px;" data-title="">
                                
                                   
                                </td>
 									-->


                            </tr>
                         </tbody>


                         </table>




                            <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small>DECLARATION OF DEPENDENTS: Legal Spouse </small></h4>
                    </div>
                 
                </div>

                    
                <table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                                <th class="col-md-2">
                                     <span class="line"></span>PIN
                                   
                                </th>
                             
                                 <th class="col-md-2">
                                    <span class="line"></span>Last Name 
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>First Name
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Name Extension
                                </th>
                                 <th class="col-md-1">
                                    <span class="line"></span>Middle Name
                                </th>
                                  <th class="col-md-2">
                                    <span class="line"></span>Date of Birth 
                                </th>
                                  <th class="col-md-1">
                                    <span class="line"></span>Sex
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="PIN">
                                
                                </td>
                                <td height="25px;" data-title="Last Name">
                              
                                </td>
                                <td  height="25px;" data-title="First Name">
                                 
                                   
                                </td>

                                 <td height="25px;" data-title="Name Extension">
                                
                                   
                                </td>

                                 <td height="25px;" data-title="Middle Name">
                                 
                                </td>

                                 <td height="25px;" data-title="Date of Birth ">
                                
                                   
                                </td>
                                    <td height="25px;" data-title="Sex">
                                 
                                   
                                </td>

                            </tr>
                         </tbody>

                         </table>


                         <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small>DECLARATION OF DEPENDENTS: Children </small></h4>
                    </div>
                 
                </div>

                    
                <table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                                <th class="col-md-2">
                                     <span class="line"></span>PIN
                                   
                                </th>
                             
                                 <th class="col-md-2">
                                    <span class="line"></span>Last Name 
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>First Name
                                </th>
                                <th class="col-md-1">
                                    <span class="line"></span>Name Extension
                                </th>
                                 <th class="col-md-1">
                                    <span class="line"></span>Middle Name
                                </th>
                                <th class="col-md-1">
                                    <span class="line"></span>Disability (yes/no)
                                </th>
                                  <th class="col-md-2">
                                    <span class="line"></span>Date of Birth 
                                </th>
                                  <th class="col-md-1">
                                    <span class="line"></span>Sex
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="PIN">
                                
                                </td>
                                <td height="25px;" data-title="Last Name">
                             
                                </td>
                                <td  height="25px;" data-title="First Name">
                                 
                                   
                                </td>

                                 <td height="25px;" data-title="Name Extension">
                                
                                   
                                </td>

                                 <td height="25px;" data-title="Middle Name">
                                
                                   
                                </td>
                                <td height="25px;" data-title="Disability (yes/no)">
                                
                                   
                                </td>

                                 <td height="25px;" data-title="Date of Birth ">
                                
                                   
                                </td>
                                    <td height="25px;" data-title="Sex">
                                
                                   
                                </td>



                            </tr>
                         </tbody>

                         </table>



                         <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small>DECLARATION OF DEPENDENTS: Parents Details </small></h4>
                    </div>
                 
                </div>

                    
                <table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                                <th class="col-md-2">
                                     <span class="line"></span>PIN
                                   
                                </th>
                             
                                 <th class="col-md-2">
                                    <span class="line"></span>Fathers Last Name 
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Fathers First Name
                                </th>
                                <th class="col-md-1">
                                    <span class="line"></span>Name Extension
                                </th>
                                 <th class="col-md-1">
                                    <span class="line"></span>Fathers Middle Name
                                </th>
                                <th class="col-md-1">
                                    <span class="line"></span>Disability (yes/no)
                                </th>
                                  <th class="col-md-2">
                                    <span class="line"></span>Date of Birth 
                                </th>
                                  <th class="col-md-1">
                                    <span class="line"></span>Sex
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="PIN">
                                
                                </td>
                                <td height="25px;" data-title="Fathers Last Name">
                             
                                </td>
                                <td  height="25px;" data-title="Fathers First Name">
                                 
                                </td>

                                 <td height="25px;" data-title="Name Extension">
                                
                                   
                                </td>

                                 <td height="25px;" data-title="Fathers Middle Name">
                                
                                   
                                </td>
                                <td height="25px;" data-title="Disability (yes/no)">
                                
                                   
                                </td>

                                 <td height="25px;" data-title="Date of Birth ">
                                
                                   
                                </td>
                                    <td height="25px;" data-title="Sex">
                                
                                   
                                </td>



                            </tr>
                         </tbody>


                         <thead>
                            <tr>
                                <th class="col-md-2">
                                     <span class="line"></span>PIN
                                   
                                </th>
                             
                                 <th class="col-md-2">
                                    <span class="line"></span>Mothers Last Name 
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Mothers First Name
                                </th>
                                <th class="col-md-1">
                                    <span class="line"></span>Name Extension
                                </th>
                                 <th class="col-md-1">
                                    <span class="line"></span>Mothers Middle Name
                                </th>
                                <th class="col-md-1">
                                    <span class="line"></span>Disability (yes/no)
                                </th>
                                  <th class="col-md-2">
                                    <span class="line"></span>Date of Birth 
                                </th>
                                  <th class="col-md-1">
                                    <span class="line"></span>Sex
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="PIN">
                                trst
                                </td>
                                <td height="25px;" data-title="Mothers Last Name">
                             	
                                </td>
                                <td  height="25px;" data-title="Mothers First Name">
                                 
                                   
                                </td>

                                 <td height="25px;" data-title="Name Extension">
                                
                                   
                                </td>

                                 <td height="25px;" data-title="Mothers Middle Name">
                                
                                   
                                </td>
                                <td height="25px;" data-title="Disability (yes/no)">
                                
                                   
                                </td>

                                 <td height="25px;" data-title="Date of Birth ">
                                
                                   
                                </td>
                                    <td height="25px;" data-title="Sex">
                                
                                   
                                </td>



                            </tr>
                         </tbody>


                         </table>


                          <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small> MEMBERSHIP CATEGORY </small></h4>
                    </div>
                 
                </div>



                <table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                                <th class="col-md-2">
                                     <span class="line"></span>Formal Economy 
                                   
                                </th>
                             
                                 <th class="col-md-2">
                                    <span class="line"></span>Informal Economy
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Indigent 
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Sponsored 
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Lifetime Member 
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Date of Retirement
                                </th>
                                 

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="Formal Economy">
                                
                                </td>
                                <td height="25px;" data-title="Informal Economy">
                             
                                </td>
                                <td  height="25px;" data-title="Indigent">
                                 
                                   
                                </td>

                                 <td height="25px;" data-title="Sponsored">
                                
                                   
                                </td>
								
                                 <td height="25px;" data-title="Lifetime Member ">
                                
                                   
                                </td>
                                <td height="25px;" data-title="Date of Retirement">
                                
                                   
                                </td>

                                 



                            </tr>
                         </tbody>
                         </table>






        </div>
            </div>

              <!--end of-->
                 <!-- philhealth -->

		
		</c:forEach>
		


            <!--start-->
            <!-- for pag ibig-->
          

	 <c:forEach items="${details}" var="data">                    

            <div class="table-products space1">
            
                <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small>Membership Category Pag-ibig</small></h4>
                    </div>
                    
                </div>



                <div id="no-more-tables">
            <table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                                <th class="col-md-2">
                                   
                                   
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Last Name
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>First Name
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Name Extension
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Middle Name
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                 <td height="25px;" data-title="">
                                  Member
                                </td>
                                <td height="25px;" data-title="Last Name">
                                 ${data.lastName }
                                </td>
                                <td height="25px;" data-title="First Name">
                                 ${data.firstName }
                                </td> 
                                
                                 <td height="25px;" data-title="Extension Name">
                                  ${data.extension_name }
                                </td>  
                                 <td height="25px;" data-title="Middle Name" colspan="2">
                                 ${data.middleName }
                                  
                               </td>
                                                             
                            </tr>
                          <tr>
                                <td height="25px;" data-title="">
                                  Father
                                </td>
                                <td height="25px;" data-title="Last Name">
                               	
                                </td>
                                <td height="25px;" data-title="First Name">
                                 
                                </td>
                                <td height="25px;" data-title="Name Extension">
                                 
                                   
                                </td>

                                 <td height="25px;" data-title="Middle Name">
                                
                                   
                                </td>


                            </tr>


                                <tr>
                                <td height="25px;" data-title="">
                                  Mother (Maiden Name)
                                </td>
                                <td height="25px;" data-title="Last Name">
                                 ${data.maiden_name}
                                </td>
                                <td height="25px;" data-title="First Name">
                                
                                </td>
                                <td height="25px;" data-title="Name Extension">
                                  
                                   
                                </td>

                                 <td height="25px;" data-title="Middle Name">
                                
                                   
                                </td>


                            </tr>
                                <tr>
                                <td height="25px;" data-title="">
                                 Spouse (If Married)
                                </td>
                                <td height="25px;" data-title="Last Name">
                               
                                </td>
                                <td height="25px;" data-title="First Name">
                                 
                                </td>
                                <td height="25px;" data-title="Name Extension">
                                 
                                   
                                </td>

                                 <td height="25px;" data-title="Middle Name">
                                 
                                   
                                </td>


                            </tr>


                            <tr>
                                <small>
                                Member's Name as Appearing in the Birth Certificate
                                </small>
                                </td>
                                <td height="25px;" data-title="Last Name">
                               ${data.lastName }
                                </td>
                                <td height="25px;" data-title="First Name">
                                 ${data.firstName }
                                </td>
                                <td height="25px;" data-title="Name Extension">
                                 ${data.extension_name }
                                   
                                </td>

                                 <td height="25px;" data-title="Middle Name">
                                 
                                   ${data.middleName }
                                </td>
                            </tr>

                        </tbody>
           




                   
            </table>




              <table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                               
                                <th class="col-md-2">
                                    <span class="line"></span>Date of Birth
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Marital Status
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Place of Birth
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Citizenship
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Sex
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Facial Features
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                               <td height="25px;" data-title="Date of Birth">
                                 <jsp:useBean id="dateValue2" class="java.util.Date" />
								<jsp:setProperty name="dateValue2" property="time" value="${data.birthday }" />
								<fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy" />
                                </td>
                                <td height="25px;" data-title="Marital Status">
                                ${data.civilstatus }
                                </td>
                                <td height="25px;" data-title="Place of Birth">
                                 
                                </td>
                                <td  height="25px;" data-title="Citizenship">
                                
                                   
                                </td>

                                 <td height="25px;" data-title="Sex">
                                 ${data.gender }
                                   
                                </td>
                                <td height="25px;" data-title="Facial Features">
                                
                                   
                                </td>
                            </tr>
      </tbody>
 <thead>
                            <tr>
                               
                                <th class="col-md-2">
                                    <span class="line"></span>TIN
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>SSS NUMBER
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>GSIS NUMBER
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>EMPLOYEE NUMBER
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>AFP/PNP Employee
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>DECS Employee
                                </th>

                            </tr>
                        </thead>
                            <tbody>
                            <!-- row -->
                            <tr>
                                  <td height="25px;" data-title="TIN">
                                 ${data.tin }
                                </td>
                                <td height="25px;" data-title="SSS NUMBER">
                                 ${data.sss }
                                </td>
                                <td height="25px;" data-title="GSIS NUMBER">
                                
                                </td>
                                <td  height="25px;" data-title="EMPLOYEE NUMBER">
                                 ${data.pi_employee_number }
                                   
                                </td>

                                 <td height="25px;" data-title="AFP/PNP Employee">
                                 
                                   
                                </td>
                                <td height="25px;" data-title="DECS Employee">
                               
                                   
                                </td>

                            </tr>
      </tbody>


                  
           






                   
            </table>


                        <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small>PRESENT HOME ADDRESS</small></h4>
                    </div>
                   
                </div>





   <table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                                <th class="col-md-2" colspan="7">
                                   <span class="line"></span>Address
                                   
                                </th>
                                <!-- <th class="col-md-2">
                                    <span class="line"></span>
                                </th>
                                 <th class="col-md-1">
                                    <span class="line"></span>
                                </th>
                                <th class="col-md-1">
                                    <span class="line"></span>
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>
                                </th>  -->

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="Unit/Floor/Room No." colspan="7">
                                  ${data.pi_address } 
                                </td>
                               <!--  <td height="25px;" data-title="Building">
                                
                                </td>
                                <td height="25px;" data-title="Lot No.">
                                
                                </td>
                                <td  height="25px;" data-title="Block No.">
                                
                                   
                                </td>

                                 <td height="25px;" data-title="Phase No.">
                                
                                   
                                </td>
                                    <td height="25px;" data-title="House No.">
                                 
                                
                                </td>
                                    <td height="25px;" data-title="Street">
                                   
                                </td>--> 


                            </tr>
                          

                        </tbody>
           






                   
            </table>

                  <!--   <table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                                <th class="col-md-2">
                                   <span class="line"></span>Subdivision
                                   
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Barangay
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Municipality/City
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Province/State(if abroad)
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Counry(if abroad)
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>ZIP Code
                                </th>
                                

                            </tr>
                        </thead>
                        <tbody>  -->
                            <!-- row -->
                          <!--    <tr>
                                <td height="25px;" data-title="Subdivision">
                                  PINEVILLE
                                </td>
                                <td height="25px;" data-title="Barangay">
                                 LAWA
                                </td>
                                <td height="25px;" data-title="Municipality/City">
                                MEYCAUAYAN
                                </td>
                                <td  height="25px;" data-title="Province/State(if abroad)">
                                 
                                   BULACAN
                                </td>

                                 <td height="25px;" data-title="Counry(if abroad)">
                                 
                                   PHILIPPINES
                                </td>
                                    <td height="25px;" data-title="ZIP Code">
                                 3020
                                </td>
                                    


                            </tr>
                          

                        </tbody>
           

           
                   
            </table>  -->

                        <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small>CONTACT DETAILS</small></h4>
                    </div>
                   
                </div>



 <table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                                <th class="col-md-2">
                                   <span class="line"></span>COUNTRY
                                   
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Cell Phone
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Business (Direct Line)
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Business (Trunk Line)
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Email Address
                                </th>
                                
                                

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="COUNTRY">
                                 ${data.ph_country_based}
                                </td>
                                <td height="25px;" data-title="Cell Phone">
                                 ${data.mobile}
                                </td>
                                <td height="25px;" data-title="Business (Direct Line)">
                                  ${data.landline}
                                </td>
                                <td  height="25px;" data-title="Business (Trunk Line)">
                                  ${data.landline}
                                   
                                </td>

                                 <td height="25px;" data-title="Email Address">
                                 
                                   ${data.email}
                                </td>

                            </tr>
                          

                        </tbody>
           

           
                   
            </table>




 <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small>PERMANENT HOME ADDRESS</small></h4>
                    </div>
                   
                </div>


                <table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                                 <th class="col-md-2" colspan="5">
                                   <span class="line"></span>Address
                                   
                                </th>
                               <!--  <th class="col-md-2">
                                    <span class="line"></span>Building
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Lot No.
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Block No.
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Phase No.
            
                                </th>
                                --> 
                                

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                 <td height="25px;" data-title="Unit/Floor/Room No.">
                                ${data.pi_address}
                                </td>
                               <!--  <td height="25px;" data-title="Building">
                               
                                </td>
                                <td height="25px;" data-title="Lot No.">
                                
                                </td>
                                <td  height="25px;" data-title="Block No.">
                                 
                                   
                                </td>

                                 <td height="25px;" data-title="Phase No.">
                                 
                                 
                                </td>
                               --> 
                                    


                            </tr>
                          

                        </tbody>
           

           
                   
            </table>

              <!-- <table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                                <th class="col-md-2">
                                   <span class="line"></span>House No.
                                   
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Street
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Subdivision
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Barangay
                                </th>
                                
                                
                                

                            </tr>
                        </thead>
                        <tbody>  -->  
                            <!-- row -->
                          <!--  <tr>
                                <td height="25px;" data-title="House No.">
                                257
                                </td>
                                <td height="25px;" data-title="Street">
                               JASMIN CORNER CAMIA
                                </td>
                                <td height="25px;" data-title="Subdivision">
                                    PINEVILLE
                                </td>
                                <td  height="25px;" data-title="Barangay">
                                 LAWA
                                   
                                </td>

                                
                               
                                    


                            </tr>
                          

                        </tbody>


                        <thead>
                            <tr>
                                <th class="col-md-2">
                                   <span class="line"></span>Municipality/City
                                   
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Province
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Zip Code
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>PREFERRED MAILING ADDRESS
                                </th>
                                
                                
                                

                            </tr>
                        </thead>
                        <tbody>  -->
                            <!-- row -->
                           <!-- <tr>
                                <td height="25px;" data-title="Municipality/City">
                                MEYCAUAYAN
                                </td>
                                <td height="25px;" data-title="Province">
                                BULACAN
                                </td>
                                <td height="25px;" data-title="Zip Code">
                                   3020 
                                </td>
                                <td  height="25px;" data-title="PREFERRED MAILING ADDRESS">
                                 
                                   
                                </td>

                                
                               
                                    


                            </tr>
                          

                        </tbody>
           

           
                   
            </table> -->  



 <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small>EMPLOYMENT/BUSINESS DETAILS</small></h4>
                    </div>
                   
                </div>



                  <table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                                <th class="col-md-2">
                                   <span class="line"></span>EMPLOYER/BUSINESS NAME
                                   
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>EMPLOYMENT STATUS
                                </th>
                                 <th class="col-md-1">
                                    <span class="line"></span>DATE STARTED
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>MONTHLY INCOME
                                </th>
                                <th class="col-md-1">
                                    <span class="line"></span>OCCUPATION
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>TYPE OF WORK(For OFWs only)
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>ASSIGNED COUNTRY(Land-based only)
                                </th>
                                
                                

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="EMPLOYER/BUSINESS NAME">
                                ${data.pi_employer_business_name }
                                </td>
                                <td height="25px;" data-title="EMPLOYMENT STATUS">
                                ${data.pi_employment_status }
                                </td>
                                <td height="25px;" data-title="DATE STARTED">
                                 <jsp:useBean id="dateValues" class="java.util.Date" />
								<jsp:setProperty name="dateValues" property="time" value="${data.pi_date_start }" />
								<fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy" />
                                </td>
                                <td  height="25px;" data-title="MONTHLY INCOME">
                               
                                 ${data.pi_allowance }
                                </td>

                                <td  height="25px;" data-title="OCCUPATION">
                             
                                  ${data.pi_profession }
                                </td>

                                 <td  height="25px;" data-title="TYPE OF WORK(For OFWs only)">
                                 
                                   ${data.pi_profession }
                                </td>

                                 <td  height="25px;" data-title="ASSIGNED COUNTRY(Land-based only)">
                                 
                                   
                                </td>

                            </tr>
                          

                        </tbody>


                      
           

           
                   
            </table>



 <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small>EMPLOYER/BUSINESS ADDRESS</small></h4>
                    </div>
                   
                </div>
<table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                                <th class="col-md-2" colspan="7">
                                   <span class="line"></span>Company Address
                                   
                                </th>
                               <!--  <th class="col-md-2">
                                    <span class="line"></span>Building
                                </th>
                                 <th class="col-md-1">
                                    <span class="line"></span>Lot No.
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Block No.
                                </th>
                                <th class="col-md-1">
                                    <span class="line"></span>Phase No.
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>House No.
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Street
                                </th>  -->

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                 <td height="25px;" data-title="Unit/Floor/Room No." colspan="6">
                             		${data.company_address}
                                </td>
                             <!--   <td height="25px;" data-title="Building">
                              THE ONE EXECUTIVE BUILDING
                                </td>
                                <td height="25px;" data-title="Lot No.">
                                  
                                </td>
                                <td  height="25px;" data-title="Block No.">
                               
                                   18,000.00
                                </td>

                                <td  height="25px;" data-title="Phase No.">
                              
                                   COMPUTER PROGRAMMERS
                                </td>

                                 <td  height="25px;" data-title="House No.">
                                 
                                   
                                </td>

                                 <td  height="25px;" data-title="Street">
                                 WEST AVENUE CORNER COLONEL MARTINEZ
                                   
                                </td>
                                


                                
                               
                                    
							-->

                            </tr>
                          <!--  

                        </tbody>
                                <thead>
                        <tr>
                                <th class="col-md-2">
                                   <span class="line"></span>Subdivision
                                   
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Barangay
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Municipality
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>State(abroad)
                                </th>
                                <th class="col-md-1">
                                    <span class="line"></span>Country(abroad)
                                </th>
                                <th class="col-md-1">
                                    <span class="line"></span>ZIP Code
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>MANNING AGENCY
                                </th>
							
                                
                                

                            </tr>
                        </thead>
                        <tbody>   -->
                            <!-- row -->
                         <!--    <tr>
                                <td height="25px;" data-title="Subdivision">
                             
                                </td>
                                <td height="25px;" data-title="Barangay">
                              
                                </td>
                                <td height="25px;" data-title="Municipality">
                                  QUEZON CITY
                                </td>
                                <td  height="25px;" data-title="State(abroad)">
                               
                                  
                                </td>

                                <td  height="25px;" data-title="Country(abroad)">
                              PHILIPPINES
                                   
                                </td>

                                 <td  height="25px;" data-title="ZIP Code">
                                 1104
                                   
                                </td>

                                 <td  height="25px;" data-title="MANNING AGENCY">
                                
                                </td>
                                


                                
                               
                                    


                            </tr>
                          

                        </tbody>

 							-->
                      
           


                      
           

           
                   
            </table>



                <div class="row filter-block" style="padding-left:15px;">
                <div class="col-md-6 col-sm-6" >
                        <h4><small>PREVIOUS EMPLOYMENT FROM DATE OF Pag-IBIG FUND MEMBERSHIP</small></h4>
                    </div>

                   
                </div>


                  <table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                                <th class="col-md-2">
                                   <span class="line"></span>EMPLOYER/BUSINESS NAME
                                   
                                </th>
                                <th class="col-md-1">
                                    <span class="line"></span>FROM
                                </th>
                                 <th class="col-md-1">
                                    <span class="line"></span>TO
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>EMPLOYER/BUSINESS ADDRESS
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>EMPLOYER/BUSINESS NAME
                                </th>
                                <th class="col-md-1">
                                    <span class="line"></span>FROM
                                </th>
                                 <th class="col-md-1">
                                    <span class="line"></span>TO
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>EMPLOYER/BUSINESS ADDRESS
                                </th>
                                
                                

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="EMPLOYER/BUSINESS NAME">
                                ${data.company_name}
                                </td>
                                <td height="25px;" data-title="FROM">
                              	
                                </td>
                                <td height="25px;" data-title="TO">
                                
                                </td>
                                <td  height="25px;" data-title="EMPLOYER/BUSINESS ADDRESS">
                               
                                  
                                </td>

                                <td  height="25px;" data-title="EMPLOYER/BUSINESS NAME">
                              
                                  
                                </td>

                                 <td  height="25px;" data-title="FROM">
                                 
                                   
                                </td>

                                 <td  height="25px;" data-title="TO">
                                 
                                   
                                </td>
                                
                                 <td  height="25px;" data-title="EMPLOYER/BUSINESS ADDRESS">
                                 
                                   
                                </td>
                                
                            </tr>
                          

                        </tbody>


                      
           

           
                   
            </table>





            <div class="row filter-block" style="padding-left:15px;">
                <div class="col-md-6 col-sm-6" >
                        <h4><small>HEIRS</small></h4>
                    </div>

                   
                </div>


                    <table class="table col-md-12 table-bordered table-striped table-condensed">
            
               
                     <thead>
                            <tr>
                                <th class="col-md-2">
                                   <span class="line"></span>LAST NAME
                                   
                                </th>
                                <th class="col-md-1">
                                    <span class="line"></span>FIRST NAME
                                </th>
                                 <th class="col-md-1">
                                    <span class="line"></span>NAME EXTENSION
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>MIDDLE NAME  
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>RELATIONSHIP
                                </th>
                                <th class="col-md-1">
                                    <span class="line"></span>DATE OF BIRTH
                                </th>
                                
                                

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="LAST NAME">
                               
                                </td>
                                <td height="25px;" data-title="FIRST NAME">
                              
                                </td>
                                <td height="25px;" data-title="NAME EXTENSION">
                                
                                </td>
                                <td  height="25px;" data-title="MIDDLE NAME">
                               
                               
                                </td>

                                <td  height="25px;" data-title="RELATIONSHIP">
                              
                                  
                                </td>

                                 <td  height="25px;" data-title="DATE OF BIRTH">
                                
                                </td>

                            </tr>



                             <tr>
                                <td height="25px;" data-title="LAST NAME">
                                
                                </td>
                                <td height="25px;" data-title="FIRST NAME">
                            	
                                </td>
                                <td height="25px;" data-title="NAME EXTENSION">
                                
                                </td>
                                <td  height="25px;" data-title="MIDDLE NAME">
                               
                                  
                                </td>

                                <td  height="25px;" data-title="RELATIONSHIP">
                             
                                  
                                </td>

                                 <td  height="25px;" data-title="DATE OF BIRTH">
                                 
                                   
                                </td>

                            </tr>
                          

                        </tbody>

     
            </table>










            
        </div>
            </div>

                         <!-- end -->
                <!--for pagibig-->
	
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
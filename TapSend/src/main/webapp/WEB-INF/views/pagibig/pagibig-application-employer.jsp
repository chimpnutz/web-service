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
    <link href="resources/pagibig/css/bootstrap.css" rel="stylesheet">
    <link href="resources/pagibig/css/bootstrap-overrides.css" type="text/css" rel="stylesheet">

    <!-- libraries -->
    <link href="resources/pagibig/css/jquery-ui-1.css" rel="stylesheet" type="text/css">
    <link href="resources/pagibig/css/font-awesome.css" type="text/css" rel="stylesheet">

    <link href="resources/pagibig/css/font-awesome.min.css" type="text/css" rel="stylesheet">


    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="resources/pagibig/css/layout.css">
    <link rel="stylesheet" type="text/css" href="resources/pagibig/css/elements.css">
    <link rel="stylesheet" type="text/css" href="resources/pagibig/css/icons.css">

    <!-- this page specific styles -->
    <link rel="stylesheet" href="resources/pagibig/css/index.css" type="text/css" media="screen">

    <!-- open sans font -->
    <link href="resources/pagibig/css/css_002.css" rel="stylesheet" type="text/css">

    <!-- lato font -->
    <link href="resources/pagibig/css/css.css" rel="stylesheet" type="text/css">

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
            <a class="navbar-brand  hidden-xs" href="pagibig-home.html" style="padding:0px 20px !important;margin-top:15px;">
               <p>Pag Ibig</p>
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
                <a  href="pagibig-home.html">
                    <i class="fa fa-home"></i>
                    <span>Home</span>
                   
                </a>
              
            </li>  

            <li>
                <a class="dropdown-toggle" href="#">
                    <i class="fa fa-group"></i>
                    <span>Users</span>
                    <i class="fa fa-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="pagibig-userlist.html">User list</a></li>
                    <li><a href="pagibig-adduser.html">New user form</a></li>
                   
                </ul>
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

           
          

                <div class="col-md-4"></div>
                <div class="col-md-4">
                   
                  
                    <div class="row ctrls">
                        <!-- Split button -->
                        <div class="btn-group">
                        <a href="pagibig-home.html"> <button type="button" class="btn btn-default active">Recent</button></a>
                        </div>


                        <div class="btn-group">
                          <a href="pagibig-return.html"><button type="button" class="btn btn-default">Return</button></a>
                          
                          
                        </div>
                        <div class="btn-group">
                         <a href="pagibig-encoded.html"> <button type="button" class="btn btn-default ">Encoded</button></a>
                        </div>                    
              
                
                       </div>
                         </div>

                        <div class="col-md-4"></div>
                   <!-- end -->


                <!--start-->
                
                    

            <div class="table-products space1">
            
                <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small>Membership Category: Employer</small></h4>
                    </div>

                    <div class="hidden-lg vissible-sm-4 vissible-md-4">
                        
                    </div>
                   
                </div>



                <div id="no-more-tables">
            <table class="table col-md-12 table-bordered table-striped table-condensed">
            
                <thead>
                            <tr>
                                <th class="col-md-4">
                                    <span class="line"></span>EMPLOYER/BUSINESS NAME
                                   
                                </th>

                                <th class="col-md-4">
                                    <span class="line"></span>EMPLOYER ID NUMBER
                                   
                                </th>

                                 <th class="col-md-4">
                                    <span class="line"></span>TRACKING NUMBER
                                   
                                </th>
                              

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="EMPLOYER/BUSINESS NAME">
                                  
                                </td>
                                  <td height="25px;" data-title="EMPLOYER ID NUMBER">
                                  
                                </td>
                                  <td height="25px;" data-title="TRACKING NUMBER">
                                  
                                </td>
                      

                            </tr>
                       

                        </tbody>



                   



                   
            </table>

      <table class="table col-md-12 table-bordered table-striped table-condensed">
            
                <thead>
                            <tr>
                                
                                <th class="col-md-2">
                                    <span class="line"></span>Unit/Room
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Building Name
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Lot No
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Block No
                                </th>
                                  <th class="col-md-2">
                                    <span class="line"></span>Phase No
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>House No
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="Unit/Room">
                                 
                                </td>
                                <td height="25px;" data-title="Building Name">
                              
                                </td>
                                <td height="25px;" data-title="Lot No">
                                 
                                </td>
                                <td  height="25px;" data-title="Block No">
                                 
                                   
                                </td>

                                 <td height="25px;" data-title="Phase No">
                                 
                                </td>
                                  <td height="25px;" data-title="House No">
                               
                                   
                                </td>



                            </tr>
                       

                        </tbody>

                        <thead>
                            <tr>
                                
                                <th class="col-md-2">
                                    <span class="line"></span>Street Name
                                </th>
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
                                    <span class="line"></span>Province
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Zip Code
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="Street Name">
                                 
                                </td>
                                <td height="25px;" data-title="Subdivision">
                              
                                </td>
                                <td height="25px;" data-title="Barangay">
                                 
                                </td>
                                <td  height="25px;" data-title="Municipality/City">
                                 
                                   
                                </td>

                                 <td height="25px;" data-title="Province">
                                 
                                </td>
                                  <td height="25px;" data-title="Zip Code">
                               
                                   
                                </td>



                            </tr>
                       

                        </tbody>
           
</table>



                        <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small>CONTACT DETAILS</small></h4>
                    </div>
                   
                </div>



                 <table class="table col-md-12 table-bordered table-striped table-condensed">
            
                <thead>
                            <tr>
                                <th class="col-md-2">
                                    <span class="line"></span>Business (direct line)
                                   
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Business (fax)
                                   
                                </th>

                                 <th class="col-md-2">
                                    <span class="line"></span>Business (trunkline)
                                   
                                </th>

                                <th class="col-md-2">
                                    <span class="line"></span>Business Email
                                   
                                </th>
                              

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="Business (direct line)">
                                  
                                </td>
                                <td height="25px;" data-title="Business (fax)">
                                  
                                </td>

                                <td height="25px;" data-title="Business (trunkline)">
                                  
                                </td>
                                 <td height="25px;" data-title="Business Email">
                                  
                                </td>

                            </tr>
                       

                        </tbody>



                   



                   
            </table>


                <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small>EMPLOYER/BUSINESS DETAILS</small></h4>
                    </div>
                   
                </div>


                      <table class="table col-md-12 table-bordered table-striped table-condensed">
            
                          <thead>
                            <tr>
                                <th class="col-md-2">
                                    <span class="line"></span>Start of Business Operation
                                   
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Industry
                                   
                                </th>

                                 <th class="col-md-2">
                                    <span class="line"></span>Branch/Office
                                   
                                </th>

                                <th class="col-md-2">
                                    <span class="line"></span>Retirement Plan
                                   
                                </th>
                              

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="Start of Business Operation">
                                  
                                </td>
                                   <td height="25px;" data-title="Industry">
                                  
                                </td>

                                <td height="25px;" data-title="Branch/Office">
                                  
                                </td>
                                 <td height="25px;" data-title="Retirement Plan">
                                  
                                </td>

                            </tr>
                       

                        </tbody>


                         <thead>
                            <tr>
                                <th class="col-md-2">
                                    <span class="line"></span>Type of Employer
                                   
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Private Employers
                                   
                                </th>

                                 <th class="col-md-2">
                                    <span class="line"></span>Government Employers
                                   
                                </th>

                                <th class="col-md-2">
                                    <span class="line"></span>Previous Business Name
                                   
                                </th>
                              

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="Type of Employer">
                                  
                                </td>
                                   <td height="25px;" data-title="Private Employers">
                                  
                                </td>

                                <td height="25px;" data-title="Government Employers">
                                  
                                </td>
                                 <td height="25px;" data-title="Previous Business Name">
                                  
                                </td>

                            </tr>
                       

                        </tbody>

                          <thead>
                            <tr>
                                <th class="col-md-2">
                                    <span class="line"></span>Registry No.
                                   
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Date of Issuance
                                   
                                </th>

                                 <th class="col-md-2">
                                    <span class="line"></span>CDA Certificate
                                   
                                </th>

                                <th class="col-md-2">
                                    <span class="line"></span>Date of Issuance
                                   
                                </th>
                              

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="Registry No.">
                                  
                                </td>
                                   <td height="25px;" data-title="Date of Issuance">
                                  
                                </td>

                                <td height="25px;" data-title="CDA Certificate">
                                  
                                </td>
                                 <td height="25px;" data-title="Date of Issuance">
                                  
                                </td>

                            </tr>
                       

                        </tbody>


                          <thead>
                            <tr>
                                <th class="col-md-2">
                                    <span class="line"></span>TIN
                                   
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Private Employers
                                   
                                </th>

                                 <th class="col-md-2">
                                    <span class="line"></span>Government Employers
                                   
                                </th>

                                <th class="col-md-2">
                                    <span class="line"></span>
                                   
                                </th>

                              

                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="TIN">
                                  
                                </td>
                                   <td height="25px;" data-title="Private Employers">
                                  
                                </td>

                                <td height="25px;" data-title="Government Employers">
                                  
                                </td>
                                
                                <td height="25px;" data-title="">
                                  
                                </td>

                            </tr>
                       

                        </tbody>



                   



                   
            </table>







            
        </div>
            </div>
</div>

</div>

                 <!-- end -->










	<!-- scripts -->
    <script src="resources/pagibig/js/jquery-latest.js"></script>
    <script src="resources/pagibig/js/bootstrap.js"></script>
    <script src="resources/pagibig/js/jquery-ui-1.js"></script>
    <!-- knob -->
    <script src="resources/pagibig/js/jquery.js"></script>
    <!-- flot charts -->
    <script src="resources/pagibig/js/jquery_004.js"></script>
    <script src="resources/pagibig/js/jquery_003.js"></script>
    <script src="resources/pagibig/js/jquery_002.js"></script>
    <script src="resources/pagibig/js/theme.js"></script>


</body></html>
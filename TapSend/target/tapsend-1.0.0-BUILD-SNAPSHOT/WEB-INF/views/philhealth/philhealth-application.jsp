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
    <link href="resources/philhealth/css/bootstrap.css" rel="stylesheet">
    <link href="resources/philhealth/css/bootstrap-overrides.css" type="text/css" rel="stylesheet">

    <!-- libraries -->
    <link href="resources/philhealth/css/jquery-ui-1.css" rel="stylesheet" type="text/css">
    <link href="resources/philhealth/css/font-awesome.css" type="text/css" rel="stylesheet">

    <link href="resources/philhealth/css/font-awesome.min.css" type="text/css" rel="stylesheet">


    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="resources/philhealth/css/layout.css">
    <link rel="stylesheet" type="text/css" href="resources/philhealth/css/elements.css">
    <link rel="stylesheet" type="text/css" href="resources/philhealth/css/icons.css">

    <!-- this page specific styles -->
    <link rel="stylesheet" href="resources/philhealth/css/index.css" type="text/css" media="screen">

    <!-- open sans font -->
    <link href="resources/philhealth/css/css_002.css" rel="stylesheet" type="text/css">

    <!-- lato font -->
    <link href="resources/philhealth/css/css.css" rel="stylesheet" type="text/css">

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
            <a class="navbar-brand  hidden-xs" href="philhealth-home.html" style="padding:0px 20px !important;margin-top:15px;">
               <p>Phil Health</p>
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
                <a  href="philhealth-home.html">
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
                    <li><a href="philhealth-userlist.html">User list</a></li>
                    <li><a href="philhealth-adduser.html">New user form</a></li>
                  
                </ul>
            </li>
           
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
                      <!--  <div class="btn-group">
                        <a href="philhealth-home.html"> <button type="button" class="btn btn-default active">Recent</button></a>
                        </div>


                        <div class="btn-group">
                          <a href="philhealth-return.html"><button type="button" class="btn btn-default">Return</button></a>
                          
                          
                        </div>
                        <div class="btn-group">
                         <a href="philhealth-encoded.html"> <button type="button" class="btn btn-default ">Encoded</button></a>
                        </div>         -->            
              
                
                       </div>
                         </div>

                        <div class="col-md-4"></div>
                   <!-- end -->


                <!--start-->
                
                    

            <div class="table-products space1">
            
                <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small>Membership Form</small></h4>
                    </div>
                   
                </div>



                <div id="no-more-tables">
                
                 <c:forEach items="${details}" var="data">
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
                               test
                                </td>
                                <td height="25px;" data-title="Last Name">
                              ${data.lastName }
                                </td>
                                <td  height="25px;" data-title="First Name">
                                  ${data.firstName }
                                   
                                </td>

                                 <td height="25px;" data-title="Name Extension">
                                test
                                   
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
                                 ${data.birthday }
                                </td>
                                <td height="25px;" data-title="Place of Birth">
                                test
                                </td>
                                <td height="25px;" data-title="Sex">
                                ${data.gender }
                                </td>
                                <td  height="25px;" data-title="Civil Status">
                                  ${data.civilstatus }
                                   
                                </td>

                                 <td height="25px;" data-title="Nationality">
                                test
                                   
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
                                <td height="25px;" data-title="E-mail Address">
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
                                test
                                </td>
                                <td height="25px;" data-title="Last Name">
                              test
                                </td>
                                <td  height="25px;" data-title="First Name">
                                 
                                   test
                                </td>

                                 <td height="25px;" data-title="Name Extension">
                                test
                                   
                                </td>

                                 <td height="25px;" data-title="Middle Name">
                                 test
                                </td>

                                 <td height="25px;" data-title="Date of Birth ">
                                test
                                   
                                </td>
                                    <td height="25px;" data-title="Sex">
                                 test
                                   
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
                                test
                                </td>
                                <td height="25px;" data-title="Last Name">
                             test
                                </td>
                                <td  height="25px;" data-title="First Name">
                                 test
                                   
                                </td>

                                 <td height="25px;" data-title="Name Extension">
                                test
                                   
                                </td>

                                 <td height="25px;" data-title="Middle Name">
                                test
                                   
                                </td>
                                <td height="25px;" data-title="Disability (yes/no)">
                                
                                   test
                                </td>

                                 <td height="25px;" data-title="Date of Birth ">
                                test
                                   
                                </td>
                                    <td height="25px;" data-title="Sex">
                                test
                                   
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
                                test
                                </td>
                                <td height="25px;" data-title="Fathers Last Name">
                             test
                                </td>
                                <td  height="25px;" data-title="Fathers First Name">
                                 test
                                   
                                </td>

                                 <td height="25px;" data-title="Name Extension">
                                tst
                                   
                                </td>

                                 <td height="25px;" data-title="Fathers Middle Name">
                                test
                                   
                                </td>
                                <td height="25px;" data-title="Disability (yes/no)">
                                test
                                   
                                </td>

                                 <td height="25px;" data-title="Date of Birth ">
                                test
                                   
                                </td>
                                    <td height="25px;" data-title="Sex">
                                
                                   test
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
                                test
                                </td>
                                <td height="25px;" data-title="Mothers Last Name">
                             test
                                </td>
                                <td  height="25px;" data-title="Mothers First Name">
                                 test
                                   
                                </td>

                                 <td height="25px;" data-title="Name Extension">
                                test
                                   
                                </td>

                                 <td height="25px;" data-title="Mothers Middle Name">
                                test
                                   
                                </td>
                                <td height="25px;" data-title="Disability (yes/no)">
                                test
                                   
                                </td>

                                 <td height="25px;" data-title="Date of Birth ">
                                test
                                   
                                </td>
                                    <td height="25px;" data-title="Sex">
                                
                                 test  
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
                                test
                                </td>
                                <td height="25px;" data-title="Informal Economy">
                             test
                                </td>
                                <td  height="25px;" data-title="Indigent">
                                 test
                                   
                                </td>

                                 <td height="25px;" data-title="Sponsored">
                                
                                 test  
                                </td>

                                 <td height="25px;" data-title="Lifetime Member ">
                                test
                                   
                                </td>
                                <td height="25px;" data-title="Date of Retirement">
                                test
                                   
                                </td>

                                 



                            </tr>
                         </tbody>
                         </table>
			</c:forEach>





        </div>
            </div>
</div>
</div>

                 <!-- end -->










	<!-- scripts -->
    <script src="resources/philhealth/js/jquery-latest.js"></script>
    <script src="resources/philhealth/js/bootstrap.js"></script>
    <script src="resources/philhealth/js/jquery-ui-1.js"></script>
    <!-- knob -->
    <script src="resources/philhealth/js/jquery.js"></script>
    <!-- flot charts -->
    <script src="resources/philhealth/js/jquery_004.js"></script>
    <script src="resources/philhealth/js/jquery_003.js"></script>
    <script src="resources/philhealth/js/jquery_002.js"></script>
    <script src="resources/philhealth/js/theme.js"></script>


</body></html>
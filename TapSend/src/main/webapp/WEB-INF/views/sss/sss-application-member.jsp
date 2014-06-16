<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form"                prefix="form"%>
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
    <link href="resources/sss/css/bootstrap.css" rel="stylesheet">
    <link href="resources/sss/css/bootstrap-overrides.css" type="text/css" rel="stylesheet">

    <!-- libraries -->
    <link href="resources/sss/css/jquery-ui-1.css" rel="stylesheet" type="text/css">
    <link href="resources/sss/css/font-awesome.css" type="text/css" rel="stylesheet">

    <link href="resources/sss/css/font-awesome.min.css" type="text/css" rel="stylesheet">


    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="resources/sss/css/layout.css">
    <link rel="stylesheet" type="text/css" href="resources/sss/css/elements.css">
    <link rel="stylesheet" type="text/css" href="resources/sss/css/icons.css">

    <!-- this page specific styles -->
    <link rel="stylesheet" href="resources/sss/css/index.css" type="text/css" media="screen">

    <!-- open sans font -->
    <link href="resources/sss/css/css_002.css" rel="stylesheet" type="text/css">

    <!-- lato font -->
    <link href="resources/sss/css/css.css" rel="stylesheet" type="text/css">

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
            <a class="navbar-brand  hidden-xs" href="sss-home.html" style="padding:0px 20px !important;margin-top:15px;">
               <p>SSS</p>
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
                <a  href="sss-home.html">
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
                    <li><a href="sss-userlist.html">User list</a></li>
                    <li><a href="sss-adduser.html">New user form</a></li>                   
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
                      <!--  <div class="btn-group">
                        <a href="sss-home.html"> <button type="button" class="btn btn-default active">Recent</button></a>
                        </div>


                        <div class="btn-group">
                          <a href="sss-return.html"><button type="button" class="btn btn-default">Return</button></a>
                          
                          
                        </div>
                        <div class="btn-group">
                         <a href="sss-encoded.html"> <button type="button" class="btn btn-default ">Encoded</button></a>
                        </div>       -->             
              
                
                       </div>
                         </div>

                        <div class="col-md-4"></div>
                   <!-- end -->


                <!--start-->
                
                    

            <div class="table-products space1">
            
                <div class="row filter-block">
                <div class="col-md-4 col-sm-4">
                        <h4><small>MEMBERSHIP FORM E-1</small></h4>
                    </div>
                  
                </div>



                <div id="no-more-tables">
                 <c:forEach items="${details}" var="data">
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
                                    <span class="line"></span>ADDRESS (NO. &  STREET ; CITY/TOWN & PROVINCE) 
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
                                 <th class="col-md-2">
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
                                ${data.birthday }
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
			</c:forEach>
                    

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
                                test
                                </td>
                                <td height="25px;" data-title="FATHER">
                               test
                                </td>
                                <td height="25px;" data-title="MOTHER">
                                test
                                </td>
                                <td  height="25px;" data-title="OTHER BENEFICIARIES">
                                test
                                   
                                </td>

                                 <td height="25px;" data-title="NAME">
                             test
                                   
                                </td>
                                    <td height="25px;" data-title="RELATIONSHIP">
                              test
                                   
                                </td>


                            </tr>
                         </tbody>  

                         <thead>
                            <tr>
                                <th class="col-md-2">
                                   <span class="line"></span>CHILDREN  
                                </th>
                                <th class="col-md-2" colspan="5">
                                    <span class="line"></span>DATE OF BIRTH  
                                </th>
                              
                               

                            </tr>
                        </thead>


                        <tbody>
                            <!-- row -->
                            <tr>
                                <td height="25px;" data-title="CHILDREN">
                               test
                                </td>
                                <td height="25px;" data-title="DATE OF BIRTH" colspan="5">
                               test
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
    <script src="resources/sss/js/jquery-latest.js"></script>
    <script src="resources/sss/js/bootstrap.js"></script>
    <script src="resources/sss/js/jquery-ui-1.js"></script>
    <!-- knob -->
    <script src="resources/sss/js/jquery.js"></script>
    <!-- flot charts -->
    <script src="resources/sss/js/jquery_004.js"></script>
    <script src="resources/sss/js/jquery_003.js"></script>
    <script src="resources/sss/js/jquery_002.js"></script>
    <script src="resources/sss/js/theme.js"></script>


</body></html>
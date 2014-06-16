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

    <style type="text/css">
    .top-buffer { margin-top:20px; }
    </style>
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
                <a href="logout" role="button">
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
                         <a href="pagibig-home.html">  <button type="button" class="btn btn-default ">Recent</button></a>
                        </div>
                        <div class="btn-group">
                          <a href="pagibig-return.html">   <button type="button" class="btn btn-default ">Return</button></a>
                          
                          
                        </div>
                        <div class="btn-group">
                        <button type="button" class="btn btn-default active">Encoded</button>
                        </div>                    
              
                
                       </div>
                         </div>

                        <div class="col-md-4"></div>
                   <!-- end -->


                <!--start-->
                
                    

            <div class="table-products space1">
               
               <div id="no-more-tables">
            <table class="table col-md-12 table-hover table-striped table-condensed">
           
                     <thead>
                            <tr>
                                <th class="col-md-2">
                                   
                                    Name of Applicant
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Application Id
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Receipt Number
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Status
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Date
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr>
                                <td data-title="Name of Applicant">
                                  
                                   
                                    <a style="text-decoration:none; color:#747474" href="#">Tata Madlang Tuta</a>
                                </td>
                                <td data-title="Application Id">
                                 <a style="text-decoration:none; color:#747474" href="#">  534534635223423</a>
                                </td>
                                <td data-title="Receipt Number">
                                 <a style="text-decoration:none; color:#747474" href="#">  12345</a>
                                </td>
                                <td data-title="Status">
                                 <a style="text-decoration:none; color:#747474" href="#"><span class="label label-success">Approved</span></a>
                                   
                                </td>


                                <td data-title="Date">
                                  <a style="text-decoration:none; color:#747474" href="#"> April 1, 2014</a>
                                </td>

                            </tr>
                          <tr>
                                <td data-title="Name of Applicant">
                                  
                                   
                                    <a style="text-decoration:none; color:#747474" href="#">Tata Madlang Tuta</a>
                                </td>
                                <td data-title="Application Id">
                                 <a style="text-decoration:none; color:#747474" href="#">  534534635223423</a>
                                </td>
                                <td data-title="Receipt Number">
                                 <a style="text-decoration:none; color:#747474" href="#">  12345</a>
                                </td>
                                <td data-title="Status">
                                 <a style="text-decoration:none; color:#747474" href="#"><span class="label label-success">Approved</span></a>
                                   
                                </td>


                                <td data-title="Date">
                                  <a style="text-decoration:none; color:#747474" href="#"> April 1, 2014</a>
                                </td>

                            </tr>



                            <tr>
                                <td data-title="Name of Applicant">
                                  
                                   
                                    <a style="text-decoration:none; color:#747474" href="#">Tata Madlang Tuta</a>
                                </td>
                                <td data-title="Application Id">
                                 <a style="text-decoration:none; color:#747474" href="#">  534534635223423</a>
                                </td>
                                <td data-title="Receipt Number">
                                 <a style="text-decoration:none; color:#747474" href="#">  12345</a>
                                </td>
                                <td data-title="Status">
                                 <a style="text-decoration:none; color:#747474" href="#"><span class="label label-success">Approved</span></a>
                                   
                                </td>


                                <td data-title="Date">
                                  <a style="text-decoration:none; color:#747474" href="#"> April 1, 2014</a>
                                </td>

                            </tr>


                             <tr>
                                <td data-title="Name of Applicant">
                                  
                                   
                                    <a style="text-decoration:none; color:#747474" href="#">Tata Madlang Tuta</a>
                                </td>
                                <td data-title="Application Id">
                                 <a style="text-decoration:none; color:#747474" href="#">  534534635223423</a>
                                </td>
                                <td data-title="Receipt Number">
                                 <a style="text-decoration:none; color:#747474" href="#">  12345</a>
                                </td>
                                <td data-title="Status">
                                  <a style="text-decoration:none; color:#747474" href="#"><span class="label label-success">Approved</span></a>
                                   
                                </td>


                                <td data-title="Date">
                                  <a style="text-decoration:none; color:#747474" href="#"> April 1, 2014</a>
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
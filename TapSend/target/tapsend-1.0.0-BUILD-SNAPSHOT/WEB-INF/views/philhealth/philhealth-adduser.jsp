<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form"                prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<style id="flot-default-styles">.flot-tick-label {font-size:smaller;color:#545454;}</style>
    <title>Admin - Home</title>
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
               <p>phil health</p>
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
                <ul class="active submenu">
                    <li><a href="philhealth-userlist.html">User list</a></li>
                    <li><a class="active">New user form</a></li>
                   
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


	<!-- main container -->
    <div class="content">
        
      
        
        <div id="pad-wrapper" class="new-user">
            <div class="row header">
                <div class="col-md-12">
                    <h3>Create a new user</h3>
                </div>                
            </div>

            <div class="row form-wrapper">
                <!-- left column -->
                <div class="col-md-9 with-sidebar">
                    <form class="form-horizontal" role="form">

                        <div class="form-group">
                            <label for="inputWebsite1" class="col-md-2 control-label">Username</label>
                            <div class="col-md-8">
                              <input class="form-control" id="inputWebsite1" placeholder="Username" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputAddress1" class="col-md-2 control-label">Passowrd</label>
                            <div class="col-md-8">
                              <input class="form-control" id="inputAddress1" placeholder="****" type="password">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputName1" class="col-md-2 control-label">Name</label>
                            <div class="col-md-8">
                                <input class="form-control" id="inputName1" placeholder="Name" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputEmail1" class="col-md-2 control-label">Email</label>
                            <div class="col-md-8">
                              <input class="form-control" id="inputEmail1" placeholder="Email" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="selecttype" class="col-md-2 control-label">Type:</label>

                            <div class="ui-select" style="margin-left:15px !important;">
                                <select>                                  
                                    <option value="philhealth">PHILHEALTH</option>                                   
                                </select>
                            </div>
                            </div>
                        <div class="form-group">
                            <label for="inputPhone1" class="col-md-2 control-label">Phone</label>
                            <div class="col-md-8">
                              <input class="form-control" id="inputPhone1" placeholder="Phone" type="text">
                            </div>
                        </div>
                        
                       
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-8">
                              <button type="submit" class="btn btn-default">Confirm</button>
                            </div>
                        </div>
                    </form>
                   
                </div>

             
            
            </div>
        </div>
    </div>
    <!-- end main container -->


	<!-- scripts -->
    <script src="resources/philhealth/js/jquery-latest.js"></script>
    <script src="resources/philhealth/js/bootstrap.js"></script>
    <script src="resources/philhealth/js/theme.js"></script>

</body></html>
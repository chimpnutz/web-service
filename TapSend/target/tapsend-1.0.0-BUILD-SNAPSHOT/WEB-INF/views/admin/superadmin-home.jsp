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
                <a href="" class="dropdown-toggle" >
                    <i class="fa fa-group"></i>
                    <span>Users</span>
                    <i class="fa fa-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a  href="superadmin-userlist.html">User list</a></li>
                    <li><a href="superadmin-adduser.html">New user form</a></li>
                    
                </ul>
            </li>
           -->
          
            
            


         

            
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
                           <button type="button" class="btn btn-default active">Recent</button>
                        </div>


                        <div class="btn-group">
                          <a href="superadmin-return.html"><button type="button" class="btn btn-default">Return</button></a>
                          
                          
                        </div>
                        <div class="btn-group">
                          <a href="superadmin-encoded.html"> <button type="button" class="btn btn-default">Encoded</button></a>
                        </div>      -->              
              
                
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
                                   
                                   First Name
                                </th>
                               <th class="col-md-2">
                                    <span class="line"></span>Middle Name
                                </th>
                                 <th class="col-md-2">
                                    <span class="line"></span>Last Name
                                </th>
                                <th class="col-md-2">
                                    <span class="line"></span>Email
                                </th> 
                            
                                
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${details}" var="data">                    
									<c:if test="${data.role == '6'}">
                            <!-- row -->
                            <tr>
                                <td data-title="Name of Applicant">
                                  
                                   
                                    <a href="superadmin-application-member.html?userid=${data.id }" style="text-decoration:none; color:#747474" >${data.firstName} </a>
                                </td>
                                 <td data-title="Application Id">
                                 <a  href="superadmin-application-member.html?userid=${data.id }" style="text-decoration:none; color:#747474">  ${data.middleName }</a>
                                </td>
                                <td data-title="Receipt Number">
                                 <a href="superadmin-application-member.html?userid=${data.id }" style="text-decoration:none; color:#747474" >  ${data.lastName}</a>
                                </td>
                                <c:choose>
                                 <c:when test="${not empty data.email}">
                              <td data-title="Status">
                                 <a href="superadmin-application-member.html?userid=${data.id }" style="text-decoration:none; color:#747474" >${data.email }</a>                                  
                                </td> 
                                </c:when>
                                <c:otherwise>
                                <td data-title="Status">
                                 <a href="superadmin-application-member.html?userid=${data.id }" style="text-decoration:none; color:#747474" > no email</a>                                  
                                </td>
                                </c:otherwise>
                                </c:choose>
                               
                                

                            </tr>
                         

					</c:if>
					</c:forEach>

                        </tbody>
            </table>
        </div>
               
            </div>

</div>

</div>
                 <!-- end -->










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
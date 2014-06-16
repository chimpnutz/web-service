<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html style="background-image: url(resources/pagibig/img/4.jpg);" class="login-bg"><head>
	<title>Log In</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
    <!-- bootstrap -->
    <link href="resources/pagibig/css/bootstrap.css" rel="stylesheet">
    <link href="resources/pagibig/css/bootstrap-overrides.css" type="text/css" rel="stylesheet">

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="resources/pagibig/css/layout.css">
    <link rel="stylesheet" type="text/css" href="resources/pagibig/css/elements.css">
    <link rel="stylesheet" type="text/css" href="resources/pagibig/css/icons.css">

    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="resources/pagibig/css/font-awesome.css">
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="resources/pagibig/css/signin.css" type="text/css" media="screen">

    <!-- open sans font -->
    <link href="resources/pagibig/css/css.css" rel="stylesheet" type="text/css">

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
     <div class="login-wrapper">
        <div class="box">
            <div class="content-wrap">
                <h6>Log in</h6>
                <span style="color:red">${error}</span> 
                <form:form name="login-form" class="login-form" modelAttribute="loginForm" action="auth" method="post">

				<form:input path="user_id" type="text" class="form-control" placeholder="Username" />
				<form:input path="password" type="password" class="form-control" placeholder="Password" />
				
				<input type="submit" name="submit" value="Login" class="btn-flat primary login" />
				   
				
				</form:form>
                
            </div>
        </div>
    </div>

	
</body>
</html>


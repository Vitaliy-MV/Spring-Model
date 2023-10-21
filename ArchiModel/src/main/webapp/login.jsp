<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Spring Security</title>
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<c:url value="/css/signin.css" />" rel="stylesheet">
</head>
<body>

<div class="container" style="width: 300px;">
 <c:url value="/perform_login" var="login" />
    <form action="${login}" method="post"> 
     <!--  <form action="login" method="post">  --> 
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="form-control" name="username">
        <input type="password" class="form-control" name="password">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
    </form>
</div>

</body>
</html>

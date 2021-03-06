<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Layer7: SignUp</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/indexStyles.css">
    <link href="https://fonts.googleapis.com/css2?family=Piazzolla:wght@100&display=swap" rel="stylesheet">
</head>
<body>
<div id="particles-js"></div>
<header>
    <h1 id="hero-text">Layer7</h1>
    <hr>
</header>
<%--@elvariable id="user" type="com.mystic.layer7.entity.User"--%>
<form:form class="input-form" action="createAccount" method="post" modelAttribute="user">
    <input type="text" class="fields" name="username" placeholder="Username"><br>
    <input type="text" class="fields" name="firstName" placeholder="First name"><br>
    <input type="text" class="fields" name="lastName" placeholder="Last Name"><br>
    <input type="text" class="fields" name="email" placeholder="Email id"><br>
    <input type="password" class="fields" name="password" placeholder="Password"><br>
    <input type="password" class="fields" name="password2" placeholder="Confirm Password"><br>
    <input type="submit" class="btn btn-submit" value="Sign Up">
</form:form>
<script type="text/javascript" src="${pageContext.request.contextPath}/particles.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app.js"></script>
</body>
</html>
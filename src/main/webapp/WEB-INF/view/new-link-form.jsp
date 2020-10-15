<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Layer7 | New Link</title>
    <link rel="stylesheet" href="indexStyles.css">
    <link href="https://fonts.googleapis.com/css2?family=Piazzolla:wght@100&display=swap" rel="stylesheet">
</head>
<body>
<div id="particles-js"></div>
<nav class="navbar">
    <ul id="navlinks">
        <li><a class="links" href="">Links</a></li>
        <li><a class="links" href="/playlists">Playlists</a></li>
        <li><a class="links" href="/forums">Forums</a></li>
        <li><a class="links" href="/users">Users</a></li>
        <li><a class="links" href="/profile">Profile</a></li>
    </ul>
</nav>
<h1 id="hero-text">New Link</h1>
<hr>

<form:form action="/addLink" method="post" modelAttribute="link">
    <input type="text" class="form-fields" name="title" placeholder="Title"><br>
    <input type="text" class="form-fields" name="link" placeholder="Paste link here..."><br>
    <textarea name="content" class="form-fields" placeholder="Description" rows="6"></textarea><br>
    <input type="submit" class="btn btn-submit" value="Add Link">
</form:form>
<script type="text/javascript" src="particles.js"></script>
<script type="text/javascript" src="app.js"></script>
</body>
</html>
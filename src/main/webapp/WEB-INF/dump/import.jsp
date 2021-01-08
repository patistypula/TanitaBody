<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: patis
  Date: 08.11.2020
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Strona główna</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body background="/images/dietetyka.jpg">
<center>
    <h1>${message}</h1>
    <image src="${image}" style="height:250px;">
</center>
<br>
<br>

<center>
    <a href="/home">
        <button class="button1">Powrót do strony głównej</button>
    </a>
</center>
</body>
</html>

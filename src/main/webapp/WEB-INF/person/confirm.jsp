<%--
  Created by IntelliJ IDEA.
  User: patis
  Date: 11.11.2020
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Potwierdzenie</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body background="/images/dietetyka.jpg">
<h2><center> Czy jesteś pewna, że chcesz usunąć tego pacjenta?</center></h2>
<form method="post" action="/person/delete">
    <input type="hidden" name="id" value="${persons.id}">
    <center><button class="button1" type="submit" value="Usuń">Usuń</button></center>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<br>
<center>
    <a href="/person/all">
        <button class="button1">Anuluj</button>
    </a>
</center>

</body>
</html>

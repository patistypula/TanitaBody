<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: patis
  Date: 08.11.2020
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Historia pomiarów</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<h2><center>Wybierz który buckup ma zostać wczytany:</center></h2>

<table>
    <tr>
        <a href="/home">
            <button class="button1">Powrót do głównej strony</button>
        </a>
        <br>
        <br>
    </tr>

    <tr></tr>
    <tr>
        <table border="1" width="100%">
            <tr>
                <th>LP</th>
                <th>Data utworzenia</th>
                <th>Wybierz</th>
            </tr>
            <c:forEach items="${files}" var="data" varStatus="loop">
                <tr>
                    <td>${loop.index+1}</td>
                    <td>${data}</td>
                    <td>
                        <center>
                        <form method="post">
                            <input  type="hidden" name="path"  value="${path}">
                            <input  type="hidden" name="fileName" value="${fileName}">
                            <input  type="hidden" name="data" value=${data}>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button class="button1">Wczytaj</button>
                        </form>
                        </center>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </tr>
</table>
</body>
</html>

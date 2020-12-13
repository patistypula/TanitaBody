<%--
  Created by IntelliJ IDEA.
  User: patis
  Date: 08.11.2020
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <h3>Jakiego użytkownika id wpiszesz??</h3>
    <form method="post" action="/test">
    <label>Nr id użytkownika:
    <input type="number" name="id">
    </label>

    <button type="submit">Wyslij</button>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    </form>
</body>
</html>
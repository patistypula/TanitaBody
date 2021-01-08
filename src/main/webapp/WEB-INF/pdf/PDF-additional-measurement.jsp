<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Medion
  Date: 21.10.2020
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Generowanie PDFa</title>
    <link rel="stylesheet" href="/css/style.css">
    <script src="${pageContext.request.contextPath}/js/index2.js"></script>
</head>
<body>
<h3>Lista pomiarów użytkownika: ${person.firstName} ${person.lastName}</h3>
<h4>Zaznacz, które pomiary mają być dodane do pliku pdf (maksymalnie 11 pomiarów):</h4>
<form:form modelAttribute="pdfData" method="post">
    <form:hidden path="firstName" value="${person.firstName}"/>
    <form:hidden path="lastName" value="${person.lastName}"/>
    <table border="1" align="center">
        <tr>
            <th rowspan="2">
                Data
                <br>pomiaru
            </th>
            <th colspan="2">
                Prawa ręka<br>
                RH
            </th>
            <th colspan="2">
                Lewa ręka<br>
                LH
            </th>
            <th colspan="2">
                Prawa noga<br>
                RL
            </th>
            <th colspan="2">
                Lewa noga<br>
                LL
            </th>
            <th colspan="2">
                BODY
            </th>
            <th rowspan="2">
                <SPAN STYLE="writing-mode: vertical-lr;
                                 -ms-writing-mode: tb-rl;
                                 transform: rotate(180deg);">
                    ZAZNACZ
                </SPAN>
            </th>
        </tr>
        <tr>

            <th>
                [fat %]
            </th>
            <th>
                [kg]
            </th>
            <th>
                [fat %]
            </th>
            <th>
                [kg]
            </th>
            <th>
                [fat %]
            </th>
            <th>
                [kg]
            </th>
            <th>
                [fat %]
            </th>
            <th>
                [kg]
            </th>
            <th>
                [fat %]
            </th>
            <th>
                [kg]
            </th>
        </tr>
        <c:forEach items="${measurements}" var="measurement">
            <tr>
                        <td>
                            ${measurement.created}
                        </td>
                        <td>
                            ${measurement.rightArmFat}
                        </td>
                        <td>
                            ${measurement.rightArmMuscle}
                        </td>
                        <td>
                            ${measurement.leftArmFat}
                        </td>
                        <td>
                            ${measurement.leftArmMuscle}
                        </td>
                        <td>
                            ${measurement.rightLegFat}
                        </td>
                        <td>
                            ${measurement.rightLegMuscle}
                        </td>
                        <td>
                            ${measurement.leftLegFat}
                        </td>
                        <td>
                            ${measurement.leftLegMuscle}
                        </td>
                        <td>
                            ${measurement.bodyFat}
                        </td>
                        <td>
                            ${measurement.bodyMuscle}
                        </td>
                        <td>
                            <form:checkbox path="measurements" value="${measurement}" class="measur"/>
                        </td>
                    </tr>
        </c:forEach>
        <tr>
            <td colspan="4">
                <input type="checkbox" id="first" name="all-first">
                <label>11 pierwszych pomiarów</label>
            </td>
            <td colspan="4">
                <input type="checkbox" id="last" name="all-last">
                <label>11 ostatnich pomiarów</label>
            </td>
            <td colspan="4">
                <input type="checkbox" id="clear" name="clear">
                <label>odznacz wszystkie</label>
            </td>
        </tr>
        </table>
        <p id="countArea">Zaznaczono:
            <label id="count">0</label>
            pomiarów</p>
        <table>
            <tr>
                <td class="create"><button class="button1" name="createPDF">Generuj PDF</button></td>
                <td>
                    <button class="button1" name="cancel">Anuluj</button>
                </td>
            </tr>
        </table>
</form:form>
</body>
</html>

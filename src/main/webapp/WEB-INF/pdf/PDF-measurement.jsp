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
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>
<h3>Lista pomiarów użytkownika: ${person.firstName} ${person.lastName}</h3>
<h4>Zaznacz które pomiary mają być dodane do pliku pdf (maksynalnie 11 pomiarów):</h4>
<form:form modelAttribute="pdfData" method="post">
    <form:hidden path="firstName" value="${person.firstName}"/>
    <form:hidden path="lastName" value="${person.lastName}"/>
    <table border="1" align="center">
        <tr>
            <th rowspan="2">
                Data
                <br>pomiaru
            </th>
            <th>
                Procentowa zawartość <br>
                tkanki tłuszczowej <br>
                w organiźmie
            </th>
            <th>
                Masa ciała
            </th>
            <th>
                Procentowa zawartość <br>wody w organiźmie
            </th>
            <th>
                Poziom tłuszczu <br>wisceralnego
            </th>
            <th>
                Masa mięśniowa
            </th>
            <th>
                Wskaźnik budowy <br>ciała
            </th>
            <th>
                Poziom mineralny kości
                <br>(wapnia i innych minerałów)
            </th>
            <th>
                BMI Wiek <br>
                metaboliczny PPM
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
                <img src="pdf/images/body_fat_percentage.jpg" border="0"></img>
            </th>
            <th>
                <img src="pdf/images/body_weight.jpg" border="0"></img>
            </th>
            <th>
                <img src="pdf/images/body_water_percentage.jpg" border="0"></img>
            </th>
            <th>
                <img src="pdf/images/vicera_fat_rating.jpg" border="0"></img>
            </th>
            <th>
                <img src="pdf/images/muscle_mass.jpg" border="0"></img>
            </th>
            <th>
                <img src="pdf/images/physique_rating.jpg" border="0"></img>
            </th>
            <th>
                <img src="pdf/images/bone_mass_ranges.jpg" border="0"></img>
            </th>
            <th>
                <img src="pdf/images/bmi.jpg" border="0"></img>
            </th>
        </tr>
        <c:forEach items="${measurements}" var="measurement">
            <tr>
                        <td>
                            ${measurement.created}
                        </td>
                        <td>
                            ${measurement.bodyFatPercentage}
                        </td>
                        <td>
                            ${measurement.bodyWeight}
                        </td>
                        <td>
                            ${measurement.bodyWaterPercentage}
                        </td>
                        <td>
                            ${measurement.visceralFat}
                        </td>
                        <td>
                            ${measurement.muscleMass}
                        </td>
                        <td>
                            ${measurement.bodyBuildingIndex}
                        </td>
                        <td>
                            ${measurement.boneMass}
                        </td>
                        <td>
                            ${measurement.BMI}
                        </td>
                        <td>
                            <form:checkbox path="measurements" value="${measurement}" class="measur"/>
                        </td>
                    </tr>
        </c:forEach>
        <tr>
            <td colspan="3">
                <input type="checkbox" id="first" name="all-first">
                <label>11 pierwszych pomiarów</label>
            </td>
            <td colspan="4">
                <input type="checkbox" id="last" name="all-last">
                <label>11 ostatnich pomiarów</label>
            </td>
            <td colspan="3">
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
                <td class="create"><button type="submit" name="createPDF">Generuj PDF</button></td>
                <td><button type="submit" name="cancel">Anuluj</button></td>
            </tr>
        </table>
</form:form>
</body>
</html>

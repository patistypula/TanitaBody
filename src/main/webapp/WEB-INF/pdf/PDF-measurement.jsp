<%--
  Created by IntelliJ IDEA.
  User: patis
  Date: 08.11.2020
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Generowanie PDFa</title>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>
<h3>Lista pomiarów użytkownika: ${person.firstName} ${person.lastName}</h3>
<h4>Zaznacz które pomiary mają być dodane do pliku pdf (maksynalnie 13 pomiarów):</h4>
<table border="1" align="center">
    <tr>
        <th rowspan="2">
            Data
            <br>pomiaru
        </th>
        <th>
            Procentowa zawartość <br>
            tkanki tłuszczowej <br>
            w organizmie
        </th>
        <th>
            Masa ciała
        </th>
        <th>
            Procentowa zawartość <br>wody w organizmie
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
                        <input type="checkbox" id="${measurement.id}" name="${measurement.id}" class="measur">
                    </td>
                </tr>
    </c:forEach>
    <tr>
        <td colspan="3">
            <input type="checkbox" id="all-first" name="all-first">
            <label>Wszystkie pomiary od pocztku</label>
        </td>
        <td colspan="4">
            <input type="checkbox" id="all-last" name="all-last">
            <label>Wszystkie ostatnie pomiary</label>
        </td>
        <td colspan="3">

        </td>
    </tr>
    </table>
</body>
</html>

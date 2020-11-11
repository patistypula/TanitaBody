<%--
  Created by IntelliJ IDEA.
  User: patis
  Date: 15.10.2020
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Dodawanie pomiaru</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<h2><center>HISTORIA POMIARÓW</center></h2>

<table>
    <tr>
        <a href="/person/all">
            <button class="button1">Lista wszystkich pacjentów</button>
        </a>
        <br>
        <br>
    </tr>
    <tr></tr>
    <tr>
        <table border="1" width="100%">
            <tr>
                <td><center>Data</center></td>
                <td><center>Data modyfikacji</center></td>
                <td><center>Procentowa zawartość tkanki tłuszczowej</center></td>
                <td><center>Masa ciała</center></td>
                <td><center>Procentowa zawartość wody w organiźmie</center></td>
                <td><center>Poziom tkanki tłuszczowej wisceralnej</center></td>
                <td><center>Masa mięśniowa</center></td>
                <td><center>Wskaźnik budowy ciała</center></td>
                <td><center>Poziom mineralny kości</center></td>
                <td><center>BMI</center></td>
                <td><center>Wiek metaboliczny</center></td>
                <td><center>Podstawowa przemiana materii</center></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td> </td>
                <td><center>%</center></td>
                <td><center>kg</center></td>
                <td><center>%</center></td>
                <td><center>level</center></td>
                <td><center>kg</center></td>
                <td><center>-</center></td>
                <td><center>kg</center></td>
                <td><center>-</center></td>
                <td><center>lat/a</center></td>
                <td><center>kcal</center></td>
                <td></td>
                <td></td>
            </tr>
            <c:forEach items="${measurements}" var="measurement">
                <tr>
                    <td><center>${measurement.created}</center></td>
                    <td><center>${measurement.updated}</center></td>
                    <td><center>${measurement.bodyFatPercentage}</center></td>
                    <td><center>${measurement.bodyWeight}</center></td>
                    <td><center>${measurement.bodyWaterPercentage}</center></td>
                    <td><center>${measurement.visceralFat}</center></td>
                    <td><center>${measurement.muscleMass}</center></td>
                    <td><center>${measurement.bodyBuildingIndex}</center></td>
                    <td><center>${measurement.boneMass}</center></td>
                    <td><center>${measurement.BMI}</center></td>
                    <td><center>${measurement.metabolicAge}</center></td>
                    <td><center>${measurement.basalMetabolicRate}</center></td>
                    <td>
                        <center>
                        <a href="/measurement/edit/${measurement.id}">
                            <button class="button1">Edytuj</button>
                        </a>
                        </center>
                    </td>
                    <td>
                        <center>
                        <a href="/measurement/delete/${measurement.id}">
                            <button class="button1">Usuń</button>
                        </a>
                        </center>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </tr>
</table>
</body>
</html>

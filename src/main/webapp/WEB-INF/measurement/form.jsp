<%--
  Created by IntelliJ IDEA.
  User: patis
  Date: 15.10.2020
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Dodawanie nowego pomiaru</title>
    <link rel="stylesheet" href="/css/style.css">
    <script src="${pageContext.request.contextPath}/js/range.js"></script>
    <style>
        .error {
            color:red;
        }
    </style>
    <meta id="sex" class="${sex}">
    <meta id="age" class="${age}">
    <meta id="height" class="${height}">
</head>
<body>
<h2><center>DODAWANIE NOWEGO POMIARU</center></h2>

<table>
    <tr>
        <a href="/person/all">
            <button class="button1">Lista wszystkich pacjentów</button>
        </a>

        <br>
    </tr>
    <tr></tr>
    <tr>
        <form:form method="post" modelAttribute="measurements">
            <form:hidden path="person.id"></form:hidden>
            <form:hidden path="id"/>
            <form:errors path="bodyFatPercentage" cssClass="error"/><br>
            <label id="row1">Procentowa zawartość tkanki tłuszczowej: <form:input id="bodyFat" path="bodyFatPercentage"/> %
            <label id="row1p"></label>
            </label><br>

            <form:errors path="bodyWeight" cssClass="error"/><br>
            <label id="row2">Masa ciała: <form:input id="bodyWeight" path="bodyWeight"/> kg </label><br>

            <form:errors path="bodyWaterPercentage" cssClass="error"/><br>
            <label id="row3">Procentowa zawartość wody w organiźmie: <form:input id="bodyWater" path="bodyWaterPercentage"/> % </label><br>

            <form:errors path="visceralFat" cssClass="error"/><br>
            <label id="row4">Poziom tkanki tłuszczowej wisceralnej: <form:input id="visceralFat" path="visceralFat"/> level</label><br>

            <form:errors path="muscleMass" cssClass="error"/><br>
            <label id="row5">Masa mięśniowa: <form:input id="muscleMass" path="muscleMass"/> kg</label><br>

            <form:errors path="bodyBuildingIndex" cssClass="error"/><br>
            <label id="row6">Wskaźnik budowy ciała: <form:input id="bodyBuildingIndex" path="bodyBuildingIndex"/>
                <label id="row6p"></label>
            </label><br>

            <form:errors path="boneMass" cssClass="error"/><br>
            <label id="row7">Poziom mineralny kości: <form:input id="boneMass" path="boneMass"/> kg </label><br>

            <form:errors path="BMI" cssClass="error"/><br>
            <label id="row8">BMI: <form:input id="bmi" path="BMI"/>
            <label id="row8p"></label>
            </label><br>

            <form:errors path="metabolicAge" cssClass="error"/><br>
            <label id="row9">Wiek metaboliczny: <form:input id="metabolicAge" path="metabolicAge"/> lat/a</label><br>

            <form:errors path="basalMetabolicRate" cssClass="error"/><br>
            <label id="row10">Podstawowa przemiana materii:
                <form:input id="basalMetabolicRate" path="basalMetabolicRate"/> kcal
            </label><br>
            <br>
            Data pomiaru (dd.mm.yyyy): <form:input path="created"/><br>
            <br>
            <button  class="button1">Zapisz</button>
        </form:form>
    </tr>
</table>

</body>
</html>

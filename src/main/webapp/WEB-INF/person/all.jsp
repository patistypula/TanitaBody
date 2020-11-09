<%--
  Created by IntelliJ IDEA.
  User: patis
  Date: 15.10.2020
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Pacjenci</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div style="float: right;">
    <a href="/logout">
        <button class="button1">Wyloguj się</button>
    </a>
</div>

<h2><center>LISTA PACJENTÓW</center></h2>

<div class="d-flex justify-content-right mb-3">
    <p>Wyszukiwarka pacjentów: </p>
    <form method="get" action="/person/search">
        <label>
            <input type="text" name="lastName" placeholder="Wpisz nazwisko pacjenta"/>
        </label>
        <label>
            <button class="button1" type="submit">Szukaj</button>
        </label>
    </form>
</div>

<table>
    <tr>
        <a href="/person/all">
            <button class="button1">Lista wszystkich pacjentów</button>
        </a>
        <br>
        <br>
    </tr>
    <tr>
        <a href="/person/add">
            <button class="button1">Dodawanie nowego pacjenta</button>
        </a>
        <br>
        <br>
    </tr>
    <tr>

        <table class="table" border="1" width="100%">
            <tr>
                <td>Imię:</td>
                <td>Nazwisko:</td>
                <td>Płeć:</td>
                <td>Wiek:</td>
                <td>Wzrost:</td>
                <td><center>Pomiary podstawowe:</center></td>
                <td><center>Pomiary dodatkowe:</center></td>
                <td><center>Historia pomiarów:</center></td>
                <td></td>
                <td></td>
            </tr>
            <c:forEach items="${persons}" var="person" varStatus="myIndex">
                <tr>
                    <td>${person.firstName}</td>
                    <td>${person.lastName}</td>
                    <td><center>${person.gender}</center></td>
                    <td><center>${ages[myIndex.index]}</center></td>
                    <td><center>${person.height}</center></td>
                    <td>
                        <center><a href="/measurement/add/${person.id}">
                            <button class="button2">Dodaj</button>
                        </a>
                        </center>
                    </td>
                    <td>
                        <center><a href="/additional/add/${person.id}">
                            <button class="button2">Dodaj</button>
                        </a>
                        </center>
                    </td>
                    <td>
                        <center>
                        <a href="/measurement/history/${person.id}">
                            <button class="button2">Podstawowy</button>
                        </a>
                        <a href="/additional/history/${person.id}">
                            <button class="button2">Dodatkowy</button>
                        </a>
                        </center>
                    </td>
                    <td>
                        <center><a href="/person/edit/${person.id}">
                            <button class="button2">Edytuj</button>
                        </a>
                        </center>
                    </td>
                    <td>
                        <center><a href="/person/delete/${person.id}">
                            <button class="button2">Usuń</button>
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

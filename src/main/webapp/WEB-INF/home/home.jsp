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
<h1><center>BAZA PACJENTÓW</center></h1>
<br>
<center>
    <a href="/person/add">
        <button class="button1">Dodawanie nowego pacjenta</button>
    </a>
</center>
<br>
<br>

<center>
    <a href="/person/all">
        <button class="button1">Lista wszystkich pacjentów</button>
    </a>
</center>
<br>
<br>

<center>
    <a href="/dump/export">
        <button class="button1">Stwórz buckup bazy danych</button>
    </a>
</center>
<br>
<br>

<center>
    <a href="/dump/import">
        <button class="button1">Wczytaj bazę danych z pliku</button>
    </a>
</center>
</body>
</html>

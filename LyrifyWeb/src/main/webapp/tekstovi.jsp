<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Tekstovi za pesmu</title>
</head>
<body>
    <h1>Tekstovi za pesmu</h1>
    <c:choose>
        <c:when test="${not empty tekstovi}">
            <ul>
                <c:forEach items="${tekstovi}" var="tekst">
                    <li>
                        <a href="/tekst/${tekst.id}">${tekst.pesma.naziv} - ${tekst.pesma.izvodjac} (${tekst.korisnik.korisnickoIme})</a><br><br>
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p>Nema dostupnih tekstova za ovu pesmu.</p>
        </c:otherwise>
    </c:choose>
    <a href="/">Nazad</a>
</body>
</html>

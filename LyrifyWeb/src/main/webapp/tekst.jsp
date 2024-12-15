<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Tekst pesme</title>
</head>
<body>
    <h1>Tekst pesme</h1>
    
    <c:if test="${not empty tekstPesme}">
        <p><strong>Pesma:</strong> ${tekstPesme.pesma.naziv} - ${tekstPesme.pesma.izvodjac}</p><br>
        <p><strong>Tekst:</strong></p>
        <pre style="font-size: 16px; line-height: 1.5; ">${tekstPesme.tekst}</pre>
        <br><br>
        <p style="font-size: 18px"><strong>Dodao korisnik:</strong> ${tekstPesme.korisnik.korisnickoIme}</p>
    </c:if>
    
    <c:if test="${empty tekstPesme}">
        <p>Tekst pesme nije pronađen.</p>
    </c:if>
    
    <a href="/">Početna</a><br><br><br>
</body>
</html>


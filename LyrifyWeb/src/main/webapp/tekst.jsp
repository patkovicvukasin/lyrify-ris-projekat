<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Tekst pesme</title>
</head>
<body>
    <h1>${tekstPesme.pesma.naziv} - ${tekstPesme.pesma.izvodjac}</h1>
    <p style="font-size: 18px">Tekst pesme:</p>
    <pre style="font-size: 18px; line-height: 1.6; ">${tekstPesme.tekst}</pre>
    <br><a href="/">Početna</a><br><br><br>
</body>
</html>

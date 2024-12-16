<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Moj nalog</title>
</head>
<body>
    <h1>Moj nalog</h1>
    <p>Korisničko ime: ${korisnik.korisnickoIme}</p>
    <p>Email: ${korisnik.email}</p>
    <p>Uloga: ${korisnik.uloga}</p>
    <p>Datum registracije: ${korisnik.datumRegistracije}</p>

    <p><strong>Moji tekstovi:</strong></p>
    <c:if test="${not empty tekstovi}">
       <ul>
           <c:forEach items="${tekstovi}" var="tekst">
               <li>
                   <a href="/tekstKorisnika/${tekst.id}">${tekst.pesma.naziv} - ${tekst.pesma.izvodjac}</a>
               </li>
           </c:forEach>
       </ul>
    </c:if>

	<br>
	<a href="/potvrda">Izloguj se</a>
    <br><br>
    
    <a href="/">Nazad</a>
</body>
</html>

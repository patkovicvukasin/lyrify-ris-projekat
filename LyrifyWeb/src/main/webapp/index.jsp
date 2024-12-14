<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Lyrify - Pocetna</title>
</head>
<body>
    <h1>Dobrodosli na Lyrify!</h1>

    <!-- Forma za pretragu po imenu pesme -->
    <form action="/pretragaPoImenu" method="GET">
        <label for="pesma">Pretrazi tekst za pesmu:</label>
        <input type="text" id="pesma" name="pesma" placeholder="">
        <button type="submit">Pretrazi</button>
    </form>

    <hr>

    <!-- Forma za pretragu po zanru -->
    <form action="/pretragaPoZanru" method="GET">
        <label for="zanr">Ili izaberite zanr i pregledajte dostupne pesme:</label>
        <select name="zanrId" id="zanr">
            <c:forEach items="${zanrovi}" var="zanr">
                <option value="${zanr.id}">${zanr.naziv}</option>
            </c:forEach>
        </select>
        <button type="submit">Pretrazi</button>
    </form>
    
    <hr>
    
    <c:choose>
        <c:when test="${not isLoggedIn}">
            <div margin-top: 20px;">
       		 	<a href="/prijava"><button type="button">Prijava</button></a>
        		<a href="/registracija"><button type="button">Registracija</button></a>
    		</div>
        </c:when>
        <c:otherwise>
            <a href="/dodajTekst"><button type="button">Dodaj tekst</button></a>
            <a href="/mojNalog"><button type="button">Moj nalog</button></a>
        </c:otherwise>
    </c:choose>
</body>
</html>


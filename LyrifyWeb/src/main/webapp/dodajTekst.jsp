<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Dodaj tekst pesme</title>
</head>
<body>
    <h1>Dodaj tekst</h1>
    
    <c:if test="${isAdmin}">
	    <form action="/processDodajZanr" method="POST">
	        <label for="zanrNaziv">Dodaj novi žanr:</label>
	        <input type="text" id="zanrNaziv" name="zanrNaziv" required>
	        <button type="submit">Dodaj žanr</button>
	    </form>
	</c:if><br>
    
    <!-- Forma za odabir zanra -->
    <form action="/dodajTekst" method="GET">
        <label for="zanrId">Izaberi žanr:</label>
        <select name="zanrId" id="zanrId">
            <c:forEach items="${zanrovi}" var="zanr">
                <option value="${zanr.id}"
                    <c:if test="${zanr.id == param.zanrId}">selected</c:if>>
                    ${zanr.naziv}
                </option>
            </c:forEach>
        </select>
        <button type="submit">Prikaži pesme</button><br>
    </form><br>
    
    <c:if test="${param.zanrId != null}">
        <c:choose>
            <c:when test="${not empty pesme}">
                <!-- Forma za unos teksta ako postoje pesme -->
                <form action="/processDodajTekst" method="POST">
                    <input type="hidden" name="zanrId" value="${param.zanrId}" />

                    <label for="pesmaId">Izaberi pesmu:</label>
                    <select name="pesmaId" id="pesmaId">
                        <c:forEach items="${pesme}" var="p">
                            <option value="${p.id}">${p.naziv} - ${p.izvodjac}</option>
                        </c:forEach>
                    </select>
                    
                    <a href="/dodajPesmu"><button type="button">Dodaj novu pesmu</button></a>
                    
                    <br><br>

                    <label for="tekst">Unesi tekst:</label><br>
                    <textarea id="tekst" name="tekst" rows="5" cols="40"></textarea>
                    <br><br>

                    <button type="submit">Sačuvaj tekst</button>
                </form>
            </c:when>
            <c:otherwise>
                <!-- Poruka i dugme za dodavanje pesme ako nema dostupnih pesama -->
                <p>Za odabrani žanr trenutno nema dostupnih pesama.</p>
                <a href="/dodajPesmu"><button type="button">Dodaj novu pesmu</button></a><br>
            </c:otherwise>
        </c:choose>
    </c:if>
    <br>
    <a href="/">Nazad</a>
</body>
</html>

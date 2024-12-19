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
        
        <!-- Prosečna ocena -->
        <p style="font-size: 18px"><strong>Prosečna ocena:</strong> 
            <c:choose>
                <c:when test="${prosecnaOcena != null}">
                    ${prosecnaOcena}
                </c:when>
                <c:otherwise>
                    Još uvek nema ocena.
                </c:otherwise>
            </c:choose>
        </p>
        
        <!-- Forma za ocenjivanje -->
        <c:if test="${ulogovaniKorisnik != null && ulogovaniKorisnik.id != tekstPesme.korisnik.id}">
	        <form action="/oceniTekst" method="POST">
	            <input type="hidden" name="tekstId" value="${tekstPesme.id}">
	            <label for="ocena">Oceni tekst:</label>
	            <select name="ocena" id="ocena">
	                <option value="1">1</option>
	                <option value="2">2</option>
	                <option value="3">3</option>
	                <option value="4">4</option>
	                <option value="5">5</option>
	            </select>
	            <button type="submit">Oceni</button>
	        </form>
        </c:if><br>
        
        <c:if test="${isAdmin}">
            <form action="/verifikacija" method="GET">
    			<input type="hidden" name="tekstId" value="${tekstPesme.id}">
    			<button type="submit">Verifikuj</button>
			</form>
        </c:if>
        
        <p style="font-size: 18px"><strong>Dodao korisnik:</strong> ${tekstPesme.korisnik.korisnickoIme}</p>
    </c:if>
    
    <c:if test="${empty tekstPesme}">
        <p>Tekst pesme nije pronađen.</p>
    </c:if>
    
    <a href="/">Početna</a><br><br><br>
</body>
</html>


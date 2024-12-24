<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Prijava</title>
</head>
<body>
    <h1>Prijava</h1>
    <form action="/processPrijava" method="POST">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <brfor><br><br>
        <label ="lozinka">Lozinka:</label>
        <input type="password" id="lozinka" name="lozinka" required>
        <br><br>
        <button type="submit">Prijavi se</button>
    </form><br><br>
    <a href="/"><button type="button">Nazad na početnu</button></a>
</body>
</html>

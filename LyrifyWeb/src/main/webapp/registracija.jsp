<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registracija</title>
</head>
<body>
    <h1>Registracija</h1>
    <form action="/processRegistracija" method="POST">
        <label for="username">Korisnicko ime:</label>
        <input type="text" id="username" name="username" required>
        <br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br><br>
        <label for="lozinka">Lozinka:</label>
        <input type="password" id="lozinka" name="lozinka" required>
        <br><br>
        <button type="submit">Registruj se</button>
    </form><br><br>
    <a href="/">Nazad na početnu</a>
</body>
</html>

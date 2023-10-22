<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/menu.css">
    <link rel="stylesheet" href="/css/catalogo.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <title>Alta protectora</title>
</head>
<body>

	<div class="titulo">
		<h1>Alta de Protectoras</h1>
		<h1>${mensaje }</h1>
	</div>
<br>
<br> 
<br>

<br>

<form action="/protectora/gestion/alta" method="post">
	<p><input type="text" name="nombre" >Nombre</p>
	<p><input type="text" name="direccion" >Dirección</p>
	<p><input type="text" name="descripcion">Descripcion</p>
	<p><input type="text" name="url_logo">Url Logo</p>
	<p><input type="submit" value="Enviar"></p>
</form>
	

</body>

</html>
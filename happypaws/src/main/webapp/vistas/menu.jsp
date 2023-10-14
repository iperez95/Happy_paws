<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<sec:authentication property="name"/>
    <sec:authorize access="hasAuthority('Adoptante')">Hola @name Eres un ADOPTANTE</sec:authorize>
	<sec:authorize access="hasAuthority('Administrador')">Eres un ADMON</sec:authorize>
	<sec:authorize access="hasAuthority('Protectora')">Eres una PROTECTORA</sec:authorize>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="/">Happy Paws</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
        <sec:authorize access="hasAuthority('Protectora')">
        <li class="nav-item active">
            <a class="nav-link" href="xxxxxxx">solo ve protectora</a>
        </li>
        </sec:authorize>
        <li class="nav-item">
            <a class="nav-link" href="/">Editar Protectora</a>
        </li>
        <sec:authorize access="hasAnyAuthority('Administrador')">
        <li class="nav-item">
            <a class="nav-link" href="/protectora/todas">Listado Protectoras</a>
        </li>
        </sec:authorize>   
        <sec:authorize access="hasAuthority('Administrador')">
        <li class="nav-item">
            <a class="nav-link" href="/admin/panel">Panel Admin</a>
        </li>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
        <li class="nav-item"><a class="nav-link"
                                href="/login">Iniciar Sesión</a></li>
                            <li class="nav-item"><a class="nav-link"
                                href="/registro">Registrarse</a></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <li class="nav-item"><a class="nav-link"
                    href="/logout">Cerrar Sesión</a></li>
        </sec:authorize>
    </ul>
  </div>
</nav>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	</body>
	</html>
	